package application;


import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class girisController {

    @FXML
    private JFXPasswordField passtext;

    @FXML
    private JFXTextField usertext;{
    
    }
    
    @FXML
    private FontAwesomeIcon carpı;
    
    @FXML
    private JFXButton giris;

    @FXML
    private JFXButton kayıt;
    
    public void close() {
        System.exit(0);
    }

    private void renkDegistir(JFXButton button, String hoverColor) {
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            button.setStyle("-fx-background-color: " + hoverColor + ";");
        });
        
        button.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            button.setStyle("-fx-background-color: #14b1d4;");
        });
        
      }
    
    @FXML
    private void initialize() {
        renkDegistir( giris, "red");
         renkDegistir(kayıt,"red");
         
    }

    
    
    
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void adminLogin() {
        String sql = "SELECT * FROM admin WHERE kisim = ? and ksifre = ?";
        connect = Database.connectDb();
        try {
            Alert alert;

            if (usertext.getText().isEmpty() || passtext.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("KULLANICI ADI VEYA ŞİFRE BOŞ GİRİLEMEZ");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, usertext.getText());
                prepare.setString(2, passtext.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Login successful");

                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("anasayfa.fxml"));
                    Scene scene = new Scene(root, 1300, 780);
                    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                    primaryStage.setScene(scene);
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    primaryStage.show();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("YANLIŞ KULLANICI ADI VEYA YANLIŞ ŞİFRE");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
        
    public void registerUser() {
        String sql = "INSERT INTO admin (kisim, ksifre) VALUES (?, ?)";
        connect = Database.connectDb();
        try {
            Alert alert;

            if (usertext.getText().isEmpty() || passtext.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("KULLANICI ADI VEYA ŞİFRE BOŞ OLAMAZ");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, usertext.getText());
                prepare.setString(2, passtext.getText());
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("KAYIT BAŞARILI");
                    // Gerekirse, kayıt işleminden sonra yapılması gereken diğer işlemleri burada gerçekleştirebilirsiniz.

                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
  
}

