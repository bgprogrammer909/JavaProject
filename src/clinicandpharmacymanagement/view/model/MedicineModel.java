/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.view.model;

import java.util.Date;
import view.model.MedicineModel;


/**
 *
 * @author AngkitKharel
 */
public class MedicineModel {
    private String  medicinename;
    private int unit;
    private Date expirydate;
    private String id;
    private String price;
    
  public MedicineModel( String medicinename,int unit, Date expiry date, String id, String price) {
        this.medicinename = medicinename;
        this.unit = unit;
        this.expirydate = expirydate;
        this.id = id;
        this.price = price;
    }
   public void setmedicinename(String name){
        this.medicinename=medicinename;
    }
    
     public void setUnit(int unit){
        this.unit=unit;
    }
    
     public void setexpirydate(Date expirydate){
        this.expirydate=expirydate;
    }
     
    public String getmedicinename(){
        return this.medicinename;
    }
    
    public int getunit(){
        return this.unit;
    }
    
    public Date getexpirydate(){
        return this.expirydate;
    }
    
       public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public void setprice(String id){
        this.price = price;
    }
     public String getprice(){
      return price;   
    }
     
}
         
     