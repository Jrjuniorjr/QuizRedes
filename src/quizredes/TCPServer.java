package quizredes;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {

    private ServerSocket serverSocket;

    public void serverOn() {
        try {
            criarServerSocket(5432);
            while (true) {
                
                Socket socket1 = esperarConexao();
              
                Socket socket2 = esperarConexao();

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
