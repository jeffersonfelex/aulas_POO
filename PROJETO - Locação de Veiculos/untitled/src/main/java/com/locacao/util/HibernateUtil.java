package com.locacao.util;

import java.util.Properties;

import com.locacao.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.locacao.model.*;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                try {
                    Properties settings = new Properties();
                    settings.load(HibernateUtil.class.getClassLoader().getResourceAsStream("hibernate.properties"));
                    configuration.setProperties(settings);
                } catch (Exception e) {
                    System.err.println("Aviso: Não foi possível carregar hibernate.properties manualmente. Tentando padrão do Hibernate.");
                }

                configuration.addAnnotatedClass(Categoria.class);
                configuration.addAnnotatedClass(Cliente.class);
                configuration.addAnnotatedClass(Contato.class);
                configuration.addAnnotatedClass(ContratoLocacao.class);
                configuration.addAnnotatedClass(Endereco.class);
                configuration.addAnnotatedClass(Locacao.class);
                configuration.addAnnotatedClass(Manutencao.class);
                configuration.addAnnotatedClass(Marca.class);
                configuration.addAnnotatedClass(Modelo.class);
                configuration.addAnnotatedClass(Ocorrencia.class);
                configuration.addAnnotatedClass(Pagamento.class);
                configuration.addAnnotatedClass(Usuario.class);
                configuration.addAnnotatedClass(Veiculo.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao iniciar a configuração do Hibernate: " + e.getMessage());
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}