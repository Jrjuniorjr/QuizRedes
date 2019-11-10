/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizredes;

/**
 *
 * @author Junior
 */
public class Jogador {
    
    private String nome;
    private int pontuacao;
    private boolean vez;

    public Jogador(String nome, boolean vez) {
        this.pontuacao = 0;
        this.nome = nome;
        this.vez = vez;
    }

    public void acertou(){
        pontuacao++;
    }
    
    
    //GETTERS & SETTERS
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public boolean isVez() {
        return vez;
    }

    public void setVez(boolean vez) {
        this.vez = vez;
    }
    
    
    
    
}
