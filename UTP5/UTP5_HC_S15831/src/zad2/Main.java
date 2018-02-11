/**
 *
 *  @author Han Cezary S15831
 *
 */

package zad2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/*<-- niezbędne import */
public class Main {
  public static void main(String[] args) throws IOException  {
	  
	  Function<String,List> flines =(flines<String, List>) (a->{
			 List tmp =null;
					tmp =  Files.lines(Paths.get(a)).collect(Collectors.toList());
			return tmp;
		}); 
		  Function<List, String> join = (a->
		  {
			  String wynik ="";
			  for (int i=0; i <a.size(); i++)
				  wynik+=a.get(i);
			  return wynik;
		  });
		  Function <String,List<Integer>> collectInts = (a ->
		  {
			  Pattern p = Pattern.compile("[-]?[0-9]+");
			  Matcher m = p.matcher(a);
			 	List<String> matches = new ArrayList<>();
			    while (m.find()) {
			        matches.add(m.group(0));
			    }
			    

			  return matches.stream().map(Integer::parseInt).collect(Collectors.toList());
		  });
		  
		  Function<List<Integer>, Integer> sum = (a ->
		  {
			  int wynik =0;
			  for (int i = 0; i < a.size(); i++)
			{
				wynik+=a.get(i);
			}
			  return wynik;
		  });
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

    // Zadania badawcze:
    // Operacja flines zawiera odczyt pliku, zatem może powstac wyjątek IOException
    // Wymagane jest, aby tę operację zdefiniowac jako lambda-wyrażenie
    // Ale z lambda wyrażeń nie możemy przekazywac obsługi wyjatków do otaczającego bloku
    // I wobec tego musimy pisać w definicji flines try { } catch { }
    // Jak spowodować, aby nie było to konieczne i w przypadku powstania wyjątku IOException
    // zadziałała klauzula throws metody main
  }
}
