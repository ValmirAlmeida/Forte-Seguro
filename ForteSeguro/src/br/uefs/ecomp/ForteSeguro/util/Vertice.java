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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe responsável por definir um vértice de um grafo.
 * 
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class Vertice {
    /* Conteudo do vértice.*/
    private Buscavel conteudo;
    
    /* Lista de arestas com as quais o vértice se conecta.*/
    private List<Aresta> arestas;
   
    /**
     * Obtém uma nova instância de vértice.
     * @param conteudo conteudo do vértice
     */
    public Vertice(Buscavel conteudo) {
        this.conteudo = conteudo; //atribui a referência de conteudo recebida à variável "conteudo"
        arestas = new ArrayList<>(); //obtém uma instância de lista para alocar arestas
    }
    
    /**
     * Obtém o conteúdo do vértice.
     * @return conteudo do vértice
     */
    public Buscavel getConteudo() {
        return conteudo; //retorna o atributo "conteudo"
    }
    
    /**
     * Adiciona uma aresta que se conecta ao vértice.
     * @param nova nova aresta que se conecta ao vértice
     */
    public void adicionarAresta(Aresta nova) {
        arestas.add(nova); //adiciona a nova aresta na lista de arestas
    }
    
    /**
     * Obtém a lista de arestas que se conectam com o vértice.
     * @return lista de arestas que se conectam com esse vértice
     */
    public List<Aresta> getArestas() {
        return arestas; //retorna o atributo "arestas"
    }
    
    /**
     * Verifica se esse vértice está conectado a um dado vértice e retorna o peso da conexão, caso ela exista.
     * @param vertice vértice para o qual se deseja verificar se a conexão existe
     * @return peso da conexão, caso a conexão exista; -1, caso contrário
     */
    public int estaConectado(Vertice vertice) {
        Iterator<Aresta> iterador = arestas.iterator(); //obtém o iterador para a lista de arestas
        Aresta auxiliar; //referência auxiliar para aresta
        
        while(iterador.hasNext()) { //enquanto houver uma próxima aresta
            auxiliar = iterador.next(); //obtém a aresta
            Vertice[] extremidades = auxiliar.getExtremidades(); //obtém as extremidades da aresta
            
            for(Vertice extremo : extremidades) { //percorre as duas extremidades da aresta
                if(!extremo.equals(this) && extremo.equals(vertice)) { //caso a extremidade seja o véritce procurado
                    return auxiliar.getPeso();
                }
            }
        }
        
        return -1;
    }
}
