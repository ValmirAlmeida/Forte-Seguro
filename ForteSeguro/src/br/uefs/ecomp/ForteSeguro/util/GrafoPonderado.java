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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Classe que representa um grafo ponderado, com métodos para adição e remoção de vértices, além de geração da matriz de adjacência.
 * 
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class GrafoPonderado {
    
    /* Lista de vértices do grafo.*/
    private List<Vertice> vertices;
    
    /**
     * Obtém uma nova instância do grafo.
     */
    public GrafoPonderado() {
        vertices = new ArrayList<>(); //obtém uma nova instância de ArrayList
    }
    
    /**
     * Obtém a lista de vértices do grafo.
     * @return lista de vértices do grafo
     */
    public List<Vertice> getVertices() {
        return vertices; //retorna o atributo "vertices"
    }
    
  
    /**
     * Adiciona um novo vértice no grafo.
     * @param conteudo conteúdo do vértice
     * @return <code>true</code>, caso o vértice seja adicionado com sucesso; <code>null</code>, caso contrário
     */
    public boolean adicionarVertice(Buscavel conteudo) {
        if(conteudo == null) {
            return false;
        }
        
        Vertice novoVertice = new Vertice(conteudo); //cria uma nova instancia de Vertice
        vertices.add(novoVertice); //adiciona o novo vértice na lista 
        
        return true;
    }
    
    /**
     * Remove um vértice do grafo.
     * @param conteudo conteúdo do vértice que se deseja remover
     * @return conteúdo do vértice removido, caso a remoção seja bem sucedida; <code>null</code>, caso contrário.
     */
    public Buscavel removerVertice(Buscavel conteudo) {
        Vertice verticeRemovido = obterVertice(conteudo); //obtém o vértice que será removido
        
        if(verticeRemovido == null) { //caso o vértice não seja encontrado
            return null;
        }
        
        removerArestasConectadas(verticeRemovido); //remove todas as arestas que possuam o vértice removido como alguma das suas extremidades
        vertices.remove(verticeRemovido); //remove o vértice

        return verticeRemovido.getConteudo(); //retorna a referência para o vértice removido
    }
    
    /**
     * Realiza a remoção de todas arestas conectadas a um determinado vértice.
     * @param vertice vértice cujas arestas serão removidas
     */
    private void removerArestasConectadas(Vertice vertice) {
        Iterator<Vertice> iVertices = vertices.iterator(); //obtém o iterador da lista de vértices
        Iterator<Aresta> iArestas; //iterador para as listas de arestas
        Aresta arestaAuxiliar; //referência auxiliar para´ aresta
        List<Aresta> listaArestas; //referência para lista de arestas
        Vertice verticeAuxiliar; //referência auxiliar para vértice
        
        while(iVertices.hasNext()) { //enquanto há um próximo vértice
            verticeAuxiliar = iVertices.next();
            listaArestas = verticeAuxiliar.getArestas(); //obtém a lista de arestas do vértice
            iArestas = listaArestas.iterator(); //obtém o iterador da lista de arestas
            
            while(iArestas.hasNext()) { //enquanto há uma próxima aresta
                arestaAuxiliar = iArestas.next(); //obtém uma aresta
                
                if(arestaAuxiliar.getExtremidades()[0].equals(vertice) || arestaAuxiliar.getExtremidades()[1].equals(vertice)) { //caso alguma das extremidades da aresta seja o vértice desejado
                    break;
                }
            }
            
            removerAresta(verticeAuxiliar.getConteudo(), vertice.getConteudo()); //remove a aresta
        }
    }
    
    /**
     * Remove uma aresta do grafo.
     * @param conteudoExtremidade conteúdo de uma das extremidades da aresta que será removida
     * @param conteudoExtremidade2 conteúdo de uma das extremidades da aresta que será removida
     * @return -1, caso algum dos conteúdos informados seja nulo; 0, caso a aresta não exista; 1, caso a remoção seja bem sucedida
     */
    public int removerAresta(Buscavel conteudoExtremidade, Buscavel conteudoExtremidade2) {
        Vertice verticeExtremidade = obterVertice(conteudoExtremidade), verticeExtremidade2 = obterVertice(conteudoExtremidade2); //obtém as extremidades da aresta
        
        if(verticeExtremidade == null || verticeExtremidade2 == null) { //caso algum dos vértices obtido seja nulo
            return -1;
        }
        
        Aresta arestaRemovida = obterAresta(conteudoExtremidade, conteudoExtremidade2); //obtém a aresta que será removida
        
        if(arestaRemovida == null) { //caso a aresta não seja encontrada
            return 0; 
        }
       
        /* Remove a aresta que será removida da lista de aresta de ambos os vértices. */
        verticeExtremidade.getArestas().remove(arestaRemovida);
        verticeExtremidade2.getArestas().remove(arestaRemovida);
        
        return 1; //retorna a aresta removida
    }
    /**
     * Adiciona uma nova aresta no grafo.
     * @param conteudoExtremidade conteúdo de uma das extremidades da aresta
     * @param conteudoExtremidade2 conteúdo de uma das extremidades da aresta
     * @param peso peso da aresta
     * @return <code>true</code>, caso a aresta seja adicionada com sucesso; <code>false</code>, caso contrário
     */
    public boolean adicionarAresta(Buscavel conteudoExtremidade, Buscavel conteudoExtremidade2, int peso) {
        Vertice extremidade = obterVertice(conteudoExtremidade), extremidade2 = obterVertice(conteudoExtremidade2);
        Aresta arestaAuxiliar = obterAresta(conteudoExtremidade, conteudoExtremidade2);
        
        if(arestaAuxiliar != null || extremidade == null || extremidade2 == null) { //caso a aresta já exista ou algum dos vértices não existam
            return false;
        }

        Aresta aresta = new Aresta(extremidade, extremidade2, peso);
        
        extremidade.adicionarAresta(aresta);
        extremidade2.adicionarAresta(aresta);
        
        return true; //caso a aresta seja adicionada com sucesso
    }
    
    /**
     * Obtém um vértice do grafo.
     * @param conteudo conteúdo do vértice que seja deseja obter
     * @return referência para um vértice, caso exista; <code>null</code>, caso contrário
     */
    public Vertice obterVertice(Buscavel conteudo) {
        Iterator<Vertice> iterador = vertices.iterator();  //obtém o iterador da lista de vértices
        Vertice selecionado; //referência para vértice
        
        
        if(conteudo == null) { //caso o conteúdo informado seja nulo
            return null;
        }
        
        while(iterador.hasNext()) { //enquanto houver um próximo vértice
            selecionado = iterador.next(); //obtém o bértice
            if(selecionado.getConteudo().isIgual(conteudo)) { //caso seja igual ao procurado
                return selecionado; //retorna o vértice procurado
            }
        }
        
        return null; //retorna referência nula caso o vértice não exista
    }
    
    /**
     * Obtém uma aresta do grafo.
     * @param extremidade conteúdo de uma das extremidades da aresta
     * @param extremidade2 conteúdo de uma das extremidades da aresta
     * @return aresta do grafo, caso exista; <code>null</code>, caso contrário
     */
    public Aresta obterAresta(Buscavel extremidade, Buscavel extremidade2) {
        if(extremidade == null || extremidade2 == null) { //caso alguma das referências recebidas forem nulas
            return null;
        }
        
        Vertice verticeExtremidade = obterVertice(extremidade), verticeExtremidade2 = obterVertice(extremidade2);
        
        if(verticeExtremidade == null || verticeExtremidade2 == null) { //caso algum dos vértices não seja encontrado
            return null;
        }
        
        if(verticeExtremidade == verticeExtremidade2) { //caso os vértices sejam os mesmos
            return null;
        }        
        
        List<Aresta> listaArestas = verticeExtremidade.getArestas(); //obtém a lista de arestas de uma das extremidades recebidas
        Iterator<Aresta> iterador = listaArestas.iterator(); //obtém o iterador da lista
        Aresta arestaAuxiliar;
        
        while(iterador.hasNext()) { //enquanto há uma próxima aresta
            arestaAuxiliar = iterador.next(); //obtém a próxima aresta
            
            if(arestaAuxiliar.getExtremidades()[0].equals(verticeExtremidade2) || arestaAuxiliar.getExtremidades()[1].equals(verticeExtremidade2)) { //caso alguma das extremidades seja o outro vértice recebido
                return arestaAuxiliar; //retorna a aresta
            }
        }
        
        return null;
    }
    
    /**
     * Gera a matriz de adjacência que corresponde ao grafo.
     * @return matriz de adjacência do grafo
     */
    public Object[][] obterMatriz() {
        Object[][] matrizGrafo = new Object[vertices.size()+1][vertices.size()+1]; //obtém uma instância de matriz
        Vertice obtido; //referência para vértice obtido da lista
        Vertice verticeLinha, verticeColuna;
        
        
        /* Preenche a primeira coluna e a primeira linha com os vértices. */
        for(int i = 1; i<=vertices.size(); i++) {
            obtido = vertices.get(i-1); //obtém um vértice da lista
            
            /* Posiciona cada vértice na primeira linha e coluna da matriz. */
            matrizGrafo[i][0] = obtido;
            matrizGrafo[0][i] = obtido;
        }
        
        for(int linha = 1; linha<=vertices.size(); linha++) { //percorre as linhas da matriz
            verticeLinha = vertices.get(linha-1); //obtém o vértice que corresponde a linha atual
        
            for(int coluna = 1; coluna<=vertices.size(); coluna++) { //percore as colunas da matriz
                verticeColuna = vertices.get(coluna-1); //obtém o vértice que corresponde à coluna atual
                
                int pesoConexao = verticeColuna.estaConectado(verticeLinha); //obtém o peso da conexão entre o vértice da coluna e o vértice da linha, se ela existir
            
                if(pesoConexao != -1) { //caso o peso obtido seja diferente de -1, ou seja: a conexão existe
                     matrizGrafo[linha][coluna] = pesoConexao; //preenche a célula da matriz com o peso
                }
            }   
        }
        
        return matrizGrafo; //retorna a matriz criada
    }
}
