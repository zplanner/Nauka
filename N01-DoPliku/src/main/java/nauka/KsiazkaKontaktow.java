package nauka;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class KsiazkaKontaktow {
	static final String PLIK = "MiejsceZapisu.txt";
	static Map<UUID, Notatka> database = new HashMap<>();

	public static void main(String[] args) {
		pokazMenu();
		program();
	}

	public static void program() {
		Scanner sc = new Scanner(System.in);
		String komenda;
		do {
			komenda = sc.nextLine();
			switch (komenda) {
			case "l":
				wyswietlListe();
				break;
			case "d":
				usunWpis();
				break;
			case "n":
				utworzNowyWpis();
				break;
			case "q":
				System.out.println("Koniec.");
				System.exit(0);
			case "m":
				pokazMenu();
				break;
			case "w":
				ObslugaPliku.wczytaj(database, PLIK);
				break;
			case "z":
				ObslugaPliku.zapisz(database, PLIK);
				break;
			default:
				System.out.println("Error! Wrong command.");
				break;

			}
		} while (true);
	}
	
	static void pokazMenu() {
		System.out.println(">Ksiazka Kontaktow<");
		System.out.println("--------------------------------------");
		System.out.println("Wpisz polecenie:");
		System.out.println("m -> Wyswietl ponownie menu.");
		System.out.println("n -> Stworz nowy kontakt.");
		System.out.println("l -> Wyswietl liste kontaktow.");
		System.out.println("d -> Usun kontakt.");
		System.out.println("w -> Wczytaj liste kontaktow z pliku.");
		System.out.println("z -> Zapisz liste kontaktow do pliku.");
		System.out.println("q -> Zakoncz program.");
		System.out.println("--------------------------------------");
	}

	static void wyswietlListe() {
		database.values().stream().forEach(System.out::println);
	}

	static void utworzNowyWpis() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Wprowadz tytul: ");
		String tytul = sc.nextLine();
		System.out.println("Wprowadz tresc: ");
		String tresc = sc.nextLine();
		System.out.println("Czy dane są prawidlowe? Tytul = " + tytul + ", tresc = " + tresc + "?");
		String odp = sc.nextLine();
		switch (odp) {
		case "tak":
			Notatka note = new Notatka(tytul, tresc);
			database.put(note.getId(), note);
			break;
		case "nie":
			return;
		default:
			System.out.println("error");
			break;
		}
	}

	static void usunWpis() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Podaj nr id rekordu, ktory chcesz usunac:");
		String id = sc.nextLine();
		System.out.println("Czy na pewno chcesz usunąc ten rekord?");
		String odp = sc.nextLine();
		switch (odp) {
		case "tak":
			database.remove(UUID.fromString(id));
		case "nie":
			return;
		default:
			System.out.println("error");
			break;
		}
	}

}