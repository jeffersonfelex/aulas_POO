package funcionario;

import java.util.ArrayList;
import java.util.Scanner;

public class MainFuncionario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE FUNCIONÁRIOS ===");
            System.out.println("1 - Cadastrar Gerente");
            System.out.println("2 - Cadastrar Desenvolvedor");
            System.out.println("3 - Cadastrar Estagiário");
            System.out.println("4 - Listar todos os funcionários");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar o buffer

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do gerente: ");
                    String nome = sc.nextLine();
                    System.out.print("Matrícula: ");
                    String matricula = sc.nextLine();
                    System.out.print("Salário base: ");
                    double salarioBase = sc.nextDouble();
                    System.out.print("Bônus: ");
                    double bonus = sc.nextDouble();
                    funcionarios.add(new Gerente(nome, matricula, salarioBase, bonus));
                    System.out.println("✅ Gerente cadastrado com sucesso!");
                }
                case 2 -> {
                    System.out.print("Nome do desenvolvedor: ");
                    String nome = sc.nextLine();
                    System.out.print("Matrícula: ");
                    String matricula = sc.nextLine();
                    System.out.print("Salário base: ");
                    double salarioBase = sc.nextDouble();
                    sc.nextLine(); // limpar
                    System.out.print("Linguagem preferida: ");
                    String linguagem = sc.nextLine();
                    System.out.print("Anos de experiência: ");
                    int anos = sc.nextInt();
                    funcionarios.add(new Desenvolvedor(nome, matricula, salarioBase, linguagem, anos));
                    System.out.println("✅ Desenvolvedor cadastrado com sucesso!");
                }
                case 3 -> {
                    System.out.print("Nome do estagiário: ");
                    String nome = sc.nextLine();
                    System.out.print("Matrícula: ");
                    String matricula = sc.nextLine();
                    System.out.print("Bolsa auxílio: ");
                    double bolsa = sc.nextDouble();
                    funcionarios.add(new Estagiario(nome, matricula, bolsa));
                    System.out.println("✅ Estagiário cadastrado com sucesso!");
                }
                case 4 -> {
                    if (funcionarios.isEmpty()) {
                        System.out.println("⚠️ Nenhum funcionário cadastrado ainda.");
                    } else {
                        System.out.println("\n=== LISTA DE FUNCIONÁRIOS ===");
                        for (Funcionario f : funcionarios) {
                            System.out.println("------------------------");
                            System.out.println("Nome: " + f.nome);
                            System.out.println("Matrícula: " + f.matricula);
                            System.out.println("Salário: R$" + f.calcularSalario());
                            f.realizarTarefa();
                        }
                    }
                }
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> {
                    if (opcao < 0 || opcao > 4)
                        System.out.println("❌ Opção inválida!");
                }
            }

        } while (opcao != 0);

        sc.close();
    }
}
