


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Administrator on 6/2/2015.
 */
public class Network {
    public static boolean IS_SERVER = true;
    private static final int PORT = 8888;;
    private static ObjectOutputStream writer;
    private static ObjectInputStream reader;
    private static DataOutputStream writerData;
    private static DataInputStream readerData;
    static ServerSocket serverSocket;

    private static String serverIP="";

    public static void setServerIP(String serverIP) {
        Network.serverIP = serverIP;
    }
    public static void setup() {
        try {
            Network.IS_SERVER =NetworkGUI.isServer;
            Socket socket;
            if (IS_SERVER) {

                // create socket and bind to port
                serverSocket = new ServerSocket(PORT);
                socket = serverSocket.accept();
                System.out.println("Client has connected.");
            } else {
                System.out.print("What's the server IP? ");

                socket = new Socket(serverIP, PORT);
                System.out.println("Connected to server.");
            }

            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());
            writerData=new DataOutputStream(socket.getOutputStream());
            readerData=new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    public static int[] readStatusArray() {
        try {
            return (int[]) reader.readObject();
        } catch (IOException e) {
            throw new Error("IO Exception occurred.");
        } catch (ClassNotFoundException e) {
            throw new Error("ClassNotFoundException occurred.");
        }
    }

    public static void writeStatusArray(int[] i) {
        try {
            writer.writeObject(i);
        } catch (IOException e) {
            throw new Error("IO Exception occurred.");
        }
    }
    public static int readInt(){
        try {
           return readerData.readInt();
        } catch (IOException e) {
            throw new Error("IO Exception occurred.");
        }
    }

    public static void writeInt(int counter){
        try {
            writerData.writeInt(counter);
        } catch (IOException e) {
            throw new Error("IO Exception occurred.");
        }
    }


}
