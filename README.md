# 💳 Sistema de Procesamiento de Pagos — Labo 6 (IS-489)

[![Java Version](https://img.shields.io/badge/Java-26-orange.svg)](https://www.oracle.com/java/)
[![Testing Framework](https://img.shields.io/badge/JUnit-5.10--Standalone-green.svg)](https://junit.org/)
[![Mocking Tool](https://img.shields.io/badge/Mockito-Core-blue.svg)](https://site.mockito.org/)
[![Architecture](https://img.shields.io/badge/Architecture-Layered__SRP-blueviolet.svg)]()

Este repositorio contiene la implementación refactorizada y robusta de un **Procesador de Pagos (`PaymentProcessor`)** desarrollado para la asignatura de Aseguramiento de la Calidad del Software. Aplica el **Principio de Responsabilidad Única (SRP)**, Inversión de Dependencias mediante el uso de interfaces de persistencia, cálculo dinámico de impuestos (IGV 18%) y validaciones estrictas de negocio.

---

## 🏛️ Arquitectura del Proyecto

El código fuente ha sido migrado al paquete profesional `com.unsch.payment`, organizando los componentes de forma desacoplada para facilitar su mantenimiento y la inyección de dependencias en pruebas unitarias:

* **`PaymentRequest`**: Objeto de Transferencia de Datos (DTO) inmutable que encapsula los datos de la transacción de entrada.
* **`PaymentResponse`**: DTO de salida que retorna el desglose financiero procesado (Impuesto y Total Neto).
* **`PaymentRepository`**: Abstracción de persistencia (Interface) que desacopla las reglas de negocio de la infraestructura o base de datos.
* **`PaymentProcessor`**: Núcleo del dominio (Servicio) encargado de ejecutar los flujos de validación y cálculo del procesamiento.
* **`Main`**: Driver interactivo por consola con captura dinámica de datos mediante `Scanner`.

---

## ⚙️ Reglas de Negocio Implementadas

1.  **Validación de Monto Mínimo**: No se permiten transacciones con montos iguales o inferiores a `S/. 0.0` (`IllegalArgumentException`).
2.  **Control de Límite Diario**: Se evalúa el acumulado del cliente consumiendo el repositorio. Si la suma del gasto diario actual más el nuevo monto supera el límite estricto de `S/. 1000.0`, la operación es denegada (`IllegalStateException`).
3.  **Cálculo Fiscal**: Aplicación automática de la tasa impositiva del **IGV (18%)** sobre el importe base.

---

## 🚀 Instrucciones de Ejecución (Consola Local)

Dado que el proyecto utiliza el enfoque clásico de compilación manual mediante el Classpath nativo, ejecuta los siguientes comandos desde la raíz del directorio (`Labo6 - IS489`):

### 1. Compilación del Código Fuente
Compila en bloque todo el árbol de archivos dentro del nuevo paquete:
```bash
javac src/com/unsch/payment/*.java