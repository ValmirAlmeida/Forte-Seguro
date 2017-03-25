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
package br.uefs.ecomp.ForteSeguro.test.util;

import br.uefs.ecomp.ForteSeguro.model.Ponto;
import br.uefs.ecomp.ForteSeguro.util.DijkstraForte;
import br.uefs.ecomp.ForteSeguro.util.GrafoPonderado;
import br.uefs.ecomp.ForteSeguro.util.Vertice;
import java.util.Iterator;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;


public class DijkstraTest {
    private DijkstraForte algoritmo; 
    
    
    @Before
    public void setUp() {
        algoritmo = new DijkstraForte(); //obtem uma inst�ncia de dijkstra
    }
    
    @Test
    public void gerarCaminhoSucessoCasosValmirIago() {
        GrafoPonderado grafo = new GrafoPonderado(); //obt�m uma inst�ncia do grafo
        
        /* Cria o conte�do que ser� armazenado nos v�rtices do grafo. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");
        Ponto pontoE = new Ponto("E");
        Ponto pontoF = new Ponto("F");
        Ponto pontoG = new Ponto("G");
        Ponto pontoH = new Ponto("H");
        Ponto pontoI = new Ponto("I");
        Ponto pontoJ = new Ponto("J");
        Ponto pontoL = new Ponto("L");
        Ponto pontoM = new Ponto("M");
        Ponto pontoN = new Ponto("N");
        Ponto pontoO = new Ponto("O");
        Ponto pontoP = new Ponto("P");
        Ponto pontoQ = new Ponto("Q");
        Ponto pontoR = new Ponto("R");
        Ponto pontoS = new Ponto("S");
        Ponto pontoT = new Ponto("T");
        
        /* Cria os v�rtices do grafo. */
        grafo.adicionarVertice(pontoA);
        grafo.adicionarVertice(pontoB);
        grafo.adicionarVertice(pontoC);
        grafo.adicionarVertice(pontoD);
        grafo.adicionarVertice(pontoE);
        grafo.adicionarVertice(pontoF);
        grafo.adicionarVertice(pontoG);
        grafo.adicionarVertice(pontoH);
        grafo.adicionarVertice(pontoI);
        grafo.adicionarVertice(pontoJ);
        grafo.adicionarVertice(pontoL);
        grafo.adicionarVertice(pontoM);
        grafo.adicionarVertice(pontoN);
        grafo.adicionarVertice(pontoO);
        grafo.adicionarVertice(pontoP);
        grafo.adicionarVertice(pontoQ);
        grafo.adicionarVertice(pontoR);
        grafo.adicionarVertice(pontoS);
        grafo.adicionarVertice(pontoT);        
        
        /* Cria as arestas do grafo. */
        grafo.adicionarAresta(pontoA, pontoB, 16); 
        grafo.adicionarAresta(pontoA, pontoC, 9);
        grafo.adicionarAresta(pontoB, pontoC, 12);
        grafo.adicionarAresta(pontoB, pontoF, 11);
        grafo.adicionarAresta(pontoB, pontoE, 11);
        grafo.adicionarAresta(pontoC, pontoD, 4);
        grafo.adicionarAresta(pontoC, pontoF, 1);
        grafo.adicionarAresta(pontoD, pontoL, 8);
        grafo.adicionarAresta(pontoD, pontoG, 1);
        grafo.adicionarAresta(pontoD, pontoH, 2);
        grafo.adicionarAresta(pontoE, pontoR, 8);
        grafo.adicionarAresta(pontoE, pontoF, 10);
        grafo.adicionarAresta(pontoF, pontoG, 2);
        grafo.adicionarAresta(pontoF, pontoN, 50);
        grafo.adicionarAresta(pontoF, pontoM, 5);
        grafo.adicionarAresta(pontoF, pontoQ, 40);
        grafo.adicionarAresta(pontoG, pontoH, 3);
        grafo.adicionarAresta(pontoI, pontoH, 4);
        grafo.adicionarAresta(pontoH, pontoO, 5);
        grafo.adicionarAresta(pontoI, pontoO, 2);
        grafo.adicionarAresta(pontoI, pontoP, 3);
        grafo.adicionarAresta(pontoI, pontoJ, 2);
        grafo.adicionarAresta(pontoJ, pontoL, 2);
        grafo.adicionarAresta(pontoM, pontoN, 21);
        grafo.adicionarAresta(pontoQ, pontoM, 10);
        grafo.adicionarAresta(pontoQ, pontoE, 2);
        grafo.adicionarAresta(pontoM, pontoE, 15);
        grafo.adicionarAresta(pontoQ, pontoR, 3);
        grafo.adicionarAresta(pontoS, pontoE, 4);
        grafo.adicionarAresta(pontoQ, pontoS, 15);
        grafo.adicionarAresta(pontoS, pontoR, 5);
        
        algoritmo.definirGrafo(grafo); //define o grafo para o algoritmo de Dijkstra
        
        //CASO 1 | ORIGEM: A - DESTINO: B
        List<List<Vertice>> todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoA), grafo.obterVertice(pontoB)); //define o destino e gera o caminho m�nimo
        assertEquals(16, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoB))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        Iterator<Vertice> iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("A", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista        

        //CASO 2 | ORIGEM: C - DESTINO: Q
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoC), grafo.obterVertice(pontoQ)); //define o destino e gera o caminho m�nimo
        assertEquals(13, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoQ))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("Q", ((Ponto) iterador.next().getConteudo()).getNome());        
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista        
        
        //CASO 3 | ORIGEM: A - DESTINO: L
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoA), grafo.obterVertice(pontoL)); //define o destino e gera o caminho m�nimo
        assertEquals(21, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoL))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(2, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a primeira lista de caminho m�nimo
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("A", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("L", ((Ponto) iterador.next().getConteudo()).getNome());     
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista            
        
        iterador = todosCaminhosMinimos.get(1).iterator(); //obt�m o iterador para a segunda lista de caminho m�nimo
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("A", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());      
        
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());            
        
        assertTrue(iterador.hasNext());
        assertEquals("L", ((Ponto) iterador.next().getConteudo()).getNome());              
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista                

        //CASO 4 | ORIGEM: N - DESTINO: E
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoN), grafo.obterVertice(pontoE)); //define o destino e gera o caminho m�nimo
        assertEquals(33, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoE))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("N", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("M", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("Q", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());        
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista
        
        //CASO 5 | ORIGEM: G - DESTINO: H
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoG), grafo.obterVertice(pontoH)); //define o destino e gera o caminho m�nimo
        assertEquals(3, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoH))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(2, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a primeira lista de caminho m�nimo
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista            
        
        iterador = todosCaminhosMinimos.get(1).iterator(); //obt�m o iterador para a segunda lista de caminho m�nimo
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista
        
        //CASO 6 | ORIGEM: P - DESTINO: Q
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoP), grafo.obterVertice(pontoQ)); //define o destino e gera o caminho m�nimo
        assertEquals(24, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoQ))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(2, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a primeira lista de caminho m�nimo
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        
        assertEquals("P", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());      
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());            
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());    
        
        assertTrue(iterador.hasNext());
        assertEquals("Q", ((Ponto) iterador.next().getConteudo()).getNome());    
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista            
        
        iterador = todosCaminhosMinimos.get(1).iterator(); //obt�m o iterador para a segunda lista de caminho m�nimo
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        
        assertEquals("P", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());     
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());     

        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());       
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());        

        assertTrue(iterador.hasNext());
        assertEquals("Q", ((Ponto) iterador.next().getConteudo()).getNome());               
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista   
        
        //CASO 7 | ORIGEM: C - DESTINO: B
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoC), grafo.obterVertice(pontoB)); //define o destino e gera o caminho m�nimo
        assertEquals(12, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoB))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(2, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a primeira lista de caminho m�nimo
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista            
        
        iterador = todosCaminhosMinimos.get(1).iterator(); //obt�m o iterador para a segunda lista de caminho m�nimo
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista     
        
        //CASO 8 | ORIGEM: R - DESTINO: J
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoR), grafo.obterVertice(pontoJ)); //define o destino e gera o caminho m�nimo
        assertEquals(26, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoJ))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(2, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a primeira lista de caminho m�nimo
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        
        assertEquals("R", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("Q", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());      
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());            
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());    
        
        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());    
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());    
        
        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());            
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista            
        
        iterador = todosCaminhosMinimos.get(1).iterator(); //obt�m o iterador para a segunda lista de caminho m�nimo
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertEquals("R", ((Ponto) iterador.next().getConteudo()).getNome());
        assertTrue(iterador.hasNext());
                
        assertEquals("Q", ((Ponto) iterador.next().getConteudo()).getNome());
        assertTrue(iterador.hasNext());
        
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        assertTrue(iterador.hasNext());

        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());            
        assertTrue(iterador.hasNext());

        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());    
        assertTrue(iterador.hasNext());

        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());    
        assertTrue(iterador.hasNext());

        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());    
        assertTrue(iterador.hasNext());

        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());    
        assertTrue(iterador.hasNext());

        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());            
        assertFalse(iterador.hasNext());       
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista      
        
        //CASO 9 | ORIGEM: S - DESTINO: N
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoS), grafo.obterVertice(pontoN)); //define o destino e gera o caminho m�nimo
        assertEquals(37, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoN))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("S", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("Q", ((Ponto) iterador.next().getConteudo()).getNome());        
        
        assertTrue(iterador.hasNext());
        assertEquals("M", ((Ponto) iterador.next().getConteudo()).getNome());          
        
        assertTrue(iterador.hasNext());
        assertEquals("N", ((Ponto) iterador.next().getConteudo()).getNome());            
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista 
        
        //CASO 10 | ORIGEM: J - DESTINO: B
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoJ), grafo.obterVertice(pontoB)); //define o destino e gera o caminho m�nimo
        assertEquals(22, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoB))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(2, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a primeira lista de caminho m�nimo
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());

        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());

        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());            

        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());    
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());    

        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista            
        
        iterador = todosCaminhosMinimos.get(1).iterator(); //obt�m o iterador para a segunda lista de caminho m�nimo
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        
        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());      
        
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());            
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());    
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());    
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());    
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista           
    }
    
    @Test
    public void gerarCaminhoSucessoCasosMatheusTaison() {
        GrafoPonderado grafo = new GrafoPonderado(); //obt�m uma inst�ncia do grafo
        
        /* Cria o conte�do que ser� armazenado nos v�rtices do grafo. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");
        Ponto pontoE = new Ponto("E");
        Ponto pontoF = new Ponto("F");
        Ponto pontoG = new Ponto("G");
        Ponto pontoH = new Ponto("H");
        Ponto pontoI = new Ponto("I");
        Ponto pontoJ = new Ponto("J");
        Ponto pontoK = new Ponto("K");
        Ponto pontoL = new Ponto("L");
        
        /* Cria os v�rtices do grafo. */
        grafo.adicionarVertice(pontoA);
        grafo.adicionarVertice(pontoB);
        grafo.adicionarVertice(pontoC);
        grafo.adicionarVertice(pontoD);
        grafo.adicionarVertice(pontoE);
        grafo.adicionarVertice(pontoF);
        grafo.adicionarVertice(pontoG);
        grafo.adicionarVertice(pontoH);
        grafo.adicionarVertice(pontoI);
        grafo.adicionarVertice(pontoJ);
        grafo.adicionarVertice(pontoK);
        grafo.adicionarVertice(pontoL);
        
        /* Cria as arestas do grafo. */
        grafo.adicionarAresta(pontoA, pontoB, 7);
        grafo.adicionarAresta(pontoA, pontoH, 10);
        grafo.adicionarAresta(pontoB, pontoC, 5);
        grafo.adicionarAresta(pontoB, pontoH, 15);
        grafo.adicionarAresta(pontoB, pontoD, 25);
        grafo.adicionarAresta(pontoB, pontoJ, 11);
        grafo.adicionarAresta(pontoC, pontoD, 30);
        grafo.adicionarAresta(pontoD, pontoE, 40);
        grafo.adicionarAresta(pontoD, pontoF, 27);
        grafo.adicionarAresta(pontoD, pontoK, 17);
        grafo.adicionarAresta(pontoE, pontoF, 5);
        grafo.adicionarAresta(pontoF, pontoL, 12);
        grafo.adicionarAresta(pontoF, pontoH, 40);
        grafo.adicionarAresta(pontoH, pontoG, 18);
        grafo.adicionarAresta(pontoF, pontoG, 20);
        grafo.adicionarAresta(pontoH, pontoI, 15);
        grafo.adicionarAresta(pontoI, pontoJ, 5);
        grafo.adicionarAresta(pontoJ, pontoK, 19);        
        grafo.adicionarAresta(pontoK, pontoL, 22);        
        grafo.adicionarAresta(pontoL, pontoI, 9);        
        
        algoritmo.definirGrafo(grafo); //define o grafo para o algoritmo de Dijkstra
        
        //CASO 1 | ORIGEM: A - DESTINO: H
        //Primeira parte - ida do ponto A ao E
        List<List<Vertice>> todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoA), grafo.obterVertice(pontoE)); //define o destino e gera o caminho m�nimo
        assertEquals(49, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoE))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        Iterator<Vertice> iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("A", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());        

        assertTrue(iterador.hasNext());
        assertEquals("L", ((Ponto) iterador.next().getConteudo()).getNome());  

        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());  

        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());  
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista

        //Segunda parte - ida do ponto E ao ponto H
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoE), grafo.obterVertice(pontoH)); //define o destino e gera o caminho m�nimo
        
        assertEquals(41, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoH))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("L", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());        

        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());  
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista        

        //CASO 2 | ORIGEM: C - DESTINO: J
        //Primeira parte - ida do ponto C ao ponto E
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoC), grafo.obterVertice(pontoE)); //define o destino e gera o caminho m�nimo
        assertEquals(47, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoE))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());        

        assertTrue(iterador.hasNext());
        assertEquals("L", ((Ponto) iterador.next().getConteudo()).getNome());  

        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());  

        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());  
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista     
        
        //Segunda parte - ida do ponto E ao ponto J
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoE), grafo.obterVertice(pontoJ)); //define o destino e gera o caminho m�nimo
        assertEquals(31, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoJ))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("L", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());        

        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());  
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista   
        
        //CASO 3 | ORIGEM: D - DESTINO: E
        //Primeira parte - ida de D a H
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoD), grafo.obterVertice(pontoH)); //define o destino e gera o caminho m�nimo
        assertEquals(40, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoH))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista      

        //Segunda parte - ida de H a E
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoH), grafo.obterVertice(pontoE)); //define o destino e gera o caminho m�nimo
        assertEquals(41, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoE))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("L", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista   
        
        //CASO 4 | ORIGEM: F - DESTINO: E
        //Primeira parte - ida de F a B
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoF), grafo.obterVertice(pontoB)); //define o destino e gera o caminho m�nimo
        assertEquals(37, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoB))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("L", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());        
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista      

        //Segunda parte - ida de B a E
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoB), grafo.obterVertice(pontoE)); //define o destino e gera o caminho m�nimo
        assertEquals(42, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoE))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("L", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());        
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista          
        
        //CASO 5 | ORIGEM: G - DESTINO: A
        //Primeira parte - ida de G a K
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoG), grafo.obterVertice(pontoK)); //define o destino e gera o caminho m�nimo
        assertEquals(54, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoK))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("L", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("K", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista      

        //Segunda parte - ida de K a A
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoK), grafo.obterVertice(pontoA)); //define o destino e gera o caminho m�nimo
        assertEquals(37, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoA))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("K", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("A", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista            
    }
    
    @Test
    public void gerarCaminhoSucessoCasosEmersonMarcus() {
        GrafoPonderado grafo = new GrafoPonderado(); //obt�m uma inst�ncia do grafo
        
       /* Cria o conte�do que ser� armazenado nos v�rtices dos grafos. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");
        Ponto pontoE = new Ponto("E");
        Ponto pontoF = new Ponto("F");
        Ponto pontoG = new Ponto("G");
        Ponto pontoH = new Ponto("H");
        
        //GRAFO 1
        /* Cria os v�rtices do grafo. */
        grafo.adicionarVertice(pontoA);
        grafo.adicionarVertice(pontoB);
        grafo.adicionarVertice(pontoC);
        grafo.adicionarVertice(pontoD);
        grafo.adicionarVertice(pontoE);
        grafo.adicionarVertice(pontoF);
        grafo.adicionarVertice(pontoG);
        grafo.adicionarVertice(pontoH);

        /* Cria as arestas do grafo. */
        grafo.adicionarAresta(pontoA, pontoH, 5); 
        grafo.adicionarAresta(pontoA, pontoE, 4);
        grafo.adicionarAresta(pontoB, pontoE, 3);
        grafo.adicionarAresta(pontoB, pontoD, 7);
        grafo.adicionarAresta(pontoB, pontoF, 6);
        grafo.adicionarAresta(pontoC, pontoG, 7);
        grafo.adicionarAresta(pontoD, pontoF, 6);
        grafo.adicionarAresta(pontoD, pontoH, 7);
        grafo.adicionarAresta(pontoE, pontoG, 3);
        
        algoritmo.definirGrafo(grafo); //define o grafo para o algoritmo de Dijkstra  

        //CASO 1 | ORIGEM: A - DESTINO: F        
        List<List<Vertice>> todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoA), grafo.obterVertice(pontoF)); //define o destino e gera o caminho m�nimo
        assertEquals(13, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoF))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        Iterator<Vertice> iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("A", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());        
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista         
        
        //CASO 2 | ORIGEM: F - DESTINO: G
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoF), grafo.obterVertice(pontoG)); //define o destino e gera o caminho m�nimo
        assertEquals(12, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoG))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());        
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista        
        
        //CASO 3 | ORIGEM: C - DESTINO: H
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoC), grafo.obterVertice(pontoH)); //define o destino e gera o caminho m�nimo
        assertEquals(19, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoH))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());

        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("A", ((Ponto) iterador.next().getConteudo()).getNome());    
        
        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());            
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista   

        //GRAFO 2
        grafo = new GrafoPonderado(); //obt�m uma nova inst�ncia de grafo
        /* Cria os v�rtices do grafo. */
        grafo.adicionarVertice(pontoA);
        grafo.adicionarVertice(pontoB);
        grafo.adicionarVertice(pontoC);
        grafo.adicionarVertice(pontoD);
        grafo.adicionarVertice(pontoE);
        grafo.adicionarVertice(pontoF);
        grafo.adicionarVertice(pontoG);
        grafo.adicionarVertice(pontoH);
        
        /* Cria as arestas do grafo. */
        grafo.adicionarAresta(pontoA, pontoH, 2); 
        grafo.adicionarAresta(pontoB, pontoH, 8);
        grafo.adicionarAresta(pontoB, pontoE, 3);
        grafo.adicionarAresta(pontoB, pontoF, 4);
        grafo.adicionarAresta(pontoC, pontoG, 4);
        grafo.adicionarAresta(pontoC, pontoH, 3);
        grafo.adicionarAresta(pontoC, pontoE, 4);
        grafo.adicionarAresta(pontoD, pontoG, 3);
        grafo.adicionarAresta(pontoE, pontoF, 6);
        grafo.adicionarAresta(pontoE, pontoG, 5);
        grafo.adicionarAresta(pontoG, pontoF, 4);

        
        algoritmo.definirGrafo(grafo); //define o grafo para o algoritmo de Dijkstra  
        
        //CASO 1 | ORIGEM: A - DESTINO: F        
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoA), grafo.obterVertice(pontoF)); //define o destino e gera o caminho m�nimo
        assertEquals(13, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoF))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("A", ((Ponto) iterador.next().getConteudo()).getNome());

        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());    
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());            
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista     
        
        //CASO 2 | ORIGEM: E - DESTINO: H
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoE), grafo.obterVertice(pontoH)); //define o destino e gera o caminho m�nimo
        assertEquals(7, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoH))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());

        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista             
        
        //CASO 3 | ORIGEM: D - DESTINO: B
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoD), grafo.obterVertice(pontoB)); //define o destino e gera o caminho m�nimo
        assertEquals(11, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoB))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(2, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());

        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());        
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista     
        
        iterador = todosCaminhosMinimos.get(1).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());

        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());        
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista    
        
    }
    
    
    @Test
    public void gerarCaminhosSucessoCasosGustavoLeonardo() {
        GrafoPonderado grafo = new GrafoPonderado(); //obt�m uma inst�ncia do grafo
        
        /* Cria o conte�do que ser� armazenado nos v�rtices do grafo. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");
        Ponto pontoE = new Ponto("E");
        Ponto pontoF = new Ponto("F");
        Ponto pontoG = new Ponto("G");
        Ponto pontoH = new Ponto("H");
        Ponto pontoI = new Ponto("I");
        Ponto pontoJ = new Ponto("J");
        Ponto pontoK = new Ponto("K");
        Ponto pontoL = new Ponto("L");
        
        /* Cria os v�rtices do grafo. */
        grafo.adicionarVertice(pontoA);
        grafo.adicionarVertice(pontoB);
        grafo.adicionarVertice(pontoC);
        grafo.adicionarVertice(pontoD);
        grafo.adicionarVertice(pontoE);
        grafo.adicionarVertice(pontoF);
        grafo.adicionarVertice(pontoG);
        grafo.adicionarVertice(pontoH);
        grafo.adicionarVertice(pontoI);
        grafo.adicionarVertice(pontoJ);
        grafo.adicionarVertice(pontoK);
        grafo.adicionarVertice(pontoL);
        
        /* Cria as arestas do grafo. */
        grafo.adicionarAresta(pontoA, pontoB, 3); 
        grafo.adicionarAresta(pontoA, pontoE, 2);
        grafo.adicionarAresta(pontoB, pontoD, 7);
        grafo.adicionarAresta(pontoC, pontoF, 2);
        grafo.adicionarAresta(pontoD, pontoE, 5);
        grafo.adicionarAresta(pontoD, pontoG, 9);
        grafo.adicionarAresta(pontoD, pontoF, 3);
        grafo.adicionarAresta(pontoE, pontoG, 4);
        grafo.adicionarAresta(pontoF, pontoG, 6);
        grafo.adicionarAresta(pontoG, pontoI, 1);
        grafo.adicionarAresta(pontoH, pontoJ, 7);
        grafo.adicionarAresta(pontoJ, pontoI, 3);
        grafo.adicionarAresta(pontoJ, pontoK, 9);
        grafo.adicionarAresta(pontoJ, pontoH, 3);
        
        algoritmo.definirGrafo(grafo); //define o grafo para o algoritmo de Dijkstra  
        
        //CASO 1 | ORIGEM: A - DESTINO: H
        //Primeira parte - ida do ponto A ao G
        List<List<Vertice>> todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoA), grafo.obterVertice(pontoG)); //define o destino e gera o caminho m�nimo
        assertEquals(6, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoG))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        Iterator<Vertice> iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("A", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista

        //Segunda parte - ida do ponto G ao ponto H
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoG), grafo.obterVertice(pontoH)); //define o destino e gera o caminho m�nimo
        assertEquals(11, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoH))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());

        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());  
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista    
        
        //CASO 2 | ORIGEM: B - DESTINO: C
        //Primeira parte - ida do ponto B ao G
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoB), grafo.obterVertice(pontoG)); //define o destino e gera o caminho m�nimo
        assertEquals(9, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoG))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("A", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());        
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista

        //Segunda parte - ida do ponto G ao ponto C
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoG), grafo.obterVertice(pontoC)); //define o destino e gera o caminho m�nimo
        assertEquals(8, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoC))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());

        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista        
        
        //CASO 3 | ORIGEM: D - DESTINO: B
        //Primeira parte - ida do ponto D ao G
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoD), grafo.obterVertice(pontoG)); //define o destino e gera o caminho m�nimo
        assertEquals(9, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoG))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(3, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista
        
        iterador = todosCaminhosMinimos.get(1).iterator(); //obt�m o iterador para a segunda lista de caminho m�nimo
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());    
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista       
        
        iterador = todosCaminhosMinimos.get(2).iterator(); //obt�m o iterador para a segunda lista de caminho m�nimo
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista              

        //Segunda parte - ida do ponto G ao ponto B
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoG), grafo.obterVertice(pontoB)); //define o destino e gera o caminho m�nimo
        assertEquals(9, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoB))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("A", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());        

        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista  
        
        //CASO 4 | ORIGEM: K - DESTINO: A
        //Primeira parte - ida do ponto K ao F
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoK), grafo.obterVertice(pontoF)); //define o destino e gera o caminho m�nimo
        assertEquals(19, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoF))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("K", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());

        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());        
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista

        //Segunda parte - ida do ponto F ao ponto A
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoF), grafo.obterVertice(pontoA)); //define o destino e gera o caminho m�nimo
        assertEquals(10, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoA))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());

        assertTrue(iterador.hasNext());
        assertEquals("A", ((Ponto) iterador.next().getConteudo()).getNome());  
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista      
        
        //CASO 5 | ORIGEM: C - DESTINO: H
        //Primeira parte - ida do ponto C ao K
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoC), grafo.obterVertice(pontoK)); //define o destino e gera o caminho m�nimo
        assertEquals(21, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoK))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());

        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());     
        
        assertTrue(iterador.hasNext());
        assertEquals("K", ((Ponto) iterador.next().getConteudo()).getNome());    
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista

        //Segunda parte - ida do ponto K ao ponto H
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoK), grafo.obterVertice(pontoH)); //define o destino e gera o caminho m�nimo
        assertEquals(16, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoH))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("K", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista       
        
        //CASO 6 | ORIGEM: H - DESTINO: K
        //Primeira parte - ida do ponto H ao I
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoH), grafo.obterVertice(pontoI)); //define o destino e gera o caminho m�nimo
        assertEquals(10, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoI))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista

        //Segunda parte - ida do ponto I ao ponto K
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoI), grafo.obterVertice(pontoK)); //define o destino e gera o caminho m�nimo
        assertEquals(12, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoK))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("K", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista    
        
        //CASO 7 | ORIGEM: F - DESTINO: I
        //Primeira parte - ida do ponto F ao E
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoF), grafo.obterVertice(pontoE)); //define o destino e gera o caminho m�nimo
        assertEquals(8, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoE))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista

        //Segunda parte - ida do ponto E ao ponto I
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoE), grafo.obterVertice(pontoI)); //define o destino e gera o caminho m�nimo
        assertEquals(5, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoI))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista         
        
        //CASO 8 | ORIGEM: I - DESTINO: B
        //Primeira parte - ida do ponto I ao C
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoI), grafo.obterVertice(pontoC)); //define o destino e gera o caminho m�nimo
        assertEquals(9, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoC))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());        
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista

        //Segunda parte - ida do ponto C ao ponto B
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoC), grafo.obterVertice(pontoB)); //define o destino e gera o caminho m�nimo
        assertEquals(12, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoB))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());        
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista      
        
        //CASO 9 | ORIGEM: D - DESTINO: A
        //Primeira parte - ida do ponto D ao B
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoD), grafo.obterVertice(pontoB)); //define o destino e gera o caminho m�nimo
        assertEquals(7, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoB))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista

        //Segunda parte - ida do ponto B ao ponto A
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoB), grafo.obterVertice(pontoA)); //define o destino e gera o caminho m�nimo
        assertEquals(3, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoA))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("B", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("A", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista    
        
        //CASO 10 | ORIGEM: E - DESTINO: C
        //Primeira parte - ida do ponto E ao C
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoE), grafo.obterVertice(pontoC)); //define o destino e gera o caminho m�nimo
        assertEquals(10, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoC))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("E", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("D", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());        
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista

        //Segunda parte - ida do ponto C ao ponto H
        todosCaminhosMinimos = algoritmo.executar(grafo.obterVertice(pontoC), grafo.obterVertice(pontoH)); //define o destino e gera o caminho m�nimo
        assertEquals(19, algoritmo.obterCustoMinimoCaminho(grafo.obterVertice(pontoH))); //verifica se o custo m�nimo obtido pro destino � correto
        assertEquals(1, todosCaminhosMinimos.size()); //verifica se a quantidade de caminhos m�nimos obtida est� correta
        
        iterador = todosCaminhosMinimos.get(0).iterator(); //obt�m o iterador para a lista de caminhos m�nimos
        
        /* Verifica se a lista de caminho m�nimo cont�m os v�rtices na ordem correta. */
        assertTrue(iterador.hasNext());
        assertEquals("C", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("F", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("G", ((Ponto) iterador.next().getConteudo()).getNome());
        
        assertTrue(iterador.hasNext());
        assertEquals("I", ((Ponto) iterador.next().getConteudo()).getNome());     
        
        assertTrue(iterador.hasNext());
        assertEquals("J", ((Ponto) iterador.next().getConteudo()).getNome()); 
        
        assertTrue(iterador.hasNext());
        assertEquals("H", ((Ponto) iterador.next().getConteudo()).getNome());            
        
        assertFalse(iterador.hasNext()); //certifica-se de que n�o h� um pr�ximo na lista             
        
        
    }
    
    
}
