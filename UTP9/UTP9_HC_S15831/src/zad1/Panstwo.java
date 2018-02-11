package zad1;

public class Panstwo
{
	String nazwa;
	String rok;
	Double ludnosc;
	public Panstwo(String...strings)
	{
		strings = strings[0].split(" ");
		this.nazwa = strings[0];
		this.rok = strings[1];
		this.ludnosc = Double.parseDouble(strings[2]);
	}
}
