

import java.io.Serializable;

   
//JPA (Begin)
import javax.persistence.*;
//JPA (End)   
  

@Entity
@Table(name = "\"event\"")
public class Event implements Serializable
{

  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq_gen")
  @SequenceGenerator(name = "event_seq_gen", sequenceName = "event_id_seq",  allocationSize=1, initialValue = 5)
  @Id
  @Column(name = "\"id\"")
  private Integer id;

  @Column(name = "\"name\"")
  private String name;

  @Column(name = "\"info\"")
  private String info;

  @Column(name = "\"place\"")
  private String place;

  @Column(name = "\"start_date\"")
  private String start_date;

  @Column(name = "\"end_date\"")
  private String end_date;

  @Column(name = "\"group_id\"")
  private Integer group_id;

  @Column(name = "\"user_id\"")
  private Integer user_id;




  public Integer getEventId() {return id;}
  
  public void setEventId(Integer sID) {id = sID;}

  public String getEventName() {return name;}

  public void setEventName(String sName) {name = sName;}

  public String getEventInfo() {return info;}

  public void setEventInfo(String sInfo) {info = sInfo;}

  public String getEventPlace() {return place;}

  public void setEventPlace(String sPlace) {place = sPlace;}

  public String getEventStartDate() {return start_date;}

  public void setEventStartDate(String sDate) {start_date = sDate;}

  public String getEventEndDate() {return end_date;}

  public void setEventEndDate(String eDate) {end_date = eDate;}
  
  public Integer getEventGroupId() {return group_id;}

  public void setEventGroupId(Integer sGroupId) {group_id = sGroupId;}

  public Integer getEventUserId() {return user_id;}

  public void setEventUserId(Integer sUserId) {user_id = sUserId;}
}