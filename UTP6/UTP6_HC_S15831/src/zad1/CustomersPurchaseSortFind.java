/**
 *
 *  @author Han Cezary S15831
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class CustomersPurchaseSortFind 
{
	LinkedList<Purchase> lista = new LinkedList<Purchase>();
	public void readFile(String fname)
	{
		try
		{
			BufferedReader  fileIn = new BufferedReader (new InputStreamReader(
                    new FileInputStream(fname), "UTF8"));
			String tmp;
			while((tmp=fileIn.readLine()) !=null)
			{
				String [] argumenty= tmp.split(";");
				String [] dane =argumenty[1].split(" ");
				lista.add(new Purchase(argumenty[0], dane[0],dane[1], argumenty[2], Double.parseDouble(argumenty[3]), Double.parseDouble(argumenty[4])));
			}
			fileIn.close();
		} catch (NumberFormatException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void showSortedBy(String polecenie)
	{
		System.out.println(polecenie);
		if (polecenie.equals("Nazwiska"))
		{
			Collections.sort(lista,Purchase.porownanieNazwisko);
			for (Purchase purchase : lista)
			{
				System.out.println(purchase);
			}
			System.out.println();
		}
		if (polecenie.equals("Koszty"))
		{
			Collections.sort(lista,Purchase.porownanieKoszty);
			for (Purchase purchase : lista)
			{
				System.out.println(purchase.toStringKoszty());
			}
			System.out.println();
		}
	}

	public void showPurchaseFor(String id)
	{
		System.out.println("Klient " + id);
		for (Purchase zakupy : lista)
		{
			if (zakupy.nrKlietna.equals(id))
				System.out.println(zakupy);
		}
		System.out.println();
	}

}
