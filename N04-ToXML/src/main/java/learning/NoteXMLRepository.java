package learning;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



public class NoteXMLRepository {

  private final String filename;

  public NoteXMLRepository(String filename) {
    this.filename = filename;
  }

  public List<Note> getAll() throws RepositoryException {
    List<Note> list = new ArrayList<>();

    return list;
  }

  public void save(Note note) throws RepositoryException {
    System.out.println("Zapisywanie...");
    try {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    
    Document doc = db.newDocument();
    
    Element element = doc.createElement("Note");
    doc.appendChild(element);
    
    Attr attr = doc.createAttribute("Id");
    attr.setValue(note.getId().toString());
    element.setAttributeNode(attr);
    
    Element title = doc.createElement("Title");
    title.appendChild(doc.createTextNode(note.getTitle()));
    element.appendChild(title);
    
    Element date = doc.createElement("Date");
    date.appendChild(doc.createTextNode(note.getDateAsString()));
    element.appendChild(date);
    
    Element comment = doc.createElement("Comment");
    comment.appendChild(doc.createTextNode(note.getComment()));
    element.appendChild(comment);
    
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    
    StreamResult streamresult = new StreamResult(new File(this.filename));
    
    transformer.transform(source, streamresult);
    } catch (Exception e) {
      throw new RepositoryException("Cannot save notes", e);
    }
    System.out.println("Dane zostały zapisane.");
  }
  
  public void delete(UUID id) throws RepositoryException {
    List<Note> list = new ArrayList<>();

  }
}
