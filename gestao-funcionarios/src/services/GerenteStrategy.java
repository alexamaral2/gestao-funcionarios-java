package services;

public class GerenteStrategy implements CargoStrategy {

    private static final double SALARIO_BASE = 20000.00;
    private static final double VALOR_POR_ANO_SERVICO = 3000.00;

    @Override
    public double calcularSalario(int anosDeServico) {
        return SALARIO_BASE + (VALOR_POR_ANO_SERVICO * anosDeServico);
    }

    @Override
    public double calcularBeneficio(int anosDeServico, double valorVendido) {
        return 0.0;
    }
}
