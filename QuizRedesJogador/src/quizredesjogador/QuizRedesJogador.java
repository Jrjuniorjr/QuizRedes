/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizredesjogador;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Junior
 */
public class QuizRedesJogador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
        Socket socket = new Socket("localhost", 5555);
        String mgs;
        Scanner scnaner = new Scanner(System.in);
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        
        boolean b = in.readBoolean();
        boolean vez = false;
        if (b) {
            System.err.println("Digite o nome: ");
            mgs = scnaner.next();
            out.writeUTF(mgs);
            out.flush();
            b = in.readBoolean();
            System.out.println("Jogo Começou!");
            b = in.readBoolean();
            if(b){
                Pergunta p = (Pergunta)in.readObject();
                System.out.println(p.getPergunta());
                for(String s: p.getAlternativa()){
                    System.out.println(s);
                    
                }
                
            }

        }
    }

}
