package myns;

public class OperationReponse {

	int id;
	int value;
	String comment;
	
	public OperationReponse() {}
	
	public OperationReponse(int n, String string, int i) {
		value = n;
		comment = string;
		id=i;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}
}
