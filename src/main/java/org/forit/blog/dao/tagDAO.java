/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.forit.blog.entity.TagEntity;
import org.forit.blog.exception.TagException;


/**
 *
 * @author UTENTE
 */
public class tagDAO {
    
 public final static String DB_URL = "jdbc:mysql://localhost:3306/blog?useSSL=false&user=forit&password=12345";
  public static final String UPDATE_TAG
            = "UPDATE TAG"
            + "SET NOME = ? "
            + "WHERE ID = ?";

     public Map<Long, String> getListaTag() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        TypedQuery<TagEntity> query = em.createNamedQuery("tag.selectAll",TagEntity.class);
        List<TagEntity> list = query.getResultList();
        Map<Long, String> map = list.stream().
                collect(Collectors.toMap(
                        nazione -> nazione.getID(),
                        nazione -> nazione.getNome(),
                        (u, v) -> u,
                        LinkedHashMap::new));

        em.close();
        emf.close();

        return map;
    }

    public String getTag(long ID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        TagEntity tag = em.find(TagEntity.class, ID);
        String nome = tag.getNome();

        em.close();
        emf.close();

        return nome;
    }

    public void insertTag(String nome) throws TagException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            TagEntity tag = new TagEntity();
            tag.setNome(nome);
            em.persist(tag);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new TagException(ex);
        } finally {
            em.close();
            emf.close();
        }
    }

    public void updateTag(long id, String nome) throws TagException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(UPDATE_TAG);) {

            ps.setString(1, nome);
            
            ps.setLong(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new TagException(ex);
        }
    }
    public void deleteTag(long ID) throws TagException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            TagEntity tag = em.find(TagEntity.class, ID);
            em.remove(tag);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new TagException(ex);
        } finally {
            em.close();
            emf.close();
        }
    }
}
