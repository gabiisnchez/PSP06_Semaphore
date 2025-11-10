import java.util.Random;

/**
 * Representa un Coche, que es una tarea ejecutable (Runnable)
 * en un hilo independiente. [cite: 61, 90]
 * Compite por una plaza en el Aparcamiento.
 */
public class Coche implements Runnable {

    // Referencia al recurso compartido (el aparcamiento)
    private final Aparcamiento aparcamiento;

    // Nombre identificativo del coche
    private final String nombre;

    // Generador de números aleatorios para el tiempo de espera
    private final Random random = new Random();

    /**
     * Constructor para crear un nuevo Coche.
     *
     * @param aparcamiento La instancia compartida del aparcamiento.
     * @param nombre       El nombre de este coche (ej: "Coche-1").
     */
    public Coche(Aparcamiento aparcamiento, String nombre) {
        this.aparcamiento = aparcamiento;
        this.nombre = nombre;
    }

    /**
     * Define el comportamiento del hilo (Coche).
     * Sigue la secuencia: entrar, esperar y salir.
     */
    @Override
    public void run() {
        try {
            // 0. Coche llega e intenta entrar
            System.out.println(nombre + " llega e intenta entrar...");

            // 1. Llamar a entrar() (metodo bloqueante)
            aparcamiento.entrar(nombre);

            // 2. Simular tiempo aparcado (entre 1 y 4 segundos)
            // Thread.sleep(milisegundos)
            int tiempoAparcado = 1000 + random.nextInt(3000);
            System.out.println(nombre + " está aparcado por " + (tiempoAparcado / 1000.0) + " segundos.");
            Thread.sleep(tiempoAparcado);

            // 3. Llamar a salir() para liberar la plaza
            aparcamiento.salir(nombre);

        } catch (InterruptedException e) {
            // Buena práctica: gestionar la InterruptedException
            System.err.println(nombre + " fue interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}
