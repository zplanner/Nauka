package learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class GuestBook {
	static NoteDAO notedao = new NoteDAO();
	static final String LOCALFILE = "MiejsceZapisu.txt";
	static List<Note> list = new ArrayList<>(notedao.load(LOCALFILE));
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		showMenu();
		selectMenu();
	}

	public static void selectMenu() {
		String cmd;
		do {
			cmd = sc.nextLine();
			switch (cmd) {
			case "l":
				showList();
				break;
			case "d":
				delete();
				break;
			case "n":
				createNew();
				break;
			case "q":
				System.out.println("Koniec.");
				System.exit(0);
			case "m":
				showMenu();
				break;
			case "z":
				notedao.save(list,LOCALFILE);
				break;
			default:
				System.out.println("Error! Wrong command.");
				break;

			}
		} while (true);
	}
	
	static void showMenu() {
		System.out.println(">GuestBook<\n"
		        + "--------------------------------------\n"
		        + "Wpisz polecenie:\n"
		        + "m -> Wyswietl ponownie menu.\n"
		        + "n -> Stworz nowy kontakt.\n"
		        + "l -> Wyswietl liste kontaktow.\n"
		        + "d -> Usun kontakt.\n"
		        + "z -> Zapisz liste kontaktow do pliku.\n"
		        + "q -> Zakoncz program.\n"
		        + "--------------------------------------\n");
	}

	static void showList() {
	    for (Note note : list) {
            System.out.println(note);
        }
	}

	static void createNew() {
		System.out.println("Wprowadz tytul: ");
		String tytul = sc.nextLine();
		System.out.println("Wprowadz tresc: ");
		String tresc = sc.nextLine();
		System.out.println("Czy dane są prawidlowe? Tytul = " + tytul + ", tresc = " + tresc + "?");
		String odp = sc.nextLine();
		switch (odp) {
		 case "tak":
			Note note = new Note(tytul, tresc);
			list.add(note);
			break;
		 case "nie":
			return;
		 default:
			System.out.println("error");
			break;
		 }
	}

	static void delete() {
		System.out.println("Podaj nr id rekordu, ktory chcesz usunac:");
		UUID id = UUID.fromString(sc.nextLine());
		System.out.println("Czy na pewno chcesz usunąc ten rekord?");
		String odp = sc.nextLine();
		switch (odp) {
		 case "tak":
		    notedao.delete(list,id,LOCALFILE);
		    list = notedao.load(LOCALFILE);
		 case "nie":
			return;
		 default:
			System.out.println("error");
			break;
		}
	}
}