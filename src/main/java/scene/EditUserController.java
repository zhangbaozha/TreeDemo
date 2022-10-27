package scene;

import dataLayer.Common;
import Sql.Entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Ҧ����
 * 2020/6/17,22:30
 * ��ֵĳ���������
 */
public class EditUserController implements Initializable {


    public TextArea UserListShower;
    public Button freshButton;
    public Button addButton;
    public Button deleteButton;
    public Button editButton;
    public TextField addUserName;
    public TextField addUserPassword;
    public TextField deleteUserName;
    public TextField editedUserName;
    public TextField editUserNameTo;
    public TextField editUserPasswordTo;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void freshList(ActionEvent actionEvent) {
        StringBuilder tempString = new StringBuilder();
        tempString.append("�û��б�\n");
        for (int i = 0; i < Common.userList.size(); i++) {
            tempString.append(Common.userList.get(i).getAccount()+"\n");
        }
        UserListShower.setText(tempString.toString());
    }

    public void add(ActionEvent actionEvent) {
        //�������ظ��û���
        if (User.ifNameExist(addUserName.getText())){
            Common.sendAlert(false,"�û����Ѵ���,����������");
            return;
        }
        //���������λ������
        if(addUserPassword.getText().length()<6){
            Common.sendAlert(false,"����δ�ﵽ��λ�����ϣ�����������");
            return;
        }
        Common.userList.add(new User(addUserName.getText(),addUserPassword.getText()));
        Common.sendAlert(true,"�ɹ�������ͨ�˻�");
    }

    public void delete(ActionEvent actionEvent) {
        if (deleteUserName.getText().equals(Common.currentUser.getAccount())){
            Common.sendAlert(false,"������ɾ�����ڲ������˺�");
        }else if (User.ifNameExist(deleteUserName.getText())){
            for (int i = 0; i < Common.userList.size(); i++) {
                if (Common.userList.get(i).getAccount().equals(deleteUserName.getText())){
                    Common.userList.remove(i);
                    break;
                }
            }
            Common.sendAlert(true,"�ɹ�ɾ��");
        }else {
            Common.sendAlert(true,"�û���������");
        }
    }

    public void edit(ActionEvent actionEvent) {
        if (!User.ifNameExist(editedUserName.getText())){
            Common.sendAlert(true,"Ҫ�޸ĵ��û���������");
            return;
        }
        if (User.ifNameExist(editUserNameTo.getText())){
            Common.sendAlert(false,"�û����Ѵ���,����������");
            return;
        }
        if(editUserPasswordTo.getText().length()<6){
            Common.sendAlert(false,"����δ�ﵽ��λ�����ϣ�����������");
            return;
        }
        int i;//���Ҫ�޸ĵ�λ��
        for (i = 0; i < Common.userList.size(); i++) {
            if (Common.userList.get(i).getAccount().equals(editedUserName.getText())){
                break;
            }
        }
        User temp = Common.userList.get(i);
        temp.setAccount(editUserNameTo.getText());
        temp.setPassword(editUserPasswordTo.getText());
        Common.sendAlert(true,"�޸ĳɹ�");
    }
}
