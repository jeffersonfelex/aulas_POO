package formas;

import java.util.ArrayList;
import java.util.Scanner;

public class MainFormas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Forma> formas = new ArrayList<>();
        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE FORMAS GEOMÉTRICAS ===");
            System.out.println("1 - Adicionar Círculo");
            System.out.println("2 - Adicionar Quadrado");
            System.out.println("3 - Adicionar Triângulo");
            System.out.println("4 - Listar todas as formas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Informe o raio: ");
                    double raio = sc.nextDouble();
                    formas.add(new Circulo(raio));
                    System.out.println("✅ Círculo adicionado!");
                }
                case 2 -> {
                    System.out.print("Informe o lado: ");
                    double lado = sc.nextDouble();
                    formas.add(new Quadrado(lado));
                    System.out.println("✅ Quadrado adicionado!");
                }
                case 3 -> {
                    System.out.print("Informe a base: ");
                    double base = sc.nextDouble();
                    System.out.print("Informe a altura: ");
                    double altura = sc.nextDouble();
                    formas.add(new Triangulo(base, altura));
                    System.out.println("✅ Triângulo adicionado!");
                }
                case 4 -> {
                    if (formas.isEmpty()) {
                        System.out.println("⚠️ Nenhuma forma cadastrada ainda.");
                    } else {
                        System.out.println("\n=== LISTA DE FORMAS ===");
                        for (Forma f : formas) {
                            f.desenhar();
                            System.out.println("Área: " + f.calcularArea());
                            System.out.println("--------------------");
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
