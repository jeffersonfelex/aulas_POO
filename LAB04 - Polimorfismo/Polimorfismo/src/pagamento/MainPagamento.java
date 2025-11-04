package pagamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MainPagamento {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<MetodoPagamento> pagamentos = new ArrayList<>();
        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE PAGAMENTOS ===");
            System.out.println("1 - Adicionar pagamento com Cartão");
            System.out.println("2 - Adicionar pagamento com Boleto");
            System.out.println("3 - Adicionar pagamento com Pix");
            System.out.println("4 - Processar todos os pagamentos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do titular: ");
                    String nome = sc.nextLine();
                    System.out.print("Número do cartão: ");
                    String numero = sc.nextLine();
                    System.out.print("Validade (MM/AA): ");
                    String validade = sc.nextLine();
                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    MetodoPagamento cartao = new CartaoCredito(numero, nome, validade);
                    pagamentos.add(cartao);
                    cartao.processarPagamento(valor);
                }
                case 2 -> {
                    System.out.print("Código de barras: ");
                    String codigo = sc.nextLine();
                    System.out.print("Ano de vencimento: ");
                    int ano = sc.nextInt();
                    System.out.print("Mês: ");
                    int mes = sc.nextInt();
                    System.out.print("Dia: ");
                    int dia = sc.nextInt();
                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    MetodoPagamento boleto = new Boleto(codigo, LocalDate.of(ano, mes, dia));
                    pagamentos.add(boleto);
                    boleto.processarPagamento(valor);
                }
                case 3 -> {
                    System.out.print("Chave Pix: ");
                    String chave = sc.nextLine();
                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();
                    MetodoPagamento pix = new Pix(chave);
                    pagamentos.add(pix);
                    pix.processarPagamento(valor);
                }
                case 4 -> {
                    if (pagamentos.isEmpty()) {
                        System.out.println("⚠️ Nenhum pagamento realizado ainda.");
                    } else {
                        System.out.println("💰 Todos os pagamentos foram processados!");
                    }
                }
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("❌ Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}


