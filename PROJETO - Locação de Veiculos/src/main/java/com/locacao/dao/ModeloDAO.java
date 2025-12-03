package com.locacao.dao;

import com.locacao.model.Marca;
import com.locacao.model.Modelo;
import com.locacao.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ModeloDAO {

    public List<Modelo> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT m FROM Modelo m JOIN FETCH m.marca ORDER BY m.marca.nome, m.nome",
                    Modelo.class
            ).list();
        }
    }

    public void salvarModelo(String nomeMarca, String nomeModelo, int ano) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query<Marca> query = session.createQuery("FROM Marca WHERE nome = :nome", Marca.class);
            query.setParameter("nome", nomeMarca);
            Marca marca = query.uniqueResult();

            if (marca == null) {
                marca = new Marca(nomeMarca);
                session.save(marca);
            }

            Modelo modelo = new Modelo(nomeModelo, ano);
            marca.adicionarModelo(modelo);

            session.save(modelo);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}