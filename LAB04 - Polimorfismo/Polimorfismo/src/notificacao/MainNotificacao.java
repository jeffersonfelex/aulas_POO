package notificacao;

import java.util.ArrayList;
import java.util.Scanner;

public class MainNotificacao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Notificacao> notificacoes = new ArrayList<>();
        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE NOTIFICAÇÕES ===");
            System.out.println("1 - Adicionar notificação por E-mail");
            System.out.println("2 - Adicionar notificação por SMS");
            System.out.println("3 - Adicionar notificação Push");
            System.out.println("4 - Enviar todas as notificações");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Endereço de e-mail: ");
                    String email = sc.nextLine();
                    notificacoes.add(new EmailNotificacao(email));
                    System.out.println("✅ E-mail adicionado!");
                }
                case 2 -> {
                    System.out.print("Número de telefone: ");
                    String telefone = sc.nextLine();
                    notificacoes.add(new SMSNotificacao(telefone));
                    System.out.println("✅ SMS adicionado!");
                }
                case 3 -> {
                    System.out.print("Token do dispositivo: ");
                    String token = sc.nextLine();
                    notificacoes.add(new PushNotificacao(token));
                    System.out.println("✅ Push adicionado!");
                }
                case 4 -> {
                    if (notificacoes.isEmpty()) {
                        System.out.println("⚠️ Nenhuma notificação cadastrada.");
                    } else {
                        System.out.print("Digite a mensagem a ser enviada: ");
                        String mensagem = sc.nextLine();
                        for (Notificacao n : notificacoes) {
                            n.enviar(mensagem);
                        }
                    }
                }
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("❌ Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}
