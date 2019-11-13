/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;

/**
 *
 * @author Junior
 */
public class Protocolo implements Serializable{
    private String nomeJogador;
    private InformacaoControle informacaoControle;
    private Placar placar;
    private PerguntaDTO perguntaDTO;
    private Integer resposta;

    public Protocolo(String nomeJogador, InformacaoControle informacaoControle, Placar placar, PerguntaDTO perguntaDTO, Integer resposta) {
        this.nomeJogador = nomeJogador;
        this.informacaoControle = informacaoControle;
        this.placar = placar;
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

    public Placar getPlacar() {
        return placar;
    }

    public void setPlacar(Placar placar) {
        this.placar = placar;
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
