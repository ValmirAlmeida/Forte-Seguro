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
import br.uefs.ecomp.ForteSeguro.util.Aresta;
import br.uefs.ecomp.ForteSeguro.util.GrafoPonderado;
import br.uefs.ecomp.ForteSeguro.util.Vertice;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GrafoPonderadoTest {
    
    private GrafoPonderado grafo;
    
    @Before
    public void setUp() {
        grafo = new GrafoPonderado();
    }
    
    @Test
    public void testAdicionarVerticeSucesso() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));
        assertTrue(grafo.adicionarVertice(pontoC));
        assertTrue(grafo.adicionarVertice(pontoD));
        
        
        /* Verifica se os v�rtices foram realmente cadastrados no grafo. */
        Vertice vertice = grafo.obterVertice(pontoA);
        assertNotNull(vertice);
        assertEquals(pontoA, vertice.getConteudo());
        
        vertice = grafo.obterVertice(pontoB);
        assertNotNull(vertice);
        assertEquals(pontoB, vertice.getConteudo());
        
        vertice = grafo.obterVertice(pontoC);
        assertNotNull(vertice);
        assertEquals(pontoC, vertice.getConteudo());

        vertice = grafo.obterVertice(pontoD);
        assertNotNull(vertice);
        assertEquals(pontoD, vertice.getConteudo());    
    }
    
    @Test
    public void testAdicionarVerticeNulo()  {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));
        assertTrue(grafo.adicionarVertice(pontoC));
        assertTrue(grafo.adicionarVertice(pontoD));        
        assertFalse(grafo.adicionarVertice(null)); //tenta adicionar um v�rtice nulo
    }

    @Test
    public void testRemoverVerticeSucesso() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e cria os v�rtices. */
        grafo.adicionarVertice(pontoA);
        grafo.adicionarVertice(pontoB);
        grafo.adicionarVertice(pontoC);
        grafo.adicionarVertice(pontoD);
        
        /* Realiza as remo��es e verifica se ela foi bem sucedida. */
        assertEquals(pontoA, grafo.removerVertice(pontoA));
        assertNull(grafo.obterVertice(pontoA));

        assertEquals(pontoB, grafo.removerVertice(pontoB));
        assertNull(grafo.obterVertice(pontoB));

        assertEquals(pontoC, grafo.removerVertice(pontoC));
        assertNull(grafo.obterVertice(pontoC));

        assertEquals(pontoD, grafo.removerVertice(pontoD));
        assertNull(grafo.obterVertice(pontoD));        
    }
    
    @Test
    public void testRemoverVerticeNaoCadastrado() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e cria os v�rtices. */
        grafo.adicionarVertice(pontoA);
        grafo.adicionarVertice(pontoB);
        grafo.adicionarVertice(pontoC);
        
        /* Cria as arestas do grafo. */
        grafo.adicionarAresta(pontoA, pontoB, 1); 
        grafo.adicionarAresta(pontoB, pontoC, 2);
        grafo.adicionarAresta(pontoC, pontoD, 3);
        grafo.adicionarAresta(pontoD, pontoA, 4);              
        
        assertNull(grafo.removerVertice(pontoD)); //certifica-se de que a refer�ncia retornada � nula
    }
    
    @Test
    public void testRemoverVerticeNulo() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e cria os v�rtices. */
        grafo.adicionarVertice(pontoA);
        grafo.adicionarVertice(pontoB);
        grafo.adicionarVertice(pontoC);
        grafo.adicionarVertice(pontoD);

        assertNull(grafo.removerVertice(null)); //certifica-se de que a refer�ncia retornada � nula
    }

    @Test
    public void testAdicionarArestaSucesso() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));
        assertTrue(grafo.adicionarVertice(pontoC));
        assertTrue(grafo.adicionarVertice(pontoD));
        
        /* Cria as arestas do grafo e verifica se o processo ocorreu corretamente. */
        assertTrue(grafo.adicionarAresta(pontoA, pontoB, 1)); 
        assertTrue(grafo.adicionarAresta(pontoB, pontoC, 2));
        assertTrue(grafo.adicionarAresta(pontoC, pontoD, 3));
        assertTrue(grafo.adicionarAresta(pontoD, pontoA, 4));     
        
        /* Tenta obter as arestas cadastradas e verifica se elas est�o conforme o cadastro.*/
        Aresta aresta = grafo.obterAresta(pontoA, pontoB);
        assertNotNull(aresta);
        assertEquals(pontoA, aresta.getExtremidades()[0].getConteudo());
        assertEquals(pontoB, aresta.getExtremidades()[1].getConteudo());
        assertEquals(1, aresta.getPeso());
        
        aresta = grafo.obterAresta(pontoB, pontoC);
        assertNotNull(aresta);
        assertEquals(pontoB, aresta.getExtremidades()[0].getConteudo());
        assertEquals(pontoC, aresta.getExtremidades()[1].getConteudo());
        assertEquals(2, aresta.getPeso());

        aresta = grafo.obterAresta(pontoC, pontoD);
        assertNotNull(aresta);
        assertEquals(pontoC, aresta.getExtremidades()[0].getConteudo());
        assertEquals(pontoD, aresta.getExtremidades()[1].getConteudo());
        assertEquals(3, aresta.getPeso());

        aresta = grafo.obterAresta(pontoD, pontoA);
        assertNotNull(aresta);
        assertEquals(pontoD, aresta.getExtremidades()[0].getConteudo());
        assertEquals(pontoA, aresta.getExtremidades()[1].getConteudo());
        assertEquals(4, aresta.getPeso());        
    }
    
    @Test
    public void testAdicionarArestaVerticeNaoCadastrado() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));
        assertTrue(grafo.adicionarVertice(pontoC));
        
        /* Cria as arestas do grafo e verifica se o processo ocorreu conforme esperado. */
        assertTrue(grafo.adicionarAresta(pontoA, pontoB, 1)); 
        assertTrue(grafo.adicionarAresta(pontoB, pontoC, 2));
        assertFalse(grafo.adicionarAresta(pontoC, pontoD, 3));
        assertFalse(grafo.adicionarAresta(pontoD, pontoA, 4));     
        
        /* Tenta obter as arestas cadastradas e verifica se elas est�o conforme o cadastro.*/
        Aresta aresta = grafo.obterAresta(pontoA, pontoB);
        assertEquals(pontoA, aresta.getExtremidades()[0].getConteudo());
        assertEquals(pontoB, aresta.getExtremidades()[1].getConteudo());
        assertEquals(1, aresta.getPeso());
        
        aresta = grafo.obterAresta(pontoB, pontoC);
        assertEquals(pontoB, aresta.getExtremidades()[0].getConteudo());
        assertEquals(pontoC, aresta.getExtremidades()[1].getConteudo());
        assertEquals(2, aresta.getPeso());

        aresta = grafo.obterAresta(pontoC, pontoD);
        assertNull(aresta);

        aresta = grafo.obterAresta(pontoD, pontoA);
        assertNull(aresta);
    }    
    
    @Test
    public void testAdicionarArestaConteudoNulo() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));
        assertTrue(grafo.adicionarVertice(pontoC));
        assertTrue(grafo.adicionarVertice(pontoD));
        
        /* Cria as arestas do grafo e verifica se o processo ocorreu corretamente. */
        assertFalse(grafo.adicionarAresta(null, pontoB, 1)); 
        assertFalse(grafo.adicionarAresta(pontoB, null, 2));
        assertTrue(grafo.adicionarAresta(pontoC, pontoD, 3));
        assertTrue(grafo.adicionarAresta(pontoD, pontoA, 4));        
        
        /* Tenta obter as arestas cadastradas e verifica se elas est�o conforme o cadastro.*/
        Aresta aresta = grafo.obterAresta(pontoA, pontoB);
        assertNull(aresta);
        
        aresta = grafo.obterAresta(pontoB, pontoC);
        assertNull(aresta);

        aresta = grafo.obterAresta(pontoC, pontoD);
        assertNotNull(aresta);
        assertEquals(pontoC, aresta.getExtremidades()[0].getConteudo());
        assertEquals(pontoD, aresta.getExtremidades()[1].getConteudo());
        assertEquals(3, aresta.getPeso());

        aresta = grafo.obterAresta(pontoD, pontoA);
        assertNotNull(aresta);
        assertEquals(pontoD, aresta.getExtremidades()[0].getConteudo());
        assertEquals(pontoA, aresta.getExtremidades()[1].getConteudo());
        assertEquals(4, aresta.getPeso());             
    }

    @Test
    public void testRemoverArestaSuceso() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));
        assertTrue(grafo.adicionarVertice(pontoC));
        assertTrue(grafo.adicionarVertice(pontoD));
        
        /* Cria as arestas do grafo e verifica se o processo ocorreu corretamente. */
        assertTrue(grafo.adicionarAresta(pontoA, pontoB, 1)); 
        assertTrue(grafo.adicionarAresta(pontoB, pontoC, 2));
        assertTrue(grafo.adicionarAresta(pontoC, pontoD, 3));
        assertTrue(grafo.adicionarAresta(pontoD, pontoA, 4)); 
        
        /* Tenta realizar as remo��es e verifica se o processo ocorreu corretamente. */
        assertEquals(1, grafo.removerAresta(pontoA, pontoB));
        assertNull(grafo.obterAresta(pontoA, pontoB));
        
        assertEquals(1, grafo.removerAresta(pontoB, pontoC));
        assertNull(grafo.obterAresta(pontoB, pontoC));

        assertEquals(1, grafo.removerAresta(pontoC, pontoD));
        assertNull(grafo.obterAresta(pontoC, pontoD));

        assertEquals(1, grafo.removerAresta(pontoD, pontoA));
        assertNull(grafo.obterAresta(pontoD, pontoA));
    }
    
    @Test
    public void testRemoverArestasComExtremidadeNaoCadastrada() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));
        assertTrue(grafo.adicionarVertice(pontoC));
        
        /* Cria as arestas do grafo e verifica se o processo ocorreu corretamente. */
        assertTrue(grafo.adicionarAresta(pontoA, pontoB, 1)); 
        assertTrue(grafo.adicionarAresta(pontoB, pontoC, 2));
        assertFalse(grafo.adicionarAresta(pontoC, pontoD, 3));
        assertFalse(grafo.adicionarAresta(pontoD, pontoA, 4));   
        
        /* Tenta realizar as remo��es e verifica se o processo ocorreu conforme esperado. */
        assertEquals(1, grafo.removerAresta(pontoA, pontoB));
        assertNull(grafo.obterAresta(pontoA, pontoB));
        
        assertEquals(1, grafo.removerAresta(pontoB, pontoC));
        assertNull(grafo.obterAresta(pontoB, pontoC));
        
        assertEquals(-1, grafo.removerAresta(pontoC, pontoD));
        assertEquals(-1, grafo.removerAresta(pontoD, pontoA));
    }
    
    @Test
    public void testRemoverArestaNaoCadastrada() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));
        assertTrue(grafo.adicionarVertice(pontoC));
        assertTrue(grafo.adicionarVertice(pontoD));
        
        /* Cria as arestas do grafo e verifica se o processo ocorreu corretamente. */
        assertTrue(grafo.adicionarAresta(pontoA, pontoB, 1)); 
        assertTrue(grafo.adicionarAresta(pontoB, pontoC, 2));

        /* Tenta realizar as remo��es e verifica se o processo ocorreu conforme esperado. */
        assertEquals(1, grafo.removerAresta(pontoA, pontoB));
        assertNull(grafo.obterAresta(pontoA, pontoB));
        
        assertEquals(1, grafo.removerAresta(pontoB, pontoC));
        assertNull(grafo.obterAresta(pontoB, pontoC));
        
        assertEquals(0, grafo.removerAresta(pontoC, pontoD));
        assertEquals(0, grafo.removerAresta(pontoD, pontoA));        
    }
    
    @Test
    public void testObterVerticeSucesso() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));
        assertTrue(grafo.adicionarVertice(pontoC));
        assertTrue(grafo.adicionarVertice(pontoD));

        /* Cria as arestas do grafo e verifica se o processo ocorreu corretamente. */
        assertTrue(grafo.adicionarAresta(pontoA, pontoB, 1)); 
        assertTrue(grafo.adicionarAresta(pontoB, pontoC, 2));
        assertTrue(grafo.adicionarAresta(pontoC, pontoD, 3));
        assertTrue(grafo.adicionarAresta(pontoD, pontoA, 4));         
        
        /* Verifica se os v�rtices obtidos correspondem ao esperado. */
        Vertice vertice = grafo.obterVertice(pontoA);
        assertNotNull(vertice);
        assertEquals(pontoA, vertice.getConteudo());
        
        vertice = grafo.obterVertice(pontoB);
        assertNotNull(vertice);
        assertEquals(pontoB, vertice.getConteudo());
        
        vertice = grafo.obterVertice(pontoC);
        assertNotNull(vertice);
        assertEquals(pontoC, vertice.getConteudo());

        vertice = grafo.obterVertice(pontoD);
        assertNotNull(vertice);
        assertEquals(pontoD, vertice.getConteudo());        
        
    }
    
    @Test
    public void testobterVerticeNulo() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));
        assertTrue(grafo.adicionarVertice(pontoC));
        assertTrue(grafo.adicionarVertice(pontoD));

        /* Cria as arestas do grafo e verifica se o processo ocorreu corretamente. */
        assertTrue(grafo.adicionarAresta(pontoA, pontoB, 1)); 
        assertTrue(grafo.adicionarAresta(pontoB, pontoC, 2));
        assertTrue(grafo.adicionarAresta(pontoC, pontoD, 3));
        assertTrue(grafo.adicionarAresta(pontoD, pontoA, 4));         
        
        /* Verifica se os v�rtices obtidos correspondem ao esperado. */
        assertNull(grafo.obterVertice(null));
    }

    @Test
    public void testObterArestaSucesso() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));
        assertTrue(grafo.adicionarVertice(pontoC));
        assertTrue(grafo.adicionarVertice(pontoD));

        /* Cria as arestas do grafo e verifica se o processo ocorreu corretamente. */
        assertTrue(grafo.adicionarAresta(pontoA, pontoB, 1)); 
        assertTrue(grafo.adicionarAresta(pontoB, pontoC, 2));
        assertTrue(grafo.adicionarAresta(pontoC, pontoD, 3));
        assertTrue(grafo.adicionarAresta(pontoD, pontoA, 4));    
        
        /* Tenta obter as arestas cadastradas e verifica se elas est�o conforme o esperado.*/
        Aresta aresta = grafo.obterAresta(pontoA, pontoB);
        assertNotNull(aresta);
        assertEquals(pontoA, aresta.getExtremidades()[0].getConteudo());
        assertEquals(pontoB, aresta.getExtremidades()[1].getConteudo());
        assertEquals(1, aresta.getPeso());
        
        aresta = grafo.obterAresta(pontoB, pontoC);
        assertNotNull(aresta);
        assertEquals(pontoB, aresta.getExtremidades()[0].getConteudo());
        assertEquals(pontoC, aresta.getExtremidades()[1].getConteudo());
        assertEquals(2, aresta.getPeso());

        aresta = grafo.obterAresta(pontoC, pontoD);
        assertNotNull(aresta);
        assertEquals(pontoC, aresta.getExtremidades()[0].getConteudo());
        assertEquals(pontoD, aresta.getExtremidades()[1].getConteudo());
        assertEquals(3, aresta.getPeso());

        aresta = grafo.obterAresta(pontoD, pontoA);
        assertNotNull(aresta);
        assertEquals(pontoD, aresta.getExtremidades()[0].getConteudo());
        assertEquals(pontoA, aresta.getExtremidades()[1].getConteudo());
        assertEquals(4, aresta.getPeso());   
    }
    
    @Test
    public void testObterArestaConteudoExtremidadeNula() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));
        assertTrue(grafo.adicionarVertice(pontoC));
        assertTrue(grafo.adicionarVertice(pontoD));

        /* Cria as arestas do grafo e verifica se o processo ocorreu corretamente. */
        assertTrue(grafo.adicionarAresta(pontoA, pontoB, 1)); 
        assertTrue(grafo.adicionarAresta(pontoB, pontoC, 2));
        assertTrue(grafo.adicionarAresta(pontoC, pontoD, 3));
        assertTrue(grafo.adicionarAresta(pontoD, pontoA, 4));   
        
        /* Tenta obter as arestas cadastradas e verifica se elas est�o conforme o esperado.*/
        Aresta aresta = grafo.obterAresta(null, pontoB);
        assertNull(aresta);
        
        aresta = grafo.obterAresta(pontoC, null);
        assertNull(aresta);
    }
    
    @Test
    public void testObterArestaVerticeNaoCadastrado() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));

        /* Cria as arestas do grafo e verifica se o processo ocorreu conforme o esperado. */
        assertTrue(grafo.adicionarAresta(pontoA, pontoB, 1)); 
        assertFalse(grafo.adicionarAresta(pontoB, pontoC, 2));
        assertFalse(grafo.adicionarAresta(pontoC, pontoD, 3));
        assertFalse(grafo.adicionarAresta(pontoD, pontoA, 4));    
        
        /* Tenta obter as arestas cadastradas e verifica se elas est�o conforme o esperado.*/
        Aresta aresta = grafo.obterAresta(pontoC, pontoD);
        assertNull(aresta);

        aresta = grafo.obterAresta(pontoD, pontoA);
        assertNull(aresta);
    }
    
    @Test
    public void testObterArestaInexistente() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));
        assertTrue(grafo.adicionarVertice(pontoC));
        assertTrue(grafo.adicionarVertice(pontoD));

        /* Cria as arestas do grafo e verifica se o processo ocorreu corretamente. */
        assertTrue(grafo.adicionarAresta(pontoA, pontoB, 1)); 
        assertTrue(grafo.adicionarAresta(pontoB, pontoC, 2));
        assertTrue(grafo.adicionarAresta(pontoC, pontoD, 3));
        assertTrue(grafo.adicionarAresta(pontoD, pontoA, 4));    
        
        /* Tenta obter as arestas cadastradas e verifica se elas est�o conforme o esperado.*/
        Aresta aresta = grafo.obterAresta(pontoB, pontoD);
        assertNull(aresta);

        aresta = grafo.obterAresta(pontoA, pontoC);
        assertNull(aresta);
    }


    /**
     * Test of obterMatriz method, of class GrafoPonderado.
     */
    @Test
    public void testObterMatriz() {
        /* Cria o conte�do dos v�rtices. */
        Ponto pontoA = new Ponto("A");
        Ponto pontoB = new Ponto("B");
        Ponto pontoC = new Ponto("C");
        Ponto pontoD = new Ponto("D");     
        
        /* Adiciona o conte�do nos grafos e verifica se o proceso ocorreu corretamente. */
        assertTrue(grafo.adicionarVertice(pontoA));
        assertTrue(grafo.adicionarVertice(pontoB));
        assertTrue(grafo.adicionarVertice(pontoC));
        assertTrue(grafo.adicionarVertice(pontoD));
        
        /* Cria as arestas do grafo e verifica se o processo ocorreu corretamente. */
        assertTrue(grafo.adicionarAresta(pontoA, pontoB, 1)); 
        assertTrue(grafo.adicionarAresta(pontoB, pontoC, 2));
        assertTrue(grafo.adicionarAresta(pontoC, pontoD, 3));
        assertTrue(grafo.adicionarAresta(pontoD, pontoA, 4));   
        
        Object[][] matrizGrafo = grafo.obterMatriz(); //obt�m a matriz representativa grafo
        
        /* Verifica se a primeira linha e a primeira coluna est�o preenchidas com os v�rtices corretamente. */
        for(int i = 1; i < matrizGrafo[0].length; i++) {
            switch(i) {
                case 1: {
                    assertEquals("A", ((Ponto) ((Vertice) matrizGrafo[i][0]).getConteudo()).getNome());
                    assertEquals("A", ((Ponto) ((Vertice) matrizGrafo[0][i]).getConteudo()).getNome());
                    break;
                }
                case 2: {
                    assertEquals("B", ((Ponto) ((Vertice) matrizGrafo[i][0]).getConteudo()).getNome());
                    assertEquals("B", ((Ponto) ((Vertice) matrizGrafo[0][i]).getConteudo()).getNome());
                    break;
                }                
                case 3: {
                    assertEquals("C", ((Ponto) ((Vertice) matrizGrafo[i][0]).getConteudo()).getNome());
                    assertEquals("C", ((Ponto) ((Vertice) matrizGrafo[0][i]).getConteudo()).getNome());
                    break;
                }
                case 4: {
                    assertEquals("D", ((Ponto) ((Vertice) matrizGrafo[i][0]).getConteudo()).getNome());
                    assertEquals("D", ((Ponto) ((Vertice) matrizGrafo[0][i]).getConteudo()).getNome());
                    break;
                }
            }
        }
        
        /* Verifica se as outras c�lulas est�o preenchidas corretamente: com o respectivo valor de peso quando h� uma aresta e com null, quando n�o h� aresta. */
        for(int linha = 1; linha < matrizGrafo[0].length; linha++) {
            for(int coluna = 1; coluna < matrizGrafo[1].length; coluna++) {
                if((linha == 1 && coluna == 2) || (linha == 2 && coluna == 1)) {
                    assertEquals(1, ((Integer) matrizGrafo[linha][coluna]).intValue());
                    assertEquals(1, ((Integer) matrizGrafo[coluna][linha]).intValue());
                } else if((linha == 1 && coluna == 4) || (linha == 4 && coluna == 1)) {
                    assertEquals(4, ((Integer) matrizGrafo[linha][coluna]).intValue());
                    assertEquals(4, ((Integer) matrizGrafo[coluna][linha]).intValue());
                } else if((linha == 2 && coluna == 3) || (linha == 3 && coluna == 2)) {
                    assertEquals(2, ((Integer) matrizGrafo[linha][coluna]).intValue());
                    assertEquals(2, ((Integer) matrizGrafo[coluna][linha]).intValue());
                } else if((linha == 3 && coluna == 4) || (linha == 4 && coluna == 3)) {
                    assertEquals(3, ((Integer) matrizGrafo[linha][coluna]).intValue());
                    assertEquals(3, ((Integer) matrizGrafo[coluna][linha]).intValue());
                } else {
                    assertEquals(null, matrizGrafo[linha][coluna]);
                }
            }
        }
    }
    
}
