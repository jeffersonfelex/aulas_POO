package funcionario;

public class Desenvolvedor extends Funcionario {
    private double salarioBase;
    private String linguagemPreferida;
    private int anosExperiencia;

    public Desenvolvedor(String nome, String matricula, double salarioBase, String linguagemPreferida, int anosExperiencia) {
        super(nome, matricula);
        this.salarioBase = salarioBase;
        this.linguagemPreferida = linguagemPreferida;
        this.anosExperiencia = anosExperiencia;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (salarioBase * 0.1 * anosExperiencia);
    }

    @Override
    public void realizarTarefa() {
        System.out.println("Desenvolvedor " + nome + " está codificando em " + linguagemPreferida + ".");
    }
}

