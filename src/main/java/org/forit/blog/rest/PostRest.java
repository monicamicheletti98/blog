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
import org.forit.blog.dao.PostDAO;
import org.forit.blog.dto.PostDTO;



import org.forit.blog.exception.BlogException;


/**
 *
 * @author UTENTE
 */
@Path("/post")
public class PostRest implements Serializable{

    @Path("/")
    @GET()
    @Produces("application/json")
    public List<PostDTO> getPosts() {
        PostDAO postDAO = new PostDAO();
        return postDAO.getPostsList();
    }

    @Path("/{id}")
    @GET()
    @Produces("application/json")
    public PostDTO getPost(@PathParam("id") long ID) {
        PostDAO postDAO = new PostDAO();
        return postDAO.getPost(ID);
    }

    @Path("/")
    @POST()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean postPost(PostDTO post) {
        try {
            PostDAO postDAO = new PostDAO();
            postDAO.insertPost(post.getId(), post.getIdCategoria(), post.getTitolo(), post.getDescrizione(),
                    post.getData(),
                    post.isVisibile(), post.getVisite(), post.getIdAutore());
            return true;
        } catch (BlogException ex) {
            return false;
        }
    }

    @Path("/")
    @PUT()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean putPost(PostDTO post) {
        try {
            PostDAO postDAO = new PostDAO();
            postDAO.updatePost(post.getId(), post.getIdCategoria(), post.getTitolo(), post.getDescrizione(),
                    post.getData(),
                    post.isVisibile(), post.getVisite(), post.getIdAutore());
            return true;
        } catch (BlogException ex) {
            return false;
        }
    }

    @Path("/")
    @DELETE()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean deletePost(long ID) throws WebApplicationException {
        try {
            PostDAO postDAO = new PostDAO();
            postDAO.deletePost(ID);
            return true;
        } catch (BlogException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new WebApplicationException(ex, Response.Status.CONFLICT);
        }

    }
}
