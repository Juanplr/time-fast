/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio;

import clienteescritorio.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class FXMLInicioSesionController implements Initializable {

    @FXML
    private TextField tfNumeroPersonal;
    @FXML
    private PasswordField pfContrasenia;
    @FXML
    private Button btnEntrar;
    @FXML
    private Label lbOlvidasteContraseña;
    @FXML
    private Label errorNumeroPersonal;
    @FXML
    private Label errorContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void irPantallaPrincipal(){
        try {
            Stage escenarioBase = (Stage) errorNumeroPersonal.getScene().getWindow();
            
            Parent principal = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));
            Scene escenaPrincipal = new Scene(principal);
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.setTitle("Time-Fast");
            escenarioBase.show();
        } catch (IOException ex) {
           // Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
           Utilidades.mostrarAlertaSimple("Error", "No podemos ir a la pantalla principal :(", Alert.AlertType.ERROR);
        }   
    }
    
    
    private boolean validarCampos(String correo, String contrasena){
        boolean camposValidos = true;
        errorNumeroPersonal.setText("");
        errorContraseña.setText("");

        if(correo.isEmpty()){
            camposValidos = false;
            errorNumeroPersonal.setText("Numero Personal boligatorio");
        }
        if(contrasena.isEmpty()){
            camposValidos = false;
            errorContraseña.setText("Contraseña obligatoria");
        }
        return camposValidos;
    }
    
    @FXML
    private void iniciarSesion(ActionEvent event) {
         String correo = tfNumeroPersonal.getText();
         String contrasena = pfContrasenia.getText();
        
        if(validarCampos(correo, contrasena)){
            irPantallaPrincipal();
        } 
    }
    
}