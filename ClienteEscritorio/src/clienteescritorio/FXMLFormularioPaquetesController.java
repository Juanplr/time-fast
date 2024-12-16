/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio;

import clienteescritorio.dao.EnvioDAO;
import clienteescritorio.dao.PaqueteDAO;
import clienteescritorio.observador.NotificadoOperacion;
import clienteescritorio.utilidades.Utilidades;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pojo.Envio;
import pojo.Mensaje;
import pojo.Paquete;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class FXMLFormularioPaquetesController implements Initializable {

    @FXML
    private ImageView imgRegresar;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField tfPeso;
    @FXML
    private TextField tfAlto;
    @FXML
    private TextField tfAncho;
    @FXML
    private TextField tfProfundidad;
    @FXML
    private ComboBox<Envio> cbEnvios;

    /**
     * Initializes the controller class.
     */
    ObservableList<Envio> listaEnvios;
    
    private NotificadoOperacion observador; 
    private Paquete paqueteEditado;
    private boolean modoEdicion = false;
    @FXML
    private TextField tfDescripcion;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarEnvios();
    }    
     public void initializeValores(NotificadoOperacion observador, Paquete paqueteEditado ){
        this.paqueteEditado = paqueteEditado;
        this.observador = observador;
        if(paqueteEditado!= null){
            modoEdicion = true;
            llenarcampos();
            btnGuardar.setText("Editar");
        }
    }

    @FXML
    private void regresarPrincipal(MouseEvent event) {
        cerrarVentana();    
    }


    @FXML
    private void onClickGuardar(ActionEvent event) {
        Paquete paquete = new Paquete();
        paquete.setDescripcion(tfDescripcion.getText());
        float peso = Float.parseFloat(tfPeso.getText());
        float alto = Float.parseFloat(tfAlto.getText());
        float ancho = Float.parseFloat(tfAncho.getText());
        float profundidad = Float.parseFloat(tfProfundidad.getText());
        paquete.setPeso(peso);
        paquete.setAlto(alto);
        paquete.setAncho(ancho);
        paquete.setProfundidad(profundidad);
        paquete.setIdEnvio((cbEnvios.getSelectionModel().getSelectedItem()!= null)?
               cbEnvios.getSelectionModel().getSelectedItem().getIdEnvio(): -1);
        if(validarCampos(paquete)){
            if(!modoEdicion){
               guardarDatosPaquete(paquete);
           }else{
               paquete.setIdPaquete(paqueteEditado.getIdPaquete());
               editarDatosPaquete(paquete);
           }
        }
    }


    private void llenarcampos() {
        tfPeso.setText(""+paqueteEditado.getPeso());
        tfAlto.setText(""+paqueteEditado.getAlto());
        tfProfundidad.setText(""+paqueteEditado.getProfundidad());
        tfAncho.setText(""+paqueteEditado.getAncho());
        tfDescripcion.setText(paqueteEditado.getDescripcion());
        int poscicion = buscarIdEnvio(paqueteEditado.getIdEnvio());
        cbEnvios.getSelectionModel().select(poscicion);
    }

    private void cargarEnvios() {
        listaEnvios = FXCollections.observableArrayList();
        listaEnvios.addAll(EnvioDAO.obtenerEnvios());  
        cbEnvios.setItems(listaEnvios);
    }

    private int buscarIdEnvio(Integer idEnvio) {
        for(int i=0; i<listaEnvios.size();i++){
            if(listaEnvios.get(i).getIdEnvio()== idEnvio){
                return i;
            }
        }
        return -1;
    }

    private boolean validarCampos(Paquete paquete) {
        return true;
    }

    private void guardarDatosPaquete(Paquete paquete) {
        Mensaje msj = PaqueteDAO.registrarPaquete(paquete);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Registro Exitoso", "Paquete Agregado", Alert.AlertType.INFORMATION);
            observador.notificarOperacion("Guardar", ""+paquete.getIdEnvio());
            cerrarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void editarDatosPaquete(Paquete paquete) {
        Mensaje msj = PaqueteDAO.editarPaquete(paquete);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Registro Exitoso", "Paquete Editado", Alert.AlertType.INFORMATION);
            observador.notificarOperacion("Guardar", ""+paquete.getIdEnvio());
            cerrarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void cerrarVentana() {
        Stage base = (Stage) tfAlto.getScene().getWindow();
        base.close();
    }

    
}
