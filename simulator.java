package Labb2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Simulator {

	private double[] bandwidth = new double[Main.rows];
	private int[] requestedQuality = new int[Main.rows];
	double [] timeStamps = new double[Main.rows];
	double totalTime = 0;
	private double eastimatedBandwidth;

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
			bandwidth[i] = 8.0*Integer.parseInt(secTemp[4])/downloadTime;
		}
	}

	public void test() {
		System.out.println(bandwidth[3]);
	}

	public void setQuality(VideoPlayer vp, int time, int previousQuality) {

		if (vp.checkQualityLevel(bandwidth[time]) > previousQuality) {

			requestedQuality[time] = previousQuality + 1;

		}

		else if (vp.checkQualityLevel(bandwidth[time]) < previousQuality - 2) {

			requestedQuality[time] = previousQuality - 2;
		} else {

			requestedQuality[time] = vp.checkQualityLevel(bandwidth[time]);
		}
	}

	public int getPreviousQulity(int time) {

		return requestedQuality[time];
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

				}
				newFragment.addCurrentlyCapacity(bandwidth[i]);
				newFragment.addTimeElapsed(timeStamps[i]);

				if (newFragment.downloadCompleted()) {

					Main.currentBufferdData += 4;
					newFragment.setReadyForNew(true);
					setEastimatedBandwidth(newFragment.getCurrentlyCapacity(), newFragment.getTimeElapsed());

				}

				setQuality(vp, i, previousQuality);
				previousQuality = getPreviousQulity(i);

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

		for (int i = 0; i < Main.rows; i++) {
			System.out.println(requestedQuality[i]);
		}
	}
	
	//Option 1
	
	public void setEastimatedBandwidth(double bits, double time){
		
		eastimatedBandwidth = bits/time;
	}
	
	//Option2
	/*public void setEastimatedBandwidth(double bits, double time){
		eastimatedBandwidth = (1-a)*oldEstimatedBandwidth + a*bits/time;
		oldEstimatedBandwidth = eastimatedBandwidth;
	}*/
	
	public double getEastimatedBandwidth(){
		return eastimatedBandwidth;
	}
	

}
