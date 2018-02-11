package zad3;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

public class Account
{
	private PropertyChangeSupport chg = new PropertyChangeSupport(this);
	private VetoableChangeSupport veto = new VetoableChangeSupport(this);
	double stanKonta;
	private static int next_id = 0;
    public int id;   
	public Account(double i)
	{
		this.id = ++Account.next_id;
		this.stanKonta = i;
	}

	public Account()
	{
		this.id = ++Account.next_id;
		this.stanKonta = 0;
	}

	public void deposit(double i)
	{
		double oldValue = stanKonta;
		double  newValue = stanKonta+i;
		try
		{
			this.veto.fireVetoableChange("stanKonta", new Double(oldValue), new Double(newValue));
			this.stanKonta = newValue;
			System.out.println();
		} catch (PropertyVetoException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Double getstanKonta() 
	{
        return this.stanKonta;
    }
	public void withdraw(double i)
	{
		double oldValue = stanKonta;
		double  newValue = stanKonta-i;
		try
		{
			this.veto.fireVetoableChange("stanKonta", new Double(oldValue), new Double(newValue));
			this.chg.firePropertyChange("stanKonta", new Double(oldValue), new Double(newValue));
			this.stanKonta = newValue;
			System.out.println();
		} catch (PropertyVetoException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	public void transfer(Account acc1, double i)
	{
		double oldValue = stanKonta;
		double  newValue = stanKonta-i;
		try
		{
			veto.fireVetoableChange("stanKonta", new Double(oldValue), new Double(newValue));
			this.chg.firePropertyChange("stanKonta", new Double(oldValue), new Double(newValue));
			System.out.println();
			acc1.deposit(i);
			this.stanKonta = newValue;
			System.out.println();
		} catch (PropertyVetoException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public synchronized void addVetoableChangeListener(VetoableChangeListener listener) 
	{
		veto.addVetoableChangeListener(listener);
	}

	public synchronized void removeVetoableChangeListener(VetoableChangeListener listener) 
	{
		veto.removeVetoableChangeListener(listener);
	}
	public String toString()
	{
		return  "Acc: "+ id + " " + this.stanKonta;
		
	}

	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) 
	{
		chg.addPropertyChangeListener(listener);
	  }

	  public synchronized void removePropertyChangeListener(PropertyChangeListener l) 
	  {
		  chg.removePropertyChangeListener(l);
	  }


}
