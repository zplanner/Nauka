package nauka;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Notatka {
	private UUID id;
	private String tytul;
	private Date data;
	private String opis;
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public String getData() {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = dateFormat.format(data);
	return dateString;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Notatka(String tytul, String opis) {
		super();
		this.id = UUID.randomUUID();
		this.tytul = tytul;
		this.data= new Date();
		this.opis = opis;

	}

	public Notatka(String id, String tytul, Date data, String opis) {
		super();
		this.id = UUID.fromString(id);
		this.tytul = tytul;
		this.data = data;
		this.opis = opis;
	}

	@Override
	public String toString() {
		return "Notatka [id=" + id + ", tytul=" + tytul + ", data=" + getData() + ", opis=" + opis + "]";
	}

}
