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
public class DistanciaEstadosFinais {
    private int distancias[];
    
    DistanciaEstadosFinais() {
        distancias = new int[qtdEstados];
    }
    
    public void setDistancias(int d[]) {
        distancias = new int[qtdEstados];
        for(int i = 0; i < qtdEstados; i++)
            this.distancias[i] = d[i];
    }
    
    public int getDistancia(int i) {
        return distancias[i];
    }
}
