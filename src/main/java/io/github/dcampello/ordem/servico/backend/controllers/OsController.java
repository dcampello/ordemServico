/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dcampello.ordem.servico.backend.controllers;

import com.sun.jersey.multipart.FormDataParam;
import io.github.dcampello.ordem.servico.backend.business.OrdemServicoBus;
import io.github.dcampello.ordem.servico.backend.data.OrdemServico;
import io.github.dcampello.ordem.servico.backend.enumerado.ordemServico.Status;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Daniel
 */
@Path("ordensServico")
public class OsController {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<OrdemServico>    listOs(){
       try {
             OrdemServicoBus ordemServicoBus = new OrdemServicoBus();
             return ordemServicoBus.listar();
         } catch (Exception ex) {
             Logger.getLogger(OsController.class.getName()).log(Level.SEVERE, null, ex);
             throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
         }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public OrdemServico getOs(@PathParam("id") long id) {
        try {
             OrdemServicoBus ordemServicoBus = new OrdemServicoBus();
             return ordemServicoBus.selecionar(id);
         } catch (Exception ex) {
             Logger.getLogger(OsController.class.getName()).log(Level.SEVERE, null, ex);
             throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
         }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response create(OrdemServico ordemServico){
         try {

             OrdemServicoBus ordemServicoBus = new OrdemServicoBus();
             ordemServicoBus.inserir(ordemServico);
             return Response.status(Response.Status.OK).build();
         } catch (Exception ex) {
             Logger.getLogger(OsController.class.getName()).log(Level.SEVERE, null, ex);
             throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
         }
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response update(OrdemServico ordemServico){
   
            try {
             ordemServico.setStatus(Status.PENDENTE);
 
             OrdemServicoBus ordemServicoBus = new OrdemServicoBus();
             ordemServicoBus.alterar(ordemServico);
             return Response.status(Response.Status.OK).build();
         } catch (Exception ex) {
             Logger.getLogger(OsController.class.getName()).log(Level.SEVERE, null, ex);
             throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
         }
     }
     
    @DELETE
    @Path("{id}/")
    public Response delete(@PathParam("id") long id){ 
         try {
             OrdemServicoBus ordemServicoBus = new OrdemServicoBus();
             ordemServicoBus.excluir(id);
             return Response.status(Response.Status.OK).build();
         } catch (Exception ex) {
             Logger.getLogger(OsController.class.getName()).log(Level.SEVERE, null, ex);
             throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
         }
     }

    @PUT
    @Path("{id}/")
    public Response concluir(@PathParam("id") long id) {
         try {
             OrdemServicoBus ordemServicoBus = new OrdemServicoBus();
 
             OrdemServico c = ordemServicoBus.selecionar(id);
             c.setStatus(Status.FINALIZADA);
 
             ordemServicoBus.alterar(c);
             return Response.status(Response.Status.OK).build();
         } catch (Exception ex) {
             Logger.getLogger(OsController.class.getName()).log(Level.SEVERE, null, ex);
             throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
         }
    }
    
//    @POST
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Path("/upload")
//    public Response upload(@FormDataParam("ordemServico") OrdemServico ordemServico,
//            @FormDataParam("file") InputStream arquivo){
//         try {
//             return Response.status(Response.Status.OK).build();
//         } catch (Exception ex) {
//             Logger.getLogger(OsController.class.getName()).log(Level.SEVERE, null, ex);
//             throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
//         }
//    }
}
