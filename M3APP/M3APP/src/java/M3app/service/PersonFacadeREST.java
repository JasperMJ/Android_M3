/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3app.service;

import M3app.Person;
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
import javax.persistence.Query; /* import query library*/
import javax.persistence.TypedQuery; /*import typed query library*/
import java.sql.Date; /* import date library

*/

/**
 *
 * @author Jasper
 */
@Stateless
@Path("m3app.person")
public class PersonFacadeREST extends AbstractFacade<Person> {

    @PersistenceContext(unitName = "M3APPPU")
    private EntityManager em;

    public PersonFacadeREST() {
        super(Person.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Person entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Person entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Person find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    // Static //
    @GET //find by firstname//
    @Path("Person.findByFirstname/{firstname}")
    @Produces({"application/json"})
    public List<Person> findByFirstname(@PathParam("firstname") String firstname)
    {
        Query query = em.createNamedQuery("Person.findByFirstname");
        query.setParameter("firstname", firstname);
        return query.getResultList();
    }
    
    @GET //find by surname//
    @Path("Person.findBySurname/{surname}")
    @Produces({"application/json"})
    public List<Person> findBySurname(@PathParam("surname") String surname)
    {
        Query query = em.createNamedQuery("Person.findBySurname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }
    
    @GET //find by gender//
    @Path("Person.findByGender/{gender}")
    @Produces({"application/json"})
    public List<Person> findByGender(@PathParam("gender") String gender)
    {
        Query query = em.createNamedQuery("Person.findByGender");
        query.setParameter("gender", gender);
        return query.getResultList();
    }
    
    @GET //find by DOB//
    @Path("Person.findByDob/{dob}")
    @Produces({"application/json"})
    public List<Person> findByDob(@PathParam("dob") Date dob)
    {
        Query query = em.createNamedQuery("Person.findByDob");
        query.setParameter("dob", dob);
        return query.getResultList();
    }
    
    @GET //find by address//
    @Path("Person.findByAddress/{address}")
    @Produces({"application/json"})
    public List<Person> findByAddress(@PathParam("address") String address)
    {
        Query query = em.createNamedQuery("Person.findByAddress");
        query.setParameter("address", address);
        return query.getResultList();
    }
    
     @GET //find by state//
    @Path("Person.findByState/{state}")
    @Produces({"application/json"})
    public List<Person> findByState(@PathParam("state") String state)
    {
        Query query = em.createNamedQuery("Person.findByState");
        query.setParameter("state", state);
        return query.getResultList();
    }
    
    @GET //find by postcode//
    @Path("Person.findByPostcode/{postcode}")
    @Produces({"application/json"})
    public List<Person> findByPostcode(@PathParam("postcode") String postcode)
    {
        Query query = em.createNamedQuery("Person.findByPostcode");
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }
    
    // dynamic 
    
    //Task 3(b)
    @GET //dynamic find by address, state, and postcode
    @Path("findByAsp/{address}/{state}/{postcode}")
    @Produces({"application/json"})
    public List<Person> findByAsp(@PathParam("address") String address, @PathParam("state") String state, @PathParam("postcode") String postcode)
    {
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.address = :address AND p.state = :state AND p.postcode = :postcode", Person.class);
        query.setParameter("address", address);
        query.setParameter("state", state);
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }
    

}