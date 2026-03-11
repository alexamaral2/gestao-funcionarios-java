package entities;

import java.util.Map;

public class Vendedor extends Funcionario {
    private Map<String, Integer> vendas;

    public Vendedor() {
    }

    public Vendedor(Map<String, Integer> vendas) {
        this.vendas = vendas;
    }

    public Vendedor(String nome, String dataContratacao, Map<String, Integer> vendas) {
        super(nome, dataContratacao);
        this.vendas = vendas;
    }

    public Map<String, Integer> getVendas() {
        return vendas;
    }

    public void setVendas(Map<String, Integer> vendas) {
        this.vendas = vendas;
    }

    @Override
    public double getVendasNoMes(int mes, int ano) {
        String chave = String.format("%02d/%d", mes, ano);
        return vendas.getOrDefault(chave, 0).doubleValue();
    }
}
