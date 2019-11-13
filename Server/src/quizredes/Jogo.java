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
import util.*;

/**
 *
 * @author Junior
 */
public class Jogo {

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

    public void run() throws ClassNotFoundException, IOException {
        int vez = 1;
        boolean acertou = false;
        int respostaJogador;
        List<Jogador> vencedores;
        jogo();
        vencedores = acharVencedores();
        anunciarPlacar(vencedores);
        anunciarPlacar(jogadores);
        fecharConexao();

    }

    public void comecarJogo() throws IOException {
        Protocolo protocolo = new Protocolo(null, InformacaoControle.Comecou, null, null, null);

        for (ObjectOutputStream output : out) {
            output.writeObject(protocolo);
            output.flush();
        }
    }

    public void anunciarPlacar(List<Jogador> j) throws IOException {

        Placar placar = new Placar(j);
        Protocolo protocolo = new Protocolo(null, InformacaoControle.Placar,
                placar, null, null);

        for (ObjectOutputStream output : out) {
            output.writeObject(protocolo);
            output.flush();
        }

    }

    public void anunciarPergunta(Pergunta pergunta) throws IOException {
        PerguntaDTO perguntaDTO = new PerguntaDTO(pergunta.getPergunta(),
                pergunta.getAlternativas(), pergunta.getPontuacao());
        Protocolo protocolo = new Protocolo(null, InformacaoControle.Pergunta,
                null, perguntaDTO, null);
        for (ObjectOutputStream output : out) {
            output.writeObject(protocolo);
            output.flush();
        }

    }

    public void jogo() throws ClassNotFoundException {
        int respostaJogador;
        int i;
        String msgParaJogador;
        Protocolo protocolo;
        try {
            solicitarNomes();
            comecarJogo();
            for (Pergunta pergunta : perguntas) {
                i = 0;
                while (i < 4) {
                    anunciarPergunta(pergunta);
                    anunciarPlacar(jogadores);
                    protocolo = new Protocolo(null, InformacaoControle.Vez,
                            null, null, null);
                    out.get(i % 2).writeObject(protocolo);
                    out.get(i % 2).flush();
                    protocolo = (Protocolo) in.get(i % 2).readObject();

                    if (protocolo.getInformacaoControle().equals(InformacaoControle.Resposta)) {
                        if (pergunta.getResposta().equals(protocolo.getResposta())) {
                            jogadores.get(i % 2).acertou();
                            protocolo = new Protocolo(null, InformacaoControle.Acertou,
                                    null, null, null);
                            out.get(i % 2).writeObject(protocolo);
                            out.get(i % 2).flush();
                            i++;
                            break;
                        } else {
                            protocolo = new Protocolo(null, InformacaoControle.Errou,
                                    null, null, null);
                            out.get(i % 2).writeObject(protocolo);
                            out.get(i % 2).flush();
                        }

                    }
                    i++;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void solicitarNomes() throws IOException, ClassNotFoundException {

        int i = 0;
        Protocolo protocolo = new Protocolo(null, InformacaoControle.SolicitandoNome,
                null, null, null);

        for (Socket s : sockets) {

            out.get(i).writeObject(protocolo);
            out.get(i).flush();
            protocolo = (Protocolo) in.get(i).readObject();
            jogadores.add(new Jogador(protocolo.getNomeJogador()));
            i++;
        }

    }

    public void preparativos() throws IOException {

        int i = 0;

        Protocolo protocolo = new Protocolo(null, InformacaoControle.Preparativos,
                null, null, null);

        for (Socket s : sockets) {

            out.add(new ObjectOutputStream(s.getOutputStream()));
            in.add(new ObjectInputStream(s.getInputStream()));
            out.get(i).writeObject(protocolo);
            out.get(i).flush();
            i++;
        }

    }

    public void fecharConexao() {
        try {
            int i = 0;
            Protocolo protocolo = new Protocolo(null, InformacaoControle.AcabouPartida,
                    null, null, null);

            for (Socket s : sockets) {

                out.get(i).writeObject(protocolo);
                out.get(i).flush();
                out.get(i).close();
                in.get(i).close();
                s.close();
                i++;
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
