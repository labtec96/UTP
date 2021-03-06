package zad2;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;


public class InputConverter<T>
{
	T dane;
	public InputConverter(T dane)
	{
		this.dane = dane;
	}

	public <R> R convertBy(Function ... funkcje)
	{
		Function f = funkcje[0];
		for(int i = 1 ; i < funkcje.length ; i++) {
			f = f.andThen(funkcje[i]);
		}
				return (R) f.apply(dane);
	}
}
