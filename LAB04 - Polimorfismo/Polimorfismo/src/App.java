import java.util.Scanner;
import formas.MainFormas;
import pagamento.MainPagamento;
import notificacao.MainNotificacao;
import funcionario.MainFuncionario;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- SISTEMA PRINCIPAL ---");
            System.out.println("1 - Formas Geométricas");
            System.out.println("2 - Pagamentos");
            System.out.println("3 - Notificações");
            System.out.println("4 - Funcionários");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> MainFormas.main(new String[]{});
                case 2 -> MainPagamento.main(new String[]{});
                case 3 -> MainNotificacao.main(new String[]{});
                case 4 -> MainFuncionario.main(new String[]{});
                case 0 -> System.out.println("Encerrando o sistema principal :/");
                default -> System.out.println(" Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}
