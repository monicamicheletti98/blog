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
import org.forit.blog.dto.UserDTO;
import org.forit.blog.entity.UserEntity;
import org.forit.blog.exception.BlogException;

public class UserDAO {

    public final static String DB_URL = "jdbc:mysql://localhost:3306/blog?useSSL=false&user=forit&password=12345";
    public static final String INSERT_USER = "INSERT INTO account (NOME, COGNOME, USERNAME, EMAIL, PASSWORD)  values (?, ?, ?, ?, ?)";
    public static final String UPDATE_USER
            = "UPDATE ACCOUNT "
            + "SET NOME = ?, COGNOME = ?, EMAIL = ?, USERNAME = ?, PASSWORD = ?, DATA_REGISTRAZIONE = ?, DATA_ULTIMO_ACCESSO = ?, ATTIVO = ?, NUMERO_TENTATIVI = ?, BLOCCATO = ?, ID_RUOLO = ?  "
            + "WHERE ID = ?";

    public List<UserDTO> getUsersList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        TypedQuery<UserEntity> query = em.createNamedQuery("user.selectAll", UserEntity.class);
        List<UserEntity> list = query.getResultList();
        List<UserDTO> users = list.stream().map(entity -> {
            UserDTO user = new UserDTO(entity.getID(), entity.getNome(), entity.getCognome(), entity.getUsername(), entity.getEmail(),
                    entity.getPassword(), entity.getDataRegistrazione(), entity.getDataUltimoAccesso(),
                    entity.isAttivo(), entity.getTentativi(), entity.isBloccato(), entity.getIdRuolo());
            return user;
        }).collect(Collectors.toList());
        em.close();
        emf.close();

        return users;
    }

    public UserDTO getUser(long ID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        UserEntity entity = em.find(UserEntity.class, ID);
        UserDTO user = new UserDTO(entity.getID(), entity.getNome(), entity.getCognome(), entity.getUsername(), entity.getEmail(), entity.getPassword(),
                entity.getDataRegistrazione(), entity.getDataUltimoAccesso(), entity.isAttivo(),
                entity.getTentativi(), entity.isBloccato(), entity.getIdRuolo());

        em.close();
        emf.close();
        return user;
    }

    public void insertUser(long ID, String nome, String cognome, String username, String email, String password, LocalDate dataRegistrazione, LocalDate dataUltimoAccesso, boolean attivo, int tentativi, boolean bloccato, int idRuolo) throws BlogException {

        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(INSERT_USER)) {

            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.setString(3, username);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new BlogException(ex);
        }
    }

    public void updateUser(long ID, String nome, String cognome, String username, String email, String password, LocalDate dataRegistrazione, LocalDate dataUltimoAccesso, boolean attivo, int tentativi, boolean bloccato, int idRuolo) throws BlogException {
        try (
                Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement ps = conn.prepareStatement(UPDATE_USER);) {

            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.setString(3, username);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.setDate(6, Date.valueOf(dataRegistrazione));
            ps.setDate(7, Date.valueOf(dataUltimoAccesso));
            ps.setBoolean(8, attivo);
            ps.setInt(9, tentativi);
            ps.setBoolean(10, bloccato);
            ps.setLong(11, idRuolo);
            ps.setLong(12, ID);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new BlogException(ex);
        }
    }

    public void deleteUser(long ID) throws BlogException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blog_pu");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            UserEntity user = em.find(UserEntity.class, ID);
            em.remove(user);

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
