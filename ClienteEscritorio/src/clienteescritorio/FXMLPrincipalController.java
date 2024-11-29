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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class FXMLPrincipalController implements Initializable {

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
            Stage escenarioBase = (Stage) moduloColaboradores.getScene().getWindow();
                    
            Parent principal = FXMLLoader.load(getClass().getResource("FXMLModuloColaboradores.fxml"));
            Scene escenaPrincipal = new Scene(principal);
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.setTitle("Time-Fast Colaboradores");
            escenarioBase.show();
        } catch (IOException ex) {
            // Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.mostrarAlertaSimple("Error", "No podemos ir al modulo Colaboradores :(", Alert.AlertType.ERROR);
        }
    }
    
    private void moduloUnidades(){
        try{
            Stage escenarioBase = (Stage) moduloUnidades.getScene().getWindow();
            
            Parent principal = FXMLLoader.load(getClass().getResource("FXMLModuloUnidades.fxml"));
            Scene escenaPrincipal = new Scene(principal);
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.setTitle("Time-Fast Unidades");
            escenarioBase.show();
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error", "No podemos ir al modulo Unidades", Alert.AlertType.ERROR);
        }
    }
    
    private void moduloClientes(){
        try{
            Stage escenarioBase = (Stage) moduloClientes.getScene().getWindow();
            
            Parent principal = FXMLLoader.load(getClass().getResource("FXMLModuloClientes.fxml"));
            Scene escenaPrincipal = new Scene(principal);
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.setTitle("Time-Fast Clientes");
            escenarioBase.show();
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error", "No podemos ir al modulo Clientes", Alert.AlertType.ERROR);
        }
    }
    
    
    private void moduloEnvios(){
        try{
            Stage escenarioBase = (Stage) moduloUnidades.getScene().getWindow();
            
            Parent principal = FXMLLoader.load(getClass().getResource("FXMLModuloEnvios.fxml"));
            Scene escenaPrincipal = new Scene(principal);
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.setTitle("Time-Fast Envios");
            escenarioBase.show();
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error", "No podemos ir al modulo Envios", Alert.AlertType.ERROR);
        }
    }
    
    private void moduloPaquetes(){
        try{
            Stage escenarioBase = (Stage) moduloUnidades.getScene().getWindow();
            
            Parent principal = FXMLLoader.load(getClass().getResource("FXMLModuloPaquetes.fxml"));
            Scene escenaPrincipal = new Scene(principal);
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.setTitle("Time-Fast Paquetes");
            escenarioBase.show();
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error", "No podemos ir al modulo Paquetes", Alert.AlertType.ERROR);
        }
    }
    
    
    
    @FXML
    private void irModuloColaboradores() {
        moduloColaboradores();
    }

    @FXML
    private void irModuloPaquetes(MouseEvent event) {
        moduloPaquetes();
    }

    @FXML
    private void irModuloUnidades(MouseEvent event) {
        moduloUnidades();
    }

    @FXML
    private void irModuloClientes(MouseEvent event) {
        moduloClientes();
    }

    @FXML
    private void irModuloEnvios(MouseEvent event) {
        moduloEnvios();
    }

}
