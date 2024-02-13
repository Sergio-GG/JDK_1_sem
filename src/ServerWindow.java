import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame implements ActionListener {

    private final static int WIDTH = 350;
    private final static int HEIGHT = 350;

    JButton jb1, jb2;

    JTextArea jtarea;

    ClientGUI client;
    ServerWindow(){
        setSize(WIDTH, HEIGHT);
        setTitle("Chat server");
        setLocation(800,200);
        add(mainFieldServer());
        add(bottomButtonsServer(), BorderLayout.SOUTH);
        setVisible(true);
    }


//    public void setClient(ClientGUI client) {
//        this.client = client;
//    }

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
        jtarea.setEnabled(false);
        JScrollPane jScrollPane = new JScrollPane(jtarea);

        jp.add(jScrollPane);
        return jp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb1){
            jtarea.setEnabled(true);
            jtarea.append("Сервер подключен\n");

        }
        if(e.getSource() == jb2){
            jtarea.setEnabled(false);
            jtarea.append("Сервер отключен\n");

        }
    }
}
