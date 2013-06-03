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
	
	// Main Perception Category
	public final static String	PERC_MAPBORDER =			"mapBorder";
	
	// Main Agent States
	public final static String	STATE_DONOTHING =			"doNothing";
	public final static String	STATE_WALKING =				"walk";

}
