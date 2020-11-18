package ej1;

import java.util.Scanner;

public class Main {

	private int numberOfProcesses;
	private int percentageOfUse;

	private Matrix data;
	private Matrix utilization;
	private Matrix events;
	
	public Scanner scanner = new Scanner(System.in);
	
	public void execute() {
		loadDataMatrix();
		createUtilizationMatrix();
		createEventMatrix();
	}
	
	private void sortDataMatrix() {
		/*Matrix auxiliar = new Matrix(this.numberOfProcesses);
		
		auxiliar.addValue(this.data.getValue(0, 0), 0, 0);
		auxiliar.addValue(this.data.getValue(0, 0), 0, 1);
		
		Double start, duration;
		
		for (int i=1; i<this.numberOfProcesses; i++) {
			start = this.data.getValue(i, 0);
			duration = this.data.getValue(i, 1);
			
			for (int j=0; j<auxiliar.getMatrix().size(); j++) {
				if (start < auxiliar.getValue(j, 0)) {
					auxiliar.addValue(start, j, 0);
					auxiliar.addValue(duration, j, 1);
				}
			}
		}
		
		this.data = auxiliar;*/
	}
	
	public void loadDataMatrix() {
		/*System.out.print("Ingrese la cantidad de procesos (valor entero mayor que 0): ");
		this.numberOfProcesses = this.scanner.nextInt();
		
		System.out.print("Ingrese el porcentaje de uso para E/S (valor entero entre 1 y 100): ");
		this.percentageOfUse = this.scanner.nextInt();
		
		System.out.println();
		
		this.data = new Matrix(this.numberOfProcesses);

		for (int i=0; i < this.numberOfProcesses; i++) {
			// Carga del inicio y duración de los procesos.
			System.out.print("Ingrese el valor de inicio del proceso " + (i+1) +" (valor decimal, ej: 14,15): ");
			this.data.addValue(this.scanner.nextDouble(), i, 0);
			System.out.print("Ingrese el valor de la duración del proceso " + (i+1) +" (valor decimal, ej: 2,15): ");
			this.data.addValue(this.scanner.nextDouble(), i, 1);
			
			// Ordenamos los valores que se ingresan.
			sortDataMatrix();
		
			System.out.println();
		}*/
		
		/*// Datos para hacer las pruebas para ejercicio 1
		
		this.numberOfProcesses = 3;
		this.percentageOfUse = 50;
		
		this.data = new Matrix(this.numberOfProcesses);
		
		// 1er fila
		this.data.addValue(14.20, 0, 0);
		this.data.addValue(2.0, 0, 1);
		
		// 2da fila
		this.data.addValue(14.21, 1, 0);
		this.data.addValue(2.0, 1, 1);
		
		// 3er fila
		this.data.addValue(14.26, 2, 0);
		this.data.addValue(1.0, 2, 1);*/
		
		// Datos para hacer las pruebas para ejercicio 1
		
		this.numberOfProcesses = 5;
		this.percentageOfUse = 90;
		
		this.data = new Matrix(this.numberOfProcesses);
		
		// 1er fila
		this.data.addValue(15.00, 0, 0);
		this.data.addValue(2.0127, 0, 1);
		
		// 2da fila
		this.data.addValue(15.10, 1, 0);
		this.data.addValue(1.0127, 1, 1);
		
		// 3er fila
		this.data.addValue(15.15, 2, 0);
		this.data.addValue(1.007942, 2, 1);
		
		// 4ta fila
		this.data.addValue(15.20, 3, 0);
		this.data.addValue(0.746, 3, 1);
		
		// 5ta fila
		this.data.addValue(15.25, 4, 0);
		this.data.addValue(0.48, 4, 1);
		
		System.out.println("Tabla de inicio y duración de los procesos:\n");
		this.data.showMatrix(false);
	}
	
	public void createUtilizationMatrix() {
		this.utilization = new Matrix(this.numberOfProcesses);
		
		int exponent = 1;
		double base = (double) this.percentageOfUse/100;
		
		for (int i=0; i < this.numberOfProcesses; i++) {
			this.utilization.addValue(Math.pow(base, exponent), i, 0);
			this.utilization.addValue((100 - (this.utilization.getValue(i, 0)*100)) / 100, i, 1);
			this.utilization.addValue(this.utilization.getValue(i, 1) / (i+1), i, 2);
			
			exponent++;
		}
		
		System.out.println("Tabla de utilización de los procesos con " + this.percentageOfUse + "% de espera de E/S:\n");
		this.utilization.showMatrix(false);
	}
	
	public void createEventMatrix() {
		this.events = new Matrix(this.numberOfProcesses + 1);
		
		// 1ra vuelta
		this.events.addValue(this.data.getValue(0, 0), 0, 0);
		
		for (int i=1; i<this.numberOfProcesses+1; i++) {
			this.events.addValue(this.data.getValue(i - 1, 1), i, 0);
		}
		
		// Demás vueltas
		

		System.out.println("Tabla de secuencia de eventos:\n");
		this.events.showMatrix(true);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.execute();
	}

}
