package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator <T>
{
	ArrayList<T> lista = new ArrayList<T>();
	
	public static <T> ListCreator<T> collectFrom(List<T> destinations)
	{
		ListCreator<T> tmp = new ListCreator<T>();
		for (T zmienna : destinations)
		{
			//System.out.println(zmienna);
			tmp.lista.add(zmienna);
		}
		return tmp;
	}

	public ListCreator<T> when (Predicate<String> predicate)
	{
		for(int i = 0; i < lista.size(); i++)
		{
			T element = lista.get(i);
			if (!predicate.test(element.toString().substring(0, 3)))
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
	//t string
	//s integer
	public <S> List<S> mapEvery(Function<T, S> func)
	{
		List<S> listaZwracana = new ArrayList<S>();
		for(int i = 0; i < lista.size(); i++)
		{
			T element = lista.get(i);
			S elementZmodyfikowany = func.apply(element);
			
			listaZwracana.add(elementZmodyfikowany);
		}
		return listaZwracana;
	}

	

}
