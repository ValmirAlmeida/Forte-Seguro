package br.uefs.ecomp.ForteSeguro.util;

import br.uefs.ecomp.ForteSeguro.model.Ponto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Classe na qual � implementada uma vers�o modificada do algoritmo de Dijkstra.
 *
 * @author Valmir Vinicius
 * @author Iago Almeida
 */
public class DijkstraForte {

    /* Matriz que representa o grafo no qual ser� aplicado o algoritmo. */
    private Object[][] matrizGrafo;

    /* Lista de visitas que j� foram realizadas. */
    public List<Visita> visitasRealizadas;

    /* Fila de prioridade contendo poss�veis visitas. */
    private PriorityQueue<Visita> possiveisVisitas;

    /* V�rtice de in�cio do caminho. */
    private Vertice origem;

    /* Lista contedo os v�rtices que foram o menor caminho.*/
    public List<List<Vertice>> caminhosMinimos;

    /**
     * Obt�m uma inst�ncia do algoritmo de Dijkstra.
     */
    public DijkstraForte() {
        visitasRealizadas = new ArrayList<>(); //obt�m uma inst�ncia de ArrayLnnmnnist
        possiveisVisitas = new PriorityQueue<>(); //obt�m uma inst�ncia de PriorityQueue
    }

    /**
     * Define o grafo no qual ser� aplicado o algoritmo.
     *
     * @param grafo grafo no qual ser� aplicado o algoritmo
     */
    public void definirGrafo(GrafoPonderado grafo) {
        matrizGrafo = grafo.obterMatriz(); //obt�m a representa��o em matriz do grafo
    }

    /**
     * Executa o algoritmo para c�lculo de menor caminho.
     *
     * @param origem origem do caminho
     * @param destino destino do caminho
     * @return lista contendo todas as listas que v�rtices que resultam nos
     * menores caminhos poss�veis
     */
    public List<List<Vertice>> executar(Vertice origem, Vertice destino) {
        visitasRealizadas = new ArrayList<>(); //obt�m uma inst�ncia de ArrayList
        possiveisVisitas = new PriorityQueue<>(); //obt�m uma inst�ncia de PriorityQueue            
        this.origem = origem; //define a nova origem
        
        /* Realiza verifica��es inicias para garantir que os v�rtices recebidos s�o v�lidos para execu��o do algoritmo. */
        if (origem == null || destino == null || destino == origem || !isVerticeValido(origem) || !isVerticeValido(destino)) {
            return null;
        }
        
        calcularTodosPossiveisCaminhosMinimos(); //calcula os caminhos m�nimos poss�veis
        

        caminhosMinimos = new ArrayList<>(); //obt�m uma nova inst�ncia de ArrayList

        gerarMenoresCaminhos(destino, new ArrayList<>()); //gera os menores caminhos com o destino definido

        return caminhosMinimos; //retorna a lista gerada
    }

    /**
     * Verifica se todos os v�rtices v�lidos foram visitados.
     * @return <code>true</code>, caso todos os v�rtices v�lidos tenham sido visitados; <code>false</code>, caso contr�rio
     */
    private boolean isTodosVisitados() {
        for (int i = 1; i < matrizGrafo.length; i++) { //percorre as colunas da matriz a partir da segunda
            if (isVerticeValido(((Vertice) matrizGrafo[0][i]))) { //verifica se o v�rtice � v�lido
                if (obterVisitaRealizada((Vertice) matrizGrafo[0][i]) == null) { //verifica se existe uma visita com o v�rtice
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Realiza o c�lculo de todos os m�nimos caminhos a partir da origem
     * fornecida.
     */
    private void calcularTodosPossiveisCaminhosMinimos() {
        if (matrizGrafo == null && origem == null && !isVerticeValido(origem)) { //verifica se foi definido um grafo e uma origem e se essa origem � v�lida
            return;
        }

        visitasRealizadas.add(new Visita(origem, 0, null)); //cria uma nova visita correspondente a origem e a adiciona na lista de visitas realizadas
        descobrirPossiveisVisitas(origem); //descobre as poss�veis visitas que podem ser feitas a partir da origem
        realizarVisita(); //realiza uma visita a partir das poss�veis visitas que partem da origem

        while (true) { //enquanto todos os v�rtices do grafo n�o forem visitados
            descobrirPossiveisVisitas(visitasRealizadas.get(visitasRealizadas.size() - 1).getDestino()); //descobre as poss�veis visitas que partem do destino do �ltimo v�rtice visitado
            realizarVisita(); //realiza uma a melhor visita partindo do destino da �ltima visita realizada

            if (possiveisVisitas.peek() == null) { //caso a lista de poss�veis visitas esteja vazia
                break;
            }
            if (isTodosVisitados()) { //caso todos os v�rtices j� tenham sido visitados
                break;
            }
        }

        visitasRealizadas.remove(0); //realiza a primeira visita da lista de visitas realizadas, essa visita n�o tem significa��o para o resultado final, pois corresponde a uma visita que tem como destino a origem e n�o possui via 

    }

    /**
     * Descobre as poss�veis visitas que podem ser realizadas a partir de um
     * dado v�rtice que ser� a via.
     *
     * @param via v�rtice a partir do qual ser�o descobertas as poss�veis
     * visitas
     */
    private void descobrirPossiveisVisitas(Vertice via) {
        int linhaVia = obterLinhaVertice(via); //obt�m a linha da matriz de incid�ncia que corresponde a via recebida

        if (linhaVia == -1) { //caso a linha que corresponde a via n�o seja encontrada
            return;
        }

        int custoVia = obterCustoMinimoCaminho(via); //obt�m o custo para se chegar a via

        for (int coluna = 1; coluna < matrizGrafo[1].length; coluna++) { //obt�m valores que correspondem aos ind�ces das colunas da matriz de incid�ncia, a partir da segunda

            if (matrizGrafo[linhaVia][coluna] != null) {
                Visita visitaPorOutraVia = obterPossivelVisita((Vertice) matrizGrafo[0][coluna], custoVia + (Integer) matrizGrafo[linhaVia][coluna]); //refer�ncia para uma visita j� realizada

                if (visitaPorOutraVia == null) { //caso n�o tenha sido feita uma visita por outra via
                    possiveisVisitas.add(new Visita((Vertice) matrizGrafo[0][coluna], custoVia + (Integer) matrizGrafo[linhaVia][coluna], via)); //cria uma nova visita que tem como destino o v�rtice da coluna selecionada, como custo a soma do custo da via com o custo para se chegar ao v�rtice e define a sua via
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
     * Realiza visita ao v�rtice com menor custo e que ainda n�o foi visitado.
     */
    private void realizarVisita() {
        Visita visitaObtida = possiveisVisitas.poll(); //obt�m a primeira visita da fila

        if (visitaObtida == null) { //caso n�o hajam visitas na fila
            return;
        }

        while (obterVisitaRealizada(visitaObtida.getDestino()) != null) { //enquanto a visita obtida tiver como destino um v�rtice j� visitado
            visitaObtida = possiveisVisitas.poll(); //obt�m outra visita
            if (visitaObtida == null) { //caso a visita obtida seja nula, indicando que n�o h� mais visitas na fila
                return;
            }
        }

        visitasRealizadas.add(visitaObtida); //adiciona uma visita v�lida na lista de visitas realizadas
    }

    /**
     * Gera uma lista contendo os v�rtices que formam um caminho m�nimo da
     * origem a um dado destino.
     *
     * @param destino destino para o qual se deseja obter o caminho m�nimo
     * @param outroCaminhoMinimo lista que ser� preenchida com os v�rtices que
     * fazem parte do caminho m�nimo
     */
    private void gerarMenoresCaminhos(Vertice destino, List<Vertice> outroCaminhoMinimo) {
        if (destino == origem) { //caso base da recurs�o: caso o destino recebido seja a origem do caminho
            Collections.reverse(outroCaminhoMinimo); //inverte a lista de caminho min�nimo
            caminhosMinimos.add(outroCaminhoMinimo); //insere na lista de listas de caminho minimo
            return;
        }

        Visita visitaDestino = obterVisitaRealizada(destino); //obt�m a visita realizada ao destino

        if (visitaDestino == null) { //caso a visita obtida seja nula
            return;
        }

        if (outroCaminhoMinimo.isEmpty()) { //caso a lista que est� sendo criada esteja vazia
            outroCaminhoMinimo.add(destino); //adiciona o destino do caminho
        }

        Iterator<Vertice> iterador = visitaDestino.getVias().iterator(); //obt�m o iterador para a lista de viads

        while (iterador.hasNext()) { //enquanto houver uma pr�xima visita
            Vertice viaObtida = iterador.next(); //obt�m uma visita
            outroCaminhoMinimo.add(viaObtida); //adiciona a visita obtida no caminho m�nimo
            gerarMenoresCaminhos(viaObtida, new ArrayList<>(outroCaminhoMinimo)); //chama o m�todo novamente passando a via obtida como destino e uma novo caminho m�nimo baseado no anterior

            while (outroCaminhoMinimo.contains(viaObtida)) { //enquanto existirem v�rtices repetidos na lista de caminho m�nimo
                outroCaminhoMinimo.remove(outroCaminhoMinimo.indexOf(viaObtida)); //remove o v�rtice repetido
            }
        }

    }

    /**
     * Obt�m o custo para se chegar a um dado v�rtice.
     *
     * @param destino v�rtice para o qual se deseja inferir o custo
     * @return custo para se chegar ao v�rtice informado, caso o v�rtice seja
     * v�lido; -1, caso contr�rio.
     */
    public int obterCustoMinimoCaminho(Vertice destino) {
        Iterator<Visita> iterador = visitasRealizadas.iterator(); //obt�m o iterador para a lista de visitas realizadas
        Visita visitaAuxiliar; //refer�ncia auxiliar de visita
        int custoVia = 0; //custo da via 

        if (!isVerticeValido(destino)) { //verifica se o v�rtice informado � v�lido
            return -1;
        }

        while (iterador.hasNext()) { //enquanto h� uma pr�xima visita na lista
            visitaAuxiliar = iterador.next(); //obt�m a pr�xima visita

            if (visitaAuxiliar.getDestino().equals(destino)) { //verifica se o destino da visita auxiliar corresponde ao v�rtice informado
                custoVia = visitaAuxiliar.getCusto(); //obt�m o custo para se chegar ao v�rtice
            }
        }

        return custoVia; //retorna o custo obtido
    }

    /**
     * Obt�m a visita que possui um dado v�rtice como destino.
     *
     * @param vertice v�rtice do qual se deseja obter a visia
     * @return refer�ncia para a visita que possui o v�rtice informado como
     * destino, caso o v�rtice informado seja v�lido; <code>null</code>, caso
     * contr�rio
     */
    private Visita obterVisitaRealizada(Vertice vertice) {
        Iterator<Visita> iterador = visitasRealizadas.iterator(); //obt�m o iterador da lista de visitas realizadas
        Visita visitaAuxiliar; //refer�ncia auxiliar para visita

        if (vertice == null || !isVerticeValido(vertice)) { //caso o v�rtice informado n�o seja v�lido
            return null;
        }

        while (iterador.hasNext()) { //enquanto h� uma pr�xima visita
            visitaAuxiliar = iterador.next(); //obt�m uma pr�xima visita
            if (visitaAuxiliar.getDestino().equals(vertice)) { //verifica se o v�rtice de destino � aquele procurado
                return visitaAuxiliar;
            }
        }

        return null;
    }

    /**
     * Obt�m uma poss�vel visita.
     *
     * @param destino destino da visita
     * @param custo custo da realiza��o da poss�vel visita
     * @return refer�ncia para uma poss�vel visita; <code>null</code>, caso
     * contr�rio
     */
    private Visita obterPossivelVisita(Vertice destino, int custo) {
        Iterator<Visita> iterador = possiveisVisitas.iterator();
        Visita visitaAuxiliar; //refer�ncia auxiliar para visita

        if (destino == null || !isVerticeValido(destino)) { //caso o v�rtice informado n�o seja v�lido
            return null;
        }

        while (iterador.hasNext()) { //enquanto h� uma pr�xima visita
            visitaAuxiliar = iterador.next(); //obt�m uma pr�xima visita
            if (visitaAuxiliar.getDestino().equals(destino) && visitaAuxiliar.getCusto() == custo) { //verifica se o v�rtice de destino � aquele procurado
                return visitaAuxiliar;
            }
        }

        return null;
    }

    /**
     * Verifica se o v�rtice informado � v�lido, isto �, se ele possui conex�o
     * com o resto do grafo.
     *
     * @param vertice v�rtice que se deseja verificar se � v�lido
     * @return <code>true</code>, caso o v�rtice seja v�lido;
     * <code>false</code>, caso contr�rio
     */
    private boolean isVerticeValido(Vertice vertice) {
        int linhaVertice = obterLinhaVertice(vertice); //obt�m a linha da matriz que corresponde ao v�rtice que se deseja aferir a validade

        if (linhaVertice == -1) { //caso o v�rtice n�o exista na matriz
            return false;
        }

        for (int coluna = 1; coluna < matrizGrafo[1].length; coluna++) { //percorre as colunas da matriz a partir da segunda
            if (matrizGrafo[linhaVertice][coluna] != null) { //verifica se a refer�ncia contida na coluna � n�o nula
                return true; //retorna true indicando que o v�rtice � v�lido
            }
        }

        return false; //retorna false indicando a n�o validade do v�rtice
    }

    /**
     * Obt�m o �ndice da linha da matriz de incid�ncias que corresponde a um
     * dado v�rtice.
     *
     * @param vertice v�rtice para o qual se deseja obter o �ndice
     * @return �ndice da linha da matriz que corresponde ao v�rtice; -1, caso
     * essa linha n�o exista na matriz
     */
    private int obterLinhaVertice(Vertice vertice) {
        for (int linha = 1; linha < matrizGrafo[1].length; linha++) { //percorre as linhas da matriz a partir da segunda
            if (matrizGrafo[linha][0].equals(vertice)) { //verifica se o objeto contido na primeira coluna e na selecionda na itera��o corresponde ao v�rtice procurado
                return linha; //retorna o �ndice da linha
            }
        }

        return -1; //retorna -1 caso a linha n�o seja encontrada
    }

}
