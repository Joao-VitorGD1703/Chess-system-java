package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	

		// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

		final static String ANSI_RESET = "\u001B[0m";
		final String ANSI_BLACK = "\u001B[30m";
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_GREEN = "\u001B[32m";
		final static String ANSI_YELLOW = "\u001B[33m";
		final String ANSI_BLUE = "\u001B[34m";
		final String ANSI_PURPLE = "\u001B[35m";
		final String ANSI_CYAN = "\u001B[36m";
		final static String ANSI_WHITE = "\u001B[37m";

		final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
		final String ANSI_RED_BACKGROUND = "\u001B[41m";
		final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
		final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
		final static String ANSI_BLUE_BACKGROUND = "\u001B[44m";
		final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
		final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
		final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
		
		
		// https://stackoverflow.com/questions/2979383/java-clear-the-console
		public static void clearScreen() { 
		 System.out.print("\033[H\033[2J"); 
		 System.out.flush(); 
		}

		
		/*final String RESET = "\033[0m";  // Text Reset final String 
	    // Regular Colors
	     final String BLACK = "\033[0;30m";   // BLACK
	     final String RED = "\033[0;31m";     // RED
	     final String GREEN = "\033[0;32m";   // GREEN
	     final String YELLOW = "\033[0;33m";  // YELLOW
	     final String BLUE = "\033[0;34m";    // BLUE
	     final String PURPLE = "\033[0;35m";  // PURPLE
	     final String CYAN = "\033[0;36m";    // CYAN
	     final String WHITE = "\033[0;37m";   // WHITE
		
		
		
		     final String BLACK_BACKGROUND;   
		     final String RED_BACKGROUND;     
		     final String GREEN_BACKGROUND;   
		     final String YELLOW_BACKGROUND;  
		     final String BLUE_BACKGROUND;    
		     final String MAGENTA_BACKGROUND; 
		     final String CYAN_BACKGROUND;    
		     final String WHITE_BACKGROUND;*/
		
		public static ChessPosition readChessPosition(Scanner sc) {
			try {
			String s = sc.next();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
			} catch(RuntimeException e) {
				throw new InputMismatchException("Erro reading ChessPosition. Valid values from a1 at h8.");
			}
		}
		
		public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
			printBoard(chessMatch.getPieces());
			System.out.println();
			printCapturedPieces(captured);
			System.out.println();
			System.out.println("Turn : " + chessMatch.getTurn());
			System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
			if(chessMatch.getCheck()) {
				System.out.println("CHECK!");
			}
		}
		    
	public  static  void  printBoard ( ChessPiece [] [] pieces ) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);

			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");

	}
	
	public  static  void  printBoard ( ChessPiece [] [] pieces, boolean[][] possibleMoves ) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);

			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");

	}



	private static void printPiece(ChessPiece piece, boolean background) {
    	if(background) {
    		 System.out.print( ANSI_BLUE_BACKGROUND);
    	}
		if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print( ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	private static void printCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.println("Captured pieces");
		System.out.print("White: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.println(ANSI_RESET);
		System.out.print("Black: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.println(ANSI_RESET);
		
		
	}
	

}
