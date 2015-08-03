package read_number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Generates readable number string for the given input
 * @author Srikant Kammila
 *
 */
public class Main {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String maxStr;

		while (true) {
			System.out.println("Enter a number to read: ");
			try {
				maxStr = br.readLine();
				String dollarSufficx = " dollars";
				double number;
				try {
					number = Double.parseDouble(maxStr);
					if (number < 0) {
						throw new NumberFormatException();
					}
					if (Math.ceil(number) <= 1.0) {
						dollarSufficx = " dollar";
					}
					if (Math.ceil(number) == 0.0) {
						System.out.println("zero" + dollarSufficx);
					} else {
						System.out.println(numberToString(maxStr).trim() + dollarSufficx);
					}
				} catch (NumberFormatException e) {
					System.out.println("Wrong input. Try again...");
				}

			} catch (IOException e) {
				System.out.println("Error while reading input. Try again...");
			}
		}
	}

	/**
	 * Generated string representations of the given number. Reads three digits
	 * at a time
	 * 
	 * @param number
	 * @return
	 */

	public static String numberToString(String number) {
		char[] chars = number.toCharArray();
		// int len = chars.length;
		int decimalIndex = number.indexOf(".");
		String integerPart = decimalIndex >= 0 ? number.substring(0, decimalIndex) : number;
		String decimalpart = decimalIndex >= 0 ? number.substring(decimalIndex + 1) : "";
		double decimalNumber = Math.ceil(Double.parseDouble(decimalpart));
		int chunkCounter = 0;
		String integerString = "", chunk = "", unreadChunk = "";
		
		//read integer part
		while (integerPart.length() > 0) {
			if (integerPart.length() >= 3) {
				// read three digits
				chunk = integerPart.substring(integerPart.length() - 3, integerPart.length());
				unreadChunk = integerPart.substring(0, integerPart.length() - 3);
			} else {
				chunk = integerPart;
				unreadChunk = "";
			}

			String readChunk = readThreeDecimal(chunk);
			String numberSystemString = getNumberSystemString(chunkCounter);

			numberSystemString = readChunk.length() > 0 ? " " + numberSystemString + " " : "";

			integerString = readChunk + numberSystemString + integerString;

			integerPart = unreadChunk;
			chunkCounter++;
			if (chunkCounter == 5) {
				//reached break point of hundred trillion.
				//start the chunk counter again
				chunkCounter = 1;
			}
		}
		//read decimal part
		int decimalPlacess = decimalpart.length();
		String decimalDenominator = "1";
		for (int i = 0; i < decimalPlacess; i++) {
			decimalDenominator += "0";
		}

		String decimalString = (decimalPlacess > 0 && decimalNumber > 0.0)? decimalpart + "/" + decimalDenominator : "";

		String result = integerString.length() > 0 ? (decimalString.length() > 0 ? integerString + "and " + decimalString : integerString) : decimalString;
		result += " ";
		return result;
	}

	public static String readThreeDecimal(String n) {
		String result = "";
		if (n.length() == 3) {
			char m = n.charAt(0);
			String b = convertToReadString("" + m);
			result = b.length() > 0 ? b.concat(" hundred ") : "";
			n = n.substring(1);
		}

		if (n.length() == 2) {
			char m = n.charAt(0);
			String b = "";
//			if (("" + m).equals("1")) {
			if (m == '1') {
				b = convertToReadString(n);
				n = n.substring(2);
			} else {
				b = convertToReadString("" + m + "0");
				n = n.substring(1);
			}
//			result = b.length() > 0 ? " " + result.concat(b) : result;
			result =  result.concat(b) ;

		}

		if (n.length() == 1) {
			char m = n.charAt(0);
			String b = convertToReadString("" + m);
			result = b.length() > 0 ? result.concat(" " + b) : result;
		}

		return result;

	}

	public static String getNumberSystemString(int chunkCount) {
		switch (chunkCount) {
		case 0:
			return "";
		case 1:
			return "thousand";
		case 2:
			return "million";
		case 3:
			return "billion";
		case 4:
			return "trillion";
		default:
			return "";
		}

	}

	public static String convertToReadString(String n) {
		switch (n) {
//		case "0":
//			return "zero";
		case "1":
			return "one";
		case "2":
			return "two";
		case "3":
			return "three";
		case "4":
			return "four";
		case "5":
			return "five";
		case "6":
			return "six";
		case "7":
			return "seven";
		case "8":
			return "eight";
		case "9":
			return "nine";
		case "10":
			return "ten";
		case "11":
			return "eleven";
		case "12":
			return "twelve";
		case "13":
			return "thirteen";
		case "14":
			return "fourteen";
		case "15":
			return "fifteen";
		case "16":
			return "sixteen";
		case "17":
			return "seventeen";
		case "18":
			return "eighteen";
		case "19":
			return "ninteen";
		case "20":
			return "twenty";
		case "30":
			return "thirty";
		case "40":
			return "fourty";
		case "50":
			return "fifty";
		case "60":
			return "sisty";
		case "70":
			return "seventy";
		case "80":
			return "eighty";
		case "90":
			return "ninty";
		case "100":
			return "hundred";
		default:
			return "";
		}

	}
}
