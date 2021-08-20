/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3app.service;

import M3app.Memoir;
import java.math.BigDecimal;
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
import java.sql.Date; 
import java.sql.Time;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.Json;
import javax.json.JsonObject;
import java.text.SimpleDateFormat; 
import java.text.DateFormatSymbols;/*Task 4(b)*/
/**
 *
 * @author Jasper
 */
@Stateless
@Path("m3app.memoir")
public class MemoirFacadeREST extends AbstractFacade<Memoir> {

    @PersistenceContext(unitName = "M3APPPU")
    private EntityManager em;

    public MemoirFacadeREST() {
        super(Memoir.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Memoir entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Memoir entity) {
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
    public Memoir find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Memoir> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Memoir> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    //static//
    
    @GET //find by movie name    
    @Path("Memoir.findByMovieName/{movieName}")
    @Produces({"application/json"})
    public List<Memoir> findByMovieName(@PathParam("movieName") String movieName)
    {
        Query query = em.createNamedQuery("Memoir.findByMovieName");
        query.setParameter("movieName", movieName);
        return query.getResultList();
    }
    
    @GET //find by movie release date
    @Path("Memoir.findByMovieReleaseDate/{movieReleaseDate}")
    @Produces({"application/json"})
    public List<Memoir> findByMovieReleaseDate(@PathParam("movieReleaseDate") Date movieReleaseDate)
    {
        Query query = em.createNamedQuery("Memoir.findByMovieReleaseDate");
        query.setParameter("movieReleaseDate", movieReleaseDate);
        return query.getResultList();
    }
    
    @GET //find by watch date
    @Path("Memoir.findByWatchDate/{watchDate}")
    @Produces({"application/json"})
    public List<Memoir> findByWatchDate(@PathParam("watchDate") Date watchDate)
    {
        Query query = em.createNamedQuery("Memoir.findByWatchDate");
        query.setParameter("watchDate", watchDate);
        return query.getResultList();
    }
    
    @GET //find by watch time
    @Path("Memoir.findByWatchTime/{watchTime}")
    @Produces({"application/json"})
    public List<Memoir> findByWatchTime(@PathParam("watchTime") Time watchTime)
    {
        Query query = em.createNamedQuery("Memoir.findByWatchTime");
        query.setParameter("watchTime", watchTime);
        return query.getResultList();
    }
    
    @GET //find by comment
    @Path("Memoir.findByComment/{comment}")
    @Produces({"application/json"})
    public List<Memoir> findByComment(@PathParam("comment") String comment)
    {
        Query query = em.createNamedQuery("Memoir.findByComment");
        query.setParameter("comment", comment);
        return query.getResultList();
    }
    
    @GET //find by rating
    @Path("Memoir.findByRating/{rating}")
    @Produces({"application/json"})
    public List<Memoir> findByRating(@PathParam("rating") float rating)
    {
        Query query = em.createNamedQuery("Memoir.findByRating");
        query.setParameter("rating", rating);
        return query.getResultList();
    }
    
    // dynamic
    
    @GET //find by cinemaid
    @Path("findByCinemaid/{cinemaid}")
    @Produces({"application/json"})
    public List<Memoir> findByCinemaid(@PathParam("cinemaid") String cinemaid)
    {
        TypedQuery<Memoir> query = em.createQuery("SELECT m FROM Memoir m WHERE m.cinemaid.cinemaid = :cinemaid", Memoir.class);
        query.setParameter("cinemaid",cinemaid);
        return query.getResultList();
    }
    
    @GET //find by personid
    @Path("findByPersonid/{personid}")
    @Produces({"application/json"})
    public List<Memoir> findByPersonid(@PathParam("personid") String personid)
    {
        TypedQuery<Memoir> query = em.createQuery("SELECT m FROM Memoir m WHERE m.personid.personid = :personid", Memoir.class);
        query.setParameter("personid",personid);
        return query.getResultList();
    }
    
    
    
    //Task 3(c)
    @GET
    @Path("findByRatingANDlocation/{rating}/{location}")
    @Produces({"application/json"})
    public List<Memoir> findByRatingANDlocation(@PathParam("rating") double rating, @PathParam("location") String location)
    {
        TypedQuery<Memoir> query = em.createQuery("SELECT m FROM Memoir m WHERE m.cinemaid.location = :location AND m.rating = :rating", Memoir.class);
        query.setParameter("rating", rating);
        query.setParameter("location", location);
        return query.getResultList();
    }
    
    
    //Task 3(d)
    @GET
    @Path("findByRatingANDlocation2/{rating}/{location}")
    @Produces({"application/json"})
    public List<Memoir> findByRatingANDlocation2(@PathParam("rating") double rating, @PathParam("location") String location)
    {
        Query query = em.createNamedQuery("Memoir.findByRatingANDlocation");
        query.setParameter("rating", rating);
        query.setParameter("location", location);
        return query.getResultList();
    }
    
    
    //Task 4(a)
    @GET
    @Path("findByPersonidAndDate/{personid}/{start}/{end}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object findByPersonidAndDate(@PathParam("personid") String personid, @PathParam("start") Date start, @PathParam("end") Date end) 
    {
        Query query = em.createQuery("SELECT m.cinemaid.location, count (m.memoirid) FROM Memoir m WHERE m.personid.personid = :personid AND m.watchDate between :start and :end group by m.cinemaid.location");
        query.setParameter("personid", personid);
        query.setParameter("start", start);
        query.setParameter("end", end);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList) 
        {
            JsonObject Object = Json.createObjectBuilder().add("location", (String) row[0])
            .add("count", (long) row[1])
            .build();
            arrayBuilder.add(Object);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    //Task 4(b)  //

     
    public String getMonth(int month)
    {
        return new DateFormatSymbols().getMonths()[month-1];
    }
    
    @GET
    @Path("findByPersonidAndYear/{personid}/{year}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object findByPersonAndYear(@PathParam("personid") String personid, @PathParam("year") int year)
    {
        DateFormatSymbols format = new DateFormatSymbols();
        Query query = em.createQuery("SELECT EXTRACT(Month from m.watchDate), count(m.memoirid) FROM Memoir m WHERE m.personid.personid = :personid AND EXTRACT(Year from m.watchDate) = :year group by EXTRACT(Month from m.watchDate)");
        query.setParameter("personid", personid);
        query.setParameter("year", year);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList)
        {
            JsonObject Object = Json.createObjectBuilder().add("Month", (String) format.getMonths()[(int) row[0]-1] ).add("count", (long) row[1]).build();
            arrayBuilder.add(Object);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }  
    
    //Task 4(c)
    @GET
    @Path("findHighestRatingByPersonid/{personid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object findHighestRatingByPersonid(@PathParam("personid") String personid)
    {
        Query query = em.createQuery("SELECT m.movieName, m.rating, m.movieReleaseDate FROM Memoir m WHERE m.rating = (SELECT max(n.rating) FROM Memoir n where n.personid.personid = :personid) AND m.personid.personid = :personid");
        query.setParameter("personid", personid);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList)
        {
            JsonObject Object = Json.createObjectBuilder()  
                    .add("movieName", (String) row[0])
                    .add("rating", (BigDecimal) row[1])
                    .add("releaseDate", (String) row[2].toString())
                    .build();
            arrayBuilder.add(Object);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    
    //Task 4(d)
    @GET
    @Path("returnMovieSameYear/{personid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object returnMovieSameYear(@PathParam("personid") String personid)
    {
        Query query = em.createQuery("SELECT m.movieName, EXTRACT(Year from m.movieReleaseDate) FROM Memoir m WHERE m.personid.personid = :personid AND EXTRACT(Year from m.movieReleaseDate) = EXTRACT(Year from m.watchDate)");
        query.setParameter("personid", personid);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Object[] row : queryList)
        {
            JsonObject Object = Json.createObjectBuilder()  
                    .add("movieName", (String) row[0])
                    .add("year", (int) row[1])
                    .build();
            arrayBuilder.add(Object);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    //Task 4(e)
    @GET
    @Path("findWatchedBefore/{personid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object findWatchedBefore(@PathParam("personid") String personid)
    {
        Query query = em.createQuery("SELECT (m.movieName), EXTRACT(Year from m.movieReleaseDate) FROM Memoir m WHERE m.personid.personid = :personid AND m.movieName in (SELECT n.movieName from Memoir n WHERE n.personid.personid = :personid group by n.movieName having count(distinct(n.watchDate)) != 1)");
        query.setParameter("personid", personid);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Object[] row : queryList)
        {
            JsonObject Object = Json.createObjectBuilder()  
                    .add("movieName", (String) row[0])
                    .add("year", (int) row[1])
                    .build();
            arrayBuilder.add(Object);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    //Task 4(f)
    @GET
    @Path("findHighFive/{personid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object findHighFive(@PathParam("personid") String personid)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Query query = em.createQuery("SELECT m.movieName, m.movieReleaseDate, m.rating FROM Memoir m "
                + "WHERE m.personid.personid = :personid AND EXTRACT(Year from m.movieReleaseDate) = 2020 order by m.rating DESC");
        query.setParameter("personid", personid);
        query.setMaxResults(5);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Object[] row : queryList)
        {
            JsonObject Object = Json.createObjectBuilder()  
                    .add("movieName", (String) row[0])
                    .add("releaseDate", (String) format.format(row[1]))
                    .add("rating", (BigDecimal) row[2])
                    .build();
            arrayBuilder.add(Object);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
}
