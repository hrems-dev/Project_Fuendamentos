import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.FileWriter;

public class variable {
    public Scanner CSS = new Scanner(System.in);
    public int opMenu = 0;
    // menu opciones
    public String[] menu = { "BUSCAR PASAJE", "PASAJERO CONSTANTE", "VIAJE CORPORATIVO", "SERVICIOS", "CONTACTOS",
            "SALIR" };
    // registro de datos
    public String[] Labels = { "NOMBRES", "APELLIDO", "AÑO DE NACIMIENT", "NACIONALIDAD", "NRO DOCUMENTO",
            "ORIGEN", "DESTINO", "FECHA DE VIAJE", "FECHA DE RETORNO", "HORA DE VIAJE", "COSTO" };
    public String[] infoEmpresa = { "CRUZ DEL SUR", "Av. Francisco Bolognesi 495, Surquillo", "20160227461" };
    public String[] sala = new String[Labels.length];
    // variables pago
    ArrayList<String> telefonoYape = new ArrayList<>();
    ArrayList<String> numeroTarjeta = new ArrayList<>();
    ArrayList<String> N_Verficacion_pago = new ArrayList<>();
    ArrayList<String> tel_Verficacion_pago = new ArrayList<>();
    ArrayList<String> tar_Verficacion_V = new ArrayList<>();
    int count5=1;
    public String metodPago="";
    // registro de datos
    // data de registro
    public ArrayList<String> dt_Nombre = new ArrayList<String>();
    public ArrayList<String> dt_apellido = new ArrayList<String>();
    public ArrayList<String> dt_nacionalidad = new ArrayList<String>();
    public ArrayList<String> dt_nroDoc = new ArrayList<String>();
    public ArrayList<String> dt_anioNac = new ArrayList<String>();
    public ArrayList<String> dt_origen = new ArrayList<String>();
    public ArrayList<String> dt_destino = new ArrayList<String>();
    public ArrayList<String> dt_fechaViaje = new ArrayList<String>();
    public ArrayList<String> dt_fechaRetorno = new ArrayList<String>();
    public ArrayList<String> dt_nroAsiento = new ArrayList<String>();
    public ArrayList<Double> dt_costo = new ArrayList<Double>();
    public ArrayList<String> dt_horaSalida = new ArrayList<String>();
    public ArrayList<Integer> dt_asientoRese = new ArrayList<Integer>();
    //
    public ArrayList<Boolean> dt_retorno = new ArrayList<Boolean>();
    public ArrayList<Boolean> dt_Estado = new ArrayList<Boolean>();

    // descuento
    public ArrayList<String> dt_dniFrec = new ArrayList<String>();
    public ArrayList<String> dt_codDes = new ArrayList<String>();
    // servicio
    public ArrayList<String> Servicio = new ArrayList<String>();
    // datos de ciudad
    public String[] dt_ciudad = { "Lima", "Ica", "Huancayo", "Abancay", "Arequipa", "Ayacucho", "Camana", "Chepen",
            "Chclayo", "Chimbote", "Cusco", "Huaraz", "Jauja", "Juliaca", "La Merced", "Atocongo-(Lima)",
            "Javier-(Lima)", "Plaza Norte-(Lima) ", "Marcona", "Nasca", "Oxapampa", "Pacasmayo", "Paracas",
            "Piura", "Puno", "Trujillo", "salir" };
    public String[] horarioViajes = { "5:00 am", "6:00 am", "7:30 am", "8:00am", "2:00 pm", "5:00pm", "7:00 pm",
            "7:30 pm", "8:00pm,", "10:00pm" };
    public String[] proxViaje = new String[6]; // datos de proximo viaje - origen,destino fecha de viaje . fecha de
    // regrso- hora
    // variables de proximo viaje
    public int count = 0, count2, origen = 0, count1 = 0;
    public int dia = 0, opmes, anio, idPas = 0;
    public ArrayList<String> rutas = new ArrayList<String>();
    public int[] mes = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    public Date FechaActual = new Date();

    public int diahoy = FechaActual.getDate(), meshoy = FechaActual.getMonth() + 1,
            aniohoy = FechaActual.getYear() + 1900;
    public String stadoval = "snVal"; // estado de validadicon
    public boolean retorno = true;
    Random rand = new Random();
    // int horaRean=rand.nextInt(horarioViajes.length);
    // horario de viaje
    public ArrayList<String> coleccionHorario = new ArrayList<String>();

    String fechaViaje = "";
    // cliente en reserva
    int cantPasajeros = 0, nroPas = 1;
    // servicion
    public String[] servics = { "CONFORT SUITE", "CRUCERO SUITE", "CRUZERO EVOLUTION", "CRUZERO PLUS",
            "LINE XPRESS" };
    public double precioServ = 0;
    public boolean esFrecuente = false;
    // costos de boleta
    public double descuento = 0, costo = 0;
    //
    public String prepa = "iniciando";

    // validaciones
    public String Telefono() {
        while (true) {
            System.out.print("Ingrese su número de teléfono (9 dígitos): ");
            String telefonoIntroducido = CSS.nextLine();
            Pattern patronTelefono = Pattern.compile("[0-9]{9}");
            Matcher mat = patronTelefono.matcher(telefonoIntroducido);
            if (mat.matches()) {
                System.out.println("El número de teléfono " + telefonoIntroducido + " es válido.");
                return telefonoIntroducido;
            } else {
                System.out.println(
                        "El número de teléfono introducido es incorrecto. Debe tener exactamente 9 dígitos.");
            }
        }
    }

    public String DNI() {
        while (true) {
            System.out.print("Ingrese su DNI (8 dígitos): ");
            String dniIntroducido = CSS.nextLine();
            Pattern patronDNI = Pattern.compile("[0-9]{8}");
            Matcher mat = patronDNI.matcher(dniIntroducido);

            if (mat.matches()) {
                System.out.println("El DNI " + dniIntroducido + " es válido.");
                return dniIntroducido;
            } else {
                System.out.println(
                        "El DNI introducido es incorrecto. Debe tener exactamente 8 dígitos.");
            }
        }
    }

    public String validarRUC() {
        while (true) {
            System.out.print("Ingrese su RUC (11 dígitos): ");
            String rucIntroducido = CSS.nextLine();
            Pattern patronRUC = Pattern.compile("[0-9]{11}");
            Matcher mat = patronRUC.matcher(rucIntroducido);

            if (mat.matches()) {
                System.out.println("El RUC " + rucIntroducido + " es válido.");
                return rucIntroducido;
            } else {
                System.out.println(
                        "El RUC introducido es incorrecto. Debe tener exactamente 11 dígitos.");
            }
        }
    }

    public String Correo() {
        while (true) {
            System.out.print("Ingrese su correo electrónico: ");
            String correoIntroducido = CSS.nextLine();
            Pattern patronCorreo = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
            Matcher mat = patronCorreo.matcher(correoIntroducido);

            if (mat.matches()) {
                System.out.println("El correo electrónico " + correoIntroducido + " es válido.");
                return correoIntroducido;
            } else {
                System.out.println(
                        "El correo electrónico introducido es incorrecto. Debe tener un formato válido con '@'.");
            }
        }
    }

}
