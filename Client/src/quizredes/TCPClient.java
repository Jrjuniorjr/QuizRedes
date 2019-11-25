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
import util.PerguntaDTO;
import util.Placar;
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

    public void run() throws Exception {
        preparativos();
        serverSolicitaNome();

        jogo();

    }

    public Protocolo comecarJogo() throws Exception {
        Protocolo p = (Protocolo) in.readObject();
        return p;
    }

    public void jogo() throws Exception {
        Protocolo p = comecarJogo();
        PerguntaDTO pergunta;

        while (!p.getInformacaoControle().equals(InformacaoControle.AcabouPartida)) {
            p = (Protocolo) in.readObject();
            if (p.getInformacaoControle().equals(InformacaoControle.Placar)) {
                serverAnunciarPlacar(p);
            } else if (p.getInformacaoControle().equals(InformacaoControle.Pergunta)) {
                pergunta = serverAnunciarPergunta(p);
                System.out.println(pergunta.getPergunta());
                System.out.println(pergunta.getAlternativas());
            } else if(p.getInformacaoControle().equals(InformacaoControle.Vez)){
                Scanner s = new Scanner(System.in);
                Integer resposta = s.nextInt();
                 p = new Protocolo(p.getNomeJogador(), InformacaoControle.Resposta, null, null, resposta);
                 out.writeObject(p);
                 out.flush();
            }else if(p.getInformacaoControle().equals(InformacaoControle.Acertou)){
                System.out.println("Parabéns, você acertou!");
            }else if(p.getInformacaoControle().equals(InformacaoControle.Errou)){
                System.out.println("Você errou, vamos ver se seu adversário consegue acertar.");
            }else if(p.getInformacaoControle().equals(InformacaoControle.AcabouVez)){
                System.out.println("Não é sua vez! Aguarde!");
            }
        }
    }

    public void serverSolicitaNome() throws IOException, ClassNotFoundException {
        Scanner s = new Scanner(System.in);
        Protocolo p = (Protocolo) in.readObject();
        if (p.getInformacaoControle().equals(InformacaoControle.SolicitandoNome)) {
            System.out.println("Digite o nome: ");
            String nomeJogador = s.next();
            p = new Protocolo(nomeJogador, InformacaoControle.Nome, null, null, null);
            out.writeObject(p);
            out.flush();
        }
    }

    public PerguntaDTO serverAnunciarPergunta(Protocolo p) throws IOException, ClassNotFoundException {
        return (PerguntaDTO) p.getPerguntaDTO();
    }

    public void serverAnunciarPlacar(Protocolo p) throws IOException, ClassNotFoundException {
        System.out.println(p.getPlacar().getJogadores().get(0).getNome() + ": " + p.getPlacar().getJogadores().get(0).getPontuacao());
        System.out.println(p.getPlacar().getJogadores().get(1).getNome() + ": " + p.getPlacar().getJogadores().get(1).getPontuacao());
    }

    public void preparativos() throws IOException, ClassNotFoundException {
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        Protocolo p = (Protocolo) in.readObject();
        System.out.println("Conseguiu");

    }

}
