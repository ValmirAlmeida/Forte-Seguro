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
 * Classe que modela a representa��o gr�fica de uma aresta.
 * 
 * @author Iago Almeida
 * @author Valmir Vinicius
 */
public class ArestaVisual {
    /* Extremidades da aresta. */
    private VerticeVisual extremidade1, extremidade2;
    
    /* Tempo para percorrer a aresta. */
    private int tempo;
    
    /**
     * Obt�m uma nova inst�ncia de aresta visual. 
     */
    public ArestaVisual() {
        
    }
    
    /**
     * Obt�m uma nova inst�ncia de aresta visual. 
     * @param extremidade1 extremidade da aresta visual
     * @param extremidade2 extremidade da aresta visual
     * @param tempo tempo para percorrer a aresta
     */
    public ArestaVisual(VerticeVisual extremidade1, VerticeVisual extremidade2, int tempo) {
        /* Define os atributos da aresta visual. */
        this.extremidade1 = extremidade1;
        this.extremidade2 = extremidade2;
        this.tempo = tempo;
    }

    /**
     * Retorna o tempo para percorrer a aresta.
     * @return tempo para percorrer a aresta
     */
    public int getTempo() {
        return tempo; //retorna o atributo "tempo"
    }

    /**
     * Configura o tempo para percorrer a aresta.
     * @param tempo tempo para percorrer a aresta
     */
    public void setTempo(int tempo) {
        this.tempo = tempo; //atribui o tempo recebido ao atributo "tempo"
    }

    /**
     * Retorna uma das extremidades da aresta.
     * @return uma das extremidades da aresta
     */    
    public VerticeVisual getExtremidade1() {
        return extremidade1; //retorna o atributo "extremidade1"
    }

    /**
     * Configura uma das extremidades da aresta.
     * @param extremidade1 uma das extremidades da aresta
     */    
    public void setExtremidade1(VerticeVisual extremidade1) {
        this.extremidade1 = extremidade1; //atribui a extremidade recebido ao atributo "extremidade1"
    }

    /**
     * Retorna uma das extremidades da aresta.
     * @return uma das extremidades da aresta
     */        
    public VerticeVisual getExtremidade2() {
        return extremidade2; //retorna o atributo "extremidade2"
    }

    /**
     * Configura uma das extremidades da aresta.
     * @param extremidade2 uma das extremidades da aresta
     */        
    public void setExtremidade2(VerticeVisual extremidade2) {
        this.extremidade2 = extremidade2; //atribui a extremidade recebido ao atributo "extremidade2"
    }

}
