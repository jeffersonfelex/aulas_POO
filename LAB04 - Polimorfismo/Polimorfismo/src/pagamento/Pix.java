package pagamento;

public class Pix implements MetodoPagamento {
    private String chavePix;

    public Pix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public boolean processarPagamento(double valor) {
        System.out.println("Pagamento com Pix realizado para a chave " + chavePix);
        return true;
    }
}
