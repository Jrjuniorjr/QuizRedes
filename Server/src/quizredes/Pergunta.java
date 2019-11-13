/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizredes;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Junior
 */
public class Pergunta implements Serializable {

    private static final long serialVersionUID = 1L;
    private String pergunta;
    private List<String> alternativas;
    private Integer resposta;
    private Integer pontuacao;

    public Pergunta(String pergunta, List<String> alternativas, Integer resposta, Integer pontuacao) {
        this.pergunta = pergunta;
        this.alternativas = alternativas;
        this.resposta = resposta;
        this.pontuacao = pontuacao;
    }

    
    public void perguntaErrada(){
        this.pontuacao--;
    }
    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public List<String> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<String> alternativas) {
        this.alternativas = alternativas;
    }

    public Integer getResposta() {
        return resposta;
    }

    public void setResposta(Integer resposta) {
        this.resposta = resposta;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

}
