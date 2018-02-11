package zad2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.*;

public class Futil {
	static String calosc = "";
	public static void processDir(String dirName, String resultFileName){
		try {
			calosc = "";
			Files.walk(Paths.get(dirName))
				.filter(p -> p.toString().endsWith(".txt"))
				.forEach(p -> {
						String line = "";
						try {
							FileInputStream f = new FileInputStream(p.toString());
							BufferedReader br = new BufferedReader(new InputStreamReader(f, "Cp1250"));
							while((line = br.readLine()) != null){
								calosc += line;
							}
							br.close();	
						} catch (IOException e) {
							e.printStackTrace();
						}
			});
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFileName), "UTF-8"));
			out.write(calosc);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
