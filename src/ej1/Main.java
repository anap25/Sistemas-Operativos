package ej1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private int numberOfProcesses;
	private int percentageOfUse;

	private Matrix data;
	private Matrix utilization;
	private Matrix events;
	
	public Scanner scanner = new Scanner(System.in);
	
	public void execute() {
		loadAndSortTheDataMatrixInAscendingOrder();
		createUtilizationMatrix();
		createEventMatrix();
	}
	
	// IMPORTANTE: Al cargar los procesos ingresarlos ordenado por inicio, de menor a mayor.
	private void tests() {
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
		
		// Datos para hacer las pruebas para ejercicio 2
		
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
		
		/*// Datos para hacer las pruebas aleatorios
		
		this.numberOfProcesses = 6;
		this.percentageOfUse = 50;
		
		this.data = new Matrix(this.numberOfProcesses);
		
		// 1er fila
		this.data.addValue(14.10, 0, 0);
		this.data.addValue(10.0, 0, 1);
				
		// 2da fila
		this.data.addValue(14.15, 1, 0);
		this.data.addValue(1.0, 1, 1);
		
		// 3er fila
		this.data.addValue(14.20, 2, 0);
		this.data.addValue(2.0, 2, 1);
		
		// 4ta fila
		this.data.addValue(14.20, 3, 0);
		this.data.addValue(7.0, 3, 1);
		
		// 5ta fila
		this.data.addValue(14.25, 4, 0);
		this.data.addValue(3.0, 4, 1);
		
		// 6ta fila
		this.data.addValue(14.30, 5, 0);
		this.data.addValue(5.0, 5, 1);*/
	}

	public void loadAndSortTheDataMatrixInAscendingOrder() {
		/*System.out.print("Ingrese la cantidad de procesos (valor entero mayor que 0): ");
		this.numberOfProcesses = this.scanner.nextInt();
		
		System.out.print("Ingrese el porcentaje de uso para E/S (valor entero entre 1 y 100): ");
		this.percentageOfUse = this.scanner.nextInt();
		
		System.out.println();
		
		this.data = new Matrix(this.numberOfProcesses);

		Double startOfTheNewProcess, durationOfTheNewProcess, startOfTheOldProcess, durationOfTheOldProcess;
		
		// Carga del inicio y duración de los procesos en orden ascendente (de menor a mayor) usando el valor de inicio.
		for (int i=0; i < this.numberOfProcesses; i++) {
			if (i==0) {
				System.out.print("Ingrese el valor de inicio del proceso 1 (valor decimal, ej: 14,15): ");
				this.data.addValue(this.scanner.nextDouble(), i, 0);
				System.out.print("Ingrese el valor de la duración del proceso 1 (valor decimal, ej: 2,15): ");
				this.data.addValue(this.scanner.nextDouble(), i, 1);
			} else {
				System.out.print("Ingrese el valor de inicio del proceso " + (i+1) +" (valor decimal, ej: 14,15): ");
				startOfTheNewProcess = this.scanner.nextDouble();
				System.out.print("Ingrese el valor de la duración del proceso " + (i+1) +" (valor decimal, ej: 2,15): ");
				durationOfTheNewProcess = this.scanner.nextDouble();
			
				for (int j=0; j<i; j++) {
					if (startOfTheNewProcess < this.data.getValue(j, 0)) {
						startOfTheOldProcess = this.data.getValue(j, 0);
						durationOfTheOldProcess = this.data.getValue(j, 1);
						
						this.data.setValue(startOfTheNewProcess, j, 0);
						this.data.setValue(durationOfTheNewProcess, j, 1);
						
						startOfTheNewProcess = startOfTheOldProcess;
						durationOfTheNewProcess = durationOfTheOldProcess;
					}
				}
				
				this.data.addValue(startOfTheNewProcess, i, 0);
				this.data.addValue(durationOfTheNewProcess, i, 1);
			}
			
			System.out.println();
		}*/
		
		tests();
		
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
		// ...

		System.out.println("Tabla de secuencia de eventos:\n");
		this.events.showMatrix(true);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.execute();
	}

}
