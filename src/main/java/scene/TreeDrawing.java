package scene;


import dataLayer.LiveState;
import dataLayer.Member;
import dataLayer.Tree;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Ҧ����
 * 2020/6/18,21:09
 * ��ֵĳ���������
 */
public class TreeDrawing {
    private static ArrayList<Integer> generationCount;
    private static double canvasWidth;//�������
    private static double canvasHeight;//��������
    private static int gridWidth = 40;//ÿ�����Ŀ��
    private static int gridHeight = 20;//ÿ�����ĸ߶�
    private static int yGap = 50;//ÿ2�����Ĵ�ֱ����
    private static int[] xGap;//ÿ2������ˮƽ����
    private static int[] drawCount;//��¼ÿ���Ѿ����˼���
    private static int startY = 10;//�߿�Y��Ĭ�Ͼ��붥��10����
    private static int startX = 10;//�߿�X��Ĭ��ˮƽ���ж���
    private static Calendar now;
    private static int month;
    private static int date;

    public static void draw(Canvas canvas,Tree tree){
        now = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
        month = now.get(Calendar.MONTH)+1;//calendar ��õ��·��Ǵ�0��ʼ��
        date = now.get(Calendar.DATE);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());//��ջ���
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        generationCount = tree.generationStatistic();//���ڷ���ռ�
        yGap = ((int)canvasHeight-2*startY)/(generationCount.size()+1)-gridHeight;//��ֱ����̶�
        xGap = new int[generationCount.size()];
        //ת����ÿ�еľ���
        for (int i = 0; i < xGap.length; i++) {
            xGap[i] = (((int)canvasWidth-2*startX))/(generationCount.get(i))-gridWidth;
        }
        drawCount = new int[generationCount.size()];
        draw(gc,tree.getRoot());
    }

    private static void draw(GraphicsContext gc, Member member){
//        gc.fillText(member.getName(),);
        //generation�����Ǵ�0��ʼ��
        int lay = member.getGeneration();
        int y = startY+lay*(yGap +gridHeight);//��λ��
        int StringY = y + gridHeight/2+5;//����λ��

        //����Ҫ��ı������
        if (member.getIsAlive()== LiveState.alive && member.getBirthday()[1]==month && member.getBirthday()[2] == date){
            gc.setStroke(Color.BLUE);
        }else{
            gc.setStroke(Color.BLACK);
        }

        int x = startX + drawCount[lay]*(xGap[lay]+gridWidth);
        gc.strokeRect(x,y,gridWidth,gridHeight);
        gc.fillText(member.getName(),x+5,StringY);
        drawCount[lay]=drawCount[lay]+1;

        gc.setStroke(Color.BLACK);//���ߵĲ��ֻ��ǻع��ɫ
        for (int i = 0; i < member.getDescendents().size(); i++) {
            //������һ������ϵ㣬���ظ����㣬�����Ż�
            gc.strokeLine(x+gridWidth/2,y+gridHeight,startX + drawCount[lay+1]*(xGap[lay+1]+gridWidth)+gridWidth/2,y+gridHeight+yGap);
            draw(gc,member.getDescendents().get(i));
        }

    }








    public static void drawExamples(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //shape examples
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }


}
