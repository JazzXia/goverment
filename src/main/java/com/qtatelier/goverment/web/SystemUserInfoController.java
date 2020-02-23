package com.qtatelier.goverment.web;

import com.alibaba.fastjson.JSONObject;
import com.qtatelier.common.aop.SystemControllerLog;
import com.qtatelier.config.CodeBusiness;
import com.qtatelier.config.CodeEnum;
import com.qtatelier.config.ResultView;
import com.qtatelier.config.ToolRedis;
import com.qtatelier.config.token.JWTUtil;
import com.qtatelier.dev_util.commons.annotation.UserLoginToken;
import com.qtatelier.dto.BlogUser;
import com.qtatelier.goverment.basics.bloguser.service.BlogUserService;
import com.qtatelier.goverment.response.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-02-03 10:07
 */
@RestController
@Api(value = "用户信息", tags = "用户信息列表")
@RequestMapping("/role")
public class SystemUserInfoController{

    //日志
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BlogUserService blogUserService;
    @Autowired
    private JWTUtil tokenService;

    @Autowired
    private ToolRedis redis;

    @Autowired
    private RedisTemplate redisTemplate;


    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @GetMapping("/detail")
    @UserLoginToken
    //@SystemControllerLog(description = "登录并展示当前信息", optType = CodeBusiness.OPT_TYPE.SEARCH_CODE, moduleName = CodeBusiness.MODULE_NAME.USER_MODULE)
    public ResultView getUser(String userId, String token) {
        String logStr = "获取用户";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始={}", token, userId);
            BlogUser blogUser = blogUserService.selectByPrimaryKey(userId);
            if (null == blogUser) {
                logger.info(logStr + "失败");
                return new ResultView(CodeEnum.ERROR_404, "暂无用户信息");
            }
            return new ResultView(CodeEnum.SUCCESS, "获取用户成功", blogUser);
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, "获取用户异常");
        } finally {

            logger.info(logStr + "结束");
        }
        return resultView;
    }

    @ApiOperation(value = "添加用户", notes = "添加新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @PostMapping("/add")
    public ResultView insertUser(BlogUser blogUser, String token) {
        return null;
    }



    @ApiOperation(value = "登录", notes = "登录账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", dataType = "String")
    })
    @PostMapping("/login")
    @SystemControllerLog(description = "用户登录", optType = CodeBusiness.OPT_TYPE.LOGIN_CODE, moduleName = CodeBusiness.MODULE_NAME.LOGIN_MODULE)
    public ResultView login(String userName, String password) {
        BlogUser blogUser = new BlogUser();
        blogUser.setPassword(password);
        blogUser.setUsername(userName);
        logger.info("用户" + blogUser.getUsername() + "尝试登录");
        String logStr = "用户登录";
        ResultView resultView = null;
        try {
            UserResponse userForBase = blogUserService.findUserByname(blogUser);
            if (userForBase == null) {
                logger.warn(logStr + "账号" + blogUser.getUsername() + "不存在！");
                resultView = new ResultView(CodeEnum.ERROR_403, "登录失败，账号或密码错误");
                return resultView;
            } else {
                blogUser.setUsername(userForBase.getUsername());
                blogUser.setRoleTypeName(userForBase.getRoleTypeName());
                blogUser.setRoleType(userForBase.getRoleType());
                blogUser.setUserEmail(userForBase.getUserEmail());
                blogUser.setImageName(userForBase.getImageName());
                blogUser.setNickName(userForBase.getNickName());
                blogUser.setUserId(userForBase.getUserId());
                if (!userForBase.getPassword().equals(DigestUtils.md5Hex(userForBase.getSalt() + blogUser.getPassword().trim()))) {
                    logger.warn(logStr + "密码错误");
                    resultView = new ResultView(CodeEnum.ERROR_403, "登录失败，账号或密码错误");
                    return resultView;
                }else {
                    String token = tokenService.getToken(blogUser);
                    userForBase.setToken(token);
                    blogUser.setToken(token);
                    //用户信息存入redis中
                    redis.set(CodeBusiness.TOKEN_ACCESS_KEY + token, blogUser, CodeBusiness.SESSTION_TIME);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("user", userForBase);
                    logger.info(logStr + "登录成功");
                    resultView = new ResultView(CodeEnum.SUCCESS, "登陆成功", jsonObject);
                    return resultView;
                }
            }
        } catch (Exception e) {
            logger.error(logStr + "用户登录失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, "登录系统异常");
        } finally {
            logger.info(logStr + "用户：" + blogUser.getUsername() + "登录结束");
        }
        return resultView;
    }


    @ApiOperation(value = "登出", notes = "退出账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @GetMapping("/logout")
    @UserLoginToken
    @SystemControllerLog(description = "退出账号", optType = CodeBusiness.OPT_TYPE.LOGOUT_CODE, moduleName = CodeBusiness.MODULE_NAME.LOGIN_MODULE)
    public ResultView logout(String token) {
        String logStr = "用户退出";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始", token);
            boolean flag = redis.delKey(CodeBusiness.TOKEN_ACCESS_KEY);
            if (flag) {
                resultView = new ResultView(CodeEnum.SUCCESS, "退出成功");
            } else {
                resultView = new ResultView(CodeEnum.ERROR_500, "退出失败");
            }
            redisTemplate.delete(CodeBusiness.TOKEN_ACCESS_KEY+ token);
            return resultView;
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, "退出异常", e);
        } finally {
            logger.info(logStr + "结束");
        }
        return resultView;
    }
}
