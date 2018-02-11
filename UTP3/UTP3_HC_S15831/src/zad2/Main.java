/**
 *
 *  @author Han Cezary S15831
 *
 */

package zad2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*<-- niezbędne importy */

public class Main {

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = 
    /*<-- tu należy dopisać fragment
     * przy czym nie wolno używać żadnych własnych klas, jak np. ListCreator
     * ani też żadnych własnych interfejsów
     * Podpowiedź: należy użyć strumieni
     */
    	
    
    	  dest.stream()
    	  				.filter(line -> "WAW".equals(line.substring(0, 3)))
    	  				.map(line -> "to " + line.substring(4,7) + " - price in PLN:	" + (Double.parseDouble(line.substring(8,line.length()))*ratePLNvsEUR))
    	  				.collect(Collectors.toList());
    	 

    for (String r : result) System.out.println(r);
  }
}
