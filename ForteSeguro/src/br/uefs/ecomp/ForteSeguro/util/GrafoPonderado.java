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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Classe que representa um grafo ponderado, com m�todos para adi��o e remo��o de v�rtices, al�m de gera��o da matriz de adjac�ncia.
 * 
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class GrafoPonderado {
    
    /* Lista de v�rtices do grafo.*/
    private List<Vertice> vertices;
    
    /**
     * Obt�m uma nova inst�ncia do grafo.
     */
    public GrafoPonderado() {
        vertices = new ArrayList<>(); //obt�m uma nova inst�ncia de ArrayList
    }
    
    /**
     * Obt�m a lista de v�rtices do grafo.
     * @return lista de v�rtices do grafo
     */
    public List<Vertice> getVertices() {
        return vertices; //retorna o atributo "vertices"
    }
    
  
    /**
     * Adiciona um novo v�rtice no grafo.
     * @param conteudo conte�do do v�rtice
     * @return <code>true</code>, caso o v�rtice seja adicionado com sucesso; <code>null</code>, caso contr�rio
     */
    public boolean adicionarVertice(Buscavel conteudo) {
        if(conteudo == null) {
            return false;
        }
        
        Vertice novoVertice = new Vertice(conteudo); //cria uma nova instancia de Vertice
        vertices.add(novoVertice); //adiciona o novo v�rtice na lista 
        
        return true;
    }
    
    /**
     * Remove um v�rtice do grafo.
     * @param conteudo conte�do do v�rtice que se deseja remover
     * @return conte�do do v�rtice removido, caso a remo��o seja bem sucedida; <code>null</code>, caso contr�rio.
     */
    public Buscavel removerVertice(Buscavel conteudo) {
        Vertice verticeRemovido = obterVertice(conteudo); //obt�m o v�rtice que ser� removido
        
        if(verticeRemovido == null) { //caso o v�rtice n�o seja encontrado
            return null;
        }
        
        removerArestasConectadas(verticeRemovido); //remove todas as arestas que possuam o v�rtice removido como alguma das suas extremidades
        vertices.remove(verticeRemovido); //remove o v�rtice

        return verticeRemovido.getConteudo(); //retorna a refer�ncia para o v�rtice removido
    }
    
    /**
     * Realiza a remo��o de todas arestas conectadas a um determinado v�rtice.
     * @param vertice v�rtice cujas arestas ser�o removidas
     */
    private void removerArestasConectadas(Vertice vertice) {
        Iterator<Vertice> iVertices = vertices.iterator(); //obt�m o iterador da lista de v�rtices
        Iterator<Aresta> iArestas; //iterador para as listas de arestas
        Aresta arestaAuxiliar; //refer�ncia auxiliar para� aresta
        List<Aresta> listaArestas; //refer�ncia para lista de arestas
        Vertice verticeAuxiliar; //refer�ncia auxiliar para v�rtice
        
        while(iVertices.hasNext()) { //enquanto h� um pr�ximo v�rtice
            verticeAuxiliar = iVertices.next();
            listaArestas = verticeAuxiliar.getArestas(); //obt�m a lista de arestas do v�rtice
            iArestas = listaArestas.iterator(); //obt�m o iterador da lista de arestas
            
            while(iArestas.hasNext()) { //enquanto h� uma pr�xima aresta
                arestaAuxiliar = iArestas.next(); //obt�m uma aresta
                
                if(arestaAuxiliar.getExtremidades()[0].equals(vertice) || arestaAuxiliar.getExtremidades()[1].equals(vertice)) { //caso alguma das extremidades da aresta seja o v�rtice desejado
                    break;
                }
            }
            
            removerAresta(verticeAuxiliar.getConteudo(), vertice.getConteudo()); //remove a aresta
        }
    }
    
    /**
     * Remove uma aresta do grafo.
     * @param conteudoExtremidade conte�do de uma das extremidades da aresta que ser� removida
     * @param conteudoExtremidade2 conte�do de uma das extremidades da aresta que ser� removida
     * @return -1, caso algum dos conte�dos informados seja nulo; 0, caso a aresta n�o exista; 1, caso a remo��o seja bem sucedida
     */
    public int removerAresta(Buscavel conteudoExtremidade, Buscavel conteudoExtremidade2) {
        Vertice verticeExtremidade = obterVertice(conteudoExtremidade), verticeExtremidade2 = obterVertice(conteudoExtremidade2); //obt�m as extremidades da aresta
        
        if(verticeExtremidade == null || verticeExtremidade2 == null) { //caso algum dos v�rtices obtido seja nulo
            return -1;
        }
        
        Aresta arestaRemovida = obterAresta(conteudoExtremidade, conteudoExtremidade2); //obt�m a aresta que ser� removida
        
        if(arestaRemovida == null) { //caso a aresta n�o seja encontrada
            return 0; 
        }
       
        /* Remove a aresta que ser� removida da lista de aresta de ambos os v�rtices. */
        verticeExtremidade.getArestas().remove(arestaRemovida);
        verticeExtremidade2.getArestas().remove(arestaRemovida);
        
        return 1; //retorna a aresta removida
    }
    /**
     * Adiciona uma nova aresta no grafo.
     * @param conteudoExtremidade conte�do de uma das extremidades da aresta
     * @param conteudoExtremidade2 conte�do de uma das extremidades da aresta
     * @param peso peso da aresta
     * @return <code>true</code>, caso a aresta seja adicionada com sucesso; <code>false</code>, caso contr�rio
     */
    public boolean adicionarAresta(Buscavel conteudoExtremidade, Buscavel conteudoExtremidade2, int peso) {
        Vertice extremidade = obterVertice(conteudoExtremidade), extremidade2 = obterVertice(conteudoExtremidade2);
        Aresta arestaAuxiliar = obterAresta(conteudoExtremidade, conteudoExtremidade2);
        
        if(arestaAuxiliar != null || extremidade == null || extremidade2 == null) { //caso a aresta j� exista ou algum dos v�rtices n�o existam
            return false;
        }

        Aresta aresta = new Aresta(extremidade, extremidade2, peso);
        
        extremidade.adicionarAresta(aresta);
        extremidade2.adicionarAresta(aresta);
        
        return true; //caso a aresta seja adicionada com sucesso
    }
    
    /**
     * Obt�m um v�rtice do grafo.
     * @param conteudo conte�do do v�rtice que seja deseja obter
     * @return refer�ncia para um v�rtice, caso exista; <code>null</code>, caso contr�rio
     */
    public Vertice obterVertice(Buscavel conteudo) {
        Iterator<Vertice> iterador = vertices.iterator();  //obt�m o iterador da lista de v�rtices
        Vertice selecionado; //refer�ncia para v�rtice
        
        
        if(conteudo == null) { //caso o conte�do informado seja nulo
            return null;
        }
        
        while(iterador.hasNext()) { //enquanto houver um pr�ximo v�rtice
            selecionado = iterador.next(); //obt�m o b�rtice
            if(selecionado.getConteudo().isIgual(conteudo)) { //caso seja igual ao procurado
                return selecionado; //retorna o v�rtice procurado
            }
        }
        
        return null; //retorna refer�ncia nula caso o v�rtice n�o exista
    }
    
    /**
     * Obt�m uma aresta do grafo.
     * @param extremidade conte�do de uma das extremidades da aresta
     * @param extremidade2 conte�do de uma das extremidades da aresta
     * @return aresta do grafo, caso exista; <code>null</code>, caso contr�rio
     */
    public Aresta obterAresta(Buscavel extremidade, Buscavel extremidade2) {
        if(extremidade == null || extremidade2 == null) { //caso alguma das refer�ncias recebidas forem nulas
            return null;
        }
        
        Vertice verticeExtremidade = obterVertice(extremidade), verticeExtremidade2 = obterVertice(extremidade2);
        
        if(verticeExtremidade == null || verticeExtremidade2 == null) { //caso algum dos v�rtices n�o seja encontrado
            return null;
        }
        
        if(verticeExtremidade == verticeExtremidade2) { //caso os v�rtices sejam os mesmos
            return null;
        }        
        
        List<Aresta> listaArestas = verticeExtremidade.getArestas(); //obt�m a lista de arestas de uma das extremidades recebidas
        Iterator<Aresta> iterador = listaArestas.iterator(); //obt�m o iterador da lista
        Aresta arestaAuxiliar;
        
        while(iterador.hasNext()) { //enquanto h� uma pr�xima aresta
            arestaAuxiliar = iterador.next(); //obt�m a pr�xima aresta
            
            if(arestaAuxiliar.getExtremidades()[0].equals(verticeExtremidade2) || arestaAuxiliar.getExtremidades()[1].equals(verticeExtremidade2)) { //caso alguma das extremidades seja o outro v�rtice recebido
                return arestaAuxiliar; //retorna a aresta
            }
        }
        
        return null;
    }
    
    /**
     * Gera a matriz de adjac�ncia que corresponde ao grafo.
     * @return matriz de adjac�ncia do grafo
     */
    public Object[][] obterMatriz() {
        Object[][] matrizGrafo = new Object[vertices.size()+1][vertices.size()+1]; //obt�m uma inst�ncia de matriz
        Vertice obtido; //refer�ncia para v�rtice obtido da lista
        Vertice verticeLinha, verticeColuna;
        
        
        /* Preenche a primeira coluna e a primeira linha com os v�rtices. */
        for(int i = 1; i<=vertices.size(); i++) {
            obtido = vertices.get(i-1); //obt�m um v�rtice da lista
            
            /* Posiciona cada v�rtice na primeira linha e coluna da matriz. */
            matrizGrafo[i][0] = obtido;
            matrizGrafo[0][i] = obtido;
        }
        
        for(int linha = 1; linha<=vertices.size(); linha++) { //percorre as linhas da matriz
            verticeLinha = vertices.get(linha-1); //obt�m o v�rtice que corresponde a linha atual
        
            for(int coluna = 1; coluna<=vertices.size(); coluna++) { //percore as colunas da matriz
                verticeColuna = vertices.get(coluna-1); //obt�m o v�rtice que corresponde � coluna atual
                
                int pesoConexao = verticeColuna.estaConectado(verticeLinha); //obt�m o peso da conex�o entre o v�rtice da coluna e o v�rtice da linha, se ela existir
            
                if(pesoConexao != -1) { //caso o peso obtido seja diferente de -1, ou seja: a conex�o existe
                     matrizGrafo[linha][coluna] = pesoConexao; //preenche a c�lula da matriz com o peso
                }
            }   
        }
        
        return matrizGrafo; //retorna a matriz criada
    }
}
