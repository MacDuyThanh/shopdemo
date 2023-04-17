/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ProductDAO;
import entity.Product;
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
@Path("/Product")
public class ProductService {
    ProductDAO dao;

    public ProductService() {
        dao = new ProductDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProduct() {
        return dao.getAllProduct();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search-by-name/{name}")
    public List<Product> getProductByName(@PathParam("name") String name) {
        return dao.getByName(name);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search-by-category/{idCate}")
    public List<Product> getProductByCategory(@PathParam("idCate") Integer idCate) {
        return dao.getByCategory(idCate);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search-by-id/{id}")
    public Product getProductById(@PathParam("id") Integer id) {
        return dao.getById(id);
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String add(Product p) {
        return dao.insert(p)>0?"Add Success":"Add fail";
    }
    
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Product p) {
        return dao.update(p)>0?"Update Success":"Update fail";
    }
    
     @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public Integer delete(@PathParam("id") Integer id) {
        return dao.delete(id);
    }
    
    
}
