package quizredes;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {

    private ServerSocket serverSocket;
    
    public TCPServer() {
    }

    public void serverOn() {
        List<Pergunta> perguntas = new ArrayList<>();
        List<Socket> sockets = new ArrayList<>();
        try {
            criarServerSocket(5432);
            while (true) {
                Socket socket1 = esperarConexao();
                sockets.add(socket1);
                Socket socket2 = esperarConexao();
                sockets.add(socket2);
                Thread t = new Thread(new Jogo(perguntas, sockets));
                
                
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void criarServerSocket(int porta) throws IOException {
        this.serverSocket = new ServerSocket(porta);
    }

    private Socket esperarConexao() throws IOException {
        return this.serverSocket.accept();
    }

}
