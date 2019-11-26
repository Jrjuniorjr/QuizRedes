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
import util.*;

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

    }

    public void run() throws ClassNotFoundException, IOException {
        int vez = 1;
        boolean acertou = false;
        int respostaJogador;
        List<Jogador> vencedores;
        preparativos();
        jogo();
        //vencedores = acharVencedores();
        anunciarVencedores();
        anunciarPlacar(jogadores);
        fecharConexao();

    }

    public void anunciarVencedores() throws IOException {
        
        if(jogadores.get(0).getPontuacao().equals(jogadores.get(1).getPontuacao())){
            Protocolo p = new Protocolo(null, InformacaoControle.Empatou, null, null, null,
                    null, null, null);
            out.get(0).writeObject(p);
            out.get(0).flush();
            out.get(1).writeObject(p);
            out.get(1).flush();
            
        }else if(jogadores.get(0).getPontuacao() > jogadores.get(1).getPontuacao()){
            Protocolo p = new Protocolo(null, InformacaoControle.Ganhou, null, null, null,
                    null, null, null);
            out.get(0).writeObject(p);
            out.get(0).flush();
            p = new Protocolo(null, InformacaoControle.Perdeu, null, null, null,
                    null, null, null);
            out.get(1).writeObject(p);
            out.get(1).flush();
        }else{
            Protocolo p = new Protocolo(null, InformacaoControle.Perdeu, null, null, null,
                    null, null, null);
            out.get(0).writeObject(p);
            out.get(0).flush();
            p = new Protocolo(null, InformacaoControle.Ganhou, null, null, null,
                    null, null, null);
            out.get(1).writeObject(p);
            out.get(1).flush();
        }
    }

    public void comecarJogo() throws IOException {
        Protocolo protocolo = new Protocolo(null, InformacaoControle.Comecou, null, null, null, null, null, null);

        for (ObjectOutputStream output : out) {
            output.writeObject(protocolo);
            output.flush();
        }
    }

    public void anunciarPlacar(List<Jogador> j) throws IOException {
        Protocolo protocolo = new Protocolo(null, InformacaoControle.Placar,
                j.get(0).getNome(), j.get(0).getPontuacao(), j.get(1).getNome(), j.get(1).getPontuacao(), null, null);
        System.out.println();
        for (ObjectOutputStream output : out) {
            output.writeObject(protocolo);
            output.flush();
        }

    }

    public void anunciarPergunta(Pergunta pergunta) throws IOException {
        PerguntaDTO perguntaDTO = new PerguntaDTO(pergunta.getPergunta(),
                pergunta.getAlternativas(), pergunta.getPontuacao());
        Protocolo protocolo = new Protocolo(null, InformacaoControle.Pergunta,
                null, null, null, null, perguntaDTO, null);
        for (ObjectOutputStream output : out) {
            output.writeObject(protocolo);
            output.flush();
        }

    }

    public void jogo() throws ClassNotFoundException {
        int respostaJogador;
        int i = 0;
        Protocolo protocolo;
        int jogadorProximo;
        int j;
        try {
            solicitarNomes();
            comecarJogo();
            for (Pergunta pergunta : perguntas) {
                j = 0;
                jogadorProximo = i + 1;
                while (j < 2) {
                    anunciarPlacar(jogadores);
                    anunciarPergunta(pergunta);
                    protocolo = new Protocolo(null, InformacaoControle.Vez,
                            null, null, null, null, null, null);
                    out.get(i % 2).writeObject(protocolo);
                    out.get(i % 2).flush();
                    protocolo = (Protocolo) in.get(i % 2).readObject();

                    if (protocolo.getInformacaoControle().equals(InformacaoControle.Resposta)) {
                        if (pergunta.getResposta().equals(protocolo.getResposta())) {
                            jogadores.get(i % 2).acertou();

                            protocolo = new Protocolo(null, InformacaoControle.Acertou,
                                    null, null, null, null, null, null);
                            out.get(i % 2).writeObject(protocolo);
                            out.get(i % 2).flush();
                            protocolo = new Protocolo(null, InformacaoControle.AcabouVez,
                                    null, null, null, null, null, null);
                            out.get(i % 2).writeObject(protocolo);
                            out.get(i % 2).flush();
                            i = jogadorProximo;
                            break;
                        } else {
                            protocolo = new Protocolo(null, InformacaoControle.Errou,
                                    null, null, null, null, null, null);
                            out.get(i % 2).writeObject(protocolo);
                            out.get(i % 2).flush();
                        }

                    }
                    protocolo = new Protocolo(null, InformacaoControle.AcabouVez,
                            null, null, null, null, null, null);
                    out.get(i % 2).writeObject(protocolo);
                    out.get(i % 2).flush();
                    i++;
                    j++;
                }
                i = jogadorProximo;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void solicitarNomes() throws IOException, ClassNotFoundException {

        int i = 0;

        for (Socket s : sockets) {
            Protocolo protocolo = new Protocolo(null, InformacaoControle.SolicitandoNome,
                    null, null, null, null, null, null);
            out.get(i).writeObject(protocolo);
            out.get(i).flush();
            protocolo = (Protocolo) in.get(i).readObject();
            if (protocolo.getInformacaoControle().equals(InformacaoControle.Nome)) {
                jogadores.add(new Jogador(protocolo.getNomeJogador()));
            }
            i++;
        }

    }

    public void preparativos() throws IOException {

        int i = 0;

        Protocolo protocolo = new Protocolo(null, InformacaoControle.Preparativos,
                null, null, null, null, null, null);

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
                    null, null, null, null, null, null);

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
