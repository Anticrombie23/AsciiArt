import java.util.Scanner;

/*
 * Must create size in multiples of 5 for length of YEN
 * 
 * 
 * 
 * 
 */
public class AsciiArt {
	
	final static char characterToPrint = 'X';
	final static char spaceCharacter = '-';
	final static int fourChar = 4;
	final static char spacePrint = '-';
	//final static int LENGTHOFYEN = 25; This will be dynamically determined after user input
	//final static int ROWSOFYEN = 9; This will be dynamically determined after user input
	
	
	// The following three methods all do the same thing
	private static void printChars(int number, char c){		
		while (number != 0){
			System.out.print(c);
			number --;
		}
	}
	
	private static void printChars(double number, char c){		
		while (number != 0){
			System.out.print(c);
			number --;		}
	}
	
	private static void printXs(int number, char c){
		while (number != 0){
			System.out.print(c);
			number --;
		}
		
	}
	
	
	//Found this on stack overflow to test if a double is an integer. This is needed to size the V in the YEN
	private static boolean isInteger(double d){
	    if(d > Long.MAX_VALUE || d < Long.MIN_VALUE){
	        return true;
	    } else if((long)d == d){
	        return true;
	    } else {
	        return false;
	    }
	}
	
		// This prints the last three rows of XX's at the bottom of the yen. Should be 7, 5, 3...
	private static void bottomVofTop(int numberOfRows, int sizeOfYen){
		
		int rows = 3;
		int beginningXs = 7;
		double spacesLeftandRight = 0; 
		
		while(rows != 0){
			
			for (int i = 0; i < 1; i++){
				
				//print left set of spaces				
				spacesLeftandRight = (double)(sizeOfYen - beginningXs)/2;				
				boolean testIfInteger = isInteger(spacesLeftandRight);
				
				if (testIfInteger){
					printChars(spacesLeftandRight, spaceCharacter);
				}else{
					printChars(spacesLeftandRight+0.5, spaceCharacter);
				}
							
				//print X's in the middle
				printXs(beginningXs, characterToPrint);
				
				//print right side of spaces
				if (testIfInteger){
					printChars(spacesLeftandRight, spaceCharacter);
				}else{
					printChars(spacesLeftandRight-0.5, spaceCharacter);
				}
												
			}
			rows --;
			numberOfRows ++;
			beginningXs -=2;
			System.out.println();
		}
		
		
	}
		
	/*
	 * This prints the top of the V dynamically dependent on the yen length after user input
	 * 
	 * This method takes the most math. First it takes in the length then determines how many rows of dashes
	 * are needed. I kind of found a pattern that looks aestetically pleasing and the formula is below:
	 * 
	 *  if MOD10 (divisible by ten) then it's divide by 2, add four
	 *	if NOT mot10, then its subtract one, divide by 2, add four
	 *
	 *	It helps to look at examples 
	 * 
	 */
	private static int printTopofYen (int totalYenLength, int fourChar, char charX, char space){		
		
		int middleSpaces = totalYenLength - fourChar * 2;
		int rowsInTop = 9;
		
		int modTotalYenLength = totalYenLength % 10;
		
		if(modTotalYenLength == 0){
			int checker = (totalYenLength / 2) + 4;
			rowsInTop = totalYenLength - checker ;
		}else {
			int checker = ((totalYenLength - 1) / 2) + 4;
			rowsInTop = totalYenLength - checker;
		}
		
		
		for (int i = 0; i < rowsInTop; i ++){			
			
			for (int j = 0; j < 1; j ++){
				//Print number of spaces left
								
				printChars(i, space);
				
				//Use a third level loop to print four X's. This is an explicit example of a third level loop
				// The rest of the program will use a method to print the chars. 
				
				for(int k = 0; k < fourChar; k++){
					System.out.print(charX);
				}
				//printXs(fourChar, charX);
				
				//Print number of spaces middle
				printChars(middleSpaces, space);
				//print four X's
				printXs(fourChar, charX);
				//print number of spaces right
				printChars(i, space);
			}
			
			// Go to the next line, increment number of spaces on next line, decrement middle spaces
			System.out.println("");
			middleSpaces -= 2;			
		}		
		return rowsInTop;
	}
	
	private static void printCrossofYen(int numberOfRows, int lengthOfYen){
		
		//We will always use a spacer of four for the crosses
		int spaces = 4;
		
		for (int i = 0; i < numberOfRows; i ++){
			
			for (int j = 0; j < 1; j ++){
				//print left spaces
				printChars(spaces, spaceCharacter);
				//print middle characters
				printXs(lengthOfYen - 8, characterToPrint);
				//print right spaces
				printChars(spaces, spaceCharacter);
			}
			System.out.println("");
		}
	}
	
	private static void printThreeCharRow(int numberOfRows, int lengthOfYen){
		
		//Three chars in middle always
		int charsInMiddle = 3;
		double spacesInMiddle = (lengthOfYen - 3) / 2;
		
		
		
		for (int i = 0; i < numberOfRows; i ++){
			
			for (int j = 0; j < 1; j ++){
				//If it's MOD10, it creates a non integer (0.5 multiple) double. Chars can't be half so add to 
				// make the spaces uniform with the length of the yen
				if((spacesInMiddle * 2) + 3 == lengthOfYen){
					printChars(spacesInMiddle, spaceCharacter);
				}else{
					printChars(spacesInMiddle+1, spaceCharacter);
				}
				
				
				//print middle characters
				printXs(charsInMiddle, characterToPrint);
				//print right spaces
				printChars(spacesInMiddle, spaceCharacter);
			}
			System.out.println("");
		}
		
	}
	
	public static void draw(){
		System.out.println("Hello! This program will print out a YEN symbol once you enter an integer that is a multiple of 5!!"
				+ "\nNote: the number of rows in the V will scale to the number of rows! "
				+"\nPlease enter an integer that is a multiple of 5 that is greater than 25: ");
		
		Scanner scanner = new Scanner(System.in);
				
		int sizeOfYen = scanner.nextInt();
		
		while (sizeOfYen % 5 != 0 || sizeOfYen > 100){
			
			System.out.println("Oops! You didn't put a number in a multiple of 5 less than 50 rows!\n Please enter"
					+ " a number less than 100 that is a multiple of 5 (e.g 25, 30, 45 etc): ");
			sizeOfYen = scanner.nextInt();			
		}
		
		
		//print blank line
		printChars(sizeOfYen, spaceCharacter);
		System.out.println("");		
		
		//Print top of Yen symbol
		int forBottom = printTopofYen(sizeOfYen, fourChar, characterToPrint, spacePrint);
		//Print bottom of Yen Symbol
		bottomVofTop(forBottom, sizeOfYen);
		//Print the first two rows of crosses in the yen
		printCrossofYen(2, sizeOfYen);
		//Print middle part of three char rows
		printThreeCharRow(2, sizeOfYen);
		//Print next yen cross row
		printCrossofYen(2, sizeOfYen);
		//Print next 5 three char rows
		printThreeCharRow(5, sizeOfYen);
		//print blank line
		printChars(sizeOfYen, spaceCharacter);
		
		scanner.close();
	}
	
	

}
