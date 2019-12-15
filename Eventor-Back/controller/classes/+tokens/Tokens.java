package controller;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "\"tokens\"")
public class Tokens implements Serializable{
    @Id
    @Column(name = "\"id_user\"")
    private Integer id_user;
    @Column(name = "\"token\"")
    private String token;
    @Column(name = "\"expires_in\"")
    private Date expires_in;

    public Integer getId() {
        return id_user;
    }

    public void setId(Integer sID) {
        id_user = sID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String sToken) {
        token = sToken;
    }

    public Date getExpires_in(){
        return expires_in;
    }

    public void setExpires_in( Date newExpiresIn) {
        expires_in = newExpiresIn;
    }
}