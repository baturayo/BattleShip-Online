import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Baturay on 5/22/2015.
 */
public class Buttons {
    private static GUIcompts guicompts;
    private static double screen_width= GUIcompts.getScreen_width();
    private static double screen_height= GUIcompts.getScreen_width();
    private static final int totalButtonInGrid =(GUIcompts.getGridWidth())*(GUIcompts.getGridHeight());
    private static JPanel panel;
    private JButton buttons;
    private static JButton[] buttons_array;


    Buttons(JLabel label){
        gridPanels();
        label.add(panel);
    }



    public void gridPanels(){ //10x10 panel
        panel=new JPanel();
        panel.setLayout(new GridLayout(GUIcompts.getGridWidth(), GUIcompts.getGridHeight()));
        panel.setBounds((int) screen_width / 50, (int) screen_height / 24, (int) screen_width / 2, (int) screen_height / 3 + (int) screen_height / 9);
        buttons_array=new JButton[100];

        for (int i=0;i<totalButtonInGrid;i++){
                buttons = new JButton();
                buttons.setText(String.valueOf(i));
                buttons_array[i] = buttons;
                buttons.setBackground(Color.pink);
                panel.add(buttons);

        }

    }

    public static JButton[] getButtons_array() {
        return buttons_array;
    }
    public static JPanel getPanel() {
        return panel;
    }
}
