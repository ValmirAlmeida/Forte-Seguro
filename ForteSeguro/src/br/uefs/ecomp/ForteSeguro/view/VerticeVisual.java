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


/**
 * Classe que modela a representa��o gr�fica de um v�rtice.
 * 
 * @author Iago Almeida
 * @author Valmir Vinicius
 */
public class VerticeVisual {
    /* Coordenadas X e Y da localiza��o do v�rtice na tela. */
    private int posicaoX, posicaoY;
    
    /* Nome do v�rtice. */
    private String nome;
    
    /**
     * Obt�m uma nova inst�ncia de VerticeVisual.
     */
    public VerticeVisual(){};
    
    /**
     * Obt�m uma nova inst�ncia de VerticeVisual.
     * @param posicaoX coordenada X da localiza��o do v�rtice na tela
     * @param posicaoY coordenada U da localiza��o do v�rtice na tela
     * @param nome nome do v�rtice
     */
    public VerticeVisual(int posicaoX, int posicaoY, String nome){
        /* Atribui as informa��es recebidas aos respectivos atributos da classe. */
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.nome = nome;
    }    

    /**
     * Obt�m o nome do v�rtice.
     * @return nome do v�rtice
     */
    public String getNome() {
        return nome; //retorna o atributo "nome"
    }

    /**
     * Configura o nome do v�rtice.
     * @param nome nome do v�rtice
     */
    public void setNome(String nome) {
        this.nome = nome; //atribui o nome recebido ao atributo "nome"
    }
    
    /**
     * Obt�m a posi��o X do v�rtice.
     * @return posi��o X do v�rtice
     */
    public int getPosicaoX() {
        return posicaoX; //retorna o atributo "posicaoX"
    }

    /**
     * Configura a posi��o X do v�rtice.
     * @param posicaoX posi��o X do v�rtice
     */
    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX; //atribui a posi��o recebida ao atributo "posicaoX"
    }
    
    /**
     * Obt�m a posi��o Y do v�rtice.
     * @return posi��o Y do v�rtice
     */
    public int getPosicaoY() {
        return posicaoY; //retorna o atributo "posicaoY"
    }
    
    /**
     * Configura a posi��o Y do v�rtice.
     * @param posicaoY posi��o Y do v�rtice
     */
    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY; //atribui a posi��o recebida ao atributo "posicaoY"
    }
    
    
}
