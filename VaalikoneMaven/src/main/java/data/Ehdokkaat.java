package data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Ehdokkaat.findAll", query="SELECT e FROM Ehdokkaat e")
public class Ehdokkaat implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int EHDOKAS_ID ;
	private String sukunimi;
	private String etunimi;
	private String puolue;
	private String kotipaikkakunta;
	private int ika;
	private String miksi_eduskuntaan;
	private String mita_asioita_haluat_edistaa;
	private String ammatti;
	
	
	public Ehdokkaat(String id, String sukunimi, String etunimi, String puolue, String kotipaikkakunta, String ika, String miksi_eduskuntaan, String mita_asioita_haluat_edistaa, String ammatti) {
		setId(id);
		setIka(ika);
		this.sukunimi=sukunimi;
		this.etunimi=etunimi;
		this.puolue=puolue;
		this.kotipaikkakunta=kotipaikkakunta;
		this.miksi_eduskuntaan=miksi_eduskuntaan;
		this.mita_asioita_haluat_edistaa=mita_asioita_haluat_edistaa;
		this.ammatti=ammatti;
	}
	public Ehdokkaat() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return EHDOKAS_ID;
	}
	public void setId(int id) {
		this.EHDOKAS_ID = id;
	}
	public void setId(String id) {
		try {
			this.EHDOKAS_ID = Integer.parseInt(id);
		}
		catch(NumberFormatException | NullPointerException e) {
			
		}
		
	}
	
	public String getSukunimi() {
		return this.sukunimi;
	}
	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}
	
	public String getEtunimi() {
		return this.etunimi;
	}
	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}
	
	public String getPuolue() {
		return this.puolue;
	}
	public void setPuolue(String puolue) {
		this.puolue = puolue;
	}
	
	public String getKotipaikkakunta() {
		return this.kotipaikkakunta;
	}
	public void setKotipaikkakunta(String kotipaikkakunta) {
		this.kotipaikkakunta = kotipaikkakunta;
	}
	
	public int getIka() {
		return ika;
	}
	public void setIka(int ika) {
		this.ika = ika;
	}
	public void setIka(String ika) {
		try {
			this.ika = Integer.parseInt(ika);
		}
		catch(NumberFormatException | NullPointerException e) {

		}
	}
	
	public String getMiksi_eduskuntaan() {
		return this.miksi_eduskuntaan;
	}
	public void setMiksi_eduskuntaan(String miksi_eduskuntaan) {
		this.miksi_eduskuntaan = miksi_eduskuntaan;
	}
	
	public String getMita_asioita_haluat_edistaa() {
		return this.mita_asioita_haluat_edistaa;
	}
	public void setMita_asioita_haluat_edistaa(String mita_asioita_haluat_edistaa) {
		this.mita_asioita_haluat_edistaa = mita_asioita_haluat_edistaa;
	}
	
	public String getAmmatti() {
		return this.ammatti;
	}
	public void setAmmatti(String ammatti) {
		this.ammatti = ammatti;
	}
	
	
}
