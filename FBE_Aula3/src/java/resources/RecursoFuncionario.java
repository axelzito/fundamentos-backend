/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import com.google.gson.Gson;
import business.AccessManager;
import com.google.gson.GsonBuilder;
import dto.Funcionario;
import java.net.URI;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author viniciusspatto
 */
@Path("funcionarios")
public class RecursoFuncionario {

    /**
     * @return a JSON (collection of funcionarios)
     * Acessa todos os funcionarios por 
     * http://localhost:8080/FBE_Aula3/resources/funcionarios
     */
    @GET
    @Produces("application/json" + "; charset=utf8")
    public Response getFuncionarios() {
        String funcionarios = "";
        ArrayList<Funcionario> funcLista = new ArrayList<Funcionario>();
        try {
            funcLista = new AccessManager().getFuncionarios();
            Gson gson = new Gson();
            funcionarios = gson.toJson(funcLista);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (funcionarios.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.ok(funcionarios).build();
        }
    }
    
    /*
     * @return a JSON (just one funcionario)
     *Acessa um unico funcionario por 
     *http://localhost:8080/FBE_Aula3/resources/funcionarios/0001 (0002, ...)
    */
    @Path("{cpf}")
    @GET
    @Produces("application/json; charset=utf8")
    public Response getFuncionario(@PathParam("cpf") String cpf){
        String func = "";
        Funcionario funcionario = new Funcionario();
        try{
            funcionario = new AccessManager().getFuncionario(cpf);
            Gson gson = new Gson();
            func = gson.toJson(funcionario);
        } catch(Exception e){
            e.printStackTrace();
        }
        if(funcionario.getCpf() == null || funcionario.getCpf().trim().equals("")){
            return Response.status(Response.Status.NOT_FOUND).build();
        } else{
            return Response.ok(func).build();
        }

    }

    /*
     * @return a HTTP Response (OK or Not Found)
     *Acessa e atualiza um unico o funcionario por 
     *http://localhost:8080/FBE_Aula3/resources/funcionarios/
    */
    @PUT
    @Consumes("application/json")
    public Response setFuncionario(String func) {
        boolean result = false;
        Funcionario funcionario = new Funcionario();
        try{
            Gson gson = new GsonBuilder().setDateFormat("dd/mm/yyyy").create();
            funcionario = gson.fromJson(func, Funcionario.class);
            result = new AccessManager().setFuncionario(funcionario);
        } catch(Exception e){
            e.printStackTrace();
        }
        if(result){
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }    
    }
    
    /*
     * @return a HTTP Response (OK and URI, or Expectation Failed)
     * Insere um funcionario por 
     *http://localhost:8080/FBE_Aula3/resources/funcionarios/
    */
    @POST
    @Consumes("application/json")
    public Response addFuncionario(String func, @Context UriInfo uriInfo) {
        boolean result = false;
        URI uri;
        Funcionario funcionario = new Funcionario();
        try{
            Gson gson = new GsonBuilder().setDateFormat("dd/mm/yyyy").create();
            funcionario = gson.fromJson(func, Funcionario.class);
            result = new AccessManager().addFuncionario(funcionario);
        } catch(Exception e){
            e.printStackTrace();
        }
        if (result){
            uri = uriInfo.getAbsolutePathBuilder().path(funcionario.getCpf()).build();
            return Response.created(uri).build();
        } else{
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }
    
    /*
     * @return a HTTP Response (OK, or Not Found)
     * Exclui um funcionario por 
     *http://localhost:8080/FBE_Aula3/resources/funcionarios/del/0001 (0002, ...)
    */
    @DELETE
    @Path("del/{cpf}")
    public Response delFuncionario(@PathParam("cpf") String cpf){
        boolean result = false;
        try{
            result = new AccessManager().delFuncionario(cpf);
        } catch(Exception e){
            e.printStackTrace();
        }
        if(result){
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
}
