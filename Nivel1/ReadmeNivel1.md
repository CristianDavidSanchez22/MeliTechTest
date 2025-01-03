# Descripción del Código

Este código en Java implementa una solución para encontrar la combinación óptima de artículos que se pueden comprar con un cupón de descuento, sin exceder una cantidad máxima especificada. Utiliza una técnica de programación dinámica similar al problema de la mochila (knapsack problem).

## Estructura del Código

El código se compone de una clase principal `Coupon` que contiene dos métodos:

1. `main`: Método principal que inicializa los datos de los artículos y el monto máximo del cupón, y luego llama al método `calculate` para obtener la combinación óptima de artículos.
2. `calculate`: Método que implementa la lógica de programación dinámica para encontrar la combinación óptima de artículos.

## Explicación del Método `calculate`

El método `calculate` toma dos parámetros:
- `items`: Un mapa que contiene los artículos y sus respectivos precios.
- `amount`: El monto máximo del cupón.

El método sigue estos pasos:
1. Convierte el mapa de artículos en una lista de entradas.
2. Inicializa una tabla de programación dinámica (`dp`) y una tabla de seguimiento (`taken`).
3. Llena la tabla `dp` con los valores óptimos utilizando un bucle anidado.
4. Recupera la combinación óptima de artículos a partir de la tabla `taken`.

## Ejecución del Código

Para ejecutar el código, asegúrate de tener un entorno de desarrollo Java configurado. Luego, compila y ejecuta la clase `Coupon`:

```sh
javac Coupon.java
java Coupon