package entities;

import enums.CargoEnums;

public abstract class Funcionario {
    protected String nome;
    protected String dataContratacao;

    public Funcionario() {
    }

    public Funcionario(String nome, String dataContratacao) {
        this.nome = nome;
        this.dataContratacao = dataContratacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(String dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public CargoEnums getCargo() {
        return CargoEnums.buscarPorClasse(this);
    }

    public int getAnosDeServico(final int mesAlvo, final int anoAlvo) {
        String[] partes = this.dataContratacao.split("/");
        int mesContratacao = Integer.parseInt(partes[0]);
        int anoContratacao = Integer.parseInt(partes[1]);

        int anos = anoAlvo - anoContratacao;

        if (mesAlvo < mesContratacao) {
            anos--;
        }

        return Math.max(0, anos);
    }

    public double getVendasNoMes(int mes, int ano) {
        return 0.0;
    }
}
