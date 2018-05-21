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
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import static org.forit.blog.dao.CategoriaDAO.DB_URL;
import org.forit.blog.dto.CategoriaDTO;
import org.forit.blog.entity.CategoriaEntity;
import org.forit.blog.exception.BlogException;


/**
 *
 * @author UTENTE
 */
public class CategoriaDAO {
    
 
    public final static String DB_URL = "jdbc:mysql://localhost:3306/blog?useSSL=false&user=forit&password=12345";
      public static final String INSERT_CATEGORIA = "INSERT INTO CATEGORIA (NOME,DESCRIZIONE, IMMAGINE)  values (?, ?, ?)";
  public static final String UPDATE_CATEGORIA
            = "UPDATE CATEGORIA "
            + "SET NOME = ?, DESCRIZIONE=?, IMMAGINE=? "
            + "WHERE ID = ?";

     public List<CategoriaDTO> getCategoriasList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        TypedQuery<CategoriaEntity> query = em.createNamedQuery("categoria.selectAll", CategoriaEntity.class);
        List<CategoriaEntity> list = query.getResultList();
        List<CategoriaDTO> categorias = list.stream().map(entity -> {
            CategoriaDTO categoria = new CategoriaDTO(entity.getID(), entity.getNome(), entity.getDescrizione(), entity.getImmagine());
            return categoria;
        }).collect(Collectors.toList());
        em.close();
        emf.close();

        return categorias;
    }

    public CategoriaDTO getCategoria(long ID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        CategoriaEntity entity = em.find(CategoriaEntity.class, ID);
        CategoriaDTO categoria = new CategoriaDTO(entity.getID(), entity.getNome(), entity.getDescrizione(), entity.getImmagine());

        em.close();
        emf.close();
        return categoria;
    }

    public void insertCategoria(long ID, String nome, String descrizione, String immagine) throws BlogException {

        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(INSERT_CATEGORIA)) {

            ps.setString(1, nome);
            ps.setString(2, descrizione);
            ps.setString(3, immagine);
          
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new BlogException(ex);
        }
    }

    public void updateCategoria(long ID, String nome, String descrizione, String immagine) throws BlogException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(UPDATE_CATEGORIA);) {

            ps.setString(1, nome);
            ps.setString(2, descrizione);
            ps.setString(3, immagine);
            ps.setLong(4, ID);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new BlogException(ex);
        }
    }

    public void deleteCategoria(long ID) throws BlogException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            CategoriaEntity categoria = em.find(CategoriaEntity.class, ID);
            em.remove(categoria);

            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new BlogException(ex);
        } finally {
            em.close();
            emf.close();
        }
    }
}

