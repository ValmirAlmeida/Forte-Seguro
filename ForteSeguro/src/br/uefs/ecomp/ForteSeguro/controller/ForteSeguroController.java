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
 * Classe responsável por gerenciar os processos da aplicação.
 *
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class ForteSeguroController {

    /* Referência para uma instância do algoritmo DijkstraForte. */
    private DijkstraForte algoritmo;

    /* Grafo ponderado que contém os pontos e os caminhos cadastrados. */
    private GrafoPonderado grafo;

    /**
     * Obtém uma nova instância do controller.
     */
    public ForteSeguroController() {
        algoritmo = new DijkstraForte(); //obtém uma instância do algoritmo utilizado
        grafo = new GrafoPonderado(); //obtém uma instância de grafo
    }

    /**
     * Obtém os caminhos mínimos entre o estacionamento e o banco, passando pelo ponto de coleta.
     * @param nomeEstacionamento nome do estacionamento
     * @param nomePontoDeColeta nome do ponto de coleta
     * @param nomeBanco nome do banco
     * @return lista de lista de vértices que representam os caminhos mínimos entre o estacionamento e o banco, passando pelo ponto de coleta
     * @throws PontoInexistenteException caso não exista ponto com o nome
     * informado
     * @throws NomeInvalidoException caso o nome informado para o ponto seja
     * inválido
     * @throws PontoInatingivelException caso algum dos pontos informados seja inatingível, isto é, caso não exista
     * conexão entre o ponto e o resto dos pontos
     * @throws OrigemEDestinoIguaisException caso a origem e o destino dos caminhos coincida
     */
    public List<List<Vertice>> obterCaminhoMinimo(String nomeEstacionamento, String nomePontoDeColeta, String nomeBanco) throws PontoInexistenteException, NomeInvalidoException, PontoInatingivelException, OrigemEDestinoIguaisException {
        Ponto estacionamento, coleta, banco; //referências para pontos
        algoritmo.definirGrafo(grafo); //define o grafo no qual será aplicado o algoritmo
        
        try {
            /* Obtém os pontos. */
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
        
        
        /* Obtém as duas partes que correspondem ao caminho mínimo completo (do estacionamento ao ponto de coleta, do ponto de coleta ao banco). */
        List<List<Vertice>> caminhosMinimosPrimeiraParte = algoritmo.executar(grafo.obterVertice(estacionamento), grafo.obterVertice(coleta)); //obtém os possíveis caminhos indo do estacionamento ao ponto de coleta
        List<List<Vertice>> caminhosMinimosSegundaParte = algoritmo.executar(grafo.obterVertice(coleta), grafo.obterVertice(banco));

        if (caminhosMinimosPrimeiraParte == null || caminhosMinimosSegundaParte == null) { //caso algum dos pontos informados seja inatingível
            throw new PontoInatingivelException();
        }

        Iterator<List<Vertice>> iterador = caminhosMinimosPrimeiraParte.iterator(); //iterador para a lista de listas que corresponde aos caminhos daprimeira parte

        /* No laço de repetição abaixo é realizada a remoção do último vértice das listas da primeira parte do caminho, 
        pois esses vértices existem na listas da segunda parte e seriam repetidos.
         */
        while (iterador.hasNext()) {
            List<Vertice> lista = iterador.next();

            lista.remove(lista.size() - 1);
        }

        return fundirCaminhos(caminhosMinimosPrimeiraParte, caminhosMinimosSegundaParte); //funde os caminhos e retorna a lista resultante
    }

    /**
     * Funde os caminhos mínimos entre o estacionamento e o ponto de coleta e
     * entre o ponto de coleta e o banco.
     *
     * @param caminhosMinimosPrimeiraParte primeira parte dos caminhos mínimos,
     * indo do estacionamento ao ponto de coleta
     * @param caminhosMinimosSegundaParte segunda parte dos caminhos mínimos,
     * indo do ponto de coleta ao banco
     * @return lista contendo listas que correspondem aos caminhos mínimos
     */
    private List<List<Vertice>> fundirCaminhos(List<List<Vertice>> caminhosMinimosPrimeiraParte, List<List<Vertice>> caminhosMinimosSegundaParte) {
        Iterator<List<Vertice>> iterador1 = caminhosMinimosPrimeiraParte.iterator(); //obtém o iterador da lista de listas da primeira parte do caminho
        Iterator<List<Vertice>> iterador2; //iterador para a lista de listas correspondente a segunda parte do caminho
        List<List<Vertice>> caminhosFundidos = new ArrayList<>(); //lista que conterá todos os caminhos possíveis
        List<Vertice> caminhoObtido1, caminhoObtido2, novoCaminho; //listas auxiliares

        while (iterador1.hasNext()) { //enquanto há um próximo caminho minimo
            caminhoObtido1 = iterador1.next(); //obtém a primeira parte de um caminho mínimo
            iterador2 = caminhosMinimosSegundaParte.iterator(); //obtém o iterador para a lista de listas que corresponde a segunda parte do caminho

            while (iterador2.hasNext()) { //enquanto há um próximo caminho minimo
                caminhoObtido2 = iterador2.next(); //obtém a segunda parte de um caminho mínimo
                novoCaminho = new ArrayList<>(); //cria uma nova lista para armazenar um caminho mínimo
                novoCaminho.addAll(caminhoObtido1); //concatena a primeira parte do caminho na lista de caminho mínimo
                novoCaminho.addAll(caminhoObtido2);  //concatena a segunda parte do caminho na lista de caminho mínimo
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
     * inválido
     * @throws CadastrarPontoExistenteException caso já existe o cadastro de um ponto com
     * o nome informado
     */
    public void cadastrarPonto(String nomePonto) throws NomeInvalidoException, CadastrarPontoExistenteException {
        try {
            obterPonto(nomePonto); //tenta obter o ponto que será cadastrado
            throw new CadastrarPontoExistenteException(); //caso a execução chegue nesse ponto lança uma exceção indicando que o ponto já existe
        } catch (PontoInexistenteException ex) { //caso seja lançada uma exceção de ponto inexistente
            grafo.adicionarVertice(new Ponto(nomePonto)); //insere o novo ponto em um novo vértice do grafo
        } catch (NomeInvalidoException ex) {
            throw ex;
        }

    }

    /**
     * Realiza a remoção de um ponto cadastrado.
     *
     * @param nomePonto nome do ponto que será removido
     * @throws PontoInexistenteException caso não exista ponto com o nome
     * informado
     * @throws NomeInvalidoException caso o nome informado para o ponto seja
     * inválido
     */
    public void removerPonto(String nomePonto) throws PontoInexistenteException, NomeInvalidoException {
        Ponto ponto = null; //referência para o ponto que será removido

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
     * @param tempo tempo necessário para percorrer o novo caminho
     * @throws PontoInexistenteException caso não exista ponto com o nome
     * informado
     * @throws NomeInvalidoException caso o nome informado para o ponto seja
     * inválido
     * @throws CaminhoJaExisteException caso o caminho a ser cadastrado já exista
     */
    public void cadastrarCaminho(String nomeExtremidade, String nomeExtremidade2, int tempo) throws PontoInexistenteException, NomeInvalidoException, CaminhoJaExisteException {
        /* Referências para as extremidades do novo caminho. */
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

        if (grafo.obterAresta(pontoExtremidade, pontoExtremidade2) != null) { //caso a aresta já exista
            throw new CaminhoJaExisteException();
        }

        grafo.adicionarAresta(pontoExtremidade, pontoExtremidade2, tempo); //realiza o cadastro do caminho
    }

    /**
     * Realiza a remoção de um caminho previamente cadastrado.
     *
     * @param nomeExtremidade nome do ponto de uma das extremidades
     * @param nomeExtremidade2 nome do ponto de uma das extremidades
     * @throws PontoInexistenteException caso não exista ponto com o nome
     * informado
     * @throws RemoverCaminhoInexistenteException caso não exista caminho cujas
     * extremidades sejam as informadas
     * @throws NomeInvalidoException caso o nome informado para o ponto seja
     * inválido
     */
    public void removerCaminho(String nomeExtremidade, String nomeExtremidade2) throws PontoInexistenteException, RemoverCaminhoInexistenteException, NomeInvalidoException {
        /* Referências para os pontos. */
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

        int resultadoRemocao = grafo.removerAresta(pontoExtremidade, pontoExtremidade2); //tenta realizar a remoção desejada e retorna o valor resultante

        if (resultadoRemocao == 0) { //caso o caminho que se deseja remover não exista
            throw new RemoverCaminhoInexistenteException();
        }
    }

    /**
     * Obtém um ponto cadastrado.
     *
     * @param nomePonto nome do ponto que se deseja obter
     * @return referência para o ponto cujo nome foi informado, caso ele tenha
     * sido cadastrado; <code>null</code>, caso contrário.
     * @throws PontoInexistenteException caso não exista ponto com o nome
     * informado
     * @throws NomeInvalidoException caso o nome informado para o ponto seja
     * inválido
     */
    public Ponto obterPonto(String nomePonto) throws PontoInexistenteException, NomeInvalidoException {
        if (nomePonto == null || nomePonto.trim().isEmpty()) { //caso algum dos nomes informados seja inválido
            throw new NomeInvalidoException();
        }

        List<Vertice> vertices = grafo.getVertices(); //obtém a lista de vértices do grafo
        Iterator<Vertice> iterador = vertices.iterator(); //obtém o iterador da lista de vértices
        Vertice auxiliar; //referência auxiliar para vértice

        while (iterador.hasNext()) { //percorre a lista de vértices
            auxiliar = iterador.next(); //obtém um vértice

            if (((Ponto) auxiliar.getConteudo()).getNome().equals(nomePonto)) { //verifica se o nome do vértice obtido condiz com o procurado
                return (Ponto) auxiliar.getConteudo();
            }
        }

        throw new PontoInexistenteException();

    }

    /**
     * Obtém uma representação em texto de um dado caminho mínimo.
     * @param caminhoMinimo caminho mínimo para qual se deseja obter uma representação em texto
     * @return string representando um caminho minimo
     */
    public String obterRotaEmTexto(List<Vertice> caminhoMinimo) {
        Iterator<Vertice> iVertices = caminhoMinimo.iterator(); //obtém o iterador da lista de caminho mínimo
        StringBuffer rotaFinal = new StringBuffer(); //obtém uma nova instância de StringBuffer
        Vertice verticeAuxiliar; //referência auxiliar para vértice

        while (iVertices.hasNext()) { //percorre a lista de caminho mínimo
            verticeAuxiliar = iVertices.next(); //obtém um vértice
            rotaFinal.append(((Ponto) verticeAuxiliar.getConteudo()).getNome()); //concatena o vértice obtido na String
            rotaFinal.append(" - ");
        }

        return rotaFinal.toString();
    }

}
