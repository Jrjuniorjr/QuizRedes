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
public class Jogador implements Serializable{
    
    private static long serialVersionUID = 1L;
    private String nome;
    private Integer pontuacao;

    public Jogador(String nome) {
        this.pontuacao = 0;
        this.nome = nome;
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

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }
   
}
