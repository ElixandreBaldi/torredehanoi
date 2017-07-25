/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torrehanoy;

/**
 *
 * @author elixandrebaldi
 */
public class PilhaPinos {
    private int topo;
    private Discos[] pilha;
    
    PilhaPinos() {
        topo = -1;
        pilha = new Discos[0];
    }
    
    PilhaPinos(Discos[] pilha) {
        topo = pilha.length - 1;
        this.pilha = pilha;
    }
    
    public Discos pop() {
        Discos temp = pilha[topo];
        Discos[] temp2 = pilha;
                
        pilha = new Discos[temp2.length-1];
        for(int i = 0; i < pilha.length; i++) {
            pilha[i] = new Discos(temp2[i].getTamanho());
        }
        topo--; 
        
        return temp;        
    }
    
    public int getQtdDiscos() {
        return pilha.length;
    }
    public PilhaPinos getCopia(){
        Discos[] r = new Discos[pilha.length];
        for(int i = 0; i < r.length; i++) {
            r[i] = pilha[i].getCopia();
        }
        
        return new PilhaPinos(r);        
    }
    
    public boolean push(Discos disco) {        
        Discos[] temp = pilha;
        pilha = new Discos[temp.length+1];        
        System.arraycopy(temp, 0, pilha, 0, temp.length);        
        topo = pilha.length - 1;        
        pilha[topo] = disco;      
        return true;        
    }
    
    public int getTamanhoTopo(){
        if(topo < 0)
            return Integer.MAX_VALUE; //numero bem alto
        return pilha[topo].getTamanho();
    }        
    
    public void print() {  
        System.out.print("[");
        for(int i = 0; i <= topo; i++){
            System.out.print(pilha[i].getTamanho());            
            if(i < topo)
                System.out.print(",");
            else
                System.out.print("*");
        }
        System.out.println("]");
    }
    
    public boolean estaVazio() {
        if(topo == -1)
            return true;
        return false;
    }    

    public Discos[] getPilhaDiscos() {
        return pilha;
    }
    
    public boolean verificaIgualdade(Discos testado[]) {
        if(pilha.length != testado.length)
            return false;
        for(int i = 0; i < pilha.length; i++)
            if(pilha[i].getTamanho() != testado[i].getTamanho())
                return false;
        return true;
    }
    public boolean isEqual(PilhaPinos possibilidade) {
        if(possibilidade.getPilhaDiscos().length != this.pilha.length)           
            return false;        
        
        for(int i = 0; i < pilha.length; i++)
            if(possibilidade.getPilhaDiscos()[i].getTamanho() != pilha[i].getTamanho())
                return false;            
        
        return true;
    }
}
