/**
 *
 *  @author Han Cezary S15831
 *
 */

package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Anagrams {
    List<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
	public Anagrams(String allWords) throws FileNotFoundException 
	{
        Scanner scan = new Scanner (new FileReader(allWords));
        List <String> slowa = new ArrayList<String>();
        while (scan.hasNext()) 
        {
             slowa.add(scan.next());
        }
 
        scan.close();
 
        while (!slowa.isEmpty()) 
        {
            ArrayList<String> anagramy = new ArrayList<String>();
            String anagramPierwszy = slowa.remove(0);
            anagramy.add(anagramPierwszy);
            char[] ch = anagramPierwszy.toCharArray();
            Arrays.sort(ch);
            for (int i = 0; i<slowa.size(); i++) 
            {
                String slowo = slowa.get(i);
                char[] cha = slowo.toCharArray();
                Arrays.sort(cha);
                if (Arrays.equals(ch, cha)) 
                {
                    anagramy.add(slowo);
                    slowa.remove(i);
                    i--;
                }
            }
            lista.add(anagramy);
        }
}

	public List<ArrayList<String>> getSortedByAnQty()
	{
		for (ArrayList<String> arrayList : lista)
		{
			arrayList.sort((e1,e2)->{
				return e1.compareTo(e2);
			});
		}
		lista.sort((e1,e2)->{
			if (e2.size()!=e1.size())
				return e2.size()-e1.size();
			else
				return e1.get(0).compareTo(e2.get(0));
		});
		return lista;
	}

	public String getAnagramsFor(String slowoKlucz)
	{
		List<ArrayList<String>> listatmp;
		for (ArrayList<String> arrayList : lista)
		{
			for (String string : arrayList)
			{
				if (slowoKlucz.equals(string))
				{
					arrayList.remove(slowoKlucz);
					return arrayList.toString();
				}
				
			}
		}
		return null;
	}
}  
