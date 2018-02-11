/**
 *
 *  @author Han Cezary S15831
 *
 */

package zad4;

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

public class Main {
  public static void main(String[] args) {
	  Function<String,List> flines = (a->{
			 List tmp =null;
			try
			{
				tmp =  Files.lines(Paths.get(a)).collect(Collectors.toList());
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

    // Przy powierzchownej implementacji
    // następujący fragment:
    slistConv.convertBy(collectInts, sum); // spowoduej powstanie wyjątku ClassCastException

    // Zadania badawcze:
    // jak temu zaradzić w fazie wykonania programu, tak by uzyskiwać operacyjne wyniki (i nigdy NullPointer)
    // - wymaga odpowidniej definicji klasy InputConverter oraz ew. modyfikacji klasy Main (są tu dozwolone) 
  }
}
