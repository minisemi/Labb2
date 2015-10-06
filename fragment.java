package Labb2;

public class Fragment {
	
	int qualityLevel;
	int fragmentSize;
	int currentlyDownloaded = 0;
	
	public Fragment(int qualityLevel){
		
		qualityLevel = this.qualityLevel;
		
		switch(qualityLevel){
		
		case 0:
			fragmentSize = 250000/8;
			break;
		case 1:
			fragmentSize = 500000/8;
			break;
		case 2:
			fragmentSize = 850000/8;
			break;
		case 3:
			fragmentSize = 1300000/8;
			break;
			
		
		}
		
	}
		public int getFragmentSize(){
			return fragmentSize;
	}
		
		public void setCurrentlyDownloaded(int bandwidth){
			
			currentlyDownloaded += bandwidth;
		}
		
		public int getCurrentlyDownloaded(){
			return currentlyDownloaded;
		}
		
		public boolean downloadCompleted(){
			
			if(currentlyDownloaded >= fragmentSize){
				return true;
			}
			else{
				return false;
			}
		}

}
