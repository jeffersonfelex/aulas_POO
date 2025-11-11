import lab01.ProcessadorFuncionarios;
import lab02.ProcessadorPedidos;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("STREAMS EM JAVA");
        System.out.println();
        System.out.println("Escolha qual laboratório executar:");
        System.out.println("1 - Processador de Funcionários");
        System.out.println("2 - Processador de Pedidos");
        System.out.println("3 - Executar ambos");
        System.out.println("0 - Sair");
        System.out.println();
        System.out.print("Opção: ");

        int opcao = scanner.nextInt();
        System.out.println();

        switch (opcao) {
            case 1:
                executarLab01();
                break;
            case 2:
                executarLab02();
                break;
            case 3:
                executarLab01();
                System.out.println("\n\n");
                executarLab02();
                break;
            case 0:
                System.out.println("Encerrando...");
                break;
            default:
                System.out.println("Opção inválida!");
        }

        scanner.close();
    }

    private static void executarLab01() {
        System.out.println("\n EXECUTANDO ATIVIDADE 01 \n");
        ProcessadorFuncionarios processador = new ProcessadorFuncionarios();
        processador.gerarRelatorio();
    }

    private static void executarLab02() {
        System.out.println("\n EXECUTANDO ATIVIDADE 02 \n");

        ProcessadorPedidos.main(new String[]{});
    }
}