package client;
import server.ServerController;
public class ClientController {

    View view;
    ServerController serverController;

    ClientController(View view, ServerController serverController){
        this.view = view;
        this.serverController = serverController;
        sendMessageToServerController();
        setConnection();
    }

    public void sendMessageToServerController(){
        String message = view.sendMessage();
        serverController.catchMessage(message);
    }

    public void setConnection(){
        serverController.catchConnect(view.connectClient());
    }
}
