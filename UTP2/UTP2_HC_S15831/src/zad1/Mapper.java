/**
 *
 *  @author Han Cezary S15831
 *
 */

package zad1;


public interface Mapper <T, S>
{
	public S map(T element);
	// Uwaga: interfejs musi być sparametrtyzowany
}  
