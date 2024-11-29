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
public class FXMLFormularioColaboradoresController implements Initializable {

    @FXML
    private ImageView imgCerrarSesion;
    @FXML
    private ImageView imgRegresar;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCurp;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfNumeroPersonal;
    @FXML
    private TextField tfContraseña;
    @FXML
    private TextField tfRol;
    @FXML
    private ImageView imgFotografia;
    @FXML
    private TextField tfVehiculo;
    @FXML
    private TextField tfNoLicencia;
    @FXML
    private Button btnGuardar;

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
    private void subirFotografia(MouseEvent event) {
    }

    @FXML
    private void onClickGuardar(ActionEvent event) {
    }

    @FXML
    private void irCerrarSesion(MouseEvent event) {
    }
    
}
