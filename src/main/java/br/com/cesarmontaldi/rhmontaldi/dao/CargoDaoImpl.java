package br.com.cesarmontaldi.rhmontaldi.dao;

import br.com.cesarmontaldi.rhmontaldi.domain.Cargo;
import br.com.cesarmontaldi.rhmontaldi.util.PaginacaoUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {

    public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao) {
        int tamanho = 5;
        int inicio = (pagina - 1) * tamanho;
        List<Cargo> cargos = getEntityManager()
                .createQuery("SELECT c FROM Cargo c ORDER BY c.nome " + direcao, Cargo.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .getResultList();

        Long totalRegistros = count();
        Long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

        return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas,direcao,cargos);
    }

    public Long count() {
        return getEntityManager()
                .createQuery("SELECT COUNT(*) FROM Cargo", Long.class)
                .getSingleResult();
    }
}
