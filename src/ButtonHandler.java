import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Baturay on 6/3/2015.
 */
public class ButtonHandler implements ActionListener{
    private int buttonIndex;
    private static int buttonClickCounter;
    private static int bombedCounter;

    ButtonHandler(int buttonIndex) {
        this.buttonIndex = buttonIndex;
    }

    public static void buttonPressed(int i){

        if(BoatReplacementCompleted.getEnemyArray()[i]==3){ //3 means there is an hidden enemy boat at that location
            BoatReplacer.getButtonsStatus()[i]=1; //1 means bombed an enemy boat
            Buttons.getButtons_array()[i].setBackground(Color.CYAN);
            bombedCounter++;

            buttonClickCounter++;
            GUIcompts.informationLabel("<html>You have bombed:  "+bombedCounter+" units.     <br>Your total number of trial is: "+buttonClickCounter+"<html>");
            BoatReplacementCompleted.winningCondition();

        }
        else if(BoatReplacementCompleted.getEnemyArray()[i]==0){
            BoatReplacer.getButtonsStatus()[i]=2; // 2 means that Cannot bombed an enemy boat
            Buttons.getButtons_array()[i].setBackground(Color.red);
            buttonClickCounter++;
            GUIcompts.informationLabel("<html>You have bombed:  "+bombedCounter+" units.     <br>Your total number of trial is: "+buttonClickCounter+"<html>");
        }
    }

    public static int getBombedCounter() {
        return bombedCounter;
    }

    public static int getButtonClickCounter() {
        return buttonClickCounter;
    }
    public void actionPerformed(ActionEvent e) {
        JButton button=(JButton)e.getSource();
        buttonPressed(buttonIndex);
        button.removeActionListener(this);
    }

}

