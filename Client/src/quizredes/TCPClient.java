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
import util.Protocolo;

/**
 *
 * @author Junior
 */
public class TCPClient {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private PerguntaDTO pergunta;

    public TCPClient(Socket socket) throws IOException, ClassNotFoundException {
        this.socket = socket;

    }

    public void run() throws Exception {
        preparativos();
        serverSolicitaNome();

        jogo();
        
        fecharConexao();

    }

    
    public void fecharConexao() throws IOException{
        in.close();
        out.close();
        socket.close();
    }
    public Protocolo comecarJogo() throws Exception {
        Protocolo p = (Protocolo) in.readObject();
        return p;
    }

    public void jogo() throws Exception {
        Protocolo p = comecarJogo();
        System.out.println("Aguarde até sua vez.");
        while (!p.getInformacaoControle().equals(InformacaoControle.AcabouPartida)) {
            p = (Protocolo) in.readObject();
            if (p.getInformacaoControle().equals(InformacaoControle.Placar)) {
                serverAnunciarPlacar(p);
            } else if (p.getInformacaoControle().equals(InformacaoControle.Pergunta)) {
                serverAnunciarPergunta(p);
            } else if (p.getInformacaoControle().equals(InformacaoControle.Vez)) {
                System.out.println(pergunta.getPergunta());
                System.out.println(pergunta.getAlternativas());
                Scanner s = new Scanner(System.in);
                Integer resposta = s.nextInt();
                p = new Protocolo(p.getNomeJogador(), InformacaoControle.Resposta, null, null, null, null, null, resposta);
                out.writeObject(p);
                out.flush();
            } else if (p.getInformacaoControle().equals(InformacaoControle.Acertou)) {
                System.out.println("Parabéns, você acertou!");
            } else if (p.getInformacaoControle().equals(InformacaoControle.Errou)) {
                System.out.println("Você errou, vamos ver se seu adversário consegue acertar.");
            } else if (p.getInformacaoControle().equals(InformacaoControle.AcabouVez)) {
                System.out.println("Não é sua vez! Aguarde!");
            } else if(p.getInformacaoControle().equals(InformacaoControle.Empatou)){
                System.out.println("Partida Empada");
            }
            else if(p.getInformacaoControle().equals(InformacaoControle.Perdeu)){
                System.out.println("Você perdeu");
            }
            else if(p.getInformacaoControle().equals(InformacaoControle.Ganhou)){
                System.out.println("Você ganhou");
            }
            
        }
        System.out.println("Acabou a partida.");
    }

    public void serverSolicitaNome() throws IOException, ClassNotFoundException {
        Scanner s = new Scanner(System.in);
        Protocolo p = (Protocolo) in.readObject();
        if (p.getInformacaoControle().equals(InformacaoControle.SolicitandoNome)) {
            System.out.println("Digite o nome: ");
            String nomeJogador = s.next();
            p = new Protocolo(nomeJogador, InformacaoControle.Nome, null, null, null, null, null, null);
            out.writeObject(p);
            out.flush();
        }
    }

    public void serverAnunciarPergunta(Protocolo p) throws IOException, ClassNotFoundException {
       // return (PerguntaDTO) p.getPerguntaDTO();
       this.pergunta = (PerguntaDTO) p.getPerguntaDTO();
    }

    public void serverAnunciarPlacar(Protocolo p) throws IOException, ClassNotFoundException {
        System.out.println(p.getNomeJogador1Placar() + ": " + p.getPontuacaoJogador1());
        System.out.println(p.getNomeJogador2Placar() + ": " + p.getPontuacaoJogador2());
        //System.out.println(p.getPontuacaoJogadores().get(1).getNome() + ": " + p.getPontuacaoJogadores().get(1).getPontuacao());
    }

    public void preparativos() throws IOException, ClassNotFoundException {
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        Protocolo p = (Protocolo) in.readObject();
        System.out.println("Aguarde a solicitação do nome");

    }

}
