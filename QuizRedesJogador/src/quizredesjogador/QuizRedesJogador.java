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
        Scanner scanner = new Scanner(System.in);
        Protocolo p;
        Status s;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        boolean terminou = false;
        s = (Status) in.readObject();
        if (s.getTipoStatus().equals(TipoStatus.Preparativos)) {

            s = (Status) in.readObject();
            if (s.getTipoStatus().equals(TipoStatus.SolicitandoNome)) {
                String s1 = scanner.next();
                out.writeUTF(s1);
                out.flush();

                s = (Status) in.readObject();
                if (s.getTipoStatus().equals(TipoStatus.Comecou)) {
                    while (!terminou) {
                        Placar placar = (Placar) in.readObject();
                        for (Jogador j : placar.getJogadores()) {

                            System.out.println(j.getNome() + ": " + j.getPontuacao());

                        }
                        Pergunta pergunta = (Pergunta) in.readObject();
                        System.out.println(pergunta.getPergunta());
                        s = (Status) in.readObject();
                        if (s.getTipoStatus().equals(TipoStatus.Vez)) {
                            for (String s2 : pergunta.getAlternativa()) {
                                System.out.println(s2);
                            }
                            int resposta = scanner.nextInt();
                            Resposta r = new Resposta(resposta);
                            out.writeObject(r);
                            out.flush();
                            
                        }

                    }
                }

            }
        }

    }

}
