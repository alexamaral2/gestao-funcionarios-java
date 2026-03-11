package services;

import entities.Funcionario;
import java.util.List;

public class FolhaPagamentoService {

    public double calcularTotalPagoNoMes(List<Funcionario> funcionarios, int mes, int ano) {
        double total = 0;
        for (Funcionario f : funcionarios) {
            double salario = f.getCargo().getService().calcularSalario(f.getAnosDeServico(mes, ano));
            double beneficio = f.getCargo().getService().calcularBeneficio(f.getAnosDeServico(mes, ano), f.getVendasNoMes(mes, ano));
            total += (salario + beneficio);
        }
        return total;
    }

    public double calcularTotalSalariosNoMes(List<Funcionario> funcionarios, int mes, int ano) {
        double total = 0;
        for (Funcionario f : funcionarios) {
            total += f.getCargo().getService().calcularSalario(f.getAnosDeServico(mes, ano));
        }
        return total;
    }

    public double calcularTotalBeneficiosNoMes(List<Funcionario> funcionarios, int mes, int ano) {
        double total = 0;
        for (Funcionario f : funcionarios) {
            total += f.getCargo().getService().calcularBeneficio(f.getAnosDeServico(mes, ano), f.getVendasNoMes(mes, ano));
        }
        return total;
    }

    public Funcionario getFuncionarioComMaiorRecebimento(List<Funcionario> funcionarios, int mes, int ano) {
        if (funcionarios.isEmpty()) return null;

        Funcionario maisPago = funcionarios.get(0);
        double maiorValor = 0;

        for (Funcionario f : funcionarios) {
            double totalAtual = f.getCargo().getService().calcularSalario(f.getAnosDeServico(mes, ano))
                    + f.getCargo().getService().calcularBeneficio(f.getAnosDeServico(mes, ano), f.getVendasNoMes(mes, ano));

            if (totalAtual > maiorValor) {
                maiorValor = totalAtual;
                maisPago = f;
            }
        }
        return maisPago;
    }

    public String getNomeMaiorBeneficioNoMes(List<Funcionario> funcionarios, int mes, int ano) {
        Funcionario funcionarioMaiorBeneficio = null;
        double maiorBeneficio = -1;

        for (Funcionario f : funcionarios) {
            double beneficioAtual = f.getCargo().getService().calcularBeneficio(f.getAnosDeServico(mes, ano), f.getVendasNoMes(mes, ano));

            if (beneficioAtual > maiorBeneficio) {
                maiorBeneficio = beneficioAtual;
                funcionarioMaiorBeneficio = f;
            }
        }
        return (funcionarioMaiorBeneficio != null) ? funcionarioMaiorBeneficio.getNome() : "Nenhum";
    }

    public String getVendedorQueMaisVendeu(List<Funcionario> funcionarios, int mes, int ano) {
        Funcionario melhorVendedor = null;
        double maiorVenda = -1;

        for (Funcionario f : funcionarios) {
            double vendaAtual = f.getVendasNoMes(mes, ano);

            if (vendaAtual > maiorVenda) {
                maiorVenda = vendaAtual;
                melhorVendedor = f;
            }
        }
        return (melhorVendedor != null) ? melhorVendedor.getNome() : "Nenhum";
    }
}