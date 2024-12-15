/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio;

import clienteescritorio.dao.UnidadDAO;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pojo.EstadoUnidad;
import pojo.Mensaje;
import pojo.TipoUnidad;
import pojo.Unidad;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class FXMLFormularioUnidadesController implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfModelo;
    @FXML
    private TextField tfNumeroIdentificacion;
    @FXML
    private TextField tfVin;
    @FXML
    private ComboBox<TipoUnidad> cbTipoUnidad;

    /**
     * Initializes the controller class.
     */
    
    private NotificadoOperacion observador; 
    private Unidad unidadEditada;
    private boolean modoEdicion = false;
    
    ObservableList<TipoUnidad> tiposDeUnidades;
    ObservableList<EstadoUnidad> tiposDeEstado;
    
    
    @FXML
    private TextField tfAnio;
    @FXML
    private Label lEstadoUnidad;
    @FXML
    private ComboBox<EstadoUnidad> cbEstadoUnidad;
    @FXML
    private Label lMotivo;
    @FXML
    private TextField tfMotivo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lEstadoUnidad.setVisible(false);
        lMotivo.setVisible(false);
        cbEstadoUnidad.setVisible(false);
        tfMotivo.setVisible(false);
        cargarTiposDeUnidad();
        cargarEstadoUnidad();
    }    
    public void initializeValores(NotificadoOperacion observador, Unidad unidadEditada){
        this.unidadEditada = unidadEditada;
        this.observador = observador;
        if(unidadEditada!= null){
            modoEdicion = true;
            lEstadoUnidad.setVisible(true);
            cbEstadoUnidad.setVisible(true);
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
       Unidad unidad= new Unidad();
       unidad.setMarca(tfMarca.getText());
       unidad.setModelo(tfModelo.getText());
       unidad.setVin(tfVin.getText());
       unidad.setNii(tfNumeroIdentificacion.getText());
       unidad.setAnio(tfAnio.getText());
       unidad.setIdTipoUnidad((cbTipoUnidad.getSelectionModel().getSelectedItem()!= null)?
               cbTipoUnidad.getSelectionModel().getSelectedItem().getIdTipoUnidad(): -1);
       if(validarCampos(unidad)){
           if(!modoEdicion){
               guardarDatosUnidad(unidad);
           }else{
               unidad.setIdEstadoUnidad(1);
               unidad.setIdUnidad(unidadEditada.getIdUnidad());
               editarUnidad(unidad);
           }
       }else{
           Utilidades.mostrarAlertaSimple("Campos Obligatorios", "Hubo un error al llenar los campos", Alert.AlertType.ERROR);
       }
        
    }
    
    private void llenarcampos() {
        tfMarca.setText(unidadEditada.getMarca());
        tfModelo.setText(unidadEditada.getModelo());
        tfVin.setText(unidadEditada.getVin());
        tfVin.setEditable(false);
        tfNumeroIdentificacion.setText(unidadEditada.getNii());
        tfAnio.setText(unidadEditada.getAnio());
        int poscicion = buscarIdTipoUnidad(unidadEditada.getIdTipoUnidad());
        cbTipoUnidad.getSelectionModel().select(poscicion);
        int pocicionUnidad = buscarIdEstadoUnidad(unidadEditada.getIdEstadoUnidad());
        cbEstadoUnidad.getSelectionModel().select(pocicionUnidad);
    }
     private int buscarIdTipoUnidad(int idTipoUnidad){
        for(int i=0; i<tiposDeUnidades.size();i++){
            if(tiposDeUnidades.get(i).getIdTipoUnidad() == idTipoUnidad){
                return i;
            }
        }
        return -1;
    }
     
    private void cerrarVentana(){
        Stage base = (Stage) tfModelo.getScene().getWindow();
        base.close();
    }
     
    private void cargarTiposDeUnidad(){
        tiposDeUnidades = FXCollections.observableArrayList();
        tiposDeUnidades.addAll(UnidadDAO.obtenerTiposUnidades());  
        cbTipoUnidad.setItems(tiposDeUnidades);
    }
    
    private boolean validarCampos(Unidad unidad) {
        if (unidad.getMarca() == null || unidad.getMarca().trim().isEmpty()) {
            System.out.println("La marca es obligatoria.");
            return false;
        }

        if (unidad.getModelo() == null || unidad.getModelo().trim().isEmpty()) {
            System.out.println("El modelo es obligatorio.");
            return false;
        }

        if (unidad.getAnio() == null || !unidad.getAnio().matches("\\d{4}")) {
            System.out.println("El año debe ser un valor numérico de 4 dígitos.");
            return false;
        }

        if (unidad.getVin() == null || !unidad.getVin().matches("[A-HJ-NPR-Z0-9]{17}")) {
            System.out.println("El VIN debe tener exactamente 17 caracteres alfanuméricos (sin las letras I, O, Q).");
            return false;
        }

        if (unidad.getTipoUnidad() == null || unidad.getTipoUnidad().trim().isEmpty()) {
            System.out.println("El tipo de unidad (Gasolina, Diesel, Eléctrica, Híbrida) es obligatorio.");
            return false;
        }

        if (unidad.getNii() == null || !unidad.getNii().equals(generarNII(unidad))) {
            System.out.println("El Número de Identificación Interno (NII) no coincide con el formato requerido (Año + primeros 4 caracteres del VIN).");
            return false;
        }

        if (unidad.getIdTipoUnidad() == null || unidad.getIdTipoUnidad() <= 0) {
            System.out.println("El ID del tipo de unidad debe ser un valor positivo.");
            return false;
        }

        if (unidad.getIdEstadoUnidad() == null || unidad.getIdEstadoUnidad() <= 0) {
            System.out.println("El ID del estado de la unidad debe ser un valor positivo.");
            return false;
        }

        return true;
    }

    private String generarNII(Unidad unidad) {
        String anio = unidad.getAnio();
        String vin = unidad.getVin();
        if (anio != null && vin != null && vin.length() >= 4) {
            return anio + vin.substring(0, 4);
        }
        return null;
    }

    
    private void guardarDatosUnidad(Unidad unidad){
        Mensaje msj = UnidadDAO.agregarUnidad(unidad);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Registro Exitoso", "Unidad: " + unidad.getMarca()+" Agregado", Alert.AlertType.INFORMATION);
            observador.notificarOperacion("Guardar", unidad.getMarca());
            cerrarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
     private void editarUnidad(Unidad unidad){
        Mensaje msj = UnidadDAO.EditarUnidad(unidad);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Registro Exitoso", "Todo good", Alert.AlertType.INFORMATION);
            observador.notificarOperacion("Guardar", unidad.getMarca());
            cerrarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void cargarEstadoUnidad() {
        tiposDeEstado = FXCollections.observableArrayList();
        tiposDeEstado.addAll(UnidadDAO.obtenerTiposDeEstados());  
        cbEstadoUnidad.setItems(tiposDeEstado);
    }

    private int buscarIdEstadoUnidad(Integer idEstadoUnidad) {
         for(int i=0; i<tiposDeEstado.size();i++){
            if(tiposDeEstado.get(i).getIdEstadoUnidad()== idEstadoUnidad){
                return i;
            }
        }
        return -1;
    }

    @FXML
    private void detectarEstado(ActionEvent event) {
        if(cbEstadoUnidad.getSelectionModel().getSelectedItem().getIdEstadoUnidad()==2){
            lMotivo.setVisible(true);
            tfMotivo.setVisible(true);
        }else{
            lMotivo.setVisible(false);
            tfMotivo.setVisible(false);
        }
    }
}
