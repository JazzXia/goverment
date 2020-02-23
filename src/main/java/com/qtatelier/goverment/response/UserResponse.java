package com.qtatelier.goverment.response;

import lombok.Data;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-02-03 10:49
 */
@Data
public class UserResponse {

    private String userId;
    private String username;
    private String password;
    private String sign;
    private String location;
    private String webUrl;
    private String callself;
    private String nickName;
    private String imageName;
    private String userEmail;
    private String token;
    private String salt;
    private String roleType;
    private String roleTypeName;

}
