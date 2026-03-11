package services;

import entities.Funcionario;
import entities.Vendedor;
import enums.CargoEnums;

import java.util.List;

public class FolhaPagamentoService {

    private CargoStrategy obterStrategy(Funcionario funcionario) {
        return CargoEnums.buscarPorClasse(funcionario).getService();
    }

    private double obterVendas(Funcionario funcionario, int mes, int ano) {
        if (funcionario instanceof Vendedor vendedor) {
            return vendedor.getVendasNoMes(mes, ano);
        }
        return 0.0;
    }

    private double calcularSalario(Funcionario funcionario, int mes, int ano) {
        CargoStrategy strategy = obterStrategy(funcionario);
        int anosDeServico = funcionario.getAnosDeServico(mes, ano);
        return strategy.calcularSalario(anosDeServico);
    }

    private double calcularBeneficio(Funcionario funcionario, int mes, int ano) {
        CargoStrategy strategy = obterStrategy(funcionario);
        int anosDeServico = funcionario.getAnosDeServico(mes, ano);
        double vendas = obterVendas(funcionario, mes, ano);
        return strategy.calcularBeneficio(anosDeServico, vendas);
    }

    private double calcularTotalRecebido(Funcionario funcionario, int mes, int ano) {
        return calcularSalario(funcionario, mes, ano) + calcularBeneficio(funcionario, mes, ano);
    }

    public double calcularTotalPagoNoMes(List<Funcionario> funcionarios, int mes, int ano) {
        double total = 0.0;

        for (Funcionario funcionario : funcionarios) {
            total += calcularTotalRecebido(funcionario, mes, ano);
        }

        return total;
    }

    public double calcularTotalSalariosNoMes(List<Funcionario> funcionarios, int mes, int ano) {
        double total = 0.0;

        for (Funcionario funcionario : funcionarios) {
            total += calcularSalario(funcionario, mes, ano);
        }

        return total;
    }

    public double calcularTotalBeneficiosNoMes(List<Funcionario> funcionarios, int mes, int ano) {
        double total = 0.0;

        for (Funcionario funcionario : funcionarios) {
            total += calcularBeneficio(funcionario, mes, ano);
        }

        return total;
    }

    public Funcionario getFuncionarioComMaiorRecebimento(List<Funcionario> funcionarios, int mes, int ano) {
        if (funcionarios == null || funcionarios.isEmpty()) {
            return null;
        }

        Funcionario funcionarioMaisBemPago = null;
        double maiorRecebimento = -1.0;

        for (Funcionario funcionario : funcionarios) {
            double totalRecebido = calcularTotalRecebido(funcionario, mes, ano);

            if (totalRecebido > maiorRecebimento) {
                maiorRecebimento = totalRecebido;
                funcionarioMaisBemPago = funcionario;
            }
        }

        return funcionarioMaisBemPago;
    }

    public Funcionario getFuncionarioComMaiorBeneficioNoMes(List<Funcionario> funcionarios, int mes, int ano) {
        if (funcionarios == null || funcionarios.isEmpty()) {
            return null;
        }

        Funcionario funcionarioMaiorBeneficio = null;
        double maiorBeneficio = -1.0;

        for (Funcionario funcionario : funcionarios) {
            double beneficioAtual = calcularBeneficio(funcionario, mes, ano);

            if (beneficioAtual > maiorBeneficio && beneficioAtual > 0) {
                maiorBeneficio = beneficioAtual;
                funcionarioMaiorBeneficio = funcionario;
            }
        }

        return funcionarioMaiorBeneficio;
    }

    public Vendedor getVendedorQueMaisVendeu(List<Funcionario> funcionarios, int mes, int ano) {
        if (funcionarios == null || funcionarios.isEmpty()) {
            return null;
        }

        Vendedor melhorVendedor = null;
        double maiorVenda = -1.0;

        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof Vendedor vendedor) {
                double vendaAtual = vendedor.getVendasNoMes(mes, ano);

                if (vendaAtual > maiorVenda) {
                    maiorVenda = vendaAtual;
                    melhorVendedor = vendedor;
                }
            }
        }

        return melhorVendedor;
    }
}