package ej3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private int numberOfProcesses;
	private int percentageOfUse;

	private Matrix data;
	private Matrix utilization;
	private Matrix events;
	
	private Scanner scanner = new Scanner(System.in);
	
	// IMPORTANTE: Al cargar los procesos ingresarlos ordenado por inicio, de menor a mayor.
	private void tests() {
		/*// Datos para hacer las pruebas del ejercicio hecho en clase practica del pdf
		
		this.numberOfProcesses = 4;
		this.percentageOfUse = 80;
		
		this.data = new Matrix(this.numberOfProcesses);
		
		// 1er fila
		this.data.addValue(10.00, 0, 0);
		this.data.addValue(4.0, 0, 1);
		
		// 2da fila
		this.data.addValue(10.10, 1, 0);
		this.data.addValue(3.0, 1, 1);
		
		// 3er fila
		this.data.addValue(10.15, 2, 0);
		this.data.addValue(2.0, 2, 1);
		
		// 4ta fila
		this.data.addValue(10.20, 3, 0);
		this.data.addValue(2.0, 3, 1);*/
		
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
	}

	private void loadAndSortTheData() {
		System.out.print("Ingrese la cantidad de procesos (valor entero mayor que 0): ");
		this.numberOfProcesses = this.scanner.nextInt();
		
		System.out.print("Ingrese el porcentaje de uso para E/S (valor entero entre 1 y 100): ");
		this.percentageOfUse = this.scanner.nextInt();
		
		System.out.println();
		
		this.data = new Matrix(this.numberOfProcesses);

		Double startOfTheNewProcess, durationOfTheNewProcess, startOfTheOldProcess, durationOfTheOldProcess;
		
		// Carga del inicio y duración de los procesos en orden ascendente (de menor a mayor) usando el valor de inicio.
		System.out.print("Ingrese el valor de inicio del proceso 1 (valor decimal, ej: 14,15): ");
		this.data.addValue(this.scanner.nextDouble(), 0, 0);
		System.out.print("Ingrese el valor de la duración del proceso 1 (valor decimal, ej: 2,15): ");
		this.data.addValue(this.scanner.nextDouble(), 0, 1);
		
		for (int i=1; i < this.numberOfProcesses; i++) {	
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
	}
	
	public void loadAndSortTheDataMatrixInAscendingOrder() {
		//loadAndSortTheData();
		
		tests();
		
		System.out.println("Tabla de inicio y duración de los procesos:\n");
		this.data.showMatrix(false);
		//System.out.println(this.data);
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
		//System.out.println(this.utilization);
	}
	
	// La creación de la tabla de eventos finaliza cuando toda una columna se llena de 0s.
	private boolean finished() {
		int numberOfRow = 1;
		
		while (numberOfRow < this.events.getSize()) {
			ArrayList<Double> row = this.events.getRow(numberOfRow);
			
			if (row.get(row.size()-1).equals(0.0)) {
				numberOfRow++;				
			} else {
				return false;
			}
		}
		
		return true;
	}
	
	// Obtenemos una lista de posiciones de la matriz de datos, las cuales cumplen con que no se hayan iniciado todavia y además no haya terminado todavia.
	private ArrayList<Integer> listOfUnfinishedProcesses(Double time) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int numberOfRow = 0;
		
		while (numberOfRow < this.data.getSize()) {
			ArrayList<Double> eventMatrixRow = this.events.getRow(numberOfRow+1);
			
			if (this.data.getValue(numberOfRow, 0)<time && this.events.getValue(numberOfRow+1, eventMatrixRow.size()-1) != 0.0) {
				list.add(numberOfRow);
			}
			
			numberOfRow++;
		}
		
		return list;
	}
	
	public void createEventMatrix() {
		this.events = new Matrix(this.numberOfProcesses + 1);
	
		// 1ra vuelta
		this.events.addValue(this.data.getValue(0, 0), 0, 0);
		
		for (int i=1; i<this.numberOfProcesses+1; i++) {
			this.events.addValue(this.data.getValue(i - 1, 1), i, 0);
		}
		
		// Resto de vueltas
		ArrayList<Integer> list;
		int minute = 1;
		
		while (! finished()) {
			Double time = this.data.getValue(0, 0) + (double) minute/100;
			this.events.addValue(time, 0, minute);
			
			list = listOfUnfinishedProcesses(time);
			
			// Realizamos la operación y cargamos el nuevo valor, solo para los procesos que se estan ejecutando y no terminaron aún.
			for (int position=0; position<list.size(); position++) {
				Double newValue = this.events.getValue(list.get(position)+1, minute-1) - (this.utilization.getValue(list.size()-1, 2)*1);
				// duda en position de events.getValue CREO que esta bien.
				if (newValue <= 0.001) {
					this.events.addValue(0.0, list.get(position)+1, minute);					
				} else {
					this.events.addValue(newValue, list.get(position)+1, minute);					
				}
			}
			
			// Copiamos los valores de los procesos que no se ejecutaron, o que ya terminaron.
			int numberOfColumn = minute-1;
			
			for (int numberOfRow=1; numberOfRow<this.events.getSize(); numberOfRow++) {
				Double oldValue = this.events.getValue(numberOfRow, numberOfColumn); // aqui tira error Index 22 out of bounds for length 22

				int position;
				boolean located = false;
				int i = 0;
				
				while (i<list.size() && located==false) {
					position = list.get(i) + 1;
					
					if (numberOfRow == position) {
						located = true;
					} else {
						i++;						
					}
				}
				
				if (! located) {
					this.events.addValue(oldValue, numberOfRow, numberOfColumn+1);
				}
			}
			
			minute++;
		}

		System.out.println("Tabla de secuencia de eventos:\n");
		this.events.showMatrix(true);
		//System.out.println(this.events);
	}
	
	public void execute() {
		loadAndSortTheDataMatrixInAscendingOrder();
		createUtilizationMatrix();
		createEventMatrix();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.execute();
	}

}
