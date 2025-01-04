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
        cerrarVentana();
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
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty() || cliente.getNombre().length() > 50) {
            Utilidades.mostrarAlertaSimple("Error", "El nombre es obligatorio.", Alert.AlertType.ERROR);
            return false;
        }

        if (cliente.getApellidoPaterno() == null || cliente.getApellidoPaterno().trim().isEmpty() || cliente.getApellidoPaterno().length() > 50) {
            Utilidades.mostrarAlertaSimple("Error", "El apellido paterno es obligatorio.", Alert.AlertType.ERROR);
            return false;
        }

        if (cliente.getApellidoMaterno() != null && cliente.getApellidoMaterno().length() > 50) {
            Utilidades.mostrarAlertaSimple("Error", "El apellido materno es obligatorio.", Alert.AlertType.ERROR);
            return false;
        }

        if (cliente.getCalle() == null || cliente.getCalle().trim().isEmpty() || cliente.getCalle().length() > 100) {
            Utilidades.mostrarAlertaSimple("Error", "La calle es obligatoria.", Alert.AlertType.ERROR);
            return false;
        }

        if (cliente.getNumeroDeCasa() == null || cliente.getNumeroDeCasa().trim().isEmpty() || !cliente.getNumeroDeCasa().matches("\\d{1,10}")) {
            Utilidades.mostrarAlertaSimple("Error", "El número de casa es obligatorio y debe contener solo números (máximo 10 dígitos).", Alert.AlertType.ERROR);
            return false;
        }

        if (cliente.getColonia() == null || cliente.getColonia().trim().isEmpty() || cliente.getColonia().length() > 100) {
            Utilidades.mostrarAlertaSimple("Error", "La colonia es obligatoria.", Alert.AlertType.ERROR);
            return false;
        }

        if (cliente.getCodigoPostal() == null || !cliente.getCodigoPostal().matches("\\d{5}")) {
            Utilidades.mostrarAlertaSimple("Error", "El código postal es obligatorio y debe tener exactamente 5 dígitos.", Alert.AlertType.ERROR);
            return false;
        }

        if (cliente.getCiudad() == null || cliente.getCiudad().trim().isEmpty() || cliente.getCiudad().length() > 50) {
            Utilidades.mostrarAlertaSimple("Error", "La ciudad es obligatoria.", Alert.AlertType.ERROR);
            return false;
        }

        if (cliente.getEstado() == null || cliente.getEstado().trim().isEmpty() || cliente.getEstado().length() > 50) {
            Utilidades.mostrarAlertaSimple("Error", "El estado es obligatorio.", Alert.AlertType.ERROR);
            return false;
        }

        if (cliente.getTelefono() == null || !cliente.getTelefono().matches("\\d{10}")) {
            Utilidades.mostrarAlertaSimple("Error", "El teléfono es obligatorio y debe tener exactamente 10 dígitos.", Alert.AlertType.ERROR);
            return false;
        }

        if (cliente.getCorreo() == null || !cliente.getCorreo().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            Utilidades.mostrarAlertaSimple("Error", "El correo es obligatorio y debe ser un formato válido (ej. ejemplo@dominio.com).", Alert.AlertType.ERROR);
            return false;
        }

        return true; 
    }


    private void guadarDatosDelCliente(Cliente cliente) {
        Mensaje msj = ClienteDAO.agregarCliente(cliente);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Registro Exitoso", "Cliente: " + cliente.getNombre()+" Agregado", Alert.AlertType.INFORMATION);
            observador.notificarOperacion("Guardar", cliente.getNombre());
            cerrarVentana();
        }else{
            cliente = null;
            Utilidades.mostrarAlertaSimple("Error", "No se pudo Guardar el Cliente", Alert.AlertType.ERROR);
        }
    }

    private void editarDatosDelCliente(Cliente cliente) {
        Mensaje msj = ClienteDAO.editarCliente(cliente);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Edicion Exitosa", "Cliente: " + cliente.getNombre()+" Editado", Alert.AlertType.INFORMATION);
            observador.notificarOperacion("Editado", cliente.getNombre());
            cerrarVentana();
        }else{
            cliente = null;
            Utilidades.mostrarAlertaSimple("Error", "No se pudo editar el Cliente", Alert.AlertType.ERROR);
        }
    }
    
    private void cerrarVentana(){
        Stage base = (Stage) tfNombre.getScene().getWindow();
        base.close();
    }
    
}
