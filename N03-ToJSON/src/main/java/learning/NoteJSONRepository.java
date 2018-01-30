package learning;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

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
    try (JsonReader reader = new JsonReader(new FileReader(this.filename))) {
      Note[] notes = gson.fromJson(reader, Note[].class);
      list = Arrays.asList(notes);
    } catch (Exception e) {
      throw new RepositoryException("Cannot load note!", e);
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
      throw new RepositoryException("Cannot save note!", e);
    }
    System.out.println("Dane zostały zapisane.");
  }

  public void delete(UUID id) throws RepositoryException {
    List<Note> list = new ArrayList<>();
    Gson gson = new GsonBuilder().create();
    for (Note note : getAll()) {
      if (!note.getId().equals(id))
        list.add(note);
    }
    try (Writer writer = new FileWriter(this.filename)) {
      String noteJson = gson.toJson(list);
      writer.write(noteJson);
    } catch (IOException e) {
      throw new RepositoryException("Cannot save note!", e);
    }
  }
}
