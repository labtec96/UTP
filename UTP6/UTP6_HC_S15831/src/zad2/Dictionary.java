package zad2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;


public class Dictionary
{
	String fname;
	LinkedList<Pojecie> lista = new LinkedList<>();
	ArrayList<String> listaDefinicji = new ArrayList<>();
	String hasloDoUsuwania;
	public Dictionary(String fname)
	{
		try
		{
			BufferedReader  fileIn = new BufferedReader (new InputStreamReader(
                new FileInputStream(fname), "UTF8"));
			String tmp;
			while((tmp=fileIn.readLine()) !=null)
			{
				if (tmp.matches("\\w+ = \\w+"))
				{
					String [] argumenty= tmp.split(" = ");
					lista.add(new Pojecie(argumenty[0],argumenty[1]));
				}
			}
			fileIn.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public ArrayList<String> lookup(String haslo)
	{
		listaDefinicji.clear();
		int licznik=0;
		for (Pojecie pojecie : lista)
		{
			if (pojecie.getHaslo().equals(haslo))
			{
				listaDefinicji.add(pojecie.getDefinicja());
			}
		}
		Collections.sort(listaDefinicji);
		for (String definicja : listaDefinicji)
		{
			listaDefinicji.set(licznik, (licznik+1)+"."+definicja);
			licznik++;
		}
		hasloDoUsuwania = haslo;
		return listaDefinicji;
	}
	public void add(String haslo, String definicja)
	{
		int i =1;

		for (Pojecie pojecie : lista)
		{	
			if (!(pojecie.getHaslo().equals(haslo)) || !(pojecie.getDefinicja().equals(definicja)))
			{
				i++;
			}
		}
		if (i!=lista.size())
		{
			lista.add(new Pojecie(haslo, definicja));
		}
		else 
			System.out.println("Takie haslo istnieje");
	}
	public void delete(int pozycjaDoUsuniecia)
	{
		if (pozycjaDoUsuniecia>=1 && pozycjaDoUsuniecia<=listaDefinicji.size())
		{
			for (String pojecie : listaDefinicji)
			{
				if(Integer.parseInt(pojecie.substring(0, 1))==pozycjaDoUsuniecia)
				{
					Iterator<Pojecie> it = lista.iterator();
					String definicja= pojecie.substring(2, pojecie.length());
					while (it.hasNext()) 
					{
						Pojecie pojecie1 = it.next();
						if (pojecie1.getDefinicja().equals(definicja) && pojecie1.getHaslo().equals(hasloDoUsuwania)) 
						{
							it.remove();
						}
					}
				}
			}
			lookup(hasloDoUsuwania);
		}
		else
			System.out.println("Wyszedles poza kolekcje");
	}
	public void update(String haslo, String staraDefinicja, String nowaDefinicja)
	{
		int licznik = 0;
		for (Pojecie pojecie : lista)
		{
			//System.out.println(pojecie.getHaslo() + " " +pojecie.getDefinicja());
			if (pojecie.getHaslo().equals(haslo) && pojecie.getDefinicja().equals(staraDefinicja))
			{
				//System.out.println(pojecie.getHaslo() + " " +pojecie.getDefinicja());
				lista.set(licznik, new Pojecie(haslo, nowaDefinicja));
			}
			licznik++;
		}
	}
	public void save ()
	{
		try
		{
			FileWriter writer = new FileWriter(System.getProperty("user.home") + "/output.txt"); 
			for(Pojecie str: lista)
				writer.write(str.toString()+ "\r\n");
			writer.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String wypisanie()
	{
		String result="";
		for(Object pojecie : lista) 
		{
			result +=pojecie+"\n";
		}
		return result;
	}
}
