
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class VisaoPousada extends JFrame implements ActionListener, ListSelectionListener {

    private JPanel cards;
    private CtrlPousada controle;
    private JTextField precoQuarto = new JTextField();
    private JTextField descricaoQuarto = new JTextField();
    private JTextField numeroQuarto = new JTextField();

    private JTextField cpfCliente = new JTextField();
    private JTextField nomeCliente = new JTextField();
    private JTextField enderecoCliente = new JTextField();
    private JTextField telefoneCliente = new JTextField();

    private JButton bCadastrarCliente;
    private JButton bCadastrarQuarto;
    private JButton bCadastrarReserva;
    private JButton bEditarQuarto;
    private JButton bExcluirQuarto;
    private JButton bEditarCliente;
    private JButton bExcluirCliente;
    private JButton bVoltarCliente;

    private JPanel janelaPrincipal;

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

    JTable tableTabela;
    DefaultTableModel modelo = new nonEditableJTable();

    public VisaoPousada(CtrlPousada pousada) {
        this.controle = pousada;
    }

    public void atualizaInterface(){
        
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
        
    }
    public void gerarInterface() {
        
        cards = new JPanel();
        cards.setLayout(new CardLayout());
        
        atualizaInterface();

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

        janelaPrincipal = new JPanel(new BorderLayout());
        janelaPrincipal.add(jMenuBar1, BorderLayout.PAGE_START);
        janelaPrincipal.add(cards, BorderLayout.CENTER);

        getContentPane().add(janelaPrincipal);

        setSize(800, 500);
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
        p1.add(cpfCliente = new JTextField(11), gc);

        gc.gridx = 0;
        gc.gridy = 1;
        p1.add(new JLabel("Nome:"), gc);

        gc.gridx = 1;
        gc.gridy = 1;
        p1.add(nomeCliente = new JTextField(15), gc);

        gc.gridx = 0;
        gc.gridy = 2;
        p1.add(new JLabel("Endereço:"), gc);
        gc.gridx = 1;
        gc.gridy = 2;
        p1.add(enderecoCliente = new JTextField(20), gc);

        gc.gridx = 0;
        gc.gridy = 3;
        p1.add(new JLabel("Telefone:"), gc);
        gc.gridx = 1;
        gc.gridy = 3;
        p1.add(enderecoCliente = new JTextField(15), gc);

        gc.gridx = 1;
        gc.gridy = 4;
        p1.add(bCadastrarCliente = new JButton("Cadastrar"), gc);
        bCadastrarCliente.addActionListener(this);

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
        p1.add(new JLabel("Quarto Cadastrado com Sucesso."), gc);

        gc.gridx = 0;
        gc.gridy = 1;
        //p1.add(bVoltarQuarto = new JButton("Voltar"), gc);
        //bVoltarQuarto.addActionListener(this);

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
        //p1.add(bVoltarQuarto = new JButton("Voltar"), gc);
        //bVoltarQuarto.addActionListener(this);

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

        tableTabela = new JTable(modelo);
        barraRolagem = new JScrollPane(tableTabela);

        // Colunas da lista de Clientes
        panelLista.setLayout(new BorderLayout());
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable

        panelLista.add(barraRolagem, BorderLayout.CENTER);

        p1.add(panelLista);

        p1.add(BorderLayout.CENTER, panelLista);
        p2.add(bEditarCliente = new JButton("Editar Cliente"));
        p2.add(bExcluirCliente = new JButton("Excluir Cliente"));

        p1.add(BorderLayout.CENTER, panelLista);
        p1.add(BorderLayout.SOUTH, p2);

        bEditarCliente.addActionListener(this);
        bExcluirCliente.addActionListener(this);

        return p1;
    }

    public JPanel gerarPConsultaQuarto() {

        JPanel panelLista = new JPanel();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());

        JScrollPane barraRolagem; // ScrollBar para panelControle
        modelo = new nonEditableJTable();
        tableTabela = new JTable(modelo);

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

        tableTabela.getSelectionModel().setSelectionInterval(0, 0); //Inicia a tabela com a primeira linha selecionada

        panelLista.setLayout(new BorderLayout());
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable
        barraRolagem = new JScrollPane(tableTabela);

        panelLista.add(barraRolagem, BorderLayout.CENTER);

        p1.add(panelLista);

        p1.add(BorderLayout.CENTER, panelLista);
        p2.add(BorderLayout.SOUTH, bEditarQuarto = new JButton("Editar Quarto"));
        p2.add(BorderLayout.SOUTH, bExcluirQuarto = new JButton("Excluir Quarto"));
        p1.add(BorderLayout.SOUTH, p2);

        bEditarQuarto.addActionListener(this);
        bExcluirQuarto.addActionListener(this);

        return p1;

    }

    public JPanel gerarPQuartoDisponivel() {
        JPanel panelLista = new JPanel();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());

        JScrollPane barraRolagem; // ScrollBar para panelControle
        modelo = new nonEditableJTable();
        tableTabela = new JTable(modelo);

        // Colunas da lista de Clientes
        modelo.addColumn("Número");
        modelo.addColumn("Descrição");
        modelo.addColumn("Preço");

        panelLista.setLayout(new BorderLayout());
        panelLista.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); // Bordas para o JTable
        barraRolagem = new JScrollPane(tableTabela);

        panelLista.add(barraRolagem, BorderLayout.CENTER);

        p1.add(panelLista);

        p1.add(BorderLayout.CENTER, panelLista);
        p2.add(BorderLayout.NORTH, bEditarQuarto = new JButton("Editar Quarto"));
        p2.add(BorderLayout.NORTH, bExcluirQuarto = new JButton("Excluir Quarto"));
        p1.add(BorderLayout.NORTH, p2);

        bEditarQuarto.addActionListener(this);
        bExcluirQuarto.addActionListener(this);

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
        p1.add(bVoltarCliente = new JButton("Voltar"), gc);
        bVoltarCliente.addActionListener(this);

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
        p1.add(numeroQuarto = new JTextField(5), gc);

        gc.gridx = 0;
        gc.gridy = 1;
        p1.add(new JLabel("Preco:"), gc);
        gc.gridx = 1;
        gc.gridy = 1;
        p1.add(precoQuarto = new JTextField(10), gc);

        gc.gridx = 0;
        gc.gridy = 2;
        p1.add(new JLabel("Descrição:"), gc);
        gc.gridx = 1;
        gc.gridy = 2;
        p1.add(descricaoQuarto = new JTextField(25), gc);

        gc.gridx = 1;
        gc.gridy = 3;
        p1.add(bCadastrarQuarto = new JButton("Cadastrar"), gc);
        bCadastrarQuarto.addActionListener(this);

        return p1;
    }

    public JPanel gerarPReservarQuarto() {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 3, 3, 0);
        gc.gridwidth = 1;
        gc.gridheight = 1;
        //UtilDateModel model = new UtilDateModel();
        //JDatePanelImpl datePanel = new JDatePanelImpl(model);
        //JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

        //frame.add(datePicker);
        JPanel p1 = new JPanel(grid);

        gc.gridx = 0;
        gc.gridy = 0;
        p1.add(new JLabel("Data de entrada:"), gc);
        gc.gridx = 1;
        gc.gridy = 0;
        p1.add(numeroQuarto = new JTextField(5), gc);

        gc.gridx = 0;
        gc.gridy = 1;
        p1.add(new JLabel("Data de Saida:"), gc);
        gc.gridx = 1;
        gc.gridy = 1;
        p1.add(precoQuarto = new JTextField(10), gc);

        gc.gridx = 0;
        gc.gridy = 2;
        p1.add(new JLabel("Quartos:"), gc);
        gc.gridx = 1;
        gc.gridy = 2;
        p1.add(descricaoQuarto = new JTextField(25), gc);

        gc.gridx = 1;
        gc.gridy = 3;
        p1.add(bCadastrarCliente = new JButton("Cadastrar"), gc);
        bCadastrarCliente.addActionListener(this);

        return p1;
    }

    public JPanel gerarPVisualizarReserva() {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.EAST;
        gc.insets = new Insets(0, 3, 3, 0);
        gc.gridwidth = 1;
        gc.gridheight = 1;

        JPanel p1 = new JPanel(grid);

        gc.gridx = 0;
        gc.gridy = 0;
        p1.add(new JLabel("Data de entrada:"), gc);
        gc.gridx = 1;
        gc.gridy = 0;
        p1.add(numeroQuarto = new JTextField(5), gc);

        gc.gridx = 0;
        gc.gridy = 1;
        p1.add(new JLabel("Data de Saida:"), gc);
        gc.gridx = 1;
        gc.gridy = 1;
        p1.add(precoQuarto = new JTextField(10), gc);

        gc.gridx = 0;
        gc.gridy = 2;
        p1.add(new JLabel("Quarto:"), gc);
        gc.gridx = 1;
        gc.gridy = 2;
        p1.add(descricaoQuarto = new JTextField(25), gc);

        gc.gridx = 1;
        gc.gridy = 3;
        p1.add(bCadastrarCliente = new JButton("Visualizar"), gc);
        bCadastrarCliente.addActionListener(this);

        return p1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        atualizaInterface();
        CardLayout layout = (CardLayout) cards.getLayout();

        if (e.getSource() == jMenuCadastraCliente) {
            
            layout.show(cards, "CadastrarCliente");

        } else if (e.getSource() == jMenuCadastraQuarto) {
            layout.show(cards, "CadastrarQuarto");
        } else if (e.getSource() == jMenuCadastraReserva) {
            layout.show(cards, "FazerReserva");
        } else if (e.getSource() == jMenuConsultaReserva) {
            layout.show(cards, "VisualizarReserva");
        } else if (e.getSource() == jMenuConsultaCliente) {
            layout.show(cards, "VisualizarCliente");
        } else if (e.getSource() == jMenuConsultaQuarto) {
            
            
            
            layout.show(cards, "VisualizarQuarto");
        } else if (e.getSource() == jMenuConsultaQuartoDisponivel) {
            layout.show(cards, "VisualizarQuartoDisponivel");

        } else if (e.getSource() == null) {
            layout.show(cards, null);
        } else if (e.getSource() == bCadastrarQuarto) {
            this.controle.CadastraQuarto(Double.parseDouble(this.precoQuarto.getText()), Integer.parseInt(this.numeroQuarto.getText()), this.descricaoQuarto.getText());
            layout.show(cards, "ConfirmaQuarto");
        } else if (e.getSource() == bCadastrarCliente) {
            this.controle.CadastrarCliente(this.cpfCliente.getText(), this.nomeCliente.getText(), this.enderecoCliente.getText(), this.telefoneCliente.getText());
            layout.show(cards, "ConfirmaCliente");
        } else if (e.getSource() == bCadastrarReserva) {

            //  } else if (e.getSource() == bVoltarQuarto) {
            //     layout.show(cards, "CadastrarQuarto");
        } else if (e.getSource() == bVoltarCliente) {
            layout.show(cards, "CadastrarCliente");
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
