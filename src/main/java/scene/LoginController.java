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
 * @author Ҧ����
 * 2020/6/17,14:20
 * ��ֵĳ���������
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

    //��ʼ��¼�Լ����
    public void login(ActionEvent actionEvent) {
        if (User.ifInfoOK(userAccount.getText(),userPassword.getText())){
            System.out.println("valid account and password");
            Common.currentStage.close();
            System.out.println("login scene closed");
            Main.startJiapuScene();
        }else {
            Common.sendAlert(false,"�û������������,����������");
        }
    }

    public void register(ActionEvent actionEvent) {

        //�������ظ��û���
        if (User.ifNameExist(userAccount.getText())){
            Common.sendAlert(false,"�û����Ѵ���,����������");
            return;
        }
        //���������λ������
        if(userPassword.getText().length()<6){
            Common.sendAlert(false,"����δ�ﵽ��λ�����ϣ�����������");
            return;
        }
        Common.userList.add(new User(userAccount.getText(),userPassword.getText()));
        Common.sendAlert(true,"�ɹ�������ͨ�˻�����ص���¼�����¼");
    }


}
