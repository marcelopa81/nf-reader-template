package dao;

import model.Relatorio;

import javax.persistence.EntityManager;

public class RelatorioDao {
    private EntityManager em;

    public RelatorioDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Relatorio relatorio){
        this.em.persist(relatorio);
    }
}
