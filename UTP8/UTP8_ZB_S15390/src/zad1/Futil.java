package zad1;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {
	static String calosc;
	public static void processDir(String dirName, String resultFileName) {
		Path sPath = Paths.get(dirName);
		try {
			calosc = "";
			Files.walkFileTree(sPath, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					String line = "";
					FileInputStream f = new FileInputStream(file.toString());
					BufferedReader br = new BufferedReader(new InputStreamReader(f, "Cp1250"));
					while((line = br.readLine()) != null){
						calosc += line;
					}
					br.close();				
					return FileVisitResult.CONTINUE;
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
