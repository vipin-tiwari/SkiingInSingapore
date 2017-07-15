package com.redmart.skiing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FetchInput {
	private int size;
	private int[][] matrix;
	private String fileName;
	
	FetchInput(){}
	
	FetchInput(String fileName){
		this.fileName =fileName;
		System.out.println("File Name Set: "+this.fileName);
	}

	public void getFile(){
		if(null==fileName){
			this.fileName="resources/testPath.txt";
		}

		File file = new File(fileName);
		Integer size = 0;
		
		List<int[]> cols = new ArrayList<>();
		int row = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			line = br.readLine();
			size = getSizeOfMatrix(line);
			this.size = size;
			matrix = new int[size][size];
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				populateMatrix(line,row);
				row++;
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private int getSizeOfMatrix(String line){
		Integer size = new Integer(line.split(" ")[0]);

		return size;
	}

	private void populateMatrix(String line,int row){
		String[] currentLine = line.trim().split(" "); 
		for (int i = 0; i < currentLine.length; i++) {
			matrix[row][i] = Integer.parseInt(currentLine[i]);   
			
		}
	}

	public int getSize(){
		return this.size;
	}

	public int[][] getMatrix(){
		return this.matrix;
	}

	public static void main(String[] args){
		FetchInput fetch = new FetchInput();
		fetch.getFile();
		
		int[][] path = fetch.getMatrix();
		int size = fetch.getSize();
		System.out.println("Size:"+size);
		
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				System.out.println(path[i][j]);
			}
		}
	}

}
