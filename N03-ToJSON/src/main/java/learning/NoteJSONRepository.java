package learning;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class NoteJSONRepository {

  private final String filename;

  public NoteJSONRepository(String filename) {
    this.filename = filename;
  }

  public List<Note> getAll() throws RepositoryException {    
    Gson gson = new Gson();
    List<Note> list = new ArrayList<>();
    try {
      JsonReader reader = new JsonReader(new FileReader(this.filename));
      Note[] notes = gson.fromJson(reader, Note[].class);
      list = Arrays.asList(notes);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public void save(Note note) throws RepositoryException {
    System.out.println("Zapisywanie...");
    
    List<Note> list = new ArrayList<>(getAll());
    list.add(note);
    try (Writer writer = new FileWriter(this.filename)) {
      Gson gson = new GsonBuilder().create();
      String noteJson = gson.toJson(list);  
      writer.write(noteJson);
  } catch (IOException e) {
    e.printStackTrace();
  }
    
    System.out.println("Dane zostały zapisane.");
  }

  public void delete(UUID id) throws RepositoryException{
    //TODO
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
  }
}

