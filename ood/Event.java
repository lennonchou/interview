package ood;

import java.util.TreeSet;

public class Event implements Comparable<Event> {
	final private String title;
	final private String url;
	// use tree set to maintain some order
	final private TreeSet<Performance> performances = new TreeSet<>();
	public Event (String title, String url) {
		this.title = title;
		this.url = url;
	}
	
	@Override
	public int compareTo(Event other) {
		if (this.title.equals(other.title)) {
			return this.url.compareTo(other.url);
		}
		return this.title.compareTo(other.title);
	}
	
	public boolean equals(Event other) {
		return this.title.equals(other.title) && this.url.equals(other.url);
	}
	
	public boolean addPerformance(String venue, int startTime, int lastTime, int maxCap) {
		return performances.add(new Performance(venue, startTime, lastTime, maxCap));
	}
	
	public boolean addPerformance(Performance p) {
		return performances.add(p);
	}
	
	public boolean removePerformance(Performance p) {
		return performances.remove(p);
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getUrl() {
		return url;
	}
	
	public TreeSet<Performance> getPerformances() {
		TreeSet<Performance> copy = new TreeSet<>();
		copy.addAll(performances);
		// return copy so the content in performances can not be changed unless using public method addPerformance
		return copy;
	}
}
