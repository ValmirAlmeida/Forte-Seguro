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
package br.uefs.ecomp.ForteSeguro.view;

import br.uefs.ecomp.ForteSeguro.model.Ponto;
import br.uefs.ecomp.ForteSeguro.util.Vertice;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe responsável por gerenciar alguns processos que envolvem o manejamento de vértices e arestas visuais
 * exibidos nsa tela principal.
 * 
 * @author Iago Almeida
 * @author Valmir Vinicius
 */
public class GerenciadorTela {
    
    /* Lista de vértices visuais que estão sendo exibidos na tela. */
    private List<VerticeVisual> listaVerticesVisuais;
    
    /* Lista de arestas visuais que estão sendo exibidos na tela. */
    private List<ArestaVisual> listaArestasVisuais;

    /**
     * Obtém uma nova instância do gerenciador de tela.
     */
    public GerenciadorTela() {
        /* Obtém instâncias de ArrayList. */
        listaVerticesVisuais = new ArrayList<>(); 
        listaArestasVisuais = new ArrayList<>();
    }
    
    /**
     * Obtém a lista de vértices visuais.
     * @return lista de vértices visuais
     */
    public List<VerticeVisual> getListaVerticesVisuais() {
        return listaVerticesVisuais; //retorna o atributo "listaVerticesVisuais"
    }

    /**
     * Obtém a lista de arestas visuais.
     * @return lista de arestas visuais
     */
    public List<ArestaVisual> getListaArestasVisuais() {
        return listaArestasVisuais; //retorna o atributo "listaArestasVisuais"
    }

    /**
     * Cadastra um novo vértice visual.
     * @param novoVertice vértice visual que será cadastrado
     */
    public void adicionarVertice(VerticeVisual novoVertice) {
        listaVerticesVisuais.add(novoVertice); //adiciona o novo vértice visual na lista
    }

    /**
     * Cadastra uma nova aresta visual.
     * @param novaAresta aresta visual que será cadastrada
     */
    public void adicionarAresta(ArestaVisual novaAresta) {
        listaArestasVisuais.add(novaAresta); //adiciona a nova aresta visual na lista
    }

    /**
     * Verifica se existe um vértice ocupando uma dada região da tela.
     * @param coordenadaX coordenada X da região da tela
     * @param coordenadaY coordenada Y da região da tela
     * @return <code>true</code>, caso haja um vértice em uma dada região da tela; <code>false</code>, caso contrário
     */
    public boolean isVerticeNaTela(int coordenadaX, int coordenadaY) {
        if (listaVerticesVisuais.isEmpty()) {//caso a lista esteja vazia
            return false;
        }
        
        Iterator<VerticeVisual> iVertices = listaVerticesVisuais.iterator(); //obtém o iterador da lista de vértices visuais
        VerticeVisual verticeVisual; //referência auxiliar para vértice visual
        
        while(iVertices.hasNext()) { //verifica se há um próximo vértice visual
            verticeVisual = iVertices.next(); //obtém um vértice visual
            
            /* Verifica se o vértice obtido está na região da tela correspondente as coordenadas recebidas. O valor 17 é utilizado
            para evitar que dois pontos fiquem extremamente juntos na tela.
            */
            if ((verticeVisual.getPosicaoX() + 17 > coordenadaX && verticeVisual.getPosicaoX() - 17 < coordenadaX) && (verticeVisual.getPosicaoY() + 17 > coordenadaY && verticeVisual.getPosicaoY() - 17 < coordenadaY)) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Obtém um vértice visual que está ocupando uma dada região da tela.
     * @param coordenadaX coordenada X da região da tela
     * @param coordenadaY coordenada Y da região da tela
     * @return referência de um vértice visual, caso ele exista; <code>null</code>, caso contrário
     */
    public VerticeVisual obterVertice(int coordenadaX, int coordenadaY) {
        Iterator<VerticeVisual> iVertices = listaVerticesVisuais.iterator(); //obtém o iterador da lista de vértices visuais
        VerticeVisual verticeVisual; //referência auxiliar para vértice visual
        
        while(iVertices.hasNext()) { //verifica se há um próximo vértice visual
            verticeVisual = iVertices.next(); //obtém um vértice visual
            
            /* Verifica se o vértice obtido está na região da tela correspondente as coordenadas recebidas. O valor 17 é utilizado
            para evitar que dois pontos fiquem extremamente juntos na tela.
            */
            if ((verticeVisual.getPosicaoX() + 17 > coordenadaX && verticeVisual.getPosicaoX() - 17 < coordenadaX) && (verticeVisual.getPosicaoY() + 17 > coordenadaY && verticeVisual.getPosicaoY() - 17 < coordenadaY)) {
                return verticeVisual;
            }
        }        
        
        return null;
    }
    
    /**
     * Obtém uma aresta visual que está na tela.
     * @param coordenadaX1 coordenada X de uma das extremidades da aresta
     * @param coordenadaY1 coordenada Y de uma das extremidades da aresta
     * @param coordenadaX2 coordenada X de uma das extremidades da aresta
     * @param coordenadaY2 coordenada Y de uma das extremidades da aresta
     * @return referência de uma aresta visual, caso ela exista; <code>null</code>, caso contrário
     */
    public ArestaVisual obterAresta(int coordenadaX1, int coordenadaY1, int coordenadaX2, int coordenadaY2) {
        Iterator<ArestaVisual> iArestas = listaArestasVisuais.iterator(); //obtém o iterador da lista de arestas visuais
        ArestaVisual arestaAuxiliar; //referência auxiliar para ArestaVisual
        
        while(iArestas.hasNext()) { //enquanto há uma próxima aresta
            arestaAuxiliar = iArestas.next(); //obtém a aresta
            
            /* Verifica se as extremidades da aresta condizem com as extremidades recebidas. */
            if(((arestaAuxiliar.getExtremidade1().getPosicaoX() == coordenadaX1 && arestaAuxiliar.getExtremidade1().getPosicaoY() == coordenadaY1) || (arestaAuxiliar.getExtremidade1().getPosicaoX() == coordenadaX2 && arestaAuxiliar.getExtremidade1().getPosicaoY() == coordenadaY2)) || ((arestaAuxiliar.getExtremidade2().getPosicaoX() == coordenadaX1 && arestaAuxiliar.getExtremidade2().getPosicaoY() == coordenadaY1) || (arestaAuxiliar.getExtremidade2().getPosicaoX() == coordenadaX2 && arestaAuxiliar.getExtremidade2().getPosicaoY() == coordenadaY2))) {
                return arestaAuxiliar;
            }
        }
        
        return null;
    }
    

    /**
     * Remove um vértice visual da lista de vértices visuais da tela.
     * @param nomeVertice nome que caracteriza o vértice
     */
    public void excluirVertice(String nomeVertice) {
        Iterator<ArestaVisual> iAresta = listaArestasVisuais.iterator(); //obtém o iterador da lista de arestas visuais
        Iterator<VerticeVisual> iVertice = listaVerticesVisuais.iterator(); //obtém o iterador da lista de arestas visuais
        ArestaVisual arestaAuxiliar; //referência auxiliar para aresta visual
        VerticeVisual verticeAuxiliar; //referência auxiliar para vértice visual
        ArrayList<ArestaVisual> arestasVisuaisRemovidas = new ArrayList<>(); //lista de arestas adjacentes ao vértice que também serão removidas
        
        while (iAresta.hasNext()) { //percorre a lista de arestas
            arestaAuxiliar = iAresta.next(); //obtém uma aresta
            
            /* Caso alguma das extremidades da aresta seja o vértice que se deseja remover. */
            if (arestaAuxiliar.getExtremidade1().getNome().equals(nomeVertice) || arestaAuxiliar.getExtremidade2().getNome().equals(nomeVertice)) {
                arestasVisuaisRemovidas.add(arestaAuxiliar); //adiciona na lista de arestas que serão removidas
            }
        }

        iAresta = arestasVisuaisRemovidas.iterator(); //obtém o iterador da lista de arestas visuais que serão removidas
        
        while(iAresta.hasNext()) { //percorre a lista de arestas que serão removidas
            listaArestasVisuais.remove(iAresta.next()); //obtém a próxima aresta e realiza a remoção
        }
        
        /* Não foi possível realizar a remoção no primeiro laço de busca das arestas que seriam removidas, pois o 
        Java não permite realizar remoções em uma lista enquanto ela está sendo iterada.
        */        
        
        int indexRemovido = 0; //índice do vértice que será removido

        while (iVertice.hasNext()) { //percorre a lista de vértices visuais
            verticeAuxiliar = iVertice.next(); //obtém um vértice
            
            if (verticeAuxiliar.getNome().equals(nomeVertice)) { //caso o nome do vértice obtido seja igual ao nome do vértice procurado
                indexRemovido = listaVerticesVisuais.indexOf(verticeAuxiliar); //obtém o índice do vértice que será removido
                break;
            }
        }

        listaVerticesVisuais.remove(indexRemovido); //realiza a remoção do vértice
        
    }

    /**
     * Realiza a remoção de uma aresta visual cadastrada na tela.
     * @param nomeExtremidade1 nome do vértice que ocupa uma das extremidades da aresta
     * @param nomeExtremidade2 nome do vértice que ocupa uma das extremidades da aresta
     */
    public void excluirAresta(String nomeExtremidade1, String nomeExtremidade2) {
        int indexAresta = 0; //índice da aresta que será removida
        Iterator<ArestaVisual> iAresta = listaArestasVisuais.iterator(); //obtém o iterador da lista de arestas visuais
        ArestaVisual arestaVisual; //referência auxiliar para aresta visual
        
        while(iAresta.hasNext()) { //enquanto há uma próxima aresta
            arestaVisual = iAresta.next(); //obtém uma próxima aresta
            
            /* Verifica se os nomes dos vértices que ocupam as extremidades da aresta correspondem aos procurados. */
            if ((arestaVisual.getExtremidade1().getNome().equals(nomeExtremidade1) && arestaVisual.getExtremidade2().getNome().equals(nomeExtremidade2)) || (arestaVisual.getExtremidade1().getNome().equals(nomeExtremidade2) && arestaVisual.getExtremidade2().getNome().equals(nomeExtremidade1))) {
                indexAresta = listaArestasVisuais.indexOf(arestaVisual); //obtém o índice da aresta
                break;
            }
        }

        listaArestasVisuais.remove(indexAresta); //realiza a remoção da aresta
    }

    /**
     * Desenha um caminho mínimo na tela.
     * @param listaVertices lista de vértices que correspondem ao caminho mínimo
     * @param graphics gráfico do JFrame
     */
    public void desenharCaminhoMinimoUnico(List<Vertice> listaVertices, Graphics graphics) {
        int cont = 0; //contador auxiliar
        VerticeVisual verticeVisuaAuxiliar = new VerticeVisual(), verticeVisuaAtual; //referências auxiliares para vértices visuais
        Vertice verticeObtido; //vértice que faz parte do caminho mínimo
        Iterator iteradorListaVertices; //iteraodr para lista de vértices
        Iterator iteradorSubListas = listaVertices.iterator();// iterador para a lista de listas
        
        while (iteradorSubListas.hasNext()) { //percorre a lista principal (Lista de listas)
            verticeObtido = (Vertice) iteradorSubListas.next(); //obtém um próximo vértice
            iteradorListaVertices = listaVerticesVisuais.iterator(); //obtém o iteraodr da lista de vértices visuais
        
            while (iteradorListaVertices.hasNext()) {//percorre a lista de vertices 
                verticeVisuaAtual = (VerticeVisual) iteradorListaVertices.next();
               
                /* Verifica se cada elemento da lista de vertice é o correspodente a lista de pontos. */
                if (((Ponto) verticeObtido.getConteudo()).getNome().equals(verticeVisuaAtual.getNome())) {
                    
                    //desenha um circulo vermelho no ponto onde a trajetoria passa
                    Desenhador.pintarCirculoSelecionado(graphics, verticeVisuaAtual.getPosicaoX(), verticeVisuaAtual.getPosicaoY(), java.awt.Color.RED);
                
                    if (cont < 1) { //caso seja o primeiro ponto em que passa
                        verticeVisuaAuxiliar.setPosicaoX(verticeVisuaAtual.getPosicaoX());
                        verticeVisuaAuxiliar.setPosicaoY(verticeVisuaAtual.getPosicaoY());
                        cont++;
                    } else { // caso ja tenha passado pelo ponto que se liga a esse
                        Desenhador.pintarLinhaCaminhoMinimo(graphics, verticeVisuaAuxiliar.getPosicaoX(), verticeVisuaAuxiliar.getPosicaoY(), verticeVisuaAtual.getPosicaoX(), verticeVisuaAtual.getPosicaoY());
                        verticeVisuaAuxiliar.setPosicaoX(verticeVisuaAtual.getPosicaoX());
                        verticeVisuaAuxiliar.setPosicaoY(verticeVisuaAtual.getPosicaoY());
                    }
                    
                    break; // interrompe a busca pelo correspondente na interface, pois ja encontrou o mesmo
                }
            }
        }
    }


}
