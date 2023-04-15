
public class ColumnNodeMll {

	private String column;
	private ColumnNodeMll right;
	private NumberNodeMll down;
	
	public ColumnNodeMll(String data_to_add) {
		this.column = data_to_add;
		this.right = null;
		this.down = null;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public ColumnNodeMll getRight() {
		return right;
	}

	public void setRight(ColumnNodeMll right) {
		this.right = right;
	}

	public NumberNodeMll getDown() {
		return down;
	}

	public void setDown(NumberNodeMll down) {
		this.down = down;
	}
}
