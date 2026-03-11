package repositories;

import entities.Funcionario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncionarioRepository {

    private final List<Funcionario> funcionarios = new ArrayList<>();

    public void save(final Funcionario funcionario) {
        if (funcionario != null) {
            this.funcionarios.add(funcionario);
        }
    }

    public Optional<Funcionario> getById(final String nome) {
        return funcionarios.stream()
                .filter(f -> f.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public List<Funcionario> listAll() {
        return new ArrayList<>(this.funcionarios);
    }

    public boolean delete(final String nome) {
        return funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
    }
}
