/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;

import clinicandpharmacymanagement.Dao.PatientDao;
import clinicandpharmacymanagement.view.model.PatientModel;

/**
 *
 * @author user
 */
public class PatientController {
    private PatientDao patientDao;

    public PatientController() {
        patientDao = new PatientDao();
    }

    // Insert patient using DAO
    public boolean addPatient(PatientModel patientModel) {
        return patientDao.insertPatient(patientModel);
    }
}
