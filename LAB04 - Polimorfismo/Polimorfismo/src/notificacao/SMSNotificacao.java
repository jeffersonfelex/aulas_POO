package notificacao;

// SMSNotificacao.java
public class SMSNotificacao implements Notificacao {
    private String numeroTelefone;

    public SMSNotificacao(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando SMS para " + numeroTelefone + ": " + mensagem);
    }
}

