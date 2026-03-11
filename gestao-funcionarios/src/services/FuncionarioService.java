package services;

import entities.Funcionario;
import repositories.FuncionarioRepository;
import java.util.List;

public class FuncionarioService {
    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public void salvar(Funcionario funcionario) {
        repository.save(funcionario);
    }

    public List<Funcionario> listarTodos() {
        return repository.listAll();
    }
}