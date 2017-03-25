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
package br.uefs.ecomp.ForteSeguro.model;

import br.uefs.ecomp.ForteSeguro.util.Buscavel;

/**
 * Classe respons�vel por representar um ponto do trajeto a ser feito.
 * 
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class Ponto implements Buscavel {
    /* Nome do ponto.*/
    private String nome;
    
    /**
     * Obt�m uma inst�ncia de ponto.
     * @param nome nome do ponto
     */
    public Ponto(String nome) {
        this.nome = nome; //atribui a refer�ncia recebida ao atributo "nome"
    }

    /**
     * Obt�m o nome do ponto.
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
        if(buscavel == null) { //caso a refer�ncia recebida seja nula
            return false;
        }
        Ponto pontoComparado = (Ponto) buscavel; //obt�m o objeto recebido com refer�ncia de Ponto
        
        return pontoComparado.getNome().equals(nome); //compara o nome do ponto recebido com o nome desse ponto e retorna o resultado
    }
    
}
