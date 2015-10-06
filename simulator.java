package Labb2;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Simulator {

	private double[] bandwidth = new double[Main.rows];
	private int[] requestedQuality = new int[Main.rows];
	double [] timeStamps = new double[Main.rows];
	double totalTime = 0;
	private double eastimatedBandwidth;
	
	ArrayList<Integer> Level = new ArrayList<Integer>();

	public Simulator(double[] bandwidth) {

		bandwidth = this.bandwidth;

	}

	public void readData() throws IOException {

		BufferedReader br = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(
								("C:/Users/Dotson/workspace/TDDD66Git/src/Labb2/Data.txt"))));

		for (int i = 0; i <= Main.rows; i++) {
			String temp = br.readLine();
			if (temp == null)
				break;

			String[] secTemp = temp.split(" ");
			double downloadTime = Double.parseDouble(secTemp[5])/1000;
			timeStamps [i] = downloadTime;
			totalTime += downloadTime;
			bandwidth[i] = 8.0*Integer.parseInt(secTemp[4]);
		}
	}

	public void test() {
		System.out.println(bandwidth[3]);
	}

	public void setQuality(VideoPlayer vp, double eb, int previousQuality) {
		
		int temp;
		
		if (vp.getQualityLevel(eb) > previousQuality) {

			temp = previousQuality +1;
			Level.add(temp);

		}

		else if (vp.getQualityLevel(eb) < previousQuality - 2) {

		//	Level.add(previousQuality - 2);
		} else {

			Level.add(vp.getQualityLevel(eb));
		}
	}

	public int getPreviousQulity(int time) {

		
		return Level.get(Level.size() -1);
	}

	public void setSameQuality(int previousQuality, int time) {

		requestedQuality[time] = previousQuality;
	}

	public void simulatorFunction(VideoPlayer vp) {

		boolean overMaxBuf = false;
		int previousQuality = -1;
		int qualityLevel = 0;
		Fragment newFragment;
		
		newFragment = new Fragment(qualityLevel);

		for (int i = 0; i < Main.rows; i++) {

			if (Main.currentBufferdData > Main.maxBuf) {
				overMaxBuf = true;
			}

			if (!overMaxBuf) {
				
				if(newFragment.getReadyForNew()){

				newFragment = new Fragment(qualityLevel);
				
				setQuality(vp, getEstimatedBandwidth(), previousQuality);
				previousQuality = getPreviousQulity(i);

				}
				newFragment.addCurrentlyCapacity(bandwidth[i]);
				newFragment.addTimeElapsed(timeStamps[i]);

				if (newFragment.downloadCompleted()) {

					Main.currentBufferdData += 4;
					newFragment.setReadyForNew(true);
					setEstimatedBandwidth(newFragment.getCurrentlyCapacity(), newFragment.getTimeElapsed());

				}

			} 

			if (overMaxBuf && Main.currentBufferdData < Main.minBuf) {
				overMaxBuf = false;
			}

			// Och Videoplayer in playmode
			if (i != 0) {
				Main.currentBufferdData -= timeStamps[i];

			}

		}
	}

	public void print() {

		for (int i = 0; i < Level.size(); i++) {
			System.out.println(Level.get(i));
		}
		System.out.println(Level.size());
	}
	
	//Option 1
	
	public void setEstimatedBandwidth(double bits, double time){
		
		eastimatedBandwidth = bits/time;
	}
	
	public double getEstimatedBandwidth(){
		return eastimatedBandwidth;
	}
	

}
