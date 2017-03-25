/**
 * Componente Curricular: Módulo Integrado de Programação
 * Autor: Valmir Vinicius
 * Autor: Iago Almeida
 * Data:  14/05/2016
 *
 * Declaro que este código foi elaborado por mim de forma individual e
 * não contém nenhum trecho de código de outro colega ou de outro autor, 
 * tais como provindos de livros e apostilas, e páginas ou documentos 
 * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
 * uma citação para o  não a minha está destacado com  autor e a fonte do
 * código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação. Alguns trechos do código podem coincidir com de outros
 * colegas pois estes foram discutidos em sessões tutorias.
 */
package br.uefs.ecomp.ForteSeguro.util;

/**
 * Classe responsável por definir uma aresta de um grafo ponderado.
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class Aresta {
    
    /* Extremidades da aresta. */
    private Vertice[] extremidades;

    /* Peso da aresta. */
    private int peso;
    
    /**
     * Obtém uma nova instância de aresta.
     * @param extremidade1 extremidade da aresta
     * @param extremidade2 extremidade da aresta
     * @param peso peso da aresta
     */
    public Aresta(Vertice extremidade1, Vertice extremidade2, int peso) {
        this.extremidades = new Vertice[2]; //obtém uma instância de vetor de vértices
        
        /* Adiciona os vértices das extremidades no vetor de vértices. */
        extremidades[0] = extremidade1;
        extremidades[1] = extremidade2;
        
        this.peso = peso; //atribui o peso recebido ao atributo "peso"
    }

    /**
     * Obtém os vértices das extremidades da aresta.
     * @return Array contendo os vértices das extremidades da aresta
     */
    public Vertice[] getExtremidades() {
        return extremidades; //retorna o atributo "extremidades"
    }

    /**
     * Obtém o peso da aresta.
     * @return peso da aresta
     */
    public int getPeso() {
        return peso; //retorna o atributo "peso"
    }
    
}
