import java.io.Serializable;

   

import javax.persistence.*;
  
  

@Entity
@Table(name = "\"event\"")
public class Event implements Serializable
{
  @Id
  @Column(name = "\"id\"")
  private Integer id;
  @Column(name = "\"name\"")
  private String name;
  @Column(name = "\"date\"")
  private String date;
  @Column(name = "\"info\"")
  private String info;
  @Column(name = "\"place\"")
  private String place;
  @Column(name = "\"group_id\"")
  private Integer groupId;
  @Column(name = "\"user_id\"")
  private Integer userId;

  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer sID) {
    id = sID;
  }  

    public Integer getUserId() {
    return userId;
  }
  
  public void setUserId(Integer sUserId) {
    userId = sUserId;
  } 
    public Integer getGroupId() {
    return groupId;
  }
  
  public void setGroupId(Integer sGroupId) {
    groupId = sGroupId;
  } 
  
  public String getDate() {
    return date;
  }
  
  public void setDate(String sDate) {
    date = sDate;
  }

    public String getName() {
    return name;
  }
  
  public void setName(String sName) {
    name = sName;
  } 

    public String getPlace() {
    return place;
  }
  
  public void setPlace(String sPlace) {
    place = sPlace;
  } 

    public String getInfo() {
    return info;
  }
  
  public void setInfo(String sInfo) {
    info = sInfo;
  } 

}