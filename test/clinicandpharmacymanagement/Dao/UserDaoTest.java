/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.view.model.LoginRequest;
import clinicandpharmacymanagement.view.model.ResetPasswordRequest;
import clinicandpharmacymanagement.view.model.UserData;
import java.util.List;
import org.junit.*;


/**
 *
 * @author AngkitKharel
 */
public class UserDaoTest {
    String name="Angkit";
    String email = "angkitkharel1234@gmail.com";
    String password ="angkit123@";
    String role="patient";
    
    @Test
    public void registerUser(){
//        String name, String email, String password, String utype
        UserData user = new UserData(name,email,password,role);
        UserDao dao = new UserDao();
        boolean result= dao.register(user);
        Assert.assertTrue("Should register",result);
    }
    
    @Test
    public void registerWithDuplicate(){
        UserData user = new UserData(name,email,password,role);
        UserDao dao = new UserDao();
        boolean result= dao.register(user);
        Assert.assertFalse("Register should fail for duplicate",result);
    }
    
    @Test
    public void loginUser(){
        LoginRequest req = new LoginRequest(email,password);
        UserDao dao = new UserDao();
        UserData user= dao.login(req);
        Assert.assertNotNull("Login should retrieve user",user);
        Assert.assertEquals("Name should match",name,user.getName());        
        Assert.assertEquals("Email should match",email,user.getEmail());        
        Assert.assertEquals("Password should match",password,user.getPassword());
    }
    @Test
    public void loginWithWrongCreds(){
        LoginRequest req = new LoginRequest(email,"angfd");
        UserDao dao = new UserDao();
        UserData user= dao.login(req);
        Assert.assertNull("Login should retrieve user",user);
    }
    
}
