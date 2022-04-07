package server;

import java.util.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;


public class Server {

    private ServerSocket server;
    private Socket socket;
    private final int PORT = 8189;
    private List<ClientHandler> clients;


    public Server () {
        clients = new CopyOnWriteArrayList<>();
        try {
            server = new ServerSocket(PORT);
            System.out.println("server started");

            while(true){
                socket = server.accept();
                System.out.println("client connected " + socket.getRemoteSocketAddress());
                clients.add(new ClientHandler(this,socket));
            }

        } catch (IOException e) {
            e.printStackTrace();

        }finally {
            try {
                server.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler c : clients) {
            c.sendMsg(msg);
        }
    }
}


