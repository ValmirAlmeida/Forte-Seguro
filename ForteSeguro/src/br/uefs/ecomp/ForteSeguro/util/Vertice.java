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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe respons�vel por definir um v�rtice de um grafo.
 * 
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class Vertice {
    /* Conteudo do v�rtice.*/
    private Buscavel conteudo;
    
    /* Lista de arestas com as quais o v�rtice se conecta.*/
    private List<Aresta> arestas;
   
    /**
     * Obt�m uma nova inst�ncia de v�rtice.
     * @param conteudo conteudo do v�rtice
     */
    public Vertice(Buscavel conteudo) {
        this.conteudo = conteudo; //atribui a refer�ncia de conteudo recebida � vari�vel "conteudo"
        arestas = new ArrayList<>(); //obt�m uma inst�ncia de lista para alocar arestas
    }
    
    /**
     * Obt�m o conte�do do v�rtice.
     * @return conteudo do v�rtice
     */
    public Buscavel getConteudo() {
        return conteudo; //retorna o atributo "conteudo"
    }
    
    /**
     * Adiciona uma aresta que se conecta ao v�rtice.
     * @param nova nova aresta que se conecta ao v�rtice
     */
    public void adicionarAresta(Aresta nova) {
        arestas.add(nova); //adiciona a nova aresta na lista de arestas
    }
    
    /**
     * Obt�m a lista de arestas que se conectam com o v�rtice.
     * @return lista de arestas que se conectam com esse v�rtice
     */
    public List<Aresta> getArestas() {
        return arestas; //retorna o atributo "arestas"
    }
    
    /**
     * Verifica se esse v�rtice est� conectado a um dado v�rtice e retorna o peso da conex�o, caso ela exista.
     * @param vertice v�rtice para o qual se deseja verificar se a conex�o existe
     * @return peso da conex�o, caso a conex�o exista; -1, caso contr�rio
     */
    public int estaConectado(Vertice vertice) {
        Iterator<Aresta> iterador = arestas.iterator(); //obt�m o iterador para a lista de arestas
        Aresta auxiliar; //refer�ncia auxiliar para aresta
        
        while(iterador.hasNext()) { //enquanto houver uma pr�xima aresta
            auxiliar = iterador.next(); //obt�m a aresta
            Vertice[] extremidades = auxiliar.getExtremidades(); //obt�m as extremidades da aresta
            
            for(Vertice extremo : extremidades) { //percorre as duas extremidades da aresta
                if(!extremo.equals(this) && extremo.equals(vertice)) { //caso a extremidade seja o v�ritce procurado
                    return auxiliar.getPeso();
                }
            }
        }
        
        return -1;
    }
}
