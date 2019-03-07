package business;

import model.Gender;
import model.Surname;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@ApplicationScoped
public class InitBean {

    @PersistenceContext
    EntityManager em;

    @Transactional
    private void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        readMales();
        readFemales();


    }


    public void readMales(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/maennlich.csv")));
            br.readLine();
            String line;
            Surname surname;
            while ((line = br.readLine()) != null) {
                surname = new Surname(Gender.MALE,line);
                em.persist(surname);
            }
            em.flush();
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public void readFemales(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/weiblich.csv")));
            br.readLine();
            String line;
            Surname surname;
            while ((line = br.readLine()) != null) {
                surname = new Surname(Gender.FEMALE,line);
                em.persist(surname);
            }
            em.flush();
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }



}
