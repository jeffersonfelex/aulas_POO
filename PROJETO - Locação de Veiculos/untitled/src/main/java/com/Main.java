package com;

import com.locacao.model.*;
import com.locacao.model.*;
import com.locacao.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE LOCAÇÃO DE VEÍCULOS ===\n");

        try {
            System.out.println("1. Inserindo Marca com Modelos...");
            inserirMarcaComModelos();

            System.out.println("\n2. Inserindo Veículo...");
            inserirVeiculo();

            System.out.println("\n3. Criando Locação com Ocorrências...");
            criarLocacaoComOcorrencias();

            System.out.println("\n4. Consultando Locações do Cliente...");
            consultarLocacoesCliente();

            System.out.println("\n5. Atualizando status do Veículo...");
            atualizarStatusVeiculo();

            System.out.println("\n6. Excluindo Ocorrência...");
            excluirOcorrencia();

            System.out.println("\n=== OPERAÇÕES CONCLUÍDAS COM SUCESSO ===");

        } catch (Exception e) {
            System.err.println("Erro durante execução: " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }

    private static void inserirMarcaComModelos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Categoria categoria = new Categoria(
                    "Sedan",
                    "Veículos de passeio confortáveis",
                    new BigDecimal("150.00")
            );
            session.save(categoria);

            Marca marca = new Marca("Toyota");

            Modelo modelo1 = new Modelo("Corolla", 2023);
            modelo1.setCategoria(categoria);
            marca.adicionarModelo(modelo1);

            Modelo modelo2 = new Modelo("Camry", 2023);
            modelo2.setCategoria(categoria);
            marca.adicionarModelo(modelo2);

            session.save(marca);

            transaction.commit();
            System.out.println("✓ Marca Toyota criada com 2 modelos!");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private static void inserirVeiculo() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query<Modelo> query = session.createQuery(
                    "FROM Modelo WHERE nome = :nome", Modelo.class
            );
            query.setParameter("nome", "Corolla");
            Modelo modelo = query.uniqueResult();

            if (modelo != null) {
                Veiculo veiculo = new Veiculo("ABC1234", "Prata", 2023);
                veiculo.setQuilometragem(0);
                veiculo.setStatus(StatusVeiculo.DISPONIVEL);
                veiculo.setModelo(modelo);

                session.save(veiculo);
                transaction.commit();

                System.out.println("✓ Veículo Toyota Corolla 2023 cadastrado!");
                System.out.println("  Placa: " + veiculo.getPlaca());
                System.out.println("  Status: " + veiculo.getStatus());
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private static void criarLocacaoComOcorrencias() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Cliente cliente = new Cliente(
                    "João Silva",
                    "12345678900",
                    "joao@email.com"
            );
            cliente.setCnh("12345678901");

            Endereco endereco = new Endereco(
                    "Rua das Flores",
                    "123",
                    "Goiânia",
                    "GO",
                    "74000-000"
            );
            cliente.setEndereco(endereco);
            session.save(cliente);

            Usuario funcionario = new Usuario(
                    "Maria Atendente",
                    "maria@locadora.com",
                    "senha123"
            );
            funcionario.setTelefone("62999999999");
            session.save(funcionario);

            Query<Veiculo> queryVeiculo = session.createQuery(
                    "FROM Veiculo WHERE status = :status", Veiculo.class
            );
            queryVeiculo.setParameter("status", StatusVeiculo.DISPONIVEL);
            queryVeiculo.setMaxResults(1);
            Veiculo veiculo = queryVeiculo.uniqueResult();

            if (veiculo != null) {
                ContratoLocacao contrato = new ContratoLocacao(
                        LocalDate.now(),
                        cliente
                );
                contrato.setFuncionario(funcionario);

                Locacao locacao = new Locacao(
                        LocalDate.now(),
                        LocalDate.now().plusDays(7)
                );
                locacao.setVeiculo(veiculo);
                locacao.setKmInicial(veiculo.getQuilometragem());
                locacao.setValorTotal(new BigDecimal("1050.00"));
                locacao.setStatus(StatusLocacao.ATIVA);

                Ocorrencia ocorrencia1 = new Ocorrencia(
                        LocalDateTime.now(),
                        "Veículo retirado em perfeito estado",
                        TipoOcorrencia.OBSERVACAO
                );
                locacao.adicionarOcorrencia(ocorrencia1);

                Ocorrencia ocorrencia2 = new Ocorrencia(
                        LocalDateTime.now().plusDays(1),
                        "Cliente solicitou extensão de prazo",
                        TipoOcorrencia.OBSERVACAO
                );
                locacao.adicionarOcorrencia(ocorrencia2);

                contrato.adicionarLocacao(locacao);
                contrato.setValorTotal(locacao.getValorTotal());

                veiculo.setStatus(StatusVeiculo.LOCADO);

                session.save(contrato);
                session.update(veiculo);

                transaction.commit();

                System.out.println("✓ Locação criada com sucesso!");
                System.out.println("  Cliente: " + cliente.getNome());
                System.out.println("  Veículo: " + veiculo.getModelo().getMarca().getNome() +
                        " " + veiculo.getModelo().getNome());
                System.out.println("  Período: " + locacao.getDataRetirada() +
                        " até " + locacao.getDataDevolucaoPrevista());
                System.out.println("  Ocorrências registradas: " + locacao.getOcorrencias().size());
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private static void consultarLocacoesCliente() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            String hql = "SELECT l FROM Locacao l " +
                    "JOIN l.contrato c " +
                    "WHERE c.cliente.nome = :nomeCliente";

            Query<Locacao> query = session.createQuery(hql, Locacao.class);
            query.setParameter("nomeCliente", "João Silva");

            List<Locacao> locacoes = query.getResultList();

            System.out.println("✓ Locações encontradas: " + locacoes.size());

            for (Locacao loc : locacoes) {
                System.out.println("\n  ► Locação #" + loc.getId());
                System.out.println("    Veículo: " +
                        loc.getVeiculo().getModelo().getMarca().getNome() + " " +
                        loc.getVeiculo().getModelo().getNome() + " - " +
                        loc.getVeiculo().getPlaca());
                System.out.println("    Status: " + loc.getStatus());
                System.out.println("    Valor: R$ " + loc.getValorTotal());
                System.out.println("    Ocorrências: " + loc.getOcorrencias().size());
            }

        } finally {
            session.close();
        }
    }

    private static void atualizarStatusVeiculo() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query<Veiculo> query = session.createQuery(
                    "FROM Veiculo WHERE placa = :placa", Veiculo.class
            );
            query.setParameter("placa", "ABC1234");
            Veiculo veiculo = query.uniqueResult();

            if (veiculo != null) {
                StatusVeiculo statusAnterior = veiculo.getStatus();
                veiculo.setStatus(StatusVeiculo.MANUTENCAO);
                veiculo.setQuilometragem(15000);

                session.update(veiculo);
                transaction.commit();

                System.out.println("✓ Status do veículo atualizado!");
                System.out.println("  Placa: " + veiculo.getPlaca());
                System.out.println("  Status anterior: " + statusAnterior);
                System.out.println("  Novo status: " + veiculo.getStatus());
                System.out.println("  Quilometragem: " + veiculo.getQuilometragem() + " km");
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private static void excluirOcorrencia() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query<Ocorrencia> query = session.createQuery(
                    "FROM Ocorrencia WHERE tipo = :tipo", Ocorrencia.class
            );
            query.setParameter("tipo", TipoOcorrencia.OBSERVACAO);
            query.setMaxResults(1);

            Ocorrencia ocorrencia = query.uniqueResult();

            if (ocorrencia != null) {
                Long id = ocorrencia.getId();
                String descricao = ocorrencia.getDescricao();

                session.delete(ocorrencia);
                transaction.commit();

                System.out.println("✓ Ocorrência excluída!");
                System.out.println("  ID: " + id);
                System.out.println("  Descrição: " + descricao);
            } else {
                System.out.println("  Nenhuma ocorrência encontrada para exclusão.");
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}