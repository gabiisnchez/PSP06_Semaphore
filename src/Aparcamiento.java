import java.util.concurrent.Semaphore;
/**
 * Gestiona el acceso concurrente a las plazas del aparcamiento.
 * Utiliza un Semaphore para limitar el número de coches que pueden
 * entrar simultáneamente.
 */
public class Aparcamiento {

    // El semáforo que protege las plazas.
    private final Semaphore semaforo;

    // CAMBIO: Se usa un 'int' simple, ya que 'synchronized'
    // gestionará la concurrencia sobre esta variable.
    private int plazasOcupadas;

    // Objeto usado para el bloqueo. Podría ser 'this',
    // pero usar un objeto privado es una práctica más robusta.
    private final Object lockContador = new Object();


    /**
     * Constructor que inicializa el aparcamiento con un número
     * específico de plazas.
     *
     * @param plazas El número de plazas disponibles en el aparcamiento.
     */
    public Aparcamiento(int plazas) {

        this.semaforo = new Semaphore(plazas, true);
        this.plazasOcupadas = 0; // Inicializa el contador
    }

    /**
     * Metodo que invoca un coche para intentar entrar al aparcamiento.
     *
     * @param nombreCoche El nombre del coche que intenta entrar.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void entrar(String nombreCoche) throws InterruptedException {

        // 1. Adquirir permiso de semáforo (controla las 3 plazas)
        // El hilo se bloquea aquí si el parking está lleno
        semaforo.acquire();

        // 2. Adquirir bloqueo 'synchronized' (controla el contador y la consola)
        // Solo 1 hilo puede ejecutar este bloque a la vez.
        synchronized (lockContador) {
            // 3. Incrementar e imprimir (operación atómica)
            plazasOcupadas++;
            System.out.println(nombreCoche + " ha entrado. Plazas ocupadas: " + plazasOcupadas);
        }
    }

    /**
     * Metodo que invoca un coche para salir del aparcamiento.
     *
     * @param nombreCoche El nombre del coche que sale.
     */
    public void salir(String nombreCoche) {

        // 1. Adquirir bloqueo 'synchronized'
        // Asegura que la salida se registra en orden
        synchronized (lockContador) {
            // 2. Decrementar e imprimir (operación atómica)
            plazasOcupadas--;
            System.out.println(nombreCoche + " ha salido. Plazas ocupadas: " + plazasOcupadas);
        }

        // 3. Liberar permiso de semáforo
        // Se hace fuera del 'synchronized' para permitir que otro coche
        // inicie el proceso de 'entrar()' inmediatamente.
        semaforo.release();
    }
}