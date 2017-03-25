package br.uefs.ecomp.ForteSeguro.util;

import br.uefs.ecomp.ForteSeguro.model.Ponto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Classe na qual é implementada uma versão modificada do algoritmo de Dijkstra.
 *
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class DijkstraForte {

    /* Matriz que representa o grafo no qual será aplicado o algoritmo. */
    private Object[][] matrizGrafo;

    /* Lista de visitas que já foram realizadas. */
    public List<Visita> visitasRealizadas;

    /* Fila de prioridade contendo possíveis visitas. */
    private PriorityQueue<Visita> possiveisVisitas;

    /* Vértice de início do caminho. */
    private Vertice origem;

    /* Lista contedo os vértices que foram o menor caminho.*/
    public List<List<Vertice>> caminhosMinimos;

    /**
     * Obtém uma instância do algoritmo de Dijkstra.
     */
    public DijkstraForte() {
        visitasRealizadas = new ArrayList<>(); //obtém uma instância de ArrayLnnmnnist
        possiveisVisitas = new PriorityQueue<>(); //obtém uma instância de PriorityQueue
    }

    /**
     * Define o grafo no qual será aplicado o algoritmo.
     *
     * @param grafo grafo no qual será aplicado o algoritmo
     */
    public void definirGrafo(GrafoPonderado grafo) {
        matrizGrafo = grafo.obterMatriz(); //obtém a representação em matriz do grafo
    }

    /**
     * Executa o algoritmo para cálculo de menor caminho.
     *
     * @param origem origem do caminho
     * @param destino destino do caminho
     * @return lista contendo todas as listas que vértices que resultam nos
     * menores caminhos possíveis
     */
    public List<List<Vertice>> executar(Vertice origem, Vertice destino) {
        visitasRealizadas = new ArrayList<>(); //obtém uma instância de ArrayList
        possiveisVisitas = new PriorityQueue<>(); //obtém uma instância de PriorityQueue            
        this.origem = origem; //define a nova origem
        
        /* Realiza verificações inicias para garantir que os vértices recebidos são válidos para execução do algoritmo. */
        if (origem == null || destino == null || destino == origem || !isVerticeValido(origem) || !isVerticeValido(destino)) {
            return null;
        }
        
        calcularTodosPossiveisCaminhosMinimos(); //calcula os caminhos mínimos possíveis
        

        caminhosMinimos = new ArrayList<>(); //obtém uma nova instância de ArrayList

        gerarMenoresCaminhos(destino, new ArrayList<>()); //gera os menores caminhos com o destino definido

        return caminhosMinimos; //retorna a lista gerada
    }

    /**
     * Verifica se todos os vértices válidos foram visitados.
     * @return <code>true</code>, caso todos os vértices válidos tenham sido visitados; <code>false</code>, caso contrário
     */
    private boolean isTodosVisitados() {
        for (int i = 1; i < matrizGrafo.length; i++) { //percorre as colunas da matriz a partir da segunda
            if (isVerticeValido(((Vertice) matrizGrafo[0][i]))) { //verifica se o vértice é válido
                if (obterVisitaRealizada((Vertice) matrizGrafo[0][i]) == null) { //verifica se existe uma visita com o vértice
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Realiza o cálculo de todos os mínimos caminhos a partir da origem
     * fornecida.
     */
    private void calcularTodosPossiveisCaminhosMinimos() {
        if (matrizGrafo == null && origem == null && !isVerticeValido(origem)) { //verifica se foi definido um grafo e uma origem e se essa origem é válida
            return;
        }

        visitasRealizadas.add(new Visita(origem, 0, null)); //cria uma nova visita correspondente a origem e a adiciona na lista de visitas realizadas
        descobrirPossiveisVisitas(origem); //descobre as possíveis visitas que podem ser feitas a partir da origem
        realizarVisita(); //realiza uma visita a partir das possíveis visitas que partem da origem

        while (true) { //enquanto todos os vértices do grafo não forem visitados
            descobrirPossiveisVisitas(visitasRealizadas.get(visitasRealizadas.size() - 1).getDestino()); //descobre as possíveis visitas que partem do destino do último vértice visitado
            realizarVisita(); //realiza uma a melhor visita partindo do destino da última visita realizada

            if (possiveisVisitas.peek() == null) { //caso a lista de possíveis visitas esteja vazia
                break;
            }
            if (isTodosVisitados()) { //caso todos os vértices já tenham sido visitados
                break;
            }
        }

        visitasRealizadas.remove(0); //realiza a primeira visita da lista de visitas realizadas, essa visita não tem significação para o resultado final, pois corresponde a uma visita que tem como destino a origem e não possui via 

    }

    /**
     * Descobre as possíveis visitas que podem ser realizadas a partir de um
     * dado vértice que será a via.
     *
     * @param via vértice a partir do qual serão descobertas as possíveis
     * visitas
     */
    private void descobrirPossiveisVisitas(Vertice via) {
        int linhaVia = obterLinhaVertice(via); //obtém a linha da matriz de incidência que corresponde a via recebida

        if (linhaVia == -1) { //caso a linha que corresponde a via não seja encontrada
            return;
        }

        int custoVia = obterCustoMinimoCaminho(via); //obtém o custo para se chegar a via

        for (int coluna = 1; coluna < matrizGrafo[1].length; coluna++) { //obtém valores que correspondem aos indíces das colunas da matriz de incidência, a partir da segunda

            if (matrizGrafo[linhaVia][coluna] != null) {
                Visita visitaPorOutraVia = obterPossivelVisita((Vertice) matrizGrafo[0][coluna], custoVia + (Integer) matrizGrafo[linhaVia][coluna]); //referência para uma visita já realizada

                if (visitaPorOutraVia == null) { //caso não tenha sido feita uma visita por outra via
                    possiveisVisitas.add(new Visita((Vertice) matrizGrafo[0][coluna], custoVia + (Integer) matrizGrafo[linhaVia][coluna], via)); //cria uma nova visita que tem como destino o vértice da coluna selecionada, como custo a soma do custo da via com o custo para se chegar ao vértice e define a sua via
                } else {
                    visitaPorOutraVia.adicionarVia(via); //adiciona a nova via na lista de vias da visita existente
                }
            }

            while (coluna + 1 != matrizGrafo[1].length && !isVerticeValido((Vertice) matrizGrafo[0][coluna + 1])) {
                coluna++;
            }
        }
    }

    /**
     * Realiza visita ao vértice com menor custo e que ainda não foi visitado.
     */
    private void realizarVisita() {
        Visita visitaObtida = possiveisVisitas.poll(); //obtém a primeira visita da fila

        if (visitaObtida == null) { //caso não hajam visitas na fila
            return;
        }

        while (obterVisitaRealizada(visitaObtida.getDestino()) != null) { //enquanto a visita obtida tiver como destino um vértice já visitado
            visitaObtida = possiveisVisitas.poll(); //obtém outra visita
            if (visitaObtida == null) { //caso a visita obtida seja nula, indicando que não há mais visitas na fila
                return;
            }
        }

        visitasRealizadas.add(visitaObtida); //adiciona uma visita válida na lista de visitas realizadas
    }

    /**
     * Gera uma lista contendo os vértices que formam um caminho mínimo da
     * origem a um dado destino.
     *
     * @param destino destino para o qual se deseja obter o caminho mínimo
     * @param outroCaminhoMinimo lista que será preenchida com os vértices que
     * fazem parte do caminho mínimo
     */
    private void gerarMenoresCaminhos(Vertice destino, List<Vertice> outroCaminhoMinimo) {
        if (destino == origem) { //caso base da recursão: caso o destino recebido seja a origem do caminho
            Collections.reverse(outroCaminhoMinimo); //inverte a lista de caminho minínimo
            caminhosMinimos.add(outroCaminhoMinimo); //insere na lista de listas de caminho minimo
            return;
        }

        Visita visitaDestino = obterVisitaRealizada(destino); //obtém a visita realizada ao destino

        if (visitaDestino == null) { //caso a visita obtida seja nula
            return;
        }

        if (outroCaminhoMinimo.isEmpty()) { //caso a lista que está sendo criada esteja vazia
            outroCaminhoMinimo.add(destino); //adiciona o destino do caminho
        }

        Iterator<Vertice> iterador = visitaDestino.getVias().iterator(); //obtém o iterador para a lista de viads

        while (iterador.hasNext()) { //enquanto houver uma próxima visita
            Vertice viaObtida = iterador.next(); //obtém uma visita
            outroCaminhoMinimo.add(viaObtida); //adiciona a visita obtida no caminho mínimo
            gerarMenoresCaminhos(viaObtida, new ArrayList<>(outroCaminhoMinimo)); //chama o método novamente passando a via obtida como destino e uma novo caminho mínimo baseado no anterior

            while (outroCaminhoMinimo.contains(viaObtida)) { //enquanto existirem vértices repetidos na lista de caminho mínimo
                outroCaminhoMinimo.remove(outroCaminhoMinimo.indexOf(viaObtida)); //remove o vértice repetido
            }
        }

    }

    /**
     * Obtém o custo para se chegar a um dado vértice.
     *
     * @param destino vértice para o qual se deseja inferir o custo
     * @return custo para se chegar ao vértice informado, caso o vértice seja
     * válido; -1, caso contrário.
     */
    public int obterCustoMinimoCaminho(Vertice destino) {
        Iterator<Visita> iterador = visitasRealizadas.iterator(); //obtém o iterador para a lista de visitas realizadas
        Visita visitaAuxiliar; //referência auxiliar de visita
        int custoVia = 0; //custo da via 

        if (!isVerticeValido(destino)) { //verifica se o vértice informado é válido
            return -1;
        }

        while (iterador.hasNext()) { //enquanto há uma próxima visita na lista
            visitaAuxiliar = iterador.next(); //obtém a próxima visita

            if (visitaAuxiliar.getDestino().equals(destino)) { //verifica se o destino da visita auxiliar corresponde ao vértice informado
                custoVia = visitaAuxiliar.getCusto(); //obtém o custo para se chegar ao vértice
            }
        }

        return custoVia; //retorna o custo obtido
    }

    /**
     * Obtém a visita que possui um dado vértice como destino.
     *
     * @param vertice vértice do qual se deseja obter a visia
     * @return referência para a visita que possui o vértice informado como
     * destino, caso o vértice informado seja válido; <code>null</code>, caso
     * contrário
     */
    private Visita obterVisitaRealizada(Vertice vertice) {
        Iterator<Visita> iterador = visitasRealizadas.iterator(); //obtém o iterador da lista de visitas realizadas
        Visita visitaAuxiliar; //referência auxiliar para visita

        if (vertice == null || !isVerticeValido(vertice)) { //caso o vértice informado não seja válido
            return null;
        }

        while (iterador.hasNext()) { //enquanto há uma próxima visita
            visitaAuxiliar = iterador.next(); //obtém uma próxima visita
            if (visitaAuxiliar.getDestino().equals(vertice)) { //verifica se o vértice de destino é aquele procurado
                return visitaAuxiliar;
            }
        }

        return null;
    }

    /**
     * Obtém uma possível visita.
     *
     * @param destino destino da visita
     * @param custo custo da realização da possível visita
     * @return referência para uma possível visita; <code>null</code>, caso
     * contrário
     */
    private Visita obterPossivelVisita(Vertice destino, int custo) {
        Iterator<Visita> iterador = possiveisVisitas.iterator();
        Visita visitaAuxiliar; //referência auxiliar para visita

        if (destino == null || !isVerticeValido(destino)) { //caso o vértice informado não seja válido
            return null;
        }

        while (iterador.hasNext()) { //enquanto há uma próxima visita
            visitaAuxiliar = iterador.next(); //obtém uma próxima visita
            if (visitaAuxiliar.getDestino().equals(destino) && visitaAuxiliar.getCusto() == custo) { //verifica se o vértice de destino é aquele procurado
                return visitaAuxiliar;
            }
        }

        return null;
    }

    /**
     * Verifica se o vértice informado é válido, isto é, se ele possui conexão
     * com o resto do grafo.
     *
     * @param vertice vértice que se deseja verificar se é válido
     * @return <code>true</code>, caso o vértice seja válido;
     * <code>false</code>, caso contrário
     */
    private boolean isVerticeValido(Vertice vertice) {
        int linhaVertice = obterLinhaVertice(vertice); //obtém a linha da matriz que corresponde ao vértice que se deseja aferir a validade

        if (linhaVertice == -1) { //caso o vértice não exista na matriz
            return false;
        }

        for (int coluna = 1; coluna < matrizGrafo[1].length; coluna++) { //percorre as colunas da matriz a partir da segunda
            if (matrizGrafo[linhaVertice][coluna] != null) { //verifica se a referência contida na coluna é não nula
                return true; //retorna true indicando que o vértice é válido
            }
        }

        return false; //retorna false indicando a não validade do vértice
    }

    /**
     * Obtém o índice da linha da matriz de incidências que corresponde a um
     * dado vértice.
     *
     * @param vertice vértice para o qual se deseja obter o índice
     * @return índice da linha da matriz que corresponde ao vértice; -1, caso
     * essa linha não exista na matriz
     */
    private int obterLinhaVertice(Vertice vertice) {
        for (int linha = 1; linha < matrizGrafo[1].length; linha++) { //percorre as linhas da matriz a partir da segunda
            if (matrizGrafo[linha][0].equals(vertice)) { //verifica se o objeto contido na primeira coluna e na selecionda na iteração corresponde ao vértice procurado
                return linha; //retorna o índice da linha
            }
        }

        return -1; //retorna -1 caso a linha não seja encontrada
    }

}
