package ood;

import java.util.TreeSet;

public class Organization {
	static public enum Category {
		THEATRE, SPORTS, EDUCATION
	}
	final private Category cat;
	final private String name;
	// use tree set to maintain some order
	final private TreeSet<Event> events = new TreeSet<>();
	
	public Organization (Category cat, String name) {
		this.cat = cat;
		this.name = name;
	}
	
	public boolean addEvent(String title, String url) {
		return events.add(new Event(title, url));
	}
	
	public boolean addEvent(Event e) {
		return events.add(e);
	}
	
	public boolean removeEvent(Event e) {
		return events.remove(e);
	}
}
