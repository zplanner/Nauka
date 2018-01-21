package nauka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class ObslugaPliku {
	public static void wczytaj(Map<UUID,Notatka> database, String plik) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println("Wczytywanie...");
		try (Scanner sc = new Scanner(new File(plik))) {

			while (sc.hasNextLine()) {

				String id = sc.nextLine();
				String tytul = sc.nextLine();
				String dataTekst = sc.nextLine();
				Date data = formatter.parse(dataTekst);
				String opis = sc.nextLine();
				
				Notatka note = new Notatka(id, tytul, data, opis);
				
				database.put(note.getId(), note);
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Wczytywanie zakonczone!");
	}
	
	
	public static void zapisz(Map<UUID,Notatka> database,String plik) {
		System.out.println("Zapisywanie...");
		try(PrintWriter out = new PrintWriter(plik)){
			for (Notatka note : database.values()) {
				out.println(note.getId());
				out.println(note.getTytul());
				out.println(note.getData());
				out.println(note.getOpis());
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Dane zostały zapisane.");
	}
}
