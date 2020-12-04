package ej3;

import java.util.ArrayList;

public class Matrix {

	private ArrayList<ArrayList<Double>> matrix;
	
	Matrix(int numberOfRows) {
		this.matrix = new ArrayList<ArrayList<Double>>();
		
		for (int i = 0; i< numberOfRows; i++) {			
			this.matrix.add(new ArrayList<Double>());
		}
	}

	public ArrayList<ArrayList<Double>> getMatrix() {
		return this.matrix;
	}
	
	public void addValue(Double value, int numberOfRow, int numberOfColumn) {
		getMatrix().get(numberOfRow).add(numberOfColumn, value);
	}
	
	public void setValue(Double value, int numberOfRow, int numberOfColumn) {
		getMatrix().get(numberOfRow).set(numberOfColumn, value);
	}
	
	public Double getValue(int numberOfRow, int numberOfColumn) {
		return getMatrix().get(numberOfRow).get(numberOfColumn);
	}
	
	public ArrayList<Double> getRow(int numberOfRow) {
		return this.matrix.get(numberOfRow);
	}
	
	public int getSize() {
		return this.matrix.size();
	}
	
	public void showMatrix(boolean ignoreFirstValue) {
		StringBuilder matrix = new StringBuilder("|	");
		
		int numberOfProcess = 1;
		for (int i=0; i<getMatrix().size(); i++) {
			for (int j=0; j<getMatrix().get(i).size(); j++) {
				if (ignoreFirstValue) {
					numberOfProcess = 0;
					if (i==0 && j==0) {
						matrix.append("P/T	|	" + String.format("%.4f", getValue(i, j)) + "	|	");
					} else {						
						matrix.append(String.format("%.4f", getValue(i, j)) + "	|	");
					}
					
				} else {
					if (j==0) {					
						matrix.append("Proc. " + numberOfProcess + "	|	" + String.format("%.4f", getValue(i, j)));
					} else {
						matrix.append("	|	" + String.format("%.4f", getValue(i, j)));
					}
				}
			}
			
			ignoreFirstValue = false;
			numberOfProcess++;
			matrix.append("	|\n|	"); // primer fila, ultima columna es de aqui donde sale esa ultima |
		}
		
		matrix.deleteCharAt(matrix.length() - 2);
		
		System.out.println(matrix);
	}
	
	public String toString() {
		StringBuilder matrix = new StringBuilder("|	");
		
		for (int i=0; i<getMatrix().size(); i++) {
			for (int j=0; j<getMatrix().get(i).size(); j++) {
				matrix.append("	|	" + String.format("%.4f", getValue(i, j)));
			}
			
			matrix.append("	|\n|	");
		}
		
		matrix.deleteCharAt(matrix.length() - 2);
		
		return matrix.toString();
	}
	
}
