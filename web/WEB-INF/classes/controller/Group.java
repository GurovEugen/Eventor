import java.io.Serializable;


//JPA (Begin)
import javax.persistence.*;
//JPA (End)   


@Entity
@Table(name = "\"groups\"")
public class Group implements Serializable
{


    @Id
    @Column(name = "\"id\"")
    private Integer id;

    @Column(name = "\"name\"")
    private String name;



    public Integer getGroupId() {return this.id;}

    public void setGroupId(Integer sID) {id = sID;}

    public String getGroupName() {return name;}

    public void setGroupName(String sName) {name = sName;}

}