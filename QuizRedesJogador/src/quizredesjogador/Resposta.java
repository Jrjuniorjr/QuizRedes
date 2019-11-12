/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizredesjogador;

/**
 *
 * @author Junior
 */
public class Resposta{
    
    
    public static final int PASSARPERGUNTA = -2;
    public static final int TIMEOUTPERGUNTA = -1;
    
    private int resposta;

    public Resposta(int resposta) {
        this.resposta = resposta;
    }

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }
    
    
    
}
