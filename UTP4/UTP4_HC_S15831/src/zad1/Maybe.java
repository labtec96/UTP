package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T>
{
	T pole;
		public Maybe (T pole)
		{
			this.pole = pole;
		}
		public Maybe ()
		{
		}
	
		public String toString()
		{
			if (pole != null)
				return "Maybe has value " + pole;
			else
				return "Maybe is empty";
			
		}

		public static <T> Maybe<T> of(T s)
		{
			Maybe<T> tmp = new Maybe<T>(s);
			return tmp;
		}

		public void ifPresent(Consumer<T> cons)
		{
			if(pole!=null)
			cons.accept(pole);
		}

		public  <S> Maybe<S> map(Function<T, S> func)
		{
			if (pole !=null)
			{
				S tm =func.apply(pole);
				Maybe<S> tmp = new Maybe<S>(tm);
				return tmp;
			}
			else
			{
				Maybe<S> tmp = new Maybe<S>();
				return tmp;
			}
		}

		public T get()
		{
			if (pole!=null)
				return pole;
			else
				throw new NoSuchElementException(" maybe is empty");
		}
		
		public boolean isPresent()
		{
			if (pole!=null)
				return true;
			else 
				return false;
		}
		public T orElse(T defVal)
		{
			if (isPresent())
				return pole;
			else 
				return defVal;
		}

		public Maybe<T> filter(Predicate<T> pred) 
		{
			if (pred.test(pole) || !isPresent())
				return this;
			else
				return new Maybe<T>();
		}
		
		

}
