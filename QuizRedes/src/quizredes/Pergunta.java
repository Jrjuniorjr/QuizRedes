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
    private List<String> alternativa;
    private int resposta;
    private int pontuacao;

    public Pergunta(String pergunta, List<String> alternativa, int resposta, int pontuacao) {
        this.pergunta = pergunta;
        this.alternativa = alternativa;
        this.resposta = resposta;
        this.pontuacao = pontuacao;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public List<String> getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(List<String> alternativa) {
        this.alternativa = alternativa;
    }

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

}
