package learning;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class NoteCSVRepository {

  private final String filename;

  public NoteCSVRepository(String filename) {
    this.filename = filename;
  }

  public List<Note> getAll() throws RepositoryException {    
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    try {
      CSVReader reader = new CSVReader(new FileReader(this.filename), ';', '"');
      List<Note> list = new ArrayList<>();
      reader.forEach(a -> {
        try {
          Note n = new Note(a[0], a[1], formatter.parse(a[2]), a[3]);
          list.add(n);
        } catch (ParseException ex) {
          //TODO logger.
        }
      });  
      reader.close();
      return list;
    } catch (Exception e) {
      throw new RepositoryException("Cannot read notes.", e);
    }
  }
  
  public void save(Note note) throws RepositoryException {
    System.out.println("Zapisywanie...");
    try {
      CSVWriter writer = new CSVWriter(new FileWriter(this.filename,true), ';');
      writer.writeNext(new String[] {
          note.getId().toString(),
          note.getTitle(),
          note.getDateAsString(),
          note.getComment()
      });
      writer.close();
    } catch (Exception e) {
      throw new RepositoryException("Cannot save note.", e);
    }
    System.out.println("Dane zostały zapisane.");
  }

  public void delete(UUID id) throws RepositoryException{
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    try {
      CSVReader reader = new CSVReader(new FileReader(this.filename), ';', '"');
      List<Note> list = new ArrayList<>();
      reader.forEach(a -> {
        try {
          Note n = new Note(a[0], a[1], formatter.parse(a[2]), a[3]);
          if(!a[0].equals(id.toString())) list.add(n);
        } catch (ParseException ex) {
          //TODO logger.
        }
      });  
      reader.close();
      
      CSVWriter writer = new CSVWriter(new FileWriter(this.filename), ';');
      list.stream()
          .map(n -> new String[] {
               n.getId().toString(),
               n.getTitle(),
               n.getDateAsString(),
               n.getComment()    
      })  .forEach(note -> writer.writeNext(note, true));
      writer.flush();
      writer.close();
    } catch (Exception e) {
      throw new RepositoryException("Cannot read notes.", e);
    }
  }
}
