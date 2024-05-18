package test;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;

import datos.Funciones;

public class PruebaEficenciaOrdenamientos extends Thread{
	// Colores a usar en la consola
	public static String COMIENZO 	= "\033[0;32m";
	public static String FINAL 		= "\033[0;36m";
	public static String RESET 		= "\033[0m";
	
	// ARRAY ORIGINAL
	private static int array[];
	
	private int hiloID;
	private String algoritmo;	// Nombre del algoritmo
	private double tInicial;	// Tiempo inicial en nanosegundos
	private double tFinal;		// Indica la duración del algoritmo en ms
	private int arrayCopia[];	// Array propio (copia del original)
	
	// CONSTRUCTOR
	public PruebaEficenciaOrdenamientos(int hiloID) {
		super();
		this.hiloID = hiloID;
		this.arrayCopia = Arrays.copyOf(array, array.length);	// Directamente cuando creo el hilo copio el array
	}
	
	// GETTERS
	public int getHiloID() {
		return hiloID;
	}
	public String getAlgoritmo() {
		return algoritmo;
	}
	public double gettFinal() {
		return tFinal;
	}

	// MANEJO DEL CRONOMETRO
	private void cronStart() {
		tInicial = System.nanoTime();
	}
	private void cronEnd() {
		tFinal = (System.nanoTime()-tInicial)/1000000;
	}
	
	// MENSAJES
	private void mostrarInicio() {
		System.out.println(COMIENZO + "#" + this.hiloID + " " + this.algoritmo + RESET);
		cronStart();
	}
	private void mostrarFin() {
		cronEnd();
		System.out.println(FINAL + "#" + this.hiloID + " " + this.algoritmo + "   \t" + this.tFinal + " ms" + RESET);
	}

	// MANEJO DE HILOS
	@Override
	public void run() {
		switch(hiloID) {
			case 0: // BURBUJA
				this.algoritmo = "Burbuja";
				mostrarInicio();
				Funciones.burbuja(this.arrayCopia);
				mostrarFin();
			break;
			case 1: // SELECCION
				this.algoritmo = "Selección";
				mostrarInicio();
				Funciones.seleccion(this.arrayCopia);
				mostrarFin();
			break;
			case 2: // INSERSION
				this.algoritmo = "Insersion";
				mostrarInicio();
				Funciones.insersion(this.arrayCopia);
				mostrarFin();
			break;
			case 3: // MERGE
				this.algoritmo = "Merge";
				mostrarInicio();
				Funciones.merge(this.arrayCopia);
				mostrarFin();
			break;
			case 4: // QUICK
				this.algoritmo = "QuickSort";
				mostrarInicio();
				Funciones.quickSort(this.arrayCopia, 0, this.arrayCopia.length-1);
				mostrarFin();
			break;
			case 5: // HEAP
				this.algoritmo = "HeapSort";
				mostrarInicio();
				Funciones.heapSort(this.arrayCopia);
				mostrarFin();
			break;
			// En caso que haya mas algoritmos se tendrían que incluir aqui
			// y modificar la cantidad de algoritmos cuando se crean los hilos
		}
	}
	
	public static void main(String[] args) {
		// ELIGO LA CANTIDAD DE ELEMENTOS Y CREO EL ARRAY
		int[] cantElementos = {10,1000,500000,1000000};
		array = Funciones.generarArrayAleatorio(cantElementos[0], -1000, 1000);
		System.out.println("A probar con " + array.length + " elementos\n");
		
		// CREO LOS HILOS NESCESARIOS Y LOS EMPIEZO A EJECUTAR
		PruebaEficenciaOrdenamientos[] hilos = new PruebaEficenciaOrdenamientos[6];	// Son 6 algoritmos
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new PruebaEficenciaOrdenamientos(i);	// Simplemente le asigno el HiloID
		}
		for (int i = 0; i < hilos.length; i++) {
			hilos[i].start();
		}
		
		
		// A LA ESPERA DE RESULTADOS
		for (int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// ORDENO LOS RESULTADOS
		Arrays.sort(hilos, Comparator.comparingDouble(PruebaEficenciaOrdenamientos::gettFinal));
		
		// MUESTRO LOS RESULTADOS
        System.out.println("\n\n----------------------------------------------------");
        System.out.println("\tRESULTADOS FINALES - " + ((new DecimalFormat("#,###").format(array.length))) + " elementos");
        System.out.println("----------------------------------------------------");
        System.out.printf("%-5s %-15s %-15s %-5s%n", "Top", "Algoritmo", "Duración", "Hilo");
        System.out.println("----------------------------------------------------");
        DecimalFormat formato = new DecimalFormat("0.0000");
        for (int i = 0; i < hilos.length; i++) {
            PruebaEficenciaOrdenamientos h = hilos[i];
            System.out.printf("%-5d %-15s %-15s %-5s%n", (i + 1), h.getAlgoritmo(), formato.format(h.gettFinal()) + " ms", "#" + h.getHiloID());
        }
        System.out.println(RESET + "----------------------------------------------------");

	}

}