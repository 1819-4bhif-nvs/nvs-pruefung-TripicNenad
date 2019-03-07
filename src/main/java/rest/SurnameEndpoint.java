package rest;

import model.Gender;
import model.Surname;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.lang.reflect.Type;

@Path("surname")
public class SurnameEndpoint {
    @PersistenceContext
    EntityManager em;



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll()
    {
        TypedQuery<Long> result =em.createNamedQuery("countAll",Long.class);
        long all = result.getSingleResult();

        TypedQuery<Long> resultM =em.createNamedQuery("countAllMales",Long.class)
                .setParameter("MALE", Gender.MALE);
        long allM = resultM.getSingleResult();

        TypedQuery<Long> resultF =em.createNamedQuery("countAllFemales",Long.class)
                .setParameter("FEMALE",Gender.FEMALE);
        long allF = resultF.getSingleResult();
        return "{ total_all : "+ all+"\n, total_male "+ allM +"\n, total_Female:"+ allF +"}";
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Surname getSurnameById(@PathParam(("id"))long id)
    {
        TypedQuery<Surname> result = em.createNamedQuery("Find.ById",Surname.class).setParameter("ID",id);
        return result.getSingleResult();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam(("id"))long id)
    {
        Surname s = em.createNamedQuery("Find.ById",Surname.class).setParameter("ID",id).getSingleResult();
        em.remove(s);
    }



}
