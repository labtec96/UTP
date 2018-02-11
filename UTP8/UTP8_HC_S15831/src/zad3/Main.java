/**
 *
 *  @author Han Cezary S15831
 *
 */
package zad3;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	 private static String canonicalize(String s) {
	        return Stream.of(s.split("")).sorted().collect(Collectors.joining());
	    }

  public static void main(String[] args) throws IOException {
	  Map<String, Set<String>>  d= Files.lines(Paths.get(System.getProperty("user.home")+"/anagrams.txt"))
	  .flatMap(Pattern.compile("\\W+")::splitAsStream)
	  .collect(Collectors.groupingBy(Main::canonicalize, Collectors.toSet()));
	  d.values()
	  .stream()
	  .forEach(System.out::println);
  }
}
