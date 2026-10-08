import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AtencionMedica {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFinalizacion;
    private String observaciones;
    private String recomendaciones;
    private List<MedicionSignos> mediciones = new ArrayList<>();

    public AtencionMedica() {
    }

    public AtencionMedica(LocalDateTime fechaHoraInicio, String observaciones, String recomendaciones) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.observaciones = observaciones;
        this.recomendaciones = recomendaciones;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFinalizacion() {
        return fechaHoraFinalizacion;
    }

    public void setFechaHoraFinalizacion(LocalDateTime fechaHoraFinalizacion) {
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public void agregarMedicion(MedicionSignos medicion) {
        if (medicion != null) {
            mediciones.add(medicion);
        }
    }

    public List<MedicionSignos> getMediciones() {
        return mediciones;
    }

    public void registrar() {
        System.out.println("Atencion medica registrada: " + this);
    }

    @Override
    public String toString() {
        return "AtencionMedica{inicio=" + (fechaHoraInicio == null ? "null" : fechaHoraInicio.format(FORMATO))
                + ", fin=" + (fechaHoraFinalizacion == null ? "en curso" : fechaHoraFinalizacion.format(FORMATO))
                + ", observaciones='" + observaciones + "', recomendaciones='" + recomendaciones
                + "', mediciones=" + mediciones.size() + "}";
    }
}
