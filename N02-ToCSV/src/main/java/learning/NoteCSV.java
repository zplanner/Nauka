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

public class NoteCSV {

  private final String filename;

  public NoteCSV(String filename) {
    this.filename = filename;
  }

  public List<Note> getAll() {
    List<Note> list = new ArrayList<>();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    try (Scanner sc = new Scanner(new File(this.filename))) {
      while (sc.hasNextLine()) {
        String oneObject = sc.nextLine();
        String[] Fields = oneObject.split(";");
        
        Date date = formatter.parse(Fields[2]);
        Note note = new Note(Fields[0], Fields[1], date, Fields[3]);
        
        list.add(note);
      }
    } catch (Exception e) {
      throw new RuntimeException("Error!");
    }
    return list;
  }

  public void save(List<Note> list) {
    System.out.println("Zapisywanie...");
    try (PrintWriter out = new PrintWriter(this.filename)) {
      
      for (Note note : list) {
        out.println(note.getId()+";"
            +note.getTitle()+";"
            +note.getDate()+";"
            +note.getComment());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println("Dane zostały zapisane.");
  }

  public void delete(List<Note> list, UUID id) {
    try (PrintWriter out = new PrintWriter(this.filename)) {
      for (Note note : list) {
        if (note.getId().equals(id))
          continue;
        out.println(note.getId()+";"
            +note.getTitle()+";"
            +note.getDate()+";"
            +note.getComment());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
