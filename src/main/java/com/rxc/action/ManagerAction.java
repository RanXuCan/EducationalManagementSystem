package com.rxc.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/22 23:22
 */
public class ManagerAction extends ActionSupport {

    /**
     *
     */
    private String managerAccount;
    private String managerPassword;

    public String getManagerAccount() {
        return managerAccount;
    }

    public void setManagerAccount(String managerAccount) {
        this.managerAccount = managerAccount;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String managerLogin() {             //管理员登陆
        HttpServletRequest request = ServletActionContext.getRequest();   //请求作用域保存登录情况的信息
        String message = null;
        if (managerAccount.trim().equals("admin") && managerPassword.trim().equals("admin")) {
            return SUCCESS;
        } else {
            message = "账号或密码错误!";
            request.setAttribute("message", message);
            return INPUT;
        }
    }
}

