package org.principal;

import org.model.*;
import java.util.*;

public class Principal {
    public static void main(String[] args) {

        // --- Criando contato
        Contato contato = new Contato(1, "joao@gmail.com", "623333-3333", "6299999-9999");
        
        // --- Criando endereço
        Endereco endereco = new Endereco(1, "74000-000", "Rua A", "Apto 101", "123", "Próximo ao shopping");
        
        // --- Criando usuário
        Usuario usuario = new Usuario(1, "João Silva", "123.456.789-00", "joao123", "senha123", contato, endereco);
        
        // --- Criando cliente
        Cliente cliente = new Cliente(1, "joazinho","CNH123456");
        
        // --- Criando funcionário
        Funcionario funcionario = new Funcionario(1, "Maria Souza", "Gerente");
        
        // --- Criando uma marca
        Marca marca = new Marca(1, "Ford", new ArrayList<>());
        
        // --- Criando um modelo
        Modelo modelo = new Modelo(1, "Ford Ka", new Date(), 4, new ArrayList<>());
        
        // Adicionando modelo à marca
        marca.getListaModelos().add(modelo);
        
        // --- Criando uma categoria
        Categoria categoria = new Categoria(1, "Econômico", 120.0f, Arrays.asList(modelo));
        
        // --- Criando um veículo
        Veiculo veiculo = new Veiculo(1, Veiculo.Status.disponivel, 50000, "ABC-1234", "9BWZZZ377VT004251", "12345678901", "Prata", modelo);
        
        // Adicionando veículo ao modelo
        modelo.getListaVeiculos().add(veiculo);
        
        // --- Criando uma manutenção
        Manutencao manutencao = new Manutencao(1, "Troca de óleo", new Date(), 150.0f);
        
        // --- Criando uma ocorrência
        Ocorrencia ocorrencia = new Ocorrencia(1, "Multa por excesso de velocidade", new Date(), veiculo, Ocorrencia.Tipo.multa);        
        // --- Criando uma locação
        Locacao locacao = new Locacao(1, new Date(), new Date(), 350.0f, Arrays.asList(ocorrencia), veiculo);
        
        // --- Criando pagamento
        Pagamento pagamento = new Pagamento(1, Pagamento.TipoPagamento.dinheiro, 350.0f);
        
        // --- Criando contrato de locação
ContratoLocacao contrato = new ContratoLocacao(1, new Date(), 500.0f, ContratoLocacao.statusContrato.ativo, Arrays.asList(locacao), funcionario, cliente);
        // --- Exibindo os dados
        System.out.println("--- SYS LOCARCAR DO FELEX ---");
        System.out.println("Cliente: " + usuario.getNome() + " | CNH: " + cliente.getCnh());
        System.out.println("Veículo: " + veiculo.getModelo().getNome() + " - " + veiculo.getPlaca());
        System.out.println("Categoria: " + categoria.getNome() + " | Valor: R$" + categoria.getValorLocacao());
        System.out.println("Marca: " + marca.getNome());
        System.out.println("Locação: " + locacao.getId() + " | Status Veículo: " + veiculo.getStatus());
        System.out.println("Pagamento: R$" + pagamento.getValorTotal() + " | Tipo: " + pagamento.getTipoPagamento());
        System.out.println("Funcionário: " + funcionario.getNome());
        System.out.println("Ocorrência: " + ocorrencia.getDescricao());
        System.out.println("Manutenção: " + manutencao.getDescricao() + " | Custo: R$" + manutencao.getCusto());
        System.out.println("Contrato: " + contrato.getId() + " | Status: " + contrato.getStatus());
        System.out.println("Valor do Caução: R$" + contrato.getValorCaucao());
    }
}