/**
 *
 *  @author Han Cezary S15831
 *
 */

package zad1;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class Purchase
{
	String nrKlietna;
	String imie;
	String nazwisko;
	String produkt;
	Double cena;
	Double ilosc;
	public Purchase(String nrKlienta,String nazwisko,String imie,String produkt, Double cena, Double ilosc)
	{
		this.nrKlietna = nrKlienta;
		this.nazwisko = nazwisko;
		this.imie = imie;
		this.produkt = produkt;
		this.cena = cena;
		this.ilosc = ilosc;
	}
	
	public String getNrKlietna()
	{
		return nrKlietna;
	}
	public Double getCena()
	{
		return cena;
	}
	public String getNazwiko()
	{
		return nazwisko;
	}
	public Double getIlosc()
	{
		return ilosc;
	}
	
	@Override
	public String toString()
	{
		return nrKlietna+";"+nazwisko+" "+imie+";"+produkt+";"+cena+";"+ilosc;
	}
	
	public String toStringKoszty()
	{
		return nrKlietna+";"+nazwisko+" "+imie+";"+produkt+";"+cena+";"+ilosc+"(koszt: "+ilosc*cena+")";
	}
	
	
	public static Comparator<Purchase> porownanieNazwisko
    = new Comparator<Purchase>() {

		public int compare(Purchase zakup1, Purchase zakup2) 
		{
			Collator collator = Collator.getInstance(new Locale("pl", "PL"));
			String nazwisko1 = zakup1.getNazwiko().toUpperCase();
			String nazwisko2 = zakup2.getNazwiko().toUpperCase();
			if (nazwisko1.compareTo(nazwisko2)==0)
				return zakup1.getNrKlietna().compareTo(zakup2.nrKlietna);
			else
				return collator.compare(nazwisko1, nazwisko2);
		}

	};
	
	public static Comparator<Purchase> porownanieKoszty
    = new Comparator<Purchase>() {
		public int compare(Purchase zakup1, Purchase zakup2) 
		{
			
			Double koszt1 = zakup1.getCena()*zakup1.getIlosc();
			Double koszt2 = zakup2.getCena()*zakup2.getIlosc();
			if(koszt2.compareTo(koszt1)==0)
				return zakup1.getNrKlietna().compareTo(zakup2.nrKlietna);
			else
				return koszt2.compareTo(koszt1);
			
		}

	};
	
	


}
