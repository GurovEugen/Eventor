import java.io.Serializable;



import javax.persistence.*;



@Entity
@Table(name = "\"groups\"")
public class Group implements Serializable {
    @Id
    @Column(name = "\"id\"")
    private Integer id;
    @Column(name = "\"name\"")
    private String name;

    public Integer getId(){ return id;}

    public void setId(Integer sID) {
        id = sID;
    }

    public String getName() {
        return name;
    }

    public void setName(String sName) {
        name = sName;
    }

}