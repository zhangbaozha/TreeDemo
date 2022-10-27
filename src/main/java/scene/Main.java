package scene;

/**
 * @author Ҧ����
 * 2020/6/4,16:40
 * ��ֵĳ���������
 */
import dataDealer.IODealer;
import dataLayer.Common;
import dataLayer.Tree;
import dataLayer.User;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

//    private TableView table= new TableView;
//    private final ObservableList<record> dataList  = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Common.currentStage = primaryStage;
        Common.currentRoot = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/LoginScene.fxml"));//����fxml����

//        Common.currentRoot = FXMLLoader.load(getClass().getResource("JiapuScene.fxml"));//����fxml����
        primaryStage.setTitle("���� by:H17000623Ҧ����");
        primaryStage.setScene(new Scene(Common.currentRoot));
        primaryStage.show();

        //���رճ���ʱ��Ҫ�����Ѿ��޸ĵ��û�list�ͼ�����������һ��������������ڼ���������ر�ʱ�����û�����
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("�����������ڹر�");
//                IODealer.writeObjectToFile(Common.userList,Common.userInfoPath);
            }
        });

    }

    static ArrayList<User> loadUserInfo() {
        return (ArrayList<User>)IODealer.readObjectFromFile(Common.userInfoPath);
    }
    static Tree loadFamilyTree(){
        return (Tree) IODealer.readObjectFromFile(Common.treePath);
    }

    //����û����������Ƿ��Ӧ��ȷ



    public static void main(String[] args) {
//        System.out.println(ifInfoOK("admin","123456"));
        launch(args);
    }

    //�����ڶ�������
    public static void startJiapuScene(){
        Stage secondStage = new Stage();
        try {
            Common.currentRoot = FXMLLoader.load(Main.class.getClassLoader().getResource("fxml/JiapuScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        secondStage.setTitle("���� by:Ҧ����");
        secondStage.setScene(new Scene(Common.currentRoot));
        secondStage.show();

        //�ٴα���
        secondStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                //�����رղ��ܱ�����׸Ķ�
                System.out.println("���������ڹر�");
                IODealer.writeObjectToFile(Common.userList,Common.userInfoPath);
                IODealer.writeObjectToFile(Common.currentTree,Common.treePath);
            }
        });
    }

    //��������������
    public static void startEditScene() {
        Stage thirdStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getClassLoader().getResource("fxml/EditScene.fxml"));
//            System.out.println("###############################");
        } catch (IOException e) {
            e.printStackTrace();
        }
        thirdStage.setTitle("�����޸�");
        thirdStage.setScene(new Scene(root));
        thirdStage.show();
    }

    //�������ĸ�����
    public static void startEditUserScene() {
        Stage fourthStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getClassLoader().getResource("fxml/EditUserScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fourthStage.setTitle("�û��޸�");
        fourthStage.setScene(new Scene(root));
        fourthStage.show();
        fourthStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                //�ر�ʱ�����û��Ķ�
                System.out.println("���������ڹر�");
                IODealer.writeObjectToFile(Common.userList,Common.userInfoPath);
            }
        });
    }
}
