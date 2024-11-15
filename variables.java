import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class transporte extends variable {
    public static void main(String[] args) {
        transporte busTrans = new transporte();
        busTrans.MenuOpciones();
    }

    public void MenuOpciones() {

        switch (opMenu) {
            case 1: // buscarPasaje();// buscarPasaje();
                System.out.println("Eligio buscar pasjero");
                this.pasajeDisponible();
                break;
            case 2:
                this.PasajeroFrecuente();
                break;
            case 3: // Viaje corporativo
                System.out.println("ingresando a viaje corporativo");
                this.ViajeCorp();
                break;
            case 4: // servicsios
                this.Servicios();
                break;
            case 5:// contactos
                this.Contactos();
                break;
            case 6:
                System.out.println("Saliendo del programa...");
                break;
            default:
                // menu de oopciones
                this.generMenu();
                System.out.println("ELIJA UNA DE LAS OPCIONES --------------------------------------");
                opMenu = CSS.nextInt();
                MenuOpciones();
        }

    }

    public void reinicio() {
        opMenu = 0;
        count = 0;
        count2 = 0;
        count1 = 0;

    }

    public void generMenu() {
        System.out.println("""
                +------------------ EMPRESA DE TRANSPORTES ----------------------+
                |  ____ ____  _   _ _____  ____  _____ _       ____  _   _ ____  |
                | / ___|  _ \\| | | |__  / |  _ \\| ____| |     / ___|| | | |  _ \\ |
                || |   | |_) | | | | / /  | | | |  _| | |     \\___ \\| | | | |_) ||
                || |___|  _ <| |_| |/ /_  | |_| | |___| |___   ___) | |_| |  _ < |
                | \\____|_| \\_\\\\___//____| |____/|_____|_____| |____/ \\___/|_| \\_\\|
                +----------------------------------------------------------------+
                """);
        System.out.println("""
                ----------------------------------------------------------------
                | MENU DE OPCIONES
                ----------------------------------------------------------------""");

        for (int i = 0; i < menu.length; i++) {
            System.out.println("| " + (i + 1) + " - " + menu[i]);

        }
        System.out.println("----------------------------------------------------------------");
    }

    public void ciudades() {
        int j = 0;
        for (int y = 0; y < dt_ciudad.length; y++) {
            if (j == 3) {
                System.out.println();
                System.out.print("| " + y + " | " + dt_ciudad[y]);

                j = 0;
            } else {
                System.out.print("| " + y + " | " + dt_ciudad[y]);

            }
            if (dt_ciudad[y].length() < 9) {

                System.out.print("\t \t \t");
            } else if (dt_ciudad[y].length() < 16) {
                System.out.print("\t \t");
            } else if (dt_ciudad[y].length() < 18) {
                System.out.print("\t ");
            }
            j++;
        }
    } //

    public void pasajeDisponible() {
        switch (count) {
            case 1: // seleccion de origen
                ciudades();
                System.out.println(); // Salto de linea
                System.out.println("""
                        -------------------------------------------------------------------
                        | ELIJA EL ORIGEN
                        -------------------------------------------------------------------""");
                origen = CSS.nextInt();
                if (origen >= 0 && origen < dt_ciudad.length - 1) {
                    String ciuddOrigen = dt_ciudad[origen];
                    System.out.println("usted eligio: " + ciuddOrigen.toUpperCase());
                    count = 2;
                    dt_origen.add(ciuddOrigen); // gurdando la informacion
                    proxViaje[0] = ciuddOrigen;// destino
                    pasajeDisponible();
                } else if (origen == dt_ciudad.length - 1) {
                    System.out.println("volviendo al menu principal ......");
                    this.reinicio();
                    this.MenuOpciones();
                } else {
                    System.out.println("opcion invalida, intente nuevamente");
                    this.pasajeDisponible();
                    count = 1;
                }
                break;
            case 2: // selecion de destino
                Rutas(origen);
                System.out.println(); // salto de linea
                System.out.println("""
                        -------------------------------------------------------------------
                        | ELIJA EL DESTINO
                        -------------------------------------------------------------------""");
                int destino = CSS.nextInt();
                if (destino >= 0 && destino < rutas.size()) {
                    for (int i = 0; i < dt_ciudad.length; i++) {
                        if (rutas.get(destino).equals(dt_ciudad[i])) {
                            System.out.println("usted eligio viajar a : " + dt_ciudad[i].toUpperCase());
                            dt_destino.add(dt_ciudad[i]); // guardando la informacion
                            proxViaje[1] = dt_ciudad[i];// destino
                            destino = i;
                            count = 3;
                            break;
                        }
                    }
                } else {
                    System.out.println("opcion invalida, intente nuevamente");
                    rutas.clear();
                    count = 2;
                }
                pasajeDisponible();
                break;
            case 3:// registro de fecha de viaje

                switch (count1) {
                    case 1:// seleccion del dia de viaje
                        System.out.println("indeque fecha de viaje (DD):");
                        dia = CSS.nextInt();
                        if (dia > 0 && dia < 31) {
                            System.out.println("fecha correcta");
                            count1 = 2;
                        } else {
                            System.out.println("fecha incorrecta, intente nuevamente");
                            count1 = 1;
                        }
                        fechaViaje = " " + dia;
                        pasajeDisponible();
                        break;
                    case 2:// seleccion del mes de viaje
                        System.out.println("indeque fecha de viaje (MM):");
                        opmes = CSS.nextInt();
                        if (opmes >= meshoy && opmes < 12) {
                            if (dia <= diahoy && opmes >= meshoy) {
                                System.out.println("fecha correcta");
                                stadoval = "validado"; // validadion
                                // break;
                            } else if (dia >= diahoy && opmes >= meshoy) {
                                System.out.println("fecha correcta");
                                stadoval = "validado";
                            } else {
                                stadoval = "snVal"; // sin validacion
                                System.out.println("fecha no valida ingresa de nuevo");
                                pasajeDisponible();
                            }
                        } else {
                            pasajeDisponible();
                        }
                        if (stadoval.equals("validado")) {
                            for (int i = 0; i < mes.length; i++) {
                                if (opmes - 1 == i) {
                                    if (dia <= mes[i]) {
                                        System.out.println("fecha correcta");
                                        count1 = 3;
                                        // break;
                                    } else {
                                        System.out.println("fecha incorrecta, intente nuevamente");
                                        pasajeDisponible();
                                    }
                                }
                            }

                        }
                        fechaViaje = " " + fechaViaje + mes;
                        pasajeDisponible();
                        break;
                    case 3:
                        System.out.println("Indique la fecha de viaje (AA)");
                        anio = CSS.nextInt();
                        if (anio >= 2024 && anio <= 2025) {
                            System.out.println("fecha correcta");
                            fechaViaje = dia + " " + opmes + " " + anio; // fecha de viaje
                            dt_fechaViaje.add(fechaViaje);
                            proxViaje[2] = fechaViaje;
                            if (retorno == false) {
                                // agreegndo la fecha de viaje
                                dt_fechaRetorno.add(fechaViaje); // guardando datos
                                proxViaje[3] = fechaViaje; // guadndo datos;
                                this.listaPasajesDisponibles();

                            } else {
                                System.out.println("desea fecha de retorno? \n 0 - no \n 1 - si");
                                // siguiente paso --- listar pasajes disponibles
                                int opRetorn = CSS.nextInt();
                                if (opRetorn == 1) {
                                    retorno = true;
                                    count1 = 4;

                                    pasajeDisponible();
                                } else {
                                    dt_fechaRetorno.add("sin reorno");
                                    proxViaje[3] = "sin reorno";
                                    // System.out.println(fechaViaje);
                                    retorno = false;
                                    this.listaPasajesDisponibles();
                                }
                            }

                        }

                        // pasajeDisponible();
                        break;
                    case 4:
                        diahoy = dia;
                        meshoy = opmes;
                        aniohoy = anio;
                        retorno = false;
                        System.out.println("Ahora indique la fecha de retorno... ");
                        count1 = 0;
                        pasajeDisponible();
                        break;
                    default:
                        System.out.println("Ahora indique la fecha de viaje... ");
                        count1 = 1;
                        pasajeDisponible();
                        break;
                }
                break;
            default:
                System.out.println("usted selecciono " + menu[1]);
                count = 1;
                pasajeDisponible();
                break;
        }
    }

    public void listaPasajesDisponibles() {

        System.out.println("""
                -----------------------------------------------------------------------
                | LISTA DE PASAJES DISPONIBLES
                -----------------------------------------------------------------------""");
        System.out.println();
        for (int y = 0; y < 4; y++) {
            int horaRean = rand.nextInt(horarioViajes.length);
            if (!coleccionHorario.contains(horarioViajes[horaRean])) {
                coleccionHorario.add(y, horarioViajes[horaRean]);
            } else {
                y -= 1;
            }
        }
        for (int i = 0; i < coleccionHorario.size(); i++) {
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("OPcion #" + (i + 1));
            System.out
                    .println("\t desde: " + dt_origen.get(idPas) + "\t \t \t Con destino a: " + dt_destino.get(idPas));
            System.out.println("\t fecha de vaiaje: \t " + dt_fechaViaje.get(idPas));
            System.out.println("\t Horarios de salida: " + coleccionHorario.get(i));
        }
        this.RegistroPasajero();
    }

    public void RegistroPasajero() {
        switch (count2) {
            case 1:

                System.out.println("seleccione los asientos a ocupar");
                this.bus();
                for (int i = 0; i < cantPasajeros; i++) {
                    System.out.println((i + 1) + "Indique el el asiento: ");
                    int asiento = CSS.nextInt();
                    if (asiento > 0 && asiento < 31) {
                        dt_nroAsiento.add(String.valueOf(asiento));
                        // pasjaro sus datos
                        System.out.println("Comencemos ingresando sus datos: ");
                        count2 = 2;
                        RegistroPasajero();
                    } else {
                        System.out.println("El asiento no es valido");
                        i--;
                    }
                }
                break;
            case 2: // registrando los datos del cliente
                CSS.nextLine();
                System.out.println(Labels[0] + ": "); // nombre del cliente
                dt_Nombre.add(CSS.nextLine());
                System.out.println(Labels[1] + ": "); // Apellido del cliente
                dt_apellido.add(CSS.nextLine());
                System.out.println(Labels[2] + ": "); // Año de Naiminetos
                dt_anioNac.add(CSS.nextLine());
                System.out.println(Labels[3] + ": "); // Nacionalidad del cliente
                dt_nacionalidad.add(CSS.nextLine());
                System.out.println(Labels[4]);// numero de documnto
                dt_nroDoc.add(CSS.nextLine());

                dt_Estado.add(true); // agrgando estados de pasajero
                if (cantPasajeros > nroPas) {
                    idPas++;
                    nroPas++;
                    count2 = 1;
                    this.RegistroPasajero();
                }
                // dt_costo.add(150.0);
                // pasajeros
                for (int i = 0; i < cantPasajeros; i++) {
                    System.out.println("Pasajero #" + (i + 1));
                    System.out.println("--------------------------------");
                    for (int j = 0; j < proxViaje.length; j++) {
                        System.out.println("\t" + Labels[5 + j] + ": " + proxViaje[j]);
                    }
                    System.out.println("--------------------------------");
                    System.out.println("\tNombre: " + dt_Nombre.get(i));
                    System.out.println("\tApellido: " + dt_apellido.get(i));
                    System.out.println("\tAño de Nacimiento: " + dt_anioNac.get(i));
                    System.out.println("\tNacionalidad: " + dt_nacionalidad.get(i));
                    System.out.println("\tNro. Documento: " + dt_nroDoc.get(i));
                    System.out.println("\tAsiento: " + dt_nroAsiento.get(i));
                    System.out.println("-----------------------------------------------------------------------------");
                    dt_costo.add(150.0);
                    Double valCosto = dt_costo.get(i);
                    costo = costo + valCosto;
                }
                System.out.println("\tCosto:" + costo);
                System.out.println("fin del registro");
                this.Preparacion();
                break;
            default: // inicia el switch con lista viajes por realizar
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("ELIJA UNA DE LAS OPCIONES MOSTRADAS --------------------------------------");
                int opPasajehorario = CSS.nextInt();
                if (opPasajehorario > 0 && opPasajehorario < 5) {
                    for (int i = 0; i < horarioViajes.length; i++) {
                        String horaVia = String.valueOf(horarioViajes[i]);
                        if (coleccionHorario.get(opPasajehorario - 1).equals(horaVia)) {
                            dt_horaSalida.add(horarioViajes[i]);

                            System.out.println("Horario seleccionado: " + dt_horaSalida.get(idPas));
                            proxViaje[4] = horarioViajes[i]; // horas de viaje
                            count2 = 1;
                        }
                    }
                } else {
                    System.out.println("la opcion no es valida");
                    this.RegistroPasajero();
                }
                System.out.println("""
                        -----------------------------------------------------------------------
                        | REGISTRO DE PASAJEROS
                        -----------------------------------------------------------------------
                        """);
                System.out.println("indeique la cantidad a registrar");
                cantPasajeros = CSS.nextInt();
                proxViaje[5] = String.valueOf(cantPasajeros); // cantidad de pasajeros regsitrados

                RegistroPasajero();
                break;
        }

    }

    public void bus() {
        int nros = 1;
        int[] valores = new int[30];
        System.out.println("-----------------------------------------------------------------------------");
        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j <= 9; j++) {
                valores[nros - 1] = nros;
                System.out.print("[  " + nros + " ] ");
                if (j == 9) {
                    if (i == 1) {
                        // System.out.println("2");
                        System.out.println();
                        System.out.println("""
                                -----------------------------------------------------------------------------
                                -----------------------------------------------------------------------------  """);
                        nros = -1;
                    } else if (i == 2) {
                        // System.out.println("3");
                        System.out.println();
                        nros = 0;
                    } else {
                        System.out.println();
                    }
                }
                nros += 3;

            }
            System.out.println("-----------------------------------------------------------------------------");

        }
    }

    public void Rutas(int origen) { // destinos
        origen = origen + 1;
        int cantDest = 0, y = 0, pos = 0;
        int j = 0;
        do {
            switch (origen) {
                case 1:// Lima
                    int[] lima = { 1, 25, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17 };
                    cantDest = lima.length;
                    pos = lima[y];
                    break;
                case 2:// ica
                    int[] ica = { 0, 22, 3, 4, 6, 10, 15, 12, 17, 18, 19 };
                    cantDest = ica.length;
                    pos = ica[y];
                    break;
                case 3: // Huancayo
                    int[] huancayo = { 0, 12, 16, 17 };
                    cantDest = huancayo.length;
                    pos = huancayo[y];
                    break;
                case 4: // abancay
                    int[] abancay = { 0, 2, 10, 16, 19 };
                    cantDest = abancay.length;
                    pos = abancay[y];
                    break;
                case 5: // Arequipa
                    int[] arequipa = { 0, 1, 6, 10, 13, 16, 17, 18, 19, 24 };
                    cantDest = arequipa.length;
                    pos = arequipa[y];
                    break;
                case 6:// Ayacucho
                    int[] ayacucho = { 0, 16, 17 };
                    cantDest = ayacucho.length;
                    pos = ayacucho[y];
                    break;
                case 7: // Camana
                    int[] camana = { 0, 1, 4, 16, 17, 18, 19 };
                    cantDest = camana.length;
                    pos = camana[y];
                    break;
                case 8: // chepen
                    int[] chepen = { 0, 25, 8, 9, 16, 17, 21, 23 };
                    cantDest = chepen.length;
                    pos = chepen[y];
                    break;
                case 9: // chiclayo
                    int[] chiclayo = { 0, 25, 7, 9, 16, 17, 21, 23 };
                    cantDest = chiclayo.length;
                    pos = chiclayo[y];
                    break;
                case 10: // chimbote
                    int[] chimbote = { 0, 25, 7, 8, 16, 17, 21, 23 };
                    cantDest = chimbote.length;
                    pos = chimbote[y];
                    break;
                case 11: // cusco
                    int[] cusco = { 0, 1, 3, 4, 16, 19, 24 };
                    cantDest = cusco.length;
                    pos = cusco[y];
                    break;
                case 12: // huaraz
                    int[] huaraz = { 1, 16, 17 };
                    cantDest = huaraz.length;
                    pos = huaraz[y];
                    break;
                case 13: // jauja
                    int[] jauja = { 0, 2, 16, 17 };
                    cantDest = jauja.length;
                    pos = jauja[y];
                    break;
                case 14:// Juliaca
                    int[] juliaca = { 4, 24 };
                    cantDest = juliaca.length;
                    pos = juliaca[y];
                    break;
                case 15:// La Merced
                    int[] laMerced = { 0, 16, 17, 20 };
                    cantDest = laMerced.length;
                    pos = laMerced[y];
                    break;
                case 16:// Lima Otocngo
                    int[] limaOtocngo = { 1, 3, 4, 5, 6, 10, 19 };
                    cantDest = limaOtocngo.length;
                    pos = limaOtocngo[y];
                    break;
                case 17:// Lima Javier
                    int[] limaJavier = { 1, 2, 22, 25, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 18, 19, 20, 21, 23 };
                    cantDest = limaJavier.length;
                    pos = limaJavier[y];
                    break;
                case 18:// Lima Plaza Norte
                    int[] limaPlazaNorte = { 1, 2, 25, 3, 4, 5, 6, 7, 9, 10, 11, 12, 19, 21, 23 };
                    cantDest = limaPlazaNorte.length;
                    pos = limaPlazaNorte[y];
                    break;
                case 19: // marcona
                    int[] marcona = { 0, 1, 4, 6, 16, 19 };
                    cantDest = marcona.length;
                    pos = marcona[y];
                    break;
                case 20:// nasca
                    int[] nasca = { 0, 1, 22, 3, 4, 6, 10, 15, 16, 17, 18 };
                    cantDest = nasca.length;
                    pos = nasca[y];
                    break;
                case 21:// Oxopamapa
                    int[] oxopampa = { 0, 14, 16, 17 };
                    cantDest = oxopampa.length;
                    pos = oxopampa[y];
                    break;
                case 22:// pacasmayo
                    int[] pacasmayo = { 0, 25, 7, 8, 9, 16, 17, 23 };
                    cantDest = pacasmayo.length;
                    pos = pacasmayo[y];
                    break;
                case 23:// paracas
                    int[] paracas = { 0, 1, 4, 6, 16, 18, 19 };
                    cantDest = paracas.length;
                    pos = paracas[y];
                    break;
                case 24:// piura
                    int[] piura = { 0, 25, 7, 8, 9, 16, 17, 21 };
                    cantDest = piura.length;
                    pos = piura[y];
                    break;
                case 25:// puno
                    int[] puno = { 4, 10, 13 };
                    cantDest = puno.length;
                    pos = puno[y];
                    break;
                case 26:// trujillo
                    int[] trujillo = { 0, 7, 8, 9, 16, 17, 21, 23 };
                    cantDest = trujillo.length;
                    pos = trujillo[y];
                    break;
                default:
                    break;
            }

            if (j == 3) {
                System.out.println();
                System.out.print("| " + y + " | " + dt_ciudad[pos]);
                j = 0;
            } else {
                System.out.print("| " + y + " | " + dt_ciudad[pos]);
            }
            if (dt_ciudad[pos].length() < 9) {
                System.out.print("\t \t \t");
            } else if (dt_ciudad[pos].length() < 16) {
                System.out.print("\t \t");
            } else if (dt_ciudad[pos].length() < 18) {
                System.out.print("\t ");
            }
            rutas.add(dt_ciudad[pos]);
            j++;

            // System.out.println(pos+" "+dt_ciudad[pos]);

            y++;
        } while (y < cantDest);
    }

    public void Servicios() {
        int num = 0;
        System.out.println("lista de Servicios");
        for (String lisServ : servics) {
            System.out.println("| " + num + " - " + lisServ);
            num++;
        }
        System.out.println("seleccione una de las opciones");
        int opServ = CSS.nextInt();
        if (opServ < servics.length) {
            System.out.println("usted eligio " + servics[opServ] + " esta opcion");
        }
        switch (opServ) {
            case 1:
                precioServ = 2.5;
                break;
            case 2:
                precioServ = 1.2;
                break;
            case 3:
                precioServ = 1.5;
                break;
            case 4:
                precioServ = 1.5;
                break;
            case 5:
                precioServ = 2;
                break;
            default:
                System.out.println("vuelva ingresar otro numero");
                this.Servicios();
                break;

        }
        this.RegistroPasajero();
    }

    public void PasajeroFrecuente() {
        CSS.nextLine();
        System.out.println("ingrese su dni");
        boolean pasFrec = false;
        String dniPf = CSS.nextLine();// IMGRESNDO EL DENI
        int frecuencia = 0;
        for (int i = 0; i > dt_nroDoc.size(); i++) {
            if (dniPf.equals(dt_nroDoc.get(i))) {
                // System.out.println("usted tiene descuento")
                frecuencia++;
            } else {
                pasFrec = false;
            }
        }
        if (frecuencia > 1) {
            pasFrec = true;
            dt_dniFrec.add(dniPf);
            dt_codDes.add(String.valueOf(generarCodDesc()));
        }
        if (pasFrec == false) {
            System.out.println("dni no valido");
            System.out.println("desea intentarlo \n 0 - no \n 1 - si");
            int opIntent = CSS.nextInt();
            if (opIntent == 1) {
                this.pasajeDisponible();
            } else {
                System.out.println("volviendo al menu");
                opMenu = 0;
                this.MenuOpciones();
            }
        } else {
            System.out.println("desea comprar pasaje \n 0 - no \n 1 - si ");
            int opCompra = CSS.nextInt();
            if (opCompra == 1) {
                System.out.println("empecemos con su reguistro");
                this.pasajeDisponible();
            } else {
                System.out.println("guarde el codigo para su proximo viaje");
                this.MenuOpciones();
            }
        }

    }

    public int generarCodDesc() {
        int coddescuento = rand.nextInt(9000) + 1000;
        return coddescuento;
    }

    public void Preparacion() { // asignacion de datos faltndes

        if (prepa.equals("iniciando")) {
            idPas = dt_Estado.size();
        }

        for (String NOT : proxViaje) {
            System.out.println(NOT);

        }
        int val = dt_Estado.size();
        dt_origen.add(proxViaje[0]);
        dt_destino.add(proxViaje[1]);
        dt_fechaViaje.add(proxViaje[2]);
        dt_fechaRetorno.add(proxViaje[3]);
        dt_horaSalida.add(proxViaje[4]);

        System.out.println(" GENERANDO BOLETA " + cantPasajeros + " " + idPas);

        System.out.println("nombre_ " + dt_Nombre.size());
        System.out.println("apellido_ " + dt_apellido.size());
        System.out.println("doc " + dt_nroDoc.size());
        System.out.println("origen " + dt_origen.size());
        System.out.println("destino " + dt_destino.size());
        System.out.println("costo " + dt_costo.size());
        System.out.println("Numro asient " + dt_nroAsiento.size());
        System.out.println("Hora salida " + dt_horaSalida.size());
        System.out.println("fecha retorno " + dt_fechaRetorno.size());
        System.out.println("Fecha de viaje " + dt_fechaViaje.size());
        System.out.println("estado  " + dt_Estado.size());
        idPas = cantPasajeros - dt_Estado.size();
        this.iniciarSistemaPago();

        //this.BoletoViaje();

    }

    public void BoletoViaje() {
        String edad = "";
        try {
            FileWriter writer = new FileWriter("boleta.txt");

            for (int j = 0; j < cantPasajeros; j++) {
                if (Integer.parseInt(dt_anioNac.get(idPas)) > 2006) {
                    edad = "menor de edad";
                } else {
                    edad = "Adulta";
                }
                sala[0] = dt_Nombre.get(idPas); // nombre
                sala[1] = dt_apellido.get(idPas); // apellido
                sala[2] = edad; // adulto o menor de edad
                sala[3] = dt_nacionalidad.get(idPas); // nacionealidad
                sala[4] = dt_nroDoc.get(idPas); // numero de documento
                sala[5] = dt_origen.get(idPas); // Origen
                sala[6] = dt_destino.get(idPas);
                sala[7] = dt_fechaViaje.get(idPas);
                sala[8] = dt_fechaRetorno.get(idPas);
                sala[9] = dt_horaSalida.get(idPas);
                sala[10] = String.valueOf(dt_costo.get(idPas));

                writer.write("===== BOLETA DE VIAJE =====\n");
                writer.write("Empresa: " + infoEmpresa[0] + "\n");
                writer.write("Dirección: " + infoEmpresa[1] + "\n");
                writer.write("RUC: " + infoEmpresa[2] + "\n\n");

                for (int i = 0; i < Labels.length; i++) {
                    writer.write(Labels[i] + ": " + sala[i] + "\n");
                }

                writer.write("\nTotal Costo: " + costo + "\n");
                writer.write("Metodo de pago: " + metodPago + "\n");
                writer.write("===========================\n");
                idPas++;
            }
            writer.close();
            this.reinicio();
            this.MenuOpciones();
            //System.out.println("Boleta impresa en D:\\boleta.txt");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir la boleta.");
            e.printStackTrace();
        }
    }

    /*
     * public void BoletoViaje() {
     * System.out.println(" GENERANDO BOLETA " + cantPasajeros + " " + idPas);
     * // int recorr = Integer.valueOf(proxViaje[5]);// conversion a entero
     * int recorr = cantPasajeros;
     * for (int i = 0; i < recorr; i++) {
     * String empresa = infoEmpresa[0];
     * String direccion = infoEmpresa[1];
     * String ruc = infoEmpresa[2];
     * String numeroBoleta = "B001-00804276";
     * String nombreCliente = dt_Nombre.get(idPas) + " " + dt_apellido.get(idPas);
     * String servicio = dt_origen.get(idPas) + " - " + dt_destino.get(idPas);
     * String asiento = dt_nroAsiento.get(idPas);
     * String fechaViaje = dt_fechaViaje.get(idPas);
     * String horaSalida = "06:00 AM";
     * String lugarEmbarque = dt_origen.get(idPas);
     * String lugarLlegada = dt_destino.get(idPas);
     * String RetornoViaje = dt_fechaRetorno.get(idPas);
     * double precio;
     *
     * for (int j = 0; j < dt_dniFrec.size(); j++) {
     * if (dt_nroDoc.get(idPas).equals(dt_dniFrec.get(j))) {
     * esFrecuente = true;
     * }
     * }
     * if (esFrecuente == true) {
     * descuento = dt_costo.get(idPas) * 0.1;
     * precio = dt_costo.get(idPas) - (dt_costo.get(idPas) * 0.1);
     * } else {
     * precio = dt_costo.get(idPas);
     *
     * }
     * double subtotal = (precio * 1.8) - precio;
     * double total = precio;
     * double igv = total - subtotal;
     * // Imprimir boleta
     * System.out.println("""
     * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     * || BOLETA DE VIAJE - CRUZ DEL SUR ||
     * ---------------------------------------------------------------------
     * """);
     * System.out.println("        || Empresa: " + empresa);
     * System.out.println("        || Dirección: " + direccion);
     * System.out.println("        || RUC: " + ruc);
     * System.out.println("        || Número de Boleta: " + numeroBoleta);
     * System.out.
     * println("        ---------------------------------------------------------------------"
     * );
     * System.out.println("        || Nombre del Cliente: " + nombreCliente);
     * System.out.println("        || Servicio: " + servicio);
     * System.out.println(" || Asiento: " + asiento);
     * System.out.println("        || Fecha de Viaje: " + fechaViaje);
     * System.out.println("        || Hora de Salida: " + horaSalida);
     * System.out.
     * println("        ---------------------------------------------------------------------"
     * );
     * System.out.println("        || Lugar de Embarque: " + lugarEmbarque);
     * System.out.println("        || Lugar de Llegada: " + lugarLlegada);
     * System.out.
     * println("        ---------------------------------------------------------------------"
     * );
     * System.out.println("        || presio total: S/ " + subtotal);
     * System.out.println("        || Retorno de Viaje: " + RetornoViaje);
     * if (esFrecuente == true) {
     *
     * System.out.println("        || tiene descuento: " + descuento);
     * }
     * System.out.println("        || subtotal del Pasaje: S/ " + subtotal);
     * System.out.println("        || igv(18%) del Pasaje: S/ " + igv);
     * System.out.println("        || Precio del Pasaje: S/ " + total);
     * System.out.println("""
     * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     * """);
     * // CSS.nextLine();
     * System.out.println(
     * " -----------------------------------------------------------------------------------------"
     * );
     * idPas++;
     * }
     * this.reinicio();
     * System.out.println("gracias por su preferncia");
     * this.MenuOpciones();
     *
     * }
     */
    public void viajeCorporativo() {
        System.out.println("buienvenido a viaje corporativo");
    }

    public void Contactos() {
        ArrayList<Object> contacto = new ArrayList<>();
        System.out.println("-----------------------------------------------");
        System.out.println("|         Bienvenido a Contacto Cruz del Sur   |");
        System.out.println("-----------------------------------------------");
        System.out.println("""
                --------------------------------------
                |            OPCIONES                 |
                --------------------------------------
                | 1 |  SOY PASAJERO                   |
                | 2 |  SOY EMPRESA                    |
                --------------------------------------
                """);
        System.out.println("Seleccione una opción: ");
        int opcion = CSS.nextInt();
        CSS.nextLine();
        if (opcion == 1) {
            System.out.println("-----------------------------------------------");
            System.out.println("|               Soy Pasajero                   |");
            System.out.println("-----------------------------------------------");
            System.out.println("""
                    --------------------------------------
                    |           MOTIVO DE CONTACTO        |
                    --------------------------------------
                    | 1 |  SOLICITUD / PEDIDO             |
                    | 2 |  SUGERENCIA / COMENTARIO        |
                    | 3 |  PREGUNTA / DUDA                |
                    --------------------------------------
                    """);
            System.out.println("Seleccione el motivo: ");
            int motivo = CSS.nextInt();
            CSS.nextLine();
            System.out.println("Escriba su motivo de contacto:");
            contacto.add(CSS.nextLine());
            System.out.println("Ingrese su Nombre y Apellidos:");
            contacto.add(CSS.nextLine());
            contacto.add(Telefono());
            contacto.add(DNI());
            contacto.add(Correo());
            System.out.println("""
                    -----------------------------------------------
                    | Gracias por contactarnos. Procesaremos su    |
                    | solicitud lo antes posible.                  |
                    | Puedes contactarnos llamando al 311-5050 o   |
                    | escribiendo a nuestro WhatsApp 993 555 276.  |
                    -----------------------------------------------""");
        } else if (opcion == 2) {
            System.out.println("-----------------------------------------------");
            System.out.println("|               Soy Empresa                    |");
            System.out.println("-----------------------------------------------");
            contacto.add(validarRUC());
            System.out.println("Ingrese sus Nombres y Apellidos:");
            contacto.add(CSS.nextLine());
            contacto.add(Telefono());
            System.out.println("Ingrese su Razón Social:");
            contacto.add(CSS.nextLine());
            contacto.add(Correo());
            System.out.println(
                    "Describa el servicio que necesita (Cantidad de buses, cantidad de pasajeros, fecha, etc):");
            contacto.add(CSS.nextLine());
            System.out.println("""
                    -----------------------------------------------
                    | Gracias por contactarnos. Procesaremos su    |
                    | solicitud lo antes posible.                  |
                    | Puedes contactarnos llamando al 311-5050 o   |
                    | escribiendo a nuestro WhatsApp 993 555 276.  |
                    -----------------------------------------------""");
        } else {
            System.out.println("Opción no válida. El programa finalizará.");
        }
        System.out.println("Gracias por utilizar Contacto Cruz del Sur.");
        this.reinicio();

    }
    public void ViajeCorp() {
        // Variables
        String EmailCorp;
        String TelefonoCorp;
        String RazonSocialCorp;
        String RucCorp;
        String ComentarioCorp;
        CSS.nextLine();
        // cotisacion
        // vc-viaje corporativo
        System.out.println("""
                ----------------------------------------------------------------
                |                   PIDE TU COTIZACION                         |
                ----------------------------------------------------------------
                |     INRESE SU EMAIL:                                         |
                ----------------------------------------------------------------""");
        EmailCorp = CSS.nextLine();
        System.out.println("""
                ----------------------------------------------------------------
                |     INGRESE SU TELEFONO:                                     |
                ----------------------------------------------------------------""");
        TelefonoCorp = CSS.nextLine();
        System.out.println("""
                ----------------------------------------------------------------
                |     INGRESE SU RAZON SOCIAL:                                |
                ----------------------------------------------------------------""");
        RazonSocialCorp = CSS.nextLine();
        System.out.println("""
                ----------------------------------------------------------------
                |     INGRESE SU RUC:                                          |
                ----------------------------------------------------------------""");
        RucCorp = CSS.nextLine();
        System.out.println("""
                ----------------------------------------------------------------
                |     INGRESE SU COMENTARIO:                                   |
                ----------------------------------------------------------------""");
        ComentarioCorp = CSS.nextLine();

        System.out.println("""
                    ----------------------------------------------------------------
                    |              Registrado con exito                            |
                    ----------------------------------------------------------------
                    le estaremos enviando la cotizacion en su correo. gracias por su preferencia
                """);

    }
    //metodos pago frank
    public void iniciarSistemaPago() {
        System.out.println("""
                --------------------------------------------------------------------------
                | SISTEMA DE PAGO
                --------------------------------------------------------------------------
                """);
        while (true) {
            switch (count5) {
                case 1 -> validarCorreo();
                case 2 -> validarTelefono();
                case 3 -> mostrarMenuOpcionesPago();
                default -> count5 = 1;
            }
        }
    }
    public void validarCorreo() {
        System.out.print("Correo electrónico: ");
        String correo = CSS.nextLine();
        N_Verficacion_pago.add(correo);
        guardarEnArchivo("Correo: " + correo);
        if (esCorreoValido(correo)) {
            System.out.println("Correo electrónico aceptado.");
            count5 = 2;
        } else {
            System.out.println("Correo electrónico incorrecto. Intente nuevamente.");
            count5 = 1;
        }
    }
    private boolean esCorreoValido(String correo) {
        String regex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,7}$";
        return Pattern.matches(regex, correo);
    }
    public void validarTelefono() {
        System.out.print("Número telefónico: ");
        String telefono = CSS.nextLine();
        tel_Verficacion_pago.add(telefono);
        guardarEnArchivo("Teléfono: " + telefono);

        if (esTelefonoValido(telefono)) {
            System.out.println("Número telefónico aceptado.");
            count5 = 3;
        } else {
            System.out.println("Número telefónico incorrecto. Debe comenzar con 9 y tener 9 dígitos.");
            count5 = 2;
        }
    }
    public boolean esTelefonoValido(String telefono) {
        String regex = "\\b9\\d{8}\\b";
        return Pattern.matches(regex, telefono);
    }
    public void mostrarMenuOpcionesPago() {
        System.out.println("----- Menú de Opciones de Pago -----");
        String[] opciones = {"Pago con tarjeta de débito o crédito", "Pago con Yape", "Pago en efectivo", "Salir"};
        for (int i = 0; i < opciones.length; i++) {
            System.out.println(" | "+(i + 1) + " | " + opciones[i]);
        }
        int opcion = CSS.nextInt();
        CSS.nextLine();
        switch (opcion) {
            case 1 -> realizarPagoTarjeta();
            case 2 -> realizarPagoYape();
            case 3 -> realizarPagoEfectivo();
            case 4 -> {
                System.out.println("Saliendo del menú.");
                return;
            }
            default -> System.out.println("Opción no válida. Intente de nuevo.");
        }
    }
    public void realizarPagoTarjeta() {
        System.out.println("Has seleccionado Pago con tarjeta de débito.");
        String tarjeta = solicitarDato("Ingrese el número de la tarjeta de débito(13 digitos): ", this::esTarjetaValida,
                "Número de tarjeta inválido. Debe contener 16  dígitos.");
        numeroTarjeta.add(tarjeta);
        guardarEnArchivo("Número de Tarjeta: " + tarjeta);

        String fecha = solicitarDato("Ingrese la fecha de vencimiento (MM/AA): ", this::esFechaVencimientoValida,
                "Fecha de vencimiento inválida. Intente nuevamente.");
        tar_Verficacion_V.add(fecha);
        guardarEnArchivo("Fecha de Vencimiento: " + fecha);

        System.out.println("Pago realizado con éxito.");
        guardarEnArchivo("Pago con tarjeta de débito realizado con éxito.");
        metodPago="targeta de credito";
        this.BoletoViaje();
    }
    public void realizarPagoYape() {
        System.out.println("Has seleccionado Pago con Yape.");
        System.out.println("MONTO: "+costo);
        String telefono = solicitarDato("Ingrese el número de teléfono asociado a Yape: ", this::esTelefonoValido,
                "Número telefónico incorrecto. Debe comenzar con 9 y tener 9 dígitos.");
        telefonoYape.add(telefono);
        guardarEnArchivo("Teléfono Yape: " + telefono);

        System.out.print("¿Desea confirmar el pago a " + telefono + " por "+costo +" (si/no): ");
        String op_PY=CSS.nextLine();
        if (op_PY.equals("si")) {
            System.out.println("Pago con Yape de 0 confirmado a " + telefono + ".");
            guardarEnArchivo("Pago con Yape confirmado a " + telefono + ".");
           // this.BoletoViaje();
        } else {
            System.out.println("Pago cancelado.");
            guardarEnArchivo("Pago con Yape cancelado.");
        }
        metodPago="pago con YAPE";
        this.BoletoViaje();
    }
    public void realizarPagoEfectivo() {
        int codigoPago = rand.nextInt(9000) + 1000;
        System.out.println("Método de pago por Pago efectivo seleccionado.");
        System.out.println("\nEmpresa: PagoEfectivo.\nCódigo de pago (CIP): " + codigoPago + "\nMonto: " + costo); // monto
        guardarEnArchivo("Código de pago (CIP): " + codigoPago);
        System.out.print("¿Desea confirmar el pago? (si/no): ");
        String op_PE=CSS.nextLine();
        if (op_PE.equals("si")) {
            System.out.println("Pago en efectivo confirmado.");
            guardarEnArchivo("Pago en efectivo confirmado.");
            metodPago="pago en efectivo";
            this.BoletoViaje();
        } else {
            System.out.println("Pago cancelado.");
            guardarEnArchivo("Pago en efectivo cancelado.");
        }
    }
    public void guardarEnArchivo(String texto) {
        try (FileWriter writer = new FileWriter("D:\\datos_pago.txt", true)) {
            writer.write(texto + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }
    public boolean esTarjetaValida(String tarjeta) {
        return tarjeta.matches("\\d{16}");
    }
    public boolean esFechaVencimientoValida(String fecha) {
        Pattern pattern = Pattern.compile("^(0[1-9]|1[0-2])/(\\d{2})$");
        Matcher matcher = pattern.matcher(fecha);
        if (!matcher.matches()) return false;
        String[] partes = fecha.split("/");
        int mes = Integer.parseInt(partes[0]);
        int anio = Integer.parseInt(partes[1]) + 2000;
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaTarjeta = LocalDate.of(anio, mes, 1);
        return !fechaTarjeta.isBefore(fechaActual);
    }
    public String solicitarDato(String mensaje, Function<String, Boolean> validacion, String mensajeError) {
        while (true) {
            System.out.print(mensaje);
            String dato = CSS.nextLine();
            if (validacion.apply(dato)) return dato; // Usar la funcion para validar
            System.out.println(mensajeError);
        }
    }
    //final de metodo de pago

}
