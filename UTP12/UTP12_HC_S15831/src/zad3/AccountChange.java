package zad3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

public class AccountChange implements PropertyChangeListener
{
	private PropertyChangeSupport chg = new PropertyChangeSupport(this);

	public synchronized void addPropertyChangeListener(PropertyChangeListener l)
	{
		chg.addPropertyChangeListener(l);
	}

	public synchronized void removePropertyChangeListener(PropertyChangeListener l)
	{
		chg.removePropertyChangeListener(l);
	}

	@Override
	public void propertyChange(PropertyChangeEvent e)
	{
		if ((Double)e.getNewValue()<0)
		   System.out.print(" ,balance < 0!");
		else
			System.out.println();
	}

}
