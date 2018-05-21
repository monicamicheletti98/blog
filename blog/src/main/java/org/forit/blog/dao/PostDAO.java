package org.forit.blog.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.forit.blog.dto.PostDTO;
import org.forit.blog.entity.PostEntity;
import org.forit.blog.exception.BlogException;

public class PostDAO {

    public final static String DB_URL = "jdbc:mysql://localhost:3306/blog?useSSL=false&user=forit&password=12345";
    public static final String INSERT_POST = "INSERT INTO post (id_categoria, titolo, descrizione, data ,id_autore, visibile)  values (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_POST
            = "UPDATE POST "
            + "SET ID_CATEGORIA = ?, TITOLO = ?, DESCRIZIONE = ?, DATA = ?, VISIBILE = ?,VISITE=?, ID_AUTORE = ?  "
            + "WHERE ID = ?";

    public List<PostDTO> getPostsList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        TypedQuery<PostEntity> query = em.createNamedQuery("post.selectAll", PostEntity.class);
        List<PostEntity> list = query.getResultList();
        List<PostDTO> posts = list.stream().map(entity -> {
            PostDTO post = new PostDTO(entity.getID(), entity.getIdCategoria(), entity.getTitolo(), entity.getDescrizione(), entity.getData(),
                    entity.isVisibile(),entity.getVisite(), entity.getIdAutore()
            );
            return post;
        }).collect(Collectors.toList());
        em.close();
        emf.close();

        return posts;
    }

    public PostDTO getPost(long ID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        PostEntity entity = em.find(PostEntity.class, ID);
        PostDTO post = new PostDTO(entity.getID(), entity.getIdCategoria(), entity.getTitolo(), entity.getDescrizione(), entity.getData(),
                entity.isVisibile(),entity.getVisite(), entity.getIdAutore()
        );

        em.close();
        emf.close();
        return post;
    }

    public void insertPost(long ID, long IdCategoria, String titolo, String descrizione, LocalDate data, boolean visibile,int visite, long IdAutore) throws BlogException {

        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(INSERT_POST)) {

            ps.setLong(1, IdCategoria);
            ps.setString(2, titolo);
            ps.setString(3, descrizione);
            ps.setDate(4, Date.valueOf(data));
            ps.setLong(5, IdAutore);
            ps.setBoolean(6, visibile);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new BlogException(ex);
        }
    }

    public void updatePost(long ID, long IdCategoria, String titolo, String descrizione, LocalDate data, boolean visibile,int visite, long IdAutore) throws BlogException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(UPDATE_POST);) {
            
            ps.setLong(1, ID);
            ps.setLong(2, IdCategoria);
            ps.setString(3, titolo);
            ps.setString(4, descrizione);
            ps.setDate(4, Date.valueOf(data));
            ps.setBoolean(5, visibile);
            ps.setInt(6, visite);
            ps.setLong(7, IdAutore);

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new BlogException(ex);
        }
    }

    public void deletePost(long ID) throws BlogException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            PostEntity post = em.find(PostEntity.class, ID);
            em.remove(post);

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
