import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Baturay on 5/24/2015.
 */
public class BoatReplacer implements Serializable{

    private static JLabel selectedLabel;
    private int dx, dy;
    private EtchedBorder border;
    private static int lastX; //last Mouse Position X
    private static int lastY; //last Mouse Position Y
    private static JButton[] buttonsArray;
    private static int[] buttonsStatus;
    private JLabel[] label;
    private int selectedLabelIndex;
    private ArrayList<Integer> greenButtonIndexMemory;
    private ArrayList<Integer> redButtonIndexMemory;
    private static int[] boatSize;
    private int finalReplacedButtonIndex;
    private static boolean isInsideButton;
    private static MouseHandler mouseHandler;
    public static ImageIcon pin;

    BoatReplacer(JLabel background) {
        border = new EtchedBorder();
        mouseHandler = new MouseHandler();
        Boats boats = new Boats();
        buttonsArray = Buttons.getButtons_array();
        buttonsStatus= new int[100];
        greenButtonIndexMemory=new ArrayList<>();
        redButtonIndexMemory=new ArrayList<>();
        label = Boats.getLabel();
        

        for (int i = 0; i <label.length; i++) {
            background.add(label[i]);
            label[i].addMouseListener(mouseHandler);
            label[i].addMouseMotionListener(mouseHandler);
        }
        for (int i = 0; i <buttonsArray.length; i++) {
            buttonsArray[i].addMouseListener(mouseHandler);
        }
    }
    public static int totalBoatsSize(){
        int counter = 0;
        for (int i = 0; i <boatSize.length; i++) {
            counter+=boatSize[i];
        }
        return counter;
    }

    public static JButton[] getButtonsArray() {
        return buttonsArray;
    }


    public void buttonSelectVertical(int i, int boatSize){
        Boolean isMarked=true;
        for (int j = 0; j <boatSize ; j++) {
            try {
                if (buttonsStatus[i + j] == 3) isMarked = false;
            }catch (ArrayIndexOutOfBoundsException ignored){
                isMarked=true;
            }
        }
        for (int n = 0; n < boatSize; n++) {
            if (i % 10 + boatSize - 1 < 10&&isMarked) {
                buttonsArray[i + n].setBackground(Color.green);
                greenButtonIndexMemory.add(i+n);
            } else {
                buttonsArray[i + n].setBackground(Color.red);
                redButtonIndexMemory.add(i + n);
                if (i % 10 + n >= 9) break;
            }
        }
    }
    public void buttonCheckForDraggedBoat() {
        boatSize = Boats.getLabelSize();
        if (selectedLabel != null) {
            for (int i = 0; i < buttonsArray.length; i++) {
                if (lastX >= buttonsArray[i].getX() + Buttons.getPanel().getX() && lastX < buttonsArray[i].getX() + buttonsArray[i].getWidth() + Buttons.getPanel().getX() &&
                        lastY >= buttonsArray[i].getY() + Buttons.getPanel().getY() && lastY < buttonsArray[i].getY() + buttonsArray[i].getHeight() + Buttons.getPanel().getY()) {

                    buttonSelectVertical(i, boatSize[selectedLabelIndex]);
                    finalReplacedButtonIndex = i;
                    nonvisibleIcon();
                    isInsideButton = true;
                    break;
                }
            }
        }
    }

    public void pinner() {
        boolean isPinnable=true;
        for (int i = 0; i < boatSize[selectedLabelIndex]; i++) {
            try{
                if(buttonsStatus[finalReplacedButtonIndex + i]==3)isPinnable=false;
            }catch (ArrayIndexOutOfBoundsException ignored){
                isPinnable=true;
            }
        }
        for (int i = 0; i < boatSize[selectedLabelIndex]; i++) {
            try {
                if (finalReplacedButtonIndex % 10 + boatSize[selectedLabelIndex] <= 10 && isPinnable) {
                    pin = new ImageIcon(getClass().getResource("anchor.png"));
                    JLabel label = new JLabel(pin);
                    buttonsStatus[finalReplacedButtonIndex + i] = 3;
                    buttonsArray[finalReplacedButtonIndex + i].setBackground(Color.pink);
                    buttonsArray[finalReplacedButtonIndex + i].add(label);
                } else {
                    selectedLabel.setLocation(Boats.getPosX(selectedLabelIndex), Boats.getPosY(selectedLabelIndex));
                    selectedLabel.setVisible(true);
                    isInsideButton = false;
                }
            }catch (NullPointerException ex){
                ex.getMessage();
            }
        }

    }

    public static boolean  boatReplacementCompletedCheck(){
        int sizeOfTotalBoats=0;
        int numberOfStatus3=0;
        for (int i = 0; i <boatSize.length ; i++) {
            sizeOfTotalBoats+=boatSize[i];
        }
        for (int i = 0; i <buttonsStatus.length ; i++) {
            if(buttonsStatus[i]==3)numberOfStatus3++;
        }
        if(sizeOfTotalBoats==numberOfStatus3){
            return true;
        }
        return false;
    }
    public void nonvisibleIcon() {

        label[selectedLabelIndex].setVisible(false);
    }
    public static MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    public static int[] getButtonsStatus() {
        return buttonsStatus;
    }

    class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < 5; i++) {
                if (e.getSource() == label[i]) {
                    selectedLabel = label[i]; //label means Boats
                    selectedLabelIndex = i;
                    selectedLabel.setBorder(border);
                    dx = e.getXOnScreen() - selectedLabel.getX();
                    dy = e.getYOnScreen() - selectedLabel.getY();
                    break;
                }
            }
        }

        public void mouseDragged(MouseEvent e) {
            if (selectedLabel != null) {
                lastX = e.getXOnScreen();
                lastY = e.getYOnScreen();
                selectedLabel.setBorder(border);
                selectedLabel.setLocation(e.getXOnScreen() - dx, e.getYOnScreen() - dy);
            }

        }

        public void mouseReleased(MouseEvent e) throws NullPointerException {
            greenButtonIndexMemory.clear();
            if(isInsideButton) {
                pinner();
                selectedLabel.setBorder(null);
                selectedLabel = null;
                if(boatReplacementCompletedCheck()) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    JOptionPane.showConfirmDialog (null,"Do you want to start the game?","WARNING", dialogButton);
                    if(dialogButton == JOptionPane.YES_OPTION) {
                        BoatReplacementCompleted.removeAnchorIcon();
                        BoatReplacementCompleted.boatsLocation();
                        BoatReplacementCompleted.bomber();


                    }
                    else if(dialogButton == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }
                }


            }
        }

        public void mouseEntered(MouseEvent e){
            if (e.getSource() instanceof JLabel){
                Cursor c= new Cursor(Cursor.MOVE_CURSOR);
                for (int i = 0; i <label.length ; i++) {
                    label[i].setCursor(c);
                }
            }

            buttonCheckForDraggedBoat();
        }

        public void mouseExited(MouseEvent e){

            for (int i = 0; i <greenButtonIndexMemory.size() ; i++){
                buttonsArray[greenButtonIndexMemory.get(i)].setBackground(Color.pink);
            }
            for (int i = 0; i <redButtonIndexMemory.size() ; i++) {
        buttonsArray[redButtonIndexMemory.get(i)].setBackground(Color.pink);
            }
        greenButtonIndexMemory.clear();
        redButtonIndexMemory.clear();
        }
    }
}
