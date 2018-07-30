/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbe_aula4.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fbe_aula4.dao.ProdutoDAO;
import fbe_aula4.dao.daoImpl.ProdutoDAOImpl;
import fbe_aula4.model.Produto;
import java.net.URI;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author viniciusspatto
 */
@Path("produtos")
public class RecursoProdutos {

    private final ProdutoDAO produtoDao = new ProdutoDAOImpl();
    
    /**
     * Creates a new instance of RecursoProdutos
     */
    public RecursoProdutos() {
    }

    /**
     * Retrieves representation of an instance of fbe_aula4.resources.RecursoProdutos
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProdutos() {
        String produtos = null;
        List<Produto> prodLista = produtoDao.getProdutos();
        try{
            Gson gson = new Gson();
            produtos = gson.toJson(prodLista);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(produtos.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        } else{
            return Response.ok(produtos).build();
        }
    }

    @Path("{codigo}")
    @GET
    @Produces("application/json")
    public Response getProduto(@PathParam("codigo") String codigo) {
        String prod = null;
        Produto produto = produtoDao.getProduto(codigo);
        try{
            Gson gson = new Gson();
            prod = gson.toJson(produto);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(prod.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        } else{
            return Response.ok(prod).build();
        }
    }
    
//    @Path("{codigo}")
//    @POST
//    @Produces("application/json")
//    public void 
  
    /**
     * PUT method for updating or creating an instance of RecursoProdutos
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduto(String produto, @Context UriInfo uriInfo){
        boolean result = false;
        URI uri;
        Produto novoProduto = new Produto();
        Gson gson = new Gson();
        try {
            novoProduto = gson.fromJson(produto, Produto.class);
            result = produtoDao.addProduto(novoProduto);
        } catch (Exception e) {
        }
        if (result) {
            uri = uriInfo.getAbsolutePathBuilder().path(novoProduto.getCodigo()).build();
            return Response.created(uri).build();
        }
        else{
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }
    
    @Path("delete/{codigo}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeProduto(@PathParam("codigo") String codigo){
        boolean result = false;
        try {
            result = produtoDao.delProduto(codigo);
        } catch (Exception e) {
        }
        if (result) {
            return Response.ok().build();
        }
        else{
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putProduto(String produto) {
        boolean result = false;
        Produto prod = new Produto();
        Gson gson = new Gson();
        try {
            prod = gson.fromJson(produto, Produto.class);
            result = produtoDao.setProduto(prod);
        } catch (Exception e) {
        }
        if (result) {
            return Response.ok().build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
