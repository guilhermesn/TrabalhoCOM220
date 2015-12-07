

public class Pagamento {
    private double valorTotal;
    private double valorPg;
    private Boolean situacao = false;

    public Pagamento(double valor) {
        this.valorTotal = valor;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
        
    }

    public double getValorPg() {
        return valorPg;
    }

    public void setValorPg(double valorPg) {
        if(valorTotal-valorPg >= valorPg){
            situacao = true;
        }
        this.valorPg = valorPg;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    
}
