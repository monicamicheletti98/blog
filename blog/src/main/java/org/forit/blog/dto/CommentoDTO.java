package org.forit.blog.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import java.util.Objects;
import org.forit.blog.serializer.LocalDateDeserializer;
import org.forit.blog.serializer.LocalDateSerializer;

public class CommentoDTO {

    private long id;
    private String nickname, email;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataInserimento;

    private String testo;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataRisposta;
    private String risposta;

    private boolean visibile;
    private long id_post;

    public CommentoDTO() {
    }

    public CommentoDTO(long id, String nickname, String email, LocalDate dataInserimento, String testo, LocalDate dataRisposta, String risposta, boolean visibile, long id_post) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.dataInserimento = dataInserimento;
        this.testo = testo;
        this.dataRisposta = dataRisposta;
        this.risposta = risposta;
        this.visibile = visibile;
        this.id_post = id_post;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getId_post() {
        return id_post;
    }

    public void setId_post(long id_post) {
        this.id_post = id_post;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 17 * hash + Objects.hashCode(this.nickname);
        hash = 17 * hash + Objects.hashCode(this.email);
        hash = 17 * hash + Objects.hashCode(this.dataInserimento);
        hash = 17 * hash + Objects.hashCode(this.testo);
        hash = 17 * hash + Objects.hashCode(this.dataRisposta);
        hash = 17 * hash + Objects.hashCode(this.risposta);
        hash = 17 * hash + (this.visibile ? 1 : 0);
        hash = 17 * hash + (int) (this.id_post ^ (this.id_post >>> 32));
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
        final CommentoDTO other = (CommentoDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.visibile != other.visibile) {
            return false;
        }
        if (this.id_post != other.id_post) {
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

   

    @Override
    public String toString() {
        return "CommentoDTO{" + "id=" + id + ", nickname=" + nickname + ", email=" + email + ", dataInserimento=" + dataInserimento + ", testo=" + testo + ", dataRisposta=" + dataRisposta + ", risposta=" + risposta + ", visibile=" + visibile + ", id_post=" + id_post + '}';
    }
    
}

  