import javax.swing.*;
import java.util.Random;

/**
     * Created by Baturay on 5/21/2015.
     */
    public class Boats {
        private static int[] labelSize; // boatSize
        private static JLabel[] label;
        private static int[] posX;
        private static int[] posY;
        static ImageIcon[] imageIcons;



        public static JLabel[] getLabel() {
            return label;
        }
        public static int[] getLabelSize() {
            return labelSize;
        }
        static int getPosX(int i) {
            return posX[i];
        }
        public static int getPosY(int i) {
            return posY[i];
        }

    Boats(){
        imageCreator();
    }
    public void imageCreator() {
        label = new JLabel[5];
        labelSize = new int[5];
        posX=new int[5];
        posY=new int[5];
        imageIcons=new ImageIcon[5];

        ImageIcon p1 = new ImageIcon(getClass().getResource("p1.jpg"));
        imageIcons[0]=p1;
        posX[0]=(int) GUIcompts.getScreen_width() * 37 / 50;
        posY[0]= (int) GUIcompts.getScreen_height() / 30;
        label[0] = new JLabel(p1);
        label[0].setBounds(posX[0],posY[0], p1.getIconWidth(), p1.getIconHeight());
        labelSize[0] = 3;

        ImageIcon p2 = new ImageIcon(getClass().getResource("p2.jpg"));
        imageIcons[1]=p2;
        posX[1]=(int) GUIcompts.getScreen_width() * 37 / 50;
        posY[1]=(int) GUIcompts.getScreen_height() * 8 / 30;
        label[1] = new JLabel(p2);
        label[1].setBounds(posX[1],posY[1], p2.getIconWidth(), p2.getIconHeight());
        labelSize[1] = 2;

        ImageIcon p3 = new ImageIcon(getClass().getResource("p3.jpg"));
        imageIcons[2]=p3;
        posX[2]=(int) GUIcompts.getScreen_width() * 37 / 50;
        posY[2]= (int) GUIcompts.getScreen_height() * 13 / 30;
        label[2] = new JLabel(p3);
        label[2].setBounds(posX[2],posY[2], p3.getIconWidth(), p3.getIconHeight());
        labelSize[2] = 3;

        ImageIcon p4 = new ImageIcon(getClass().getResource("p4.jpg"));
        imageIcons[3]=p4;
        posX[3]=(int) GUIcompts.getScreen_width() * 37 / 50;
        posY[3]= (int) GUIcompts.getScreen_height() * 17 / 30;
        label[3] = new JLabel(p4);
        label[3].setBounds(posX[3],posY[3], p4.getIconWidth(), p4.getIconHeight());
        labelSize[3] = 4;

        ImageIcon p5 = new ImageIcon(getClass().getResource("p5.jpg"));
        imageIcons[4]=p5;
        posX[4]=(int) GUIcompts.getScreen_width() * 37 / 50;
        posY[4]= (int) GUIcompts.getScreen_height() * 22 / 30;
        label[4] = new JLabel(p5);
        label[4].setBounds(posX[4],posY[4], p5.getIconWidth(), p5.getIconHeight());
        labelSize[4] = 2;

    }

}

