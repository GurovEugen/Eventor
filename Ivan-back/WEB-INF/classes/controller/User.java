import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.persistence.*;



@Entity
@Table(name = "\"users\"")
public class User implements Serializable {
    @Id
    @Column(name = "\"id\"")
    private Integer id;
    @Column(name = "\"first_name\"")
    private String firstName;
    @Column(name = "\"last_name\"")
    private String lastName;
    @Column(name = "\"date_of_birth\"")
    private Date birthDate;
    @Column(name = "\"gender\"")
    private String gender;
    @Column(name = "\"bio\"")
    private String bio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer sID) {
        id = sID;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String sFirstName) { firstName = sFirstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String sLastName) { lastName = sLastName; }

    public Date getBirthDate() { return birthDate; }

    public void setBirthDate(Date sBirthDate) { birthDate = sBirthDate; }

    public String getGender() { return gender; }

    public void setGender(String sGender) { gender = sGender; }

    public String getBio() { return bio; }

    public void setBio(String sBio) { bio = sBio; }



}
