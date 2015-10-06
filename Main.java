package Labb2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class Main {
	
	static public final int maxBuf = 6, minBuf = 4;
	static public double currentBufferdData;

	public static int rows = 565;

	static double[] rate = new double[rows];


	public static void main(String[] args) throws IOException {
			

		
		Simulator sim = new Simulator(rate);
		VideoPlayer vp = new VideoPlayer();
		
			sim.readData();
			sim.simulatorFunction(vp);
			sim.print();
		


	}

}
