package learning;

import java.io.File;
import java.text.SimpleDateFormat;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NoteXMLRepository {

  private final String filename;

  public NoteXMLRepository(String filename) {
    this.filename = filename;
  }

  public List<Note> getAll() throws RepositoryException {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    List<Note> list = new ArrayList<>();
    try {
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
          .newInstance();
      DocumentBuilder documentBuilder = documentBuilderFactory
          .newDocumentBuilder();
      Document document = documentBuilder
          .parse(new File(this.filename));
      NodeList nList = document
          .getElementsByTagName("Note");

      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node node = nList.item(temp);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) node;
          String id = eElement
              .getAttribute("Id");
          String title = eElement
              .getElementsByTagName("Title")
              .item(0)
              .getTextContent();
          String date = eElement
              .getElementsByTagName("Date")
              .item(0)
              .getTextContent();
          String comment = eElement
              .getElementsByTagName("Comment")
              .item(0)
              .getTextContent();
          
          list.add(new Note(id, title, formatter.parse(date), comment));
        }
      }
    } catch (Exception e) {
      throw new RepositoryException("Cannot load notes", e);
    }
    return list;
  }

  public void save(Note newNote) throws RepositoryException {
    System.out.println("Zapisywanie...");
    List<Note> list = getAll();
    list.add(newNote);
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf
          .newDocumentBuilder();

      Document doc = db
          .newDocument();
      Element main = doc
          .createElement("GuestBook");
      doc.appendChild(main);
      for (Note note : list) {
        Element element = doc
            .createElement("Note");

        main.appendChild(element);

        Attr attr = doc
            .createAttribute("Id");
        attr.setValue(note.getId()
            .toString());
        element.setAttributeNode(attr);

        Element title = doc
            .createElement("Title");
        title.appendChild(doc
            .createTextNode(note.getTitle()));
        element.appendChild(title);

        Element date = doc
            .createElement("Date");
        date.appendChild(doc
            .createTextNode(note.getDateAsString()));
        element.appendChild(date);

        Element comment = doc
            .createElement("Comment");
        comment.appendChild(doc
            .createTextNode(note.getComment()));
        element.appendChild(comment);

        TransformerFactory transformerFactory = TransformerFactory
            .newInstance();
        Transformer transformer = transformerFactory
            .newTransformer();
        DOMSource source = new DOMSource(doc);

        StreamResult streamresult = new StreamResult(new File(this.filename));
        transformer.transform(source, streamresult);
      }
    } catch (Exception e) {
      throw new RepositoryException("Cannot save notes", e);
    }
    System.out.println("Dane zostały zapisane.");
  }

  public void delete(UUID id) throws RepositoryException {
    List<Note> list = new ArrayList<>();
    for (Note note : getAll()) {
      if (!note.getId().equals(id))
        list.add(note);
    }
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf
          .newDocumentBuilder();

      Document doc = db
          .newDocument();
      Element main = doc
          .createElement("GuestBook");
      doc.appendChild(main);
      for (Note note : list) {
        Element element = doc
            .createElement("Note");

        main.appendChild(element);

        Attr attr = doc
            .createAttribute("Id");
        attr.setValue(note.getId()
            .toString());
        element.setAttributeNode(attr);

        Element title = doc
            .createElement("Title");
        title.appendChild(doc
            .createTextNode(note.getTitle()));
        element.appendChild(title);

        Element date = doc
            .createElement("Date");
        date.appendChild(doc
            .createTextNode(note.getDateAsString()));
        element.appendChild(date);

        Element comment = doc
            .createElement("Comment");
        comment.appendChild(doc
            .createTextNode(note.getComment()));
        element.appendChild(comment);

        TransformerFactory transformerFactory = TransformerFactory
            .newInstance();
        Transformer transformer = transformerFactory
            .newTransformer();
        DOMSource source = new DOMSource(doc);

        StreamResult streamresult = new StreamResult(new File(this.filename));
        transformer.transform(source, streamresult);
      }
    } catch (Exception e) {
      throw new RepositoryException("Cannot save notes", e);
    }    
  }
}
