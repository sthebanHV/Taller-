public class ProfesionalSalud extends Usuario implements INotificable {

    private String numeroRegistroProfesional;

    public ProfesionalSalud() {
    }

    public ProfesionalSalud(String identificacion, String nombre, String correo, String numeroRegistroProfesional) {
        super(identificacion, nombre, correo);
        this.numeroRegistroProfesional = numeroRegistroProfesional;
    }

    public String getNumeroRegistroProfesional() {
        return numeroRegistroProfesional;
    }

    public void setNumeroRegistroProfesional(String numeroRegistroProfesional) {
        this.numeroRegistroProfesional = numeroRegistroProfesional;
    }

    @Override
    public void notificar(String mensaje) {
        System.out.println("[Notificacion a profesional " + nombre + " (" + correo + ")]: " + mensaje);
    }

    public void registrar() {
        System.out.println("Profesional de salud registrado: " + nombre + " (Registro: " + numeroRegistroProfesional + ")");
    }

    @Override
    public String toString() {
        return "ProfesionalSalud{identificacion='" + identificacion + "', nombre='" + nombre + "', correo='" + correo
                + "', numeroRegistroProfesional='" + numeroRegistroProfesional + "'}";
    }
}
