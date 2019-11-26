/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Junior
 */
public class Protocolo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nomeJogador;
    private InformacaoControle informacaoControle;
    //private Placar placar;
    // private List<Jogador> pontuacaoJogadores;

    private String nomeJogador1Placar;
    private Integer pontuacaoJogador1;
    private String nomeJogador2Placar;
    private Integer pontuacaoJogador2;
    private PerguntaDTO perguntaDTO;
    private Integer resposta;

    public Protocolo(String nomeJogador, InformacaoControle informacaoControle,
            String nomeJogador1Placar, Integer pontuacaoJogador1, String nomeJogador2Placar,
            Integer pontuacaoJogador2, PerguntaDTO perguntaDTO, Integer resposta) {
        this.nomeJogador = nomeJogador;
        this.informacaoControle = informacaoControle;
        this.nomeJogador1Placar = nomeJogador1Placar;
        this.pontuacaoJogador1 = pontuacaoJogador1;
        this.nomeJogador2Placar = nomeJogador2Placar;
        this.pontuacaoJogador2 = pontuacaoJogador2;
        this.perguntaDTO = perguntaDTO;
        this.resposta = resposta;
    }


    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public InformacaoControle getInformacaoControle() {
        return informacaoControle;
    }

    public void setInformacaoControle(InformacaoControle informacaoControle) {
        this.informacaoControle = informacaoControle;
    }

    public String getNomeJogador1Placar() {
        return nomeJogador1Placar;
    }

    public void setNomeJogador1Placar(String nomeJogador1Placar) {
        this.nomeJogador1Placar = nomeJogador1Placar;
    }

    public Integer getPontuacaoJogador1() {
        return pontuacaoJogador1;
    }

    public void setPontuacaoJogador1(Integer pontuacaoJogador1) {
        this.pontuacaoJogador1 = pontuacaoJogador1;
    }

    public String getNomeJogador2Placar() {
        return nomeJogador2Placar;
    }

    public void setNomeJogador2Placar(String nomeJogador2Placar) {
        this.nomeJogador2Placar = nomeJogador2Placar;
    }

    public Integer getPontuacaoJogador2() {
        return pontuacaoJogador2;
    }

    /*  public List<Jogador> getPontuacaoJogadores() {
    return pontuacaoJogadores;
    }
    public void setPontuacaoJogadores(List<Jogador> pontuacaoJogadores) {
    this.pontuacaoJogadores = pontuacaoJogadores;
    }
    /
    /* public Placar getPlacar() {
    return placar;
    }
    public void setPlacar(Placar placar) {
    this.placar = placar;
    }
     */
    public void setPontuacaoJogador2(Integer pontuacaoJogador2) {
        this.pontuacaoJogador2 = pontuacaoJogador2;
    }

    public PerguntaDTO getPerguntaDTO() {
        return perguntaDTO;
    }

    public void setPerguntaDTO(PerguntaDTO perguntaDTO) {
        this.perguntaDTO = perguntaDTO;
    }

    public Integer getResposta() {
        return resposta;
    }

    public void setResposta(Integer resposta) {
        this.resposta = resposta;
    }

}
