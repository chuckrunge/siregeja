package test.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.sidacoja.utils.Cell;
import com.sidacoja.utils.Row;
import com.sidacoja.utils.RowCache;

import rptGenerator.Generator;

public class TestGenerator01 {

	@Test
	public void test01() {
		Generator generator = new Generator(6,15,120);
		com.sidacoja.utils.RowCache cache = createCache();
		generator.execRpt(cache);
		
		//validate number of lines
		console("line count is: "+generator.getLineCtr());
		assert(generator.getLineCtr()==14);
		
		//validate column totals
		HashMap<String, Double> totals = generator.getTotalsCache();
		Set<String> keys = totals.keySet();
		for(String key: keys) {
			if("INCOME".equals(key)) {
				assert(totals.get(key)==600000);
			}
			if("ID".equals(key)) {
				assert(totals.get(key)==6600);
			
			}
		}
		
	}
	//create mock data object
	public RowCache createCache() {

		RowCache genCache = new RowCache();
		List<Row> rptRowList = new ArrayList<Row>();
		
		for (int i=0;i<12;i++) {
			Row row = createRow(i);
			rptRowList.add(row);
		}
		genCache.setList(rptRowList);
		return genCache;
	}

	//create mock row
	public Row createRow(int i) {
		Row rptRow = new Row();
		List<Cell> rptCellList = new ArrayList<Cell>();

		int j = 100 * i;
		
		Cell cell1 = createCell(1,"ID",Integer.toString(j),"Integer");
		rptCellList.add(cell1);
		Cell cell2 = createCell(2,"NAME","Fluff McFluff","String");
		rptCellList.add(cell2);
		Cell cell3 = createCell(3,"ADDRESS","300 Stret St.","String");
		rptCellList.add(cell3);
		Cell cell4 = createCell(4,"CITYST","Foobar, ST","String");
		rptCellList.add(cell4);
		Cell cell5 = createCell(5,"INCOME","50000","Integer");
		rptCellList.add(cell5);
		Cell cell6 = createCell(6,"PSWD","password","String");
		rptCellList.add(cell6);
		
		rptRow.setList(rptCellList);
		return rptRow;
	}

	//create mock cell
	public Cell createCell(int number, String label, String value, String dataType ) {
		Cell rptCell = new Cell(number, label, value, dataType);
		rptCell.setDataType(dataType);
		rptCell.setLabel(label);
		rptCell.setNumber(number);
		rptCell.setValue(value);
		return rptCell;
	}
	
	public void console(String sz) {
		System.out.println(sz);
	}


} //end class


