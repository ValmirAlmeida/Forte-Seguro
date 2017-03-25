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

/**
 * Interface a ser implementada em objetos que serão inseridos no grafo, afim de possibilitar a busca
 * desses.
 * 
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public interface Buscavel {
    
    /**
     * Verifica se esse objeto é igual ao recebido por parâmetro.
     * @param buscavel objeto que será comperado
     * @return <code>true</code>, caso os objetos sejam iguais; <code>false</code>, caso contrário
     */
    public boolean isIgual(Buscavel buscavel);
    
}
