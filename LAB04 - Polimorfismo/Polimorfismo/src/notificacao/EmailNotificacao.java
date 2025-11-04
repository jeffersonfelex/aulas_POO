package notificacao;

public class EmailNotificacao implements Notificacao {
    private String enderecoEmail;

    public EmailNotificacao(String enderecoEmail) {
        this.enderecoEmail = enderecoEmail;
    }

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando e-mail para " + enderecoEmail + ": " + mensagem);
    }
}

