import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "\"auth\"")
public class AuthUser implements Serializable {
  @Id
  @Column(name = "\"id_user\"")
  private Integer id;
  @Column(name = "\"login\"", unique = true)
  private String login;
  @Column(name = "\"password\"")
  private String password;

  public Integer getId() {
    return id;
  }
  
  public void setId(Integer sID) {
    id = sID;
  }  
  
  public String getLogin() {
    return login;
  }
  
  public void setLogin(String userLogin) {
    login = userLogin;
  }

  public String getPassword(){
    return password;
  }

  public void setPassword(String pass){
    password = pass;
  }

}