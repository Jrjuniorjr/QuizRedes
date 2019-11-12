/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizredes;

import java.util.List;

/**
 *
 * @author Junior
 */
public class PerguntaDTO {
      private String pergunta;
    private List<String> alternativas;
    private Integer pontuacao;

    public PerguntaDTO(String pergunta, List<String> alternativas, Integer pontuacao) {
        this.pergunta = pergunta;
        this.alternativas = alternativas;
        this.pontuacao = pontuacao;
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

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

}
