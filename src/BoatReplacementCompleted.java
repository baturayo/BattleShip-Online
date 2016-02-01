import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Created by Baturay on 6/2/2015.
 */
public abstract class BoatReplacementCompleted {
    private static int[] enemyArray;
    private static boolean isReadyToWar;
    static int enemyTotalClickCount;

    public static void boatsLocation(){

        enemyArray=new int[Buttons.getButtons_array().length];
        if(BoatReplacer.boatReplacementCompletedCheck()){
           Network.writeStatusArray(BoatReplacer.getButtonsStatus());
            enemyArray= Network.readStatusArray();

        }
    }
    public static void removeAnchorIcon(){
        for (int i = 0; i <Buttons.getButtons_array().length ; i++) {
            BoatReplacer.getButtonsArray()[i].removeAll();
        }
    }

    public static void winningCondition(){

        if(ButtonHandler.getBombedCounter()==BoatReplacer.totalBoatsSize()){
            String s="You Have Bombed All Enemy Boats\nYour Total Trial is: "+ButtonHandler.getButtonClickCounter()+"\nWait For Your Enemy To Finish";
            String win="GOOD JOB, YOU ARE THE KING!";
            String loser="NEXT TIME, YOU ARE THE LOOSER ONE";
            String equal="YOU DRAW WITH YOUR ENEMY.\nYOU SHOULD BE BETTER FOR THE NEXT TIME";
            System.out.println("finished");
            JOptionPane.showMessageDialog(GUIcompts.frame,
                   s, "Congratulations!",
                    JOptionPane.INFORMATION_MESSAGE);



                Network.writeInt(ButtonHandler.getButtonClickCounter());
                enemyTotalClickCount=Network.readInt();
            if(enemyTotalClickCount>ButtonHandler.getButtonClickCounter()){
                JOptionPane.showMessageDialog(GUIcompts.frame,
                        win, "WINNING CONDITION",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else if (enemyTotalClickCount<ButtonHandler.getButtonClickCounter()){
                JOptionPane.showMessageDialog(GUIcompts.frame,
                        loser, "WINNING CONDITION",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else if(enemyTotalClickCount==ButtonHandler.getButtonClickCounter()){
                JOptionPane.showMessageDialog(GUIcompts.frame,
                        equal, "WINNING CONDITION",
                        JOptionPane.INFORMATION_MESSAGE);
            }







        }
    }

    public static void bomber(){
        ButtonHandler[] buttonHandler=new ButtonHandler[Buttons.getButtons_array().length];
            for (int i = 0; i <Buttons.getButtons_array().length ; i++) {
                Buttons.getButtons_array()[i].removeMouseListener(BoatReplacer.getMouseHandler());
                buttonHandler[i]=new ButtonHandler(i);
                Buttons.getButtons_array()[i].addActionListener(buttonHandler[i]);
            }
    }

    public static int[] getEnemyArray() {
        return enemyArray;
    }
}
