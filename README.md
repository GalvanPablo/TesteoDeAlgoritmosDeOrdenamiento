# Introducción
El objetivo de este análisis es comparar el rendimiento de seis algoritmos de ordenamiento (Burbuja, Selección, Inserción, Merge Sort, QuickSort y HeapSort) para arrays de distintos tamaños: 10, 1.000, 500.000 y 1.000.000 elementos y establecer una relación respecto a su complejidad algorítmica.
Dichos arrays fueron generados aleatoriamente con valores entre -1000 y 1000.

## Hardware de la PC de pruebas
- Procesador: I5-7400 @3.00GHz - 4 Núcleos - 4 Hilos
- Memoria: 16gb DDR4 3200MHz

## Registro de tiempos obtenidos en milisegundos (ms)

| Algoritmo\Elementos | 10    | 1.000  | 500.000     | 1.000.000     |
|---------------------|-------|--------|------------|-------------|
| Burbuja             | 0.0084| 6.561  | 365547.2891| 1471319.607 |
| Selección           | 0.0052| 3.2087 | 73046.3171 | 315796.4003 |
| Insersion           | 0.0054| 3.2581 | 87811.7192 | 298608.9318 |
| Merge               | 0.0223| 0.7719 | 286.3232   | 392.4377    |
| QuickSort           | 0.0122| 0.3267 | 171.2621   | 357.4141    |
| HeapSort            | 0.0103| 1.1763 | 173.6375   | 266.6758    |

# Análisis

## Análisis según cantidad de datos (n)
- **Conjunto pequeños (10 Elementos):** Todos los algoritmos completan rápidamente el ordenamiento, con tiempos en el rango de milisegundos. Las diferencias de tiempo no son significativas debido al pequeño tamaño del array.
- **Conjuntos medianos (1.000 elementos):** Los algoritmos cuadráticos (Burbuja, Selección, Inserción) empiezan a mostrar tiempos de ejecución significativamente mayores. Merge Sort, QuickSort y HeapSort, con complejidad O(n log n), muestran mejores tiempos.
- **Conjuntos grandes (500.000 y 1.000.000 elementos):** La diferencia entre los algoritmos de complejidad O(n^2) y O(n log n) se vuelve muy pronunciada. QuickSort y Merge se destacan por sus tiempos de ejecución considerablemente menores. HeapSort también muestra buen rendimiento, aunque ligeramente por detrás de QuickSort y Merge Sort.

# Conclusión
A medida que aumenta la cantidad de elementos en los datos, se observa una distinción clara entre los algoritmos de ordenamiento. En escalas menores, los algoritmos muestran comportamientos similares en cuanto a su tiempo de ejecución. Sin embargo, al aumentar la cantidad de elementos, emergen dos grupos bien definidos. Este patrón se hace evidente en el gráfico, donde ya con 1.000 elementos se pueden distinguir claramente 2 grupos. A partir de este punto, la diferencia en la complejidad algorítmica se vuelve evidente. Clasificando los algoritmos según su complejidad, se observan los mismos dos grupos.
- **Cuadrática O(n^2) - Grupo A:**
  - Burbuja
  - Inserción
  - Selección
- **Casi-lineal O(n log n) - Grupo B:**
  - Merge
  - QuickSort
  - HeapSort

Estos datos subrayan la importancia de considerar la complejidad algorítmica al seleccionar un algoritmo de ordenamiento para una determinada tarea. Si bien los algoritmos de complejidad cuadrática pueden ser adecuados para conjuntos de datos pequeños, rápidamente se vuelven ineficientes para conjuntos de datos más grandes. En cambio, los algoritmos con complejidad O(n log n) son más adecuados para manejar grandes volúmenes de datos debido a su tiempo de ejecución más predecible y eficiente en estos escenarios.
