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


/**
 * Classe que modela a representação gráfica de um vértice.
 * 
 * @author Iago Almeida
 * @author Valmir Vinicius
 */
public class VerticeVisual {
    /* Coordenadas X e Y da localização do vértice na tela. */
    private int posicaoX, posicaoY;
    
    /* Nome do vértice. */
    private String nome;
    
    /**
     * Obtém uma nova instância de VerticeVisual.
     */
    public VerticeVisual(){};
    
    /**
     * Obtém uma nova instância de VerticeVisual.
     * @param posicaoX coordenada X da localização do vértice na tela
     * @param posicaoY coordenada U da localização do vértice na tela
     * @param nome nome do vértice
     */
    public VerticeVisual(int posicaoX, int posicaoY, String nome){
        /* Atribui as informações recebidas aos respectivos atributos da classe. */
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.nome = nome;
    }    

    /**
     * Obtém o nome do vértice.
     * @return nome do vértice
     */
    public String getNome() {
        return nome; //retorna o atributo "nome"
    }

    /**
     * Configura o nome do vértice.
     * @param nome nome do vértice
     */
    public void setNome(String nome) {
        this.nome = nome; //atribui o nome recebido ao atributo "nome"
    }
    
    /**
     * Obtém a posição X do vértice.
     * @return posição X do vértice
     */
    public int getPosicaoX() {
        return posicaoX; //retorna o atributo "posicaoX"
    }

    /**
     * Configura a posição X do vértice.
     * @param posicaoX posição X do vértice
     */
    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX; //atribui a posição recebida ao atributo "posicaoX"
    }
    
    /**
     * Obtém a posição Y do vértice.
     * @return posição Y do vértice
     */
    public int getPosicaoY() {
        return posicaoY; //retorna o atributo "posicaoY"
    }
    
    /**
     * Configura a posição Y do vértice.
     * @param posicaoY posição Y do vértice
     */
    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY; //atribui a posição recebida ao atributo "posicaoY"
    }
    
    
}
