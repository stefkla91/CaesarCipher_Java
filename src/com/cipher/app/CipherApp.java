package com.cipher.app;

import java.util.Scanner;

public class CipherApp {

	public static void main(String[] args) {

		boolean abortSignal = false;

		Scanner scanner = new Scanner(System.in).useDelimiter("\r");

		System.out.println("Welcome to the Caesar App!");

		while (!abortSignal) {
			System.out.println("What do you want to do?");
			System.out.println("\ta - Encode");
			System.out.println("\tb - Decode");
			System.out.println("\tq - Quit");

			switch (scanner.nextLine()) {
			case "a":
				run(0, scanner);
				break;
			case "b":
				run(1, scanner);
				break;
			case "q":
				abortSignal = true;
				break;
			}
		}
		scanner.close();

		System.out.println("Bye!");
		System.exit(0);
	}

	/**
	 * Executes the Encoding/Decoding process
	 * 
	 * @param mode    - 0 = Encode; 1 = Decode
	 * @param scanner - the Scanner instance to use
	 */
	private static void run(int mode, Scanner scanner) {
		String keyInput = null;
		String cipher = null;
		int cipherKey = 0;

		// Read the Cipher Key
		System.out.println("Please Enter a Cipher Key: ");
		keyInput = scanner.nextLine();

		try {
			cipherKey = Integer.valueOf(keyInput.replaceAll("\\s", ""));

		} catch (NumberFormatException e) {
			System.out.println("\n*** Please enter the cipher key as either a possitive or negative number \n");
			return;
		}

		System.out.println("Please enter your Cipher");
		cipher = scanner.nextLine();

		if (mode == 0) { // Encode
			System.out.println("Your Encoded text is: \n");
			System.out.println(applyCipher(cipher.trim(), cipherKey) + "\n");
		} else if (mode == 1) { // Decode
			System.out.println("Your Decoded text is: \n");
			System.out.println(applyCipher(cipher.trim(), -cipherKey) + "\n");
		}

	}

	/**
	 * Applies the Caesar Cipher. Encoding and Decoding is the same mathematical
	 * process. Only the "direction" of the key changes. Note = Can be
	 * encoded/decoded in either direction by the use of a negative key
	 * 
	 * @param text - the text on which to apply the Cipher
	 * @param key  - The cipher key to apply
	 * @return
	 */
	static String applyCipher(String text, int key) {
		StringBuffer stringBuilder = new StringBuffer();
		char ch;

		for (int i = 0; i < text.length(); i++) {
			if (Character.isWhitespace(text.charAt(i))) { // whitespace
				ch = text.charAt(i);
				stringBuilder.append(ch);
			} else if (Character.isUpperCase(text.charAt(i))) { // Upper case letters
				if ((int) text.charAt(i) + key < 65) {
					// negative change -> reset to Z
					ch = (char) (((int) text.charAt(i) + key - 90) % 26 + 90);
				} else {
					ch = (char) (((int) text.charAt(i) + key - 65) % 26 + 65);
				}

				stringBuilder.append(ch);
			} else { // lower case letters
				if ((int) text.charAt(i) + key < 97) {
					// negative change -> reset to z
					ch = (char) (((int) text.charAt(i) + key - 122) % 26 + 122);
				} else {
					ch = (char) (((int) text.charAt(i) + key - 97) % 26 + 97);
				}

				stringBuilder.append(ch);
			}
		}
		return stringBuilder.toString();
	}

}
