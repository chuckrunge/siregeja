package rptGenerator;

import java.util.List;

public class RowX implements Comparable<RowX> {
	
	public RowX(){}

	public RowX(int number, List<CellX> list) {
		super();
		this.number = number;
		this.list = list;
	}

	private int number;
	private List<CellX> list; //= new ArrayList();
	private boolean selected = true;
	private String sortKey = ""; 
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<CellX> getList() {
		return list;
	}

	public void setList(List<CellX> list) {
		this.list = list;
	};
	
	public boolean isSelected() {
		return this.selected;
	}

	public void setSelected(boolean selectRow) {
		this.selected = selectRow;
	}

	public String getSortKey() {
		
		StringBuffer newKey = new StringBuffer();
		if(sortKey.isEmpty()) {
			List<CellX> cellList = this.getList();
			for(CellX cell: cellList) {
				newKey.append(cell.getValue());
			}
			setSortKey(newKey.toString()); 
		}
		return sortKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public int compareTo(RowX row) {
		return this.getSortKey().
				compareTo(
						row.getSortKey());			
	}

	@Override
	public String toString() {
		return "Row [number=" + number + ", list=" + list.toString() + "]";
	}

}
