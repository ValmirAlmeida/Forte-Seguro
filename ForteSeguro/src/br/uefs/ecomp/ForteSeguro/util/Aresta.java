/**
 * Componente Curricular: M�dulo Integrado de Programa��o
 * Autor: Valmir Vinicius
 * Autor: Iago Almeida
 * Data:  14/05/2016
 *
 * Declaro que este c�digo foi elaborado por mim de forma individual e
 * n�o cont�m nenhum trecho de c�digo de outro colega ou de outro autor, 
 * tais como provindos de livros e apostilas, e p�ginas ou documentos 
 * eletr�nicos da Internet. Qualquer trecho de c�digo de outra autoria que
 * uma cita��o para o  n�o a minha est� destacado com  autor e a fonte do
 * c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins
 * de avalia��o. Alguns trechos do c�digo podem coincidir com de outros
 * colegas pois estes foram discutidos em sess�es tutorias.
 */
package br.uefs.ecomp.ForteSeguro.util;

/**
 * Classe respons�vel por definir uma aresta de um grafo ponderado.
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class Aresta {
    
    /* Extremidades da aresta. */
    private Vertice[] extremidades;

    /* Peso da aresta. */
    private int peso;
    
    /**
     * Obt�m uma nova inst�ncia de aresta.
     * @param extremidade1 extremidade da aresta
     * @param extremidade2 extremidade da aresta
     * @param peso peso da aresta
     */
    public Aresta(Vertice extremidade1, Vertice extremidade2, int peso) {
        this.extremidades = new Vertice[2]; //obt�m uma inst�ncia de vetor de v�rtices
        
        /* Adiciona os v�rtices das extremidades no vetor de v�rtices. */
        extremidades[0] = extremidade1;
        extremidades[1] = extremidade2;
        
        this.peso = peso; //atribui o peso recebido ao atributo "peso"
    }

    /**
     * Obt�m os v�rtices das extremidades da aresta.
     * @return Array contendo os v�rtices das extremidades da aresta
     */
    public Vertice[] getExtremidades() {
        return extremidades; //retorna o atributo "extremidades"
    }

    /**
     * Obt�m o peso da aresta.
     * @return peso da aresta
     */
    public int getPeso() {
        return peso; //retorna o atributo "peso"
    }
    
}
