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
package br.uefs.ecomp.ForteSeguro.exceptions;

/**
 * Exceção lançada ao informar um ponto inatingível na tentativa de encontrar o menor caminho.
 * 
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class PontoInatingivelException extends Exception {
   /**
    * Cria uma nova instância da exceção com uma mensagem predefinida.
    */
    public PontoInatingivelException() {
        super("Impossível atingir um dos pontos informados. Tente novamente!");
    }
	
    /**
     * Cria uma nova instância da exceção com uma mensagem predefinida e uma exceção que a causou.
     * @param causa causa da exceção
     */
    public PontoInatingivelException(Throwable causa) {
	super("Impossível atingir um dos pontos informados. Tente novamente!", causa);
    }    
    
}
