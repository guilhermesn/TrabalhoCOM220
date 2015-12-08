
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;

public class CtrlPousada implements Serializable {

    private ArrayList<Reserva> Reservas = new ArrayList<>();
    private ArrayList<Reserva> ReservasCanceladas = new ArrayList<>();
    private ArrayList<Reserva> ReservasDoDia = new ArrayList<>();
    private ArrayList<Quarto> Quartos = new ArrayList<>();
    private ArrayList<Cliente> Clientes = new ArrayList<>();
    private final VisaoPousada view;

    public CtrlPousada() throws Exception {

        this.view = new VisaoPousada(this);
        view.gerarInterface();

        try {
            desserializaCliente();
            desserializaQuarto();
            desserializaReserva();
        } catch (Exception e) {
        }

        VerificaReservas();
        GeraRelatorioReservaCancelada();
    }

    public void VerificaReservas() {
        Date hoje = new Date(System.currentTimeMillis());
        Calendar HOJE = Calendar.getInstance();
        Calendar data2 = Calendar.getInstance();
        HOJE.setTime(hoje);
        if (Reservas.size() != 0) {
            for (int i = 0; i < Reservas.size(); i++) {
                data2.setTime(Reservas.get(i).getEntrada());
                if (data2.get(Calendar.DAY_OF_YEAR) - 3 < HOJE.get(Calendar.DAY_OF_YEAR)) {
                    if (Reservas.get(i).getPgtReserva().getSituacao() == false) {                        
                        ReservasCanceladas.add(Reservas.get(i));
                        Reservas.remove(i);
                    }
                }
                if (data2.get(Calendar.DAY_OF_YEAR) - 3 == HOJE.get(Calendar.DAY_OF_YEAR)) {
                    if (Reservas.get(i).getPgtReserva().getSituacao() == false) {                        
                        ReservasDoDia.add(Reservas.get(i));
                        Reservas.remove(i);
                    }
                }
            }
        }
    }

    public void GeraRelatorioReservaCancelada() {
        String relatorio = "Número\tNúmero da Reserva\tNome do Cliente\tData Prevista\tValor\n";
        String nome = "";
        for (int i = 0; i < ReservasCanceladas.size(); i++) {
            for(int n=0;n<Clientes.size();n++)
            {
                if(Clientes.get(n).getCPF() == ReservasCanceladas.get(i).getCpf())
                    nome = Clientes.get(n).getNome();
            }
            
            relatorio += i + "\t" + ReservasCanceladas.get(i).getNumeroReserva() + "\t" + nome + "\t" + ReservasCanceladas.get(i).getEntrada() + "\t" + ReservasCanceladas.get(i).getDiarias().getValorTotal() +"\n";
        }                
    }
    
    public void GeraRelatorioReservaDoDia() {
        String relatorio = "Número\tNúmero da Reserva\tNome do Cliente\tData Prevista\tValor\n";
        String nome = "";
        for (int i = 0; i < ReservasDoDia.size(); i++) {
            for(int n=0;n<Clientes.size();n++)
            {
                if(Clientes.get(n).getCPF() == ReservasDoDia.get(i).getCpf())
                    nome = Clientes.get(n).getNome();
            }
            
            relatorio += i + "\t" + ReservasDoDia.get(i).getNumeroReserva() + "\t" + nome + "\t" + ReservasDoDia.get(i).getEntrada() + "\t" + ReservasDoDia.get(i).getDiarias().getValorTotal() +"\n";
        }                
    }

    public void CadastrarCliente(String CPF, String nome, String endereco, String telefone) {
        Cliente cl = new Cliente(CPF, nome, endereco, telefone);
        Clientes.add(cl);
        arquiva();
    }

    public void CadastrarReserva(Date entrada, Date saida, int desconto, String Cpf, ArrayList<Quarto> vectorQuartos) {
        int numeroReserva = 1;
        if (Reservas.size() > 0) {
            numeroReserva = Reservas.get(Reservas.size() - 1).getNumeroReserva();
            numeroReserva++;
        }

        Pagamento diarias = new Pagamento(TotalDiarias(entrada, saida, vectorQuartos) - ((TotalDiarias(entrada, saida, vectorQuartos) / 100) * desconto));
        Pagamento pgtReserva = new Pagamento(TotalDiarias(vectorQuartos) - ((TotalDiarias(vectorQuartos) / 100) * desconto));

        Reserva rs = new Reserva(numeroReserva, entrada, saida, diarias, pgtReserva, Cpf, vectorQuartos);
        Reservas.add(rs);
        arquiva();

    }

    public void CadastraQuarto(double preco, int numero, String descricao) {
        Quarto qt = new Quarto(preco, numero, descricao);
        this.Quartos.add(qt);
        arquiva();
    }

    public void AlterarQuarto(double preco, int numero, String descricao) {
        for (int i = 0; i < this.Quartos.size(); i++) {
            if (this.Quartos.get(i).getNumero() == numero) {
                this.Quartos.get(i).setDescricao(descricao);
                this.Quartos.get(i).setPreco(preco);
                break;
            }
        }
        arquiva();
    }

    public void AlterarCliente(String CPF, String nome, String endereco, String telefone) {
        for (int i = 0; i < this.Clientes.size(); i++) {
            if (this.Clientes.get(i).getCPF() == CPF) {
                this.Clientes.get(i).setEndereco(endereco);
                this.Clientes.get(i).setNome(nome);
                this.Clientes.get(i).setTelefone(telefone);
                break;
            }
        }
        arquiva();
    }

    public void AlterarReserva(int numeroReserva, Date entrada, Date saida, double desconto, Pagamento diarias, Pagamento pgtReserva, String Cpf) {
        for (int i = 0; i < this.Reservas.size(); i++) {
            if (this.Reservas.get(i).getNumeroReserva() == numeroReserva) {
                this.Reservas.get(i).setEntrada(entrada);
                this.Reservas.get(i).setSaida(saida);
                break;
            }
        }
        arquiva();
    }

    public void RemoverReserva(int numeroReserva) {
        for (int i = 0; i < this.Reservas.size(); i++) {
            if (this.Reservas.get(i).getNumeroReserva() == numeroReserva) {
                this.Reservas.remove(i);
                break;
            }
        }
        arquiva();
    }

    public void RemoverQuarto(int nro) {
        for (int i = 0; i < this.Quartos.size(); i++) {
            if (this.Quartos.get(i).getNumero() == nro) {
                this.Quartos.remove(i);
                break;
            }
        }
        arquiva();
    }

    public void RemoverCliente(String CPF) {
        for (int i = 0; i < this.Clientes.size(); i++) {
            if (this.Clientes.get(i).getCPF() == CPF) {
                this.Clientes.remove(i);
                break;
            }
        }
        arquiva();
    }

    public Quarto getQuarto(int nro) {
        for (int i = 0; i < this.Quartos.size(); i++) {
            if (this.Quartos.get(i).getNumero() == nro) {
                return Quartos.get(i);
            }
        }
        return null;
    }

    public Cliente getCliente(String cpf) {
        for (int i = 0; i < this.Clientes.size(); i++) {
            if (this.Clientes.get(i).getCPF() == cpf) {
                return Clientes.get(i);
            }
        }
        return null;
    }

    public Reserva getReserva(int nroReserva) {
        for (int i = 0; i < this.Reservas.size(); i++) {
            if (this.Reservas.get(i).getNumeroReserva() == nroReserva) {
                return Reservas.get(i);
            }
        }
        return null;
    }

    public double CalculaDesconto(int nroReserva, int desconto) {
        double total = 0;
        for (int i = 0; i < this.Reservas.size(); i++) {
            if (this.Reservas.get(i).getNumeroReserva() == nroReserva) {
                total = (Reservas.get(i).getDiarias().getValorTotal()) * (((double) desconto) / 100);
                return total;
            }
        }
        return total;
    }

    public double CalculaValPG(int nroReserva) {
        double total = 0;
        for (int i = 0; i < this.Reservas.size(); i++) {
            if (this.Reservas.get(i).getNumeroReserva() == nroReserva) {
                total = (Reservas.get(i).getDiarias().getValorPg() + Reservas.get(i).getPgtReserva().getValorPg());
                return total;
            }
        }

        return total;
    }

    public double CalculaValAPG(int nroReserva) {
        double total = 0;
        for (int i = 0; i < this.Reservas.size(); i++) {
            if (this.Reservas.get(i).getNumeroReserva() == nroReserva) {
                total = (Reservas.get(i).getDiarias().getValorTotal()) - CalculaValPG(nroReserva);
                return total;
            }
        }

        return total;
    }

    public void realizaPagamento(int nroReserva, double pagamento) {
        double falta;
        for (int i = 0; i < this.Reservas.size(); i++) {
            if (this.Reservas.get(i).getNumeroReserva() == nroReserva) {
                if (!this.Reservas.get(i).getPgtReserva().getSituacao()) {
                    falta = this.Reservas.get(i).getPgtReserva().getValorTotal() - this.Reservas.get(i).getPgtReserva().getValorPg();
                    if (pagamento >= falta) {
                        this.Reservas.get(i).getPgtReserva().setValorPg(falta + this.Reservas.get(i).getPgtReserva().getValorPg());
                        if (pagamento - falta > 0) {
                            this.Reservas.get(i).getDiarias().setValorPg(pagamento - falta);
                        }
                    } else {
                        this.Reservas.get(i).getPgtReserva().setValorPg(pagamento + this.Reservas.get(i).getPgtReserva().getValorPg());
                    }

                } else {
                    this.Reservas.get(i).getDiarias().setValorPg(pagamento + this.Reservas.get(i).getDiarias().getValorPg());
                }
            }
        }
        arquiva();

    }

    public ArrayList<Quarto> QuartoDisponiveis(Date Entrada, Date saida) {

        ArrayList<Quarto> disponiveis = new ArrayList<>(Quartos);
        for (int i = 0; i < this.Reservas.size(); i++) {
            if (!((Entrada.after(this.Reservas.get(i).getSaida())) || (saida.before(this.Reservas.get(i).getEntrada())))) {
                for (int j = 0; j < this.Reservas.get(i).getQuartos().size(); j++) {
                    disponiveis.remove(this.Reservas.get(i).getQuartos().get(j));
                }
            }
        }
        return disponiveis;
    }

    public double TotalDiarias(Date entrada, Date saida, ArrayList<Quarto> vectorQuartos) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar data1 = Calendar.getInstance();
        Calendar data2 = Calendar.getInstance();
        data1.setTime(entrada);
        data2.setTime(saida);
        double total = 0;
        int totalDias = data2.get(Calendar.DAY_OF_YEAR)
                - data1.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < vectorQuartos.size(); i++) {
            total += totalDias * vectorQuartos.get(i).getPreco();
        }
        return total;
    }

    public double TotalDiarias(ArrayList<Quarto> vectorQuartos) {
        double total = 0;
        for (int i = 0; i < vectorQuartos.size(); i++) {
            total += vectorQuartos.get(i).getPreco();
        }
        return total;
    }

    public ArrayList<Reserva> ListarReservas() {
        return Reservas;
    }

    public ArrayList<Quarto> ListarQuartos() {
        return Quartos;
    }

    public ArrayList<Cliente> ListaClientes() {
        return Clientes;
    }

    public void serializaCliente() throws Exception {
        try {
            FileOutputStream objFileOS = new FileOutputStream("cliente.dat");
            ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
            objOS.writeObject(Clientes);
            objOS.flush();
            objOS.close();
            objFileOS.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public void desserializaCliente() throws Exception {
        try {
            File objFile = new File("cliente.dat");
            if (objFile.exists()) {
                FileInputStream objFileIS = new FileInputStream("cliente.dat");
                ObjectInputStream objIS = new ObjectInputStream(objFileIS);
                Clientes = (ArrayList) objIS.readObject();
                objIS.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void serializaQuarto() throws Exception {
        try {
            FileOutputStream objFileOS = new FileOutputStream("quarto.dat");
            ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
            objOS.writeObject(Quartos);
            objOS.flush();
            objOS.close();
            objFileOS.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public void desserializaQuarto() throws Exception {
        try {
            File objFile = new File("quarto.dat");
            if (objFile.exists()) {
                FileInputStream objFileIS = new FileInputStream("quarto.dat");
                ObjectInputStream objIS = new ObjectInputStream(objFileIS);
                Quartos = (ArrayList) objIS.readObject();
                objIS.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void serializaReserva() throws Exception {
        try {
            FileOutputStream objFileOS = new FileOutputStream("reserva.dat");
            ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
            objOS.writeObject(Reservas);
            objOS.flush();
            objOS.close();
            objFileOS.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public void desserializaReserva() throws Exception {
        try {
            File objFile = new File("reserva.dat");
            if (objFile.exists()) {
                FileInputStream objFileIS = new FileInputStream("reserva.dat");
                ObjectInputStream objIS = new ObjectInputStream(objFileIS);
                Reservas = (ArrayList) objIS.readObject();
                objIS.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void arquiva() {
        try {
            serializaCliente();
            serializaQuarto();
            serializaReserva();
        } catch (Exception e) {
        }
    }

}
