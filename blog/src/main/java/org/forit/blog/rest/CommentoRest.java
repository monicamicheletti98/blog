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
import org.forit.blog.dao.CommentoDAO;
import org.forit.blog.dto.CommentoDTO;
import org.forit.blog.exception.BlogException;

@Path("/commento")
public class CommentoRest implements Serializable{

    @Path("/")
    @GET()
    @Produces("application/json")
    public List<CommentoDTO> getCommentos() {
        CommentoDAO commentoDAO = new CommentoDAO();
        return commentoDAO.getCommentosList();
    }

    @Path("/{id}")
    @GET()
    @Produces("application/json")
    public CommentoDTO getCommento(@PathParam("id") long ID) {
        CommentoDAO commentoDAO = new CommentoDAO();
        return commentoDAO.getCommento(ID);
    }

    @Path("/")
    @POST()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean postCommento(CommentoDTO commento) {
        try {
            CommentoDAO commentoDAO = new CommentoDAO();
            commentoDAO.insertCommento(commento.getId(), commento.getNickname(), commento.getEmail(), commento.getDataInserimento(), commento.getTesto(), commento.getDataRisposta()
                    ,commento.getRisposta(),commento.isVisibile(), commento.getId_post());
            return true;
        } catch (BlogException ex) {
            return false;
        }
    }

    @Path("/")
    @PUT()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean putCommento(CommentoDTO commento) {
        try {
            CommentoDAO commentoDAO = new CommentoDAO();
            commentoDAO.updateCommento(commento.getId(), commento.getNickname(), commento.getEmail(), commento.getDataInserimento(), commento.getTesto(), commento.getDataRisposta(),
                    commento.getRisposta(), commento.isVisibile(), commento.getId_post());
            return true;
        } catch (BlogException ex) {
            return false;
        }
    }

    @Path("/{id}")
    @DELETE()
    @Produces("application/json")
    public boolean deleteCommento(@PathParam("id") long ID) throws WebApplicationException {
        try {
            CommentoDAO commentoDAO = new CommentoDAO();
            commentoDAO.deleteCommento(ID);
            return true;
        } catch (BlogException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new WebApplicationException(ex, Response.Status.CONFLICT);
        }

    }
}
