package number_srpiral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Generate spiral matrix from 0 to the given number. 
 * 
 * @author Srikant Kammila
 *
 */
public class GenerateSpiral {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String maxStr;  

		while (true) {
			System.out.println("Enter the maximum number to generate spiral matrix starting with 0: ");
			try {
				maxStr = br.readLine();
				
				int max;
				try {
					max = Integer.parseInt(maxStr);
					if (max < 0) {
						throw new NumberFormatException();
					}
					max++;
					printMatrix(generateSpiral(max), max);
				} catch (NumberFormatException e) {
					System.out.println("Wrong input. Try again...");
				}
				
			} catch (IOException e) {
				System.out.println("Error while reading input. Try again...");
			}
		}
		
	}
	
	public static int[][] generateSpiral(int max) {
		int n = (int)Math.ceil(Math.sqrt(max)),
				newMax = 0;
		if (n % 2 == 0) {
			n++;
		}
		newMax = n * n;
		int[][] result = new int[n][n];
		int x = n/2, y=n/2;
		int stepLimit = 1, stepsMade = 0, stepChange = 0;
		String currentDirection = "r";
		for (int i = 0; i < newMax; i++) {
			result[x][y] = i;
			if (stepsMade == stepLimit) {
				//change direction
				stepsMade = 0;
				stepChange++;
				currentDirection = getNextDirection(currentDirection);
			}
			if (stepChange == 2) {
				//change stepLimit
				stepLimit++;
				stepChange = 0;
				stepsMade = 0;
			}
			if (currentDirection == "r") {
				y++;
			} else if (currentDirection == "d") {
				x++;
			} else if (currentDirection == "l") {
				y--;
			} else if (currentDirection == "u") {
				x--;
			}
			stepsMade++;
			
		}
		
		return result;
		
	}
	
	public static String getNextDirection (String currentDirection) {
		switch (currentDirection) {
		case "r":
			return "d";
		case "d":
			return "l";
		case "l":
			return "u";
		case "u":
			return "r";
		default:
			return "";
		}
	}
	
	public static void printMatrix(int[][]  m, int max) {
		int xLen = m.length;
		int yLen = xLen > 0 ? m[0].length : 0;
		for (int i=0; i< m.length; i++) {
			for (int j=0; j<m[0].length; j++) {
				String printVal = m[i][j] < max ? String.format("%3d ",  m[i][j]) : "    ";
				System.out.print(printVal);
			}
			System.out.println("");
		}
		
		
	}

}
