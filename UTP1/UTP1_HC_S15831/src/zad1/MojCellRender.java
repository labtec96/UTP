package zad1;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MojCellRender extends DefaultTableCellRenderer 
{

	@Override
	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


		  if (Integer.parseInt(value.toString()) >20000)
		  {
			  cellComponent.setForeground(Color.red);
		  }
		  else 
		  {
			  cellComponent.setForeground(Color.black);
		  }
		return cellComponent;
	}

}
