package controller;

import java.io.Serializable;

//JPA (Begin)
import javax.persistence.*;
//JPA (End)     

@Entity
@Table(name = "\"auth\"")
public class authOperations implements Serializable
{

  @Id
  @Column(name = "\"id\"")
  private Integer id;
  @Column(name = "\"login\"")
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

  public void setPassword(String passs){
    password = passs;
  }

}