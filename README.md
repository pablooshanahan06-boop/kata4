# Kata 4 - Servicio Web REST con Javalin y Cliente HTTP

Implementación de un servicio web ligero en Java 21 con **Javalin** y un cliente HTTP desacoplado siguiendo **Arquitectura Hexagonal (Puertos y Adaptadores)** y serialización JSON con **Gson**.

## Arquitectura del Proyecto

```text
src/main/java/software/ulpgc/katas/
├── application/
│   ├── domain/       # Modelos inmutables (FibonacciSeries)
│   ├── ports/        # Contratos de entrada/salida y excepciones de dominio
│   └── usecases/     # Lógica de negocio pura (FibonacciCalculationUseCase)
├── infrastructure/
│   ├── adapters/http/# Cliente HTTP Java 11+, parser JSON y manejo de errores
│   ├── config/       # Configuración centralizada de URL base (ServiceConfig)
│   └── server/       # Endpoints REST Javalin (/hola, /fibonacci)
├── ServerMain.java   # Punto de entrada del servidor (puerto 8080)
└── ClientMain.java   # Punto de entrada del cliente consumidor