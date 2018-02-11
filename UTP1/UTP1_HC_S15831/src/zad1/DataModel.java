package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import javafx.scene.chart.PieChart.Data;

public class DataModel extends DefaultTableModel
{
	List<Panstwo> list = new ArrayList<Panstwo>();
	String countriesFileName;
	String linnia = null;
	BufferedReader reader;
	String[] nazwyKolumn;
	public DataModel(String countriesFileName)
	{
		this.countriesFileName = countriesFileName;
		try
		{
			reader = new BufferedReader(new FileReader(this.countriesFileName));
			linnia = reader.readLine();
			nazwyKolumn = linnia.split("\\t+");
			for (String nazwaKolumny : nazwyKolumn)
			{
				addColumn(nazwaKolumny);
			}
			while ((linnia =reader.readLine()) != null)
			{
				String[] panstwo = linnia.split("\\t+");
				list.add(new Panstwo(panstwo[0], panstwo[1], Integer.parseInt(panstwo[2]), new ImageIcon("data/"+panstwo[0]+".png")));
				//System.out.println(panstwo[0]+" "+panstwo[1]+" "+panstwo[2]);
			}
			for (Panstwo panstwo : list)
			{
				addRow(new Object[]{panstwo.nazwa, panstwo.stolica, panstwo.ludnosc, panstwo.ikona});
			}
				
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public boolean isCellEditable(int row, int column)
    {
		switch (column) {
         case 2:
             return true;
         default:
             return false;
      }
    }
	@Override
	public Class<?> getColumnClass(int column)
	{
		switch(column) 
		{
			case 2 : return Integer.class;
			case 3: return ImageIcon.class;
			case 4 : return Data.class;
			default: return String.class;
		}
	}
	public Object getValueAt(int arg0, int arg1)
	{
		Panstwo p = list.get(arg0);
		if (arg1==0)
			 return p.getNazwa();
		if (arg1==1)
			 return p.getStolica();
		 if (arg1==2)
			 return p.getLudnosc();
		 if (arg1==3)
			 return p.getIkona();
		 if (arg1==4)
			 return p.getData();
		return null;
	}
	@Override
	public void setValueAt(Object arg0, int arg1, int arg2)
	{
		Panstwo p = list.get(arg1);
		if (arg2==2)
		{
			 p.setLudnosc(Integer.parseInt(arg0.toString()));
			 p.setData(LocalDateTime.now());
		}
	}
	
}
