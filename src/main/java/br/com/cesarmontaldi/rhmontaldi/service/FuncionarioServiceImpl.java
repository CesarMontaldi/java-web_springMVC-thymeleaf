package br.com.cesarmontaldi.rhmontaldi.service;

import br.com.cesarmontaldi.rhmontaldi.dao.FuncionarioDao;
import br.com.cesarmontaldi.rhmontaldi.domain.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioDao funcionarioDao;


    @Override @Transactional(readOnly = false)
    public void salvar(Funcionario funcionario) {
        funcionarioDao.save(funcionario);
    }


    @Override @Transactional(readOnly = false)
    public void editar(Funcionario funcionario) {
        funcionarioDao.update(funcionario);
    }


    @Override @Transactional(readOnly = false)
    public void excluir(Long id) {
        funcionarioDao.delete(id);
    }

    @Override
    public Funcionario buscarPorId(Long id) {

        return funcionarioDao.findById(id);
    }

    @Override
    public List<Funcionario> buscarTodos() {

        return funcionarioDao.findAll();
    }

    @Override
    public List<Funcionario> buscarPorNome(String nome) {
        return funcionarioDao.findByNome(nome);
    }

    @Override
    public List<Funcionario> buscarPorCargo(Long id) {
        return funcionarioDao.findByCargoId(id);
    }

    @Override
    public List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida) {
        if (entrada != null && saida != null) {
            return funcionarioDao.findByDataEntradaDataSaida(entrada, saida);
        } else if (entrada != null) {
            return funcionarioDao.findByDataEntrada(entrada);
        } else if (saida != null) {
            return funcionarioDao.findByDataSaida(saida);
        }else {
            return new ArrayList<>();
        }
    }
}
