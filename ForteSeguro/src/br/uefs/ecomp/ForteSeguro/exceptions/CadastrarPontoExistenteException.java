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
 * Exceção lançada caso o nome atribuido a um novo ponto já tenha sido cadastrado anteriormente.
 * 
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class CadastrarPontoExistenteException extends Exception {
   /**
    * Cria uma nova instância da exceção com uma mensagem predefinida.
    */
    public CadastrarPontoExistenteException() {
        super("Já existe um ponto com o nome informado!");
    }
	
    /**
     * Cria uma nova instância da exceção com uma mensagem predefinida e uma exceção que a causou.
     * @param causa causa da exceção
     */
    public CadastrarPontoExistenteException(Throwable causa) {
	super("Já existe um ponto com o nome informado!", causa);
    }
            
    
}
