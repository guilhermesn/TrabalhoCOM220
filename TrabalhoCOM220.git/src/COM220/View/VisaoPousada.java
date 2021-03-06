package COM220.View;


import COM220.Control.CtrlPousada;
import COM220.Model.Quarto;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class VisaoPousada extends JFrame implements ActionListener {

    private JPanel cards;
    private CtrlPousada controle;
    private JTextField precoQuarto = new JTextField();
    private JTextField descricaoQuarto = new JTextField();
    private JTextField descricaoEditQuarto = new JTextField(25);
    private JTextField numeroQuarto = new JTextField();
    private JTextField precoEditQuarto = new JTextField(10);

    private JTextField cpfCliente = new JTextField();
    private JTextField nomeCliente = new JTextField();
    private JTextField enderecoCliente = new JTextField();
    private JTextField telefoneCliente = new JTextField();

    private JTextField EdtNomeCliente = new JTextField();
    private JTextField EdtEnderecoCliente = new JTextField();
    private JTextField EdtTelefoneCliente = new JTextField();
    private JTextArea textArea1 = new JTextArea(5, 20);
    private JTextArea textArea2 = new JTextArea(5, 20);
    private JTextArea textArea3 = new JTextArea(5, 20);
    private JTextArea textArea4 = new JTextArea(5, 20);

    private JFormattedTextField dataInicialqt;
    private JFormattedTextField dataFinalqt;

    private JFormattedTextField dataInicio;
    private JFormattedTextField dataFim;

    private JFormattedTextField dataIniciorRelatorio;
    private JFormattedTextField dataFimRelatorio;

    private JButton bCadastrarCliente;
    private JButton bCadastrarQuarto;
    private JButton bCadastrarReserva;
    private JButton bEditarQuarto;
    private JButton bExcluirQuarto;
    private JButton bEditarCliente;
    private JButton bEditaCliente;
    private JButton bExcluirCliente;
    private JButton bVoltarCliente;
    private JButton bVoltarQuarto;
    private JButton bModificarQuarto;
    private JButton bPagarReserva;

    private JButton bReservarQuartoDisponiveis;

    private JButton bExcluirReserva;

    private JPanel janelaPrincipal;
    private JComboBox jCBCliente = new javax.swing.JComboBox();

    private javax.swing.JMenuItem jMenuCadastraQuarto;
    private javax.swing.JMenuItem jMenuCadastraCliente;
    private javax.swing.JMenuItem jMenuCadastraReserva;
    private javax.swing.JMenuItem jMenuConsultaQuarto;
    private javax.swing.JMenuItem jMenuConsultaQuartoDisponivel;
    private javax.swing.JMenuItem jMenuConsultaCliente;
    private javax.swing.JMenuItem jMenuConsultaReserva;
    private javax.swing.JMenuItem jMenuPagamento;
    private javax.swing.JMenuItem jRelatorioCancelado;
    private javax.swing.JMenuItem jRelatorioData;
    private javax.swing.JMenuItem jRelatorioDoDia;
    private javax.swing.JMenuItem jRelatorioNaoPG;
    private javax.swing.JMenu jCadastrar;
    private javax.swing.JMenu jVisualisar;
    private javax.swing.JMenu jRelatorio;
    private javax.swing.JMenuBar jMenuBar1;

    private String NEditQuarto;
    private String NEditCPF;
    private JTable jTabelaQuarto;
    private JTable jTabelaCliente;
    private JTable jTabelaQuartoDisp;
    private JTable jTabelaQuartoDispRes;
    private JTable jTabelaQuartoARes;
    private JTable jTabelaReserva;
    private JTable jTabelaRelatorio;
    private CardLayout layout;
    private DefaultTableModel modeloVRes = new nonEditableJTable();
    private DefaultTableModel modeloQuartos = new nonEditableJTable();
    private DefaultTableModel modelo = new nonEditableJTable();
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private DefaultTableModel modelo2 = new nonEditableJTable();
    private DefaultTableModel modelo1 = new nonEditableJTable();
    private DefaultTableModel modeloRelatorio = new nonEditableJTable();
    private String JTF_data;

    public VisaoPousada(CtrlPousada pousada) {
        iniciaBotoes();
        this.controle = pousada;
    }

    public void atualizaInterface() {

        JTF_data = (new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));

        cards.add("Menu", gerarPMain());
        cards.add("CadastrarCliente", gerarPCadastraCliente());
        cards.add("EditarCliente", gerarPEditarCliente());
        cards.add("CadastrarQuarto", gerarPCadastraQuarto());
        cards.add("FazerReserva", gerarPReservarQuarto());
        cards.add("VisualizarReserva", gerarPVisualizarReserva());
        cards.add("VisualizarCliente", gerarPConsultaCliente());
        cards.add("VisualizarQuarto", gerarPConsultaQuarto());
        cards.add("ConfirmaQuarto", gerarPConfirmaQuarto());
        cards.add("VisualizarQuartoDisponivel", gerarPQuartoDisponivel());
        cards.add("ConfirmaCliente", gerarPConfirmaCliente());
        cards.add("EditarQuarto", gerarPEditarQuarto());
        cards.add("RealizarPagamento", gerarPRealizarPagamento());

        cards.add("GerarRelatorioPeriodo", gerarPRelatorioPeriodo());
        cards.add("GerarRelatorioEfetivar", gerarPRelatorioEfetivar());
        cards.add("GerarRelatorioCanceladas", gerarPRelatorioCanceladas());
        cards.add("GerarRelatorioNaoPagos", gerarPRelatorioNaoPagos());

    }

    private void iniciaBotoes() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jCadastrar = new javax.swing.JMenu();
        jVisualisar = new javax.swing.JMenu();
        jRelatorio = new javax.swing.JMenu();
        jMenuCadastraQuarto = new javax.swing.JMenuItem();
        jMenuCadastraCliente = new javax.swing.JMenuItem();
        jMenuCadastraReserva = new javax.swing.JMenuItem();
        jMenuConsultaQuarto = new javax.swing.JMenuItem();
        jMenuConsultaQuartoDisponivel = new javax.swing.JMenuItem();
        jMenuConsultaCliente = new javax.swing.JMenuItem();
        jMenuConsultaReserva = new javax.swing.JMenuItem();
        jMenuPagamento = new javax.swing.JMenuItem();
        jRelatorioCancelado = new javax.swing.JMenuItem();
        jRelatorioData = new javax.swing.JMenuItem();
        jRelatorioDoDia = new javax.swing.JMenuItem();
        jRelatorioNaoPG = new javax.swing.JMenuItem();

        jRelatorioNaoPG.setText("Relatorio de não pagos");
        jRelatorioDoDia.setText("Relatorio a serem efetivadas");
        jRelatorioCancelado.setText("Gerar Relatório de Reservas Canceladas");
        jRelatorioData.setText("Gerar no período");

        jRelatorioCancelado.addActionListener(this);
        jRelatorio.add(jRelatorioData);
        jRelatorio.add(jRelatorioCancelado);
        jCadastrar.setText("Cadastrar");
        jRelatorioData.addActionListener(this);
        jMenuBar1.add(jCadastrar);

        jRelatorio.add(jRelatorioDoDia);
        jRelatorio.add(jRelatorioNaoPG);
        jRelatorioDoDia.addActionListener(this);
        jRelatorioNaoPG.addActionListener(this);

        jVisualisar.setText("Consultar");
        jMenuBar1.add(jVisualisar);

        jRelatorio.setText("Relatorio");
        jMenuBar1.add(jRelatorio);

        jMenuCadastraQuarto.setText("Quarto");
        jMenuCadastraQuarto.addActionListener(this);
        jCadastrar.add(jMenuCadastraQuarto);

        jMenuCadastraCliente.setText("Cliente");
        jMenuCadastraCliente.addActionListener(this);
        jCadastrar.add(jMenuCadastraCliente);

        jMenuCadastraReserva.setText("Reserva");
        jMenuCadastraReserva.addActionListener(this);
        jCadastrar.add(jMenuCadastraReserva);

        jMenuConsultaQuarto.setText("Quartos");
        jMenuConsultaQuarto.addActionListener(this);
        jVisualisar.add(jMenuConsultaQuarto);

        jMenuConsultaQuartoDisponivel.setText("Quartos Disponíveis");
        jMenuConsultaQuartoDisponivel.addActionListener(this);
        jVisualisar.add(jMenuConsultaQuartoDisponivel);

        jMenuConsultaCliente.setText("Clientes");
        jMenuConsultaCliente.addActionListener(this);
        jVisualisar.add(jMenuConsultaCliente);

        jMenuConsultaReserva.setText("Reservas");
        jMenuConsultaReserva.addActionListener(this);
        jMenuPagamento.setText("Pagamentos");
        jMenuPagamento.addActionListener(this);
        jVisualisar.add(jMenuConsultaReserva);
        jCadastrar.add(jMenuPagamento);

        bCadastrarQuarto = new JButton("Cadastrar");
        bVoltarCliente = new JButton("Voltar");
        bCadastrarCliente = new JButton("Cadastrar");
        bEditarCliente = new JButton("Editar Cliente");
        bEditaCliente = new JButton("Alterar");
        bExcluirCliente = new JButton("Excluir Cliente");
        bCadastrarReserva = new JButton("Cadastrar");
        bEditarQuarto = new JButton("Editar Quarto");
        bVoltarQuarto = new JButton("Voltar");
        bModificarQuarto = new JButton("Modificar");

        bReservarQuartoDisponiveis = new JButton("Reservar");

        bCadastrarQuarto.addActionListener(this);
        bVoltarCliente.addActionListener(this);
        bCadastrarCliente.addActionListener(this);
        bEditaCliente.addActionListener(this);
        bEditarCliente.addActionListener(this);
        bExcluirCliente.addActionListener(this);
        bCadastrarReserva.addActionListener(this);
        bEditarQuarto.addActionListener(this);
        bVoltarQuarto.addActionListener(this);
        bModificarQuarto.addActionListener(this);

        bReservarQuartoDisponiveis.addActionListener(this);

        cpfCliente = new JTextField(11);
        nomeCliente = new JTextField(15);
        enderecoCliente = new JTextField(20);
        telefoneCliente = new JTextField(15);
        numeroQuarto = new JTextField(5);
        precoQuarto = new JTextField(10);
        descricaoQuarto = new JTextField(25);
        EdtNomeCliente = new JTextField(15);
        EdtEnderecoCliente = new JTextField(20);
        EdtTelefoneCliente = new JTextField(15);
    }

    public void gerarInterface() {

        cards = new JPanel();
        cards.setLayout(new CardLayout());

        iniciaBotoes();
        atualizaInterface();

        janelaPrincipal = new JPanel(new BorderLayout());
        janelaPrincipal.add(jMenuBar1, BorderLayout.PAGE_START);
        janelaPrincipal.add(cards, BorderLayout.CENTER);

        getContentPane().add(janelaPrincipal);

        setSize(1130, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pousada");

        setVisible(true);
    }

    public JPanel gerarPMain() {
        Icon logo = new ImageIcon("logo.jpg");
        JLabel imagem = new JLabel(logo);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 1));
        p.add(imagem);

        return p;
    }

    public JPanel gerarPRelatorioPeriodo() {
        JPanel panelLista = new JPanel();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JButton bPesquisarRes;
        JButton bEscolherPasta = new JButton("Salvar");

        bPesquisarRes = new JButton("Pesquisar");

        MaskFormatter mascaraData = null;
        try {
            mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
        } catch (ParseException excp) {
            System.err.println("Erro na formatação: " + excp.getMessage());
        }

        bEscolherPasta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date dateini;
                Date datefim;

                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    dateini = formatter.parse(dataIniciorRelatorio.getText());
                    datefim = formatter.parse(dataFimRelatorio.getText());
                    if (datefim.before(dateini)) {
                        textArea1.setText(controle.relatorioPorData(dateini, datefim));
                        try {
                            controle.SalvarRelatorioPorData(escolheArquivo(), dateini, datefim);
                        } catch (IOException ex) {
                            Logger.getLogger(VisaoPousada.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                          JOptionPane.showMessageDialog(null, "A data inicial não pode ser menor que a data final!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Datas invalidas!", "Erro", JOptionPane.ERROR_MESSAGE);

                }

            }
        });
        JScrollPane scrollPane = new JScrollPane(textArea1);
        textArea1.setEditable(false);

        dataIniciorRelatorio = new JFormattedTextField(mascaraData);
        dataFimRelatorio = new JFormattedTextField(mascaraData);

        bPesquisarRes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Date dateini;
                Date datefim;

                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    dateini = formatter.parse(dataIniciorRelatorio.getText());
                    datefim = formatter.parse(dataFimRelatorio.getText());
                     if (dateini.before(datefim) || dateini.equals(datefim)) {
                    textArea1.setText(controle.relatorioPorData(dateini, datefim));
                    } else {
                          JOptionPane.showMessageDialog(null, "A data inicial não pode ser menor que data final!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Data inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        panelLista.setLayout(new BorderLayout());
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable

        panelLista.add(scrollPane, BorderLayout.CENTER);

        dataIniciorRelatorio.setText(JTF_data);
        dataFimRelatorio.setText(JTF_data);

        p1.add(panelLista);
        p1.add(BorderLayout.CENTER, panelLista);
        p2.add(BorderLayout.NORTH, new JLabel("Inicio:"));
        p2.add(BorderLayout.NORTH, dataIniciorRelatorio);
        p2.add(BorderLayout.NORTH, new JLabel(" Fim:"));
        p2.add(BorderLayout.NORTH, dataFimRelatorio);
        p2.add(BorderLayout.NORTH, bPesquisarRes);
        p2.add(BorderLayout.NORTH, bEscolherPasta);

        p1.add(BorderLayout.NORTH, p2);

        return p1;

    }

    public JPanel gerarPRelatorioEfetivar() {
        JPanel panelLista = new JPanel();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JButton bEscolherPasta = new JButton("Salvar");

        bEscolherPasta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    controle.SalvarRelatorioReservaDoDia(escolheArquivo());
                } catch (IOException ex) {
                    Logger.getLogger(VisaoPousada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(textArea2);
        textArea2.setEditable(false);

        textArea2.setText(this.controle.GeraRelatorioReservaDoDia());
        panelLista.setLayout(new BorderLayout());
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable

        panelLista.add(scrollPane, BorderLayout.CENTER);

        p1.add(panelLista);
        p1.add(BorderLayout.CENTER, panelLista);
        p2.add(BorderLayout.NORTH, bEscolherPasta);

        p1.add(BorderLayout.NORTH, p2);

        return p1;

    }

    public JPanel gerarPRelatorioCanceladas() {
        JPanel panelLista = new JPanel();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());

        JButton bEscolherPasta = new JButton("Salvar");

        bEscolherPasta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    controle.SalvarRelatorioReservaCancelada(escolheArquivo());
                } catch (IOException ex) {
                    Logger.getLogger(VisaoPousada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(textArea3);
        textArea3.setEditable(false);
        textArea3.setText(this.controle.GeraRelatorioReservaCancelada());

        panelLista.setLayout(new BorderLayout());
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable
        panelLista.add(scrollPane, BorderLayout.CENTER);

        p1.add(panelLista);
        p1.add(BorderLayout.CENTER, panelLista);
        p2.add(BorderLayout.NORTH, bEscolherPasta);

        p1.add(BorderLayout.NORTH, p2);

        return p1;

    }

    public JPanel gerarPRelatorioNaoPagos() {
        JPanel panelLista = new JPanel();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());

        JButton bEscolherPasta = new JButton("Salvar");

        bEscolherPasta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    controle.SalvarRelatorioReservaNaoPaga(escolheArquivo());
                } catch (IOException ex) {
                    Logger.getLogger(VisaoPousada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(textArea4);
        textArea4.setEditable(false);
        textArea4.setText(this.controle.GeraRelatorioReservaNaoPaga());

        panelLista.setLayout(new BorderLayout());
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable
        panelLista.add(scrollPane, BorderLayout.CENTER);

        p1.add(panelLista);
        p1.add(BorderLayout.CENTER, panelLista);
        p2.add(BorderLayout.NORTH, bEscolherPasta);

        p1.add(BorderLayout.NORTH, p2);

        return p1;

    }

    public JPanel gerarPCadastraCliente() {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 3, 3, 0);
        gc.gridwidth = 1;
        gc.gridheight = 1;

        JPanel p1 = new JPanel(grid);

        gc.gridx = 0;
        gc.gridy = 0;
        p1.add(new JLabel("CPF:"), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        p1.add(cpfCliente, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        p1.add(new JLabel("Nome:"), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        p1.add(nomeCliente, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        p1.add(new JLabel("Endereço:"), gc);
        gc.gridx = 1;
        gc.gridy = 2;
        p1.add(enderecoCliente, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        p1.add(new JLabel("Telefone:"), gc);
        gc.gridx = 1;
        gc.gridy = 3;
        p1.add(telefoneCliente, gc);

        gc.gridx = 1;
        gc.gridy = 4;
        p1.add(bCadastrarCliente, gc);
        return p1;
    }

    public JPanel gerarPRealizarPagamento() {
        JPanel panelLista = new JPanel();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JScrollPane barraRolagem; // ScrollBar para panelControle

        modeloVRes = new nonEditableJTable();
        jTabelaReserva = new JTable(modeloVRes);

        // Colunas da lista de Clientes
        modeloVRes.addColumn("Numero");
        modeloVRes.addColumn("Entrada");
        modeloVRes.addColumn("Saida");
        modeloVRes.addColumn("Cliente");
        modeloVRes.addColumn("Quartos");
        modeloVRes.addColumn("Valor Pago");
        modeloVRes.addColumn("Valor Restante");
        modeloVRes.addColumn("Estado");

        bPagarReserva = new JButton("Realizar pagamento");

        for (int i = 0; i < this.controle.ListarReservas().size(); i++) {

            Object[] dados = {controle.ListarReservas().get(i).getNumeroReserva(), controle.ListarReservas().get(i).getEntrada(), controle.ListarReservas().get(i).getSaida(), controle.ListarReservas().get(i).getCpf(), controle.ListarReservas().get(i).getQuartosVet(), controle.CalculaValPG(controle.ListarReservas().get(i).getNumeroReserva()), controle.CalculaValAPG(controle.ListarReservas().get(i).getNumeroReserva())};
            modeloVRes.addRow(dados);
        }

        bPagarReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numeroReserva = jTabelaReserva.getValueAt(jTabelaReserva.getSelectedRow(), 0).toString();
                if (controle.CalculaValAPG(Integer.parseInt(numeroReserva)) > 0) {
                    String valor = JOptionPane.showInputDialog(null, "Para garantir a reserva o valor a ser pago é: " + (controle.getReserva(Integer.parseInt(numeroReserva)).getPgtReserva().getValorTotal() - controle.getReserva(Integer.parseInt(numeroReserva)).getPgtReserva().getValorPg()) + "\nO valor total a ser pago é: " + controle.CalculaValAPG(Integer.parseInt(numeroReserva)));
                    controle.realizaPagamento(Integer.parseInt(numeroReserva), Double.parseDouble(valor));
                } else {
                    JOptionPane.showMessageDialog(null, "Esta reserva já está paga!");
                }
                atualizaInterface();
                layout = (CardLayout) cards.getLayout();
                layout.show(cards, "RealizarPagamento");

            }
        });

        jTabelaReserva.getSelectionModel().setSelectionInterval(0, 0); //Inicia a tabela com a primeira linha selecionada
        barraRolagem = new JScrollPane(jTabelaReserva);

        panelLista.setLayout(new BorderLayout());
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable

        panelLista.add(barraRolagem, BorderLayout.CENTER);

        p1.add(panelLista);

        p1.add(BorderLayout.CENTER, panelLista);

        p2.add(BorderLayout.SOUTH, bPagarReserva);
        p1.add(BorderLayout.SOUTH, p2);

        return p1;
    }

    public JPanel gerarPEditarCliente() {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 3, 3, 0);
        gc.gridwidth = 1;
        gc.gridheight = 1;

        JPanel p1 = new JPanel(grid);
        gc.gridx = 0;
        gc.gridy = 0;
        p1.add(new JLabel("CPF:"), gc);

        gc.gridx = 1;
        gc.gridy = 0;
        p1.add(new JLabel(NEditCPF), gc);

        if (NEditCPF != null) {
            EdtNomeCliente.setText(controle.getCliente(NEditCPF).getEndereco());
            EdtEnderecoCliente.setText(controle.getCliente(NEditCPF).getEndereco());
            EdtTelefoneCliente.setText(controle.getCliente(NEditCPF).getTelefone());
        }

        gc.gridx = 0;
        gc.gridy = 1;
        p1.add(new JLabel("Nome:"), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        p1.add(EdtNomeCliente, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        p1.add(new JLabel("Endereço:"), gc);
        gc.gridx = 1;
        gc.gridy = 2;
        p1.add(EdtEnderecoCliente, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        p1.add(new JLabel("Telefone:"), gc);
        gc.gridx = 1;
        gc.gridy = 3;
        p1.add(EdtTelefoneCliente, gc);

        gc.gridx = 1;
        gc.gridy = 4;
        p1.add(bEditaCliente, gc);

        return p1;
    }

    public JPanel gerarPConfirmaQuarto() {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 3, 3, 0);
        gc.gridwidth = 1;
        gc.gridheight = 1;

        JPanel p1 = new JPanel(grid);

        gc.gridx = 0;
        gc.gridy = 0;
        p1.add(new JLabel("Quarto Cadastrado com Sucesso."), gc);

        gc.gridx = 0;
        gc.gridy = 1;
        p1.add(bVoltarQuarto, gc);

        return p1;
    }

    public JPanel gerarPConsultaCliente() {

        JPanel panelLista = new JPanel();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());

        JScrollPane barraRolagem; // ScrollBar para panelControle
        modelo = new nonEditableJTable();

        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Telefone");
        modelo.addColumn("Endereço");

        jTabelaCliente = new JTable(modelo);
        barraRolagem = new JScrollPane(jTabelaCliente);

        for (int i = 0; i < this.controle.ListaClientes().size(); i++) {
            Object[] dados = {this.controle.ListaClientes().get(i).getNome(), this.controle.ListaClientes().get(i).getCPF(), this.controle.ListaClientes().get(i).getTelefone(), this.controle.ListaClientes().get(i).getEndereco()};
            modelo.addRow(dados);
        }

        jTabelaCliente.getSelectionModel().setSelectionInterval(0, 0); //Inicia a tabela com a primeira linha selecionada

        panelLista.setLayout(new BorderLayout());
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable

        panelLista.add(barraRolagem, BorderLayout.CENTER);

        p1.add(panelLista);

        p1.add(BorderLayout.CENTER, panelLista);
        p2.add(bEditarCliente);
        p2.add(bExcluirCliente);

        p1.add(BorderLayout.CENTER, panelLista);
        p1.add(BorderLayout.SOUTH, p2);

        return p1;
    }

    public JPanel gerarPConsultaQuarto() {
        bExcluirQuarto = new JButton("Excluir Quarto");
        JPanel panelLista = new JPanel();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());

        JScrollPane barraRolagem; // ScrollBar para panelControle

        modeloQuartos = new nonEditableJTable();
        jTabelaQuarto = new JTable(modeloQuartos);

        // Colunas da lista de Clientes
        modeloQuartos.addColumn("Número");
        modeloQuartos.addColumn("Descrição");
        modeloQuartos.addColumn("Preço");

        for (int i = 0; i < this.controle.ListarQuartos().size(); i++) {
            int numero = this.controle.ListarQuartos().get(i).getNumero();
            String descricao = this.controle.ListarQuartos().get(i).getDescricao();
            double preco = this.controle.ListarQuartos().get(i).getPreco();

            Object[] dados = {numero, descricao, preco};
            modeloQuartos.addRow(dados);
        }

        jTabelaQuarto.getSelectionModel().setSelectionInterval(0, 0); //Inicia a tabela com a primeira linha selecionada
        barraRolagem = new JScrollPane(jTabelaQuarto);

        panelLista.setLayout(new BorderLayout());
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable

        panelLista.add(barraRolagem, BorderLayout.CENTER);

        p1.add(panelLista);
        bExcluirQuarto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int comfirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o quarto?");
                if (comfirmacao == 0) {
                    controle.RemoverQuarto(Integer.parseInt(jTabelaQuarto.getValueAt(jTabelaQuarto.getSelectedRow(), 0).toString()));
                    modeloQuartos.removeRow(jTabelaQuarto.getSelectedRow());
                }

            }
        });
        p1.add(BorderLayout.CENTER, panelLista);
        p2.add(BorderLayout.SOUTH, bEditarQuarto);
        p2.add(BorderLayout.SOUTH, bExcluirQuarto);
        p1.add(BorderLayout.SOUTH, p2);

        return p1;

    }

    public JPanel gerarPQuartoDisponivel() {
        JPanel panelLista = new JPanel();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JButton bPesquisarQuartoDisponiveis;
        bPesquisarQuartoDisponiveis = new JButton("Pesquisar");

        MaskFormatter mascaraData = null;
        try {
            mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
        } catch (ParseException excp) {
            System.err.println("Erro na formatação: " + excp.getMessage());
        }

        dataInicialqt = new JFormattedTextField(mascaraData);
        dataFinalqt = new JFormattedTextField(mascaraData);

        bPesquisarQuartoDisponiveis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Date dateini;
                Date datefim;

                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    dateini = formatter.parse(dataInicialqt.getText());
                    datefim = formatter.parse(dataFinalqt.getText());

                    for (int i = modelo.getRowCount(); i > 0; i--) {
                        modelo.removeRow(0);
                    }
                    for (int i = 0; i < controle.QuartoDisponiveis(dateini, datefim).size(); i++) {
                        int numero = controle.QuartoDisponiveis(dateini, datefim).get(i).getNumero();
                        String descricao = controle.QuartoDisponiveis(dateini, datefim).get(i).getDescricao();
                        double preco = controle.QuartoDisponiveis(dateini, datefim).get(i).getPreco();

                        Object[] dados = {numero, descricao, preco};
                        modelo.addRow(dados);
                    }

                } catch (ParseException ex) {
                    Logger.getLogger(VisaoPousada.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        JScrollPane barraRolagem; // ScrollBar para panelControle
        modelo = new nonEditableJTable();
        jTabelaQuartoDisp = new JTable(modelo);

        // Colunas da lista de Clientes
        modelo.addColumn("Número");
        modelo.addColumn("Descrição");
        modelo.addColumn("Preço");

        panelLista.setLayout(new BorderLayout());
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable
        barraRolagem = new JScrollPane(jTabelaQuartoDisp);

        panelLista.add(barraRolagem, BorderLayout.CENTER);

        dataInicialqt.setText(JTF_data);
        dataFinalqt.setText(JTF_data);

        p1.add(panelLista);
        p1.add(BorderLayout.CENTER, panelLista);
        p2.add(BorderLayout.NORTH, dataInicialqt);
        p2.add(BorderLayout.NORTH, dataFinalqt);
        p2.add(BorderLayout.NORTH, bPesquisarQuartoDisponiveis);
        //p1.add(BorderLayout.SOUTH, bReservarQuartoDisponiveis);
        p1.add(BorderLayout.NORTH, p2);

        return p1;

    }

    public JPanel gerarPConfirmaCliente() {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 3, 3, 0);
        gc.gridwidth = 1;
        gc.gridheight = 1;

        JPanel p1 = new JPanel(grid);

        gc.gridx = 0;
        gc.gridy = 0;
        p1.add(new JLabel("Cliente Cadastrado com Sucesso."), gc);

        gc.gridx = 0;
        gc.gridy = 1;
        p1.add(bVoltarCliente, gc);

        return p1;
    }

    public JPanel gerarPCadastraQuarto() {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 3, 3, 0);
        gc.gridwidth = 1;
        gc.gridheight = 1;

        JPanel p1 = new JPanel(grid);

        gc.gridx = 0;
        gc.gridy = 0;
        p1.add(new JLabel("Numero:"), gc);
        gc.gridx = 1;
        gc.gridy = 0;
        p1.add(numeroQuarto, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        p1.add(new JLabel("Preco:"), gc);
        gc.gridx = 1;
        gc.gridy = 1;
        p1.add(precoQuarto, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        p1.add(new JLabel("Descrição:"), gc);
        gc.gridx = 1;
        gc.gridy = 2;
        p1.add(descricaoQuarto, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        p1.add(bCadastrarQuarto, gc);

        return p1;
    }

    public JPanel gerarPReservarQuarto() {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 3, 3, 0);
        gc.gridwidth = 1;
        gc.gridheight = 1;
        SpinnerNumberModel model = new SpinnerNumberModel(10, 0, 100, 1);
        final JSpinner jSDesconto = new JSpinner();
        jSDesconto.setModel(model);
        MaskFormatter mascaraData = null;
        final JLabel valorDiarias = new JLabel("0.0");
        try {
            mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
        } catch (ParseException excp) {
            System.err.println("Erro na formatação: " + excp.getMessage());
        }

        dataInicio = new JFormattedTextField(mascaraData);
        dataFim = new JFormattedTextField(mascaraData);

        JPanel p6 = new JPanel(grid);

        JPanel panelLista1 = new JPanel();
        JPanel panelLista2 = new JPanel();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());

        JPanel p4 = new JPanel(new BorderLayout());
        JPanel p5 = new JPanel(new BorderLayout());

        JScrollPane barraRolagem1; // ScrollBar para panelControle
        JScrollPane barraRolagem2;

        modelo2 = new nonEditableJTable();
        modelo1 = new nonEditableJTable();

        jTabelaQuartoDispRes = new JTable(modelo2);
        jTabelaQuartoARes = new JTable(modelo1);

        // Colunas da lista de Clientes
        modelo2.addColumn("Número");
        modelo2.addColumn("Descrição");
        modelo2.addColumn("Preço");

        modelo1.addColumn("Número");
        modelo1.addColumn("Descrição");
        modelo1.addColumn("Preço");

        JButton BtBuscar = new JButton("Buscar");
        JButton AddReserva = new JButton("Reservar -->>");
        JButton RemoveReserva = new JButton("<<-- Remover");
        JButton ReservarQt = new JButton("Finalizar reserva");

        ReservarQt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jTabelaQuartoARes.getRowCount() > 0) {

                    Date dateini;
                    Date datefim;
                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        dateini = formatter.parse(dataInicio.getText());
                        datefim = formatter.parse(dataFim.getText());
                        ArrayList<Quarto> vectorQuartos = new ArrayList<>();

                        for (int i = 0; i < jTabelaQuartoARes.getRowCount(); i++) {

                            for (int j = 0; j < controle.ListarQuartos().size(); j++) {
                                if ((controle.ListarQuartos().get(j).getNumero()) == Integer.parseInt((String) jTabelaQuartoARes.getValueAt(i, 0))) {
                                    vectorQuartos.add(controle.ListarQuartos().get(j));

                                }
                            }

                        }
                        try {
                            controle.CadastrarReserva(dateini, datefim, (int) jSDesconto.getValue(), controle.ListaClientes().get(jCBCliente.getSelectedIndex()).getCPF(), vectorQuartos);
                            for (int i = modelo1.getRowCount(); i > 0; i--) {
                                modelo1.removeRow(0);
                            }
                            JOptionPane.showMessageDialog(null, "Reserva realizada com sucesso!");
                        } catch (Exception erro) {
                            JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (ParseException ex) {
                        Logger.getLogger(VisaoPousada.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Deve existir pelo menos um quarto a ser reservado!", "Erro", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        BtBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Date dateini;
                Date datefim;

                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    dateini = formatter.parse(dataInicio.getText());
                    datefim = formatter.parse(dataFim.getText());

                    for (int i = modelo2.getRowCount(); i > 0; i--) {
                        modelo2.removeRow(0);
                    }
                    for (int i = modelo1.getRowCount(); i > 0; i--) {
                        modelo1.removeRow(0);
                    }
                    for (int i = 0; i < controle.QuartoDisponiveis(dateini, datefim).size(); i++) {
                        int numero = controle.QuartoDisponiveis(dateini, datefim).get(i).getNumero();
                        String descricao = controle.QuartoDisponiveis(dateini, datefim).get(i).getDescricao();
                        double preco = controle.QuartoDisponiveis(dateini, datefim).get(i).getPreco();

                        Object[] dados = {numero, descricao, preco};
                        modelo2.addRow(dados);
                    }

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Datas invalidas!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                valorDiarias.setText("0.0");
            }
        });

        AddReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jTabelaQuartoDispRes.getRowCount() > 0) {
                    if (jTabelaQuartoDispRes.getSelectedRow() >= 0) {
                        Object[] dados = {jTabelaQuartoDispRes.getValueAt(jTabelaQuartoDispRes.getSelectedRow(), 0).toString(), jTabelaQuartoDispRes.getValueAt(jTabelaQuartoDispRes.getSelectedRow(), 1).toString(), jTabelaQuartoDispRes.getValueAt(jTabelaQuartoDispRes.getSelectedRow(), 2).toString()};
                        modelo1.addRow(dados);
                        modelo2.removeRow(jTabelaQuartoDispRes.getSelectedRow());
                        jTabelaQuartoDispRes.getSelectionModel().setSelectionInterval(0, 0);

                        Date dateini = new Date();
                        Date datefim = new Date();
                        ArrayList<Quarto> vectorQuartos = new ArrayList<>();
                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            dateini = formatter.parse(dataInicio.getText());
                            datefim = formatter.parse(dataFim.getText());

                            for (int i = 0; i < jTabelaQuartoARes.getRowCount(); i++) {

                                for (int j = 0; j < controle.ListarQuartos().size(); j++) {
                                    if ((controle.ListarQuartos().get(j).getNumero()) == Integer.parseInt((String) jTabelaQuartoARes.getValueAt(i, 0))) {
                                        vectorQuartos.add(controle.ListarQuartos().get(j));

                                    }
                                }

                            }

                        } catch (ParseException ex) {
                            Logger.getLogger(VisaoPousada.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        valorDiarias.setText("" + (controle.TotalDiarias(dateini, datefim, vectorQuartos)));
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum quarto selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não existem quartos disponiveis!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        RemoveReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jTabelaQuartoARes.getRowCount() > 0) {
                    if (jTabelaQuartoARes.getSelectedRow() >= 0) {
                        Object[] dados = {jTabelaQuartoARes.getValueAt(jTabelaQuartoARes.getSelectedRow(), 0).toString(), jTabelaQuartoARes.getValueAt(jTabelaQuartoARes.getSelectedRow(), 1).toString(), jTabelaQuartoARes.getValueAt(jTabelaQuartoARes.getSelectedRow(), 2).toString()};
                        modelo2.addRow(dados);
                        modelo1.removeRow(jTabelaQuartoARes.getSelectedRow());
                        jTabelaQuartoARes.getSelectionModel().setSelectionInterval(0, 0);

                        Date dateini = new Date();
                        Date datefim = new Date();
                        ArrayList<Quarto> vectorQuartos = new ArrayList<>();
                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            dateini = formatter.parse(dataInicio.getText());
                            datefim = formatter.parse(dataFim.getText());

                            for (int i = 0; i < jTabelaQuartoARes.getRowCount(); i++) {

                                for (int j = 0; j < controle.ListarQuartos().size(); j++) {
                                    if ((controle.ListarQuartos().get(j).getNumero()) == Integer.parseInt((String) jTabelaQuartoARes.getValueAt(i, 0))) {
                                        vectorQuartos.add(controle.ListarQuartos().get(j));

                                    }
                                }

                            }

                        } catch (ParseException ex) {
                            Logger.getLogger(VisaoPousada.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        valorDiarias.setText("" + (controle.TotalDiarias(dateini, datefim, vectorQuartos)));
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum quarto selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não existem quartos a serem removidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        for (int i = 0; i < this.controle.ListaClientes().size(); i++) {
            jCBCliente.addItem(this.controle.ListaClientes().get(i).getNome() + " " + this.controle.ListaClientes().get(i).getCPF());
        }

        jTabelaQuartoDispRes.getSelectionModel().setSelectionInterval(0, 0); //Inicia a tabela com a primeira linha selecionada
        jTabelaQuartoARes.getSelectionModel().setSelectionInterval(0, 0);
        barraRolagem1 = new JScrollPane(jTabelaQuartoDispRes);
        barraRolagem2 = new JScrollPane(jTabelaQuartoARes);

        panelLista1.setLayout(new BorderLayout());
        panelLista1.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable

        panelLista2.setLayout(new BorderLayout());
        panelLista2.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable

        dataInicio.setText(JTF_data);
        dataFim.setText(JTF_data);

        p2.add(new JLabel("Data inicial:"));
        p2.add(dataInicio);
        p2.add(new JLabel("    Data Final:"));
        p2.add(dataFim);
        p2.add(BtBuscar);
        p2.add(new JLabel("    Cliente:"));
        p2.add(jCBCliente);

        p4.add(new JLabel("      Quartos livres:"), BorderLayout.NORTH);
        p4.add(panelLista1, BorderLayout.CENTER);

        p5.add(new JLabel("      Quartos a serem rezervados:"), BorderLayout.NORTH);
        p5.add(panelLista2, BorderLayout.CENTER);

        gc.gridx = 0;
        gc.gridy = 2;

        p6.add(AddReserva, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        p6.add(RemoveReserva, gc);

        gc.gridx = 0;
        gc.gridy = 4;

        p6.add(new javax.swing.JSeparator(), gc);

        gc.gridx = 0;
        gc.gridy = 5;
        p6.add(new JLabel("Valor total a pagar"), gc);

        gc.gridx = 0;
        gc.gridy = 6;
        p6.add(valorDiarias, gc);

        gc.gridx = 0;
        gc.gridy = 7;
        p6.add(new JLabel("Porcentagem de desconto"), gc);

        gc.gridx = 0;
        gc.gridy = 8;
        p6.add(jSDesconto, gc);

        gc.gridx = 0;
        gc.gridy = 9;
        p6.add(ReservarQt, gc);

        panelLista1.add(barraRolagem1, BorderLayout.CENTER);
        panelLista2.add(barraRolagem2, BorderLayout.CENTER);

        p1.add(BorderLayout.CENTER, p6);
        p1.add(BorderLayout.EAST, p5);
        p1.add(BorderLayout.WEST, p4);
        p1.add(BorderLayout.NORTH, p2);
        return p1;

    }

    public JPanel gerarPVisualizarReserva() {
        JPanel panelLista = new JPanel();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        modeloVRes = new nonEditableJTable();
        JScrollPane barraRolagem; // ScrollBar para panelControle
        bExcluirReserva = new JButton("Excluir Reserva");

        modeloVRes = new nonEditableJTable();
        jTabelaReserva = new JTable(modeloVRes);

        // Colunas da lista de Clientes
        modeloVRes.addColumn("Numero");
        modeloVRes.addColumn("Entrada");
        modeloVRes.addColumn("Saida");
        modeloVRes.addColumn("Cliente");
        modeloVRes.addColumn("Quartos");
        modeloVRes.addColumn("Valor Pago");
        modeloVRes.addColumn("Valor Restante");
        modeloVRes.addColumn("Estado");

        for (int i = 0; i < this.controle.ListarReservas().size(); i++) {

            Object[] dados = {controle.ListarReservas().get(i).getNumeroReserva(), controle.ListarReservas().get(i).getEntrada(), controle.ListarReservas().get(i).getSaida(), controle.ListarReservas().get(i).getCpf(), controle.ListarReservas().get(i).getQuartosVet(), controle.CalculaValPG(controle.ListarReservas().get(i).getNumeroReserva()), controle.CalculaValAPG(controle.ListarReservas().get(i).getNumeroReserva())};
            modeloVRes.addRow(dados);
        }

        bExcluirReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numeroReserva = jTabelaReserva.getValueAt(jTabelaReserva.getSelectedRow(), 0).toString();
                int comfirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar a reserva?");
                if (comfirmacao == 0) {
                    //modeloVRes.removeRow(jTabelaReserva.getSelectedRow());
                    controle.RemoverReserva(Integer.parseInt(numeroReserva));
                    atualizaInterface();
                    layout = (CardLayout) cards.getLayout();
                    layout.show(cards, "VisualizarReserva");
                }

            }
        });

        jTabelaReserva.getSelectionModel().setSelectionInterval(0, 0); //Inicia a tabela com a primeira linha selecionada
        barraRolagem = new JScrollPane(jTabelaReserva);

        panelLista.setLayout(new BorderLayout());
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable

        panelLista.add(barraRolagem, BorderLayout.CENTER);

        p1.add(panelLista);

        p1.add(BorderLayout.CENTER, panelLista);

        p2.add(BorderLayout.SOUTH, bExcluirReserva);
        p1.add(BorderLayout.SOUTH, p2);

        return p1;
    }

    public JPanel gerarPEditarQuarto() {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 3, 3, 0);
        gc.gridwidth = 1;
        gc.gridheight = 1;

        JPanel p1 = new JPanel(grid);

        gc.gridx = 0;
        gc.gridy = 0;
        p1.add(new JLabel("Numero:"), gc);
        gc.gridx = 1;
        gc.gridy = 0;
        p1.add(new JLabel(NEditQuarto), gc);

        gc.gridx = 0;
        gc.gridy = 1;
        p1.add(new JLabel("Preco:"), gc);
        gc.gridx = 1;
        gc.gridy = 1;
        if (NEditQuarto != null) {
            this.controle.getQuarto(Integer.parseInt(NEditQuarto)).getPreco();
            precoEditQuarto.setText("" + this.controle.getQuarto(Integer.parseInt(NEditQuarto)).getPreco());
        }

        p1.add(precoEditQuarto, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        p1.add(new JLabel("Descrição:"), gc);
        gc.gridx = 1;
        gc.gridy = 2;
        if (NEditQuarto != null) {
            descricaoEditQuarto.setText(this.controle.getQuarto(Integer.parseInt(NEditQuarto)).getDescricao());
        }
        p1.add(descricaoEditQuarto, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        p1.add(bModificarQuarto, gc);

        return p1;
    }

    public static String escolheArquivo() {

        String arquivoWave;
        JFileChooser arquivo = new JFileChooser();
        arquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (arquivo.showOpenDialog(arquivo) == JFileChooser.APPROVE_OPTION) {
            arquivoWave = arquivo.getSelectedFile().getPath();
        } else {
            arquivoWave = "";

        }

        return arquivoWave;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jMenuCadastraCliente) {

            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "CadastrarCliente");

        } else if (e.getSource() == jMenuCadastraQuarto) {
            numeroQuarto = new JTextField(5);
            precoQuarto = new JTextField(10);
            descricaoQuarto = new JTextField(25);
            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "CadastrarQuarto");
        } else if (e.getSource() == jMenuCadastraReserva) {

            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "FazerReserva");
        } else if (e.getSource() == jMenuConsultaReserva) {

            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "VisualizarReserva");
        } else if (e.getSource() == jMenuConsultaCliente) {

            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "VisualizarCliente");
        } else if (e.getSource() == jMenuConsultaQuarto) {

            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "VisualizarQuarto");
        } else if (e.getSource() == jMenuConsultaQuartoDisponivel) {

            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "VisualizarQuartoDisponivel");

        } else if (e.getSource() == bCadastrarQuarto) {
            try {
                this.controle.CadastraQuarto(Double.parseDouble(this.precoQuarto.getText()), Integer.parseInt(this.numeroQuarto.getText()), this.descricaoQuarto.getText());
                numeroQuarto = new JTextField(5);
                precoQuarto = new JTextField(10);
                descricaoQuarto = new JTextField(25);

                atualizaInterface();
                layout = (CardLayout) cards.getLayout();
                layout.show(cards, "ConfirmaQuarto");
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == bCadastrarCliente) {
            try {
                this.controle.CadastrarCliente(this.cpfCliente.getText(), this.nomeCliente.getText(), this.enderecoCliente.getText(), this.telefoneCliente.getText());
                cpfCliente = new JTextField(11);
                nomeCliente = new JTextField(15);
                enderecoCliente = new JTextField(20);
                telefoneCliente = new JTextField(15);

                atualizaInterface();
                layout = (CardLayout) cards.getLayout();
                layout.show(cards, "ConfirmaCliente");
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == bCadastrarReserva) {

        } else if (e.getSource() == bEditarQuarto) {

            NEditQuarto = jTabelaQuarto.getValueAt(jTabelaQuarto.getSelectedRow(), 0).toString();
            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "EditarQuarto");
        } else if (e.getSource() == bVoltarCliente) {

            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "CadastrarCliente");

        } else if (e.getSource() == bModificarQuarto) {

            try {
                this.controle.AlterarQuarto(Double.parseDouble(precoEditQuarto.getText()), Integer.parseInt(NEditQuarto), descricaoEditQuarto.getText());
                precoEditQuarto = new JTextField(10);
                descricaoEditQuarto = new JTextField(25);
                atualizaInterface();
                layout = (CardLayout) cards.getLayout();
                layout.show(cards, "VisualizarQuarto");
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == bVoltarQuarto) {
            numeroQuarto = new JTextField(5);
            precoQuarto = new JTextField(10);
            descricaoQuarto = new JTextField(25);

            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "CadastrarQuarto");
        } else if (e.getSource() == bExcluirCliente) {

            this.controle.RemoverCliente(jTabelaCliente.getValueAt(jTabelaCliente.getSelectedRow(), 1).toString());
            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "VisualizarCliente");
        } else if (e.getSource() == bEditarCliente) {

            NEditCPF = jTabelaCliente.getValueAt(jTabelaCliente.getSelectedRow(), 1).toString();//this.controle.RemoverCliente();
            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "EditarCliente");
        } else if (e.getSource() == bEditaCliente) {

            try {
                this.controle.AlterarCliente(NEditCPF, this.EdtNomeCliente.getText(), this.EdtEnderecoCliente.getText(), this.EdtTelefoneCliente.getText());
                atualizaInterface();
                layout = (CardLayout) cards.getLayout();
                layout.show(cards, "VisualizarCliente");
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == jMenuPagamento) {

            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "RealizarPagamento");
        } else if (e.getSource() == jRelatorioCancelado) {
            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "GerarRelatorioCanceladas");
        } else if (e.getSource() == jRelatorioData) {
            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "GerarRelatorioPeriodo");
        } else if (e.getSource() == jRelatorioDoDia) {
            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "GerarRelatorioEfetivar");
        } else if (e.getSource() == jRelatorioNaoPG) {
            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "GerarRelatorioNaoPagos");
        }

    }
}
