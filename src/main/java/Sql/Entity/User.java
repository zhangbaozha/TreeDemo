package Sql.Entity;

import dataLayer.Common;

/**
 * @author zhw
 * Date:2022/10/26
 */
public class User {
    private String account;
    private String password;
    private String power;

    public User() {
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
    public boolean equals(Object obj) {

        if (obj instanceof dataLayer.User) {
            if (this.getAccount().equals(((dataLayer.User) obj).getAccount())
                    && this.getPassword().equals(((dataLayer.User) obj).getPassword())) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public static boolean ifInfoOK(String userName, String userPassword){
        Sql.Entity.User temp;
        for (int i = 0; i < Common.userList.size(); i++) {
            temp = Common.userList.get(i);
            if (temp.getAccount().equals(userName)
                    && temp.getPassword().equals(userPassword) ) {
                Common.currentUser = temp;//存储当前账户，可以查权限
                return true;
            }
        }
        return false;
    }

    //检测是否此用户名已存在，存在返回true
    public static boolean ifNameExist(String userName){
        Sql.Entity.User temp;
        for (int i = 0; i < Common.userList.size(); i++) {
            temp = Common.userList.get(i);
            if (temp.getAccount().equals(userName) ) {
                return true;
            }
        }
        return false;
    }
}
