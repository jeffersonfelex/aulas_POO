package notificacao;

public class PushNotificacao implements Notificacao {
    private String tokenDispositivo;

    public PushNotificacao(String tokenDispositivo) {
        this.tokenDispositivo = tokenDispositivo;
    }

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando notificação push para o dispositivo " + tokenDispositivo + ": " + mensagem);
    }
}

