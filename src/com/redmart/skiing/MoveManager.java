package com.redmart.skiing;

import java.util.ArrayList;
import java.util.List;

public class MoveManager {
	private int max;
	
	private static int[][]path;
	
	private List<Point> maxTraversedPath;
	private int maxPathSum;
	private int currPathSum;
	
	MoveManager(int max,int[][] path){
		this.max = max;
		this.path = path;
		maxTraversedPath = new ArrayList<Point>();
	}

	
	public Point isValidPoint(Point point,Moves move){
		Point newPoint = new Point(point.getX()+move.getNextMove()[0],point.getY()+move.getNextMove()[1]);
		if(isValidCartesianPoint(newPoint)){
			if(valueOfPoint(newPoint)<valueOfPoint(point)){
				return newPoint;
			}
		}
		
		return null;
		
	}
	
	public static int valueOfPoint(Point point){
		return path[point.getX()][point.getY()];
	}
	
	private boolean isValidCartesianPoint(Point point){
		int x = point.getX();
		int y = point.getY();
		if(x<0){return false;}
		if(x>max-1){return false;}
		if(y<0){return false;}
		if(y>max-1){return false;}
		
		return true;
	}
	
	
	public boolean isNextMovePossible(Point point){
		boolean possible = false;
		Point newPoint;
		for(Moves move: Moves.values()){
			newPoint = new Point(point.getX()+move.getNextMove()[0],point.getY()+move.getNextMove()[1]);
			if(isValidCartesianPoint(newPoint)){
//				System.out.println(newPoint.toString()+" : "+valueOfPoint(newPoint));
//				
				if(valueOfPoint(newPoint)<valueOfPoint(point)){
		//			System.out.println("ValidNextMove:"+valueOfPoint(newPoint));
					possible = true;
					break;
					
				}
			}
			possible = false;

		}
		
		return possible;
		
	}
	
	public void updateRecords(List<Point> currentPath,int[][] path){
		if(currentPath.size()>maxTraversedPath.size()){
			System.out.println("Current Size is MOre than maxHistorical.  "+currentPath.size()+" : "+maxTraversedPath.size());
			maxTraversedPath = new ArrayList<Point>(currentPath);
			currPathSum = depthOfList(currentPath);
			maxPathSum = currPathSum;
			return;
		}
		
		if(currentPath.size()==maxTraversedPath.size()){
			System.out.println("Current Size is same as maxHistorical.  "+currentPath.size()+" : "+maxTraversedPath.size());
			
			currPathSum = depthOfList(currentPath);
			if(currPathSum>maxPathSum){
				System.out.println("Current PAth Depth is MOre than maxHistorical.  "+currPathSum+" : "+maxPathSum);
				
				maxTraversedPath = new ArrayList<Point>(currentPath);
				maxPathSum = currPathSum;
			}
		}
	}
	
	public static int depthOfList(List<Point> list){
		Point firstPoint = list.get(0);
		Point lastPoint = list.get(list.size()-1);

		return MoveManager.valueOfPoint(firstPoint)-MoveManager.valueOfPoint(lastPoint);
		
	}
	
	public List<Point> getMaxTraversedPath(){
		return maxTraversedPath;
	}
	
	public int getMaxPathSum(){
		return maxPathSum;
	}

}
