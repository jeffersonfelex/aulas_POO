package pagamento;

import java.time.LocalDate;

public class Boleto implements MetodoPagamento {
    private String codigoBarra;
    private LocalDate dataVencimento;

    public Boleto(String codigoBarra, LocalDate dataVencimento) {
        this.codigoBarra = codigoBarra;
        this.dataVencimento = dataVencimento;
    }

    @Override
    public boolean processarPagamento(double valor) {
        System.out.println("Pagamento com boleto gerado. Pague até " + dataVencimento);
        return true;
    }
}

