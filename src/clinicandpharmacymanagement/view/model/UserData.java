/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.view.model;

/**
 *
 * @author ACER
 */
public class UserData {
    // private attributes
    //public metthod
    private String name;
    private String email;
    private String password;
    private String id;
    private String utype;

    public UserData( String id,String name, String email, String password, String utype) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
        this.utype = utype;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

 

    public UserData(String name, String email, String password, String id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
    }
    
    public UserData(String name,String email, String password){
        this.name=name;
        this.email=email;
        this.password=password;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
     public void setEmail(String email){
        this.email=email;
    }
    
     public void setPassword(String password){
        this.password=password;
    }
     
    public String getName(){
        return this.name;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
}
