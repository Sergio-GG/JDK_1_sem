package server;
import client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ServerWindow extends JFrame implements ActionListener {
    private final static int WIDTH = 350;
    private final static int HEIGHT = 350;

    private static String LOG = "file.txt";

    public boolean state = false;

    JButton jb1, jb2;
    JTextArea jtarea;

    ServerController serverController;
    ArrayList<ClientGUI> clients;


    public ServerWindow(){
        clients = new ArrayList<>();
        this.state = false;
        serverSettings();


    }

    public void serverSettings(){
        setSize(WIDTH, HEIGHT);
        setTitle("Chat server");
        setLocation(800,200);
        add(mainFieldServer());
        add(bottomButtonsServer(), BorderLayout.SOUTH);
        setVisible(true);
        serverController = new ServerController(this);
    }

    public void connectClient(ClientGUI clientGUI){
        clients.add(clientGUI);
    }


    // отправить текст клиенту
    public void messageToClients(String text){
        for (ClientGUI client: clients){
            client.getMessage(text);
            System.out.println(client);
        }
    }

    public void changeWork(boolean work){
        for (ClientGUI client: clients){
            client.setWork(work);
        }
    }

    public void setState(boolean state){
        this.state = state;
    }

    // установить текст в окно
    public void setTextJArea(String text){
        jtarea.append(text);
    }

    public void saveMessagesOnServer(String text){
        try(FileWriter fw = new FileWriter(LOG, true)){
            fw.write(text);
            //fw.write("\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String readMessageFromServer(){
        StringBuilder sb = new StringBuilder();
        try(FileReader fr = new FileReader(LOG)){
            int c;
            while((c = fr.read()) != -1){
                sb.append((char) c);
            }
            sb.delete(sb.length() - 1, sb.length());
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public JPanel bottomButtonsServer(){
        JPanel jp = new JPanel(new GridLayout(1,2));
        jb1 = new JButton("Start");
        jb2 = new JButton("Stop");
        jb1.addActionListener(this);
        jb2.addActionListener(this);

        jp.add(jb1);
        jp.add(jb2);
        return jp;
    }

    public JPanel mainFieldServer() {
        JPanel jp = new JPanel(new GridLayout(1, 1));
        jtarea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(jtarea);
        jp.add(jScrollPane);
        return jp;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb1){
            jtarea.append("Сервер подключен\n");
            changeWork(true);
            setState(true);
            revalidate();
        }
        if(e.getSource() == jb2){
            jtarea.append("Сервер отключен\n");
            changeWork(false);
            setState(false);
            messageToClients("История переписки:\n" + readMessageFromServer());
            revalidate();
        }
    }
}
