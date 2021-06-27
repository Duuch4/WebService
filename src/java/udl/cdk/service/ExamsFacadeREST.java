
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udl.cdk.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import udl.cdk.Exams;
import udl.cdk.Grades;

/**
 *
 * @author Albert
 */
@Stateless
@Path("udl.cdk.exams")
public class ExamsFacadeREST extends AbstractFacade<Exams> {

    @PersistenceContext(unitName = "WebServiceAlbertGuillemPU")
    private EntityManager em;

    public ExamsFacadeREST() {
        super(Exams.class);
    }

    @POST
    @Override
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Exams entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Exams entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }
    
    // Devuelve el examen por ID que se introduce.
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Exams find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exams> findAll() {
        return super.findAll();
    }
    
    // Modificar la descripción de un examen.
    @PUT
    @Path("/id/{id}")
    @Consumes({MediaType.TEXT_PLAIN})
    public void modifyDescriptions(@PathParam("id") Integer id, String description) {
        Exams newExam = super.find(id);
        newExam.setDescription(description);
        super.edit(newExam);
    }
    
     // Borrar examenes que no tengan notas.
    @DELETE
    @Path("/delete")
    public void removeEmptyExams() {
        List<Exams> list = super.findAll();

        list.forEach((exam) -> {
            Collection<Grades> gc = exam.getGradesCollection();
            if (gc.isEmpty()) {
                super.remove(super.find(exam.getId()));
            }
        });
    }
       
    // Devuelve el examen por "Description" que se introduce.
    @GET
    @Path("/description/{description}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exams> findExamDescription(@PathParam("description") String description) {
        List<Exams> allExams = super.findAll();
        List<Exams> matches = new ArrayList<>();

        allExams.stream().filter((exam) -> (exam.getDescription().contains(description))).forEachOrdered((exam) -> {
            matches.add(exam);
        });
        return matches;
    }
    
    // Descargar el examen.
    @GET
    @Path("/download/{id}")
    @Produces({javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM})
    public Response downloadExam(@javax.ws.rs.PathParam("id") Integer id) {
    String info = "Examen con ID: " + super.find(id).getId() + ", DESCRIPTION: " + super.find(id).getDescription() + ", DATE: " + 
            super.find(id).getDate() + ", TIME: " + super.find(id).getTime() + ", LOCATION: " + super.find(id).getLocation();
        File file= new File("exams.txt");
        BufferedWriter bwriter = null;
        try{
           bwriter = new BufferedWriter(new FileWriter(file));
            bwriter.write(info);
        }catch(IOException e){
        }finally{
            try{
                bwriter.close();
            }catch(IOException e){
            }
        }
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment;filename=" + "exams.txt");
        System.out.println("Se ha descargado con éxito!");
        return response.build();
    }


    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exams> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
