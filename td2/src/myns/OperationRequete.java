package myns;

public class OperationRequete {

	String action;
	int[] args;
	int id;
	
	public OperationRequete() {}
	
	public OperationRequete(String string, int[] tab, int i) {
		action=string;
		args=tab;
		id=i;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int[] getArgs() {
		return args;
	}

	public void setArgs(int[] args) {
		this.args = args;
	}
}
