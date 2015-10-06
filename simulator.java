package Labb2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Simulator {

	private int[] bandwidth = new int[Main.rows];
	private int[] requestedQuality = new int[Main.rows];

	public Simulator(int[] bandwidth) {

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
			bandwidth[i] = Integer.parseInt(secTemp[4]) * 8;
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
				newFragment.setCurrentlyDownloaded(bandwidth[i]);

				if (newFragment.downloadCompleted()) {

					Main.currentBufferdData += 4;
					newFragment.setReadyForNew(true);

				}

				setQuality(vp, i, previousQuality);
				previousQuality = getPreviousQulity(i);

			} else {
				// setSameQuality(previousQuality, i);
			}

			if (overMaxBuf && Main.currentBufferdData < Main.minBuf) {
				overMaxBuf = false;
			}

			// Och Videoplayer in playmode
			if (i != 0) {
				Main.currentBufferdData -= 1;

			}

		}
	}

	public void print() {

		for (int i = 0; i < Main.rows; i++) {
			System.out.println(requestedQuality[i]);
		}
	}

}
