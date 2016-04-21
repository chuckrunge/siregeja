package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.sidacoja.utils.*;
import rptGenerator.Generator;
import rptGenerator.Cell;
import rptGenerator.Row;
import rptGenerator.RowCache;
import org.junit.Test;

public class testGenerator1 {

	@Test
	public void test01() {

		Generator generator = new Generator(6,15,120);
		RowCache cache = createCache();
		generator.execRpt(cache);
		
		//validate number of lines
		console("line count is: "+generator.getLineCtr());
		assert(generator.getLineCtr()==14);
		
		//validate column totals
		HashMap<String, Integer> totals = generator.getTotalsCache();
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
	public rptGenerator.RowCache createCache() {
		rptGenerator.RowCache genCache = new rptGenerator.RowCache();
		List<rptGenerator.Row> rptRowList = new ArrayList<rptGenerator.Row>();
		
		for (int i=0;i<12;i++) {
			Row row = createRow(i);
			rptRowList.add(row);
		}
		genCache.setList(rptRowList);
		return genCache;
	}
	
	//create mock row
	public Row createRow(int i) {
		rptGenerator.Row rptRow = new rptGenerator.Row();
		List<rptGenerator.Cell> rptCellList = new ArrayList<rptGenerator.Cell>();
		//for (Cell cell : cellList) {
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
		//}
		rptRow.setList(rptCellList);
		return rptRow;
	}
	
	//create mock cell
	public Cell createCell(int number, String label, String value, String dataType ) {
		Cell rptCell = new Cell(number, label, value, dataType);
		return rptCell;
	}
	public void console(String sz) {
		System.out.println(sz);
	}

} //end class