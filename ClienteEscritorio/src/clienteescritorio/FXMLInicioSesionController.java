/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio;

import clienteescritorio.dao.LoginDAO;
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
import pojo.Colaborador;

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

    private void irPantallaPrincipal(Colaborador colaborador){
        try {
            Stage escenarioBase = (Stage) errorNumeroPersonal.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPrincipal.fxml"));
            Parent principal = loader.load() ;
            
            FXMLPrincipalController controlador = loader.getController();
            controlador.setColaborador(colaborador);
            Scene escenaPrincipal = new Scene(principal);
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.setTitle("Time-Fast");
            escenarioBase.show();
        } catch (IOException ex) {
           // Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
           Utilidades.mostrarAlertaSimple("Error", "No podemos ir a la pantalla principal :(", Alert.AlertType.ERROR);
        }   
    }
    
    
    private boolean validarCampos(String noPersonal, String contrasena){
        boolean camposValidos = true;
        errorNumeroPersonal.setText("");
        errorContraseña.setText("");

        if(noPersonal.isEmpty()){
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
         String noPersonal = tfNumeroPersonal.getText();
         String contrasena = pfContrasenia.getText();
        
        if(validarCampos(noPersonal, contrasena)){
            verificarCredenciales(noPersonal, contrasena);
        } 
    }
    private void verificarCredenciales(String noPersonal, String password){
        Colaborador respuesta = LoginDAO.iniciarSesion(noPersonal, password);
        if(respuesta != null){
            Utilidades.mostrarAlertaSimple("Bienvenido", "Bienvenido " + respuesta.getNombre(), Alert.AlertType.INFORMATION);
            irPantallaPrincipal(respuesta);
        }else{
            Utilidades.mostrarAlertaSimple("Atención", "Numero Personal y/o Contraseña incorrecta", Alert.AlertType.ERROR);
        }
    }
    
}
