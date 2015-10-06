package Labb2;

public class Fragment {
	
	int qualityLevel;
	int fragmentSize;
	int currentlyDownloaded = 0;
	boolean readyForNew = false;
	int timeElapsed = 0;
	
	public Fragment(int qualityLevel){
		
		qualityLevel = this.qualityLevel;
		
		switch(qualityLevel){
		
		case 0:
			fragmentSize = 250000;
			break;
		case 1:
			fragmentSize = 500000;
			break;
		case 2:
			fragmentSize = 850000;
			break;
		case 3:
			fragmentSize = 1300000;
			break;
		default: 
			break;
		
		}
		
	}
		public double getFragmentSize(){
			return fragmentSize;
	}
		
		public void setCurrentlyDownloaded(double bandwidth){
			
			currentlyDownloaded += bandwidth;
		}
		
		public double getCurrentlyDownloaded(){
			return currentlyDownloaded;
		}
		
		public void setReadyForNew(boolean b){
			readyForNew = b;
		}
		
		public boolean getReadyForNew(){
			return readyForNew;
		}
		
		public boolean downloadCompleted(){
			
			if(currentlyDownloaded >= fragmentSize){
				return true;
			}
			else{
				return false;
			}
		}
		
		public void addTimeElapsed(){
			
			timeElapsed += 1;
		}
		
		public int getTimeElapsed(){
			return timeElapsed;
		}

}
