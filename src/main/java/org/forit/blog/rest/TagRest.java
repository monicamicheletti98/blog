/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.forit.blog.rest;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
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
import org.forit.blog.dao.tagDAO;
import org.forit.blog.dto.tagDTO;
import org.forit.blog.exception.TagException;

/**
 *
 * @author UTENTE
 */
@Path("/tag")
public class TagRest implements Serializable{

    @Path("/")
    @GET()
    @Produces("application/json")
    public List<tagDTO> getTag() {
        tagDAO TagDAO = new tagDAO();
        return TagDAO.getTagsList();
    }

    @Path("/{id}")
    @GET()
    @Produces("application/json")
    public String getTag(@PathParam("id") long ID) {       
        tagDAO TagDAO = new tagDAO();
        return TagDAO.getTag(ID);
    }

    @Path("/{id}")
    @DELETE()
    @Produces("application/json")
    public boolean deleteTag(@PathParam("id") long ID) throws WebApplicationException {
        try {
            tagDAO TagDAO = new tagDAO();
            TagDAO.deleteTag(ID);
            return true;
        } catch (TagException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new WebApplicationException(ex, Response.Status.CONFLICT);
        }

    }

    @PUT()
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/")
    public boolean putTag(tagDTO tag) {
        try {
            tagDAO tagDAO = new tagDAO();
            tagDAO.updateTag(tag.getId(), tag.getNome());
            return true;
        } catch (TagException ex) {
            return false;
        }
    }

    @Path("/")
    @POST()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean postTag(tagDTO tag) {
        try {
            tagDAO TagDAO = new tagDAO();
            TagDAO.insertTag(tag.getNome());
            return true;
        } catch (TagException ex) {
            return false;
        }
    }
}
