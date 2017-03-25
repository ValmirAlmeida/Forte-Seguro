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

import br.uefs.ecomp.ForteSeguro.controller.ForteSeguroController;
import br.uefs.ecomp.ForteSeguro.exceptions.CadastrarPontoExistenteException;
import br.uefs.ecomp.ForteSeguro.exceptions.CaminhoJaExisteException;
import br.uefs.ecomp.ForteSeguro.exceptions.RemoverCaminhoInexistenteException;
import br.uefs.ecomp.ForteSeguro.exceptions.NomeInvalidoException;
import br.uefs.ecomp.ForteSeguro.exceptions.OrigemEDestinoIguaisException;
import br.uefs.ecomp.ForteSeguro.exceptions.PontoInatingivelException;
import br.uefs.ecomp.ForteSeguro.exceptions.PontoInexistenteException;
import br.uefs.ecomp.ForteSeguro.util.Vertice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe respons�vel por gerar a tela principal do sistema e inicializar o
 * applet.
 *
 * @author Iago Almeida
 * @author Valmir Vinicius
 */
public class TelaPrincipal extends javax.swing.JApplet {

    /* Gerenciador da tela. */
    private GerenciadorTela gerenciadorTela;

    /* Indica se um ponto foi selecionado para cria��o de uma aresta. */
    private boolean selecionouPonto;

    /* Aresta visual que come�ou a ser criada com um clique. */
    private ArestaVisual arestaCriada;

    /* Controlador de neg�cios. */
    private ForteSeguroController controller;

    /* Lista que representa o caminho m�nimo exibido na tela.*/
    private List<List<Vertice>> caminhosMinimos;

    /**
     * Inicializa o applet e define os par�metros iniciais da tela.
     */
    @Override
    public void init() {
        gerenciadorTela = new GerenciadorTela();  //obt�m uma nova inst�ncia do gerenciador de tela
        controller = new ForteSeguroController(); //obt�m uma nova inst�ncia do controller

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //define o Look And Feel da tela
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            return;
        }
        
        setSize(950, 500); //define o tamanho da tela

        try {
            java.awt.EventQueue.invokeAndWait(() -> {
                initComponents(); //inicializa os componentes da tela
                
                /* Define os par�metros iniciais do ComboBox de exclus�o. */
                jComboBoxExcluir.setSelectedItem(null);
                jTextoPontoExcluir.setEditable(false);
                jTextoExcluirExtremidade1.setEditable(false);
                jTextoExcluirExtremidade2.setEditable(false);                
                
                jComboBoxExcluir.addActionListener((ActionEvent ae) -> { //adiciona a��es na ComboBox respons�vel pela exclus�o de v�rtices e arestas
                    if (jComboBoxExcluir.getSelectedIndex() == 0) { //caso seja selecionado a primeira op��o
                        /* Define os par�metros do ComboBox de exclus�o. */
                        jTextoPontoExcluir.setEditable(true);
                        jTextoExcluirExtremidade1.setEditable(false);
                        jTextoExcluirExtremidade2.setEditable(false);
                    } else {
                        /* Define os par�metros do ComboBox de exclus�o. */
                        jTextoPontoExcluir.setEditable(false);
                        jTextoExcluirExtremidade1.setEditable(true);
                        jTextoExcluirExtremidade2.setEditable(true);
                    }
                });
                
                jComboBoxPossiveisCaminhos.addActionListener((ActionEvent ae) -> { //adiciona a��es na ComboBox que exibe os caminhos gerados
                    if (!jComboBoxPossiveisCaminhos.getSelectedItem().equals("Possiveis Caminhos")) { //caso seja selecionada a primeira op��o
                        redesenhar(); //redesenha a tela
                        gerenciadorTela.desenharCaminhoMinimoUnico(caminhosMinimos.get(jComboBoxPossiveisCaminhos.getSelectedIndex()), jPainelExibicao.getGraphics()); //desenha o caminho m�nimo selecionado na ComboBox
                        jTabelaCaminhosMinimos.setSelectedIndex(jComboBoxPossiveisCaminhos.getSelectedIndex()); //define o caminho m�nimo selecionado no painel com guias
                    } else {
                        JOptionPane.showMessageDialog(null, "O caminho minimo n�o foi gerado"); //caso ocorra um clique em op��o sem calcular caminho m�nimo
                    }
                });
            });
            
            jTabelaCaminhosMinimos.setVisible(false); //deixa o painel com guias invis�vel 
        } catch (InterruptedException | InvocationTargetException ex) {
            return;
        }
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPainelExibicao = new javax.swing.JPanel();
        jBotaoLimpar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jComboBoxExcluir = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextoPontoExcluir = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextoExcluirExtremidade1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextoExcluirExtremidade2 = new javax.swing.JTextField();
        jBotaoOk = new javax.swing.JButton();
        jBotaoCancelar = new javax.swing.JButton();
        jBotaoRedesenhar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextoEstacionamento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextoPontoColeta = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextoBanco = new javax.swing.JTextField();
        jBotaoGerarCaminhoMinimo = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTabelaCaminhosMinimos = new javax.swing.JTabbedPane();
        jComboBoxPossiveisCaminhos = new javax.swing.JComboBox<>();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPainelExibicao.setBorder(javax.swing.BorderFactory.createTitledBorder("Exibi��o"));
        jPainelExibicao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPainelExibicaoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPainelExibicaoLayout = new javax.swing.GroupLayout(jPainelExibicao);
        jPainelExibicao.setLayout(jPainelExibicaoLayout);
        jPainelExibicaoLayout.setHorizontalGroup(
            jPainelExibicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );
        jPainelExibicaoLayout.setVerticalGroup(
            jPainelExibicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jBotaoLimpar.setText("Limpar");
        jBotaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotaoLimparActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Excluir"));

        jComboBoxExcluir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ponto", "Caminho" }));

        jLabel1.setText("Nome do Ponto:");

        jLabel2.setText("Primeiro Ponto:");

        jLabel3.setText("Segundo Ponto");

        jBotaoOk.setLabel("OK");
        jBotaoOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotaoOkActionPerformed(evt);
            }
        });

        jBotaoCancelar.setText("Cancelar");
        jBotaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotaoCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextoPontoExcluir)
                    .addComponent(jComboBoxExcluir, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextoExcluirExtremidade1)
                    .addComponent(jTextoExcluirExtremidade2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jBotaoOk)
                                .addGap(18, 18, 18)
                                .addComponent(jBotaoCancelar)))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextoPontoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextoExcluirExtremidade1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextoExcluirExtremidade2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBotaoOk)
                    .addComponent(jBotaoCancelar))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jBotaoRedesenhar.setText("Redesenhar");
        jBotaoRedesenhar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotaoRedesenharActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Caminho m�nimo"));

        jTextoEstacionamento.setName(""); // NOI18N

        jLabel4.setText("Estacionamento:");

        jLabel5.setText("Ponto de coleta:");

        jTextoPontoColeta.setToolTipText("");

        jLabel6.setText("Banco:");

        jBotaoGerarCaminhoMinimo.setText("OK");
        jBotaoGerarCaminhoMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotaoGerarCaminhoMinimoActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Possiveis caminhos"));

        jComboBoxPossiveisCaminhos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Possiveis Caminhos" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabelaCaminhosMinimos, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jComboBoxPossiveisCaminhos, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxPossiveisCaminhos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabelaCaminhosMinimos))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jBotaoGerarCaminhoMinimo))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextoPontoColeta, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(jTextoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextoEstacionamento)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextoEstacionamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextoPontoColeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jBotaoGerarCaminhoMinimo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        jTextoEstacionamento.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jBotaoLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBotaoRedesenhar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jPainelExibicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jBotaoLimpar)
                                    .addComponent(jBotaoRedesenhar))
                                .addGap(0, 22, Short.MAX_VALUE))
                            .addComponent(jPainelExibicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3.getAccessibleContext().setAccessibleName("DDDDFS");

        getAccessibleContext().setAccessibleName("");
        getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Define as a��es de mouse no painel de exibi��o principal.
     * @param evt evento gerado
     */
    private void jPainelExibicaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPainelExibicaoMousePressed
        int posicaoCliqueX = evt.getX(), posicaoCliqueY = evt.getY(); //obt�m as coordenadas da tela do local onde o clique foi realizado
        
        /* Realiza o cadastro de um novo ponto. */
        if (!gerenciadorTela.isVerticeNaTela(posicaoCliqueX, posicaoCliqueY)) { //verifica se o clique foi sob um ponto na tela que n�o tem v�rtice
            String nomePonto = JOptionPane.showInputDialog(null, "Insira o nome do ponto:"); //obt�m o poss�vel nome para o ponto
                        
            if(nomePonto == null) { //caso a jane�a de inserir o nome do ponto seja fechada
                return;
            }
            
            VerticeVisual verticeAtual = new VerticeVisual(posicaoCliqueX, posicaoCliqueY, nomePonto); //cria um novo VerticeVisual com as informa��es obtidas no cadastro
            
            /* Realiza o cadastro do novo v�rtice. */
            try {
                controller.cadastrarPonto(nomePonto); 
            } catch (NomeInvalidoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            } catch (CadastrarPontoExistenteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            }
            
            gerenciadorTela.adicionarVertice(verticeAtual); //adiciona o v�rtice no registro de v�rtices visuais da tela
            Desenhador.pintarCirculo(jPainelExibicao.getGraphics(), posicaoCliqueX, posicaoCliqueY, nomePonto); //pinta o novo v�rtice na tela

        } else if (!selecionouPonto) { // caso o clique seja sobre um ponto e caso n�o tenha tido um clique em ponto anteriormente
            /* Realiza a primeira parte do cadastro de um caminho. */
            
            arestaCriada = new ArestaVisual(); //obt�m uma nova inst�ncia de ArestaVisual
            arestaCriada.setExtremidade1(gerenciadorTela.obterVertice(posicaoCliqueX, posicaoCliqueY)); //define uma das extremidades da aresta
            selecionouPonto = true; //define que o ponto foi selecionado
            
            VerticeVisual verticeVisual = gerenciadorTela.obterVertice(posicaoCliqueX, posicaoCliqueY); //obt�m o VerticeVisual que corresponde ao ponto selecionado
            Desenhador.pintarCirculoSelecionado(jPainelExibicao.getGraphics(), verticeVisual.getPosicaoX(), verticeVisual.getPosicaoY(), Color.GREEN); //pinta o c�rculo do ponto selecionado
        } else { //caso tenho sido selecionado um ponto e j� tenha sido selecionado outro ponto anteriormente
            VerticeVisual vertice = gerenciadorTela.obterVertice(posicaoCliqueX, posicaoCliqueY); //obt�m o correspondente em VerticeVisual do ponto selecionado na tela
            
            Desenhador.pintarCirculoSelecionado(jPainelExibicao.getGraphics(), vertice.getPosicaoX(), vertice.getPosicaoY(), Color.GREEN); //pinta internamente o ponto selecionado da tela
            
            String tempoString = JOptionPane.showInputDialog("Informe o tempo do caminho:"); //solicita o tempo do caminho
            
            if(tempoString == null) { //caso a janela seja fechada
                redesenhar();
                selecionouPonto = false;
                arestaCriada = new ArestaVisual();
                return;
            } else if(tempoString.trim().isEmpty()) { //caso seja inserido um tempo vazio
                /* Exibe mensagem de erro e redesenha a tela.*/
                JOptionPane.showMessageDialog(null, "O tempo inserido � inv�lido!");
                redesenhar();
                selecionouPonto = false;
                arestaCriada = new ArestaVisual();
                return;
            }
            
            int tempo = Integer.parseInt(tempoString); // recebe o tempo de dura��o e converte em inteiro

            arestaCriada.setExtremidade2(gerenciadorTela.obterVertice(posicaoCliqueX, posicaoCliqueY)); //define outra extremidade da ArestaVisual
            arestaCriada.setTempo(tempo); //define o tempo da aresta
            
            try {
                controller.cadastrarCaminho(arestaCriada.getExtremidade1().getNome(), arestaCriada.getExtremidade2().getNome(), tempo); //realiza o cadastro do novo ponto no grafo
            } catch (PontoInexistenteException ex) {
                /* Exibe mensagem de erro e reinicia a aresta visual que estava sendo criada.*/
                JOptionPane.showMessageDialog(null, ex.getMessage());
                arestaCriada = new ArestaVisual();
                return;
            } catch (NomeInvalidoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            } catch (CaminhoJaExisteException ex) {
                /* Exibe mensagem de erro e redesenha a tela.*/
                JOptionPane.showMessageDialog(null, ex.getMessage());
                redesenhar();
                selecionouPonto = false;
                return;
            }
           
            gerenciadorTela.adicionarAresta(arestaCriada); //adiciona a aresta visual no registro de arestas da tela
            selecionouPonto = false; //define o indicativo de ponto selecionado como falso
            
            /* Pinta a linha e o c�rculo do v�rtice visual ap�s a cria��o da aresta. */
            Desenhador.pintarLinha(jPainelExibicao.getGraphics(), arestaCriada.getExtremidade1().getPosicaoX(), arestaCriada.getExtremidade1().getPosicaoY(), arestaCriada.getExtremidade2().getPosicaoX(), arestaCriada.getExtremidade2().getPosicaoY(), arestaCriada.getTempo());
            Desenhador.pintarCirculoSelecionado(jPainelExibicao.getGraphics(), arestaCriada.getExtremidade1().getPosicaoX(), arestaCriada.getExtremidade1().getPosicaoY(), Color.WHITE);
            Desenhador.pintarCirculoSelecionado(jPainelExibicao.getGraphics(), arestaCriada.getExtremidade2().getPosicaoX(), arestaCriada.getExtremidade2().getPosicaoY(), Color.WHITE);
        }
    }//GEN-LAST:event_jPainelExibicaoMousePressed

    /**
     * Define a a��o de clique no bot�o de limpar.
     * @param evt evento gerado pelo clique
     */    
    private void jBotaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotaoLimparActionPerformed
        gerenciadorTela = new GerenciadorTela(); //obt�m uma nova inst�ncia do gerenciador de tela
        controller = new ForteSeguroController(); //reinicializa o controller
        jPainelExibicao.repaint(); //repinta o painel de exibi��o
    }//GEN-LAST:event_jBotaoLimparActionPerformed

    /**
     * Define a a��o de clique no bot�o de redesenhar.
     * @param evt evento gerado pelo clique
     */
    private void jBotaoRedesenharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotaoRedesenharActionPerformed
        redesenhar(); //redesenha a tela
    }//GEN-LAST:event_jBotaoRedesenharActionPerformed

    /**
     * Define a a��o de clique no bot�o de confirmar a exclus�o.
     * @param evt evento gerado pelo clique
     */    
    private void jBotaoOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotaoOkActionPerformed
        if (jComboBoxExcluir.getSelectedIndex() == 0) { //caso seja a primeira op��o da ComboBox (excluir ponto)
            if (jTextoPontoExcluir.getText() == null || jTextoPontoExcluir.getText().isEmpty()) { //caso o campo esteja vazio
                JOptionPane.showMessageDialog(null, "Campo obrigat�rio n�o preenchido!");
                return;
            } else {
                try {
                    controller.removerPonto(jTextoPontoExcluir.getText()); //tenta remover o ponto
                    jComboBoxPossiveisCaminhos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Possiveis Caminhos" }));// coloca a caixa de combina��o de possiveis rotas no estado inicial, caso a mesma ja tenha sido inicializada
                } catch (PontoInexistenteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                } catch (NomeInvalidoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
                
                gerenciadorTela.excluirVertice(jTextoPontoExcluir.getText()); //remove o v�rtice visual do registro de v�rtices visuais da tela
                jTextoPontoExcluir.setText("");// limpa o texto
            }
            
        } else {
            try {
                controller.removerCaminho(jTextoExcluirExtremidade1.getText(), jTextoExcluirExtremidade2.getText()); //tenta realizar a remo��o do caminho
                jComboBoxPossiveisCaminhos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Possiveis Caminhos" }));// coloca a caixa de combina��o de possiveis rotas no estado inicial, caso a mesma ja tenha sido inicializada
            } catch (PontoInexistenteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            } catch (RemoverCaminhoInexistenteException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            } catch (NomeInvalidoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            }
            
            gerenciadorTela.excluirAresta(jTextoExcluirExtremidade1.getText(), jTextoExcluirExtremidade2.getText()); //remove a aresta visual do registro de arestas visuais da tela
            
            jTextoExcluirExtremidade1.setText("");
            jTextoExcluirExtremidade2.setText("");
        }
        
        /* Repinta e redesenha o painel principal. */
        jPainelExibicao.paint(jPainelExibicao.getGraphics());
        redesenhar(); //redesenha a tela
    }//GEN-LAST:event_jBotaoOkActionPerformed

    /**
     * Define a a��o de clique no bot�o de cancelar.
     * @param evt evento gerado pelo clique
     */
    private void jBotaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotaoCancelarActionPerformed
        /* Limpa os campos do painel de exclus�o. */
        jTextoPontoExcluir.setText("");
        jTextoExcluirExtremidade1.setText("");
        jTextoExcluirExtremidade2.setText("");
    }//GEN-LAST:event_jBotaoCancelarActionPerformed
    
    /**
     * Preenche a tabela de caminhos m�nimos.
     * @param caminhosMinimos lista de listas de caminhos m�nimos
     */
    private void preencherTabelaCaminhos(List<List<Vertice>> caminhosMinimos) {
        int tamanho = caminhosMinimos.size(); //obt�m a quantidade de caminhos m�nimos
        String caminhoEmTexto = "Rota::"; //representa��o em texto do caminho
        JEditorPane jEditorPane; //componente de texto edit�vel
        JScrollPane jScrollPane; //barra de rolagem       
        
        jTabelaCaminhosMinimos.setVisible(true); //torna a tabela de caminhos m�nimos vis�vl
        jTabelaCaminhosMinimos.removeAll(); //remove todas as antigas entradas
        
        for (int i = 0; i < tamanho; i++) {
            jEditorPane = new JEditorPane(); //obt�m uma nova inst�ncia do componente de texto edit�vel
            jScrollPane = new JScrollPane(); //obt�m uma nova inst�ncia da barra de rolagem
            
            jEditorPane.setText(controller.obterRotaEmTexto(caminhosMinimos.get(i))); //escreve o caminho no componente de texto
            jScrollPane.setViewportView(jEditorPane); //adiciona o componente de texto na barra de rolagem
            
            jTabelaCaminhosMinimos.addTab((caminhoEmTexto + (i + 1)), jScrollPane); //adiciona um novo caminho na tabela de caminhos m�nimos
            caminhoEmTexto = "Rota::";
        }
    }

    /**
     * Define a��es para o clique no bot�o que confirma a gera��o de caminho m�nimo.
     * 
     * @param evt evento resultante do clique no bot�o
     */
    private void jBotaoGerarCaminhoMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotaoGerarCaminhoMinimoActionPerformed
        try {
            caminhosMinimos = controller.obterCaminhoMinimo(jTextoEstacionamento.getText(), jTextoPontoColeta.getText(), jTextoBanco.getText()); //tenta obter os caminhos m�nimos
            int numeroCaminhos = caminhosMinimos.size(); //obt�m aquantidade de caminhos m�nimos
            
            String[] caminhos = new String[numeroCaminhos]; //cria vetor de String para a Combobox de caminhos m�nimos
            String nomeCaminhos = "Op��o :::";
            
            if(caminhosMinimos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "HUMMM");
            }
            
            /* Adiciona um n�mero indicativo do caminho em cada op��o da Combobox de caminhos m�nimos. */
            for (int i = 0; i < numeroCaminhos; i++) { 
                caminhos[i] = (nomeCaminhos + " " + (i + 1));
                nomeCaminhos = "Op��o :::";
            }
            
            jComboBoxPossiveisCaminhos.setModel(new javax.swing.DefaultComboBoxModel<>(caminhos)); //adiciona o vetor de Strings na Combobox
            preencherTabelaCaminhos(caminhosMinimos); //preenche a tabela de caminhos m�nimos
            gerenciadorTela.desenharCaminhoMinimoUnico(caminhosMinimos.get(0), jPainelExibicao.getGraphics()); //desenha um caminho m�nimo �nicos
        } catch (PontoInexistenteException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (NomeInvalidoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (PontoInatingivelException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (OrigemEDestinoIguaisException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        /* Limpa os campos de c�lculo do caminho m�nimo. */
        jTextoBanco.setText("");
        jTextoEstacionamento.setText("");
        jTextoPontoColeta.setText("");
    }//GEN-LAST:event_jBotaoGerarCaminhoMinimoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBotaoCancelar;
    private javax.swing.JButton jBotaoGerarCaminhoMinimo;
    private javax.swing.JButton jBotaoLimpar;
    private javax.swing.JButton jBotaoOk;
    private javax.swing.JButton jBotaoRedesenhar;
    private javax.swing.JComboBox<String> jComboBoxExcluir;
    private javax.swing.JComboBox<String> jComboBoxPossiveisCaminhos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPainelExibicao;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabelaCaminhosMinimos;
    private javax.swing.JTextField jTextoBanco;
    private javax.swing.JTextField jTextoEstacionamento;
    private javax.swing.JTextField jTextoExcluirExtremidade1;
    private javax.swing.JTextField jTextoExcluirExtremidade2;
    private javax.swing.JTextField jTextoPontoColeta;
    private javax.swing.JTextField jTextoPontoExcluir;
    // End of variables declaration//GEN-END:variables

    /**
     * Redesenha o painel principal.
     */
    public void redesenhar() {
        Iterator<VerticeVisual> iVertices = gerenciadorTela.getListaVerticesVisuais().iterator(); //obt�m o iterador da lista de v�rtices visuais
        Iterator<ArestaVisual> iArestas = gerenciadorTela.getListaArestasVisuais().iterator(); //obt�m o iterador da lista de arestas visuais
        VerticeVisual verticeAux; //refer�ncia auxiliar para v�rtice visual
        ArestaVisual arestaAux; //refer�ncia auxiliar para aresta visual
        
        while (iVertices.hasNext()) { //enquanto houver um pr�ximo v�rtice visual
            verticeAux = iVertices.next(); //obt�m o pr�ximo v�rtice visual
            Desenhador.pintarCirculo(jPainelExibicao.getGraphics(), verticeAux.getPosicaoX(), verticeAux.getPosicaoY(), verticeAux.getNome()); //pinta o c�rculo do v�rtice
        }
        
        while (iArestas.hasNext()) { //enquanto houver uma pr�xima aresta visual
            arestaAux = iArestas.next(); //obt�m a pr�xima aresta visual
            Desenhador.pintarLinha(jPainelExibicao.getGraphics(), arestaAux.getExtremidade1().getPosicaoX(), arestaAux.getExtremidade1().getPosicaoY(), arestaAux.getExtremidade2().getPosicaoX(), arestaAux.getExtremidade2().getPosicaoY(), arestaAux.getTempo()); //pinta a aresta visual
        }
    }
    
}
