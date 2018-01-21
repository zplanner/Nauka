package nauka;

import java.util.Scanner;

public class Zgadywanka {
	public static void main(String[] args) {
		zaczynamy();
		graZgadywanka(1, 100);
		
	}
	
	static void zaczynamy() {
		System.out.println("Pomyśl o liczbie z przedziału 1 - 100");
		System.out.println("Napisz: "
				+ "\n\"w\" - jeżeli Twoja liczba jest większa."
				+ "\n\"m\" - jeżeli Twoja liczba jest mniejsza."
				+ "\n\"ok\" - jeżeli odgadłem Twoją liczbę.");
	}
	
	static void graZgadywanka(int currentMin, int currentMax) {
		Scanner sc = new Scanner(System.in);
		
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
		}
		while(true);
	}
}
