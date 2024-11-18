# Informe del Proyecto

## Introducción

Este informe documenta el desarrollo de un programa en Java para organizar y simular un torneo de fútbol profesional. El programa se ha diseñado para gestionar automáticamente las etapas del torneo: octavos de final, cuartos de final, semifinales y final. Este software tiene como objetivo generar enfrentamientos de forma aleatoria, seleccionar automáticamente a los ganadores de cada partido y determinar al campeón final.

El programa incluye la capacidad de cargar equipos desde un archivo de texto (`equipos.txt`) y guardar los resultados y estadísticas del torneo en archivos (`resultados.txt` y `estadisticas.txt`), lo que mejora su funcionalidad y permite un registro persistente de los datos.

---

## Objetivos

### Objetivo General
Desarrollar una aplicación que permita organizar y simular un torneo de fútbol profesional de manera automática, garantizando la persistencia de los datos y permitiendo una interacción flexible con el usuario, respetando las reglas establecidas para los enfrentamientos.

### Objetivos Específicos
- Implementar un sistema que permita cargar equipos desde un archivo de texto.
- Organizar enfrentamientos aleatorios para las etapas del torneo.
- Seleccionar automáticamente a los ganadores de cada partido.
- Registrar los resultados del torneo en un archivo de texto.
- Generar estadísticas detalladas para cada equipo y guardarlas en un archivo.

---

## Descripción del Programa

### Funcionalidades
1. **Carga de equipos desde un archivo de texto**:
   - Los equipos participantes se cargan automáticamente desde el archivo `equipos.txt`.
2. **Sorteo aleatorio**:
   - Cada enfrentamiento se generará de manera aleatoria en cada etapa.
3. **Selección automática de ganadores**:
   - Un ganador será elegido al azar para cada partido que se realice.
4. **Resultados del torneo**:
   - Los resultados de cada partido y el campeón final se guardan en el archivo `resultados.txt`.
5. **Estadísticas de equipos**:
   - Se registran estadísticas detalladas, como partidos jugados y victorias, en el archivo `estadisticas.txt`.

---

## Implementación

### Lenguaje y Entorno de Desarrollo
- **Lenguaje**: Java
- **Entorno**: Eclipse IDE
- **Control de versiones**: GitHub sincronizado

### Estructura del Programa

#### Clases Principales:
1. **Clase Principal (`TorneoFutbol`)**:
   - Contiene el flujo principal del programa y la interacción con el usuario.
2. **Métodos Auxiliares**:
   - `cargarEquiposDesdeArchivo`: Lee los equipos desde un archivo de texto.
   - `sortearPartidos`: Genera los enfrentamientos de forma recursiva.
   - `guardarResultadosEnArchivo`: Guarda los resultados en un archivo de texto.
   - `guardarEstadisticasEnArchivo`: Almacena estadísticas detalladas de cada equipo.
     

---

## Archivos del Programa

### Archivo de Entrada: `equipos.txt`
Contiene los nombres de los equipos participantes, uno por línea. Ejemplo:


![Captura de pantalla 2024-11-17 221211](https://github.com/user-attachments/assets/d9d7cbc5-b68e-484e-86f5-956f5348d90f)


### Archivo de Resultados: `resultados.txt`
Generado automáticamente al final de cada etapa, registra los enfrentamientos y los ganadores. Ejemplo:


![Captura de pantalla 2024-11-17 221400](https://github.com/user-attachments/assets/a2a6ebbe-d3f3-4f6f-b8d3-590f3f5813cd)


### Archivo de Estadísticas: `estadisticas.txt`
Generado automáticamente al final del torneo, detalla el desempeño de cada equipo. Ejemplo:

![Captura de pantalla 2024-11-17 222953](https://github.com/user-attachments/assets/4a8bcb1c-8c0e-48fb-9895-685d1ebf2021)


---

## Conclusiones

1. **Organización y Persistencia**:
   - El programa permite gestionar el torneo de manera eficiente, registrando los datos clave en archivos de texto para su consulta posterior.
2. **Automatización**:
   - La selección de ganadores y la generación de estadísticas se realiza automáticamente, facilitando el uso del programa.
3. **Flexibilidad**:
   - El sistema de archivos facilita la personalización y el análisis de datos.

---

## Anexos

### Repositorio GitHub
El código completo y los archivos asociados están disponibles en el siguiente enlace:
[https://github.com/AngeloChavezZ/TORNEO_FUTBOL](https://github.com/AngeloChavezZ/TORNEO_FUTBOL)

