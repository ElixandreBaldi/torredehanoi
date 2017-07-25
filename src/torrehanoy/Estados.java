/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torrehanoy;

import static torrehanoy.TorreHanoi.qtdEstados;

/**
 *
 * @author elixandrebaldi
 */
public class Estados {
    private PilhaPinos pinos[];    
    private boolean possiveisCaminhos[];
    private int valorHeuristico;
    private int custoChegada;
    private int funcaoAvaliadora;
    
    Estados() {
        this.pinos = new PilhaPinos[3];        
        this.possiveisCaminhos = new boolean[qtdEstados];
    }
    
    Estados(PilhaPinos p[]) {        
        this.pinos = new PilhaPinos[3]; 
        for(int i = 0; i < 3; i++) {
            this.pinos[i] = p[i];
        }
        this.possiveisCaminhos = new boolean[qtdEstados];        
    }

    public int getCustoChegada() {
        return custoChegada;
    }

    public void setCustoChegada(int custoChegada) {
        this.custoChegada = custoChegada;
        this.funcaoAvaliadora = this.valorHeuristico + this.custoChegada;
    }

    public int getFuncaoAvaliadora() {
        return funcaoAvaliadora;
    }

    public void setFuncaoAvaliadora(int funcaoAvaliadora) {
        this.funcaoAvaliadora = funcaoAvaliadora;
    }

    
    public int getValorHeuristico() {
        return valorHeuristico;
    }

    public void setValorHeuristico(int valorHeuristico) {
        this.valorHeuristico = valorHeuristico;
    }
        
    public void inicializaPossiveisCaminhos() {
        for(int i = 0; i < qtdEstados; i++)
            possiveisCaminhos[i] = false;
    }
    public boolean verificaCaminho(int i) {
        return possiveisCaminhos[i];
    }
    public PilhaPinos[] getPinos() {
        return pinos;
    }    
    
    public void setPossivelCaminho(int i) {
        possiveisCaminhos[i] = true;
    }
    
    public Estados getCopia() {
        PilhaPinos r[] = new PilhaPinos[3];
        for(int i = 0; i < 3; i++) {
            r[i] = pinos[i].getCopia();
        }
        
        return new Estados(r);
    }
    
    public boolean verificaIgualdade(Estados testado) {
        for(int i = 0; i < 3; i++)
            if(!(pinos[i].verificaIgualdade(testado.getPinos()[i].getPilhaDiscos())))
                return false;
        return true;
    }
    
    public void print() {
        System.out.println("");
        System.out.println("#############");
        pinos[0].print();
        pinos[1].print();
        pinos[2].print();
        System.out.println("#############");
        System.out.println("");
    }
}
