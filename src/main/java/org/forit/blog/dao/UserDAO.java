package org.forit.blog.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.forit.blog.dto.UserDTO;
import org.forit.blog.entity.AccountEntity;
import org.forit.blog.entity.EntityManagerProvider;
import org.forit.blog.entity.QAccountEntity;

public class UserDAO {

    public UserDTO getUser(long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        QAccountEntity qce = QAccountEntity.accountEntity;
        JPAQueryFactory query = new JPAQueryFactory(em);
        AccountEntity entity = query.selectFrom(qce).where(qce.id.eq(id)).fetchOne();
        em.close();
        return new UserDTO(entity.getId(),
                entity.getIdRuolo(),
                entity.getNumeroTentativi(),
                entity.getNome(),
                entity.getCognome(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.isAttivo(),
                entity.isBloccato(),
                entity.getDataIscrizione(),
                entity.getDataUltimoAccesso());
    }

    public List<UserDTO> getUsersList() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        QAccountEntity qce = QAccountEntity.accountEntity;
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<AccountEntity> listaEntity = query.selectFrom(qce).fetch();
        List<UserDTO> listaCategorie = listaEntity.stream().map(entity -> {
            return new UserDTO(entity.getId(),
                    entity.getIdRuolo(),
                    entity.getNumeroTentativi(),
                    entity.getNome(),
                    entity.getCognome(),
                    entity.getUsername(),
                    entity.getEmail(),
                    entity.getPassword(),
                    entity.isAttivo(),
                    entity.isBloccato(),
                    entity.getDataIscrizione(),
                    entity.getDataUltimoAccesso());
        }).collect(Collectors.toList());
        em.close();
        return listaCategorie;
    }

    public boolean insertUser(long id, UserDTO account) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        AccountEntity entity = new AccountEntity(account.getIdRuolo(),
                account.getNumeroTentativi(),
                account.getNome(),
                account.getCognome(),
                account.getUsername(),
                account.getEmail(),
                account.getPassword(),
                account.isAttivo(),
                account.isBloccato(),
                account.getDataIscrizione(),
                account.getDataUltimoAccesso());
        if(id!=(long)-1){
            entity.setId(id);
        }
        try {
            em.getTransaction().begin();
            if (id != -1) {
                em.merge(entity);
            } else {
                em.persist(entity);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("ERRORE: " + ex);
            return false;
        }
        return true;
    }

    public boolean deleteAccount(long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        QAccountEntity qce = QAccountEntity.accountEntity;
        JPAQueryFactory query = new JPAQueryFactory(em);
        AccountEntity entity = query.selectFrom(qce).where(qce.id.eq(id)).fetchOne();
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("ERRORE: " + ex);
            return false;
        }
        em.close();
        return true;
    }
}
