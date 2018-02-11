/**
 *
 *  @author Han Cezary S15831
 *
 */
 
package zad1;
 
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

 
 
 
 
 
public class Calc {
 
    public String doCalc(String string)
    {
        HashMap<String,Method> metody = new HashMap<String,Method>();
        Method metoda = null;
        BigDecimal result = null;
        BigDecimal nr1 = null;
        BigDecimal nr2 = null;
        MathContext mc = new MathContext(7);
        try
        {
        		String[] elementy = string.split("\\s+");
                Class<BigDecimal> klasa =  (Class<BigDecimal>) Class.forName("java.math.BigDecimal");
                metoda = klasa.getMethod("add",BigDecimal.class, MathContext.class);
                metody.put("+",metoda);
                metoda = klasa.getMethod("subtract",BigDecimal.class, MathContext.class);
                metody.put("-",metoda);
                metoda = klasa.getMethod("multiply",BigDecimal.class, MathContext.class);
                metody.put("*",metoda);
                metoda = klasa.getMethod("divide",BigDecimal.class, MathContext.class);
                metody.put("/",metoda);  
            nr1 = new BigDecimal(elementy[0]);
            nr2 = new BigDecimal(elementy[2]);
            metoda = metody.get(elementy[1]);
            result = (BigDecimal)metoda.invoke(nr1,nr2,mc);
            return result.toPlainString();
        }catch(Exception e)
        {
            return "Invalid command to calc";
        }
    }
}