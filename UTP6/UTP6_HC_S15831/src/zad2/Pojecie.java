package zad2;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;


public class Pojecie
{
	String haslo;
	String definicja;
	public Pojecie(String haslo, String definicja)
	{
		this.haslo=haslo;
		this.definicja=definicja;
	}
	
	
	public String getHaslo()
	{
		return haslo;
	}


	public String getDefinicja()
	{
		return definicja;
	}


	public static Comparator<Pojecie> porownaniePojec
    = new Comparator<Pojecie>() {

		public int compare(Pojecie pojecie1, Pojecie pojecie2) 
		{
			Collator collator = Collator.getInstance(new Locale("pl", "PL"));
			String definicja1 = pojecie1.getDefinicja().toUpperCase();
			String definicja2 = pojecie2.getDefinicja().toUpperCase();
			
				return collator.compare(definicja1, definicja2);
		}
	};
	
	public String toString()
	{
		return haslo + " = " + definicja;
	}
}