package learning;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class NoteDAO {
	
	public List<Note> load(String fileName){
	    List<Note> list = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		try (Scanner sc = new Scanner(new File(fileName))) {
			while (sc.hasNextLine()) {
				String id = sc.nextLine();
				String title = sc.nextLine();
				String dateString = sc.nextLine();
				Date date = formatter.parse(dateString);
				String comment = sc.nextLine();
				
				Note note = new Note(id, title, date, comment);
				
				list.add(note);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void save(List<Note> list, String fileName) {
		System.out.println("Zapisywanie...");
		try(PrintWriter out = new PrintWriter(fileName)){
			for (Note note : list) {
				out.println(note.getId());
				out.println(note.getTitle());
				out.println(note.getDate());
				out.println(note.getComment());
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Dane zostały zapisane.");
	}
	
	public void delete(List<Note> list, UUID id, String fileName ){
	    try(PrintWriter out = new PrintWriter(fileName)){
            for (Note note : list) {
                if(note.getId().equals(id))continue;
                out.println(note.getId());
                out.println(note.getTitle());
                out.println(note.getDate());
                out.println(note.getComment());
            }
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
