package com.redmart.skiing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmartSkiing {
	
	private int max;
	private int[][] path;
	MoveManager moveManager;
	
	
	
	SmartSkiing(int max,int[][] path){
		this.max = max;
		this.path = path;
	}
	
	public void analyzeMoves(){
		moveManager = new MoveManager(max, path);
		List<Point> currentPath = new ArrayList<>();
		Point point;
		for(int i=0;i<max;i++){
			for(int j=0;j<max;j++){
				point = new Point(i,j);
				currentPath.clear();
				currentPath.add(point);
				System.out.println("Strating From: "+MoveManager.valueOfPoint(point));
				goDownTillEnd(point,path,max,currentPath);
				System.out.println("=============================");
				point = null;
			}
		}
	}
	
	private void goDownTillEnd(Point point,int[][] path,int max,List<Point> currentPath){
		//System.out.println("Standing at point"+path[point.getX()][point.getY()]);
		
		if(!moveManager.isNextMovePossible(point)){
			moveManager.updateRecords(currentPath,path);
			System.out.println("Path Has been Trversed: "+printPath(currentPath).toString()+" Depth"+MoveManager.depthOfList(currentPath)+" Hitorical List: "+printPath(moveManager.getMaxTraversedPath())+" Depth: "+MoveManager.depthOfList(moveManager.getMaxTraversedPath()));
			
			return;
		}
		
		//System.out.println("Next Moves Possible for "+MoveManager.valueOfPoint(point));
		for(Moves move:Moves.values()){
			//System.out.println("Trying Move:"+move.toString());
			Point nextPoint = moveManager.isValidPoint(point, move);
			if(null!=nextPoint){
				//System.out.println("Next Point for "+MoveManager.valueOfPoint(point)+" is :"+MoveManager.valueOfPoint(nextPoint));
				
				currentPath.add(nextPoint);
				goDownTillEnd(nextPoint, path, max, currentPath);
				currentPath.remove(nextPoint);
				nextPoint = null;
			}
			
			
		}
		
	}
	
	public List<Integer> printPath(List<Point> pathTraversed){

		List<Integer> pathVals = new ArrayList<>();
		
		for(Point p:pathTraversed){
			pathVals.add(path[p.getX()][p.getY()]);
		}
		return pathVals;
	}
	
	public void printResult(){
		List<Integer> pathVals = new ArrayList<>();
		System.out.println("Search has ended");
		
		for(Point p:moveManager.getMaxTraversedPath()){
			pathVals.add(path[p.getX()][p.getY()]);
		}
		
		System.out.println("Path: "+pathVals);
		System.out.println("Length: "+pathVals.size());
		System.out.println("Depth: "+moveManager.getMaxPathSum());
	}
	

}
