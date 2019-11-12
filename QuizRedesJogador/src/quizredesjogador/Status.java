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
public class Status extends Protocolo{
    
    private TipoStatus tipoStatus;

    public Status(TipoStatus tipoStatus, TipoProtocolo tipoProtocolo) {
        super(tipoProtocolo);
        this.tipoStatus = tipoStatus;
    }

    public TipoStatus getTipoStatus() {
        return tipoStatus;
    }

    public void setTipoStatus(TipoStatus tipoStatus) {
        this.tipoStatus = tipoStatus;
    }
    
    
    
}
