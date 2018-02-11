package zad1;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Futil
{
	public static void processDir(String dirName, String resultFileName)
	{
		List<String> list = new ArrayList<>();
		try
		{
			Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<Path>()
			{
				@Override
				public FileVisitResult visitFile(Path dir, BasicFileAttributes attrs) throws IOException
				{
					String file = dir.getFileName().toString();
					file = file.substring(file.length() - 3, file.length());
					if (file.equals("txt"))
					{
						BufferedReader reader = Files.newBufferedReader(dir, Charset.forName("windows-1250"));
						String line;
						while ((line = reader.readLine()) != null)
						{
							list.add(line);
						}
						reader.close();
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException ex)
		{
			System.out.println("blad");
		}
		try
		{
			FileOutputStream fos = new FileOutputStream(resultFileName);
			Writer w = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
			for (String tekst : list)
			{
				w.write(tekst);
				w.write(System.getProperty( "line.separator" ));
			}
			w.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
