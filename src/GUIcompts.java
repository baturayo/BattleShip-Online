import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Baturay on 5/21/2015.
 */
public class GUIcompts extends JFrame {

    private static double  screen_width;
    private static double  screen_height;
    private final static int gridWidth = 10;//numberOfVerticalButton
    private final static int gridHeight = 10;//numberOfHorizontalButton
    public static JFrame frame;
    static JLabel backgroundIconLabel;
    static JLabel label;
    GUIcompts() throws MalformedURLException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super();
        frame=this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        if(Network.IS_SERVER){
            setTitle("Modern BattleShip Server");
        }
        else if(!Network.IS_SERVER){
            setTitle("Modern BattleShip Client");
        }
        setLayout(null);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screen_width = screenSize.getWidth();
        screen_height = screenSize.getHeight();
        this.setSize((int) screen_width, (int) screen_height);

        label =new JLabel();

        backgroundImage();
        //customCursor();
        setVisible(true);



    }

    public static void informationLabel(String s){

        label.setLayout(null);
        label.setBounds((int) screen_width*9/17,(int) screen_height *3/ 45+(int) screen_height *1/100,(int)screen_width*3/17,(int)screen_height *3/16);
        label.setText(s);
        label.setBorder(new EtchedBorder());
        backgroundIconLabel.add(label);
        backgroundIconLabel.repaint();

    }
    public void backgroundImage(){

        ImageIcon seaBackground = new ImageIcon(getClass().getResource("seaTheme.jpg"));
        backgroundIconLabel = new JLabel(seaBackground);
        backgroundIconLabel.setLayout(null);
        backgroundIconLabel.setBounds(0, 0, (int) screen_width, (int) screen_height);

        Buttons buttons=new Buttons(backgroundIconLabel);
        BoatReplacer boatReplacer=new BoatReplacer(backgroundIconLabel);

        frame.add(backgroundIconLabel);
    }
    public static int getGridWidth() {
        return gridWidth;
    }
    public static int getGridHeight() {
        return gridHeight;
    }
    public static double getScreen_width() {
        return screen_width;
    }
    public static double getScreen_height() {
        return screen_height;
    }
    public  void customCursor () throws MalformedURLException {

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage(new URL("http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons/blue-jelly-icons-business/078495-blue-jelly-icon-business-cursor.png"));
            Point hotspot = new Point(0, 0);
            Cursor cursor = toolkit.createCustomCursor(image, hotspot, "Stone");
            setCursor(cursor);

    }




}




