package clienteescritorio;

import clienteescritorio.dao.PaqueteDAO;
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
import pojo.Mensaje;
import pojo.Paquete;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author reyes
 */
public class FXMLModuloPaquetesController implements Initializable, NotificadoOperacion {

    @FXML
    private ImageView imgRegresar;
    @FXML
    private TextField ftBuscar;
    @FXML
    private ImageView imgBuscar;
    @FXML
    private TableView<Paquete> tvTablaPaquetes;
    @FXML
    private TableColumn colNoGuia;
    @FXML
    private TableColumn colDimensiones;
    @FXML
    private TableColumn colDescripcion;
    @FXML
    private TableColumn colPeso;
    
    private ObservableList<Paquete> paquetes;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabla();
        cargarLaInformacion();
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
    
    @FXML
    private void regresarPrincipal(MouseEvent event) {
        irPantallaPrincipal();
    }

    @FXML
    private void irRegistrarPaquete(MouseEvent event) {
        irAFormulario(this, null);
    }

    @FXML
    private void irEliminarPaquete(MouseEvent event) {
        Paquete paquete = tvTablaPaquetes.getSelectionModel().getSelectedItem();
        if(paquete!= null){
            Mensaje mensaje = PaqueteDAO.eliminarPaquete(paquete.getIdPaquete());
            if(!mensaje.isError()){
                Utilidades.mostrarAlertaSimple("Correcto", "Paquete Eliminada correctamente", Alert.AlertType.INFORMATION);
                cargarLaInformacion();
            }else{
                Utilidades.mostrarAlertaSimple("Error", mensaje.getMensaje(), Alert.AlertType.ERROR);
            }
            
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Selecciona una Unidad", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void irEditarPaquete(MouseEvent event) {
        Paquete paquete = tvTablaPaquetes.getSelectionModel().getSelectedItem();
        if(paquete!= null){
            irAFormulario(this, paquete);
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Selecciona un Paqute", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void notificarOperacion(String tipo, String nombre) {
        cargarLaInformacion();
    }
    
    private void configurarTabla() {
        colNoGuia.setCellValueFactory(new PropertyValueFactory("noGuia"));
        colDimensiones.setCellValueFactory(new PropertyValueFactory("dimensiones"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        colPeso.setCellValueFactory(new PropertyValueFactory("peso"));
    }

    private void cargarLaInformacion() {
           paquetes = FXCollections.observableArrayList();
           List<Paquete> lista = PaqueteDAO.obtenerPaquetes();
           if (lista != null) {
               paquetes.addAll(lista);
               tvTablaPaquetes.setItems(paquetes);
           }else{
               Utilidades.mostrarAlertaSimple("ERROR", "Lo sentimos por el momento no se puede cargar la informacion"
                       + "de las Unidades, por favor intentélo mas tarde", Alert.AlertType.ERROR);
               cerrarVentana();
           }
    }
    private void cerrarVentana(){
            ((Stage) ftBuscar.getScene().getWindow()).close();
    }
    
    private void irAFormulario(NotificadoOperacion observador, Paquete paquete){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFormularioPaquetes.fxml"));
            Parent root = loader.load();
            
            FXMLFormularioPaquetesController controlador = loader.getController();
            controlador.initializeValores(observador, paquete);
            
            Stage ecena = new Stage();
            Scene ecenario = new Scene(root);
            ecena.setScene(ecenario);
            ecena.setTitle("Formulario Paquetes");
            ecena.initModality(Modality.APPLICATION_MODAL);
            ecena.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FXMLModuloColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void irABuscar(MouseEvent event) {
        if(!ftBuscar.getText().isEmpty()){
            String noGuia = ftBuscar.getText();
            buscarPaquete(noGuia);
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Campo de buscar Vacio", Alert.AlertType.ERROR);
        }
    }

    private void buscarPaquete(String noGuia) {
        try {
            paquetes.clear();
            tvTablaPaquetes.setItems(paquetes);
            List<Paquete> lista = PaqueteDAO.obtenerPaquetesPorEnvio(0);
            if (!lista.isEmpty()) {
                paquetes.addAll(lista);
                tvTablaPaquetes.setItems(paquetes);
            }else{
                Utilidades.mostrarAlertaSimple("Aviso", "No se encontro la(s) Unidades", Alert.AlertType.WARNING);
                cargarLaInformacion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
