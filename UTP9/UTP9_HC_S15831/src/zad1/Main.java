package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main
{

	public static void konkretnePanstwoRok(Stream<String> stream, String panstwo, String rok)
	{
		stream.filter(c -> c.substring(0, panstwo.length()).equals(panstwo)
				&& c.substring(c.indexOf(" ") + 1, c.indexOf(" ") + rok.length() + 1).equals(rok))
				.forEach(System.out::println);
	}

	public static void najwiekszaLiczbaLudnosc(Stream<String> stream)
	{
		stream.sorted(new Comparator<String>()
		{
			@Override
			public int compare(String s1, String s2)
			{
				Pattern pattern = Pattern.compile("(\\d*\\.\\d+)");
				Matcher matcher1 = pattern.matcher(s1);
				matcher1.find();
				String liczbaLudnosci1 = matcher1.group(0);
				Matcher matcher2 = pattern.matcher(s2);
				matcher2.find();
				String liczbaLudnosci2 = matcher2.group(0);
				return Double.compare(Double.parseDouble(liczbaLudnosci2), Double.parseDouble(liczbaLudnosci1));
			}

		}).limit(1).forEach(System.out::println);
	}

	public static List<String> panstwaNaLitere(Stream<String> stream, String poczatek)
	{
		List<String> tmp = stream.map(c -> c.substring(0, c.indexOf(" ")))
				.filter(c -> c.startsWith(poczatek))
				.distinct()
				.collect(Collectors.toList());

		tmp.sort(new Comparator<String>()
		{
			public int compare(String pojecie1, String pojecie2)
			{
				Collator collator = Collator.getInstance(new Locale("pl", "PL"));
				String definicja1 = pojecie1.toUpperCase();
				String definicja2 = pojecie2.toUpperCase();
				return collator.compare(definicja2, definicja1);
			}

		});
		return tmp;
	}
	
	public static List<Panstwo> panstwaNaLitereObiekt(Stream<String> stream, String poczatek, String rok)
	{
		List<Panstwo> tmp = stream.filter(c -> c.startsWith(poczatek)
				&& c.substring(c.indexOf(" ") + 1, c.indexOf(" ") + rok.length() + 1).equals(rok))
				.map(Panstwo::new)
				.collect(Collectors.toList());

		tmp.sort(new Comparator<Panstwo>()
		{
		

			@Override
			public int compare(Panstwo arg0, Panstwo arg1)
			{
				Double ludnosc1 = arg0.ludnosc;
				Double ludnosc2 = arg1.ludnosc;
				return Double.compare(ludnosc2, ludnosc1);
			}

		});
		return tmp;
	}

	public static double liczbaLudnosciWroku(Stream<String> stream, String rok)
	{
		return stream.filter(c -> c.substring(c.indexOf(" ") + 1, c.indexOf(" ") + rok.length() + 1).equals(rok))
				.mapToDouble(c -> {
					Pattern pattern = Pattern.compile("(\\d*\\.\\d+)");
					Matcher matcher = pattern.matcher(c);
					matcher.find();
					String liczbaLudnosci = matcher.group(0);
					return Double.parseDouble(liczbaLudnosci);
				}).sum();
	}

	public static void main(String[] args) throws IOException
	{
		String fileName = "src/data.txt";
		// fileName.indexOf(ch)
		// stream.map(c ->
		// {
		// System.out.println(c);
		// Pattern pattern = Pattern.compile("(\\d?\\.\\d+)");
		// Matcher matcher = pattern.matcher(c);
		// matcher.find();
		// String liczbaLudnosci = matcher.group(0);
		// System.out.println(whatYouNeed);
		// return whatYouNeed;
		// })
		// .forEach(System.out::println);
		najwiekszaLiczbaLudnosc(Files.lines(Paths.get(fileName)));
		konkretnePanstwoRok(Files.lines(Paths.get(fileName)), "Zambia", "1964");
		System.out.println(panstwaNaLitere(Files.lines(Paths.get(fileName)), "Mo"));
		List<Panstwo> lista = panstwaNaLitereObiekt(Files.lines(Paths.get(fileName)), "Mo", "1964");
		System.out.println();
		for (Panstwo panstwo : lista)
		{
			System.out.println(panstwo.nazwa + " " + " rok " + panstwo.rok + " " + " ludnosc:"+ panstwo.ludnosc);
		}
		System.out.println();
		System.out.println(liczbaLudnosciWroku(Files.lines(Paths.get(fileName)), "2043"));
	}

}
