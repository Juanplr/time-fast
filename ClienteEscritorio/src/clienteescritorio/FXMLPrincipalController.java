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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class FXMLPrincipalController implements Initializable {

    private ImageView irModuloColaboradores;
    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private ImageView moduloPaquetes;
    @FXML
    private ImageView moduloUnidades;
    @FXML
    private ImageView moduloClientes;
    @FXML
    private ImageView moduloColaboradores;
    @FXML
    private ImageView moduloEnvios;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    private void moduloColaboradores(){
        try {
            Stage escenarioBase = (Stage) irModuloColaboradores.getScene().getWindow();
                    
            Parent principal = FXMLLoader.load(getClass().getResource("FXMLModuloColaboradores.fxml"));
            Scene escenaPrincipal = new Scene(principal);
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.setTitle("Time-Fast Colaboradores");
            escenarioBase.show();
        } catch (IOException ex) {
            // Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.mostrarAlertaSimple("Error", "No podemos ir a la pantalla principal :(", Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void irModuloColaboradores() {
        moduloColaboradores();
    }

}
