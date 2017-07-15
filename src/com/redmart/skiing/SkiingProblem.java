package com.redmart.skiing;

public class SkiingProblem {
	
	public static void main(String[] args){
		//int max =4;
		//int[][] path = {{4,8,7,3},{2,5,9,3},{6,3,2,5},{4,4,1,6}};
		//int[][] path = {{4,8,7},{2,5,9},{6,3,2},{4,4,1}};
		
		FetchInput fetch = new FetchInput("resources/realPath.txt");
		fetch.getFile();
		
		int[][] path = fetch.getMatrix();
		int max = fetch.getSize();
		SmartSkiing smartSkiing = new SmartSkiing(max,path);
		smartSkiing.analyzeMoves();
		
		smartSkiing.printResult();
		
		
		
		
		
	}

}
