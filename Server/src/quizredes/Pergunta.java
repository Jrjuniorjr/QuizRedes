
package quizredes;

import java.io.Serializable;
import java.util.List;

public class Pergunta{

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
