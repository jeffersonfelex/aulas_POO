package lab02;

import java.util.List;

public class Pedido {
    private String cliente;
    private List<Item> itens;
    private boolean pago;

    // Construtor
    public Pedido(String cliente, List<Item> itens, boolean pago) {
        this.cliente = cliente;
        this.itens = itens;
        this.pago = pago;
    }

    // Getters
    public String getCliente() {
        return cliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public boolean isPago() {
        return pago;
    }

    // Setters
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    /**
     * Calcula o valor total do pedido
     */
    public double calcularValorTotal() {
        return itens.stream()
                .mapToDouble(Item::getValor)
                .sum();
    }

    @Override
    public String toString() {
        return String.format("Pedido{cliente='%s', itens=%d, pago=%s, total=R$ %.2f}",
                cliente, itens.size(), pago ? "Sim" : "Não", calcularValorTotal());
    }
}
