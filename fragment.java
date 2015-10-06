package Labb2;

public class Fragment {
	
	int qualityLevel;
	int fragmentSize;
	
	public Fragment(int qualityLevel){
		
		qualityLevel = this.qualityLevel;
		
		switch(qualityLevel){
		
		case 0:
			fragmentSize = 250000*8;
			break;
		case 1:
			fragmentSize = 500000*8;
			break;
		case 2:
			fragmentSize = 850000*8;
			break;
		case 3:
			fragmentSize = 1300000*8;
			break;
			
		
		}
		
	}

}
