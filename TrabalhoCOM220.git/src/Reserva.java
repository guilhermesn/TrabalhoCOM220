
import java.util.Date;
import java.util.Vector;


public class Reserva {
    private int numeroReserva;
    private Date entrada;
    private Date saida;
    private double desconto;
    private Vector quartos = new Vector();
    private Pagamento diarias;
    private Pagamento pgtReserva;
    private String Cpf;

    public Reserva(int numeroReserva, Date entrada, Date saida, double desconto, Pagamento diarias, Pagamento pgtReserva, String Cpf) {
        this.numeroReserva = numeroReserva;
        this.entrada = entrada;
        this.saida = saida;
        this.desconto = desconto;
        this.diarias = diarias;
        this.pgtReserva = pgtReserva;
        this.Cpf = Cpf;
    }

    public int getNumeroReserva() {
        return numeroReserva;
    }

    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public Vector getQuartos() {
        return quartos;
    }

    public void setQuartos(Vector quartos) {
        this.quartos = quartos;
    }

    public Pagamento getDiarias() {
        return diarias;
    }

    public void setDiarias(Pagamento diarias) {
        this.diarias = diarias;
    }

    public Pagamento getPgtReserva() {
        return pgtReserva;
    }

    public void setPgtReserva(Pagamento pgtReserva) {
        this.pgtReserva = pgtReserva;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }
    
    
    
    
    
}
