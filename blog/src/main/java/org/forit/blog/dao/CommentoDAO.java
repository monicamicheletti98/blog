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
import org.forit.blog.dto.CommentoDTO;
import org.forit.blog.entity.CommentoEntity;
import org.forit.blog.exception.BlogException;

public class CommentoDAO {

    public final static String DB_URL = "jdbc:mysql://localhost:3306/blog?useSSL=false&user=forit&password=12345";
    public static final String INSERT_COMMENTO = "INSERT INTO commento (NICKNAME, EMAIL, DATA_INSERIMENTO, TESTO, VISIBILE, ID_POST)  values (?, ?, ?, ?, ?,?)";
    public static final String UPDATE_COMMENTO
            = "UPDATE ACCOUNT "
            + "SET NICKNAME = ?, EMAIL = ?, DATA_INSERIMENTO = ?, TESTO = ?, DATA_RISPOSTA = ?, RISPOSTA = ?,VISIBILE = ?, ID_POST = ?  "
            + "WHERE ID = ?";

    public List<CommentoDTO> getCommentosList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        TypedQuery<CommentoEntity> query = em.createNamedQuery("commento.selectAll", CommentoEntity.class);
        List<CommentoEntity> list = query.getResultList();
        List<CommentoDTO> commentos = list.stream().map(entity -> {
            CommentoDTO commento = new CommentoDTO(entity.getID(), entity.getNickname(), entity.getEmail(), entity.getDataInserimento(), entity.getTesto(),
                    entity.getDataRisposta(), entity.getRisposta(), entity.isVisibile(),
                    entity.getIdPost());
            return commento;
        }).collect(Collectors.toList());
        em.close();
        emf.close();

        return commentos;
    }

    public CommentoDTO getCommento(long ID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        CommentoEntity entity = em.find(CommentoEntity.class, ID);
        CommentoDTO commento = new CommentoDTO(entity.getID(), entity.getNickname(), entity.getEmail(), entity.getDataInserimento(), entity.getTesto(), entity.getDataRisposta(),
                entity.getRisposta(), entity.isVisibile(), entity.getIdPost()
        );

        em.close();
        emf.close();
        return commento;
    }

    public void insertCommento(long ID, String nickname, String email, LocalDate dataInserimento, String testo, LocalDate dataRisposta, String risposta, boolean visibile, long idPost) throws BlogException {

        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(INSERT_COMMENTO)) {

            ps.setString(1, nickname);
            ps.setString(2, email);
            ps.setDate(3, Date.valueOf(dataInserimento));
            ps.setString(4, testo);
            ps.setBoolean(5, visibile);
            ps.setLong(6, idPost);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new BlogException(ex);
        }
    }

    public void updateCommento(long ID, String nickname, String email, LocalDate dataInserimento, String testo, LocalDate dataRisposta, String risposta, boolean visibile, long idPost) throws BlogException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(UPDATE_COMMENTO);) {

            ps.setString(1, nickname);
            ps.setString(2, email);
            ps.setDate(3, Date.valueOf(dataInserimento));
            ps.setString(4, testo);
            ps.setDate(5, Date.valueOf(dataRisposta));
            ps.setString(6, risposta);
            ps.setBoolean(7, visibile);
            ps.setLong(8, idPost);
            ps.setLong(9, ID);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new BlogException(ex);
        }
    }

    public void deleteCommento(long ID) throws BlogException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            CommentoEntity commento = em.find(CommentoEntity.class, ID);
            em.remove(commento);

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
