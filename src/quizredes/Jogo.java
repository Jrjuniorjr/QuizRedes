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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class Jogo implements Runnable {

    private List<Pergunta> perguntas;
    private List<Socket> sockets;
    private List<Jogador> jogadores;
    private int pontuacaoJogadorVenceu;
    private List<ObjectOutputStream> objectsOutputStream;
    private List<ObjectInputStream> objectsInputStream;

    public Jogo(List<Pergunta> perguntas, List<Socket> sockets) throws IOException {
        this.perguntas = perguntas;
        this.sockets = sockets;
        this.pontuacaoJogadorVenceu = -1;
        preparativos();
    }

    @Override
    public void run() {
        int vez = 1;
        boolean acertou = false;
        int respostaJogador;
        List<Jogador> vencedores;
        jogo();
        vencedores = acharVencedores();

    }

    public void jogo() {
        int respostaJogador;
        int i;
        boolean acertou;
        String msgParaJogador;
        try {
            for (Pergunta p : perguntas) {
                i = 0;
                acertou = false;
                while (i < jogadores.size() || !acertou) {
                    objectsOutputStream.get(i).writeObject(p);
                    respostaJogador = objectsInputStream.get(i).read();
                    if (p.getResposta() == respostaJogador) {
                        jogadores.get(i).acertou();
                        break;
                    }
                    msgParaJogador = mensagemParaJogador();
                    objectsOutputStream.get(i).writeUTF(msgParaJogador);
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            fecharConexao();

        }
    }

    public String mensagemParaJogador(){
        String s = " ";
        for(Jogador j: jogadores){
            s += j.getNome() + ": " + j.getPontuacao() + "\n";
        }
        return s;
        
    }
    
    public void fecharConexao() {
        try {
            int i = 0;
            for (Socket s : sockets) {
                objectsOutputStream.get(i).close();
                objectsInputStream.get(i).close();
                s.close();

            }
        } catch (IOException ex) {
            Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Jogador> acharVencedores() {

        List<Jogador> vencedores = new ArrayList<>();

        for (Jogador j : jogadores) {
            if (j.getPontuacao() > pontuacaoJogadorVenceu) {
                pontuacaoJogadorVenceu = j.getPontuacao();
            }
        }

        for (Jogador j : jogadores) {
            if (j.getPontuacao() == pontuacaoJogadorVenceu) {
                vencedores.add(j);
            }
        }

        return vencedores;
    }

    public void preparativos() throws IOException {

        int i = 0;

        for (Socket s : sockets) {

            objectsOutputStream.add(new ObjectOutputStream(s.getOutputStream()));
            objectsInputStream.add(new ObjectInputStream(s.getInputStream()));

            objectsOutputStream.get(i).writeBoolean(true);
            String nomeJogador = objectsInputStream.get(i).readUTF();
            Jogador j = new Jogador(nomeJogador);

        }

    }

    //GETTERS & SETTERS
    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

}
