package zad4;

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
		} catch (Exception exc)
		{
			System.out.println(exc);
			System.exit(1);
		}

		// Czesc A
		 String sel = "SELECT a.NAME,p.TYTUL,P.ROK,P.CENA FROM POZYCJE p" +
		 " INNER JOIN AUTOR A ON a.AUTID = p.AUTID" +
		 " WHERE p.CENA> 30 and p.ROK > 2000";
		 try
		 {
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(sel);
		 while (rs.next())
		 {
		 String nazwisko = rs.getString(1);
		 System.out.println("Autor: " + nazwisko);
		 }
	
		 } catch (SQLException exc)
		 {
		 System.out.println(exc.getMessage());
		 }
		// CZESC B
		String sel1 = "SELECT * from Pozycje";
		try
		{
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sel1);
			ResultSetMetaData rsmd = rs.getMetaData();
			int cc = rsmd.getColumnCount();
			for (int i = 1; i <= cc; i++)
				System.out.print(rsmd.getColumnLabel(i) + "     ");

			System.out.println("\n------------------------------ przewijanie do góry");
			rs.last();
			do 
			{
				for (int i = 1; i <= cc; i++)
					System.out.print(rs.getString(i) + "     ");
				System.out.println();
			}while(rs.previous());
			System.out.println("\n----------------------------- pozycjonowanie abs.");
			int[] poz =
			{ 3, 7, 9 };
			for (int p = 0; p < poz.length; p++)
			{
				rs.absolute(poz[p]);
				System.out.print("[ " + poz[p] + " ] ");
				for (int i = 1; i <= cc; i++)
					System.out.print(rs.getString(i) + ", ");
				System.out.println("");
			}
			stmt.close();
			con.close();
		} catch (SQLException exc)
		{
			System.out.println(exc.getMessage());
		}
	}
}