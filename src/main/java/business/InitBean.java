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
        //readMales();
        //readFemales();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/maennlich.csv")));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }


    /*public void readMales(){
        String FILE_NAME = "maennlich.csv";
        new BufferedReader(new InputStreamReader(this.getClass()
                .getResourceAsStream(FILE_NAME), Charset.defaultCharset()))
                .lines()
                .skip(1)
                .map(a -> new Surname(Gender.MALE,a))
                //  .forEach(i -> System.out.println(i.toString()));
                .forEach(em::merge);
    }

    public void readFemales(){
        String FILE_NAME = "weiblich.csv";
        new BufferedReader(new InputStreamReader(this.getClass()
                .getResourceAsStream(FILE_NAME), Charset.defaultCharset()))
                .lines()
                .skip(1)
                .map(a -> new Surname(Gender.FEMALE, a))
                .forEach(em::merge);
    }
    */


}
