package funcionario;

public class Gerente extends Funcionario {
    private double salarioBase;
    private double bonus;

    public Gerente(String nome, String matricula, double salarioBase, double bonus) {
        super(nome, matricula);
        this.salarioBase = salarioBase;
        this.bonus = bonus;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + bonus;
    }

    @Override
    public void realizarTarefa() {
        System.out.println("Gerente " + nome + " está gerenciando a equipe.");
    }
}
