package services;

public class VendedorStrategy implements CargoStrategy {

    private static final double SALARIO_BASE = 12000.00;
    private static final double BONUS_POR_ANO = 1800.00;
    private static final double PERCENTUAL_BENEFICIO = 0.30;

    @Override
    public double calcularSalario(int anosDeServico) {
        return SALARIO_BASE + (BONUS_POR_ANO * anosDeServico);
    }

    @Override
    public double calcularBeneficio(int anosDeServico, double valorVendido) {
        return valorVendido * PERCENTUAL_BENEFICIO;
    }
}