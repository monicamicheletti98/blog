/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author UTENTE
 */
@Entity
@Table(name = "categoria")
@NamedQueries({
    @NamedQuery(
            name = "categoria.selectAll",
            query = "SELECT c FROM CategoriaEntity c ORDER BY c.nome"
    )
})
public class CategoriaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private long ID =-1;

    @Column(table = "categoria",name = "NOME", unique = false, nullable = false)
    private String nome;
    
@Column(table = "categoria",name = "DESCRIZIONE", unique = false, nullable = false)
    private String descrizione;

@Column(table = "categoria",name = "IMMAGINE", unique = false, nullable = false)
    private String immagine;


    public CategoriaEntity() {
    }

    public CategoriaEntity(long ID, String nome, String descrizione, String immagine, boolean visibile) {
        this.ID = ID;
        this.nome = nome;
        this.descrizione = descrizione;
        this.immagine = immagine;
      
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
        int hash = 5;
        hash = 53 * hash + (int) (this.ID ^ (this.ID >>> 32));
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
        final CategoriaEntity other = (CategoriaEntity) obj;
        if (this.ID != other.ID) {
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
        return "CategoriaEntity{" + "ID=" + ID + ", nome=" + nome + ", descrizione=" + descrizione + ", immagine=" + immagine + '}';
    }




}

  