package clienteescritorio;

import clienteescritorio.utilidades.Utilidades;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author reyes
 */
public class FXMLModuloPaquetesController {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private ImageView imgRegresar;
    @FXML
    private TextField ftBuscar;
    @FXML
    private ImageView imgBuscar;
    @FXML
    private ImageView imgRegistrarPaquete;
    @FXML
    private ImageView imgEliminarCliente;
    @FXML
    private ImageView imgEditarPaquete;

    public void irPantallaPrincipal(){
                try {
            Stage escenarioBase = (Stage) imgRegresar.getScene().getWindow();
                    
            Parent principal = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));
            Scene escenaPrincipal = new Scene(principal);
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.setTitle("Time-Fast Principal");
            escenarioBase.show();
        } catch (IOException ex) {
            // Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.mostrarAlertaSimple("Error", "No podemos ir a la pantalla principal :(", Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void regresarPrincipal(MouseEvent event) {
        irPantallaPrincipal();
    }

    @FXML
    private void irRegistrarPaquete(MouseEvent event) {
    }

    @FXML
    private void irEliminarPaquete(MouseEvent event) {
    }

    @FXML
    private void irEditarPaquete(MouseEvent event) {
    }

    @FXML
    private void imgCerrarSesion(MouseEvent event) {
    }
    
}
