package application;

import chess.ChessMatch;


public class Proagram {

	public static void main(String[] args) {
		
		ChessMatch chessmatch = new ChessMatch();
        UI.printBoard(chessmatch.getPieces());	 
	}

}
