package scene;

import dataLayer.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Ҧ����
 * 2020/6/17,22:30
 * ��ֵĳ���������
 */
public class EditController implements Initializable {

    public Button editButton;
    public TextField name;
    public ChoiceBox gender;
    public ChoiceBox isAlive;
    public TextField birthYear;
    public TextField birthMonth;
    public TextField birthDay;
    public TextField deathYear;
    public TextField deathMonth;
    public TextField deathDay;
    public ChoiceBox marriage;
    public TextField address;
    public TextField extra;

    public TextField addParent;
    public TextField removeName;
    public Button addButton;
    public Button removeButton;
    public TextField movedName;
    public TextField moveUnderParent;
    public Button moveButton;
    public TextField nameToSearch;
    public Button searchButton;
    public TextField generation;
    public TextField father;
    public TextField mother;
    public TextField descendents;

    private Member currentMember;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gender.getItems().addAll(GenderType.values());//ʹ��enum���ͼ��ص�choice box
        marriage.getItems().addAll(MarriageState.values());//ʹ��enum���ͼ��ص�choice box
        isAlive.getItems().addAll(LiveState.values());//ʹ��enum���ͼ��ص�choice box
    }

    //choice box��defaultΪnull
    //textfield��defaultΪ""

    public void addInTree(ActionEvent actionEvent) {
        String addparentString = addParent.getText();
        String nameString = name.getText();
        //����Ҫ���������ֵ���Ϣ�Լ��Ա𣨹�ϵ�����丸ĸ�����ܸ�ֵ�����ֲ����ظ�
        /*��¼һ�¿��ظ���˼·��
        * ���ӣ�û������
        * ���ң���tree��ά��һ����̬�б���¼ÿ�����ֵĸ�����ÿ�μ���ɾ��ʱ��Ҫ�޸ģ�ͬʱ��ɾ�������ﶼ��Ҫ�޸ĸ�����
        *       ��ά��ÿһ����������ʵ�֣�һ�����ɷ���ÿ��ˢ��
        * ɾ������ʵ�ֵķ�������ɾ�������ظ���
        * ������ɾ���Ķ�λ������editcontroller�е��õķ�������Ϊ��¼�Ľ����Ҳ��memberʵ����ÿ�θ��û���һ����ȷ����Ե�ǰmemberɾ��
        *       Ȼ�����ʱ���ɲ����������һ����
        *       �����ͬ�����Բ�����һ��ʵ��������list�еĸ�����ͨ����ÿ��member���������һ��flag��¼��û�б������ʵ�֣�
        *       ÿ�ν�����Ҫ��flag��λ�������ٴα�����ȫ�����û����ٿ�һ��member���������¼�Ѿ������ʵ��
        * ������ɾ������һ��˼·���ڻ��������ⲿ�ٽ�����ά���ɱ����飬ÿ������Ϊ��һ�������������ͬ�������ǣ�Ȼ��ͨ�������ά���鶨λ����¼
        * �޸ģ�ͨ��������һ����λ�������û������
        * �ƶ���ͬ�ϣ���λ�����������û�ȷ��
        * */
        if (!addparentString.equals("") && !nameString.equals("") && !gender.getValue().equals("")&& Common.currentTree.find(nameString)==null){
            if (Common.currentTree.insert(addParent.getText(),
                    new Member(
                        name.getText(), (GenderType) gender.getValue(),(LiveState) isAlive.getValue(),
                            transInt(birthYear.getText()),transInt(birthMonth.getText()),transInt(birthDay.getText()),
                            transInt(deathYear.getText()),transInt(deathMonth.getText()),transInt(deathDay.getText()),
                            (MarriageState) marriage.getValue(),address.getText(),extra.getText()
                    )
            ))
                Common.sendAlert(true,"��ӳɹ�");
            else
                Common.sendAlert(false,"���ʧ�ܣ�δ�ҵ�ָ���ĸ�ĸ");
        }else{
            Common.sendAlert(false,"�����������Ҫ���������ֵ���Ϣ�Լ��Ա𣨿�δ֪�����ܸ�ֵ���������ֲ����ظ�");
        }
//        System.out.println( (GenderType) gender.getValue());
    }

    public void removeFromTree(ActionEvent actionEvent) {//remove��������У�����Ҫ�������������
//        if(Common.currentTree.remove(removeName.getText())){
        Common.currentTree.remove(removeName.getText());
            Common.sendAlert(true,"�Ƴ��ɹ�");
//        }else{
//            Common.sendAlert(false,"�Ƴ�ʧ��");
//        }
    }

    public void moveInsideTree(ActionEvent actionEvent) {//ͬ��
        if(Common.currentTree.move2des(movedName.getText(),moveUnderParent.getText())){
            Common.sendAlert(true,"�ƶ��ɹ�");
        }else{
            Common.sendAlert(false,"�ƶ�ʧ��");
        }
    }

    //�˲��ֿ���ȥ���ظ����㲿�ּ��٣��������кܶ�ط����Ժϲ�����
    public void editPerson(ActionEvent actionEvent) {
        boolean flag = false;
        if (Common.currentTree.find(name.getText())==null ||Common.currentMember.getName().equals(name.getText()))//Ҫ��������ֲ����ڻ���δ�޸�
            if (gender.getValue()!=null)
                flag = true;
        if(flag) {
            Common.currentMember.setName(name.getText());
            Common.currentMember.setGender((GenderType) gender.getValue());
            if (isAlive.getValue()!=null)Common.currentMember.setIsAlive((LiveState) isAlive.getValue());
            if (transInt(birthYear.getText()) != 0 &&
                    transInt(birthMonth.getText()) != 0 &&
                    transInt(birthDay.getText()) != 0)
                Common.currentMember.setBirthday(transInt(birthYear.getText()),
                        transInt(birthMonth.getText()), transInt(birthDay.getText()));
            if (transInt(deathYear.getText()) != 0 &&
                    transInt(deathMonth.getText()) != 0 &&
                    transInt(deathDay.getText()) != 0)
                Common.currentMember.setDeathday(transInt(deathYear.getText()),
                        transInt(deathMonth.getText()), transInt(deathDay.getText()));
            if (marriage.getValue() != null) Common.currentMember.setIfMarried((MarriageState) marriage.getValue());
            if (address.getText() != "") Common.currentMember.setAddress(address.getText());
            if (extra.getText() != "") Common.currentMember.setExtraMessage(extra.getText());
            Common.sendAlert(true,"�޸ĳɹ���");
        }else{
            Common.sendAlert(false,"���������������Ա��������������Ѵ��ڵ��ظ������޸����ֳ��⡣");
        }
    }

    private int transInt(String getText){
        try{
            return Integer.valueOf(getText);
        }catch (Exception e){
            return 0;
        }
    }


    public void search(ActionEvent actionEvent) {
        if (!nameToSearch.getText().equals("")) {
            Common.currentMember = Common.currentTree.find(nameToSearch.getText());
            freshView();
        }
    }

    private void freshView() {
        name.setText(Common.currentMember.getName());
        gender.setValue(Common.currentMember.getGender());
        isAlive.setValue(Common.currentMember.getIsAlive());
        birthYear.setText(Common.currentMember.getBirthday()[0]+"");
        birthMonth.setText(Common.currentMember.getBirthday()[1]+"");
        birthDay.setText(Common.currentMember.getBirthday()[2]+"");
        deathYear.setText(Common.currentMember.getDeathday()[0]+"");
        deathMonth.setText(Common.currentMember.getDeathday()[1]+"");
        deathDay.setText(Common.currentMember.getDeathday()[2]+"");
        marriage.setValue(Common.currentMember.getIfMarried());
        address.setText(Common.currentMember.getAddress());
        generation.setText(Common.currentMember.getGeneration()+"");
        if (Common.currentMember.getFather() != null)
            father.setText(Common.currentMember.getFather().getName());
        if (Common.currentMember.getMother() != null)
            mother.setText(Common.currentMember.getMother().getName());
        descendents.setText(Common.currentMember.getDescendentsString());
        extra.setText(Common.currentMember.getExtraMessage());
        addParent.setText("");
        removeName.setText("");
        movedName.setText("");
        moveUnderParent.setText("");
    }
}
