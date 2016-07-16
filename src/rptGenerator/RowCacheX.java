package rptGenerator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RowCacheX {
	
	private Common common = new Common();
	
	public RowCacheX(List<RowX> list) {
		super();
		this.list = list;
	}

	public RowCacheX(){}
	
	Map<String,String> simpleAttr = new LinkedHashMap<String,String>();

	List<RowX> list;

	public List<RowX> getList() {
		return list;
	}

	public void setList(List<RowX> list) {
		this.list = list;
	}
	
	public String[] getLabels(String[] columns) {
		boolean isColumnsNull = false;
		String[] selected = new String[0];
		if(columns == null) {
			isColumnsNull = true;
		} 
		int i = 0;
		List<RowX> listRows = getList();
		if(listRows == null)
			return selected;
		RowX row = listRows.get(0);
		List<CellX> listCells = row.getList();
		if(isColumnsNull) {
			selected = new String[listCells.size()];
		} else {
			selected = new String[columns.length];
		}	
		
        for(CellX cell: listCells) {
        	if(isColumnsNull) {
        		selected[i++] = cell.getLabel();
        	} else {
        		if(common.isSelected(cell.getLabel(), columns)) {
        			selected[i++] = cell.getLabel();
        		}
        	}
        }
        
		return selected;
	
	}

	public int countSelected() {
		int i = 0;
		List<RowX> listRows = getList();
		if(listRows == null)
			return 0;
        for(RowX row: listRows) {
        	if(row.isSelected()) {
        		i++;
        	}
        }
		return i;
	}

	public void display() {
    	List<RowX> listRows = getList();
    	console(listRows.size()+" rows in cache");
        for(RowX row: listRows) {
        	List<CellX> listCells = row.getList();
        	console(listCells.size()+" cells in cache row "+row.getNumber());
        	for(CellX cell: listCells) {
        		console(row.getNumber()+":"+cell.getNumber()+" "+cell.getLabel()+" "+cell.getValue()+" "+cell.getDataType());
        	}
        }
	}

	public int countLabels(String[] columns) {
		int i = 0;
		List<RowX> listRows = getList();
		if(listRows == null)
			return 0;
		List<CellX> listCells = listRows.get(0).getList();
        for(CellX cell: listCells) {
        	if(common.isSelected(cell.getLabel(), columns)) {
        		i++;
        	}
        }
		return i;
	}

	public static void console(String sz) {
		System.out.println(sz);
	}
	
	@Override
	public String toString() {
		return "RowCache [list=" + list.toString() + "]";
	}
	

}
