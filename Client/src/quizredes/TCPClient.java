/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizredes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import util.InformacaoControle;
import util.Protocolo;

/**
 *
 * @author Junior
 */
public class TCPClient {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public TCPClient(Socket socket) throws IOException, ClassNotFoundException {
        this.socket = socket;
       
    }
    
    public void run() throws Exception{
        preparativos();
        serverSolicitaNome();
        jogo();
        
    }
    
    public void comecarJogo() throws Exception{
        Protocolo p = (Protocolo) in.readObject();
    }
    
    public void jogo() throws Exception{
        comecarJogo();
        
        
        
    }
    public void serverSolicitaNome() throws IOException, ClassNotFoundException{
        Scanner s = new Scanner(System.in);
        Protocolo p =(Protocolo) in.readObject();
        if(p.getInformacaoControle().equals(InformacaoControle.SolicitandoNome)){
            System.out.println("Digite o nome: ");
            String nomeJogador = s.next();
            p = new Protocolo(nomeJogador, InformacaoControle.Nome, null, null, null);
            out.writeObject(p);
            out.flush();
        }
    }
    public void preparativos() throws IOException, ClassNotFoundException{
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        Protocolo p = (Protocolo)in.readObject();
        System.out.println("Conseguiu");
        
    }
    
}
