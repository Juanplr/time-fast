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
public class FXMLFormularioEnviosController implements Initializable {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private ImageView imgRegresar;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfNumero;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private TextField tfCliente;
    @FXML
    private TextField tfNumeroGuia;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfEstado;
    @FXML
    private TextField tfCobductor;
    @FXML
    private TextField tfCostoEnvio;
    @FXML
    private TextField tfEstadoEnvio;

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
    
}
