/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.ws;

import br.ufpr.tads.dac.beans.CidadeBean;
import br.ufpr.tads.dac.beans.ClinicaBean;
import br.ufpr.tads.dac.beans.EnderecoBean;
import br.ufpr.tads.dac.beans.EstadoBean;
import br.ufpr.tads.dac.beans.MedicoBean;
import br.ufpr.tads.dac.beans.PlanoBean;
import br.ufpr.tads.dac.beans.TipoBean;
import br.ufpr.tads.dac.beans.VinculoBean;
import br.ufpr.tads.dac.facade.CidadeEstadoFacade;
import br.ufpr.tads.dac.facade.ClinicaFacade;
import br.ufpr.tads.dac.facade.EnderecoFacade;
import br.ufpr.tads.dac.facade.MedicoFacade;
import br.ufpr.tads.dac.facade.PlanoFacade;
import br.ufpr.tads.dac.facade.TipoFacade;
import br.ufpr.tads.dac.facade.VinculoFacade;
import br.ufpr.tads.dac.facade.impl.CidadeEstadoFacadeImpl;
import br.ufpr.tads.dac.facade.impl.ClinicaFacadeImpl;
import br.ufpr.tads.dac.facade.impl.EnderecoFacadeImpl;
import br.ufpr.tads.dac.facade.impl.MedicoFacadeImpl;
import br.ufpr.tads.dac.facade.impl.PlanoFacadeImpl;
import br.ufpr.tads.dac.facade.impl.TipoFacadeImpl;
import br.ufpr.tads.dac.facade.impl.VinculoFacadeImpl;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author luis_
 */
@Path("service")
public class ServiceResource {
    
    ClinicaFacade clinicaFacade;
    MedicoFacade medicoFacade;
    PlanoFacade planoFacade;
    VinculoFacade vinculoFacade;
    TipoFacade tipoFacade;
    EnderecoFacade enderecoFacade;
    CidadeEstadoFacade cidadeEstadoFacade;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceResource
     */
    public ServiceResource() {
        this.clinicaFacade = new ClinicaFacadeImpl();
        this.medicoFacade =  new MedicoFacadeImpl();
        this.planoFacade = new PlanoFacadeImpl();
        this.vinculoFacade = new VinculoFacadeImpl();
        this.tipoFacade = new TipoFacadeImpl();
        this.enderecoFacade = new EnderecoFacadeImpl();
        this.cidadeEstadoFacade = new CidadeEstadoFacadeImpl();
    }

    /**
     * Retrieves representation of an instance of br.ufpr.tads.dac.ws.ServiceResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "{\"URL\" : {\n" +
        " \"conteudo\" : \"Esta URL é inválida!\"\n" +
        " }\n" +
        "}";
    }
    
    @GET
    @Path("/clinicas")  
    @Produces (MediaType.APPLICATION_JSON)
    public Response getClinicas(){
         List<ClinicaBean> clinicas = new ArrayList<ClinicaBean>();
                clinicas = this.clinicaFacade.listar();
                
                for (ClinicaBean c : clinicas){
                    EnderecoBean endereco = new EnderecoBean();
                    CidadeBean cidade = new CidadeBean();
                    EstadoBean estado =  new EstadoBean();
                    endereco = enderecoFacade.buscar(c.getEndereco().getId());
                    cidade = cidadeEstadoFacade.buscarCidade(endereco.getCidade().getId());
                    estado = cidadeEstadoFacade.buscarEstado(endereco.getEstado().getId());
                    endereco.setCidade(cidade);
                    endereco.setEstado(estado);
                    c.setEndereco(endereco);
                }
         
         GenericEntity<List<ClinicaBean>> lista =
        new GenericEntity<List<ClinicaBean>>(clinicas) {};
         return Response
        .ok(Response.Status.OK)
        .entity(lista)
        .build();    
    }
    
    @GET
    @Path("/clinica/{id}")  
    @Produces (MediaType.APPLICATION_JSON)
    public Response getClinica(@PathParam("id") String id){
         ClinicaBean clinica = clinicaFacade.buscar(Integer.parseInt(id));
         
         EnderecoBean endereco = new EnderecoBean();
         CidadeBean cidade = new CidadeBean();
         EstadoBean estado =  new EstadoBean();
         endereco = enderecoFacade.buscar(clinica.getEndereco().getId());
         cidade = cidadeEstadoFacade.buscarCidade(endereco.getCidade().getId());
         estado = cidadeEstadoFacade.buscarEstado(endereco.getEstado().getId());
         endereco.setCidade(cidade);
         endereco.setEstado(estado);
         clinica.setEndereco(endereco);
         
         return Response
        .ok(Response.Status.OK)
        .entity(clinica)
        .build();    
    }
    
    @GET
    @Path("/medicos")  
    @Produces (MediaType.APPLICATION_JSON)
    public Response getMedicos(){
         List<MedicoBean> medicos = this.medicoFacade.listar();
               
               for (MedicoBean m : medicos){
                   TipoBean tipo = tipoFacade.buscar(m.getTipo().getId());
                   ClinicaBean clinica = clinicaFacade.buscar(m.getClinica().getId());
                   
                   m.setClinica(clinica);
                   m.setTipo(tipo);
               }
         
         GenericEntity<List<MedicoBean>> lista =
        new GenericEntity<List<MedicoBean>>(medicos) {};
         return Response
        .ok(Response.Status.OK)
        .entity(lista)
        .build();    
    }
    
    @GET
    @Path("/medicos/integracao/{idclinica}/{idtipo}")  
    @Produces (MediaType.APPLICATION_JSON)
    public Response getMedicosPorClinicaTipo(@PathParam("idclinica") String idClinica, @PathParam("idtipo") String idTipo){
         List<MedicoBean> medicos = this.medicoFacade.buscarPorClinicaTipo(Integer.parseInt(idClinica), Integer.parseInt(idTipo));
               
               for (MedicoBean m : medicos){
                   TipoBean tipo = tipoFacade.buscar(m.getTipo().getId());
                   ClinicaBean clinica = clinicaFacade.buscar(m.getClinica().getId());
                   
                   m.setClinica(clinica);
                   m.setTipo(tipo);
               }
         
         GenericEntity<List<MedicoBean>> lista =
        new GenericEntity<List<MedicoBean>>(medicos) {};
         return Response
        .ok(Response.Status.OK)
        .entity(lista)
        .build();    
    }
    
    @GET
    @Path("/medico/{id}")  
    @Produces (MediaType.APPLICATION_JSON)
    public Response getMedico(@PathParam("id") String id){
        MedicoBean medico = medicoFacade.buscar(Integer.parseInt(id));
        
        TipoBean tipo = tipoFacade.buscar(medico.getTipo().getId());
        ClinicaBean clinica = clinicaFacade.buscar(medico.getClinica().getId());
        medico.setClinica(clinica);
        medico.setTipo(tipo); 
        
         return Response
        .ok(Response.Status.OK)
        .entity(medico)
        .build();    
    }
    
    @GET
    @Path("/planos")  
    @Produces (MediaType.APPLICATION_JSON)
    public Response getPlanos(){
         List<PlanoBean> planos = this.planoFacade.listar();   
         
         GenericEntity<List<PlanoBean>> lista =
        new GenericEntity<List<PlanoBean>>(planos) {};
         return Response
        .ok(Response.Status.OK)
        .entity(lista)
        .build();    
    }
    
    @GET
    @Path("/plano/{id}")  
    @Produces (MediaType.APPLICATION_JSON)
    public Response getPlano(@PathParam("id") String id){
        PlanoBean plano = planoFacade.buscar(Integer.parseInt(id));
        
         return Response
        .ok(Response.Status.OK)
        .entity(plano)
        .build();    
    }
    
    @GET
    @Path("/vinculos/{tipolista}/{id}")  
    @Produces (MediaType.APPLICATION_JSON)
    public Response getVinculos(@PathParam("tipolista") String tipoLista, @PathParam("id") String id){
         
        List<VinculoBean> vinculos = new ArrayList<VinculoBean>();
                
                if (tipoLista.equals("clinica") ){
                    vinculos = this.vinculoFacade.listarPorClinica(Integer.parseInt(id));
                }
                else if (tipoLista.equals("plano")){
                    vinculos = this.vinculoFacade.listarPorPlano(Integer.parseInt(id));
                }
                else {
                    vinculos = this.vinculoFacade.listar();
                }
                
                for (VinculoBean v : vinculos){
                    
                    ClinicaBean c = clinicaFacade.buscar(v.getClinica().getId());
                    
                    EnderecoBean endereco = new EnderecoBean();
                    CidadeBean cidade = new CidadeBean();
                    EstadoBean estado =  new EstadoBean();
                    endereco = enderecoFacade.buscar(c.getEndereco().getId());
                    cidade = cidadeEstadoFacade.buscarCidade(endereco.getCidade().getId());
                    estado = cidadeEstadoFacade.buscarEstado(endereco.getEstado().getId());
                    endereco.setCidade(cidade);
                    endereco.setEstado(estado);
                    c.setEndereco(endereco);
                    
                    PlanoBean p = planoFacade.buscar(v.getPlano().getId());
                    
                    v.setClinica(c);
                    v.setPlano(p);
                    
                }
         
         GenericEntity<List<VinculoBean>> lista =
        new GenericEntity<List<VinculoBean>>(vinculos) {};
         return Response
        .ok(Response.Status.OK)
        .entity(lista)
        .build();    
    }
    
    @GET
    @Path("/vinculo/{id}")  
    @Produces (MediaType.APPLICATION_JSON)
    public Response getVinculo(@PathParam("id") String id){
        VinculoBean vinculo = vinculoFacade.buscar(Integer.parseInt(id));
                
                ClinicaBean clinica = clinicaFacade.buscar(vinculo.getClinica().getId());
                PlanoBean plano = planoFacade.buscar(vinculo.getPlano().getId());
                
                vinculo.setClinica(clinica);
                vinculo.setPlano(plano);
        
         return Response
        .ok(Response.Status.OK)
        .entity(vinculo)
        .build();    
    }
    
    @GET
    @Path("/tipos")  
    @Produces (MediaType.APPLICATION_JSON)
    public Response getTipos(){
         List<TipoBean> tipos = this.tipoFacade.listar();   
         
         GenericEntity<List<TipoBean>> lista =
        new GenericEntity<List<TipoBean>>(tipos) {};
         return Response
        .ok(Response.Status.OK)
        .entity(lista)
        .build();    
    }
    
    @GET
    @Path("/tipo/{id}")  
    @Produces (MediaType.APPLICATION_JSON)
    public Response getTipo(@PathParam("id") String id){
        TipoBean tipo = tipoFacade.buscar(Integer.parseInt(id));
        
         return Response
        .ok(Response.Status.OK)
        .entity(tipo)
        .build();    
    }
    
    /**
     * PUT method for updating or creating an instance of ServiceResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
