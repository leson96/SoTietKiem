package model;
// Generated 11-Apr-2018 22:52:47 by Hibernate Tools 4.3.1



/**
 * Group generated by hbm2java
 */
public class Group  implements java.io.Serializable {


     private int idGroup;
     private String tenGroup;

    public Group() {
    }

    public Group(int idGroup, String tenGroup) {
       this.idGroup = idGroup;
       this.tenGroup = tenGroup;
    }
   
    public int getIdGroup() {
        return this.idGroup;
    }
    
    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
    public String getTenGroup() {
        return this.tenGroup;
    }
    
    public void setTenGroup(String tenGroup) {
        this.tenGroup = tenGroup;
    }




}

