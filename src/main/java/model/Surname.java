package model;

import javax.persistence.*;
import javax.ws.rs.Path;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "countAll",query = "select count(s) from Surname s"),
        @NamedQuery(name = "countAllMales",query = "select count (s) from Surname s where s.gender=:MALE"),
        @NamedQuery(name = "countAllFemales", query = "select count (s) from Surname s where s.gender=:FEMALE"),
        @NamedQuery(name = "Find.ById",query = "select s from Surname s where s.Id=:ID")
})
public class Surname {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String firstname;

    //region Konstruktoren
    public Surname() {
    }

    public Surname(Gender gender, String firstname) {
        this.gender = gender;
        this.firstname = firstname;
    }

    //endregion

    //region Getter und Setter
    public Long getId() {
        return Id;
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    //endregion
}
