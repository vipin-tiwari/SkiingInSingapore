package com.redmart.skiing;

public enum Moves {
	NORTH(new int[] {0,1}),
	SOUTH(new int[] {0,-1}),
	EAST(new int[] {1,0}),
	WEST(new int[] {-1,0});
	
	private int[] nextMove;
	
	Moves(int[] nextMove){
		this.nextMove = nextMove;
	}
	
	int[] getNextMove(){
		return nextMove;
	}
	

}
