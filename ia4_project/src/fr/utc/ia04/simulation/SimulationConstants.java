package fr.utc.ia04.simulation;

/**
 * Cette classe contient toutes les paramètres nécessaires à la simulation.
 * 
 * @author Frozarmony
 *
 */
public class SimulationConstants {

	// Param Environement Simulation
	public final static double	ENV_SIZE =					100;
	public final static double	ENV_DISCRETIZATION =		0.1;
	public final static double	ENV_DT =					0.0167;
	
	// Proportion Agent
	public final static int		NUM_HUMAN =					20;
	public final static int		NUM_FASTFOOD =				5;
	public final static int 	NUM_HOTEL =					3;
	
	// Interactions Dist
	public final static double	DIST_NEAR =					1;
	public final static double	DIST_INTERACT =				2;
	
	// Gain
	public final static double	GAIN_NULL =					0.0;
	public final static double	GAIN_LOW =					0.2;
	public final static double	GAIN_MEDIUM =				0.5;
	public final static double	GAIN_HIGHT =				0.8;
	public final static double	GAIN_MAX =					1.0;
	
	// Main Perception Category
	public final static String	PERC_MAPBORDER =			"mapBorder";
	public static final String  PERC_FASTFOOD = 			"fastfood";
	public static final String  PERC_HOTEL = 				"hotel";
	
	// Main Agent States
	public final static String	STATE_DONOTHING =			"doNothing";
	public final static String	STATE_WALKING =				"walk";
	public final static String	STATE_SPEAKING =			"speak";
	public static final String  STATE_EATING =				"eat";
	public static final String  STATE_SLEEPING =			"sleep";
	public static final String  STATE_EATINGHUM =			"eat_hum";
	public static final String  STATE_ATTAKING =			"attak";
	public static final String  STATE_RUNNINGAWAY =			"run_away";

}
