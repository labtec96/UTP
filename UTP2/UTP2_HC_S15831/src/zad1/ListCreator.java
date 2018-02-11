/**
 *
 *  @author Han Cezary S15831
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.List;


public class ListCreator <T,S>
{ // Uwaga: klasa musi być sparametrtyzowana
	ArrayList<T> lista = new ArrayList<T>();


	public static <T,S> ListCreator<T,S> collectFrom(List<T> lista)
	{
		ListCreator<T,S> tmp = new ListCreator<T,S>();
		for (T zmienna : lista)
		{
			//System.out.println(zmienna);
			tmp.lista.add(zmienna);
		}
		return tmp;
	}
	public ListCreator<T,S> when (Selector<T> sel)
	{
		for(int i = 0; i < lista.size(); i++)
		{
			T element = lista.get(i);
			if (!sel.select(element))
			{
				lista.remove(i);
				if (i!=0)
					i--;
			}
//			System.out.println(lista.get(i).toString());
//			System.out.println(i);
		}
		return this;
	}
	public List<S> mapEvery(Mapper<T,S> mapInt)
	{
		List<S> listaZwracana = new ArrayList<S>();
		for(int i = 0; i < lista.size(); i++)
		{
			T element = lista.get(i);
			S elementZmodyfikowany = mapInt.map(element);
			
			listaZwracana.add(elementZmodyfikowany);
		}
		return listaZwracana;
	}
}  
