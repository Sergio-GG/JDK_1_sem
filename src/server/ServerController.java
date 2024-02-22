package server;
import client.ClientController;
import client.ClientGUI;
import client.View;

public class ServerController {

    ServerWindow serverWindow;



    public ServerController(ServerWindow serverWindow){
        this.serverWindow = serverWindow;
    }

    public void catchMessage(String text){
        serverWindow.setTextJArea(text);
    }

    public void catchConnect(ClientGUI view){
        serverWindow.connectClient(view);
    }

    public ServerController getConnection(){
        return this;
    }
}
