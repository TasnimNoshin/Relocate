package project.Relocate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;


/**
 * 
 * @author Jenny Feng Chen
 * This is a class that contains the Province Abbrviation and the province name
 * So it can be access in both way.
 * @param <K> 
 * @param <V>
 */
public class ProvinceMap<K, V> {
	private final String f = "data/provinces.txt";
	private Map<String,String> forward = new Hashtable<String,String>();
	private Map<String,String> backward = new Hashtable<String,String>();

	public ProvinceMap() throws FileNotFoundException{
		Scanner file = new Scanner(new File(f));
		while(file.hasNextLine()){
			String[] name = file.nextLine().split(",");
			add(name[0], name[1]);
		}
		file.close();
	}
	
	public void add(String key, String value) {
		forward.put(key, value);
		backward.put(value, key);
	}

	public String getForward(String key) {
		return forward.get(key);
	}

	public String getBackward(String key) {
		return backward.get(key);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		ProvinceMap m = new ProvinceMap();
		System.out.println(m.getBackward("NB"));
	}
	
}
