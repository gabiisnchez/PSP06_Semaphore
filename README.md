# 📚 Tarea PSP06_Semaphore: Ejercicio de Semáforos en Java
> **Asignatura:** Programación de Servicios y Procesos
> **Autor:** Gabriel Sánchez Heredia
> **Fecha:** 10 Noviembre 2025

---

## 📋 Descripción del Proyecto

Este proyecto es un ejemplo práctico en Java que simula un aparcamiento con un número limitado de plazas. Se utiliza un `Semaphore` para controlar el acceso concurrente de múltiples coches (hilos), asegurando que el número de vehículos dentro del aparcamiento nunca exceda su capacidad.

---

## 📦 Estructura del Proyecto

```
PSP06_Semaphore/
│
├── src/
│   ├── Aparcamiento.java      # Clase que gestiona las plazas de aparcamiento usando un semáforo.
│   ├── Coche.java             # Clase que representa un coche (hilo) que intenta entrar y salir del aparcamiento.
│   └── PrincipalParking.java  # Clase principal que inicializa la simulación.
│
└── README.md                  # Este archivo.
```

---

## 🛠️ Conceptos Demostrados

La simulación se centra en la sincronización de hilos mediante el uso de semáforos y bloques `synchronized`.

### 1️⃣ `java.util.concurrent.Semaphore`

- **Ejemplo:** `Aparcamiento.java`
- **Concepto:** Un semáforo es un contador que controla el acceso a un recurso compartido. En este caso, el recurso son las plazas de aparcamiento.
    - `semaforo.acquire()`: Un coche (hilo) intenta "adquirir" un permiso. Si hay permisos disponibles (plazas libres), lo consigue y continúa. Si no, el hilo se bloquea hasta que otro hilo libere un permiso.
    - `semaforo.release()`: Un coche "libera" un permiso cuando sale del aparcamiento, permitiendo que otro coche que estaba esperando pueda entrar.

### 2️⃣ `implements Runnable`

- **Ejemplo:** `Coche.java`
- **Concepto:** La clase `Coche` implementa la interfaz `Runnable`, lo que permite que su lógica (entrar, esperar y salir del aparcamiento) se ejecute en un hilo separado. Esto permite simular la llegada concurrente de múltiples coches.

### 3️⃣ Bloque `synchronized`

- **Ejemplo:** `Aparcamiento.java`
- **Concepto:** Se utiliza para proteger el contador de plazas ocupadas (`plazasOcupadas`) y la salida por consola. Esto asegura que las operaciones de incremento/decremento y la impresión del estado sean atómicas, evitando condiciones de carrera y garantizando que la información mostrada sea coherente.

---

## 🚀 Cómo Ejecutar el Proyecto

1.  Abre el proyecto en tu IDE de Java preferido.
2.  Ejecuta el método `main` de la clase `PrincipalParking.java`.

Verás en la consola cómo los coches llegan, entran al aparcamiento (si hay plazas), permanecen un tiempo y finalmente salen, todo de forma concurrente y sincronizada.

---

## 👤 Autor

### Gabriel Sánchez Heredia

[![GitHub](https://img.shields.io/badge/GitHub-gabiisnchez-181717?style=for-the-badge&logo=github)](https://github.com/gabiisnchez)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Gabriel_Sánchez_Heredia-0A66C2?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/gabrielsanher/)

---

## 📄 Licencia

Este proyecto es parte de una práctica académica evaluable.

---

## 🙏 Agradecimientos

Práctica realizada para la asignatura de Programación de Servicios y Procesos.

---
