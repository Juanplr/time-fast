/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class FXMLFormularioPaquetesController implements Initializable {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private ImageView imgRegresar;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField tfNumeroGuia;
    @FXML
    private TextField tfPeso;
    @FXML
    private TextField tfAlto;
    @FXML
    private TextField tfAncho;
    @FXML
    private TextField tfProfundidad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regresarPrincipal(MouseEvent event) {
    }


    @FXML
    private void onClickGuardar(ActionEvent event) {
    }

    @FXML
    private void onClickGuardar(MouseEvent event) {
    }

    @FXML
    private void irCerrarSesion(MouseEvent event) {
    }
    
}
