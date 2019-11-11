/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizredes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class Jogo implements Runnable {

    private static final int PASSARPERGUNTA = -2;

    private List<Pergunta> perguntas;
    private List<Socket> sockets;
    private List<Jogador> jogadores;
    private int pontuacaoJogadorVenceu;
    private List<ObjectOutputStream> out;
    private List<ObjectInputStream> in;

    public Jogo(List<Pergunta> perguntas, List<Socket> sockets) throws IOException {
        this.perguntas = perguntas;
        this.sockets = sockets;
        this.pontuacaoJogadorVenceu = -1;
        this.out = new ArrayList<>();
        this.in = new ArrayList<>();
        this.jogadores = new ArrayList<>();
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

    public void comecarJogo() throws IOException {
        for (ObjectOutputStream output : out) {
            output.writeBoolean(true);
            output.flush();
        }
    }

    public void jogo() {
        int respostaJogador;
        int i;
        boolean acertou;
        boolean vez;
        String msgParaJogador;
        try {
            comecarJogo();
            for (Pergunta p : perguntas) {
                i = 0;
                acertou = false;
                while (i < jogadores.size() || !acertou) {
                    vez = true;
                    out.get(i).writeBoolean(vez);
                    out.get(i).flush();
                    out.get(i).writeObject(p);
                    out.get(i).flush();
                    respostaJogador = in.get(i).read();
                    if (p.getResposta() == respostaJogador) {
                        jogadores.get(i).acertou();
                        i++;
                        vez = false;
                        out.get(i).writeBoolean(vez);
                        out.get(i).flush();
                        break;
                    } else if (respostaJogador == PASSARPERGUNTA) { //PASSOU A PERGUNTA
                        p.setPontuacao(p.getPontuacao() + 1);

                    }
                    msgParaJogador = mensagemParaJogador();
                    out.get(i).writeUTF(msgParaJogador);
                    out.get(i).flush();
                    vez = false;
                    out.get(i).writeBoolean(vez);
                    out.get(i).flush();
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            fecharConexao();

        }
    }

    public void preparativos() throws IOException {

        boolean preparativos = true;
        int i = 0;

        for (Socket s : sockets) {

            out.add(new ObjectOutputStream(s.getOutputStream()));
            in.add(new ObjectInputStream(s.getInputStream()));
            out.get(i).writeBoolean(preparativos);
            out.get(i).flush();
            String nomeJogador = in.get(i).readUTF();
            Jogador j = new Jogador(nomeJogador);
            System.out.println(nomeJogador);
            i++;
        }

    }

    public String mensagemParaJogador() {
        String s = " ";
        for (Jogador j : jogadores) {
            s += j.getNome() + ": " + j.getPontuacao() + "\n";
        }
        return s;

    }

    public void fecharConexao() {
        try {
            int i = 0;
            for (Socket s : sockets) {
                out.get(i).close();
                in.get(i).close();
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

    //GETTERS & SETTERS
    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

}
