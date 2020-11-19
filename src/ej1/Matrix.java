package ej1;

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
	
	public void showMatrix(boolean ignoreFirstRow) {
		StringBuilder matrix = new StringBuilder("|	");
		
		int numberOfProcess = 1;
		for (int i=0; i<getMatrix().size(); i++) {
			for (int j=0; j<getMatrix().get(i).size(); j++) {
				if (ignoreFirstRow && j==0) {
					matrix.append("P/T	|" + getValue(i, j) + "\n|	");
					i++;
					ignoreFirstRow = false;
				}
				
				if (j==0) {					
					matrix.append("Proc. " + numberOfProcess + "	|	" + getValue(i, j));
				} else {
					matrix.append("	|	" + getValue(i, j));
				}
			}
			numberOfProcess++;
			matrix.append("\n|	");
		}
		
		matrix.deleteCharAt(matrix.length() - 2);
		
		System.out.println(matrix);
	}
	
}
