package br.com.cesarmontaldi.rhmontaldi.service;

import br.com.cesarmontaldi.rhmontaldi.domain.Cargo;
import br.com.cesarmontaldi.rhmontaldi.util.PaginacaoUtil;

import java.util.List;

public interface CargoService {

    void salvar (Cargo cargo);

    void editar(Cargo cargo);

    void excluir(Long id);

    Cargo buscarPorId(Long id);

    List<Cargo> buscarTodos();

    boolean cargoTemFuncionarios(Long id);

    PaginacaoUtil<Cargo> buscaPorPagina(int pagina, String direcao);
}
