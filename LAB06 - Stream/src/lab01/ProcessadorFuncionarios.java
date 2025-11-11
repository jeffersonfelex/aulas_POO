package lab01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessadorFuncionarios {

    // Lista de funcionários
    private List<Funcionario> funcionarios;

    public ProcessadorFuncionarios() {
        // Criando a lista de funcionários com pelo menos 5 funcionários
        this.funcionarios = Arrays.asList(
                new Funcionario("Ana Silva", 28, 3500.00),
                new Funcionario("Carlos Santos", 35, 5200.00),
                new Funcionario("Maria Oliveira", 42, 6800.00),
                new Funcionario("João Costa", 25, 2800.00),
                new Funcionario("Paula Ferreira", 38, 4500.00),
                new Funcionario("Roberto Lima", 31, 3900.00)
        );
    }

    /**
     * Método que gera o relatório completo com todas as operações
     */
    public void gerarRelatorio() {
        System.out.println("========================================");
        System.out.println("    RELATÓRIO DE FUNCIONÁRIOS");
        System.out.println("========================================\n");

        // 1. FILTRAGEM: Funcionários com idade superior a 30 anos
        System.out.println("1. Funcionários com idade superior a 30 anos:");
        System.out.println("-------------------------------------------");

        List<Funcionario> funcionariosMais30 = funcionarios.stream()
                .filter(f -> f.getIdade() > 30)  // Lambda para filtrar por idade
                .collect(Collectors.toList());

        funcionariosMais30.forEach(f ->
                System.out.println("   - " + f.getNome() + " (" + f.getIdade() + " anos)")
        );
        System.out.println();

        // 2. MAPEAMENTO: Lista de nomes dos funcionários
        System.out.println("2. Lista de nomes dos funcionários:");
        System.out.println("-------------------------------------------");

        List<String> nomes = funcionarios.stream()
                .map(f -> f.getNome())  // Lambda para mapear apenas os nomes
                .collect(Collectors.toList());

        nomes.forEach(nome -> System.out.println("   - " + nome));
        System.out.println();

        // 3. REDUÇÃO: Soma total dos salários
        System.out.println("3. Soma total dos salários:");
        System.out.println("-------------------------------------------");

        double somaSalarios = funcionarios.stream()
                .mapToDouble(f -> f.getSalario())  // Converte para DoubleStream
                .reduce(0.0, (a, b) -> a + b);     // Lambda para reduzir somando os valores

        System.out.printf("   Total: R$ %.2f%n", somaSalarios);
        System.out.println();

        // Informações adicionais
        System.out.println("========================================");
        System.out.println("RESUMO:");
        System.out.println("========================================");
        System.out.printf("Total de funcionários: %d%n", funcionarios.size());
        System.out.printf("Funcionários com mais de 30 anos: %d%n", funcionariosMais30.size());
        System.out.printf("Salário médio: R$ %.2f%n", somaSalarios / funcionarios.size());
        System.out.println("========================================");
    }

    /**
     * Método main para executar o programa
     */
    public static void main(String[] args) {
        ProcessadorFuncionarios processador = new ProcessadorFuncionarios();
        processador.gerarRelatorio();
    }
}
