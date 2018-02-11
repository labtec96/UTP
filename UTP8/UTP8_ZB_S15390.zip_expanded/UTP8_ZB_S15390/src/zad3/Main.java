/**
 *
 *  @author Żukowska Barbara S15390
 *
 */

package zad3;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

public class Main {
	private static String zamiana(String s) {
        return Stream.of(s.split("")).sorted().collect(Collectors.joining());
    }
	static long max;
    public static void getAnagrams(InputStreamReader reader) {
    	max = 0;
	    Map<String, Set<String>> map = new BufferedReader(reader).lines()
	                                     .flatMap(Pattern.compile("\\W+")::splitAsStream)
	                                     .collect(Collectors.groupingBy(Main::zamiana, Collectors.toSet()));
	    map.values().stream().filter(list -> {
	    	if(max < list.stream().count()) {
	    		max = list.stream().count();
	    		return true;
	    	} else { return false; }
	    }).collect(Collectors.toList());
	    
	    List<String> n = new ArrayList<>();
	    map.values().stream().filter(list -> {
	    	if(list.stream().count() == max) {
	    		TreeSet<String> ts = new TreeSet<>(list);
	    		n.add(String.join(", ", ts));
	    	}    
	    	return list.stream().count() == max;
	    	}).collect(Collectors.toList());
	    
	    Collections.sort(n);
	    for(String s : n) {
	    	System.out.println(s);
	    }
    }
    
    public static void main(String[] args) throws IOException {
        getAnagrams(new InputStreamReader(new URL("http://www.puzzlers.org/pub/wordlists/unixdict.txt").openStream(), StandardCharsets.UTF_8));
    }
}
