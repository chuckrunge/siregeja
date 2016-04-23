package Examples;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sidacoja.utils.Cell;
import com.sidacoja.utils.Row;
import com.sidacoja.utils.RowCache;
import com.sidacoja.utils.Sidacoja;

import rptGenerator.Generator;

public class SidacojaExample {

	public static void main(String[] args) {

		final String DB_URL = "jdbc:hsqldb:hsql://localhost:9001/mdb";

		Sidacoja sdcj = new Sidacoja();

		sdcj.input(DB_URL);
		sdcj.inputType("jdbc");
		sdcj.setTable("Employees");

		// sdcj.columns(new String[]{"Sponsor", "Member", "Contact"});
		// sdcj.sequence(new String[]{"Sponsor"});
		// sdcj.addFilter(new String[]{"OR", "Sponsor","EQ","Paypal1"});
		// sdcj.output("jdbc:sqlserver://CHUCK-PC\\SQLSERVERADVSP2;database=chuckDB;integratedSecurity=true;user=Chuck-PC\\Chuck;");

		sdcj.setCacheOnly(true);
		sdcj.output("N/A");
		// sdcj.setOutputTable("copyTable");
		sdcj.outputType("jdbc");

		RowCache cache = new RowCache();
		try {
			cache = sdcj.fire();
		} catch (Exception e) {
			console(e.getMessage());
			e.printStackTrace();
		}

		try {

			Generator generator = new Generator(4, 12, 120); // nbr cols, data
																// length,
																// linelength
			generator.setHeading1(sdcj.getTable());
			Calendar cal = Calendar.getInstance();
			String dfString = DateFormat.getDateInstance().format(cal.getTime());
			generator.setHeading2(dfString);
			generator.setHeading3(" ");
			generator.setControlBreak1("FIRST");
			generator.setControlBreak2(null);
			generator.setControlBreak3(null);
			generator.setFooting1("*");
			generator.setFooting2("**");
			generator.setFooting3("End of Report");
			generator.execRpt(copyCache(cache));

		} catch (Exception rex) {
			System.out.println(rex.getMessage());
			rex.printStackTrace();
		}

	} // end method

	public static rptGenerator.RowCache copyCache(RowCache cache) {
		rptGenerator.RowCache genCache = new rptGenerator.RowCache();
		List<Row> rowList = cache.getList();
		List<rptGenerator.Row> rptRowList = new ArrayList<rptGenerator.Row>();
		for (Row row : rowList) {
			rptGenerator.Row rptRow = new rptGenerator.Row();
			List<Cell> cellList = row.getList();
			List<rptGenerator.Cell> rptCellList = new ArrayList<rptGenerator.Cell>();
			for (Cell cell : cellList) {
				rptGenerator.Cell rptCell = new rptGenerator.Cell();
				if ("AGE".equals(cell.getLabel())) {
					rptCell.setDataType("Integer");
				} else {
					rptCell.setDataType(cell.getDataType());
				}
				rptCell.setLabel(cell.getLabel());
				rptCell.setNumber(cell.getNumber());
				rptCell.setValue(cell.getValue());
				rptCellList.add(rptCell);
			}
			rptRow.setList(rptCellList);
			rptRowList.add(rptRow);
		}
		genCache.setList(rptRowList);
		return genCache;
	}

	public static void console(String sz) {
		System.out.print(sz);
	}
}
