package org.forit.blog.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {
    private static EntityManagerFactory emf;

    public EntityManagerProvider() {
    }

    public static void init() {
        emf = Persistence.createEntityManagerFactory("blog_pu");
    }

    public static void destroy() {
        if (emf != null) {
            emf.close();
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
}
