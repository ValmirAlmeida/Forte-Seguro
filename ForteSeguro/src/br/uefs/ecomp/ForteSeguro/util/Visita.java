/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uefs.ecomp.ForteSeguro.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma visita realizada durante a execução do algoritmo DijkstraForte.
 * 
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class Visita implements Comparable {
    /* Destino da visita.*/
    private Vertice destino;
    
    /* Custo de realização da visita.*/
    private int custo;

    /* Lista de vias da visita. */
    private List<Vertice> vias;
    
    /**
     * Obtém uma nova instância de Visita.
     * @param destino destino da visita
     * @param custo custo da visita
     * @param via primeira via da visita
     */
    public Visita(Vertice destino, int custo, Vertice via) {
        this.destino = destino; //atribui o destino recebido ao atributo "destino"
        this.custo = custo; //atribui o custo recebido ao atributo "custo"
        vias = new ArrayList<>(); //obtém uma instância de ArrayList
        vias.add(via); //adiciona a via na lista
    }
    
    /**
     * Retorna o destino da visita.
     * @return destino da visita
     */
    public Vertice getDestino() {
        return destino; //retorna o atributo "visita"
    }

    /**
     * Retorna o custo da visita.
     * @return custo da visita
     */
    public int getCusto() {
        return custo;  //retorna o atributo "custo"
    }

    /**
     * Configura o custo da visita.
     * @param custo custo da visita
     */
    public void setCusto(int custo) {
        this.custo = custo; //atribui o valor recebido ao atributo "custo"
    }

    /**
     * Retorna a lista de vias da visita.
     * @return lista de vias da visita
     */
    public List<Vertice> getVias() {
        return vias; //retorna o atributo "vias"
    }
    
    /**
     * Adiciona uma nova via na lista de vias.
     * @param via via que será adicionada
     */
    public void adicionarVia(Vertice via) {
        vias.add(via); //adiciona a via recebida na lista de vias
    }

    /**
     * Compara essa visita com uma visita recebida em relação ao custo.
     * @param t referência para visita
     * @return 1, caso o custo dessa visita seja maior; -1, caso o custo da visita recebida seja maior; 0, caso os custos sejam iguais
     */
    @Override
    public int compareTo(Object t) {
        Visita comparada = (Visita) t; //realiza um cast na visita recebida
        
        if(comparada.getCusto() > custo) { //caso a visita recebida possua um custo maior que essa visita
            return -1;
        } else if(custo > comparada.getCusto()) { //caso a visita recebida possua um custo menor que essa visita
            return 1;
        } else { //caso os custos das vistas sejam iguais
            return 0;
        }
    }
    
}
