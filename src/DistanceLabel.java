

public class DistanceLabel implements Comparable {
	String lable;
	double distanse;
	
	
	public DistanceLabel( double distanse, String lable) {
		super();
		this.lable = lable;
		this.distanse = distanse;
	}
	
	public DistanceLabel() {
		
	}
	
	
	@Override
	public String toString() {
		return "DistanceLabel [lable=" + lable + ", distanse=" + distanse + "]";
	}

	@Override
	public int compareTo(Object obj) {
		// TODO Auto-generated method stub
		double distance1=this.distanse;
		DistanceLabel df=(DistanceLabel)obj;
		double distance2=df.distanse;
		 if(distance1<distance2)
			 return -1;
		 else if(distance1>distance2)
			 return 1;
		 else
			 return -1;
		
		
	}
}
