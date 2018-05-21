package org.forit.blog.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import java.util.Objects;
import org.forit.blog.serializer.LocalDateDeserializer;
import org.forit.blog.serializer.LocalDateSerializer;

public class PostDTO {

    private long id ;
    private long IdCategoria; 
    private String titolo, descrizione;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate data;

    private boolean visibile;
    private int visite;
    private long IdAutore;

    public PostDTO() {
    }

    public PostDTO(long id, long IdCategoria, String titolo, String descrizione, LocalDate data, boolean visibile, int visite, long IdAutore) {
        this.id = id;
        this.IdCategoria = IdCategoria;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.visibile = visibile;
        this.visite = visite;
        this.IdAutore = IdAutore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(long IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isVisibile() {
        return visibile;
    }

    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }

    public int getVisite() {
        return visite;
    }

    public void setVisite(int visite) {
        this.visite = visite;
    }

    public long getIdAutore() {
        return IdAutore;
    }

    public void setIdAutore(long IdAutore) {
        this.IdAutore = IdAutore;
    }

    @Override
    public String toString() {
        return "PostDTO{" + "id=" + id + ", IdCategoria=" + IdCategoria + ", titolo=" + titolo + ", descrizione=" + descrizione + ", data=" + data + ", visibile=" + visibile + ", visite=" + visite + ", IdAutore=" + IdAutore + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 43 * hash + (int) (this.IdCategoria ^ (this.IdCategoria >>> 32));
        hash = 43 * hash + Objects.hashCode(this.titolo);
        hash = 43 * hash + Objects.hashCode(this.descrizione);
        hash = 43 * hash + Objects.hashCode(this.data);
        hash = 43 * hash + (this.visibile ? 1 : 0);
        hash = 43 * hash + this.visite;
        hash = 43 * hash + (int) (this.IdAutore ^ (this.IdAutore >>> 32));
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
        final PostDTO other = (PostDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.IdCategoria != other.IdCategoria) {
            return false;
        }
        if (this.visibile != other.visibile) {
            return false;
        }
        if (this.visite != other.visite) {
            return false;
        }
        if (this.IdAutore != other.IdAutore) {
            return false;
        }
        if (!Objects.equals(this.titolo, other.titolo)) {
            return false;
        }
        if (!Objects.equals(this.descrizione, other.descrizione)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    
}