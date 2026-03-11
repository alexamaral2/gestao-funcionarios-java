package application;

import entities.*;
import repositories.FuncionarioRepository;
import services.FolhaPagamentoService;
import services.FuncionarioService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void initialize() {

        FuncionarioRepository repository = new FuncionarioRepository();
        FuncionarioService funcionarioService = new FuncionarioService(repository);
        FolhaPagamentoService folhaService = new FolhaPagamentoService();

        Map<String, Integer> vendasAna = new HashMap<>();
        vendasAna.put("12/2021", 5200);
        vendasAna.put("01/2022", 4000);
        vendasAna.put("02/2022", 4200);
        vendasAna.put("03/2022", 5850);
        vendasAna.put("04/2022", 7000);

        Map<String, Integer> vendasJoao = new HashMap<>();
        vendasJoao.put("12/2021", 3400);
        vendasJoao.put("01/2022", 7700);
        vendasJoao.put("02/2022", 5000);
        vendasJoao.put("03/2022", 5900);
        vendasJoao.put("04/2022", 6500);

        funcionarioService.salvar(new Secretario("Jorge Carvalho", "01/2018"));
        funcionarioService.salvar(new Secretario("Maria Souza", "12/2015"));
        funcionarioService.salvar(new Vendedor("Ana Silva", "12/2021", vendasAna));
        funcionarioService.salvar(new Vendedor("João Mendes", "12/2021", vendasJoao));
        funcionarioService.salvar(new Gerente("Juliana Alves", "07/2017"));
        funcionarioService.salvar(new Gerente("Bento Albino", "03/2014"));

        int mes = 3;
        int ano = 2022;
        List<Funcionario> listaFuncionarios = funcionarioService.listarTodos();

        System.out.println("### RELATÓRIO MENSAL - " + mes + "/" + ano + " ###");
        System.out.println("----------------------------------------------");

        System.out.printf("1. Valor total pago (Salário + Benefício): R$ %.2f%n",
                folhaService.calcularTotalPagoNoMes(listaFuncionarios, mes, ano));

        System.out.printf("2. Total pago somente em salários: R$ %.2f%n",
                folhaService.calcularTotalSalariosNoMes(listaFuncionarios, mes, ano));

        System.out.printf("3. Total pago em benefícios: R$ %.2f%n",
                folhaService.calcularTotalBeneficiosNoMes(listaFuncionarios, mes, ano));

        Funcionario maisBemPago = folhaService.getFuncionarioComMaiorRecebimento(listaFuncionarios, mes, ano);
        System.out.println("4. Funcionário com maior recebimento: " +
                (maisBemPago != null ? maisBemPago.getNome() : "Nenhum"));

        Funcionario maiorBeneficio = folhaService.getFuncionarioComMaiorBeneficioNoMes(listaFuncionarios, mes, ano);
        System.out.println("5. Nome de quem recebeu maior benefício: " +
                (maiorBeneficio != null ? maiorBeneficio.getNome() : "Nenhum"));

        Vendedor melhorVendedor = folhaService.getVendedorQueMaisVendeu(listaFuncionarios, mes, ano);
        System.out.println("6. Nome do vendedor que mais vendeu: " +
                (melhorVendedor != null ? melhorVendedor.getNome() : "Nenhum"));

        System.out.println("----------------------------------------------");
    }
}