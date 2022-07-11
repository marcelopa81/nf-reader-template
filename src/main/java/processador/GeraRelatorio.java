package processador;

import dao.RelatorioDao;
import model.Relatorio;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class GeraRelatorio{
    private final EntityManager entityManager = JPAUtil.getEntityManager();

    public void geraRelatorio(List<Relatorio> relatorios) {
    RelatorioDao relatorioDao = new RelatorioDao(entityManager);
    entityManager.getTransaction().begin();
    relatorios.forEach(relatorio -> {
        relatorioDao.cadastrar(relatorio);
    });
    entityManager.getTransaction().commit();
    entityManager.close();
    }
}

