/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author UTENTE
 */
@Entity
@Table(name = "post")
@NamedQueries({
    @NamedQuery(
            name = "post.selectAll",
            query = "SELECT p FROM PostEntity p ORDER BY p.titolo"
    )
})
public class PostEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(table = "post",name = "ID", unique = true, nullable = false)
    private long ID=-1 ;

   
    @Column(table = "post", name = "id_categoria", unique = false, nullable = false)
    private long IdCategoria;
   
     @Column(table = "post",name = "titolo", unique = false, nullable = false)
    private String titolo;
      
    @Column(table = "post",name = "descrizione", unique = false, nullable = false)
    private String descrizione;
    
    @Column(table = "post", name = "data", unique = false, nullable = false)
    private LocalDate data;
    
     @Column(table = "post", name = "visibile", unique = false, nullable = false)
    private boolean visibile;
     
      @Column(table = "post", name = "visite", unique = false, nullable = true)
    private int visite;
      
       @Column(table = "post", name = "id_autore", unique = false, nullable = false)
    private long IdAutore;

    public PostEntity() {
    }

    public PostEntity(long ID, long IdCategoria, String titolo, String descrizione, LocalDate data, boolean visibile, int visite, long IdAutore) {
        this.ID = ID;
        this.IdCategoria = IdCategoria;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.visibile = visibile;
        this.visite = visite;
        this.IdAutore = IdAutore;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

    

    

}