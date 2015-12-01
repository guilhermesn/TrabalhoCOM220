
import java.io.Serializable;


public class Cliente implements Serializable{

    private String CPF;
    private String nome;
    private String endereco;
    private String telefone;

    public Cliente(String CPF, String nome, String endereco, String telefone) {
        this.CPF = CPF;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCPF() {
        return CPF;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

}
