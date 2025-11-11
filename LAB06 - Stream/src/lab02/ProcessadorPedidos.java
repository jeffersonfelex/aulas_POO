package lab02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessadorPedidos {

    public static void main(String[] args) {
        // Criando lista de pedidos de exemplo
        List<Pedido> pedidos = Arrays.asList(
                new Pedido("João Silva", Arrays.asList(
                        new Item("Notebook", 2500.00),
                        new Item("Mouse", 80.00),
                        new Item("Teclado", 150.00)
                ), true),

                new Pedido("Maria Santos", Arrays.asList(
                        new Item("Smartphone", 1800.00),
                        new Item("Fone de Ouvido", 200.00)
                ), false),

                new Pedido("Carlos Oliveira", Arrays.asList(
                        new Item("Monitor", 900.00),
                        new Item("Webcam", 350.00)
                ), true),

                new Pedido("Ana Costa", Arrays.asList(
                        new Item("Tablet", 1200.00),
                        new Item("Capa", 50.00),
                        new Item("Carregador", 80.00)
                ), true),

                new Pedido("Pedro Almeida", Arrays.asList(
                        new Item("Desktop", 3000.00),
                        new Item("SSD", 400.00),
                        new Item("Memória RAM", 300.00)
                ), true)
        );

        System.out.println("========================================");
        System.out.println("  PROCESSAMENTO DE PEDIDOS DE COMPRA");
        System.out.println("========================================\n");

        // 1. Filtrar os pedidos pagos
        System.out.println("ETAPA 1: Filtrando pedidos pagos");
        System.out.println("-------------------------------------------");

        List<Pedido> pedidosPagos = pedidos.stream()
                .filter(p -> p.isPago())  // Lambda para filtrar pedidos pagos
                .collect(Collectors.toList());

        System.out.printf("Total de pedidos pagos: %d de %d%n%n",
                pedidosPagos.size(), pedidos.size());

        // 2. Calcular o valor total de cada pedido e exibir
        System.out.println("ETAPA 2: Valor total de cada pedido pago");
        System.out.println("-------------------------------------------");

        pedidosPagos.forEach(p -> {
            double total = p.calcularValorTotal();
            System.out.printf("Cliente: %s - Total: R$ %.2f%n", p.getCliente(), total);
        });
        System.out.println();

        // 3. Aplicar desconto de 10% em pedidos acima de R$ 1000,00
        System.out.println("ETAPA 3: Aplicando desconto de 10% em pedidos > R$ 1000,00");
        System.out.println("-------------------------------------------");

        // Cria um mapa com o valor final após desconto
        List<PedidoComDesconto> pedidosComDesconto = pedidosPagos.stream()
                .map(p -> {
                    double valorOriginal = p.calcularValorTotal();
                    double valorFinal = valorOriginal > 1000.00
                            ? valorOriginal * 0.90  // Aplica 10% de desconto
                            : valorOriginal;
                    return new PedidoComDesconto(p.getCliente(), valorOriginal, valorFinal);
                })
                .collect(Collectors.toList());

        pedidosComDesconto.forEach(pd -> {
            double desconto = pd.valorOriginal - pd.valorFinal;
            if (desconto > 0) {
                System.out.printf("Cliente: %s%n", pd.cliente);
                System.out.printf("  Valor original: R$ %.2f%n", pd.valorOriginal);
                System.out.printf("  Desconto: R$ %.2f%n", desconto);
                System.out.printf("  Valor final: R$ %.2f%n%n", pd.valorFinal);
            } else {
                System.out.printf("Cliente: %s - R$ %.2f (sem desconto)%n",
                        pd.cliente, pd.valorFinal);
            }
        });
        System.out.println();

        // 4. Retornar lista de clientes com pedidos > R$ 1500,00 após desconto
        System.out.println("ETAPA 4: Clientes com pedidos superiores a R$ 1500,00 (após desconto)");
        System.out.println("-------------------------------------------");

        List<String> clientesAcima1500 = pedidosComDesconto.stream()
                .filter(pd -> pd.valorFinal > 1500.00)  // Lambda para filtrar valores > 1500
                .map(pd -> pd.cliente)                   // Lambda para mapear apenas o nome
                .collect(Collectors.toList());

        if (clientesAcima1500.isEmpty()) {
            System.out.println("Nenhum cliente com pedidos acima de R$ 1500,00");
        } else {
            clientesAcima1500.forEach(cliente ->
                    System.out.println("   - " + cliente)
            );
        }

        System.out.println("\n========================================");
        System.out.println("RESUMO FINAL:");
        System.out.println("========================================");
        System.out.printf("Total de pedidos processados: %d%n", pedidos.size());
        System.out.printf("Pedidos pagos: %d%n", pedidosPagos.size());
        System.out.printf("Clientes com pedidos > R$ 1500,00: %d%n", clientesAcima1500.size());

        double totalVendas = pedidosComDesconto.stream()
                .mapToDouble(pd -> pd.valorFinal)
                .sum();
        System.out.printf("Total de vendas (após descontos): R$ %.2f%n", totalVendas);
        System.out.println("========================================");
    }

    // Classe auxiliar para armazenar informações de desconto
    static class PedidoComDesconto {
        String cliente;
        double valorOriginal;
        double valorFinal;

        PedidoComDesconto(String cliente, double valorOriginal, double valorFinal) {
            this.cliente = cliente;
            this.valorOriginal = valorOriginal;
            this.valorFinal = valorFinal;
        }
    }
}
