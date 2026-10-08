import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static List<Paciente> pacientes = new ArrayList<>();
    static List<ProfesionalSalud> profesionales = new ArrayList<>();
    static List<EquipoMedico> equipos = new ArrayList<>();
    static List<ServicioDomiciliario> servicios = new ArrayList<>();
    static DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("   BIENVENIDO A MEDiHOME APP");
        System.out.println("======================================");

        int opcion;
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Registrar profesional de salud");
            System.out.println("3. Crear equipo medico");
            System.out.println("4. Programar servicio domiciliario");
            System.out.println("5. Registrar atencion medica en un servicio");
            System.out.println("6. Registrar medicion de signos en una atencion");
            System.out.println("7. Notificar a un usuario");
            System.out.println("8. Listar todo");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarPaciente();
                    break;
                case 2:
                    registrarProfesional();
                    break;
                case 3:
                    crearEquipo();
                    break;
                case 4:
                    programarServicio();
                    break;
                case 5:
                    registrarAtencion();
                    break;
                case 6:
                    registrarMedicion();
                    break;
                case 7:
                    notificarUsuario();
                    break;
                case 8:
                    listarTodo();
                    break;
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    static void registrarPaciente() {
        System.out.print("Identificacion: ");
        String id = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Telefono: ");
        String tel = sc.nextLine();
        System.out.print("Direccion principal: ");
        String dir = sc.nextLine();
        Paciente p = new Paciente(id, nombre, correo, tel, dir);
        p.registrar();
        pacientes.add(p);
    }

    static void registrarProfesional() {
        System.out.print("Identificacion: ");
        String id = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Numero de registro profesional: ");
        String reg = sc.nextLine();
        ProfesionalSalud pr = new ProfesionalSalud(id, nombre, correo, reg);
        pr.registrar();
        profesionales.add(pr);
    }

    static void crearEquipo() {
        if (profesionales.isEmpty()) {
            System.out.println("Primero registre un profesional (opcion 2).");
            return;
        }
        System.out.print("Codigo del equipo: ");
        String cod = sc.nextLine();
        System.out.print("Nombre del equipo: ");
        String nom = sc.nextLine();
        System.out.print("Zona de cobertura: ");
        String zona = sc.nextLine();
        EquipoMedico equipo = new EquipoMedico(cod, nom, zona);
        listarProfesionales();
        System.out.print("Numero del profesional a agregar (0 para ninguno): ");
        int idx = leerEntero();
        if (idx > 0 && idx <= profesionales.size()) {
            equipo.agregarProfesional(profesionales.get(idx - 1));
        }
        equipos.add(equipo);
        System.out.println("Equipo creado: " + equipo);
    }

    static void programarServicio() {
        if (pacientes.isEmpty() || profesionales.isEmpty()) {
            System.out.println("Registre al menos un paciente y un profesional primero.");
            return;
        }
        System.out.print("Codigo unico del servicio: ");
        String cod = sc.nextLine();
        System.out.print("Fecha y hora (" + "yyyy-MM-dd HH:mm" + "): ");
        LocalDateTime fecha = leerFecha();
        System.out.print("Direccion de atencion: ");
        String dir = sc.nextLine();
        System.out.print("Motivo: ");
        String motivo = sc.nextLine();

        listarPacientes();
        System.out.print("Numero del paciente que solicita: ");
        int idxP = leerEntero();
        listarProfesionales();
        System.out.print("Numero del profesional que atiende: ");
        int idxPr = leerEntero();

        Paciente pac = pacientes.get(idxP - 1);
        ProfesionalSalud pro = profesionales.get(idxPr - 1);
        ServicioDomiciliario serv = new ServicioDomiciliario(cod, fecha, dir, motivo, pro, pac);
        serv.programar();
        servicios.add(serv);
    }

    static void registrarAtencion() {
        ServicioDomiciliario serv = seleccionarServicio();
        if (serv == null) return;
        System.out.print("Fecha y hora de inicio (" + "yyyy-MM-dd HH:mm" + "): ");
        LocalDateTime inicio = leerFecha();
        System.out.print("Observaciones: ");
        String obs = sc.nextLine();
        System.out.print("Recomendaciones: ");
        String rec = sc.nextLine();
        AtencionMedica at = new AtencionMedica(inicio, obs, rec);
        at.registrar();
        serv.agregarAtencion(at);
        System.out.println("Atencion agregada al servicio " + serv.getCodigoUnico());
    }

    static void registrarMedicion() {
        ServicioDomiciliario serv = seleccionarServicio();
        if (serv == null) return;
        if (serv.getAtenciones().isEmpty()) {
            System.out.println("El servicio no tiene atenciones (opcion 5).");
            return;
        }
        System.out.println("Atenciones del servicio:");
        for (int i = 0; i < serv.getAtenciones().size(); i++) {
            System.out.println((i + 1) + ". " + serv.getAtenciones().get(i));
        }
        System.out.print("Numero de la atencion: ");
        int idx = leerEntero();
        AtencionMedica at = serv.getAtenciones().get(idx - 1);

        System.out.print("Fecha y hora (" + "yyyy-MM-dd HH:mm" + "): ");
        LocalDateTime fecha = leerFecha();
        System.out.print("Temperatura (C): ");
        double temp = leerDouble();
        System.out.print("Frecuencia cardiaca (lpm): ");
        int fc = leerEntero();
        System.out.print("Presion sistolica: ");
        int sis = leerEntero();
        System.out.print("Presion diastolica: ");
        int dia = leerEntero();
        System.out.print("Saturacion de oxigeno (%): ");
        double sat = leerDouble();

        MedicionSignos med = new MedicionSignos(fecha, temp, fc, sis, dia, sat);
        med.registrar();
        at.agregarMedicion(med);
    }

    static void notificarUsuario() {
        System.out.println("1. Paciente");
        System.out.println("2. Profesional");
        System.out.print("Opcion: ");
        int tipo = leerEntero();
        System.out.print("Mensaje: ");
        String mensaje = sc.nextLine();
        if (tipo == 1) {
            listarPacientes();
            System.out.print("Numero del paciente: ");
            pacientes.get(leerEntero() - 1).notificar(mensaje);
        } else {
            listarProfesionales();
            System.out.print("Numero del profesional: ");
            profesionales.get(leerEntero() - 1).notificar(mensaje);
        }
    }

    static void listarTodo() {
        System.out.println("\n--- PACIENTES ---");
        listarPacientes();
        System.out.println("--- PROFESIONALES ---");
        listarProfesionales();
        System.out.println("--- EQUIPOS MEDICOS ---");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i));
            for (ProfesionalSalud p : equipos.get(i).getProfesionales()) {
                System.out.println("     - " + p.getNombre());
            }
        }
        System.out.println("--- SERVICIOS DOMICILIARIOS ---");
        for (int i = 0; i < servicios.size(); i++) {
            ServicioDomiciliario s = servicios.get(i);
            System.out.println((i + 1) + ". " + s);
            for (int j = 0; j < s.getAtenciones().size(); j++) {
                AtencionMedica a = s.getAtenciones().get(j);
                System.out.println("     - " + a);
                for (MedicionSignos m : a.getMediciones()) {
                    System.out.println("         * " + m);
                }
            }
        }
    }

    static ServicioDomiciliario seleccionarServicio() {
        if (servicios.isEmpty()) {
            System.out.println("No hay servicios (opcion 4).");
            return null;
        }
        for (int i = 0; i < servicios.size(); i++) {
            System.out.println((i + 1) + ". " + servicios.get(i));
        }
        System.out.print("Numero del servicio: ");
        return servicios.get(leerEntero() - 1);
    }

    static void listarPacientes() {
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + ". " + pacientes.get(i));
        }
    }

    static void listarProfesionales() {
        for (int i = 0; i < profesionales.size(); i++) {
            System.out.println((i + 1) + ". " + profesionales.get(i));
        }
    }

    static int leerEntero() {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Ingrese un numero valido: ");
        }
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    static double leerDouble() {
        while (true) {
            String texto = sc.nextLine();
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }
    }

    static LocalDateTime leerFecha() {
        while (true) {
            String texto = sc.nextLine();
            try {
                return LocalDateTime.parse(texto, FORMATO);
            } catch (Exception e) {
                System.out.print("Formato invalido. Use yyyy-MM-dd HH:mm: ");
            }
        }
    }
}
