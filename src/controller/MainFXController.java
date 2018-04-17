/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vinh
 */
public class MainFXController implements Initializable {
    @FXML private Button btnclose;
    @FXML private Label lblUser;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //An form main bằng cách sử dụng ID button
//        btnclose.setOnAction(e->{
//            try {
//                Parent root = FXMLLoader.load(getClass().getResource("/sotietkiemapp/KhachHangFX.fxml"));
//                
//                Scene scene = new Scene(root);
//                Stage stage =new Stage();
//                stage.setScene(scene);
//                stage.show();
//                
//                //An form
//                ((Stage)((Node)e.getSource()).getScene().getWindow()).hide();
//            } catch (IOException ex) {
//                Logger.getLogger(MainFXController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
    }    
    
    @FXML
     public void openKhachHang(ActionEvent e) throws IOException{     
            Parent root = FXMLLoader.load(getClass().getResource("/sotietkiemapp/KhachHangFX.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
            
    }
     //Mo form Gui Tien
     @FXML
     public void openGuiTien(ActionEvent e) throws IOException{     
            Parent root = FXMLLoader.load(getClass().getResource("/sotietkiemapp/GuiTien.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
            
    }
     @FXML
     public void openRutTien(ActionEvent e) throws IOException{     
            Parent root = FXMLLoader.load(getClass().getResource("/sotietkiemapp/RutTien.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
            
    }
     @FXML
     public void openSoTietKiem(ActionEvent e) throws IOException{     
            Parent root = FXMLLoader.load(getClass().getResource("/sotietkiemapp/SoTietKiem.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
            
    }
     @FXML
     public void openTinhLai(ActionEvent e) throws IOException{     
            Parent root = FXMLLoader.load(getClass().getResource("/sotietkiemapp/TinhLai.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
            
    }
     @FXML
     public void openCaiDat(ActionEvent e) throws IOException{     
            Parent root = FXMLLoader.load(getClass().getResource("/sotietkiemapp/CaiDat.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();
            
    }
}
