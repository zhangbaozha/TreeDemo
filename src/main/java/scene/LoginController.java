package scene;

import Sql.Dao.UserMapper;
import Sql.MybatisUtils;
import dataLayer.Common;

import Sql.Entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.ibatis.session.SqlSession;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author 姚君彦
 * 2020/6/17,14:20
 * 奇怪的程序增加了
 */
public class LoginController implements Initializable {

    public TextField userAccount;
    public Button loginButton;
    public Button registerButton;
    public PasswordField userPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SqlSession sqlSession = MybatisUtils.getSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.findAll();
        Common.userList = users;
    }

    //开始登录以及检查
    public void login(ActionEvent actionEvent) {
        if (User.ifInfoOK(userAccount.getText(),userPassword.getText())){
            System.out.println("valid account and password");
            Common.currentStage.close();
            System.out.println("login scene closed");
            Main.startJiapuScene();
        }else {
            Common.sendAlert(false,"用户名或密码错误,请重新输入");
        }
    }

    public void register(ActionEvent actionEvent) {

        //不允许重复用户名
        if (User.ifNameExist(userAccount.getText())){
            Common.sendAlert(false,"用户名已存在,请重新输入");
            return;
        }
        //密码必须六位及以上
        if(userPassword.getText().length()<6){
            Common.sendAlert(false,"密码未达到六位及以上，请重新输入");
            return;
        }
        Common.userList.add(new User(userAccount.getText(),userPassword.getText()));
        Common.sendAlert(true,"成功建立普通账户，请回到登录界面登录");
    }


}
