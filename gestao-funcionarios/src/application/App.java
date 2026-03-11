package application;

import entities.*;
import repositories.FuncionarioRepository;
import services.FolhaPagamentoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void initialize() {
        FuncionarioRepository repository = new FuncionarioRepository();
        FolhaPagamentoService service = new FolhaPagamentoService();

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

        repository.save(new Secretario("Jorge Carvalho", "01/2018"));
        repository.save(new Secretario("Maria Souza", "12/2015"));
        repository.save(new Vendedor("Ana Silva", "12/2021", vendasAna));
        repository.save(new Vendedor("João Mendes", "12/2021", vendasJoao));
        repository.save(new Gerente("Juliana Alves", "07/2017"));
        repository.save(new Gerente("Bento Albino", "03/2014"));

        int mes = 3;
        int ano = 2022;
        List<Funcionario> listaFuncionarios = repository.listAll();

        System.out.println("### RELATÓRIO MENSAL - " + mes + "/" + ano + " ###");
        System.out.println("----------------------------------------------");

        System.out.printf("1. Valor total pago (Salário + Benefício): R$ %.2f%n",
                service.calcularTotalPagoNoMes(listaFuncionarios, mes, ano));

        System.out.printf("2. Total pago somente em salários: R$ %.2f%n",
                service.calcularTotalSalariosNoMes(listaFuncionarios, mes, ano));

        System.out.printf("3. Total pago em benefícios: R$ %.2f%n",
                service.calcularTotalBeneficiosNoMes(listaFuncionarios, mes, ano));

        Funcionario maisBemPago = service.getFuncionarioComMaiorRecebimento(listaFuncionarios, mes, ano);
        System.out.println("4. Funcionário com maior recebimento: " +
                (maisBemPago != null ? maisBemPago.getNome() : "Nenhum"));

        System.out.println("5. Nome de quem recebeu maior benefício: " +
                service.getNomeMaiorBeneficioNoMes(listaFuncionarios, mes, ano));

        System.out.println("6. Nome do vendedor que mais vendeu: " +
                service.getVendedorQueMaisVendeu(listaFuncionarios, mes, ano));

        System.out.println("----------------------------------------------");
    }
}