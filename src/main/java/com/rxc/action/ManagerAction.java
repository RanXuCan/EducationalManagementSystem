package com.rxc.action;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 23:22
 */
@Controller
public class ManagerAction extends ActionSupport {

    @Getter
    @Setter
    private String managerAccount;

    @Getter
    @Setter
    private String managerPassword;

    public String managerLogin() {             //管理员登陆
        HttpServletRequest request = ServletActionContext.getRequest();   //请求作用域保存登录情况的信息
        String message;
        if (managerAccount.trim().equals("admin") && managerPassword.trim().equals("admin")) {
            return SUCCESS;
        } else {
            message = "账号或密码错误!";
            request.setAttribute("message", message);
            return INPUT;
        }
    }
}