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
        preparativos();
    }
    
    public void run(){
        
    }
    
    public void preparativos() throws IOException, ClassNotFoundException{
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        Protocolo p = (Protocolo)in.readObject();
        System.out.println("Conseguiu");
        
    }
    
}
