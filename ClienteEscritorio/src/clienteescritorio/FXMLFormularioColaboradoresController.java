package clienteescritorio;

import clienteescritorio.dao.ColaboradorDAO;
import clienteescritorio.dao.RolDAO;
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
import pojo.Colaborador;
import pojo.Mensaje;
import pojo.Rol;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class FXMLFormularioColaboradoresController implements Initializable {

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
    private TextField tfContrasena;
    @FXML
    private ImageView imgFotografia;
    @FXML
    private TextField tfNoLicencia;
    @FXML
    private Button btnGuardar;
    @FXML
    private ComboBox<Rol> cbRol;
    @FXML
    private ComboBox<?> cbVehiculo;

    private NotificadoOperacion observador; 
    private Colaborador colaboradorEditado;
    private boolean modoEdicion = false;
    ObservableList<Rol> tiposDeColaboradores;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTiposDeUsuarios();
    }    
    
    public void initializeValores(NotificadoOperacion observador, Colaborador colaboradorEditado){
        this.colaboradorEditado = colaboradorEditado;
        this.observador = observador;
        if(colaboradorEditado!=null){
            modoEdicion = true;
            llenarcampos();
            btnGuardar.setText("Editar");
        }
    }

    @FXML
    private void regresarPrincipal(MouseEvent event) {
    }

    @FXML
    private void subirFotografia(MouseEvent event) {
    }

    @FXML
    private void onClickGuardar(ActionEvent event) {
        Colaborador colaborador = new Colaborador();
        colaborador.setNombre(tfNombre.getText());
        colaborador.setApellidoPaterno(tfApellidoPaterno.getText());
        colaborador.setApellidoMaterno(tfApellidoMaterno.getText());
        colaborador.setCurp(tfCurp.getText());
        colaborador.setCorreo(tfCorreo.getText());
        colaborador.setNoPersonal(tfNumeroPersonal.getText());
        colaborador.setContrasena(tfContrasena.getText());
        colaborador.setIdRol((cbRol.getSelectionModel().getSelectedItem() != null)?
                cbRol.getSelectionModel().getSelectedItem().getIdRol(): -1
        );
        if(validarCampos(colaborador)){
            if(!modoEdicion){
                guardarDatosColaborador(colaborador);
            }else{
                colaborador.setIdColaborador(colaboradorEditado.getIdColaborador());
                editarDatosColaborador(colaborador);
            }
            
        }else{
            Utilidades.mostrarAlertaSimple("Campos Obligatorios", "Hubo un error al llenar los campos", Alert.AlertType.ERROR);
        }
    }
    
    private boolean validarCampos(Colaborador colaborador) {
        if (colaborador.getNombre() == null || colaborador.getNombre().isEmpty() ||
            colaborador.getApellidoMaterno() == null || colaborador.getApellidoMaterno().isEmpty() ||
            colaborador.getApellidoPaterno() == null || colaborador.getApellidoPaterno().isEmpty() ||
            colaborador.getCorreo() == null || colaborador.getCorreo().isEmpty() ||
            colaborador.getCurp() == null || colaborador.getCurp().isEmpty() ||
            colaborador.getNoPersonal() == null || colaborador.getNoPersonal().isEmpty() ||
            colaborador.getContrasena()== null || colaborador.getContrasena().isEmpty() ||
            colaborador.getIdRol() == null) {
            return false;
        }
        return true;
    }

    
    private void guardarDatosColaborador(Colaborador colaborador){
        Mensaje msj = ColaboradorDAO.registrarColaborador(colaborador);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Registro Exitoso", "Colaborador: " + colaborador.getNombre()+" Agregado", Alert.AlertType.INFORMATION);
            observador.notificarOperacion("Guardar", colaborador.getNombre());
            cerrarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    private void cerrarVentana(){
        Stage base = (Stage) tfNombre.getScene().getWindow();
        base.close();
    }
    
    private void cargarTiposDeUsuarios(){
        tiposDeColaboradores = FXCollections.observableArrayList();
        tiposDeColaboradores.addAll(RolDAO.optenerRoles());  
        cbRol.setItems(tiposDeColaboradores);
    }
    
    
    private void editarDatosColaborador(Colaborador colaborador){
        Mensaje msj = ColaboradorDAO.editarColaborador(colaborador);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Registro Exitoso", "Todo good", Alert.AlertType.INFORMATION);
            observador.notificarOperacion("Guardar", colaborador.getNombre());
            cerrarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error", "Todo bad", Alert.AlertType.ERROR);
        }
    }
    
    
    private void llenarcampos() {
        tfNombre.setText(colaboradorEditado.getNombre());
        tfApellidoMaterno.setText(colaboradorEditado.getApellidoMaterno());
        tfApellidoPaterno.setText(colaboradorEditado.getApellidoPaterno());
        tfContrasena.setText(colaboradorEditado.getContrasena());
        tfCorreo.setText(colaboradorEditado.getCorreo());
        tfCurp.setText(colaboradorEditado.getCurp());
        tfNumeroPersonal.setText(colaboradorEditado.getNoPersonal());
        tfNumeroPersonal.setEditable(false);
        int posicion = buscarIdRol(colaboradorEditado.getIdRol());
        cbRol.getSelectionModel().select(posicion);
        cbRol.setEditable(false);
    }
    private int buscarIdRol(int idRol){
        for(int i=0; i<tiposDeColaboradores.size();i++){
            if(tiposDeColaboradores.get(i).getIdRol() == idRol){
                return i;
            }
        }
        return -1;
    }

    @FXML
    private void irCerrarSesion(MouseEvent event) {
    }
}
