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
public class Placar implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Jogador> jogadores;

    public Placar(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}
