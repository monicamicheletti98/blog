package org.forit.blog.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@NamedQueries({
    @NamedQuery(
            name = "user.selectAll",
            query = "SELECT u FROM UserEntity u ORDER BY u.nome"
    )
})
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private long ID = -1;

    @Column(table = "account", name = "nome", unique = false, nullable = false)
    private String nome;

    @Column(table = "account", name = "cognome", unique = false, nullable = false)
    private String cognome;

    @Column(table = "account", name = "username", unique = false, nullable = false)
    private String username;

    @Column(table = "account", name = "email", unique = true, nullable = false)
    private String email;

    @Column(table = "account", name = "password", unique = false, nullable = false)
    private String password;

    @Column(table = "account", name = "data_registrazione", unique = false, nullable = false)
    private LocalDate dataRegistrazione;

    @Column(table = "account", name = "data_ultimo_accesso", unique = false, nullable = false)
    private LocalDate dataUltimoAccesso;

    @Column(table = "account", name = "attivo", unique = false, nullable = false)
    private boolean attivo;

    @Column(table = "account", name = "numero_tentativi", unique = false, nullable = false)
    private int tentativi;

    @Column(table = "account", name = "bloccato", unique = false, nullable = false)
    private boolean bloccato;

    @Column(table = "account", name = "id_ruolo", unique = false, nullable = false)
    private long idRuolo;

    public UserEntity() {
    }

    public UserEntity(long ID, String nome, String cognome, String username, String email, String password, LocalDate dataRegistrazione, LocalDate dataUltimoAccesso, boolean attivo, int tentativi, boolean bloccato, long idRuolo) {
        this.ID = ID;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dataRegistrazione = dataRegistrazione;
        this.dataUltimoAccesso = dataUltimoAccesso;
        this.attivo = attivo;
        this.tentativi = tentativi;
        this.bloccato = bloccato;
        this.idRuolo = idRuolo;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDataRegistrazione() {
        return dataRegistrazione;
    }

    public void setDataRegistrazione(LocalDate dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }

    public LocalDate getDataUltimoAccesso() {
        return dataUltimoAccesso;
    }

    public void setDataUltimoAccesso(LocalDate dataUltimoAccesso) {
        this.dataUltimoAccesso = dataUltimoAccesso;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public int getTentativi() {
        return tentativi;
    }

    public void setTentativi(int tentativi) {
        this.tentativi = tentativi;
    }

    public boolean isBloccato() {
        return bloccato;
    }

    public void setBloccato(boolean bloccato) {
        this.bloccato = bloccato;
    }

    public long getIdRuolo() {
        return idRuolo;
    }

    public void setIdRuolo(long idRuolo) {
        this.idRuolo = idRuolo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.ID ^ (this.ID >>> 32));
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.cognome);
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Objects.hashCode(this.dataRegistrazione);
        hash = 97 * hash + Objects.hashCode(this.dataUltimoAccesso);
        hash = 97 * hash + (this.attivo ? 1 : 0);
        hash = 97 * hash + this.tentativi;
        hash = 97 * hash + (this.bloccato ? 1 : 0);
        hash = 97 * hash + (int) (this.idRuolo ^ (this.idRuolo >>> 32));
        return hash;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserEntity other = (UserEntity) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.attivo != other.attivo) {
            return false;
        }
        if (this.tentativi != other.tentativi) {
            return false;
        }
        if (this.bloccato != other.bloccato) {
            return false;
        }
        if (this.idRuolo != other.idRuolo) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cognome, other.cognome)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.dataRegistrazione, other.dataRegistrazione)) {
            return false;
        }
        if (!Objects.equals(this.dataUltimoAccesso, other.dataUltimoAccesso)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "ID=" + ID + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", email=" + email + ", password=" + password + ", dataRegistrazione=" + dataRegistrazione + ", dataUltimoAccesso=" + dataUltimoAccesso + ", attivo=" + attivo + ", tentativi=" + tentativi + ", bloccato=" + bloccato + ", idRuolo=" + idRuolo + '}';
    }

}
