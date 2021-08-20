/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3app.service;

import M3app.Cinema;
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

/**
 *
 * @author Jasper
 */
@Stateless
@Path("m3app.cinema")
public class CinemaFacadeREST extends AbstractFacade<Cinema> {

    @PersistenceContext(unitName = "M3APPPU")
    private EntityManager em;

    public CinemaFacadeREST() {
        super(Cinema.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cinema entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Cinema entity) {
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
    public Cinema find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cinema> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cinema> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @GET   //find by cinema_location
    @Path("Cinema.findByLocation/{location}")
    @Produces({"application/json"})
    public List<Cinema> findByLocation(@PathParam("location") String location) 
    {
        Query query = em.createNamedQuery("Cinema.findByLocation");
        query.setParameter("location",location);
        return query.getResultList();
    }

    @GET   //find by cinema_name
    @Path("findByCinemaName/{cinemaName}")
    @Produces({"application/json"})
    public List<Cinema> findByCinemaName(@PathParam("cinemaName") String cinemaName) 
    {
        Query query = em.createNamedQuery("Cinema.findByCinemaName");
        query.setParameter("cinemaName",cinemaName );
        return query.getResultList();
    }

    @GET  //find by cinemaID
    @Path("findByCinemaid/{cinemaid}")
    @Produces({"application/json"})
    public List<Cinema> findByCinemaID(@PathParam("cinemaid") String cinemaid)
    {
        Query query = em.createNamedQuery("Cinema.findByCinemaid");
        query.setParameter("cinemaid",cinemaid);
        return query.getResultList();
    }
    
    
}
