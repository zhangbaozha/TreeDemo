package scene;

import dataLayer.Common;
import dataLayer.LiveState;
import dataLayer.Member;
import dataLayer.Power;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * @author Ҧ����
 * 2020/6/17,16:36
 * ��ֵĳ���������
 */
public class JiapuController implements Initializable {

    public Button editButton;
    public Button searchButton;
    public TextField nameToSearch;
    public TextField name;
    public TextField gender;
    public TextField isAlive;
    public TextField birthday;
    public TextField deathday;
    public TextField marriage;
    public TextField address;
    public TextField generation;
    public TextField father;
    public TextField mother;
    public TextField descendents;
    public TextField extra;
    public TextArea chronicleList;
    public Button checkButton1;
    public TextField checkRelative2;
    public TextField checkRelative1;
    public TextField checkResult;
    public Canvas canvas;
    public Button editUserButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //������
        Common.currentTree = Main.loadFamilyTree();
//        if (Common.currentTree!=null)
//            System.out.println("good!");

    }

    private void setChronicleList() {
        Common.currentTree.resort();
        LinkedList<Member> temp = Common.currentTree.getSortedlist();
        StringBuilder tempString = new StringBuilder();
        tempString.append("���������򣺣���������������\n");
        Calendar now = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
        int month = now.get(Calendar.MONTH)+1;//calendar ��õ��·��Ǵ�0��ʼ��
        int date = now.get(Calendar.DATE);
//        System.out.println("������"+month+" / "+date);
        Member tempMem;
        for (int i = 0; i < temp.size(); i++) {
            tempMem = temp.get(i);
            if (tempMem.getIsAlive()== LiveState.alive && tempMem.getBirthday()[1]==month && tempMem.getBirthday()[2] == date)
                tempString.append(tempMem.getBirthday()[0]+"  "+tempMem.getName()+" ������ta�����գ�\n");
            else
                tempString.append(tempMem.getBirthday()[0]+"  "+tempMem.getName()+"\n");
        }
//        System.out.println(tempString.toString());
//        for (int k = 0; k < Common.currentTree.getSortedlist().size(); k++) {
//            System.out.println(Common.currentTree.getSortedlist().get(k));
//        }
        chronicleList.setText(tempString.toString());
    }

    public void search(ActionEvent actionEvent) {
//        if (Common.currentTree.isInList(nameToSearch.getText())){
//        System.out.println("��ֵ���ԣ�"+nameToSearch.getText());
        if (nameToSearch.getText().length()<1){
            setChronicleList();
            TreeDrawing.draw(canvas,Common.currentTree);
        }else {
            Common.currentMember = Common.currentTree.find(nameToSearch.getText());
//        }else{
            if (Common.currentMember == null) {
                //����ˢ�¹��ܣ����������Լ�����
                setChronicleList();
                TreeDrawing.draw(canvas,Common.currentTree);
                Common.sendAlert(false, "���ֲ�����");
            }
//        }
            else {
                name.setText(Common.currentMember.getName());
                gender.setText(Common.currentMember.getGenderString());
                isAlive.setText(Common.currentMember.getIsAliveString());
                birthday.setText(Common.currentMember.getBirthdayString());
                deathday.setText(Common.currentMember.getDeathdayString());
                marriage.setText(Common.currentMember.getIfMarriedString());
                address.setText(Common.currentMember.getAddress());
                generation.setText(Common.currentMember.getGeneration() + "");
                if (Common.currentMember.getFather() != null)
                    father.setText(Common.currentMember.getFather().getName());
                if (Common.currentMember.getMother() != null)
                    mother.setText(Common.currentMember.getMother().getName());
                descendents.setText(Common.currentMember.getDescendentsString());
                extra.setText(Common.currentMember.getExtraMessage());
            }
        }

    }

    public void toEditScene(ActionEvent actionEvent) {
        //ֻ�й����߿��Խ��뵽�޸Ľ���
        if (Common.currentUser.getPower()== Power.admin.toString())
            Main.startEditScene();
        else
            Common.sendAlert(false,"��û��Ȩ���޸�");
    }


    public void check(ActionEvent actionEvent) {
        if (checkRelative1.getText()!=""&&checkRelative2.getText()!=""){
            if (Common.currentTree.isDirectRelated(checkRelative1.getText(),checkRelative2.getText())){
                checkResult.setText("ֱϵ");
            }else{
                checkResult.setText("��ֱϵ");
            }
        }
    }


    public void toEditUserScene(ActionEvent actionEvent) {
        if (Common.currentUser.getPower()== Power.admin.toString())
            Main.startEditUserScene();
        else
            Common.sendAlert(false,"��û��Ȩ���޸�");
    }
}
