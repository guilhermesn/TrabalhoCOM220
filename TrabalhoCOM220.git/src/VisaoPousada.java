
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
    private JTextField descricaoEditQuarto= new JTextField(25);
    private JTextField numeroQuarto = new JTextField();
    private JTextField precoEditQuarto = new JTextField(10);
        
    private JTextField cpfCliente = new JTextField();
    private JTextField nomeCliente = new JTextField();
    private JTextField enderecoCliente = new JTextField();
    private JTextField telefoneCliente = new JTextField();

    private JTextField EdtNomeCliente = new JTextField();
    private JTextField EdtEnderecoCliente = new JTextField();
    private JTextField EdtTelefoneCliente = new JTextField();

    private JFormattedTextField dataInicial;
    private JFormattedTextField dataFinal;

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
    private JButton bPesquisarQuartoDisponiveis;
    private JButton bReservarQuartoDisponiveis;

    private JPanel janelaPrincipal;
    private JComboBox jCBCliente = new javax.swing.JComboBox();

    private javax.swing.JMenuItem jMenuCadastraQuarto;
    private javax.swing.JMenuItem jMenuCadastraCliente;
    private javax.swing.JMenuItem jMenuCadastraReserva;
    private javax.swing.JMenuItem jMenuConsultaQuarto;
    private javax.swing.JMenuItem jMenuConsultaQuartoDisponivel;
    private javax.swing.JMenuItem jMenuConsultaCliente;
    private javax.swing.JMenuItem jMenuConsultaReserva;
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

    private DefaultTableModel modelo = new nonEditableJTable();
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private DefaultTableModel modelo2 = new nonEditableJTable();
    private DefaultTableModel modelo1 = new nonEditableJTable();

    public VisaoPousada(CtrlPousada pousada) {
        iniciaBotoes();
        this.controle = pousada;
    }

    public void atualizaInterface() {

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

        jCadastrar.setText("Cadastrar");
        jMenuBar1.add(jCadastrar);

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
        jVisualisar.add(jMenuConsultaReserva);

        bCadastrarQuarto = new JButton("Cadastrar");
        bVoltarCliente = new JButton("Voltar");
        bCadastrarCliente = new JButton("Cadastrar");
        bEditarCliente = new JButton("Editar Cliente");
        bEditaCliente = new JButton("Alterar");
        bExcluirCliente = new JButton("Excluir Cliente");
        bCadastrarReserva = new JButton("Cadastrar");
        bEditarQuarto = new JButton("Editar Quarto");
        bExcluirQuarto = new JButton("Excluir Quarto");
        bVoltarQuarto = new JButton("Voltar");
        bModificarQuarto = new JButton("Modificar");
        bPesquisarQuartoDisponiveis = new JButton("Pesquisar");
        bReservarQuartoDisponiveis = new JButton("Reservar");

        bCadastrarQuarto.addActionListener(this);
        bVoltarCliente.addActionListener(this);
        bCadastrarCliente.addActionListener(this);
        bEditaCliente.addActionListener(this);
        bEditarCliente.addActionListener(this);
        bExcluirCliente.addActionListener(this);
        bCadastrarReserva.addActionListener(this);
        bEditarQuarto.addActionListener(this);
        bExcluirQuarto.addActionListener(this);
        bVoltarQuarto.addActionListener(this);
        bModificarQuarto.addActionListener(this);
        bPesquisarQuartoDisponiveis.addActionListener(this);
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

        JPanel panelLista = new JPanel();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());

        JScrollPane barraRolagem; // ScrollBar para panelControle

        modelo = new nonEditableJTable();
        jTabelaQuarto = new JTable(modelo);

        // Colunas da lista de Clientes
        modelo.addColumn("Número");
        modelo.addColumn("Descrição");
        modelo.addColumn("Preço");

        for (int i = 0; i < this.controle.ListarQuartos().size(); i++) {
            int numero = this.controle.ListarQuartos().get(i).getNumero();
            String descricao = this.controle.ListarQuartos().get(i).getDescricao();
            double preco = this.controle.ListarQuartos().get(i).getPreco();

            Object[] dados = {numero, descricao, preco};
            modelo.addRow(dados);
        }

        jTabelaQuarto.getSelectionModel().setSelectionInterval(0, 0); //Inicia a tabela com a primeira linha selecionada
        barraRolagem = new JScrollPane(jTabelaQuarto);

        panelLista.setLayout(new BorderLayout());
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable

        panelLista.add(barraRolagem, BorderLayout.CENTER);

        p1.add(panelLista);

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
        dataInicial = new JFormattedTextField(df);
        dataFinal = new JFormattedTextField(df);

        p1.add(panelLista);
        dataInicial.setColumns(10);
        dataFinal.setColumns(10);
        p1.add(BorderLayout.CENTER, panelLista);
        p2.add(BorderLayout.NORTH, dataInicial);
        p2.add(BorderLayout.NORTH, dataFinal);
        p2.add(BorderLayout.NORTH, bPesquisarQuartoDisponiveis);
        p1.add(BorderLayout.SOUTH, bReservarQuartoDisponiveis);

        p1.add(BorderLayout.NORTH, p2);
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.install(dataInicial);
            dateMask.install(dataFinal);
        } catch (ParseException ex) {
            // Logger.getLogger(MaskFormatterTest.class.getName()).log(Level.SEVERE, null, ex);
        }

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

        JPanel p6 = new JPanel(grid);

        JPanel panelLista1 = new JPanel();
        JPanel panelLista2 = new JPanel();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());

        JPanel p4 = new JPanel(new BorderLayout());
        JPanel p5 = new JPanel(new BorderLayout());
        //JPanel p6 = new JPanel(new FlowLayout());

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
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date dateini;
                Date datefim;
                try {
                    dateini = formatter.parse("11/02/2015");//  dataInicial.getText());
                    datefim = formatter.parse("11/02/2015");//dataFinal.getText());
                    Vector vectorQuartos = new Vector();
                    for (int i = 0; i < jTabelaQuartoARes.getRowCount(); i++) {
                        vectorQuartos.add(jTabelaQuartoARes.getValueAt(i, 0));
                    }

                    controle.CadastrarReserva(dateini, datefim, 50.50, new Pagamento(50), new Pagamento(50), controle.ListaClientes().get(jCBCliente.getSelectedIndex()).getCPF(), vectorQuartos);

                } catch (ParseException ex) {
                    Logger.getLogger(VisaoPousada.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        BtBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < controle.ListarQuartos().size(); i++) {
                    int numero = controle.ListarQuartos().get(i).getNumero();
                    String descricao = controle.ListarQuartos().get(i).getDescricao();
                    double preco = controle.ListarQuartos().get(i).getPreco();

                    Object[] dados = {numero, descricao, preco};
                    modelo2.addRow(dados);
                }
            }
        });

        AddReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] dados = {jTabelaQuartoDispRes.getValueAt(jTabelaQuartoDispRes.getSelectedRow(), 0).toString(), jTabelaQuartoDispRes.getValueAt(jTabelaQuartoDispRes.getSelectedRow(), 1).toString(), jTabelaQuartoDispRes.getValueAt(jTabelaQuartoDispRes.getSelectedRow(), 2).toString()};
                modelo1.addRow(dados);
                modelo2.removeRow(jTabelaQuartoDispRes.getSelectedRow());
                jTabelaQuartoDispRes.getSelectionModel().setSelectionInterval(0, 0);
            }
        });

        RemoveReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] dados = {jTabelaQuartoARes.getValueAt(jTabelaQuartoARes.getSelectedRow(), 0).toString(), jTabelaQuartoARes.getValueAt(jTabelaQuartoARes.getSelectedRow(), 1).toString(), jTabelaQuartoARes.getValueAt(jTabelaQuartoARes.getSelectedRow(), 2).toString()};
                modelo2.addRow(dados);
                modelo1.removeRow(jTabelaQuartoARes.getSelectedRow());
                jTabelaQuartoARes.getSelectionModel().setSelectionInterval(0, 0);
            }
        });

        for (int i = 0; i < this.controle.ListaClientes().size(); i++) {
            //Object[] dados = {this.controle.ListaClientes().get(i).getNome(), this.controle.ListaClientes().get(i).getCPF(), this.controle.ListaClientes().get(i).getTelefone(), this.controle.ListaClientes().get(i).getEndereco()};
            //jCBCliente.add(dados);

            jCBCliente.addItem(this.controle.ListaClientes().get(i).getNome() + " " + this.controle.ListaClientes().get(i).getCPF());
            //modelo1.addRow(dados);
        }

        jTabelaQuartoDispRes.getSelectionModel().setSelectionInterval(0, 0); //Inicia a tabela com a primeira linha selecionada
        jTabelaQuartoARes.getSelectionModel().setSelectionInterval(0, 0);
        barraRolagem1 = new JScrollPane(jTabelaQuartoDispRes);
        barraRolagem2 = new JScrollPane(jTabelaQuartoARes);

        panelLista1.setLayout(new BorderLayout());
        panelLista1.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable

        panelLista2.setLayout(new BorderLayout());
        panelLista2.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable

        dataInicial = new JFormattedTextField(df);
        dataFinal = new JFormattedTextField(df);
        
        p2.add(new JLabel("Data inicial:"));
        p2.add(dataInicial);
        p2.add(new JLabel("    Data Final:"));
        p2.add(dataFinal);
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
        gc.gridy = 6;
        p6.add(ReservarQt, gc);

        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.install(dataInicial);
            dateMask.install(dataFinal);
        } catch (ParseException ex) {
            // Logger.getLogger(MaskFormatterTest.class.getName()).log(Level.SEVERE, null, ex);
        }

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

        JScrollPane barraRolagem; // ScrollBar para panelControle

        modelo = new nonEditableJTable();
        jTabelaReserva = new JTable(modelo);

        // Colunas da lista de Clientes
        modelo.addColumn("Numero");
        modelo.addColumn("Entrada");
        modelo.addColumn("Saida");
        modelo.addColumn("Cliente");
        modelo.addColumn("Quartos");
        modelo.addColumn("Valor Pago");
        modelo.addColumn("Valor Restante");
        modelo.addColumn("Desconto");
        modelo.addColumn("Estado");

        for (int i = 0; i < this.controle.ListarReservas().size(); i++) {

            Object[] dados = {controle.ListarReservas().get(i).getNumeroReserva(), controle.ListarReservas().get(i).getEntrada(), controle.ListarReservas().get(i).getSaida(), controle.ListarReservas().get(i).getCpf(), controle.ListarReservas().get(i).getQuartos()};
            modelo.addRow(dados);
        }

        jTabelaReserva.getSelectionModel().setSelectionInterval(0, 0); //Inicia a tabela com a primeira linha selecionada
        barraRolagem = new JScrollPane(jTabelaReserva);

        panelLista.setLayout(new BorderLayout());
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable

        panelLista.add(barraRolagem, BorderLayout.CENTER);

        p1.add(panelLista);

        p1.add(BorderLayout.CENTER, panelLista);
        p2.add(BorderLayout.SOUTH, bEditarQuarto);
        p2.add(BorderLayout.SOUTH, bExcluirQuarto);
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

    @Override
    public void actionPerformed(ActionEvent e) {

        CardLayout layout;

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
            //this.controle.CadastraQuarto(Double.parseDouble(this.precoQuarto.getText()), Integer.parseInt(this.numeroQuarto.getText()), this.descricaoQuarto.getText());
            numeroQuarto = new JTextField(5);
            precoQuarto = new JTextField(10);
            descricaoQuarto = new JTextField(25);

            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "ConfirmaQuarto");
        } else if (e.getSource() == bCadastrarCliente) {
            this.controle.CadastrarCliente(this.cpfCliente.getText(), this.nomeCliente.getText(), this.enderecoCliente.getText(), this.telefoneCliente.getText());
            cpfCliente = new JTextField(11);
            nomeCliente = new JTextField(15);
            enderecoCliente = new JTextField(20);
            telefoneCliente = new JTextField(15);

            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "ConfirmaCliente");
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

            this.controle.AlterarQuarto(Double.parseDouble(precoQuarto.getText()), Integer.parseInt(NEditQuarto), descricaoQuarto.getText());
            numeroQuarto = new JTextField(5);
            precoQuarto = new JTextField(10);
            descricaoQuarto = new JTextField(25);
            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "VisualizarQuarto");
        } else if (e.getSource() == bVoltarQuarto) {
            numeroQuarto = new JTextField(5);
            precoQuarto = new JTextField(10);
            descricaoQuarto = new JTextField(25);

            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "CadastrarQuarto");
        } else if (e.getSource() == bExcluirQuarto) {

            this.controle.RemoverQuarto(Integer.parseInt(jTabelaQuarto.getValueAt(jTabelaQuarto.getSelectedRow(), 0).toString()));
            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "VisualizarQuarto");
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

            this.controle.AlterarCliente(NEditCPF, this.EdtNomeCliente.getText(), this.EdtEnderecoCliente.getText(), this.EdtTelefoneCliente.getText());
            atualizaInterface();
            layout = (CardLayout) cards.getLayout();
            layout.show(cards, "VisualizarCliente");
        }
    }
}
