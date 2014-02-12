package com.thaipumi.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileLineShuffle {
	
	private static String[] readLines(String filename) {
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lines.toArray(new String[0]);
	}
	
	private static void writeLines(String filename, String[] lines){
		try {
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < lines.length; i++) {
				bw.write(lines[i]+"\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void shuffle(String filename) {
		shuffle(filename,filename);
	}
	
	public static void shuffle(String infilename, String outfilename){
		String[] lines = readLines(infilename);
		Util.shuffle(lines);
		writeLines(outfilename, lines);
	}
	
	public static void main(String args[]) {
		shuffle("E:\\projectlibrary\\liblinear-1.93\\windows\\FB5x5_1000.scaled","E:\\projectlibrary\\liblinear-1.93\\windows\\FBSH5x5_1000.scaled");
		System.out.println("finish RGB");
		shuffle("E:\\projectlibrary\\liblinear-1.93\\windows\\FB5x5_gray_1000.scaled","E:\\projectlibrary\\liblinear-1.93\\windows\\FBSH5x5_gray_1000.scaled");
		System.out.println("finish GRAY");
	}
}
