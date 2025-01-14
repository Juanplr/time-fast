/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio;

import clienteescritorio.dao.ClienteDAO;
import clienteescritorio.dao.ColaboradorDAO;
import clienteescritorio.dao.EnvioDAO;
import clienteescritorio.dao.HistorialDeEnvioDAO;
import clienteescritorio.observador.NotificadoOperacion;
import clienteescritorio.utilidades.Utilidades;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pojo.Cliente;
import pojo.Colaborador;
import pojo.Envio;
import pojo.EstadoDeEnvio;
import pojo.HistorialDeEnvio;
import pojo.Mensaje;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class FXMLFormularioEnviosController implements Initializable {

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
    private TextField tfNumeroGuia;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfEstado;
    @FXML
    private TextField tfCostoEnvio;
    @FXML
    private TextField tfDestino;
    @FXML
    private ComboBox<Cliente> cbClientes;
    @FXML
    private ComboBox<Colaborador> cbConductores;
    @FXML
    private ComboBox<EstadoDeEnvio> cbEstadoDeEnvio;

    /**
     * Initializes the controller class.
     */
    
    private NotificadoOperacion observador; 
    private Envio envioEditado;
    private Colaborador colaboradorLoguiado;
    private boolean modoEdicion = false;
    ObservableList<Cliente> tiposDeClientes;
    ObservableList<Colaborador> tiposDeConductores;
    ObservableList<EstadoDeEnvio> tiposDeEsadosDeEnvio;
    
    @FXML
    private Label lEstadoEnvio;
    @FXML
    private TextField tfMotivo;
    @FXML
    private Label lMotivo;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarClientes();
        cargarConductores();
        cargarEstadosDeEnvio();
        lEstadoEnvio.setVisible(false);
        cbEstadoDeEnvio.setVisible(false);
        lMotivo.setVisible(false);
        tfMotivo.setVisible(false);
    }
    public void initializeValores(NotificadoOperacion observador, Envio envioEditado, Colaborador colaboradorLoguiado){
        this.envioEditado = envioEditado;
        this.colaboradorLoguiado = colaboradorLoguiado;
        this.observador = observador;
        if(envioEditado!=null){
            modoEdicion = true;
            tfNumeroGuia.setEditable(false);
            lEstadoEnvio.setVisible(true);
            cbEstadoDeEnvio.setVisible(true);
            lMotivo.setVisible(true);
            tfMotivo.setVisible(true);
            llenarcampos();
        }
    }
    
    private void llenarcampos() {
        tfDestino.setText(envioEditado.getDestino());
        tfNumeroGuia.setText(envioEditado.getNoGuia());
        tfCalle.setText(envioEditado.getOrigenCalle());
        tfNumero.setText(envioEditado.getOrigenNumero());
        tfCodigoPostal.setText(envioEditado.getOrigenCodigoPostal());
        tfCiudad.setText(envioEditado.getOrigenCiudad());
        tfEstado.setText(envioEditado.getOrigenEstado());
        tfColonia.setText(envioEditado.getOrigenColonia());
        tfCostoEnvio.setText(""+envioEditado.getCostoDeEnvio());
        int pocisionCliente = buscarCliente(envioEditado.getIdCliente());
        int pocisionConductor = buscarConductor(envioEditado.getIdColaborador());
        int pocisionEstadoEnvio = buscarEstadoEnvio(envioEditado.getIdEstadoDeEnvio());
        cbClientes.getSelectionModel().select(pocisionCliente);
        cbConductores.getSelectionModel().select(pocisionConductor);
        cbEstadoDeEnvio.getSelectionModel().select(pocisionEstadoEnvio);
    }



    @FXML
    private void onClickGuardar(ActionEvent event) {
        Envio envio = new Envio();
        envio.setOrigenCalle(tfCalle.getText());
        envio.setOrigenNumero(tfNumero.getText());
        envio.setOrigenColonia(tfColonia.getText());
        envio.setOrigenCodigoPostal(tfCodigoPostal.getText());
        envio.setOrigenCiudad(tfCiudad.getText());
        envio.setOrigenEstado(tfEstado.getText());
        envio.setNoGuia(tfNumeroGuia.getText());
        try {
            if (tfCostoEnvio.getText().trim().isEmpty()) {
                Utilidades.mostrarAlertaSimple("Error", "El costo del envío es obligatorio.", Alert.AlertType.ERROR);
                return;
            }
            float costo = Float.parseFloat(tfCostoEnvio.getText());
            envio.setCostoDeEnvio(costo);
        } catch (NumberFormatException e) {
            Utilidades.mostrarAlertaSimple("Error", "El costo del envío debe ser un número válido.", Alert.AlertType.ERROR);
            return;
        }
        envio.setIdCliente((cbClientes.getSelectionModel().getSelectedItem() != null)?
                cbClientes.getSelectionModel().getSelectedItem().getIdCliente(): -1);
        envio.setIdColaborador((cbConductores.getSelectionModel().getSelectedItem() != null)?
                cbConductores.getSelectionModel().getSelectedItem().getIdColaborador(): -1);
        envio.setIdEstadoDeEnvio((cbEstadoDeEnvio.getSelectionModel().getSelectedItem() != null)?
                cbEstadoDeEnvio.getSelectionModel().getSelectedItem().getIdEstadoDeEnvio(): 1);
        envio.setDestino(tfDestino.getText());
        if(validarCampos(envio)){
            if(!modoEdicion){
                guardarDatosEnvio(envio);
            }else{
                envio.setIdEnvio(envioEditado.getIdEnvio());
                editarDatosEnvio(envio);
            }
            
        }
    }
    
    private void cerrarVentana(){
        Stage base = (Stage) tfCalle.getScene().getWindow();
        base.close();
    }


    @FXML
    private void irPantallaPrincipal(MouseEvent event) {
        cerrarVentana();
    }

    private void cargarClientes() {
        tiposDeClientes = FXCollections.observableArrayList();
        tiposDeClientes.addAll(ClienteDAO.obtenerClientes());  
        cbClientes.setItems(tiposDeClientes);
    }

    private void cargarConductores() {
        tiposDeConductores = FXCollections.observableArrayList();
        tiposDeConductores.addAll(ColaboradorDAO.obtenerConductores());
        cbConductores.setItems(tiposDeConductores);
    }

    private void cargarEstadosDeEnvio() {
        tiposDeEsadosDeEnvio = FXCollections.observableArrayList();
        tiposDeEsadosDeEnvio.addAll(EnvioDAO.obtenerEstadosDeEnvios());
        cbEstadoDeEnvio.setItems(tiposDeEsadosDeEnvio);
    }

    private int buscarCliente(Integer idCliente) {
        for(int i=0; i<tiposDeClientes.size();i++){
            if(tiposDeClientes.get(i).getIdCliente() == idCliente){
                return i;
            }
        }
        return -1;
    }

    private int buscarConductor(Integer idColaborador) {
        for(int i=0; i<tiposDeConductores.size();i++){
            if(tiposDeConductores.get(i).getIdColaborador()== idColaborador){
                return i;
            }
        }
        return -1;
    }

    private int buscarEstadoEnvio(Integer idEstadoDeEnvio) {
        for(int i=0; i<tiposDeEsadosDeEnvio.size();i++){
            if(tiposDeEsadosDeEnvio.get(i).getIdEstadoDeEnvio() == idEstadoDeEnvio){
                return i;
            }
        }
        return -1;
    }

    private boolean validarCampos(Envio envio) {
        if (envio.getOrigenCalle() == null || envio.getOrigenCalle().trim().isEmpty()) {
            Utilidades.mostrarAlertaSimple("Validación", "El campo Calle es obligatorio.", Alert.AlertType.WARNING);
            return false;
        }
        if (envio.getOrigenNumero() == null || envio.getOrigenNumero().trim().isEmpty()) {
            Utilidades.mostrarAlertaSimple("Validación", "El campo Número es obligatorio.", Alert.AlertType.WARNING);
            return false;
        }
        if (envio.getOrigenColonia() == null || envio.getOrigenColonia().trim().isEmpty()) {
            Utilidades.mostrarAlertaSimple("Validación", "El campo Colonia es obligatorio.", Alert.AlertType.WARNING);
            return false;
        }
        if (envio.getOrigenCodigoPostal() == null || envio.getOrigenCodigoPostal().trim().isEmpty()) {
            Utilidades.mostrarAlertaSimple("Validación", "El campo Código Postal es obligatorio.", Alert.AlertType.WARNING);
            return false;
        }
        if (envio.getOrigenCiudad() == null || envio.getOrigenCiudad().trim().isEmpty()) {
            Utilidades.mostrarAlertaSimple("Validación", "El campo Ciudad es obligatorio.", Alert.AlertType.WARNING);
            return false;
        }
        if (envio.getOrigenEstado() == null || envio.getOrigenEstado().trim().isEmpty()) {
            Utilidades.mostrarAlertaSimple("Validación", "El campo Estado es obligatorio.", Alert.AlertType.WARNING);
            return false;
        }
        if (envio.getDestino() == null || envio.getDestino().trim().isEmpty()) {
            Utilidades.mostrarAlertaSimple("Validación", "El campo Destino es obligatorio.", Alert.AlertType.WARNING);
            return false;
        }
        if (envio.getCostoDeEnvio() <= 0) {
            Utilidades.mostrarAlertaSimple("Validación", "El campo Costo de Envío debe ser mayor a 0.", Alert.AlertType.WARNING);
            return false;
        }
        if (envio.getNoGuia() == null || envio.getNoGuia().trim().isEmpty()) {
            Utilidades.mostrarAlertaSimple("Validación", "El campo Número de Guía es obligatorio.", Alert.AlertType.WARNING);
            return false;
        }

        if ((envio.getIdEstadoDeEnvio() == 4 || envio.getIdEstadoDeEnvio() == 5) && 
            (tfMotivo.getText() == null || tfMotivo.getText().trim().isEmpty())) {
            Utilidades.mostrarAlertaSimple("Validación", "El campo Motivo es obligatorio cuando el estado del envío es 'Cancelado' o 'Detenido'.", Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }


    private void guardarDatosEnvio(Envio envio) {
        Mensaje msj = EnvioDAO.agregarEnvio(envio);
        if(!msj.isError()){
            if(!enviarHistorial(envio)){
                Utilidades.mostrarAlertaSimple("Registro Exitoso", "Envio: " + envio.getNoGuia()+" Agregado", Alert.AlertType.INFORMATION);
                observador.notificarOperacion("Guardar",envio.getNoGuia() );
                cerrarVentana();
            }else{
                envio = null;
                Utilidades.mostrarAlertaSimple("Error", "No se pudo Guardar el envío"+ msj.getMensaje(), Alert.AlertType.ERROR);
            }
        }else{
            envio = null;
            Utilidades.mostrarAlertaSimple("Error", "No se pudo Guardar el envío"+ msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void editarDatosEnvio(Envio envio) {
        Mensaje msj = EnvioDAO.editarEnvio(envio);
        if(!msj.isError()){
            if(!enviarHistorial(envio)){
                Utilidades.mostrarAlertaSimple("Edición", "Envio: " +envio.getNoGuia()+ " Editado" , Alert.AlertType.INFORMATION);
                observador.notificarOperacion("Edición",envio.getNoGuia());
                cerrarVentana();
            }else{
                envio = null;
                Utilidades.mostrarAlertaSimple("Error", "No se pudo editar el envío", Alert.AlertType.ERROR);
            }
        }else{
           envio = null;
           Utilidades.mostrarAlertaSimple("Error", "No se pudo editar el envío", Alert.AlertType.ERROR);
        }
    }

    private boolean enviarHistorial(Envio envio) {
        HistorialDeEnvio historial = new HistorialDeEnvio();
        historial.setIdEstadoDeEnvio(envio.getIdEstadoDeEnvio());
        System.out.println(colaboradorLoguiado.getIdColaborador());
        historial.setIdColaborador(colaboradorLoguiado.getIdColaborador());
        historial.setNoGuia(envio.getNoGuia());
        if(tfMotivo.getText().isEmpty()){
            historial.setMotivo("S/M");
        }else{
            historial.setMotivo(tfMotivo.getText());
        }
        LocalDate fechaActual = LocalDate.now();
        historial.setTiempoDeCambio(fechaActual.toString());
        Mensaje mensaje = HistorialDeEnvioDAO.registrarHistorialEnvio(historial);
        return mensaje.isError();
    }
    
}
