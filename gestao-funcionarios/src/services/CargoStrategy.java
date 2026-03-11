package services;

public interface CargoStrategy {
    double calcularSalario(int anosDeServico);
    double calcularBeneficio(int anosDeServico, double valorVendido);
}
