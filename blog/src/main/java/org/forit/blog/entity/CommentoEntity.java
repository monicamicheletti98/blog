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
@Table(name = "commento")
@NamedQueries({
    @NamedQuery(
            name = "commento.selectAll",
            query = "SELECT c FROM CommentoEntity c ORDER BY c.nickname"
    )
})
public class CommentoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private long ID ;

    @Column(table = "commento", name = "nickname", unique = false, nullable = false)
    private String nickname;

    @Column(table = "commento", name = "email", unique = false, nullable = false)
    private String email;

    @Column(table = "commento", name = "data_inserimento", unique = false, nullable = false)
    private LocalDate dataInserimento;

    @Column(table = "commento", name = "testo", unique = false, nullable = false)
    private String testo;

    @Column(table = "commento", name = "data_risposta", unique = false, nullable = true)
    private LocalDate dataRisposta;

    @Column(table = "commento", name = "risposta", unique = false, nullable = true)
    private String risposta;

    @Column(table = "commento", name = "visibile", unique = false, nullable = false)
    private boolean visibile;

    @Column(table = "commento", name = "id_post", unique = false, nullable = false)
    private long idPost;

    public CommentoEntity() {
        
    }

    @Override
    public String toString() {
        return "CommentoEntity{" + "ID=" + ID + ", nickname=" + nickname + ", email=" + email + ", dataInserimento=" + dataInserimento + ", testo=" + testo + ", dataRisposta=" + dataRisposta + ", risposta=" + risposta + ", visibile=" + visibile + ", idPost=" + idPost + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (int) (this.ID ^ (this.ID >>> 32));
        hash = 13 * hash + Objects.hashCode(this.nickname);
        hash = 13 * hash + Objects.hashCode(this.email);
        hash = 13 * hash + Objects.hashCode(this.dataInserimento);
        hash = 13 * hash + Objects.hashCode(this.testo);
        hash = 13 * hash + Objects.hashCode(this.dataRisposta);
        hash = 13 * hash + Objects.hashCode(this.risposta);
        hash = 13 * hash + (this.visibile ? 1 : 0);
        hash = 13 * hash + (int) (this.idPost ^ (this.idPost >>> 32));
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
        final CommentoEntity other = (CommentoEntity) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.visibile != other.visibile) {
            return false;
        }
        if (this.idPost != other.idPost) {
            return false;
        }
        if (!Objects.equals(this.nickname, other.nickname)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.testo, other.testo)) {
            return false;
        }
        if (!Objects.equals(this.risposta, other.risposta)) {
            return false;
        }
        if (!Objects.equals(this.dataInserimento, other.dataInserimento)) {
            return false;
        }
        if (!Objects.equals(this.dataRisposta, other.dataRisposta)) {
            return false;
        }
        return true;
    }

    public CommentoEntity(long ID, String nickname, String email, LocalDate dataInserimento, String testo, LocalDate dataRisposta, String risposta, boolean visibile, long idPost) {
        this.ID = ID;
        this.nickname = nickname;
        this.email = email;
        this.dataInserimento = dataInserimento;
        this.testo = testo;
        this.dataRisposta = dataRisposta;
        this.risposta = risposta;
        this.visibile = visibile;
        this.idPost = idPost;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(LocalDate dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public LocalDate getDataRisposta() {
        return dataRisposta;
    }

    public void setDataRisposta(LocalDate dataRisposta) {
        this.dataRisposta = dataRisposta;
    }

    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    public boolean isVisibile() {
        return visibile;
    }

    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }

    public long getIdPost() {
        return idPost;
    }

    public void setIdPost(long idPost) {
        this.idPost = idPost;
    }

    

}
