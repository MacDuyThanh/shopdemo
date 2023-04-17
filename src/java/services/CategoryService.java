/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CategoryDAO;
import entity.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author thanh
 */
@Stateless
@Path("/Category")
public class CategoryService {

    CategoryDAO dao;

    public CategoryService() {
        dao = new CategoryDAO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategory() {
        return dao.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search-by-name/{name}")
    public List<Category> getCategoryByName(@PathParam("name") String name) {
        return dao.getByName(name);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search-by-id/{id}")
    public Category getCategoryById(@PathParam("id") Integer id) {
        return dao.getById(id);
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String add(Category c) {
        return dao.insert(c)>0?"Add Success":"Add fail";
    }
    
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Category c) {
        return dao.update(c)>0?"Update Success":"Update fail";
    }
    
     @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public Integer delete(@PathParam("id") Integer id) {
        return dao.delete(id);
    }
}
