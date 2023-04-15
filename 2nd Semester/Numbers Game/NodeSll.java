
public class NodeSll {

	private Object data;
	private NodeSll link;
	
	public NodeSll(Object data_to_add) {
		data = data_to_add;
		link = null;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public NodeSll getLink() {
		return link;
	}
	public void setLink(NodeSll link) {
		this.link = link;
	}	
}
