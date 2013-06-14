package fr.utc.ia04.decision;

import java.util.LinkedList;

import fr.utc.ia04.behaviour.Behaviour;

public class BehavioursMemory {

	/*
	 * Memory for one behaviour
	 */
	private class BehMemory{
		private double gainGeneralization;
		private LinkedList<Double> memory;
		
		public BehMemory() {
			super();
			this.gainGeneralization = 0;
			this.memory = new LinkedList<Double>();
		}
		
		public void addNewExp(double d){
			if( this.memory.size()>BehavioursMemory.this.maxExp )
				this.memory.pollLast();
			this.memory.offerFirst(d);
			this.computeGainGeneralization();
		}
		
		public boolean gainIsAvailable(){
			return this.memory.size()>=BehavioursMemory.this.minExp;
		}
		
		private void computeGainGeneralization(){
			
			if( this.memory.size() >= BehavioursMemory.this.minExp ){
				double decCoef = 1.0/((double)BehavioursMemory.this.maxExp);
				double coef = 1.0;
				double densityCoef = 0.0;
				double densityGain = 0.0;
			
				for(Double d : this.memory){
					densityGain += d*coef;
					densityCoef += coef;
					coef		-= decCoef;
				}
				
				this.gainGeneralization = densityGain / densityCoef;
			}
			
		}
		
	}
	
	/*
	 * Container
	 */
	private class Foo{
		private Behaviour beh;
		private BehMemory mem;
		
		public Foo(Behaviour beh, BehMemory mem) {
			super();
			this.beh = beh;
			this.mem = mem;
		}

		@Override
		public boolean equals(Object obj) {
			return this.beh.equals(obj);
		}
		
	}
	
	/*
	 * Members
	 */
	private int minExp;
	private int maxExp;
	private LinkedList<Foo> behList;
	
	/*
	 * Constructor
	 */
	public BehavioursMemory(int minExp, int maxExp) {
		super();
		this.minExp = minExp;
		this.maxExp = maxExp;
		this.behList = new LinkedList<Foo>();
	}
	
	/*
	 * Methods
	 */
	public void addExp(Behaviour b, double gain){
		Foo f = this.find(b);
		if( f == null ){
			BehMemory m = new BehMemory();
			m.addNewExp(gain);
			this.behList.add(new Foo(b, m));
			System.out.println(b.getClass().getName() + " --> " + gain);
		}
		else{
			f.mem.addNewExp(gain);
		}
	}
	
	public Double getExperimentedGain(Behaviour b){
		Foo f = this.find(b);
		if( f!=null && f.mem.gainIsAvailable() )
			return f.mem.gainGeneralization;
		else
			return null;
	}
	
	private Foo find(Behaviour b){
		for(Foo f : this.behList)
			if(f.equals(b))
				return f;
		return null;
	}
	
}
