import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Gestiona el acceso concurrente a las plazas del aparcamiento.
 * Utiliza un Semaphore para limitar el número de coches que pueden
 * entrar simultáneamente.
 */
public class Aparcamiento {

    // El semáforo que protege las plazas. Inicializado con el número de plazas.
    private final Semaphore semaforo;

    // Un contador atómico para llevar la cuenta de las plazas ocupadas
    // Se usa AtomicInteger para evitar condiciones de carrera al actualizar el contador
    // desde múltiples hilos sin usar 'synchronized'.
    private final AtomicInteger plazasOcupadas;

    /**
     * Constructor que inicializa el aparcamiento con un número
     * específico de plazas.
     *
     * @param plazas El número de plazas disponibles en el aparcamiento.
     */
    public Aparcamiento(int plazas) {
        // 'true' activa el modo "justo" (FIFO), para que los coches que llegan primero, entren primero.
        this.semaforo = new Semaphore(plazas, true);
        this.plazasOcupadas = new AtomicInteger(0);
    }

    /**
     * Metodo que invoca un coche para intentar entrar al aparcamiento.
     * Llama a semaphore.acquire(), que es bloqueante.
     * El hilo (coche) se quedará esperando si no hay plazas libres.
     *
     * @param nombreCoche El nombre del coche que intenta entrar.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void entrar(String nombreCoche) throws InterruptedException {
        // 1. Adquirir un permiso del semáforo
        // Si no hay permisos (plazas) disponibles, este metodo bloquea el hilo
        // hasta que otro hilo llame a release().
        semaforo.acquire();

        // 2. Una vez adquirido el permiso, actualizar contador e informar
        int ocupadas = plazasOcupadas.incrementAndGet();
        System.out.println(nombreCoche + " ha entrado. Plazas ocupadas: " + ocupadas);
    }

    /**
     * Metodo que invoca un coche para salir del aparcamiento.
     * Llama a semaphore.release() para liberar la plaza.
     *
     * @param nombreCoche El nombre del coche que sale.
     */
    public void salir(String nombreCoche) {
        // 1. Actualizar contador e informar
        int ocupadas = plazasOcupadas.decrementAndGet();
        System.out.println(nombreCoche + " ha salido. Plazas ocupadas: " + ocupadas);

        // 2. Liberar el permiso (la plaza) para que otro coche en espera pueda entrar
        semaforo.release();
    }
}