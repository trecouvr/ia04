package fr.utc.ia04.perception;

import java.util.HashMap;
import java.util.PriorityQueue;

public class StimulusBag {

	/*
	 * Attributs
	 */
	private HashMap<String, PriorityQueue<Stimulus> > map;
	
	/*
	 * Constructor
	 */
	public StimulusBag() {
		super();
		map = new HashMap<String, PriorityQueue<Stimulus> >();
	}
	
	/*
	 * Methods
	 */
	public void offer(String category, Stimulus s){
		
		PriorityQueue<Stimulus> queue = map.get(category);
		
		if(queue == null){
			queue = new PriorityQueue<Stimulus>(10);
			map.put(category, queue);
		}
		queue.add(s);
	}
	
	public Stimulus poll(String category){

		PriorityQueue<Stimulus> queue = map.get(category);
		
		if( queue == null )
			return null;
		else
			return queue.poll();
	}

}
