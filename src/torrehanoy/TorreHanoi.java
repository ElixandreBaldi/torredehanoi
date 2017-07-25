/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torrehanoy;

import static java.lang.Math.pow;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author elixandrebaldi
 */
public class TorreHanoi {

    /**
     * @param args the command line arguments
     */
    public static double tempoInicial;
    public static int qtdDiscos = 8;
    public static int tipoBusca = 1;
    public static int contadorVisitas = 0;
    public static DistanciaEstadosFinais distEstados[] = new DistanciaEstadosFinais[2];
    public static int estadoFinal[] = new int[2];
    public static int iEstadoFinal = 0;
    public static Queue q = new LinkedList();
    public static boolean estadoFinalEncontrado = false;
    public static int pinoCheioInicial = 0;
    public static int estadoInicial = -1;    
    public static int qtdEstados = (int) pow(3,qtdDiscos);    
    public static int possibilidadesPorBase = qtdEstados/3;
    public static Estados estado[] = new Estados[qtdEstados];
    public static int iEstados = 0;
    public static PilhaPinos pino[] = new PilhaPinos[3];
    public static int matrizAdjacencia[][] = new int[qtdEstados][qtdEstados];    
    public static int matrizAdjacenciaCopia[][] = new int[qtdEstados][qtdEstados];   
    public static PossibilidadesPino possibilidades1 = new PossibilidadesPino(new PilhaPinos());
    public static PossibilidadesPino possibilidades2 = new PossibilidadesPino(new PilhaPinos());
    public static PossibilidadesPino possibilidades3 = new PossibilidadesPino(new PilhaPinos());
    
    public static void printPinos () {        
        for(int i = 0; i < 3; i++) {
            System.out.print("Pino"+ (i+1) +": ");
            pino[i].print();
        }
        System.out.println("");        
    }
    
    public static boolean verificaMovimento(int discoTopo, int discoBase) {
        if (discoTopo >= discoBase)
            return false;
        return true;        
    }
    
    public static void movimento(int pop, int push) {
        if(verificaMovimento(pino[pop].getTamanhoTopo(),pino[push].getTamanhoTopo())) {
            pino[push].push(pino[pop].pop());
        }        
        //printPinos();
    }
    
    public static void reiniciarPinos() {
        pino[0] = new PilhaPinos();
        pino[1] = new PilhaPinos();
        pino[2] = new PilhaPinos();
    }       
    
    public static PossibilidadesPino possibilidadesUmPino(int[] estados, PossibilidadesPino possibilidades) {                
        reiniciarPinos();
        for(int i = 0; i < qtdDiscos; i++) {
            if(estados[i] >= 0)
                pino[0].push(new Discos(estados[i]));
        }
        
        if(!possibilidades.isPossibilidade(pino[0]))
            possibilidades.push(pino[0]);                 
        
        for(int i = 0; i < qtdDiscos; i++) {
            while(estados[i] == -1) {
                i++;
                if(i >= qtdDiscos)
                    return possibilidades;
            }
            int temp = estados[i];
            estados[i] = -1;            
            possibilidades = possibilidadesUmPino(estados, possibilidades);            
            estados[i] = temp;
        }
        
        return possibilidades;
    }
    
    public static void setPinos() { //teria que ser todos
        int pinoDisco[] = new int [qtdDiscos];
        for(int i = 0; i < qtdDiscos; i++)
            pinoDisco[i] = 0;
    }   
    
    public static void printEstados() {
        for(int i = 0; i < estado.length; i++) {
            estado[i].print();
        }
    }
    
    public static boolean verificarCombinacaoPossibilidade(int i, int j, int k) {
        boolean bitMap[] = new boolean[qtdDiscos];
        for(int s = 0; s < qtdDiscos; s++)
            bitMap[s] = false;
        
        for(int s = 0; s < possibilidades1.getPossibilidades()[i].getPilhaDiscos().length; s++){
            bitMap[possibilidades1.getPossibilidades()[i].getPilhaDiscos()[s].getTamanho()] = true;
        }
        
        for(int s = 0; s < possibilidades2.getPossibilidades()[j].getPilhaDiscos().length; s++){
            if(bitMap[possibilidades2.getPossibilidades()[j].getPilhaDiscos()[s].getTamanho()])
                return false;
            bitMap[possibilidades2.getPossibilidades()[j].getPilhaDiscos()[s].getTamanho()] = true;
        }
        
        for(int s = 0; s < possibilidades3.getPossibilidades()[k].getPilhaDiscos().length; s++){
            if(bitMap[possibilidades3.getPossibilidades()[k].getPilhaDiscos()[s].getTamanho()])
                return false;
            bitMap[possibilidades3.getPossibilidades()[k].getPilhaDiscos()[s].getTamanho()] = true;
        }        
        
        for(int s = 0; s < bitMap.length; s++)
            if(!bitMap[s])
                return false;
        
        return true;
    }
    public static void mapearPossibilidades() {
        for(int i = 0; i < possibilidades1.getPossibilidades().length; i++) {
            for(int j = 0; j < possibilidades2.getPossibilidades().length; j++) {
                for(int k = 0; k < possibilidades3.getPossibilidades().length; k++) {
                    if(verificarCombinacaoPossibilidade(i,j,k)){
                        
                        pino[0] = possibilidades1.getPossibilidades()[i];
                        pino[1] = possibilidades2.getPossibilidades()[j];
                        pino[2] = possibilidades3.getPossibilidades()[k];
                        estado[iEstados] = new Estados(pino);
                        reiniciarPinos();
                        iEstados++;
                        
                        //if(iEstados == qtdEstados)
                          //  return;
                    }
                }
            }
        }
    }
    
    public static int verificaExistenciaEstadoIgual(Estados testado) {
        
        for(int i = 0; i < qtdEstados; i++) {
            if(estado[i].verificaIgualdade(testado)){        
                return i;
            }
        }
        return -1;
    }
    
    public static boolean verificaEstadoIgual(Estados testado1, Estados testado2) {                
        if(testado1.verificaIgualdade(testado2)){        
            return true;
        }
        
        return false;
    }
    
    public static void printMatrizAdjacencia() {
        System.out.print("     ");
        for(int i = 0; i < qtdEstados; i++) {
            System.out.print(i+"");
            if(i < 10)
                System.out.print("|  ");
            else
                System.out.print("| ");
        }        
        System.out.println("");
        
        for(int i = 0; i < qtdEstados; i++) {
            for(int j = 0; j < qtdEstados; j++) {
                if(j == 0){
                    System.out.print(i);
                    if(i < 10)
                        System.out.print(" |  ");
                    else
                        System.out.print("|  ");
                }
                System.out.print(matrizAdjacencia[i][j]+"   ");
            }
            System.out.println("");
        }
    }
    
    public static boolean verificarEstadoFinal(int j) {
        if(!estado[j].getPinos()[pinoCheioInicial].estaVazio())
            return false;
        
        for(int i = 0; i < 3; i++){
            if(i != pinoCheioInicial && estado[j].getPinos()[i].estaVazio())
                return true;                           
        }
        
        return false;
    }
    
    public static void criarMatrizAdjacencia() {
        for(int i = 0; i < qtdEstados; i++) {
            for(int j = i; j < qtdEstados; j++) {
                if(i == j)
                    matrizAdjacencia[i][j] = 0;
                else {
                    if(estado[i].verificaCaminho(j))
                        matrizAdjacencia[i][j] = 1;
                    else
                        matrizAdjacencia[i][j] = 0;           
                    matrizAdjacencia[j][i] = matrizAdjacencia[i][j];
                }                 
            }            
        } 
    }
        
    public static boolean verificarEstadoInicial(PilhaPinos p[]) { 
        if(p[pinoCheioInicial].getQtdDiscos() == qtdDiscos)
            return true;
        return false;
    }
    
    public static void zerarColunaMatrizAdjacenciaCopia(int coluna) {
        for(int i = 0; i < qtdEstados; i++)
            matrizAdjacenciaCopia[i][coluna] = 0;
    }
    
    public static void buscaProfundidade(int linha) {
        zerarColunaMatrizAdjacenciaCopia(linha);
        System.out.println("Visita: "+linha+"      contador: "+contadorVisitas);
        contadorVisitas++;
        estado[linha].print();
        
        if(verificarEstadoFinal(linha)) {
            double tempoEx = System.currentTimeMillis()-tempoInicial;
            System.out.println("Encontrado Estado Final: "+linha+"    tempo de execuçao: "+tempoEx/1000+" segundos");
            estado[linha].print();
            estadoFinalEncontrado = true;
            return;
        }        
            
        for(int i = 0; i < qtdEstados; i++) {
            if(estadoFinalEncontrado)
                return;
            if(matrizAdjacenciaCopia[linha][i] == 1)
                buscaProfundidade(i);
        }
    }
    
    public static void buscaLargura(int linha) {                
        System.out.println("Visita: "+linha+"      contador: "+contadorVisitas);
        contadorVisitas++;
        estado[linha].print();
        
        if(verificarEstadoFinal(linha)) {
            double tempoEx = System.currentTimeMillis()-tempoInicial;
            System.out.println("Encontrado Estado Final: "+linha+"    tempo de execuçao: "+tempoEx/1000+" segundos");
            estado[linha].print();
            estadoFinalEncontrado = true;
            return;
        }  
        
        for(int i = 0; i < qtdEstados; i++) {
            if(matrizAdjacenciaCopia[linha][i] == 1) {
                q.add(i);
                zerarColunaMatrizAdjacenciaCopia(i);
            }
        }
        if(estadoFinalEncontrado) {
            while(!q.isEmpty())
                q.remove();
            return;
        }
        buscaLargura((int) q.remove());
    }
    
    public static void buscaGulosa(int linha) {
        int proximaVisita = -1;
        int heuristica = Integer.MAX_VALUE;
        System.out.println("Visita: "+linha+"      contador: "+contadorVisitas);
        contadorVisitas++;
        estado[linha].print();
        
        if(verificarEstadoFinal(linha)) {
            double tempoEx = System.currentTimeMillis()-tempoInicial;
            System.out.println("Encontrado Estado Final: "+linha+"    tempo de execuçao: "+tempoEx/1000+" segundos");
            estado[linha].print();
            estadoFinalEncontrado = true;
            return;
        } 
        
        for(int i = 0; i < qtdEstados; i++) {
            if(matrizAdjacenciaCopia[linha][i] == 1 && estado[i].getValorHeuristico() < heuristica) {
                proximaVisita = i;
                heuristica = estado[i].getValorHeuristico();
            }
        }
        zerarColunaMatrizAdjacenciaCopia(linha);
        buscaGulosa(proximaVisita);
    }
    
    public static void buscaAEstrela(int linha, int[] visitados) {
        visitados[linha] = 1;
        int proximaVisita = -1;
        int pesoProximaVisita = Integer.MAX_VALUE;
        System.out.println("Visita: "+linha+"      contador: "+contadorVisitas);
        contadorVisitas++;
        estado[linha].print();
        
        if(verificarEstadoFinal(linha)) {
            double tempoEx = System.currentTimeMillis()-tempoInicial;
            System.out.println("Encontrado Estado Final: "+linha+"    tempo de execuçao: "+tempoEx/1000+" segundos");
            estado[linha].print();
            estadoFinalEncontrado = true;            
            return;
        }         
                
        for(int i = 0; i < qtdEstados; i++) {
            if(matrizAdjacenciaCopia[linha][i] != 0 && visitados[i] < 1) {
                estado[i].setCustoChegada(estado[linha].getCustoChegada() + matrizAdjacenciaCopia[linha][i]);
                visitados[i] = 0;   
            }
        }
        
        for(int i = 0; i < qtdEstados; i++) {
            if(visitados[i] == 0) {
                if(estado[i].getFuncaoAvaliadora() < pesoProximaVisita) {
                    proximaVisita = i;
                    pesoProximaVisita = estado[i].getFuncaoAvaliadora();
                }
            }
        }
        
        buscaAEstrela(proximaVisita, visitados);
    }
    
    public static void copiarMatrizAdjacencia() {
        for(int i = 0; i < qtdEstados; i++)
            for(int j = 0; j < qtdEstados; j++)
                matrizAdjacenciaCopia[i][j] = matrizAdjacencia[i][j];
    }        
    
    public static void printValoresHeuristicos() {
        for(int i = 0; i < qtdEstados; i++)
            System.out.println("nodo "+i+" distancia: "+estado[i].getValorHeuristico());
    }
    
    public static int[] dijkstra (int origem) {
        boolean fixo[] = new boolean[qtdEstados];
        double dist[] = new double[qtdEstados];
        int i;
        int faltam;

        for(i=0; i<qtdEstados; i++) {
            fixo[i] = false;
            dist[i] = Double.POSITIVE_INFINITY;
        }        
        dist[origem] = 0;              
        
        for(faltam = qtdEstados; faltam > 0; faltam--) {
            int no = -1;
            for(i = 0; i < qtdEstados; i++) {
                if(!fixo[i] && (no == -1 || dist[i] < dist[no])) {                    
                    no = i;
                }
            }
            fixo[no] = true;

            if(dist[no] >= Double.POSITIVE_INFINITY) 
                break;
            

            for(i = 0; i < qtdEstados; i++) {
                if(dist[i] > dist[no] + matrizAdjacencia[no][i] && matrizAdjacencia[no][i] != 0) {                    
                    dist[i] = dist[no] + matrizAdjacencia[no][i];                     
                }                    
            }
        }
        int retorno[] = new int[qtdEstados];
        
        for(i = 0; i < qtdEstados; i++)
            retorno[i] = (int)dist[i];   
        
        return retorno;
    }
    
    public static void setarDistanciasEstadosFinais() {
        for(int i = 0; i < 2; i++) {            
            distEstados[i] = new DistanciaEstadosFinais();     
            distEstados[i].setDistancias(dijkstra(estadoFinal[i]));
        }
    }
    
    public static void setarHeuristicas() {
        for(int i = 0; i < qtdEstados; i++) {
            if(distEstados[0].getDistancia(i) < distEstados[1].getDistancia(i))
                estado[i].setValorHeuristico(distEstados[0].getDistancia(i));
            else
                estado[i].setValorHeuristico(distEstados[1].getDistancia(i));
        }
    }
    
    
    
    /*
    vetorVisitadosAEstrela:
        -1 -> nao visitado
         0 -> calculado funcao avaliadora
         1 -> visitado    
    
    */
    public static int[] vetorVisitadosAEstrela() { 
        int visitados[] = new int[qtdEstados];
        
        for(int i = 0; i < qtdEstados; i++)
            visitados[i] = -1;
        visitados[estadoInicial] = 0;
        
        estado[estadoInicial].setCustoChegada(0);
        return visitados;
    }
    public static void main(String[] args) {        
        
        //    Criaçao dos estados iniciais ou finais).         
        reiniciarPinos();
        setPinos();        
        //      Fim dos estados inicial ou finais.
        
        int estados[] = new int[qtdDiscos];
        int k = qtdDiscos - 1;
        for(int i = 0; i < qtdDiscos; i++) {
            estados[i] = k;
            k--;
        }                    
        
        possibilidades1 = possibilidadesUmPino(estados, possibilidades1);
        possibilidades2 = possibilidadesUmPino(estados, possibilidades1);
        possibilidades3 = possibilidadesUmPino(estados, possibilidades1);
        
        reiniciarPinos();        
        
        mapearPossibilidades();        
        
        reiniciarPinos();
        
        for(int i = 0; i < qtdEstados; i++) {
            if(verificarEstadoInicial(estado[i].getPinos())) {                
                estadoInicial = i;            
            } else if(verificarEstadoFinal(i)) {
                estadoFinal[iEstadoFinal] = i;
                iEstadoFinal++;
            }
                 
            Estados temp;
            
            temp = estado[i].getCopia();
            pino = temp.getPinos();            
            movimento(0,1);
            if(!verificaEstadoIgual(temp, estado[i])) {
                estado[i].setPossivelCaminho(verificaExistenciaEstadoIgual(temp));
            }            
            
            temp = estado[i].getCopia();
            pino = temp.getPinos();
            movimento(0,2);
            if(!verificaEstadoIgual(temp, estado[i])) {
                estado[i].setPossivelCaminho(verificaExistenciaEstadoIgual(temp));
            } 
            
            temp = estado[i].getCopia();
            pino = temp.getPinos();
            movimento(1,0);
            if(!verificaEstadoIgual(temp, estado[i])) {
                estado[i].setPossivelCaminho(verificaExistenciaEstadoIgual(temp));
            } 
            
            temp = estado[i].getCopia();
            pino = temp.getPinos();
            movimento(1,2);
            if(!verificaEstadoIgual(temp, estado[i])) {
                estado[i].setPossivelCaminho(verificaExistenciaEstadoIgual(temp));
            } 
            
            temp = estado[i].getCopia();
            pino = temp.getPinos();
            movimento(2,0);
            if(!verificaEstadoIgual(temp, estado[i])) {
                estado[i].setPossivelCaminho(verificaExistenciaEstadoIgual(temp));
            } 
            
            temp = estado[i].getCopia();
            pino = temp.getPinos();
            movimento(2,1);
            if(!verificaEstadoIgual(temp, estado[i])) {
                estado[i].setPossivelCaminho(verificaExistenciaEstadoIgual(temp));
            }             
        }
        
        
        criarMatrizAdjacencia();
        //printMatrizAdjacencia();
        setarDistanciasEstadosFinais();
        setarHeuristicas();
        printValoresHeuristicos();
        
        /*  BUSCAS        */
        copiarMatrizAdjacencia();        
        zerarColunaMatrizAdjacenciaCopia(estadoInicial);
        tempoInicial = System.currentTimeMillis();
        
        if(tipoBusca == 1)
            buscaProfundidade(estadoInicial);
        else if(tipoBusca == 2)
            buscaLargura(estadoInicial);
        else if(tipoBusca == 3)
            buscaGulosa(estadoInicial);        
        else if(tipoBusca == 4)
            buscaAEstrela(estadoInicial, vetorVisitadosAEstrela());
        /*  FIM BUSCAS    */
    }    
}