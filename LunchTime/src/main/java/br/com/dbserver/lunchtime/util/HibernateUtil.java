package br.com.dbserver.lunchtime.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Classe utilitária para gerenciamento do Hibernate, tem como objetivos:
 *
 * Construir a SessionFactory de acordo com o arquivo de configuração;
 * Inicializar uma única instância da SessionFactory do Hibernate.
 *
 * @author Luiz Henrique
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
            serviceRegistryBuilder.applySettings(cfg.getProperties());
            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            return cfg.buildSessionFactory(serviceRegistry);

        } catch (Throwable e) {
            System.out.println("Criação inicial do objeto Session Factory falhou. Erro: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
