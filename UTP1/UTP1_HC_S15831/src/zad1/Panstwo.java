package zad1;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;

public class Panstwo
{
	String nazwa;
	String stolica;
	int ludnosc;
	ImageIcon ikona;
	LocalDateTime data;
	public Panstwo(String nazwa, String stolica, int ludnosc, ImageIcon ikona)
	{
		this.nazwa = nazwa;
		this.stolica = stolica;
		this.ludnosc = ludnosc;
		this.ikona = ikona;
	}
	public String getNazwa()
	{
		return nazwa;
	}
	public void setNazwa(String nazwa)
	{
		this.nazwa = nazwa;
	}
	public String getStolica()
	{
		return stolica;
	}
	public void setStolica(String stolica)
	{
		this.stolica = stolica;
	}
	public int getLudnosc()
	{
		return ludnosc;
	}
	public void setLudnosc(int ludnosc)
	{
		this.ludnosc = ludnosc;
	}
	public ImageIcon getIkona()
	{
		return ikona;
	}
	public void setIkona(ImageIcon ikona)
	{
		this.ikona = ikona;
	}
	public LocalDateTime getData()
	{
		return data;
	}
	public void setData(LocalDateTime localDateTime)
	{
		this.data = localDateTime;
	}
}
