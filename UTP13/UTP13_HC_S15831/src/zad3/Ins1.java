package zad3;

import java.sql.*;

import java.sql.*;

public class Ins1
{

	static public void main(String[] args)
	{
		new Ins1();
	}

	Statement stmt;

	Ins1()
	{
		Connection con = null;
		try
		{
			con = DriverManager.getConnection("jdbc:derby:D:/DerbyDB/ksidb");
			stmt = con.createStatement();
		} catch (Exception exc)
		{
			System.out.println(exc);
			System.exit(1);
		}
		// nazwy wydawców do wpisywania do tabeli
		String[] wyd =
		{ "PWN", "PWE", "Czytelnik", "Amber", "HELION", "MIKOM" };

		// pierwszy numer wydawcy do wpisywania do tabeli: PWN ma numer 15, PWE
		// ma 16, ...
		int beginKey = 15;

		String[] ins = new String[wyd.length]; // ? ... tablica instrukcji SQL
												// do wpisywania rekordów do
												// tabeli: INSERT ...
		for (int i = 0; i < ins.length; i++)
		{
			ins[i] = "Insert into WYDAWCA (WYDID, NAME) " + "values( " + beginKey + ", '" + wyd[i] + "' )";
			beginKey++;
		}
		int insCount = 0; // ile rekordów wpisano
		try
		{
			for (int i = 0; i < ins.length; i++)
			{
				stmt.executeUpdate(ins[i]);
				insCount++;
			}
		} catch (Exception e)
		{
			System.out.println(e);
			System.exit(1);
		}
		System.out.println("wpisano " + insCount + " rekordow");
		/// CZESC B
		PreparedStatement preparedStatement = null;
		int delCount = 0;
		try
		{
			String deleteSQL = "DELETE FROM WYDAWCA WHERE WYDID = ?";
			preparedStatement = con.prepareStatement(deleteSQL);
			// usunięcie z
															// tabeli WYDAWCA
															// rekordu o podanej
															// nazwie wydawcy z
															// tablicy wyd lub o
															// podanym numerze
															// wydawcy
															// zaczynającym się
											// od beginKey
			int beginkey = 15;
			for (int i = 0; i < wyd.length; i++)
			{
				preparedStatement.setInt(1, beginkey);
				preparedStatement.executeUpdate();
				beginkey++;
				delCount++;
			}
			con.close();
		} catch (SQLException exc)
		{
			System.out.println(exc);
		}
		System.out.println("Usunieto " + delCount + " Elementow");

	}
}