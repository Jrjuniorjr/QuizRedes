package quizredes;

import quizredes.model.Pergunta;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {

    private ServerSocket serverSocket;
    
    public TCPServer() {
    }

    public void serverOn() {
        List<Pergunta> perguntas = new ArrayList<>();
        List<Socket> sockets = new ArrayList<>();
        List<String> alternativas = new ArrayList<>();
        alternativas.add("a) qual");
        alternativas.add("b) qual");
        Pergunta p = new Pergunta("Qual?", alternativas, 1, 2);
        perguntas.add(p);
        
        try {
            criarServerSocket(5555);
            while (true) {
                System.out.println("AGUARDANDO CONEXAO");
                sockets.add(esperarConexao());
                System.out.println("CONEXAO ACEITA");
                
                Jogo j = new Jogo(perguntas, sockets);
                try {
                    j.run();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                
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
