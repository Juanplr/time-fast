/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio;

import clienteescritorio.dao.EnvioDAO;
import clienteescritorio.observador.NotificadoOperacion;
import clienteescritorio.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pojo.Colaborador;
import pojo.Envio;
import pojo.Mensaje;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class FXMLModuloEnviosController implements Initializable, NotificadoOperacion {
    
    private ObservableList<Envio> envios;
    
    @FXML
    private ImageView imgRegresar;
    @FXML
    private TextField tfBuscar;
    @FXML
    private TableView<Envio> tvTablaEnvios;
    @FXML
    private TableColumn colNoGuia;
    @FXML
    private TableColumn colOrigen;
    @FXML
    private TableColumn colDestino;
    @FXML
    private TableColumn colConductor;
    @FXML
    private TableColumn colCliente;
    @FXML
    private TableColumn colEstado;

    /**
     * Initializes the controller class.
     */
    
    private Colaborador colaboradorLoguiado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarLaInformacion();
    }
    
    public void inizializar(Colaborador colaboradorLoguiado) {
        this.colaboradorLoguiado = colaboradorLoguiado;
    }
    
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
    private void configurarTabla() {
           colNoGuia.setCellValueFactory(new PropertyValueFactory("noGuia"));
           colOrigen.setCellValueFactory(new PropertyValueFactory("origen"));
           colDestino.setCellValueFactory(new PropertyValueFactory("destino"));
           colConductor.setCellValueFactory(new PropertyValueFactory("colaborador"));
           colCliente.setCellValueFactory(new PropertyValueFactory("cliente"));
           colEstado.setCellValueFactory(new PropertyValueFactory("estadoDeEnvio"));
    }

    private void cargarLaInformacion() {
           envios = FXCollections.observableArrayList();
           List<Envio> lista = EnvioDAO.obtenerEnvios();
           
           if (lista != null) {
               envios.addAll(lista);
               tvTablaEnvios.setItems(envios);
           }else{
               Utilidades.mostrarAlertaSimple("ERROR", "Lo sentimos por el momento no se puede cargar la informacion"
                       + "de los Colaboradores, por favor intentélo mas tarde", Alert.AlertType.ERROR);
               cerrarVentana();
           }

    }

    private void cerrarVentana(){
            ((Stage) tfBuscar.getScene().getWindow()).close();
    }
    
    private void irAFormulario(NotificadoOperacion observador, Envio envio){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormularioEnvios.fxml"));
            Parent root = loader.load();
            
            FXMLFormularioEnviosController controlador = loader.getController();
            controlador.initializeValores(observador, envio, colaboradorLoguiado);
            
            Stage ecena = new Stage();
            Scene ecenario = new Scene(root);
            ecena.setScene(ecenario);
            ecena.setTitle("Formulario Envios");
            ecena.initModality(Modality.APPLICATION_MODAL);
            ecena.showAndWait();
        } catch (Exception ex) {
            Logger.getLogger(FXMLModuloColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void regresarPrincipal(MouseEvent event) {
        irPantallaPrincipal();
    }


    @FXML
    private void irBuscar(MouseEvent event) {
         if(!tfBuscar.getText().isEmpty()){
            String noGuia = tfBuscar.getText();
            buscarEnvio(noGuia);
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Campo de buscar Vacio", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void irRegistrarEnvio(MouseEvent event) {
        irAFormulario(this, null);
    }

    @FXML
    private void irEliminarEnvio(MouseEvent event) {
        Envio envio = tvTablaEnvios.getSelectionModel().getSelectedItem();
        if(envio!= null){
            Mensaje mensaje = EnvioDAO.eliminarEnvio(envio.getIdEnvio());
            if(!mensaje.isError()){
                Utilidades.mostrarAlertaSimple("Correcto", "Cliente Eliminado correctamente", Alert.AlertType.INFORMATION);
                cargarLaInformacion();
            }else{
                Utilidades.mostrarAlertaSimple("Error", mensaje.getMensaje(), Alert.AlertType.ERROR);
            }
            
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Selecciona un cliente", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void irEditarEnvio(MouseEvent event) {
        Envio envio = tvTablaEnvios.getSelectionModel().getSelectedItem();
        if(envio!= null){
            irAFormulario(this, envio);
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Selecciona un Envio", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void notificarOperacion(String tipo, String nombre) {
        cargarLaInformacion();
    }

    private void buscarEnvio(String noGuia) {
        envios.clear();
        tvTablaEnvios.setItems(envios);
        List<Envio> lista = EnvioDAO.obtenerEnviosPorNoGuia(noGuia);
        if (lista!=null && !lista.isEmpty()) {
            envios.addAll(lista);
            tvTablaEnvios.setItems(envios);
        }else{
            Utilidades.mostrarAlertaSimple("Aviso", "No se encontro el Envio", Alert.AlertType.WARNING);
            cargarLaInformacion();
        }
        
    }
    
}
