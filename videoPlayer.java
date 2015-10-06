package Labb2;

public class VideoPlayer {
	
	
	
	public int checkQualityLevel(int bandwidth){
		
		int qualityLevel = 0;
		
		if(bandwidth >= 500000/8 && bandwidth < 850000/8){
			qualityLevel = 1;
		}
		
		if(bandwidth >= 850000/8 && bandwidth < 1300000/8){
			qualityLevel = 2;
		}
		
		if(bandwidth >= 1300000/8){
			qualityLevel = 3;
		}
		
	
		return qualityLevel;
	}

}
