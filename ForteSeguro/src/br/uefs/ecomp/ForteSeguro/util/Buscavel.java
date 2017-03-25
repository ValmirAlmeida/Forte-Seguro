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

/**
 * Interface a ser implementada em objetos que ser�o inseridos no grafo, afim de possibilitar a busca
 * desses.
 * 
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public interface Buscavel {
    
    /**
     * Verifica se esse objeto � igual ao recebido por par�metro.
     * @param buscavel objeto que ser� comperado
     * @return <code>true</code>, caso os objetos sejam iguais; <code>false</code>, caso contr�rio
     */
    public boolean isIgual(Buscavel buscavel);
    
}
