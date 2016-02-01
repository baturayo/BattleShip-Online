import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

/**
 * Created by Baturay on 6/5/2015.
 */

public class NetworkGUI extends JFrame {

    private static double screen_width;
    private static double screen_height;
    public static boolean isServer = false;
    private JLabel networkStatusLabel;
    private JPanel panel;
    private JTextField ipAddress;


    NetworkGUI() {

        guiCreator();
    }
      public String ipAddressGetter(){
          InetAddress iAddress = null;
          try {
              iAddress = InetAddress.getLocalHost();
          } catch (UnknownHostException e1) {
              e1.printStackTrace();
          }
          String currentIp = iAddress.getHostAddress();
          return currentIp;
      }
      public void guiCreator() {
        setTitle("Modern BattleShip");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screen_width = screenSize.getWidth();
        screen_height = screenSize.getHeight();
        setBounds((int) screen_width / 2 - (int) screen_width / 5, (int) screen_height / 2 - (int) screen_height / 5, (int) screen_width / 2, (int) screen_height / 2);

        panel = new JPanel();

        Color color = new Color(0x4AC8AD);
        panel.setBackground(color);
        panel.setLayout(null);


        ImageIcon serverIcon = new ImageIcon(getClass().getResource("server.png"));
        JButton serverButton=new JButton(serverIcon);
          serverButton.setBounds(getWidth() * 4 / 15, getHeight() * 3 / 12, serverIcon.getIconWidth(), serverIcon.getIconHeight());

        ImageIcon clientIcon = new ImageIcon(getClass().getResource("client.png"));
          JButton clientButton= new JButton(clientIcon);
          clientButton.setBounds(getWidth() * 8 / 15, getHeight() * 3 / 12, serverIcon.getIconWidth(), serverIcon.getIconHeight());

          String s="If you click CLIENT enter Server IP ADRESS Below(Press ENTER then CLICK CLIENT)";
          networkStatusLabel = new JLabel(s,SwingConstants.CENTER);
          networkStatusLabel.setBounds(getWidth() * 1 / 35, getHeight() * 4 / 9, getWidth() * 14 / 15, getHeight() / 10);

          JLabel ipAddressLabel=new JLabel("Your ip address is: "+ipAddressGetter(),SwingConstants.CENTER);
          ipAddressLabel.setBounds(0,0,getWidth(),getHeight()*1/10);


          ipAddress =new JTextField();
          ipAddress.setBackground(color);
          ipAddress.setBounds(getWidth() * 12 / 36, getHeight() * 4 / 9+getHeight()*12 /100, getWidth() * 5 / 15, getHeight() / 10-getHeight() /100);


        MouseHandlerForServer mouseHandlerForServer = new MouseHandlerForServer();
        serverButton.addMouseListener(mouseHandlerForServer);

        MouseHandlerForClient mouseHandlerForClient = new MouseHandlerForClient();
        clientButton.addMouseListener(mouseHandlerForClient);

        KeyboardListener keyboardListener=new KeyboardListener();
          ipAddress.addKeyListener(keyboardListener);
          panel.add(ipAddressLabel);
          panel.add(ipAddress);
          panel.add(networkStatusLabel);
        panel.add(clientButton);
        panel.add(serverButton);

        add(panel);
        setVisible(true);

    }
        class KeyboardListener extends KeyAdapter{
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    Network.setServerIP(ipAddress.getText());
                }
            }

        }
        class MouseHandlerForServer extends MouseAdapter {
            public void mousePressed(MouseEvent e) {
                isServer = true;
                Network.setup();

                try {
                    GUIcompts game = new GUIcompts();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
                setVisible(false);
            }
        }

        class MouseHandlerForClient extends MouseAdapter {

            public void mousePressed(MouseEvent e) {
                isServer = false;
                Network.setup();
                try {
                    GUIcompts game = new GUIcompts();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
                setVisible(false);
            }
        }

    }
