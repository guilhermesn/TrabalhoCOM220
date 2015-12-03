
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class CtrlPousada {

    private ArrayList<Reserva> Reservas = new ArrayList<>();
    private ArrayList<Quarto> Quartos = new ArrayList<>();
    private ArrayList<Cliente> Clientes = new ArrayList<>();
    private final VisaoPousada view;

    public CtrlPousada() throws Exception {
        this.view = new VisaoPousada(this);
        view.gerarInterface();   
        
        Quarto qt = new Quarto(28,1,"Muito bonito");
        
        Quartos.add(qt);
        qt = new Quarto(299,2,"Muito nossa");
        Quartos.add(qt);
        qt = new Quarto(29,3,"Muito feio");
        Quartos.add(qt);
        qt = new Quarto(26,4,"Muito fumante");
        Quartos.add(qt);
        
        Cliente ct = new Cliente("23432","Chupita","Rua do fror","91195472");
        Clientes.add(ct);
        ct = new Cliente("33355","Markito","Rua Nois vai","35914445");
        Clientes.add(ct);
        ct = new Cliente("5888","Iza folgada","Rua Nois vei","36225588");
        Clientes.add(ct);
        
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
                break;
            }
        }
    }
    
    public void AlterarCliente(String CPF, String nome, String endereco, String telefone)
    {
        for(int i = 0; i<this.Clientes.size();i++)
        {
            if(this.Clientes.get(i).getCPF() == CPF)
            {
                this.Clientes.get(i).setEndereco(endereco);
                this.Clientes.get(i).setNome(nome);
                this.Clientes.get(i).setTelefone(telefone);
                break;
            }
        }
    }
    public void AlterarReserva (int numeroReserva, Date entrada, Date saida, double desconto, Pagamento diarias, Pagamento pgtReserva, String Cpf)
    {
        for(int i = 0; i<this.Reservas.size();i++)
        {
            if(this.Reservas.get(i).getNumeroReserva() == numeroReserva)
            {
                this.Reservas.get(i).setEntrada(entrada);
                this.Reservas.get(i).setSaida(saida);
                break;
            }
        }        
    }
    public void RemoverReserva (int numeroReserva)
    {
        for(int i = 0; i<this.Reservas.size();i++)
        {
            if(this.Reservas.get(i).getNumeroReserva() == numeroReserva)
            {
                this.Reservas.remove(i);
                break;
            }
        }       
    }
    public void RemoverQuarto(int nro)
    {
        for(int i = 0; i<this.Quartos.size();i++)
        {
            if(this.Quartos.get(i).getNumero() == nro)
            {
                this.Quartos.remove(i);
                break;
            }
        }
  
    }
    public void RemoverCliente(String CPF)
    {
        for(int i = 0; i<this.Clientes.size();i++)
        {
            if(this.Clientes.get(i).getCPF()== CPF)
            {
                this.Clientes.remove(i);
                break;
            }
        }
  
    }
    public Quarto getQuarto(int nro)
    {
        for(int i = 0; i<this.Quartos.size();i++)
        {
            if(this.Quartos.get(i).getNumero() == nro)
            {
                return Quartos.get(i);
            }
        }
        return null;
    }
    public Cliente getCliente(String cpf)
    {
        for(int i = 0; i<this.Clientes.size();i++)
        {
            if(this.Clientes.get(i).getCPF() == cpf)
            {
                return Clientes.get(i);
            }
        }
        return null;
    }
    public Reserva getReserva(int nroReserva)
    {
        for(int i = 0; i<this.Reservas.size();i++)
        {
            if(this.Reservas.get(i).getNumeroReserva() == nroReserva)
            {
                return Reservas.get(i);
            }
        }   
        return null;
    }

    public double CalculaDesconto(int nroReserva, int desconto) {
        double total=0;
        for(int i = 0; i<this.Reservas.size();i++)
        {
            if(this.Reservas.get(i).getNumeroReserva() == nroReserva)
            {
                total = (Reservas.get(i).getDiarias().getValor() + Reservas.get(i).getPgtReserva().getValor())*(desconto/100);
                return total;
            }
        }   
        
        return total;
    }

    public ArrayList<Quarto> QuartoDisponiveis(Date Entrada, Date saida) {
        ArrayList<Quarto> reservasData = new ArrayList<>();
        ArrayList<Quarto> disponiveis = new ArrayList<>(Quartos);
        for (int i = 0; i < this.Reservas.size(); i++) {
            if (this.Reservas.get(i).getEntrada().after(Entrada) && this.Reservas.get(i).getEntrada().before(saida)) {
                for (int j = 0; j < this.Reservas.get(j).getQuartos().size(); j++) {
                    if (this.Reservas.get(i).getQuartos().contains(j)) {
                        reservasData.add(Quartos.get(i));
                    }
                }
            }
        }
        for(int i=0;i<disponiveis.size();i++)
        {
            for(int j=0; j< reservasData.size();j++)
            {
                if(disponiveis.get(i) == reservasData.get(j) )
                {
                    disponiveis.remove(i);
                }
            }
        }
        return disponiveis;
        }
        
    public ArrayList<Reserva> ListarReservas(){
        return Reservas;
    }
    public ArrayList<Quarto> ListarQuartos(){
        return Quartos;
    }
    
     public ArrayList<Cliente> ListaClientes(){
        return Clientes;
    }   
    public void serializaCliente() throws Exception {
        try{
            FileOutputStream objFileOS = new FileOutputStream("cliente.dat");
            ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
            objOS.writeObject(Clientes);
            objOS.flush();
            objOS.close();
            objFileOS.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        
    }

    public void desserializaCliente() throws Exception {
        try
        {
            File objFile = new File("cliente.dat");
            if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("cliente.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            Clientes = (ArrayList) objIS.readObject();
            objIS.close();
        }
         }catch(IOException ioe){
             ioe.printStackTrace();
          }
    }
    public void serializaQuarto() throws Exception {
        try{
            FileOutputStream objFileOS = new FileOutputStream("quarto.dat");
            ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
            objOS.writeObject(Quartos);
            objOS.flush();
            objOS.close();
            objFileOS.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        
    }

    public void desserializaQuarto() throws Exception {
        try
        {
            File objFile = new File("quarto.dat");
            if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("quarto.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            Quartos = (ArrayList) objIS.readObject();
            objIS.close();
        }
         }catch(IOException ioe){
             ioe.printStackTrace();
          }
    }
    public void serializaReserva() throws Exception {
        try{
            FileOutputStream objFileOS = new FileOutputStream("reserva.dat");
            ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
            objOS.writeObject(Reservas);
            objOS.flush();
            objOS.close();
            objFileOS.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        
    }

    public void desserializaReserva() throws Exception {
        try
        {
            File objFile = new File("reserva.dat");
            if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("reserva.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            Reservas = (ArrayList) objIS.readObject();
            objIS.close();
        }
         }catch(IOException ioe){
             ioe.printStackTrace();
          }
    }
}
