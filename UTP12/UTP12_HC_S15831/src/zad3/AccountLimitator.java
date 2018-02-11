package zad3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

public class AccountLimitator implements VetoableChangeListener
{
	double limit = 200;
	public AccountLimitator(double i)
	{
		this.limit = i;
	}
	@Override
	public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException
	{
		Account a =(Account)e.getSource();
		if ((Double)e.getNewValue() >=limit)
		{
			Double oldVal = (Double) e.getOldValue(),
	           newVal = (Double) e.getNewValue();
			System.out.print(a.id +": Value changed from " + oldVal + " to " + newVal);
		}
		else
			  throw new PropertyVetoException(a.id +": Unacceptable value change: "+(Double)e.getNewValue(),e );
	}
}
