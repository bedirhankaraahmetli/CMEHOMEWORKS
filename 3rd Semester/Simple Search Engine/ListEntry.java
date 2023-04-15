package searchEngine;

public class ListEntry {
    private String txtName;
    private int count;

    public ListEntry(String txtName) {
        this.txtName = txtName;
        count = 1;
    }

    public String getTxtName() {
        return txtName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
