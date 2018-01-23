package learning;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class NoteDAO {
    
	private final String filename;
	
	public NoteDAO(String filename) {
	    this.filename = filename;
	  }
	
	public List<Note> getAll(){
	    List<Note> list = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		try (Scanner sc = new Scanner(new File(this.filename))) {
			while (sc.hasNextLine()) {
				String id = sc.nextLine();
				String title = sc.nextLine();
				String dateString = sc.nextLine();
				Date date = formatter.parse(dateString);
				String comment = sc.nextLine();
				
				Note note = new Note(id, title, date, comment);
				
				list.add(note);
			}
		} catch (Exception e) {
		    throw new RuntimeException("Error!");
		}
		return list;
	}
	
	public void save(List<Note> list) {
		System.out.println("Zapisywanie...");
		try(PrintWriter out = new PrintWriter(this.filename)){
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
	
	public void delete(List<Note> list, UUID id){
	    try(PrintWriter out = new PrintWriter(this.filename)){
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
