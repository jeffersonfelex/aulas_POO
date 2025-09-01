package org.principal;

import org.model.*;
import java.util.*;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) throws Exception {

        Sala sala = new Sala(1, 40); 
        List<Sala> salas = new ArrayList<>();
        salas.add(sala);

        Cinema cinema = new Cinema("Cinema do Felex", "Rua 8 Centro", salas);

        Cliente cliente = new Cliente("Gabriela", "123456789", 1030349);
        Filme filme = new Filme("A volta dos que não foram", 160, "Ficção Científica");

        Ingresso ingresso1 = new Ingresso(Ingresso.Tipo.MEIA, 12.50, cliente);
        Ingresso ingresso2 = new Ingresso(Ingresso.Tipo.INTEIRA, 19.50, cliente);
        List<Ingresso> ingressos = Arrays.asList(ingresso1, ingresso2);

        LocalDateTime horarioSessao = LocalDateTime.of(2025, 9, 1, 20, 30);
        Sessao sessao = new Sessao(horarioSessao, filme, sala,ingressos);
        sessao.setIngressosVendidos(ingressos);

        Funcionario funcionario = new Funcionario("Antonio", "10293902");

        System.out.println("\n");
        System.out.println("Sessão criada: " + sessao + "\n");
        System.out.println("Ingressos vendidos: " + ingressos + "\n");
        System.out.println("Cinema: " + cinema.getNome() + ", Local: " + cinema.getEndereco() + "\n");
        System.out.println("Funcionário: " + funcionario.getNome() + "\n");
    }
}
