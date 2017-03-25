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
package br.uefs.ecomp.ForteSeguro.model;

import br.uefs.ecomp.ForteSeguro.util.Buscavel;

/**
 * Classe responsável por representar um ponto do trajeto a ser feito.
 * 
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class Ponto implements Buscavel {
    /* Nome do ponto.*/
    private String nome;
    
    /**
     * Obtém uma instância de ponto.
     * @param nome nome do ponto
     */
    public Ponto(String nome) {
        this.nome = nome; //atribui a referência recebida ao atributo "nome"
    }

    /**
     * Obtém o nome do ponto.
     * @return nome do ponto
     */
    public String getNome() {
        return nome; //retorna o atributo "nome"
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIgual(Buscavel buscavel) {
        if(buscavel == null) { //caso a referência recebida seja nula
            return false;
        }
        Ponto pontoComparado = (Ponto) buscavel; //obtém o objeto recebido com referência de Ponto
        
        return pontoComparado.getNome().equals(nome); //compara o nome do ponto recebido com o nome desse ponto e retorna o resultado
    }
    
}
