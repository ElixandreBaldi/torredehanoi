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
public class PossibilidadesPino {
    private PilhaPinos[] possibilidades;
    private int qtd;

    public PossibilidadesPino(PilhaPinos possibilidades) {
        push(possibilidades);
        this.qtd = 0;
    }
    
    public PossibilidadesPino(PilhaPinos[] possibilidades) {
        this.possibilidades = possibilidades;
    }   
    
    public PilhaPinos[] getPossibilidades() {
        return possibilidades;
    }

    public void setPossibilidades(PilhaPinos[] possibilidades) {
        this.possibilidades = possibilidades;
    }
    
    public void push(PilhaPinos possibilidade) {        
        PilhaPinos[] temp = this.possibilidades;
        this.qtd++;
        this.possibilidades = new PilhaPinos[qtd];       
        if(qtd > 1)
            System.arraycopy(temp, 0, this.possibilidades, 0, qtd - 1);  
        
        possibilidades[this.possibilidades.length - 1] = possibilidade;                 
    }
    
    public boolean isPossibilidade(PilhaPinos possibilidade) {        
        for(int i = 0; i < this.possibilidades.length; i++) {
            if(this.possibilidades[i].isEqual(possibilidade))
                return true;
        }
        return false;
    }
    
    public void print() {
        for(int i = 0; i < this.possibilidades.length; i++) {
            possibilidades[i].print();
        }
    }
}
