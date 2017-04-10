package project.Relocate;

import java.io.FileWriter;
import java.io.IOException;

public class SearchOutput {
	public static void main(String[] args) throws IOException {
		String t = new Searcher().searchCity("banking");
		String s = new Searcher().searchProvinceCity("banking", "on");
		
		FileWriter f = new FileWriter("data/output.txt");
		f.write(s);
		f.close();
	}
}
