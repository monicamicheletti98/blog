/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.dto;

import java.util.Objects;

/**
 *
 * @author UTENTE
 */
public class CategoriaDTO {
    private long id;
    private String nome,descrizione,immagine;

    public CategoriaDTO(long id, String nome, String descrizione, String immagine) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.immagine = immagine;
    }
  
    

    public CategoriaDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

  

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.descrizione);
        hash = 53 * hash + Objects.hashCode(this.immagine);
       
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
        final CategoriaDTO other = (CategoriaDTO) obj;
        if (this.id != other.id) {
            return false;
        }
       
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descrizione, other.descrizione)) {
            return false;
        }
        if (!Objects.equals(this.immagine, other.immagine)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CategoriaDTO{" + "id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", immagine=" + immagine + '}';
    }

    
}