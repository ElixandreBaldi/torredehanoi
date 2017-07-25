package torrehanoy;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elixandrebaldi
 */
public class Discos {
    private int tamanho;

    Discos(int i){
        this.tamanho = i;        
    }
    
    public int getTamanho() {
        return tamanho;
    }

    public Discos getCopia() {
        Discos retorno = new Discos(this.tamanho);
        
        return retorno;
    }
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
}
