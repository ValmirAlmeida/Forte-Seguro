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
package br.uefs.ecomp.ForteSeguro.view;

import br.uefs.ecomp.ForteSeguro.model.Ponto;
import br.uefs.ecomp.ForteSeguro.util.Vertice;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe respons�vel por gerenciar alguns processos que envolvem o manejamento de v�rtices e arestas visuais
 * exibidos nsa tela principal.
 * 
 * @author Iago Almeida
 * @author Valmir Vinicius
 */
public class GerenciadorTela {
    
    /* Lista de v�rtices visuais que est�o sendo exibidos na tela. */
    private List<VerticeVisual> listaVerticesVisuais;
    
    /* Lista de arestas visuais que est�o sendo exibidos na tela. */
    private List<ArestaVisual> listaArestasVisuais;

    /**
     * Obt�m uma nova inst�ncia do gerenciador de tela.
     */
    public GerenciadorTela() {
        /* Obt�m inst�ncias de ArrayList. */
        listaVerticesVisuais = new ArrayList<>(); 
        listaArestasVisuais = new ArrayList<>();
    }
    
    /**
     * Obt�m a lista de v�rtices visuais.
     * @return lista de v�rtices visuais
     */
    public List<VerticeVisual> getListaVerticesVisuais() {
        return listaVerticesVisuais; //retorna o atributo "listaVerticesVisuais"
    }

    /**
     * Obt�m a lista de arestas visuais.
     * @return lista de arestas visuais
     */
    public List<ArestaVisual> getListaArestasVisuais() {
        return listaArestasVisuais; //retorna o atributo "listaArestasVisuais"
    }

    /**
     * Cadastra um novo v�rtice visual.
     * @param novoVertice v�rtice visual que ser� cadastrado
     */
    public void adicionarVertice(VerticeVisual novoVertice) {
        listaVerticesVisuais.add(novoVertice); //adiciona o novo v�rtice visual na lista
    }

    /**
     * Cadastra uma nova aresta visual.
     * @param novaAresta aresta visual que ser� cadastrada
     */
    public void adicionarAresta(ArestaVisual novaAresta) {
        listaArestasVisuais.add(novaAresta); //adiciona a nova aresta visual na lista
    }

    /**
     * Verifica se existe um v�rtice ocupando uma dada regi�o da tela.
     * @param coordenadaX coordenada X da regi�o da tela
     * @param coordenadaY coordenada Y da regi�o da tela
     * @return <code>true</code>, caso haja um v�rtice em uma dada regi�o da tela; <code>false</code>, caso contr�rio
     */
    public boolean isVerticeNaTela(int coordenadaX, int coordenadaY) {
        if (listaVerticesVisuais.isEmpty()) {//caso a lista esteja vazia
            return false;
        }
        
        Iterator<VerticeVisual> iVertices = listaVerticesVisuais.iterator(); //obt�m o iterador da lista de v�rtices visuais
        VerticeVisual verticeVisual; //refer�ncia auxiliar para v�rtice visual
        
        while(iVertices.hasNext()) { //verifica se h� um pr�ximo v�rtice visual
            verticeVisual = iVertices.next(); //obt�m um v�rtice visual
            
            /* Verifica se o v�rtice obtido est� na regi�o da tela correspondente as coordenadas recebidas. O valor 17 � utilizado
            para evitar que dois pontos fiquem extremamente juntos na tela.
            */
            if ((verticeVisual.getPosicaoX() + 17 > coordenadaX && verticeVisual.getPosicaoX() - 17 < coordenadaX) && (verticeVisual.getPosicaoY() + 17 > coordenadaY && verticeVisual.getPosicaoY() - 17 < coordenadaY)) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Obt�m um v�rtice visual que est� ocupando uma dada regi�o da tela.
     * @param coordenadaX coordenada X da regi�o da tela
     * @param coordenadaY coordenada Y da regi�o da tela
     * @return refer�ncia de um v�rtice visual, caso ele exista; <code>null</code>, caso contr�rio
     */
    public VerticeVisual obterVertice(int coordenadaX, int coordenadaY) {
        Iterator<VerticeVisual> iVertices = listaVerticesVisuais.iterator(); //obt�m o iterador da lista de v�rtices visuais
        VerticeVisual verticeVisual; //refer�ncia auxiliar para v�rtice visual
        
        while(iVertices.hasNext()) { //verifica se h� um pr�ximo v�rtice visual
            verticeVisual = iVertices.next(); //obt�m um v�rtice visual
            
            /* Verifica se o v�rtice obtido est� na regi�o da tela correspondente as coordenadas recebidas. O valor 17 � utilizado
            para evitar que dois pontos fiquem extremamente juntos na tela.
            */
            if ((verticeVisual.getPosicaoX() + 17 > coordenadaX && verticeVisual.getPosicaoX() - 17 < coordenadaX) && (verticeVisual.getPosicaoY() + 17 > coordenadaY && verticeVisual.getPosicaoY() - 17 < coordenadaY)) {
                return verticeVisual;
            }
        }        
        
        return null;
    }
    
    /**
     * Obt�m uma aresta visual que est� na tela.
     * @param coordenadaX1 coordenada X de uma das extremidades da aresta
     * @param coordenadaY1 coordenada Y de uma das extremidades da aresta
     * @param coordenadaX2 coordenada X de uma das extremidades da aresta
     * @param coordenadaY2 coordenada Y de uma das extremidades da aresta
     * @return refer�ncia de uma aresta visual, caso ela exista; <code>null</code>, caso contr�rio
     */
    public ArestaVisual obterAresta(int coordenadaX1, int coordenadaY1, int coordenadaX2, int coordenadaY2) {
        Iterator<ArestaVisual> iArestas = listaArestasVisuais.iterator(); //obt�m o iterador da lista de arestas visuais
        ArestaVisual arestaAuxiliar; //refer�ncia auxiliar para ArestaVisual
        
        while(iArestas.hasNext()) { //enquanto h� uma pr�xima aresta
            arestaAuxiliar = iArestas.next(); //obt�m a aresta
            
            /* Verifica se as extremidades da aresta condizem com as extremidades recebidas. */
            if(((arestaAuxiliar.getExtremidade1().getPosicaoX() == coordenadaX1 && arestaAuxiliar.getExtremidade1().getPosicaoY() == coordenadaY1) || (arestaAuxiliar.getExtremidade1().getPosicaoX() == coordenadaX2 && arestaAuxiliar.getExtremidade1().getPosicaoY() == coordenadaY2)) || ((arestaAuxiliar.getExtremidade2().getPosicaoX() == coordenadaX1 && arestaAuxiliar.getExtremidade2().getPosicaoY() == coordenadaY1) || (arestaAuxiliar.getExtremidade2().getPosicaoX() == coordenadaX2 && arestaAuxiliar.getExtremidade2().getPosicaoY() == coordenadaY2))) {
                return arestaAuxiliar;
            }
        }
        
        return null;
    }
    

    /**
     * Remove um v�rtice visual da lista de v�rtices visuais da tela.
     * @param nomeVertice nome que caracteriza o v�rtice
     */
    public void excluirVertice(String nomeVertice) {
        Iterator<ArestaVisual> iAresta = listaArestasVisuais.iterator(); //obt�m o iterador da lista de arestas visuais
        Iterator<VerticeVisual> iVertice = listaVerticesVisuais.iterator(); //obt�m o iterador da lista de arestas visuais
        ArestaVisual arestaAuxiliar; //refer�ncia auxiliar para aresta visual
        VerticeVisual verticeAuxiliar; //refer�ncia auxiliar para v�rtice visual
        ArrayList<ArestaVisual> arestasVisuaisRemovidas = new ArrayList<>(); //lista de arestas adjacentes ao v�rtice que tamb�m ser�o removidas
        
        while (iAresta.hasNext()) { //percorre a lista de arestas
            arestaAuxiliar = iAresta.next(); //obt�m uma aresta
            
            /* Caso alguma das extremidades da aresta seja o v�rtice que se deseja remover. */
            if (arestaAuxiliar.getExtremidade1().getNome().equals(nomeVertice) || arestaAuxiliar.getExtremidade2().getNome().equals(nomeVertice)) {
                arestasVisuaisRemovidas.add(arestaAuxiliar); //adiciona na lista de arestas que ser�o removidas
            }
        }

        iAresta = arestasVisuaisRemovidas.iterator(); //obt�m o iterador da lista de arestas visuais que ser�o removidas
        
        while(iAresta.hasNext()) { //percorre a lista de arestas que ser�o removidas
            listaArestasVisuais.remove(iAresta.next()); //obt�m a pr�xima aresta e realiza a remo��o
        }
        
        /* N�o foi poss�vel realizar a remo��o no primeiro la�o de busca das arestas que seriam removidas, pois o 
        Java n�o permite realizar remo��es em uma lista enquanto ela est� sendo iterada.
        */        
        
        int indexRemovido = 0; //�ndice do v�rtice que ser� removido

        while (iVertice.hasNext()) { //percorre a lista de v�rtices visuais
            verticeAuxiliar = iVertice.next(); //obt�m um v�rtice
            
            if (verticeAuxiliar.getNome().equals(nomeVertice)) { //caso o nome do v�rtice obtido seja igual ao nome do v�rtice procurado
                indexRemovido = listaVerticesVisuais.indexOf(verticeAuxiliar); //obt�m o �ndice do v�rtice que ser� removido
                break;
            }
        }

        listaVerticesVisuais.remove(indexRemovido); //realiza a remo��o do v�rtice
        
    }

    /**
     * Realiza a remo��o de uma aresta visual cadastrada na tela.
     * @param nomeExtremidade1 nome do v�rtice que ocupa uma das extremidades da aresta
     * @param nomeExtremidade2 nome do v�rtice que ocupa uma das extremidades da aresta
     */
    public void excluirAresta(String nomeExtremidade1, String nomeExtremidade2) {
        int indexAresta = 0; //�ndice da aresta que ser� removida
        Iterator<ArestaVisual> iAresta = listaArestasVisuais.iterator(); //obt�m o iterador da lista de arestas visuais
        ArestaVisual arestaVisual; //refer�ncia auxiliar para aresta visual
        
        while(iAresta.hasNext()) { //enquanto h� uma pr�xima aresta
            arestaVisual = iAresta.next(); //obt�m uma pr�xima aresta
            
            /* Verifica se os nomes dos v�rtices que ocupam as extremidades da aresta correspondem aos procurados. */
            if ((arestaVisual.getExtremidade1().getNome().equals(nomeExtremidade1) && arestaVisual.getExtremidade2().getNome().equals(nomeExtremidade2)) || (arestaVisual.getExtremidade1().getNome().equals(nomeExtremidade2) && arestaVisual.getExtremidade2().getNome().equals(nomeExtremidade1))) {
                indexAresta = listaArestasVisuais.indexOf(arestaVisual); //obt�m o �ndice da aresta
                break;
            }
        }

        listaArestasVisuais.remove(indexAresta); //realiza a remo��o da aresta
    }

    /**
     * Desenha um caminho m�nimo na tela.
     * @param listaVertices lista de v�rtices que correspondem ao caminho m�nimo
     * @param graphics gr�fico do JFrame
     */
    public void desenharCaminhoMinimoUnico(List<Vertice> listaVertices, Graphics graphics) {
        int cont = 0; //contador auxiliar
        VerticeVisual verticeVisuaAuxiliar = new VerticeVisual(), verticeVisuaAtual; //refer�ncias auxiliares para v�rtices visuais
        Vertice verticeObtido; //v�rtice que faz parte do caminho m�nimo
        Iterator iteradorListaVertices; //iteraodr para lista de v�rtices
        Iterator iteradorSubListas = listaVertices.iterator();// iterador para a lista de listas
        
        while (iteradorSubListas.hasNext()) { //percorre a lista principal (Lista de listas)
            verticeObtido = (Vertice) iteradorSubListas.next(); //obt�m um pr�ximo v�rtice
            iteradorListaVertices = listaVerticesVisuais.iterator(); //obt�m o iteraodr da lista de v�rtices visuais
        
            while (iteradorListaVertices.hasNext()) {//percorre a lista de vertices 
                verticeVisuaAtual = (VerticeVisual) iteradorListaVertices.next();
               
                /* Verifica se cada elemento da lista de vertice � o correspodente a lista de pontos. */
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
