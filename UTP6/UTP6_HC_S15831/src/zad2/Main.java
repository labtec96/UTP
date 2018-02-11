package zad2;



public class Main
{
	public static void main(String[] args)
	{
		String fname = System.getProperty("user.home") + "/dictionary.txt";
		Dictionary dictionary = new Dictionary(fname);
		System.out.println(dictionary.wypisanie());
		dictionary.add("haslo", "def");
		dictionary.add("haslo", "def2");
		dictionary.add("haslo", "def3");
		dictionary.add("haslo", "def4");
		dictionary.add("haslo", "def4"); //dodajemy takie same
		System.out.println(dictionary.wypisanie());
		System.out.println(dictionary.listaDefinicji.toString());
		dictionary.lookup("haslo");
		System.out.println(dictionary.listaDefinicji.toString());
		dictionary.delete(7);
		dictionary.delete(4);
		System.out.println(dictionary.wypisanie());
		System.out.println(dictionary.listaDefinicji.toString());
		dictionary.update("haslo", "def7", "nowaDefinicja");
		System.out.println(dictionary.wypisanie());
		dictionary.update("haslo", "def", "nowaDefinicja");
		System.out.println(dictionary.wypisanie());
		dictionary.save();
	}
}
