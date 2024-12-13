package clienteescritorio;

import clienteescritorio.dao.ClienteDAO;
import clienteescritorio.observador.NotificadoOperacion;
import clienteescritorio.utilidades.Utilidades;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pojo.Cliente;
import pojo.Mensaje;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class FXMLFormularioClientesController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
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
    private TextField tfTelefono;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfEstado;
    /**
     * Initializes the controller class.
     */
    
    private NotificadoOperacion observador; 
    private Cliente clienteEditado;
    private boolean modoEdicion = false;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initializeValores(NotificadoOperacion observador, Cliente clienteEditado){
        this.clienteEditado = clienteEditado;
        this.observador = observador;
        if(clienteEditado!= null){
            modoEdicion = true;
            llenarcampos();
            btnGuardar.setText("Editar");
        }
    }

    @FXML
    private void regresarPrincipal(MouseEvent event) {
    }


    @FXML
    private void onClickGuardar(ActionEvent event) {
        Cliente cliente = new Cliente();
        cliente.setNombre(tfNombre.getText());
        cliente.setApellidoPaterno(tfApellidoPaterno.getText());
        cliente.setApellidoMaterno(tfApellidoMaterno.getText());
        cliente.setCalle(tfCalle.getText());
        cliente.setNumeroDeCasa(tfNumero.getText());
        cliente.setColonia(tfColonia.getText());
        cliente.setCodigoPostal(tfCodigoPostal.getText());
        cliente.setTelefono(tfTelefono.getText());
        cliente.setCorreo(tfCorreo.getText());
        cliente.setEstado(tfEstado.getText());
        cliente.setCiudad(tfCiudad.getText());
        if(validarCampos(cliente)){
            if(!modoEdicion){
                System.out.println("Agregar");
                guadarDatosDelCliente(cliente);
            }else{
                System.out.println("Editar");
                cliente.setIdCliente(clienteEditado.getIdCliente());
                editarDatosDelCliente(cliente);
            }
        }else{
            Utilidades.mostrarAlertaSimple("Campos Obligatorios", "Hubo un error al llenar los campos", Alert.AlertType.ERROR);
        }
    }
    
    private void llenarcampos() {
        tfNombre.setText(clienteEditado.getNombre());
        tfApellidoMaterno.setText(clienteEditado.getApellidoMaterno());
        tfApellidoPaterno.setText(clienteEditado.getApellidoPaterno());
        tfCalle.setText(clienteEditado.getCalle());
        tfNumero.setText(clienteEditado.getNumeroDeCasa());
        tfColonia.setText(clienteEditado.getColonia());
        tfCodigoPostal.setText(clienteEditado.getCodigoPostal());
        tfTelefono.setText(clienteEditado.getTelefono());
        tfCorreo.setText(clienteEditado.getCorreo());
        tfCiudad.setText(clienteEditado.getCiudad());
        tfEstado.setText(clienteEditado.getEstado());
    }

    private boolean validarCampos(Cliente cliente) {
        return true;
    }

    private void guadarDatosDelCliente(Cliente cliente) {
        Mensaje msj = ClienteDAO.agregarCliente(cliente);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Registro Exitoso", "Cliente: " + cliente.getNombre()+" Agregado", Alert.AlertType.INFORMATION);
            observador.notificarOperacion("Guardar", cliente.getNombre());
            cerrarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void editarDatosDelCliente(Cliente cliente) {
        Mensaje msj = ClienteDAO.editarCliente(cliente);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Edicion Exitosa", "Cliente: " + cliente.getNombre()+" Editado", Alert.AlertType.INFORMATION);
            observador.notificarOperacion("Editado", cliente.getNombre());
            cerrarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void cerrarVentana(){
        Stage base = (Stage) tfNombre.getScene().getWindow();
        base.close();
    }
    
}
