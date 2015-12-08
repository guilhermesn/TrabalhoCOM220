

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
        this.valorPg = valorPg;
        if(this.valorPg>=this.valorTotal){
            situacao = true;
        }
        
    }

    public Boolean getSituacao() {
        return situacao;
    }

    
}
