package zad2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;


public class XList<T> implements List<T>
{
	List<T> list =new ArrayList<T>();
	public XList(T... args)
	{
		if (args.length ==1)
		{
			for (int i = 0; i < args.length; i++)
			list.addAll((Collection<? extends T>) args[i]);
		}
		else
		{

			for (int i = 0; i < args.length; i++)
			{
				list.add(args[i]);
				//System.out.println(args[i]);
			}
		}
	}


	public static <T> XList of(T... args)
	{
		return new XList<T>(args);
	}



	public static <T> XList<T> charsOf(String string)
	{
		char[] tab = string.toCharArray();
        List<Character> list = new ArrayList<>();
        for(int i=0; i<tab.length; i++){
            list.add(tab[i]);
        }
        return new XList(list);
	}

	public static <T> XList<T> tokensOf(String string)
	{
		String[] tab = string.split("\\s");
        List<String> list = new ArrayList<>();
        for(int i=0; i<tab.length; i++){
            list.add(tab[i]);
        }
        return new XList(list);
	}

	public static <T> XList<T> tokensOf(String string, String regex)
	{
		String[] tab = string.split(regex);
        List<String> list = new ArrayList<>();
        for(int i=0; i<tab.length; i++){
            list.add(tab[i]);
        }
        return new XList(list);
	}

	public  XList<T> union(Collection<T> kolekcja)
	{
		List<T> lista = new ArrayList<>();

		for (T t : list)
		{
			lista.add(t);
		}
		for (T t : kolekcja)
		{
			lista.add(t);
		}
		return new XList(lista);
	}
	
	public XList<T> union(T... args)
	{
		List<T> lista = new ArrayList<>();
		for (T t : list)
		{
			lista.add(t);
		}
		for (T t : args)
		{
			lista.add(t);
		}
		return new XList(lista);
	}

	public XList<T> diff(Collection<T> kolekcja)
	{
		List<T> lista = new ArrayList<>();
		for (T t : list)
		{
			int count=0;
			for (T kolekcjaEle : kolekcja)
			{
				if (kolekcjaEle.equals(t))
				{
					count++;
				}
			}
			if (count == 0 )
			lista.add(t);
		}
		return new XList(lista);
	}
	
	public XList<T> unique ()
	{
		List<T> lista = new ArrayList<>();
		for (T t : list)
		{
			int count=0;
			for (T listaEle : lista)
			{
				if (listaEle.equals(t))
				{
					count++;
				}
			}
			if (count == 0 )
			lista.add(t);
		}
		return new XList(lista);

		
	}
//	public XList<XList<T>> combine()
//	{
//		HashMap<Integer, List<String>> map  = new HashMap<>();
//		int iloscElementow=1;
//		XList<T> listatm = new XList<>();
//		for (int i = 0; i < list.size(); i++)
//		{
//			String listString =list.get(i).toString().replaceAll(",", "").replace("]", "").replace("[", "");
//			List<String> listTmp =  Arrays.asList(listString.split(" "));
//			map.put(i, listTmp);
//			iloscElementow = iloscElementow *listTmp.size();
//		}
//		ofCombinations(map.values(), LinkedHashSet::new).forEach(System.out::println);
//
//		return null;
//	}

	public <R> XList<R> collect(Function<T,R> f)
	{
		XList<R> tmp = new XList<>();
		for (T t : list)
		{
			R tmpEl = f.apply(t);
			tmp.add(tmpEl);
		}
		return tmp;
	}

	public String join(String sep)
	{
		String wynik="";
		for (T t : list)
		{
			wynik = wynik +t.toString().replaceAll(",", sep).replace("]", "").replace("[", "");
		}
		return wynik;
	}

	public String join()
	{
		String wynik="";
		for (T t : list)
		{
			wynik = wynik +t.toString().replaceAll(",", "").replace("]", "").replace("[", "");
		}
		return wynik;
	}

	public void forEachWithIndex(Object object)
	{
		// TODO Auto-generated method stub

	}
	public String toString()
	{
		return list.toString();
	}


	@Override
	public boolean add(T e)
	{
		// TODO Auto-generated method stub
		return list.add(e);
	}


	@Override
	public void add(int index, T element)
	{
		list.add(index, element);
	}


	@Override
	public boolean addAll(Collection<? extends T> c)
	{
		return list.addAll(c);
	}


	@Override
	public boolean addAll(int index, Collection<? extends T> c)
	{
		// TODO Auto-generated method stub
		return list.addAll(index, c);
	}


	@Override
	public void clear()
	{
		list.clear();
	}


	@Override
	public boolean contains(Object o)
	{
		// TODO Auto-generated method stub
		return list.contains(o);
	}


	@Override
	public boolean containsAll(Collection<?> c)
	{
		// TODO Auto-generated method stub
		return list.containsAll(c);
	}


	@Override
	public T get(int index)
	{
		// TODO Auto-generated method stub
		return list.get(index);
	}


	@Override
	public int indexOf(Object o)
	{
		// TODO Auto-generated method stub
		return list.indexOf(o);
	}


	@Override
	public boolean isEmpty()
	{
		// TODO Auto-generated method stub
		return list.isEmpty();
	}


	@Override
	public Iterator<T> iterator()
	{
		// TODO Auto-generated method stub
		return list.iterator();
	}


	@Override
	public int lastIndexOf(Object o)
	{
		// TODO Auto-generated method stub
		return list.lastIndexOf(o);
	}


	@Override
	public ListIterator<T> listIterator()
	{
		// TODO Auto-generated method stub
		return list.listIterator();
	}


	@Override
	public ListIterator<T> listIterator(int index)
	{
		// TODO Auto-generated method stub
		return list.listIterator(index);
	}


	@Override
	public boolean remove(Object o)
	{
		// TODO Auto-generated method stub
		return list.remove(o);
	}


	@Override
	public T remove(int index)
	{
		// TODO Auto-generated method stub
		return list.remove(index);
	}


	@Override
	public boolean removeAll(Collection<?> c)
	{
		// TODO Auto-generated method stub
		return list.removeAll(c);
	}


	@Override
	public boolean retainAll(Collection<?> c)
	{
		// TODO Auto-generated method stub
		return list.retainAll(c);
	}


	@Override
	public T set(int index, T element)
	{
		// TODO Auto-generated method stub
		return list.set(index, element);
	}


	@Override
	public int size()
	{
		// TODO Auto-generated method stub
		return list.size();
	}


	@Override
	public List<T> subList(int fromIndex, int toIndex)
	{
		// TODO Auto-generated method stub
		return list.subList(fromIndex, toIndex);
	}


	@Override
	public Object[] toArray()
	{
		// TODO Auto-generated method stub
		return list.toArray();
	}


	@Override
	public <T> T[] toArray(T[] a)
	{
		// TODO Auto-generated method stub
		return list.toArray(a);
	}





}
