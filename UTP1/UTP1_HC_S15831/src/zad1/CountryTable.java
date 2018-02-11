package zad1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.CellRendererPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javafx.scene.chart.PieChart.Data;
import jdk.nashorn.internal.runtime.ListAdapter;

public class CountryTable extends JTable
{
	public String countriesFileName;

	
	public CountryTable(String countriesFileName)
	{
		super(new DataModel(countriesFileName));
	}


	public JTable create()
	{
		getColumnModel().getColumn(2).setCellRenderer(new MojCellRender());
		return this;
	}

}
