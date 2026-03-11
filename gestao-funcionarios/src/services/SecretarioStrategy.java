package services;

public class SecretarioStrategy implements CargoStrategy {

    private static final double SALARIO_BASE = 7000.00;
    private static final double VALOR_POR_ANO_SERVICO = 1000.00;
    private static final double PERCENTUAL_BENEFICIO = 0.20;

    @Override
    public double calcularSalario(int anosDeServico) {
        return SALARIO_BASE + (VALOR_POR_ANO_SERVICO * anosDeServico);
    }

    @Override
    public double calcularBeneficio(int anosDeServico, double valorVendido) {
        double salarioAtual = calcularSalario(anosDeServico);
        return salarioAtual * PERCENTUAL_BENEFICIO;
    }
}