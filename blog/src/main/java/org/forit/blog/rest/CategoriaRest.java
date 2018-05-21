/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.rest;

import java.io.Serializable;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.forit.blog.dao.CategoriaDAO;
import org.forit.blog.dto.CategoriaDTO;
import org.forit.blog.exception.BlogException;

/**
 *
 * @author UTENTE
 */
@Path("/categoria")
public class CategoriaRest implements Serializable {



    @Path("/")
    @GET()
    @Produces("application/json")
    public List<CategoriaDTO> getCategorias() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO.getCategoriasList();
    }

    @Path("/{id}")
    @GET()
    @Produces("application/json")
    public CategoriaDTO getCategoria(@PathParam("id") long ID) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO.getCategoria(ID);
    }

    @Path("/")
    @POST()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean postCategoria(CategoriaDTO categoria) {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categoriaDAO.insertCategoria(categoria.getId(), categoria.getNome(), categoria.getDescrizione(), categoria.getImmagine());
            return true;
        } catch (BlogException ex) {
            return false;
        }
    }

    @Path("/")
    @PUT()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean putCategoria(CategoriaDTO categoria) {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categoriaDAO.updateCategoria(categoria.getId(), categoria.getNome(), categoria.getDescrizione(), categoria.getImmagine());
            return true;
        } catch (BlogException ex) {
            return false;
        }
    }

    @Path("/{id}")
    @DELETE()
    @Produces("application/json")
    public boolean deleteCategoria(@PathParam("id") long ID) throws WebApplicationException {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categoriaDAO.deleteCategoria(ID);
            return true;
        } catch (BlogException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new WebApplicationException(ex, Response.Status.CONFLICT);
        }

    }
}
