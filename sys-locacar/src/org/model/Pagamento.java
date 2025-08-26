package org.model;

public class Pagamento {
    private long id;
    private TipoPagamento TipoPagamento;
    private float valorTotal;

    

    public Pagamento(long id, org.model.Pagamento.TipoPagamento tipoPagamento, float valorTotal) {
        this.id = id;
        TipoPagamento = tipoPagamento;
        this.valorTotal = valorTotal;
    }
    



    public long getId() {
        return id;
    }




    public void setId(long id) {
        this.id = id;
    }




    public TipoPagamento getTipoPagamento() {
        return TipoPagamento;
    }




    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        TipoPagamento = tipoPagamento;
    }




    public float getValorTotal() {
        return valorTotal;
    }




    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }




    public enum TipoPagamento{
        dinheiro,
        cartao,
        pix,
        boleto
    }
}
