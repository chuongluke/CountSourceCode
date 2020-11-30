package lession1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		final File folder = new File("/Users/chuongtrinh061091/workspace/java_udacity/src/lession3");

        List<File> result = new ArrayList<>();
        List<Integer> totals = new ArrayList<>();

        search(".*\\.java", folder, result);
    	System.out.println("Filename              Code");
        for (File s : result) {
            System.out.println(s);
            try (FileReader reader = new FileReader(s.getAbsolutePath());
            		BufferedReader bReader = new BufferedReader(reader)) {
            	int count = SourceCodeLineCounter.getNumberOfLines(bReader);
            	totals.add(count);
            	System.out.println(s.getName() + "            " + count);
            } catch (IOException e) {
               System.err.format("IOException: %s%n", e);
            }
        }
        System.out.println("Total: " + sumCount(totals));
        for (File s : result) {
        	System.out.println("------------------------------------");
        	System.out.println(s.getName());
        	CommentAnalysis obj = new CommentAnalysis();
        	obj.fileName = s;
        	obj.analyzeFile();
        	System.out.println("------------------------------------");
        }
	}
	
	public static int sumCount(List<Integer> totals) {
		int result = 0;
		for (int i : totals) {
			result = result + i;
		}
		return result;
	}
	
	public static void search(final String pattern, final File folder, List<File> result) {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(pattern, f, result);
            }

            if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    result.add(f);
                }
            }

        }
    }
}
