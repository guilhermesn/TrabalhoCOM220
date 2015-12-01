
import java.util.ArrayList;
import java.util.Date;

public class CtrlPousada {

    private ArrayList<Reserva> Reservas = new ArrayList<>();
    private ArrayList<Quarto> Quartos = new ArrayList<>();
    private ArrayList<Cliente> Clientes = new ArrayList<>();
    private final VisaoPousada view;

    public CtrlPousada() throws Exception {
        this.view = new VisaoPousada(this);
        view.gerarInterface();
    }

    public void CadastrarCliente(String CPF, String nome, String endereco, String telefone) {
        Cliente cl = new Cliente(CPF, nome, endereco, telefone);
        Clientes.add(cl);
    }

    public void CadastrarReserva(int numeroReserva, Date entrada, Date saida, double desconto, Pagamento diarias, Pagamento pgtReserva, String Cpf) {
        Reserva rs = new Reserva(numeroReserva, entrada, saida, desconto, diarias, pgtReserva, Cpf);
        Reservas.add(rs);

    }

    public void CadastraQuarto(double preco, int numero, String descricao) {
        Quarto qt = new Quarto(preco, numero, descricao);
        this.Quartos.add(qt);
    }

    public void AlterarQuarto(double preco, int numero, String descricao) {
        for (int i = 0; i < this.Quartos.size(); i++) {
            if (this.Quartos.get(i).getNumero() == numero) {
                this.Quartos.get(i).setDescricao(descricao);
                this.Quartos.get(i).setPreco(preco);
            }
        }
    }

    public double CalculaDesconto(int reserva) {
        return 0;
    }

    public ArrayList<Reserva> QuartoDisponiveis(Date Entrada, Date saida) {
        ArrayList<Reserva> reservasData = new ArrayList<>();
        for (int i = 0; i < this.Reservas.size(); i++) {
            if (this.Reservas.get(i).getEntrada().after(Entrada) && this.Reservas.get(i).getEntrada().before(saida)) {
                for (int j = 0; j < this.Reservas.get(j).getQuartos().size(); j++) {
                    if (this.Reservas.get(i).getQuartos().contains(j)) {
                        reservasData.add(Reservas.get(i));
                    }
                }
            }
        }
        return reservasData;
    }
}
