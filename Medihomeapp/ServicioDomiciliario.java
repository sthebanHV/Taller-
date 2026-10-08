import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServicioDomiciliario {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private String codigoUnico;
    private LocalDateTime fechaHora;
    private String direccionAtencion;
    private String motivo;
    private String estado;
    private ProfesionalSalud profesional;   // "atende a" (1)
    private Paciente paciente;              // "solicita"
    private List<AtencionMedica> atenciones = new ArrayList<>(); // composicion "tiene"

    public ServicioDomiciliario() {
    }

    public ServicioDomiciliario(String codigoUnico, LocalDateTime fechaHora, String direccionAtencion,
                                String motivo, ProfesionalSalud profesional, Paciente paciente) {
        this.codigoUnico = codigoUnico;
        this.fechaHora = fechaHora;
        this.direccionAtencion = direccionAtencion;
        this.motivo = motivo;
        this.profesional = profesional;
        this.paciente = paciente;
        this.estado = "Pendiente";
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDireccionAtencion() {
        return direccionAtencion;
    }

    public void setDireccionAtencion(String direccionAtencion) {
        this.direccionAtencion = direccionAtencion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ProfesionalSalud getProfesional() {
        return profesional;
    }

    public void setProfesional(ProfesionalSalud profesional) {
        this.profesional = profesional;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void agregarAtencion(AtencionMedica atencion) {
        if (atencion != null) {
            atenciones.add(atencion);
        }
    }

    public List<AtencionMedica> getAtenciones() {
        return atenciones;
    }

    public void programar() {
        this.estado = "Programado";
        System.out.println("Servicio domiciliario programado: " + codigoUnico + " el "
                + (fechaHora == null ? "sin fecha" : fechaHora.format(FORMATO)));
        if (profesional != null) {
            profesional.notificar("Se le ha asignado el servicio " + codigoUnico);
        }
        if (paciente != null) {
            paciente.notificar("Su servicio domiciliario " + codigoUnico + " ha sido programado");
        }
    }

    @Override
    public String toString() {
        return "ServicioDomiciliario{codigoUnico='" + codigoUnico + "', fechaHora="
                + (fechaHora == null ? "null" : fechaHora.format(FORMATO))
                + ", direccionAtencion='" + direccionAtencion + "', motivo='" + motivo + "', estado='" + estado
                + "', profesional=" + (profesional == null ? "ninguno" : profesional.getNombre())
                + ", paciente=" + (paciente == null ? "ninguno" : paciente.getNombre())
                + ", atenciones=" + atenciones.size() + "}";
    }
}
