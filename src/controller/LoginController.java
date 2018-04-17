/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import hibernate.HibernateUtil;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Khachhang;
import model.Nhanvien;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.sql.ordering.antlr.Factory;

/**
 * FXML Controller class
 *
 * @author vinh
 */
public class LoginController implements Initializable {

    @FXML TextField txtTenDN;
    @FXML TextField txtPass;
    @FXML Button btnLogin;
    @FXML VBox vbox;
    
    private SessionFactory factory;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //vbox.requestFocus();
        btnLogin.setOnAction(e ->{
            xuLyDangNhap();
            ((Stage)((Node)e.getSource()).getScene().getWindow()).close();        
        });
        vbox.setOnKeyPressed(e -> {
            if ( e.getCode() == KeyCode.ENTER) {
            xuLyDangNhap();
        }
        });
//        txtTenDN.setOnKeyPressed(e ->{
//            String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
//        });
    }        
    public void xuLyDangNhap(){
         try{
                
                 factory = HibernateUtil.getSessionFactory();        
//                LoginId idLogin = new LoginId()
                //Login iData = new Login
                Session session = this.factory.openSession();
                Transaction tx = session.beginTransaction();
                
                Nhanvien user = (Nhanvien) session.get(Nhanvien.class, this.txtTenDN.getText());                
                
                if(user != null && (user.getPass().equals(txtPass.getText())))
                {
                    Parent root = FXMLLoader.load(getClass().getResource("/sotietkiemapp/MainFX.fxml"));                
                    root.setUserData(user);

                    Scene scene = new Scene(root);
                    Stage stage =new Stage();
                    stage.setScene(scene);
                    stage.show();

                    //An form
                               
                }
                
                else{
                    //thong bao lo dang nhap
                    Alert al = new Alert(Alert.AlertType.ERROR,"Đăng nhập thất bại",ButtonType.OK);
                    al.setHeaderText(null);
                    al.show();
                }
                tx.commit();
                session.close();
            }
            catch (IOException ex){
                Logger.getLogger(MainFXController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
//    public void findUser(){
//        Session session = this.factory.openSession();
//        Transaction tx = session.beginTransaction();
//        
//        //String hqlUpdate = "select user, pass, idGroup from login where user = :user ";
//        // or String hqlUpdate = "update khachhang set IsActive = 0 where maKH = :maKH";
////        int updatedEntities = session.createQuery( hqlUpdate )
////                .setString("user",txtTenDN.getText())
////                .executeUpdate();
//        
////        Query query = session.createQuery("select user, pass, idGroup from login where user = :user ");
////        query.setParameter("user",txtTenDN.getText() );
////        Login list = query.list();
//        Login user = (Login) session.get(Login.class, this.txtTenDN.getText());
//        
//        tx.commit();
//        session.close();
//    }
//    
}
