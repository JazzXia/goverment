package com.qtatelier.goverment.web;

import com.qtatelier.common.aop.SystemControllerLog;
import com.qtatelier.config.CodeBusiness;
import com.qtatelier.config.CodeEnum;
import com.qtatelier.config.ResultView;
import com.qtatelier.dev_util.commons.annotation.UserLoginToken;
import com.qtatelier.goverment.basics.proposal_manage.model.ProposalManger;
import com.qtatelier.goverment.basics.proposal_manage.service.ProposalManageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-03-01 11:57
 */
@RestController
@RequestMapping("/propsal")
public class PropsalManageController {

    @Autowired
    private ProposalManageService proposalManageService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "提交提案", notes = "提交提案")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @PostMapping("/addPropsal")
    @UserLoginToken
    @SystemControllerLog(description = "提交提案", optType = CodeBusiness.OPT_TYPE.ADD_CODE, moduleName = CodeBusiness.MODULE_NAME.CUSTOMER_MODULE)
    public ResultView addPropsal(@RequestBody ProposalManger proposalManger,String token) {
        String logStr = "提交提案";
        ResultView resultView = null;
        try {
            CodeEnum codeEnum = proposalManageService.addPropposal(proposalManger);
            if (codeEnum != CodeEnum.SUCCESS){
                resultView = new ResultView(codeEnum,"各项参数不能为空哦");
            }else {
                resultView = new ResultView(codeEnum,"成功");
            }
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, "提交提案异常", e);
        } finally {
            logger.info(logStr + "结束");
        }
        return resultView;
    }

}
