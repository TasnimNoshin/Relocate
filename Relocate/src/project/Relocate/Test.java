package project.Relocate;

import java.io.IOException;
import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args) throws IOException {
		JobArray pp = new JobArray();
		ArrayList<Job> j = pp.getJobArray();
		System.out.println(j==null);  // false
		System.out.println(j.size());
//		for (Job job : j) {
//			System.out.println(job);
//		}
	}
}
