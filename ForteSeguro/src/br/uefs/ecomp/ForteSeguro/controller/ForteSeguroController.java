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
package br.uefs.ecomp.ForteSeguro.controller;

import br.uefs.ecomp.ForteSeguro.exceptions.CaminhoJaExisteException;
import br.uefs.ecomp.ForteSeguro.exceptions.RemoverCaminhoInexistenteException;
import br.uefs.ecomp.ForteSeguro.exceptions.NomeInvalidoException;
import br.uefs.ecomp.ForteSeguro.exceptions.CadastrarPontoExistenteException;
import br.uefs.ecomp.ForteSeguro.exceptions.OrigemEDestinoIguaisException;
import br.uefs.ecomp.ForteSeguro.exceptions.PontoInatingivelException;
import br.uefs.ecomp.ForteSeguro.exceptions.PontoInexistenteException;
import br.uefs.ecomp.ForteSeguro.model.Ponto;
import br.uefs.ecomp.ForteSeguro.util.DijkstraForte;
import br.uefs.ecomp.ForteSeguro.util.GrafoPonderado;
import br.uefs.ecomp.ForteSeguro.util.Vertice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe respons�vel por gerenciar os processos da aplica��o.
 *
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class ForteSeguroController {

    /* Refer�ncia para uma inst�ncia do algoritmo DijkstraForte. */
    private DijkstraForte algoritmo;

    /* Grafo ponderado que cont�m os pontos e os caminhos cadastrados. */
    private GrafoPonderado grafo;

    /**
     * Obt�m uma nova inst�ncia do controller.
     */
    public ForteSeguroController() {
        algoritmo = new DijkstraForte(); //obt�m uma inst�ncia do algoritmo utilizado
        grafo = new GrafoPonderado(); //obt�m uma inst�ncia de grafo
    }

    /**
     * Obt�m os caminhos m�nimos entre o estacionamento e o banco, passando pelo ponto de coleta.
     * @param nomeEstacionamento nome do estacionamento
     * @param nomePontoDeColeta nome do ponto de coleta
     * @param nomeBanco nome do banco
     * @return lista de lista de v�rtices que representam os caminhos m�nimos entre o estacionamento e o banco, passando pelo ponto de coleta
     * @throws PontoInexistenteException caso n�o exista ponto com o nome
     * informado
     * @throws NomeInvalidoException caso o nome informado para o ponto seja
     * inv�lido
     * @throws PontoInatingivelException caso algum dos pontos informados seja inating�vel, isto �, caso n�o exista
     * conex�o entre o ponto e o resto dos pontos
     * @throws OrigemEDestinoIguaisException caso a origem e o destino dos caminhos coincida
     */
    public List<List<Vertice>> obterCaminhoMinimo(String nomeEstacionamento, String nomePontoDeColeta, String nomeBanco) throws PontoInexistenteException, NomeInvalidoException, PontoInatingivelException, OrigemEDestinoIguaisException {
        Ponto estacionamento, coleta, banco; //refer�ncias para pontos
        algoritmo.definirGrafo(grafo); //define o grafo no qual ser� aplicado o algoritmo
        
        try {
            /* Obt�m os pontos. */
            estacionamento = obterPonto(nomeEstacionamento);
            coleta = obterPonto(nomePontoDeColeta);
            banco = obterPonto(nomeBanco);
        } catch (PontoInexistenteException ex) {
            throw ex;
        } catch (NomeInvalidoException ex) {
            throw ex;
        }        
        
        if(nomeEstacionamento.equals(nomePontoDeColeta) || nomePontoDeColeta.equals(nomeBanco) || nomeEstacionamento.equals(nomeBanco)) { //caso algum dos pontos coincidam
            throw new OrigemEDestinoIguaisException();
        }
        
        
        /* Obt�m as duas partes que correspondem ao caminho m�nimo completo (do estacionamento ao ponto de coleta, do ponto de coleta ao banco). */
        List<List<Vertice>> caminhosMinimosPrimeiraParte = algoritmo.executar(grafo.obterVertice(estacionamento), grafo.obterVertice(coleta)); //obt�m os poss�veis caminhos indo do estacionamento ao ponto de coleta
        List<List<Vertice>> caminhosMinimosSegundaParte = algoritmo.executar(grafo.obterVertice(coleta), grafo.obterVertice(banco));

        if (caminhosMinimosPrimeiraParte == null || caminhosMinimosSegundaParte == null) { //caso algum dos pontos informados seja inating�vel
            throw new PontoInatingivelException();
        }

        Iterator<List<Vertice>> iterador = caminhosMinimosPrimeiraParte.iterator(); //iterador para a lista de listas que corresponde aos caminhos daprimeira parte

        /* No la�o de repeti��o abaixo � realizada a remo��o do �ltimo v�rtice das listas da primeira parte do caminho, 
        pois esses v�rtices existem na listas da segunda parte e seriam repetidos.
         */
        while (iterador.hasNext()) {
            List<Vertice> lista = iterador.next();

            lista.remove(lista.size() - 1);
        }

        return fundirCaminhos(caminhosMinimosPrimeiraParte, caminhosMinimosSegundaParte); //funde os caminhos e retorna a lista resultante
    }

    /**
     * Funde os caminhos m�nimos entre o estacionamento e o ponto de coleta e
     * entre o ponto de coleta e o banco.
     *
     * @param caminhosMinimosPrimeiraParte primeira parte dos caminhos m�nimos,
     * indo do estacionamento ao ponto de coleta
     * @param caminhosMinimosSegundaParte segunda parte dos caminhos m�nimos,
     * indo do ponto de coleta ao banco
     * @return lista contendo listas que correspondem aos caminhos m�nimos
     */
    private List<List<Vertice>> fundirCaminhos(List<List<Vertice>> caminhosMinimosPrimeiraParte, List<List<Vertice>> caminhosMinimosSegundaParte) {
        Iterator<List<Vertice>> iterador1 = caminhosMinimosPrimeiraParte.iterator(); //obt�m o iterador da lista de listas da primeira parte do caminho
        Iterator<List<Vertice>> iterador2; //iterador para a lista de listas correspondente a segunda parte do caminho
        List<List<Vertice>> caminhosFundidos = new ArrayList<>(); //lista que conter� todos os caminhos poss�veis
        List<Vertice> caminhoObtido1, caminhoObtido2, novoCaminho; //listas auxiliares

        while (iterador1.hasNext()) { //enquanto h� um pr�ximo caminho minimo
            caminhoObtido1 = iterador1.next(); //obt�m a primeira parte de um caminho m�nimo
            iterador2 = caminhosMinimosSegundaParte.iterator(); //obt�m o iterador para a lista de listas que corresponde a segunda parte do caminho

            while (iterador2.hasNext()) { //enquanto h� um pr�ximo caminho minimo
                caminhoObtido2 = iterador2.next(); //obt�m a segunda parte de um caminho m�nimo
                novoCaminho = new ArrayList<>(); //cria uma nova lista para armazenar um caminho m�nimo
                novoCaminho.addAll(caminhoObtido1); //concatena a primeira parte do caminho na lista de caminho m�nimo
                novoCaminho.addAll(caminhoObtido2);  //concatena a segunda parte do caminho na lista de caminho m�nimo
                caminhosFundidos.add(novoCaminho); //adiciona o caminho obtido na lista de listas
            }
        }

        return caminhosFundidos;
    }

    /**
     * Realiza o cadastro de um novo ponto.
     *
     * @param nomePonto nome do novo ponto
     * @throws NomeInvalidoException caso o nome informado para o ponto seja
     * inv�lido
     * @throws CadastrarPontoExistenteException caso j� existe o cadastro de um ponto com
     * o nome informado
     */
    public void cadastrarPonto(String nomePonto) throws NomeInvalidoException, CadastrarPontoExistenteException {
        try {
            obterPonto(nomePonto); //tenta obter o ponto que ser� cadastrado
            throw new CadastrarPontoExistenteException(); //caso a execu��o chegue nesse ponto lan�a uma exce��o indicando que o ponto j� existe
        } catch (PontoInexistenteException ex) { //caso seja lan�ada uma exce��o de ponto inexistente
            grafo.adicionarVertice(new Ponto(nomePonto)); //insere o novo ponto em um novo v�rtice do grafo
        } catch (NomeInvalidoException ex) {
            throw ex;
        }

    }

    /**
     * Realiza a remo��o de um ponto cadastrado.
     *
     * @param nomePonto nome do ponto que ser� removido
     * @throws PontoInexistenteException caso n�o exista ponto com o nome
     * informado
     * @throws NomeInvalidoException caso o nome informado para o ponto seja
     * inv�lido
     */
    public void removerPonto(String nomePonto) throws PontoInexistenteException, NomeInvalidoException {
        Ponto ponto = null; //refer�ncia para o ponto que ser� removido

        try {
            ponto = obterPonto(nomePonto); //tenta obter o ponto
        } catch (PontoInexistenteException ex) {
            throw ex;
        } catch (NomeInvalidoException ex) {
            throw ex;
        }

        grafo.removerVertice(ponto); //remove o ponto
    }

    /**
     * Realiza o cadastro de um novo caminho.
     *
     * @param nomeExtremidade nome do ponto de uma das extremidades
     * @param nomeExtremidade2 nome do ponto de uma das extremidades
     * @param tempo tempo necess�rio para percorrer o novo caminho
     * @throws PontoInexistenteException caso n�o exista ponto com o nome
     * informado
     * @throws NomeInvalidoException caso o nome informado para o ponto seja
     * inv�lido
     * @throws CaminhoJaExisteException caso o caminho a ser cadastrado j� exista
     */
    public void cadastrarCaminho(String nomeExtremidade, String nomeExtremidade2, int tempo) throws PontoInexistenteException, NomeInvalidoException, CaminhoJaExisteException {
        /* Refer�ncias para as extremidades do novo caminho. */
        Ponto pontoExtremidade = null;
        Ponto pontoExtremidade2 = null;

        try {
            /* Tenta obter os pontos. */
            pontoExtremidade = obterPonto(nomeExtremidade);
            pontoExtremidade2 = obterPonto(nomeExtremidade2);
        } catch (PontoInexistenteException ex) {
            throw ex;
        } catch (NomeInvalidoException ex) {
            throw ex;
        }

        if (grafo.obterAresta(pontoExtremidade, pontoExtremidade2) != null) { //caso a aresta j� exista
            throw new CaminhoJaExisteException();
        }

        grafo.adicionarAresta(pontoExtremidade, pontoExtremidade2, tempo); //realiza o cadastro do caminho
    }

    /**
     * Realiza a remo��o de um caminho previamente cadastrado.
     *
     * @param nomeExtremidade nome do ponto de uma das extremidades
     * @param nomeExtremidade2 nome do ponto de uma das extremidades
     * @throws PontoInexistenteException caso n�o exista ponto com o nome
     * informado
     * @throws RemoverCaminhoInexistenteException caso n�o exista caminho cujas
     * extremidades sejam as informadas
     * @throws NomeInvalidoException caso o nome informado para o ponto seja
     * inv�lido
     */
    public void removerCaminho(String nomeExtremidade, String nomeExtremidade2) throws PontoInexistenteException, RemoverCaminhoInexistenteException, NomeInvalidoException {
        /* Refer�ncias para os pontos. */
        Ponto pontoExtremidade = null;
        Ponto pontoExtremidade2 = null;

        try {
            /* Tenta obter os pontos. */
            pontoExtremidade = obterPonto(nomeExtremidade);
            pontoExtremidade2 = obterPonto(nomeExtremidade2);
        } catch (PontoInexistenteException ex) {
            throw ex;
        } catch (NomeInvalidoException ex) {
            throw ex;
        }

        int resultadoRemocao = grafo.removerAresta(pontoExtremidade, pontoExtremidade2); //tenta realizar a remo��o desejada e retorna o valor resultante

        if (resultadoRemocao == 0) { //caso o caminho que se deseja remover n�o exista
            throw new RemoverCaminhoInexistenteException();
        }
    }

    /**
     * Obt�m um ponto cadastrado.
     *
     * @param nomePonto nome do ponto que se deseja obter
     * @return refer�ncia para o ponto cujo nome foi informado, caso ele tenha
     * sido cadastrado; <code>null</code>, caso contr�rio.
     * @throws PontoInexistenteException caso n�o exista ponto com o nome
     * informado
     * @throws NomeInvalidoException caso o nome informado para o ponto seja
     * inv�lido
     */
    public Ponto obterPonto(String nomePonto) throws PontoInexistenteException, NomeInvalidoException {
        if (nomePonto == null || nomePonto.trim().isEmpty()) { //caso algum dos nomes informados seja inv�lido
            throw new NomeInvalidoException();
        }

        List<Vertice> vertices = grafo.getVertices(); //obt�m a lista de v�rtices do grafo
        Iterator<Vertice> iterador = vertices.iterator(); //obt�m o iterador da lista de v�rtices
        Vertice auxiliar; //refer�ncia auxiliar para v�rtice

        while (iterador.hasNext()) { //percorre a lista de v�rtices
            auxiliar = iterador.next(); //obt�m um v�rtice

            if (((Ponto) auxiliar.getConteudo()).getNome().equals(nomePonto)) { //verifica se o nome do v�rtice obtido condiz com o procurado
                return (Ponto) auxiliar.getConteudo();
            }
        }

        throw new PontoInexistenteException();

    }

    /**
     * Obt�m uma representa��o em texto de um dado caminho m�nimo.
     * @param caminhoMinimo caminho m�nimo para qual se deseja obter uma representa��o em texto
     * @return string representando um caminho minimo
     */
    public String obterRotaEmTexto(List<Vertice> caminhoMinimo) {
        Iterator<Vertice> iVertices = caminhoMinimo.iterator(); //obt�m o iterador da lista de caminho m�nimo
        StringBuffer rotaFinal = new StringBuffer(); //obt�m uma nova inst�ncia de StringBuffer
        Vertice verticeAuxiliar; //refer�ncia auxiliar para v�rtice

        while (iVertices.hasNext()) { //percorre a lista de caminho m�nimo
            verticeAuxiliar = iVertices.next(); //obt�m um v�rtice
            rotaFinal.append(((Ponto) verticeAuxiliar.getConteudo()).getNome()); //concatena o v�rtice obtido na String
            rotaFinal.append(" - ");
        }

        return rotaFinal.toString();
    }

}
