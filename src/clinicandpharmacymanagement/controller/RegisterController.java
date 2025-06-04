

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.controller;


import clinicandpharmacymanagement.Dao.UserDao;
import clinicandpharmacymanagement.view.LoginView;
import clinicandpharmacymanagement.view.Registration;
import clinicandpharmacymanagement.view.model.UserData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;



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
        ShowPasswordControl pcon=new ShowPasswordControl();
        this.view.showPassword(pcon);
//        this.view.showPassword(reg);
}
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    class ShowPasswordControl implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO add your handling code here:
        if (view.getCheckBox().isSelected()){
            view.getPasswordField().setEchoChar((char)0);   
        }
        else{
             view.getPasswordField().setEchoChar('*');
        }
        }
//        // TODO add your handling code here:
//        if (view.getCheckBox().isSelected()){
//            view.getPasswordFeild().setEchoChar((char)0);   
//        }
//        else{
//            regPassword.setEchoChar('*');
//        }
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