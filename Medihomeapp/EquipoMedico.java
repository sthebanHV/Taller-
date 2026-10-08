import java.util.ArrayList;
import java.util.List;

public class EquipoMedico {

    private String codigo;
    private String nombre;
    private String zonaCobertura;
    private List<ProfesionalSalud> profesionales = new ArrayList<>();

    public EquipoMedico() {
    }

    public EquipoMedico(String codigo, String nombre, String zonaCobertura) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.zonaCobertura = zonaCobertura;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getZonaCobertura() {
        return zonaCobertura;
    }

    public void setZonaCobertura(String zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }

    public void agregarProfesional(ProfesionalSalud profesional) {
        if (profesional != null && !profesionales.contains(profesional)) {
            profesionales.add(profesional);
            System.out.println("Profesional " + profesional.getNombre() + " agregado al equipo " + nombre);
        }
    }

    public void quitarProfesional(ProfesionalSalud profesional) {
        if (profesionales.remove(profesional)) {
            System.out.println("Profesional " + profesional.getNombre() + " eliminado del equipo " + nombre);
        }
    }

    public List<ProfesionalSalud> getProfesionales() {
        return profesionales;
    }

    @Override
    public String toString() {
        return "EquipoMedico{codigo='" + codigo + "', nombre='" + nombre + "', zonaCobertura='" + zonaCobertura
                + "', profesionales=" + profesionales.size() + "}";
    }
}
