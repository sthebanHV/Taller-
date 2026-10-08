import java.util.HashMap;
import java.util.Map;

public class Paciente extends Usuario implements INotificable {

    private String telefono;
    private String direccionPrincipal;
    private Map<String, String> atributos = new HashMap<>();

    public Paciente() {
    }

    public Paciente(String identificacion, String nombre, String correo, String telefono, String direccionPrincipal) {
        super(identificacion, nombre, correo);
        this.telefono = telefono;
        this.direccionPrincipal = direccionPrincipal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccionPrincipal() {
        return direccionPrincipal;
    }

    public void setDireccionPrincipal(String direccionPrincipal) {
        this.direccionPrincipal = direccionPrincipal;
    }

    public String getAttribute(String atributo) {
        return atributos.get(atributo);
    }

    public void setAttribute(String atributo, String valor) {
        atributos.put(atributo, valor);
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("[Notificacion a paciente " + nombre + " (" + correo + ")]: " + mensaje);
    }

    public void registrar() {
        System.out.println("Paciente registrado: " + nombre + " (ID: " + identificacion + ")");
    }

    @Override
    public String toString() {
        return "Paciente{identificacion='" + identificacion + "', nombre='" + nombre + "', correo='" + correo
                + "', telefono='" + telefono + "', direccionPrincipal='" + direccionPrincipal + "', atributos="
                + atributos + "}";
    }
}
