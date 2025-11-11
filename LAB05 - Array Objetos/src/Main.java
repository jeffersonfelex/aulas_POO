import java.util.Scanner;

// ========== CLASSES DO EXERCÍCIO 1 ==========
class Aluno {
    private String nome;
    private double nota;

    public Aluno(String nome, double nota) {
        this.nome = nome;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return nota;
    }

    public boolean isAprovado() {
        return nota >= 60;
    }
}

// ========== CLASSES DO EXERCÍCIO 2 ==========
class Produto {
    private String nome;
    private String codigo;
    private int quantidadeEstoque;

    public Produto(String nome, String codigo, int quantidadeEstoque) {
        this.nome = nome;
        this.codigo = codigo;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public boolean precisaReabastecimento() {
        return quantidadeEstoque < 10;
    }

    public void exibirDados() {
        System.out.println("\n--- DADOS DO PRODUTO ---");
        System.out.println("Nome: " + nome);
        System.out.println("Código: " + codigo);
        System.out.println("Quantidade em estoque: " + quantidadeEstoque + " unidades");

        if (precisaReabastecimento()) {
            System.out.println("⚠️  ALERTA: Produto precisa ser reabastecido!");
        }
    }
}

// ========== CLASSES DO EXERCÍCIO 3 ==========
class DiaTemperatura {
    private String diaSemana;
    private double temperatura;

    public DiaTemperatura(String diaSemana, double temperatura) {
        this.diaSemana = diaSemana;
        this.temperatura = temperatura;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public double getTemperatura() {
        return temperatura;
    }
}

// ========== CLASSES DO EXERCÍCIO 4 ==========
class RegistroVenda {
    private int dia;
    private double valorVenda;

    public RegistroVenda(int dia, double valorVenda) {
        this.dia = dia;
        this.valorVenda = valorVenda;
    }

    public int getDia() {
        return dia;
    }

    public double getValorVenda() {
        return valorVenda;
    }
}

// ========== CLASSE PRINCIPAL ==========
public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║   LABORATÓRIO - ARRAY DE OBJETOS         ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.println("1 - Gerenciamento de Notas de Alunos");
            System.out.println("2 - Controle de Estoque de Produtos");
            System.out.println("3 - Análise de Temperaturas Diárias");
            System.out.println("4 - Registro de Vendas Mensais");
            System.out.println("0 - Sair");
            System.out.print("\nEscolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Limpar buffer

            System.out.println();

            switch (opcao) {
                case 1:
                    exercicio1();
                    break;
                case 2:
                    exercicio2();
                    break;
                case 3:
                    exercicio3();
                    break;
                case 4:
                    exercicio4();
                    break;
                case 0:
                    System.out.println("Programa encerrado. Até logo!");
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }

            if (opcao != 0) {
                System.out.println("\nPressione ENTER para continuar...");
                sc.nextLine();
            }

        } while (opcao != 0);

        sc.close();
    }

    // ========== EXERCÍCIO 1 ==========
    public static void exercicio1() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  EXERCÍCIO 1 - NOTAS DE ALUNOS           ║");
        System.out.println("╚══════════════════════════════════════════╝");

        Aluno[] alunos = new Aluno[10];
        double somaNotas = 0;

        // Inserção dos dados
        for (int i = 0; i < alunos.length; i++) {
            System.out.println("\nAluno " + (i + 1) + ":");
            System.out.print("Nome: ");
            String nome = sc.nextLine();

            double nota;
            do {
                System.out.print("Nota (0-100): ");
                nota = sc.nextDouble();
                if (nota < 0 || nota > 100) {
                    System.out.println("Nota inválida! Digite um valor entre 0 e 100.");
                }
            } while (nota < 0 || nota > 100);

            sc.nextLine(); // Limpar buffer

            alunos[i] = new Aluno(nome, nota);
            somaNotas += nota;
        }

        // Cálculo da média
        double media = somaNotas / alunos.length;
        System.out.println("\n═══════════════ RESULTADOS ═══════════════");
        System.out.printf("Média da turma: %.2f\n", media);

        // Contagem de aprovados e reprovados
        int aprovados = 0;
        int reprovados = 0;

        System.out.println("\n--- ALUNOS APROVADOS (nota >= 60) ---");
        for (Aluno aluno : alunos) {
            if (aluno.isAprovado()) {
                System.out.printf("✓ %s - Nota: %.2f\n", aluno.getNome(), aluno.getNota());
                aprovados++;
            }
        }

        System.out.println("\n--- ALUNOS REPROVADOS (nota < 60) ---");
        for (Aluno aluno : alunos) {
            if (!aluno.isAprovado()) {
                System.out.printf("✗ %s - Nota: %.2f\n", aluno.getNome(), aluno.getNota());
                reprovados++;
            }
        }

        System.out.println("\n═══════════════ ESTATÍSTICAS ═════════════");
        System.out.println("Total de aprovados: " + aprovados);
        System.out.println("Total de reprovados: " + reprovados);
    }

    // ========== EXERCÍCIO 2 ==========
    public static void exercicio2() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  EXERCÍCIO 2 - CONTROLE DE ESTOQUE       ║");
        System.out.println("╚══════════════════════════════════════════╝");

        Produto[] produtos = new Produto[5];

        // Cadastro de produtos
        for (int i = 0; i < produtos.length; i++) {
            System.out.println("\nProduto " + (i + 1) + ":");
            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("Código: ");
            String codigo = sc.nextLine();

            System.out.print("Quantidade em estoque: ");
            int quantidade = sc.nextInt();
            sc.nextLine(); // Limpar buffer

            produtos[i] = new Produto(nome, codigo, quantidade);
        }

        // Verificação de produtos com estoque baixo
        System.out.println("\n═══════════ PRODUTOS COM ESTOQUE BAIXO ═══════════");
        boolean temEstoqueBaixo = false;
        for (Produto produto : produtos) {
            if (produto.precisaReabastecimento()) {
                System.out.printf("⚠️  %s (Código: %s) - Estoque: %d unidades\n",
                        produto.getNome(), produto.getCodigo(), produto.getQuantidadeEstoque());
                temEstoqueBaixo = true;
            }
        }
        if (!temEstoqueBaixo) {
            System.out.println("✓ Todos os produtos estão com estoque adequado.");
        }

        // Pesquisa de produtos
        int opcao;
        do {
            System.out.println("\n═══════════ PESQUISA DE PRODUTOS ═══════════");
            System.out.println("1 - Pesquisar por nome");
            System.out.println("2 - Pesquisar por código");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Limpar buffer

            if (opcao == 1) {
                System.out.print("Digite o nome do produto: ");
                String nomeBusca = sc.nextLine();
                boolean encontrado = false;

                for (Produto produto : produtos) {
                    if (produto.getNome().equalsIgnoreCase(nomeBusca)) {
                        produto.exibirDados();
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("❌ Produto não encontrado!");
                }

            } else if (opcao == 2) {
                System.out.print("Digite o código do produto: ");
                String codigoBusca = sc.nextLine();
                boolean encontrado = false;

                for (Produto produto : produtos) {
                    if (produto.getCodigo().equalsIgnoreCase(codigoBusca)) {
                        produto.exibirDados();
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("❌ Produto não encontrado!");
                }
            }

        } while (opcao != 0);
    }

    // ========== EXERCÍCIO 3 ==========
    public static void exercicio3() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  EXERCÍCIO 3 - TEMPERATURAS DIÁRIAS      ║");
        System.out.println("╚══════════════════════════════════════════╝");

        String[] diasSemana = {"Segunda-feira", "Terça-feira", "Quarta-feira",
                "Quinta-feira", "Sexta-feira", "Sábado", "Domingo"};
        DiaTemperatura[] semana = new DiaTemperatura[7];
        double somaTemperaturas = 0;

        // Inserção das temperaturas
        for (int i = 0; i < semana.length; i++) {
            System.out.print(diasSemana[i] + " - Temperatura (°C): ");
            double temp = sc.nextDouble();

            semana[i] = new DiaTemperatura(diasSemana[i], temp);
            somaTemperaturas += temp;
        }

        // Cálculo da média
        double media = somaTemperaturas / semana.length;

        // Identificação do dia mais quente e mais frio
        DiaTemperatura diaQuente = semana[0];
        DiaTemperatura diaFrio = semana[0];

        for (DiaTemperatura dia : semana) {
            if (dia.getTemperatura() > diaQuente.getTemperatura()) {
                diaQuente = dia;
            }
            if (dia.getTemperatura() < diaFrio.getTemperatura()) {
                diaFrio = dia;
            }
        }

        // Exibição dos resultados
        System.out.println("\n═══════════ ANÁLISE DAS TEMPERATURAS ═══════════");
        System.out.printf("Temperatura média da semana: %.2f°C\n", media);

        System.out.println("\n--- Temperaturas da Semana ---");
        for (DiaTemperatura dia : semana) {
            System.out.printf("%s: %.2f°C\n", dia.getDiaSemana(), dia.getTemperatura());
        }

        System.out.println("\n--- Extremos da Semana ---");
        System.out.printf("🔥 Dia mais quente: %s com %.2f°C\n",
                diaQuente.getDiaSemana(), diaQuente.getTemperatura());
        System.out.printf("❄️  Dia mais frio: %s com %.2f°C\n",
                diaFrio.getDiaSemana(), diaFrio.getTemperatura());

        System.out.printf("\nAmplitude térmica: %.2f°C\n",
                diaQuente.getTemperatura() - diaFrio.getTemperatura());
    }

    // ========== EXERCÍCIO 4 ==========
    public static void exercicio4() {
        System.out.println("--------- EXERCÍCIO 4 - VENDAS MENSAIS ------------------");

        RegistroVenda[] vendas = new RegistroVenda[30];
        double totalVendas = 0;

        for (int i = 0; i < vendas.length; i++) {
            int dia = i + 1;
            System.out.print("Dia " + dia + " - Valor da venda (R$): ");
            double valor = sc.nextDouble();

            vendas[i] = new RegistroVenda(dia, valor);
            totalVendas += valor;
        }

        double mediaMensal = totalVendas / vendas.length;
        int diasAcima = 0;
        int diasAbaixo = 0;

        System.out.println("\n------------------ RELATÓRIO DE VENDAS DO MÊS ------------------");
        System.out.printf("Total de vendas: R$ %.2f\n", totalVendas);
        System.out.printf("Média diária: R$ %.2f\n", mediaMensal);


        System.out.println("\n--- DIAS COM VENDAS ACIMA DA MÉDIA ---");
        for (RegistroVenda venda : vendas) {
            if (venda.getValorVenda() > mediaMensal) {
                System.out.printf("Dia %02d: R$ %.2f (+%.2f%%)\n",
                        venda.getDia(),
                        venda.getValorVenda(),
                        ((venda.getValorVenda() - mediaMensal) / mediaMensal) * 100);
                diasAcima++;
            }
        }


        System.out.println("\n--- DIAS COM VENDAS ABAIXO DA MÉDIA ---");
        for (RegistroVenda venda : vendas) {
            if (venda.getValorVenda() < mediaMensal) {
                System.out.printf("Dia %02d: R$ %.2f (-%.2f%%)\n",
                        venda.getDia(),
                        venda.getValorVenda(),
                        ((mediaMensal - venda.getValorVenda()) / mediaMensal) * 100);
                diasAbaixo++;
            }
        }


        int diasIguais = vendas.length - diasAcima - diasAbaixo;

        RegistroVenda melhorDia = vendas[0];
        RegistroVenda piorDia = vendas[0];

        for (RegistroVenda venda : vendas) {
            if (venda.getValorVenda() > melhorDia.getValorVenda()) {
                melhorDia = venda;
            }
            if (venda.getValorVenda() < piorDia.getValorVenda()) {
                piorDia = venda;
            }
        }
        System.out.println("\n------------------ ESTATÍSTICAS DO MÊS ------------------");
        System.out.println("Dias com vendas acima da média: " + diasAcima);
        System.out.println("Dias com vendas abaixo da média: " + diasAbaixo);
        System.out.println("Dias com vendas iguais à média: " + diasIguais);

        System.out.printf("\n Melhor dia: Dia %02d com R$ %.2f\n",
                melhorDia.getDia(), melhorDia.getValorVenda());
        System.out.printf("Pior dia: Dia %02d com R$ %.2f\n",
                piorDia.getDia(), piorDia.getValorVenda());
    }
}