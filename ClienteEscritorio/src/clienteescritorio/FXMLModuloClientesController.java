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
public class FXMLModuloClientesController {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private ImageView imgRegresar;
    @FXML
    private TextField ftBuscar;
    @FXML
    private ImageView imgRegistrarCliente;
    @FXML
    private ImageView imgElminarCliente;
    @FXML
    private ImageView imgEditarCliente;

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
    private void irRegistrarCliente(MouseEvent event) {
    }

    @FXML
    private void irEliminarCliente(MouseEvent event) {
    }

    @FXML
    private void irEditarCliente(MouseEvent event) {
    }

    @FXML
    private void irCerrarSesion(MouseEvent event) {
    }

    @FXML
    private void irBuscar(MouseEvent event) {
    }
    
}
