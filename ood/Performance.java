package ood;

public class Performance implements Comparable<Performance>{
	final private String venue;
	// number to show start time counting from midnight in minutes. e.g. 480 for 8am
	final private int startTime;
	// performance last period in minutes
	final private int lastTime;
	final private int maxCap;
	
	@Override
	public int compareTo(Performance other) {
		if (this.venue.equals(other.venue)) {
			if (this.startTime == other.startTime) {
				return Integer.compare(this.lastTime, other.lastTime);
			}
			return Integer.compare(this.startTime, other.startTime);
		}
		return this.venue.compareTo(other.venue);
	}
	
	public boolean equals(Performance other) {
		return this.venue.equals(other.venue) && this.startTime == other.startTime &&
				this.lastTime == other.lastTime;
	}
	
	public Performance (String venue, int startTime, int lastTime, int maxCap) {
		this.venue = venue;
		this.startTime = startTime;
		this.lastTime = lastTime;
		this.maxCap = maxCap;
	}

}
