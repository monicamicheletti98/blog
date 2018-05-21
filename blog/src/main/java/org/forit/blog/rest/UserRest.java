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
import org.forit.blog.dao.UserDAO;

import org.forit.blog.dto.UserDTO;
import org.forit.blog.exception.BlogException;

@Path("/user")
public class UserRest implements Serializable{

    @Path("/")
    @GET()
    @Produces("application/json")
    public List<UserDTO> getUsers() {
        UserDAO userDAO = new UserDAO();
        return userDAO.getUsersList();
    }

    @Path("/{id}")
    @GET()
    @Produces("application/json")
    public UserDTO getUser(@PathParam("id") long ID) {
        UserDAO userDAO = new UserDAO();
        return userDAO.getUser(ID);
    }

    @Path("/")
    @POST()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean postUser(UserDTO user) {
        try {
            UserDAO userDAO = new UserDAO();
            userDAO.insertUser (user.getId(), user.getNome(), user.getCognome(), user.getUsername(), user.getEmail(), user.getPassword(),
                    user.getDataRegistrazione(), user.getDataUltimoAccesso(), user.isAttivo(),
                    user.getTentativi(), user.isBloccato(), user.getId_ruolo());
            return true;
        } catch (BlogException ex) {
            return false;
        }
    }

    @Path("/")
    @PUT()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean putUser(UserDTO user) {
        try {
            UserDAO userDAO = new UserDAO();
            userDAO.updateUser(user.getId(), user.getNome(), user.getCognome(), user.getUsername(), user.getEmail(), user.getPassword(),
                    user.getDataRegistrazione(), user.getDataUltimoAccesso(), user.isAttivo(),
                    user.getTentativi() , user.isBloccato(), user.getId_ruolo());
            return true;
        } catch (BlogException ex) {
            return false;
        }
    }

    @Path("/")
    @DELETE()
    @Consumes("application/json")
    @Produces("application/json")
    public boolean deleteUser(UserDTO user){
        try {
            UserDAO userDAO = new UserDAO();
            userDAO.deleteUser(user.getId());
            return true;
        } catch (BlogException ex) {
            System.out.println("Si è verificato un errore " + ex.getMessage());
            throw new WebApplicationException(ex, Response.Status.CONFLICT);
        }

    }
}
