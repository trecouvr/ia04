package fr.utc.ia04.perception;

public class Stimulus implements Comparable<Stimulus> {

	/*
	 * Attributs
	 */
	private double intensity;
	private boolean positive;
	private Object source;
	
	/*
	 * Constructeur
	 */
	public Stimulus(double intensity, Object source) {
		super();
		if( intensity < 0 ){
			this.intensity = -intensity;
			this.positive = false;
		}
		else{
			this.intensity = intensity;
			this.positive = true;
		}
		this.source = source;
	}

	/*
	 * Getters
	 */
	public double getIntensity() {return intensity;}
	public boolean isPositive() {return positive;}
	public Object getSource() {return source;}

	/*
	 * Compare Method
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Stimulus other) {
		if(intensity < other.intensity)
			return 1;
		else if(intensity > other.intensity)
			return -1;
		else
			return 0;
	}
	
	
	
}
