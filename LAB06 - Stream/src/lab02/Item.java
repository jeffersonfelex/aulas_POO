package lab02;

public class Item {
    private String nomeProduto;
    private double valor;

    // Construtor
    public Item(String nomeProduto, double valor) {
        this.nomeProduto = nomeProduto;
        this.valor = valor;
    }

    // Getters
    public String getNomeProduto() {
        return nomeProduto;
    }

    public double getValor() {
        return valor;
    }

    // Setters
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format("%s - R$ %.2f", nomeProduto, valor);
    }
}
