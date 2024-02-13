import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;


public class ClientGUI extends JFrame  implements ActionListener  {

    private final static int WIDTH = 350;
    private final static int HEIGHT = 350;

    private static int number = 0;

    private int no = 0;
    
    FileWriter fw;
    JTextField jtf;
    JTextArea jtx;

    JButton jb;

    ServerWindow serverWindow;




    ClientGUI(ServerWindow sw){
        this.serverWindow = sw;
        setSize(WIDTH, HEIGHT);
        setTitle("Chat client");
        setLocation(200,200);
        add(mainField(), BorderLayout.CENTER);
        add(bottomButtons(), BorderLayout.SOUTH);
        setVisible(true);
        no = ++number;
        revalidate();

    }

//    public void setThisClient(){
//        serverWindow.setClient(this);
//    }
    public JPanel bottomButtons(){
        JPanel jp = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0f;

        jtf = new JTextField();
        jtf.setPreferredSize(new Dimension(250,27));
        jp.add(jtf, gbc);


        jb = new JButton("Send");
        jb.addActionListener(this);
        jp.add(jb);



        jp.revalidate();
        return jp;
    }

    public JPanel mainField(){
        JPanel jp = new JPanel(new GridLayout(1,1));
        jtx = new JTextArea();
        jp.add(jtx);

        return jp;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb) {
            if(serverWindow.jtarea.isEnabled()){

                jtx.setText("Вы успешно подключились\n");

                String string = jtf.getText();
                String message = ("Client"+ no + ": " + string);
                serverWindow.jtarea.append(message + "\n");
                String finalMes = serverWindow.jtarea.getText();
                jtx.append(message);

                repaint();


            }
            if(!serverWindow.jtarea.isEnabled()) {
                jtx.setText("Подключение не удалось\n");
            }

        }

    }

}

