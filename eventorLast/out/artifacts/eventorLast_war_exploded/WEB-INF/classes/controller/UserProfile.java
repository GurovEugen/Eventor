import java.io.Serializable;
import java.util.Date;

//JPA (Begin)
import javax.persistence.*;
//JPA (End)     

@Entity
@Table(name = "\"users\"")
public class UserProfile implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorUserId")
  @SequenceGenerator(name = "generatorUserId", sequenceName = "user_id_seq",  allocationSize=1)
  @Column(name = "\"id\"")
  private Integer id;
  @Column(name = "\"first_name\"")
  private String first_name;
  @Column(name = "\"last_name\"")
  private String last_name;
  @Column(name = "\"date_of_birth\"")
  private Date date_of_birth;
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

  public String getFirstName() {
    return first_name;
  }

  public void setFirstName(String firstName) {
    first_name = firstName;
  }

  public String getLastName(){
    return last_name;
  }

  public void setLastName(String lastName){
    last_name = lastName;
  }

  public Date getDateOfBirth(){
    return date_of_birth;
  }

  public void setDateOfBirth(Date date){
    date_of_birth = date;
  }

  public String getGender(){
    return gender;
  }

  public void setGender(String genderStr){
    gender = genderStr;
  }

  public String getBio(){
    return bio;
  }

  public void setBio(String biography){
    bio = biography;
  }

  public String getSeqName(){ return "user_id_seq"; }

}