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
package br.uefs.ecomp.ForteSeguro.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Realiza o desenho das linhas e dos c�rculos que fazem parte das arestas e v�rtices visuais.
 * 
 * @author Iago Almeida
 * @author Valmir Vinicius
 */
public class Desenhador {
    
    /* Impede o instanciamento da classe. */
    private Desenhador() {};

    /**
     * Pinta o c�rculo que faz parte de um v�rtice visual.
     * @param g gr�fico do JFrame
     * @param x coordenada em x do clique
     * @param y coordenada em Y do clique
     * @param nome nome do ponto
     */
    public static void pintarCirculo(Graphics g, int x, int y, String nome) {
        ((Graphics2D) g).setColor(Color.WHITE); //define a cor do c�rculo
        ((Graphics2D) g).setStroke(new BasicStroke(4));//define a espessura do circulo        
        ((Graphics2D) g).fillOval(x, y, 15, 15); //define os limites ovais do c�rculo
        ((Graphics2D) g).setColor(Color.BLACK); //define a cor do limite oval do c�rculo
        ((Graphics2D) g).drawOval(x, y, 15, 15); //desenha os limites ovais do c�rculo
        ((Graphics2D) g).setColor(Color.RED); //define a cor do da mensagem associada ao c�rculo
        g.setFont(new Font("Monospaced", Font.BOLD, 16)); //define a fonte da mensagem associada ao c�rculo
        ((Graphics2D) g).drawString(nome, x, y); //desenha o nome associado ao c�rculo
    }

    /**
     * Pinta um c�rculo no momento em que ele foi selecionado.
     * @param g gr�fico do JFrame
     * @param x coordenada em x do clique
     * @param y coordenada em Y do clique
     * @param cor cor que ser� pintada no circulo
     */
    public static void pintarCirculoSelecionado(Graphics g, int x, int y, Color cor){
        ((Graphics2D) g).setColor(cor); //define a cor do c�rculo
        ((Graphics2D) g).setStroke(new BasicStroke(4));//define a espessura do circulo        
        ((Graphics2D) g).fillOval(x, y, 15, 15);//define os limites ovais do c�rculo
        ((Graphics2D) g).setColor(Color.BLACK); //define a cor dos limites ovais do c�rculo
        ((Graphics2D) g).drawOval(x, y, 15, 15); //desenha os limites ovais do c�rculo
    }
    
    /**
     * Pinta uma linha entre dois pontos na tela;
     * @param g gr�fico do JFrame
     * @param x1 coordenada em x de um dos cliques
     * @param y1 coordenada em y de um dos cliques
     * @param x2 coordenada em x de um dos cliques
     * @param y2 coordenada em u de um dos cliques
     * @param valorAssociado valor associado a linha
     */
    public static void pintarLinha(Graphics g, int x1, int y1, int x2, int y2, int valorAssociado) {
        int xExtremidade = 0, yExtremidade = 0; //coordenadas das extremidades da linha
        
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //define a renderiza��o da linha
        ((Graphics2D) g).setStroke(new BasicStroke(2)); //define a espessura da linha
        ((Graphics2D) g).drawLine(x1, y1, x2, y2); //desenha a linha
       
        /* Realiza o c�lculo do valor da coordenada X da extremidade da linha. */
        if (x1 <= x2) {
            xExtremidade = ((x2 - x1) / 2) + x1;
        } else if (x1 > x2) {
            xExtremidade = ((x1 - x2) / 2) + x2;
        }
        
        /* Realiza o c�lculo do valor da coordenada Y da extremidade da linha. */
        if (y1 < y2) {
            yExtremidade = ((y2 - y1) / 2) + y1;
        } else if (y1 >= y2) {
            yExtremidade = ((y1 - y2) / 2) + y2;
        }
        
        g.setFont(new Font("Monospaced", 1, 12)); //define a fonte que ser� utilizada para desenhar o valor associado a linha
        ((Graphics2D) g).setColor(Color.BLUE); //define a cor utilizada na fonte do valor associado a linha
        ((Graphics2D) g).drawString(String.valueOf(valorAssociado), xExtremidade, yExtremidade); //desenha a linha
    }
    
    /**
     * Pinta uma linha utilizada para representar um caminho m�nimo.
     * @param g gr�fico do JFrame
     * @param x1 coordenada em x de um dos cliques
     * @param y1 coordenada em y de um dos cliques
     * @param x2 coordenada em x de um dos cliques
     * @param y2 coordenada em u de um dos cliques
     */
    public static void pintarLinhaCaminhoMinimo(Graphics g, int x1, int y1, int x2, int y2){
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //define a renderiza��o da linha
        ((Graphics2D) g).setStroke(new BasicStroke(2)); //define a espessura da linha
        ((Graphics2D) g).setColor(Color.RED); //define a cor da linha
        ((Graphics2D) g).drawLine(x1, y1, x2, y2); //desenha a linha
    }    
}
