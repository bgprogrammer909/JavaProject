

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;


import clinicandpharmacymanagement.Dao.UserDao;
import clinicandpharmacymanagement.view.Registration;
import clinicandpharmacymanagement.view.model.UserData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import marksapp.java.view.LoginView;


/**
 *
 * @author ACER
 */
public class RegisterController {
    Registration view=new Registration();
    public RegisterController(Registration view){ 
        this.view=view;
        RegisterUser reg=new RegisterUser();
        this.view.register(reg);
}
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    class RegisterUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name=view.getNameTextField().getText();
            String email=view.getEmailTextField().getText();
            String password=String.valueOf(view.getPasswordField().getPassword());
            String utype="patient";
            if (name.isEmpty() || email.isEmpty()|| password.isEmpty()){
                JOptionPane.showMessageDialog(view, "Fill all the fields");
            } else{
            UserDao userDao=new UserDao();
            UserData user=new UserData(name,email,password,utype);
            boolean result=userDao.register(user);
            if (result){
                JOptionPane.showMessageDialog(view,"REGISTER SUCESS!!!!!");
                close();
                LoginView see=new LoginView();
                LoginController lcon=new LoginController(see);
                lcon.open();
            }else{
             JOptionPane.showMessageDialog(view,"REGISTER GONE WRONG!!!!!");   
            }
        }
        }
        
    }
}