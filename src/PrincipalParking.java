public class PrincipalParking {

    // Definición de los datos clave del problema
    private static final int numeroPlazas = 3;
    private static final int numeroCoches = 7;

    public static void main(String[] args) {

        // Crear el aparcamiento
        Aparcamiento aparcamiento = new Aparcamiento(numeroPlazas);
        System.out.println("Aparcamiento abierto con " + numeroPlazas + " plazas");
        System.out.println("----");

        // Array para guardar los hilos
        Thread[] hilosCoches = new Thread[numeroCoches];

        // Crear los coches y lanzarlos en hilos
        for (int i = 1; i <= numeroCoches; i++) {
            String nombreCoche = "Coche - " + i;

            // Creamos la tarea pasandole el mismo aparcamiento
            Coche cocheRunnable = new Coche(aparcamiento, nombreCoche);

            // Creamos el hilo y lo lanzamos
            // (i-1) rellenará las posiciones 0 hasta 6
            hilosCoches[i - 1] = new Thread(cocheRunnable, nombreCoche);

            // Iniciamos la ejecución del hilo
            hilosCoches[i - 1].start();
        }

        // Esperaramos a que todos los hilos terminen
        for (Thread hilo : hilosCoches) {
            try {
                // Ahora 'hilo' nunca será null, porque el bucle
                // anterior ha rellenado las 7 posiciones.
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("-----");
        System.out.println("Todos los coches han salido. Aparcamiento cerrado.");
    }
}