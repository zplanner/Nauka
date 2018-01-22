package learning;

import java.util.Scanner;

public class GuessGame {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		start();
		guess(1, 100);
		
	}
	
	static void start() {
		System.out.println("Pomyśl o liczbie z przedziału 1 - 100");
		System.out.println("Napisz: "
				+ "\n\"w\" - jeżeli Twoja liczba jest większa."
				+ "\n\"m\" - jeżeli Twoja liczba jest mniejsza."
				+ "\n\"ok\" - jeżeli odgadłem Twoją liczbę.");
	}
	
	static void guess(int currentMin, int currentMax) {
		
		int n = 0;
		int mid = 0;
		
		do {
			n++;
			mid = (currentMin+currentMax)/2;
			System.out.println("Czy twoja liczba to [ "+mid+" ]?");
			String odp = sc.nextLine();
		
		switch(odp) {
			case "w":currentMin=mid;break;
			case "m":currentMax=mid;break;
			case "tak":System.out.printf("\nOdgadłem za %d razem!",n);return;
			default:System.out.println("Zła komenda!");break;
			}
		} while(true);
	}
}
