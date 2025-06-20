/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.view.model.LoginRequest;
import clinicandpharmacymanagement.view.model.ResetPasswordRequest;
import clinicandpharmacymanagement.view.model.UserData;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author AngkitKharel
 */
public class UserDaoTest {

    public UserDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Setup class");
    }

    @AfterAll
    public static void tearDownClass() {

    }

    @BeforeEach
    public void setUp() {
        System.out.println("Setup before each class");

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of register method, of class UserDao.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        UserData user = null;
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.register(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class UserDao.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        LoginRequest loginReq = null;
        UserDao instance = new UserDao();
        UserData expResult = null;
        UserData result = instance.login(loginReq);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkEmail method, of class UserDao.
     */
    @Test
    public void testCheckEmail() {
        System.out.println("checkEmail");
        String email = "";
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.checkEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetPassword method, of class UserDao.
     */
    @Test
    public void testResetPassword() {
        System.out.println("resetPassword");
        ResetPasswordRequest reset = null;
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.resetPassword(reset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUsers method, of class UserDao.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        UserDao instance = new UserDao();
        List<UserData> expResult = null;
        List<UserData> result = instance.getAllUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
