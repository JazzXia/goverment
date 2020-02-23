package com.qtatelier.goverment.basics.bloguser.service;

import com.qtatelier.dto.BlogUser;
import com.qtatelier.goverment.basics.bloguser.mapper.BlogUserMapper;
import com.qtatelier.goverment.thread.UserException;
import com.qtatelier.goverment.response.UserResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-02-03 10:33
 */
@Service
public class BlogUserService {

    @Autowired
    private BlogUserMapper blogUserMapper;

    /**
     * 通过用户名查询
     *
     * @param blogUser
     * @return
     */
    public UserResponse findUserByname(BlogUser blogUser) {
        if (blogUser != null) {
            //System.out.println(blogUserMapper.selectByUserName(blogUser));
            return blogUserMapper.selectByUserName(blogUser);
        } else {
            throw new UserException("无当前用户");
        }
    }


    public BlogUser selectByPrimaryKey(String userId){
        if (StringUtils.isNotBlank(userId)){
            return blogUserMapper.selectByPrimaryKey(userId);
        }
        throw new UserException("用户id不能为空");
    }

}
