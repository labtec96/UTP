package zad2;

import java.io.BufferedWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Futil
{
	public static void processDir(String dirName, String resultFileName)
	{

		try 
		{
			FileOutputStream fos = new FileOutputStream(resultFileName);
			Writer w = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
			Stream<Path> stream = Files.walk(Paths.get(dirName));
			stream.filter(path -> path.toString().endsWith(".txt"))
			.forEach(path ->{
				try
				{
					Files.lines(path,Charset.forName("windows-1250"))
					.forEach(linia -> {
						try
						{
							w.write(linia);
							w.write(System.getProperty( "line.separator" ));
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			stream.close();
			w.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
