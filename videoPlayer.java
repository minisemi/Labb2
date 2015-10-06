package Labb2;

public class VideoPlayer {
	
	
	
	public int checkQualityLevel(double bandwidth){
		
		int qualityLevel = 0;
		
		if(bandwidth >= 500000 && bandwidth < 850000){
			qualityLevel = 1;
		}
		
		if(bandwidth >= 850000/8 && bandwidth < 1300000){
			qualityLevel = 2;
		}
		
		if(bandwidth >= 1300000){
			qualityLevel = 3;
		}
		
	
		return qualityLevel;
	}

}
