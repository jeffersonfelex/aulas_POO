package pagamento;

public class CartaoCredito implements MetodoPagamento {
    private String numeroCartao, nomeTitular, validade;

    public CartaoCredito(String numeroCartao, String nomeTitular, String validade) {
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.validade = validade;
    }

    @Override
    public boolean processarPagamento(double valor) {
        if (valor > 0) {
            System.out.println("Pagamento de R$" + valor + " com cartão de crédito processado.");
            return true;
        }
        return false;
    }
}

