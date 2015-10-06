package Labb2;

public class Fragment {
	
	int qualityLevel;
	int fragmentSize;
	double capacity = 0;
	boolean readyForNew = false;
	double timeElapsed = 0;
	
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
		
		public void addCurrentlyCapacity(double bits){
			
			capacity += bits;
		}
		
		public double getCurrentlyCapacity(){
			return capacity;
		}
		
		public void setReadyForNew(boolean b){
			readyForNew = b;
		}
		
		public boolean getReadyForNew(){
			return readyForNew;
		}
		
		public boolean downloadCompleted(){
			
			if(capacity >= fragmentSize){
				return true;
			}
			else{
				return false;
			}
		}
		
		public void addTimeElapsed(double d){
			
			timeElapsed += d;
		}
		
		public double getTimeElapsed(){
			return timeElapsed;
		}

}
