/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package clinicandpharmacymanagement.Dao;

import clinicandpharmacymanagement.view.model.DoctorModel;
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
public class DoctordaoTest {
    
    public DoctordaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println ("setup class");    }
    
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
     * Test of addDoctor method, of class Doctordao.
     */
    @Test
    public void testAddDoctor() {
        System.out.println("addDoctor");
        DoctorModel model = null;
        Doctordao instance = new Doctordao();
        boolean expResult = false;
        boolean result = instance.addDoctor(model);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
