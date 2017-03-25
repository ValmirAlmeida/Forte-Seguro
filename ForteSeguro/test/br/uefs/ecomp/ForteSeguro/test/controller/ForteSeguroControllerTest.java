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
package br.uefs.ecomp.ForteSeguro.test.controller;

import br.uefs.ecomp.ForteSeguro.controller.ForteSeguroController;
import br.uefs.ecomp.ForteSeguro.exceptions.CadastrarPontoExistenteException;
import br.uefs.ecomp.ForteSeguro.exceptions.CaminhoJaExisteException;
import br.uefs.ecomp.ForteSeguro.exceptions.RemoverCaminhoInexistenteException;
import br.uefs.ecomp.ForteSeguro.exceptions.NomeInvalidoException;
import br.uefs.ecomp.ForteSeguro.exceptions.OrigemEDestinoIguaisException;
import br.uefs.ecomp.ForteSeguro.exceptions.PontoInatingivelException;
import br.uefs.ecomp.ForteSeguro.exceptions.PontoInexistenteException;
import br.uefs.ecomp.ForteSeguro.model.Ponto;
import br.uefs.ecomp.ForteSeguro.util.Vertice;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ForteSeguroControllerTest {
    private ForteSeguroController controller;
    
    @Before
    public void setUp() {
        controller = new ForteSeguroController();
    }

    @Test
    public void testObterCaminhoMinimoSucesso()  {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");  
            controller.cadastrarPonto("E");
            controller.cadastrarPonto("F");
            controller.cadastrarPonto("G");
            controller.cadastrarPonto("H");  
            controller.cadastrarPonto("I");
            controller.cadastrarPonto("J");
            controller.cadastrarPonto("K");
            controller.cadastrarPonto("L");
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            Logger.getLogger(ForteSeguroControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* Realiza o cadastro dos caminhos e certifica-se de que o processo ocorreu corretamente. */
        try {
            controller.cadastrarCaminho("A", "B", 7);
            controller.cadastrarCaminho("A", "H", 10);
            controller.cadastrarCaminho("B", "C", 5);
            controller.cadastrarCaminho("B", "H", 15);
            controller.cadastrarCaminho("B", "D", 25);
            controller.cadastrarCaminho("B", "J", 11);
            controller.cadastrarCaminho("C", "D", 30);
            controller.cadastrarCaminho("D", "E", 40);
            controller.cadastrarCaminho("D", "F", 27);
            controller.cadastrarCaminho("D", "K", 17);
            controller.cadastrarCaminho("E", "F", 5);
            controller.cadastrarCaminho("F", "L", 12);
            controller.cadastrarCaminho("F", "H", 40);
            controller.cadastrarCaminho("H", "G", 18);
            controller.cadastrarCaminho("F", "G", 20);
            controller.cadastrarCaminho("H", "I", 15);
            controller.cadastrarCaminho("I", "J", 5);
            controller.cadastrarCaminho("J", "K", 19);
            controller.cadastrarCaminho("K", "L", 22);
            controller.cadastrarCaminho("L", "I", 9);
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CaminhoJaExisteException ex) {
            fail();
        }
        
        List<List<Vertice>> caminhoMinimo = null;
        
        try {
            caminhoMinimo = controller.obterCaminhoMinimo("A", "E", "H"); //obtém o caminho mínimo
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (PontoInatingivelException ex) {
            fail();
        } catch (OrigemEDestinoIguaisException ex) {
            fail();
        }
        
        assertEquals(1, caminhoMinimo.size()); //verifica se a quantidade de caminhos mínimos obtidos é correto
        
        Iterator<Vertice> iVertices = caminhoMinimo.get(0).iterator(); //obtém o iterador para o menor caminho
        
        /* Obtém cada vértice e verifica se a ordem o conteúdo dele condiz com o esperado.*/
        assertTrue(iVertices.hasNext());
        assertEquals("A", ((Ponto) iVertices.next().getConteudo()).getNome());
        
        assertTrue(iVertices.hasNext());
        assertEquals("B", ((Ponto) iVertices.next().getConteudo()).getNome());

        assertTrue(iVertices.hasNext());
        assertEquals("J", ((Ponto) iVertices.next().getConteudo()).getNome());

        assertTrue(iVertices.hasNext());
        assertEquals("I", ((Ponto) iVertices.next().getConteudo()).getNome());

        assertTrue(iVertices.hasNext());
        assertEquals("L", ((Ponto) iVertices.next().getConteudo()).getNome());

        assertTrue(iVertices.hasNext());
        assertEquals("F", ((Ponto) iVertices.next().getConteudo()).getNome());

        assertTrue(iVertices.hasNext());
        assertEquals("E", ((Ponto) iVertices.next().getConteudo()).getNome());

        assertTrue(iVertices.hasNext());
        assertEquals("F", ((Ponto) iVertices.next().getConteudo()).getNome());

        assertTrue(iVertices.hasNext());
        assertEquals("L", ((Ponto) iVertices.next().getConteudo()).getNome());

        assertTrue(iVertices.hasNext());
        assertEquals("I", ((Ponto) iVertices.next().getConteudo()).getNome());       
        
        assertTrue(iVertices.hasNext());
        assertEquals("H", ((Ponto) iVertices.next().getConteudo()).getNome());     
        
        assertFalse(iVertices.hasNext());
    }
    
    @Test
    public void testObterCaminhoMinimoPontoComNomeInvalido()  {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");  
            controller.cadastrarPonto("E");
            controller.cadastrarPonto("F");
            controller.cadastrarPonto("G");
            controller.cadastrarPonto("H");  
            controller.cadastrarPonto("I");
            controller.cadastrarPonto("J");
            controller.cadastrarPonto("K");
            controller.cadastrarPonto("L");
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            Logger.getLogger(ForteSeguroControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* Realiza o cadastro dos caminhos e certifica-se de que o processo ocorreu corretamente. */
        try {
            controller.cadastrarCaminho("A", "B", 7);
            controller.cadastrarCaminho("A", "H", 10);
            controller.cadastrarCaminho("B", "C", 5);
            controller.cadastrarCaminho("B", "H", 15);
            controller.cadastrarCaminho("B", "D", 25);
            controller.cadastrarCaminho("B", "J", 11);
            controller.cadastrarCaminho("C", "D", 30);
            controller.cadastrarCaminho("D", "E", 40);
            controller.cadastrarCaminho("D", "F", 27);
            controller.cadastrarCaminho("D", "K", 17);
            controller.cadastrarCaminho("E", "F", 5);
            controller.cadastrarCaminho("F", "L", 12);
            controller.cadastrarCaminho("F", "H", 40);
            controller.cadastrarCaminho("H", "G", 18);
            controller.cadastrarCaminho("F", "G", 20);
            controller.cadastrarCaminho("H", "I", 15);
            controller.cadastrarCaminho("I", "J", 5);
            controller.cadastrarCaminho("J", "K", 19);
            controller.cadastrarCaminho("K", "L", 22);
            controller.cadastrarCaminho("L", "I", 9);
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CaminhoJaExisteException ex) {
            fail();
        }
        
        List<List<Vertice>> caminhoMinimo = null;
        
        /* Tenta obter o caminho mínimo e verifica se a exceção correta é lançada. */
        try {
            caminhoMinimo = controller.obterCaminhoMinimo(null, "C", "D"); 
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            assertTrue(true);
        } catch (PontoInatingivelException ex) {
            fail();
        } catch (OrigemEDestinoIguaisException ex) {
            fail();
        }
        
        try {
            caminhoMinimo = controller.obterCaminhoMinimo("A", "     ", "D"); 
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            assertTrue(true);
        } catch (PontoInatingivelException ex) {
            fail();
        } catch (OrigemEDestinoIguaisException ex) {
            fail();
        }        
    }        
    
    @Test
    public void testObterCaminhoMinimoPontoInexistente()  {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");  
            controller.cadastrarPonto("E");
            controller.cadastrarPonto("F");
            controller.cadastrarPonto("G");
            controller.cadastrarPonto("H");  
            controller.cadastrarPonto("I");
            controller.cadastrarPonto("J");
            controller.cadastrarPonto("K");
            controller.cadastrarPonto("L");
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Realiza o cadastro dos caminhos e certifica-se de que o processo ocorreu corretamente. 
           Note que nenhuma aresta é criada com o vértice A, ele é isolado no grafo.
        */
        try {
            controller.cadastrarCaminho("B", "C", 5);
            controller.cadastrarCaminho("B", "H", 15);
            controller.cadastrarCaminho("B", "D", 25);
            controller.cadastrarCaminho("B", "J", 11);
            controller.cadastrarCaminho("C", "D", 30);
            controller.cadastrarCaminho("D", "E", 40);
            controller.cadastrarCaminho("D", "F", 27);
            controller.cadastrarCaminho("D", "K", 17);
            controller.cadastrarCaminho("E", "F", 5);
            controller.cadastrarCaminho("F", "L", 12);
            controller.cadastrarCaminho("F", "H", 40);
            controller.cadastrarCaminho("H", "G", 18);
            controller.cadastrarCaminho("F", "G", 20);
            controller.cadastrarCaminho("H", "I", 15);
            controller.cadastrarCaminho("I", "J", 5);
            controller.cadastrarCaminho("J", "K", 19);
            controller.cadastrarCaminho("K", "L", 22);
            controller.cadastrarCaminho("L", "I", 9);
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CaminhoJaExisteException ex) {
            fail();
        }
        
        List<List<Vertice>> caminhoMinimo = null;
        
        /* Tenta obter o caminho mínimo e verifica se a exceção correta é lançada. */
        try {
            caminhoMinimo = controller.obterCaminhoMinimo("A", "B", "C"); 
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (PontoInatingivelException ex) {
            assertTrue(true);
        } catch (OrigemEDestinoIguaisException ex) {
            fail();
        }
    }    
    
    @Test
    public void testObterCaminhoMinimoDestinoEOrigemIguais() {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");  
            controller.cadastrarPonto("E");
            controller.cadastrarPonto("F");
            controller.cadastrarPonto("G");
            controller.cadastrarPonto("H");  
            controller.cadastrarPonto("I");
            controller.cadastrarPonto("J");
            controller.cadastrarPonto("K");
            controller.cadastrarPonto("L");
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            Logger.getLogger(ForteSeguroControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* Realiza o cadastro dos caminhos e certifica-se de que o processo ocorreu corretamente. */
        try {
            controller.cadastrarCaminho("A", "B", 7);
            controller.cadastrarCaminho("A", "H", 10);
            controller.cadastrarCaminho("B", "C", 5);
            controller.cadastrarCaminho("B", "H", 15);
            controller.cadastrarCaminho("B", "D", 25);
            controller.cadastrarCaminho("B", "J", 11);
            controller.cadastrarCaminho("C", "D", 30);
            controller.cadastrarCaminho("D", "E", 40);
            controller.cadastrarCaminho("D", "F", 27);
            controller.cadastrarCaminho("D", "K", 17);
            controller.cadastrarCaminho("E", "F", 5);
            controller.cadastrarCaminho("F", "L", 12);
            controller.cadastrarCaminho("F", "H", 40);
            controller.cadastrarCaminho("H", "G", 18);
            controller.cadastrarCaminho("F", "G", 20);
            controller.cadastrarCaminho("H", "I", 15);
            controller.cadastrarCaminho("I", "J", 5);
            controller.cadastrarCaminho("J", "K", 19);
            controller.cadastrarCaminho("K", "L", 22);
            controller.cadastrarCaminho("L", "I", 9);
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CaminhoJaExisteException ex) {
            fail();
        }
        
        List<List<Vertice>> caminhoMinimo = null;
        
        /* Tenta obter o caminho mínimo e verifica se a exceção correta é lançada. */
        try {
            caminhoMinimo = controller.obterCaminhoMinimo("C", "C", "D"); 
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (PontoInatingivelException ex) {
            fail();
        } catch (OrigemEDestinoIguaisException ex) {
            assertTrue(true);
        }        
    }

    @Test
    public void testCadastrarPontoSucesso() {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");            
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Certifica-se de que os pontos foram realmente cadastrados.*/
        try {
            assertEquals("A", (controller.obterPonto("A")).getNome());
            assertEquals("B", (controller.obterPonto("B")).getNome());
            assertEquals("C", (controller.obterPonto("C")).getNome());
            assertEquals("D", (controller.obterPonto("D")).getNome());
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        }
    }
    
    @Test
    public void testCadastrarPontoNomeInvalido() {
        
        /* Tenta realizar o cadastro dos pontos e verifica se a exceção esperada foi lançada. */
        try {
            controller.cadastrarPonto("    ");
        } catch (NomeInvalidoException ex) {
            assertTrue(true);
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        try {
            controller.cadastrarPonto(null);
        } catch (NomeInvalidoException ex) {
            assertTrue(true);
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }        
    }    
    
    @Test
    public void testCadastrarPontoExistente() {
        
        /* Realiza o cadastro dos pontos e verifica se a exceção adequada foi lançada. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("A");            
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            assertTrue(true);
        }
             
    }        

    @Test
    public void testRemoverPontoSucesso() {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");            
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Tenta realizar a remoção dos pontos e certifica-se de que o processo ocorreu corretamente. */
        try {
            controller.removerPonto("A");
            controller.removerPonto("B");
            controller.removerPonto("C");
            controller.removerPonto("D");
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        }
        
        /* Certifica-se de que os pontos foram realmente removidos.*/
        
        try {
            assertEquals("A", controller.obterPonto("A"));
        } catch (PontoInexistenteException ex) {
            assertTrue(true);
        } catch (NomeInvalidoException ex) {
            fail();
        }             

        try {
            assertEquals("B", controller.obterPonto("B"));
        } catch (PontoInexistenteException ex) {
            assertTrue(true);
        } catch (NomeInvalidoException ex) {
            fail();
        }        

        try {
            assertEquals("C", controller.obterPonto("C"));
        } catch (PontoInexistenteException ex) {
            assertTrue(true);
        } catch (NomeInvalidoException ex) {
            fail();
        }        

        try {
            assertEquals("D", controller.obterPonto("D"));
        } catch (PontoInexistenteException ex) {
            assertTrue(true);
        } catch (NomeInvalidoException ex) {
            fail();
        }                
    }
    
    @Test
    public void testRemoverPontoInexistente() {
        /* Tenta realizar a remoção de um ponto não cadastrado e verifica se a exceção correta foi lançada. */
        try {
            controller.removerPonto("A");
        } catch (PontoInexistenteException ex) {
            assertTrue(true);
        } catch (NomeInvalidoException ex) {
            fail();
        }
    } 
    
    @Test
    public void testRemoverPontoComNomeInvalido() {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");            
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Tenta realizar a remoção de pontos com nomes inválidos. */
        try {
            controller.removerPonto("      ");
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            assertTrue(true);
        }
        
        try {
            controller.removerPonto(null);
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            assertTrue(true);
        }        
        
    }    
    
    @Test
    public void obterPontoSucesso() {
        /* Realiza o cadastro do ponto. */
        try {
            controller.cadastrarPonto("A");
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Tenta obter o ponto cadastrado.*/
        try {
            assertEquals("A", (controller.obterPonto("A")).getNome());
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        }
    }        
    
    @Test
    public void obterPontoInexistente() {
        /* Realiza o cadastro do ponto. */
        try {
            controller.cadastrarPonto("A");
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Tenta obter e verifica se a exceção correta foi lançada.*/
        try {
            assertEquals("B", (controller.obterPonto("B")).getNome());
        } catch (PontoInexistenteException ex) {
            assertTrue(true);
        } catch (NomeInvalidoException ex) {
            fail();
        }
    }     
    
    @Test
    public void obterPontoComNomeInválido() {
        /* Realiza o cadastro do ponto. */
        try {
            controller.cadastrarPonto("A");
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Tenta obter e verifica se a exceção correta foi lançada.*/
        try {
            assertEquals("A", (controller.obterPonto("     ")).getNome());
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            assertTrue(true);
        }
        
        try {
            assertEquals("A", (controller.obterPonto(null)).getNome());
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            assertTrue(true);
        }        
    }      

    @Test
    public void testCadastrarCaminhoSucesso() {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");            
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Realiza o cadastro dos caminhos e certifica-se de que o processo ocorreu corretamente. */
        try {
            controller.cadastrarCaminho("A", "B", 1);
            controller.cadastrarCaminho("B", "C", 2);
            controller.cadastrarCaminho("C", "D", 3);
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CaminhoJaExisteException ex) {
            fail();
        }
    }
    
    
    @Test
    public void testCadastrarCaminhoPontoInexistente() {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");            
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Tenta realizar o cadastro do caminho e certifica-se de que a exceção adequada foi lançada. */
        try {
            controller.cadastrarCaminho("E", "B", 1);
        } catch (PontoInexistenteException ex) {
            assertTrue(true);
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CaminhoJaExisteException ex) {
            fail();
        }
        
        try {
            controller.cadastrarCaminho("A", "K", 2);
        } catch (PontoInexistenteException ex) {
            assertTrue(true);
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CaminhoJaExisteException ex) {
            fail();
        }        
        
    }        
    
    @Test
    public void testCadastrarCaminhoNomeInvalido() {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");            
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Tenta realizar o cadastro do caminho e certifica-se de que a exceção adequada foi lançada. */
        try {
            controller.cadastrarCaminho("   ", "B", 1);
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            assertTrue(true);
        } catch (CaminhoJaExisteException ex) {
            fail();
        }
        
        try {
            controller.cadastrarCaminho("B", null, 2);
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            assertTrue(true);
        } catch (CaminhoJaExisteException ex) {
            fail();
        }
    }    
    
    @Test
    public void testCadastrarCaminhoExistente() {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");            
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Realiza o cadastro do caminho e certifica-se de que o processo ocorreu corretamente. */
        try {
            controller.cadastrarCaminho("A", "B", 1);
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CaminhoJaExisteException ex) {
            fail();
        }
        
        /* Tenta realizar o cadastro do caminho e certifica-se de que a exceção adequada foi lançada. */
        try {
            controller.cadastrarCaminho("A", "B", 2);
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CaminhoJaExisteException ex) {
            assertTrue(true);
        }
    }    

    @Test
    public void testRemoverCaminhoSucesso() {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");            
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Realiza o cadastro dos caminhos e certifica-se de que o processo ocorreu corretamente. */
        try {
            controller.cadastrarCaminho("A", "B", 1);
            controller.cadastrarCaminho("B", "C", 2);
            controller.cadastrarCaminho("C", "D", 3);
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CaminhoJaExisteException ex) {
            fail();
        }
        
        /* Tenta realizar a remoção do caminhos e certifica-se de que o processo ocorreu corretamente. */
        try {
            controller.removerCaminho("A", "B");
            controller.removerCaminho("B", "C");
            controller.removerCaminho("C", "D");
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (RemoverCaminhoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        }
    }
    
    @Test
    public void testRemoverCaminhoComPontoInexistente() {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");            
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Realiza o cadastro dos caminhos e certifica-se de que o processo ocorreu corretamente. */
        try {
            controller.cadastrarCaminho("A", "B", 1);
            controller.cadastrarCaminho("B", "C", 2);
            controller.cadastrarCaminho("C", "D", 3);
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CaminhoJaExisteException ex) {
            fail();
        }
        
        /* Tenta realizar a remoção do caminhos e certifica-se de que a exceção correta foi lançada. */
        try {
            controller.removerCaminho("A", "K");
        } catch (PontoInexistenteException ex) {
            assertTrue(true);
        } catch (RemoverCaminhoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        }
    }
    
    @Test
    public void testRemoverCaminhoInexistente() {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");            
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        
        /* Tenta realizar a remoção do caminhos e certifica-se de que a exceção correta foi lançada. */
        try {
            controller.removerCaminho("A", "B");
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (RemoverCaminhoInexistenteException ex) {
            assertTrue(true);
        } catch (NomeInvalidoException ex) {
            fail();
        }
    }   
    
    @Test
    public void testRemoverCaminhoComPontoDeNomeInvalido() {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");            
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Realiza o cadastro dos caminhos e certifica-se de que o processo ocorreu corretamente. */
        try {
            controller.cadastrarCaminho("A", "B", 1);
            controller.cadastrarCaminho("B", "C", 2);
            controller.cadastrarCaminho("C", "D", 3);
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CaminhoJaExisteException ex) {
            fail();
        }
        
        /* Tenta realizar a remoção do caminhos e certifica-se de que a exceção correta foi lançada. */
        try {
            controller.removerCaminho(null, "B");
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (RemoverCaminhoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            assertTrue(true);
        }
        
        try {
            controller.removerCaminho("A", "      ");
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (RemoverCaminhoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            assertTrue(true);
        }        
    }
    
    @Test
    public void testObterRotaEmTextoSucesso()  {
        /* Realiza o cadastro dos pontos e verifica se o processo ocorreu normalmente. */
        try {
            controller.cadastrarPonto("A");
            controller.cadastrarPonto("B");
            controller.cadastrarPonto("C");
            controller.cadastrarPonto("D");  
            controller.cadastrarPonto("E");
            controller.cadastrarPonto("F");
            controller.cadastrarPonto("G");
            controller.cadastrarPonto("H");  
            controller.cadastrarPonto("I");
            controller.cadastrarPonto("J");
            controller.cadastrarPonto("K");
            controller.cadastrarPonto("L");
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CadastrarPontoExistenteException ex) {
            fail();
        }
        
        /* Realiza o cadastro dos caminhos e certifica-se de que o processo ocorreu corretamente. */
        try {
            controller.cadastrarCaminho("A", "B", 7);
            controller.cadastrarCaminho("A", "H", 10);
            controller.cadastrarCaminho("B", "C", 5);
            controller.cadastrarCaminho("B", "H", 15);
            controller.cadastrarCaminho("B", "D", 25);
            controller.cadastrarCaminho("B", "J", 11);
            controller.cadastrarCaminho("C", "D", 30);
            controller.cadastrarCaminho("D", "E", 40);
            controller.cadastrarCaminho("D", "F", 27);
            controller.cadastrarCaminho("D", "K", 17);
            controller.cadastrarCaminho("E", "F", 5);
            controller.cadastrarCaminho("F", "L", 12);
            controller.cadastrarCaminho("F", "H", 40);
            controller.cadastrarCaminho("H", "G", 18);
            controller.cadastrarCaminho("F", "G", 20);
            controller.cadastrarCaminho("H", "I", 15);
            controller.cadastrarCaminho("I", "J", 5);
            controller.cadastrarCaminho("J", "K", 19);
            controller.cadastrarCaminho("K", "L", 22);
            controller.cadastrarCaminho("L", "I", 9);
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (CaminhoJaExisteException ex) {
            fail();
        }
        
        List<List<Vertice>> caminhoMinimo = null;
        
        try {
            caminhoMinimo = controller.obterCaminhoMinimo("A", "E", "H"); //obtém o caminho mínimo
        } catch (PontoInexistenteException ex) {
            fail();
        } catch (NomeInvalidoException ex) {
            fail();
        } catch (PontoInatingivelException ex) {
            fail();
        } catch (OrigemEDestinoIguaisException ex) {
            fail();
        }
        
        assertEquals("A - B - J - I - L - F - E - F - L - I - H - ", controller.obterRotaEmTexto(caminhoMinimo.get(0))); //obtém a rota mínima em texto e verifica se está correta
    }
    
}
