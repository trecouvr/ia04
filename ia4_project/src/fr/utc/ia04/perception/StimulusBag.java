package fr.utc.ia04.perception;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class StimulusBag {

	/*
	 * Attributs
	 */
	private HashMap<String, PriorityQueue<Stimulus> > map;
	private double minIntensity;
	
	/*
	 * Constructor
	 */
	public StimulusBag(double minIntensity) {
		super();
		map = new HashMap<String, PriorityQueue<Stimulus> >();
		this.minIntensity = minIntensity;
	}
	
	/*
	 * Methods
	 */
	public String getCategoryOfHightStimulus(){
		String cat = null;
		double stimulMax = 0;
		
		for(Entry<String,PriorityQueue<Stimulus>> it : this.map.entrySet())
			if( it.getValue().peek().getIntensity() > stimulMax ){
				stimulMax = it.getValue().peek().getIntensity();
				cat = it.getKey();
			}
		
		return cat;
	}
	
	public void offer(String category, Stimulus s){
		
		if(s.getIntensity() >= this.minIntensity){
		
			PriorityQueue<Stimulus> queue = map.get(category);
			
			if(queue == null){
				queue = new PriorityQueue<Stimulus>(10);
				map.put(category, queue);
			}
			queue.add(s);
		
		}
	}
	
	public Stimulus poll(String category){

		PriorityQueue<Stimulus> queue = map.get(category);
		
		if( queue == null )
			return null;
		else
			return queue.poll();
	}

}
