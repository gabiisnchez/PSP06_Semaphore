
/**
 * Punto de entrada de la aplicación.
 * Crea el aparcamiento y lanza todos los hilos (Coches)
 * para simular la concurrencia.
 */

public class Parking {

    // Definición de los datos clave del problema
    private static final int NUMERO_PLAZAS = 3;
    private static final int NUMERO_COCHES = 7;

    public static void main(String[] args) {

        // 1. Crear el Aparcamiento (el recurso compartido)
        // Se crea UNA SOLA instancia.
        Aparcamiento aparcamiento = new Aparcamiento(NUMERO_PLAZAS);
        System.out.println("Aparcamiento abierto con " + NUMERO_PLAZAS + " plazas.");
        System.out.println("---");

        // Array para guardar los hilos y esperar por ellos al final
        Thread[] hilosCoches = new Thread[NUMERO_COCHES];

        // 2. Crear los 7 coches (Runnables) y lanzarlos en hilos
        for (int i = 1; i <= NUMERO_COCHES; i++) {
            String nombreCoche = "Coche-" + i;

            // Creamos la tarea (Coche) pasándole el MISMO aparcamiento a todos
            Coche cocheRunnable = new Coche(aparcamiento, nombreCoche);

            // Creamos el Hilo (Thread) a partir de la tarea
            hilosCoches[i - 1] = new Thread(cocheRunnable, nombreCoche);

            // Iniciar la ejecución concurrente del hilo
            hilosCoches[i - 1].start();
        }

        // 3. Esperar a que todos los hilos terminen
        for (Thread hilo : hilosCoches) {
            try {
                hilo.join(); // Espera a que el hilo (coche) termine su ejecución (run())
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("---");
        System.out.println("Todos los coches han terminado. Aparcamiento cerrado.");
    }
}
