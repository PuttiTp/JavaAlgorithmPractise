package com.thaipumi.util;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Util {
	public static final String SIFTPATH = "E:\\Tsunami\\sift\\";
	public static final String FOLDERPATH = "E:\\Tsunami\\";
	public static final int IMG_W = 1024;
	public static final int IMG_H = 768;
	public static final String HISPATH = "E:\\Tsunami\\his\\";
	
	public static String[] getFilenames(String contrainerfilepath){
		BufferedReader br = null;
		ArrayList<String> filenames = new ArrayList<String>();
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(contrainerfilepath));
			
			while ((sCurrentLine = br.readLine()) != null) {
				filenames.add(sCurrentLine.trim());
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return filenames.toArray(new String[0]);
	}
	
	public static <T> void shuffle(T[] objArray){
		for (int i = 0 ; i < objArray.length ; i++){
			int j = (int)((objArray.length-i)*Math.random() + i);
			T tmp = objArray[i];
			objArray[i] = objArray[j];
			objArray[j] = tmp;
		}
	}
	
	public static void readsiftfile(String filename,ArrayList<double[]> list){
		BufferedReader br = null;
		try {
			 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(filename));
			
			while ((sCurrentLine = br.readLine()) != null) {
				String token[] = sCurrentLine.trim().split("\\s+");
				double vector[] = new double[token.length];
				//System.out.println("read "+filename+" "+descrtoken.length);
				for (int i = 0;  i < vector.length ; i++){
					vector[i] = Double.parseDouble(token[i]);
				}
				list.add(vector);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void shuffle(int[] array) {
		for (int i = 0 ; i < array.length ; i++){
			int j = (int)((array.length-i)*Math.random() + i);
			int tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
		}
	}

	public static void fillWithIndex(int[] writeOrder) {
		for (int i = 0; i < writeOrder.length; i++) {
			writeOrder[i] = i;
		}
	}
	
	public static void writeObj(String filename, Object obj) throws FileNotFoundException, IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
		oos.writeObject(obj);
		oos.close();
	}
	
	public static Object readObj(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
		Object obj = ois.readObject();
		ois.close();
		return obj;
	}
	
	public static ArrayList<String[]> getLabel(){
		BufferedReader br = null;
		ArrayList<String[]> labels = new ArrayList<String[]>();
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(Util.FOLDERPATH+"labeldata.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				String token[] = sCurrentLine.trim().split("\\s+");
				labels.add(token);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return labels;
	}
	
	public static ArrayList<String> getLabel(int labelid) {
		BufferedReader br = null;
		ArrayList<String> labels = new ArrayList<String>();
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(Util.FOLDERPATH+"labeldata.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				String token[] = sCurrentLine.trim().split("\\s+");
				labels.add(token[labelid]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return labels;
	}
	
	public static double eulerDistance(double a[] , double b[]){
		double dis = 0;
		for (int i = 0; i < b.length; i++) {
			dis += (a[i] - b[i])*(a[i] - b[i]);
		}
		return Math.sqrt(dis);
	}
	
}
