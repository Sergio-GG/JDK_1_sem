package client;

import server.ServerWindow;
import server.ServerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClientGUI extends JFrame  implements ActionListener,View {

    private final static int WIDTH = 350;
    private final static int HEIGHT = 350;

    private static int number = 0;
    private int no = 0;

    public boolean work;

    JTextField jTextField;
    JTextArea jTextArea;
    JButton jb;
    ServerWindow serverWindow;

    ClientController clientController;


    public ClientGUI(ServerWindow serverWindow){
        this.serverWindow = serverWindow;
        setting();
        //serverWindow.connectClient(catchConnect());
    }

    public  void setting(){
        setSize(WIDTH, HEIGHT);
        setTitle("Chat client");
        setLocation(200,200);
        add(mainField(), BorderLayout.CENTER);
        add(bottomButtons(), BorderLayout.SOUTH);
        setVisible(true);
        no = ++number;
        clientController = new ClientController(this, serverWindow.serverController.getConnection()); // Не понимаю, откуда сюда передается ServerController
    }

    //Нижняя панель с кнопкой и полем ввода
    public JPanel bottomButtons(){
        JPanel jp = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0f;

        jTextField = new JTextField();
        jTextField.setPreferredSize(new Dimension(250,27));
        jp.add(jTextField, gbc);

        jb = new JButton("Send");
        jb.addActionListener(this);
        jp.add(jb);

        jp.revalidate();
        return jp;
    }

    //Окно сообщений
    public JPanel mainField(){
        JPanel jp = new JPanel(new GridLayout(1,1));
        jTextArea = new JTextArea();
        jp.add(jTextArea);
        return jp;
    }

    //Добавить текст из сервера в окно
    public void getMessage(String text){
        jTextArea.setText(text);
    }

    public void setWork(boolean w){
        this.work = w;
    }

    //Отправить текст на сервер
    public String sendMessageToServer(){
        String string = jTextField.getText();
        String message = ("Client"+ no + ": " + string +"\n");
        return message;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb) {
            if(!work) {
                if (!serverWindow.state) {
                    jTextArea.setText("Подключение не удалось\n");
                }
            }
            if(work) {
                if(serverWindow.state) {
                    System.out.println(work + "yes");
                    //serverWindow.messageToClients();
                    serverWindow.setTextJArea(sendMessageToServer());
                    serverWindow.saveMessagesOnServer(sendMessageToServer());
                    serverWindow.messageToClients("Вы успешно подключились\n" + serverWindow.readMessageFromServer());

                    repaint();
                }else{
                    serverWindow.setTextJArea(sendMessageToServer());
                    serverWindow.saveMessagesOnServer(sendMessageToServer());
                    serverWindow.messageToClients(serverWindow.readMessageFromServer());
                }
            }

        }

    }

    @Override
    public String sendMessage() {
        return sendMessageToServer();
    }

    @Override
    public ClientGUI connectClient() {
        return this;
    }
}

