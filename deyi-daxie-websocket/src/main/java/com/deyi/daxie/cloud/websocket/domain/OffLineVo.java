package com.deyi.daxie.cloud.websocket.domain;

import lombok.Data;

/**
 * Description: 强制下线参数
 *
 * @author Chen Xu
 * @date 2023/6/7
 */
@Data
public class OffLineVo {

    private String truckNo;
    private String messageType = "Logout";
    private User data = new User();

    public void setUsername(String username) {
        this.data.setUsername(username);
    }

    public void setPassword(String password) {
        this.data.setPassword(password);
    }
}
@Data
class User{
    private String username;
    private String password;

}