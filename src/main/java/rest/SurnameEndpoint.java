package rest;

import model.Gender;
import model.Surname;

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
        TypedQuery<Surname> result =em.createNamedQuery("countAll",Surname.class);
        int all = result.getResultList().size();

        TypedQuery<Surname> resultM =em.createNamedQuery("countAllMales",Surname.class).setParameter("MALE", Gender.MALE);
        int allM = result.getResultList().size();

        TypedQuery<Surname> resultF =em.createNamedQuery("countAllFemales",Surname.class).setParameter("FEMALE",Gender.FEMALE);
        int allF = result.getResultList().size();

        return ("total_all: "+all+"\n total_male:"+allM+"\n total_female: "+allF);
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
