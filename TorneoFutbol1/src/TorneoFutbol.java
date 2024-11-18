import java.io.*;
import java.util.*;

public class TorneoFutbol {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Cargar equipos desde un archivo
        ArrayList<String> equipos = cargarEquiposDesdeArchivo("equipos.txt");
        if (equipos.size() < 2) {
            System.out.println("El archivo de equipos debe contener al menos 2 equipos.");
            scanner.close();
            return;
        }

        ArrayList<String> resultados = new ArrayList<>();
        Map<String, String> estadisticas = inicializarEstadisticas(equipos);

        while (true) {
            // Mostrar el menú
            System.out.println("\nSeleccione la etapa del torneo:");
            System.out.println("1. Octavos de final");
            System.out.println("2. Cuartos de final");
            System.out.println("3. Semifinales");
            System.out.println("4. Final");
            System.out.println("5. Salir del torneo");
            int opcion = scanner.nextInt();

            int numPartidos;
            switch (opcion) {
                case 1:
                    numPartidos = 8; // Octavos: 16 equipos, 8 partidos
                    break;
                case 2:
                    numPartidos = 4; // Cuartos: 8 equipos, 4 partidos
                    break;
                case 3:
                    numPartidos = 2; // Semifinales: 4 equipos, 2 partidos
                    break;
                case 4:
                    numPartidos = 1; // Final: 2 equipos, 1 partido
                    break;
                case 5:
                    // Guardar resultados y estadísticas al salir
                    guardarResultadosEnArchivo("resultados.txt", resultados);
                    guardarEstadisticasEnArchivo("estadisticas.txt", estadisticas);
                    System.out.println("¡Resultados y estadísticas guardados! Saliste del torneo.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    continue;
            }

            // Procesar automáticamente las etapas intermedias si hay más equipos
            while (equipos.size() > numPartidos * 2) {
                System.out.println("\nProcesando etapa intermedia...");
                equipos = procesarEtapaIntermedia(equipos, resultados, estadisticas);
            }

            // Validar que haya suficientes equipos para la etapa seleccionada
            if (equipos.size() < numPartidos * 2) {
                System.out.println("No hay suficientes equipos para esta etapa. Seleccione otra etapa.");
                continue;
            }

            // Sorteo y emparejamiento de equipos
            ArrayList<String[]> enfrentamientos = sortearPartidos(new ArrayList<>(equipos));
            equipos.clear(); // Limpiar la lista para almacenar los ganadores

            // Mostrar resultados y seleccionar ganadores automáticamente
            System.out.println("\nPartidos de la etapa seleccionada:");
            Random random = new Random();
            for (int i = 0; i < enfrentamientos.size(); i++) {
                String equipo1 = enfrentamientos.get(i)[0];
                String equipo2 = enfrentamientos.get(i)[1];
                System.out.println("Partido " + (i + 1) + ": " + equipo1 + " vs " + equipo2);

                // Seleccionar un ganador aleatorio
                String ganador = random.nextBoolean() ? equipo1 : equipo2;
                System.out.println("Ganador: " + ganador);
                equipos.add(ganador);

                // Guardar el resultado
                resultados.add("Partido " + (i + 1) + ": " + equipo1 + " vs " + equipo2 + " -> Ganador: " + ganador);

                // Actualizar estadísticas
                actualizarEstadisticas(estadisticas, equipo1, equipo1.equals(ganador));
                actualizarEstadisticas(estadisticas, equipo2, equipo2.equals(ganador));
            }

            // Verificar si ya tenemos un campeón
            if (equipos.size() == 1) {
                String campeon = equipos.get(0);
                System.out.println("\n¡El torneo ha finalizado! El equipo campeón es: " + campeon);
                resultados.add("\n¡El campeón del torneo es: " + campeon + "!");
                estadisticas.put(campeon, estadisticas.get(campeon) + "\n- Campeón: Sí");
                break;
            }
        }

        scanner.close();
    }

    /**
     * Procesar automáticamente una etapa intermedia.
     */
    public static ArrayList<String> procesarEtapaIntermedia(ArrayList<String> equipos, ArrayList<String> resultados, Map<String, String> estadisticas) {
        ArrayList<String[]> enfrentamientos = sortearPartidos(new ArrayList<>(equipos));
        ArrayList<String> ganadores = new ArrayList<>();
        Random random = new Random();

        for (String[] enfrentamiento : enfrentamientos) {
            String equipo1 = enfrentamiento[0];
            String equipo2 = enfrentamiento[1];
            String ganador = random.nextBoolean() ? equipo1 : equipo2;
            ganadores.add(ganador);
            resultados.add("Partido intermedio: " + equipo1 + " vs " + equipo2 + " -> Ganador: " + ganador);
            actualizarEstadisticas(estadisticas, equipo1, equipo1.equals(ganador));
            actualizarEstadisticas(estadisticas, equipo2, equipo2.equals(ganador));
        }

        return ganadores;
    }

    /**
     * Cargar los equipos desde un archivo de texto.
     */
    public static ArrayList<String> cargarEquiposDesdeArchivo(String rutaArchivo) throws IOException {
        ArrayList<String> equipos = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            equipos.add(linea);
        }
        br.close();
        return equipos;
    }

    /**
     * Inicializar las estadísticas para cada equipo.
     */
    public static Map<String, String> inicializarEstadisticas(ArrayList<String> equipos) {
        Map<String, String> estadisticas = new HashMap<>();
        for (String equipo : equipos) {
            estadisticas.put(equipo, "- Partidos jugados: 0\n- Partidos ganados: 0");
        }
        return estadisticas;
    }

    /**
     * Actualizar estadísticas de un equipo.
     */
    public static void actualizarEstadisticas(Map<String, String> estadisticas, String equipo, boolean gano) {
        String[] estad = estadisticas.get(equipo).split("\n");
        int partidosJugados = Integer.parseInt(estad[0].split(": ")[1]) + 1;
        int partidosGanados = Integer.parseInt(estad[1].split(": ")[1]) + (gano ? 1 : 0);
        estadisticas.put(equipo, "- Partidos jugados: " + partidosJugados + "\n- Partidos ganados: " + partidosGanados);
    }

    /**
     * Método recursivo para realizar el sorteo de los partidos.
     */
    public static ArrayList<String[]> sortearPartidos(ArrayList<String> equipos) {
        ArrayList<String[]> enfrentamientos = new ArrayList<>();
        if (equipos.size() == 2) {
            enfrentamientos.add(new String[]{equipos.get(0), equipos.get(1)});
            return enfrentamientos;
        }
        Collections.shuffle(equipos);
        enfrentamientos.add(new String[]{equipos.remove(0), equipos.remove(0)});
        enfrentamientos.addAll(sortearPartidos(equipos));
        return enfrentamientos;
    }

    /**
     * Guardar los resultados en un archivo de texto.
     */
    public static void guardarResultadosEnArchivo(String rutaArchivo, ArrayList<String> resultados) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));
        for (String resultado : resultados) {
            bw.write(resultado);
            bw.newLine();
        }
        bw.close();
    }

    /**
     * Guardar estadísticas en un archivo.
     */
    public static void guardarEstadisticasEnArchivo(String rutaArchivo, Map<String, String> estadisticas) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));
        bw.write("Estadísticas del Torneo\n\n");
        for (Map.Entry<String, String> entry : estadisticas.entrySet()) {
            bw.write(entry.getKey() + ":\n" + entry.getValue() + "\n\n");
        }
        bw.close();
    }
}
