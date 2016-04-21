package rptGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Generator {

	public Generator() {}
	
	public Generator(int columns, int length, int lineLen) {  //nbr cols, data length, linelength
		
		this.columns = columns;
		this.length = length;
		this.lineLen = lineLen;
		
	}
	private int columns = 0;
	private int length = 0;
	private int lineLen = 0;
	private int spaceLen = 0;
	public int lineCtr = 0;
	public int pageCtr = 0;

	public HashMap<String, Integer> totalsCache = new HashMap<String, Integer>();
	public HashMap<String, Integer> grandTotals = new HashMap<String, Integer>();

	static final String SPACES = "                                                                      ";
//  static final String SPACES = "123456789*123456789*123456789*123456789*123456789*123456789*123456789*";
	static final String FINAL = "final";
	static final String PAGE = "page";
	static final String SUBTOT = "subtot";

	private String spaces;
	private String heading1 = null;
	private String heading2 = null;
	private String heading3 = null;
	private String columnHeading = null;
	private String controlBreak1 = null;
	private String controlBreak2 = null;
	private String controlBreak3 = null;
	private String prevBreak1 = null;
	private String prevBreak2 = null;
	private String prevBreak3 = null;
	private String footing1 = null; //page
	private String footing2 = null; //total
	private String footing3 = null; //report
	private boolean controlBreak = false;

	public String getHeading1() {
		return heading1;
	}

	public void setHeading1(String heading1) {
		this.heading1 = heading1;
	}

	public String getHeading2() {
		return heading2;
	}

	public void setHeading2(String heading2) {
		this.heading2 = heading2;
	}

	public String getHeading3() {
		return heading3;
	}

	public void setHeading3(String heading3) {
		this.heading3 = heading3;
	}
	public String getFooting1() {
		return footing1;
	}

	public void setFooting1(String footing1) {
		this.footing1 = footing1;
	}

	public String getFooting2() {
		return footing2;
	}

	public void setFooting2(String footing2) {
		this.footing2 = footing2;
	}

	public String getFooting3() {
		return footing3;
	}

	public void setFooting3(String footing3) {
		this.footing3 = footing3;
	}
	
	public String getControlBreak1() {
		return controlBreak1;
	}

	public void setControlBreak1(String controlBreak1) {
		this.controlBreak1 = controlBreak1;
	}

	public String getControlBreak2() {
		return controlBreak2;
	}

	public void setControlBreak2(String controlBreak2) {
		this.controlBreak2 = controlBreak2;
	}

	public String getControlBreak3() {
		return controlBreak3;
	}

	public void setControlBreak3(String controlBreak3) {
		this.controlBreak3 = controlBreak3;
	}

	public int getLineCtr() {
		return lineCtr;
	}

	public void setLineCtr(int lineCtr) {
		this.lineCtr = lineCtr;
	}

	public int getPageCtr() {
		return pageCtr;
	}

	public void setPageCtr(int pageCtr) {
		this.pageCtr = pageCtr;
	}

	public HashMap<String, Integer> getTotalsCache() {
		return totalsCache;
	}

	public void setTotalsCache(HashMap<String, Integer> totalsCache) {
		this.totalsCache = totalsCache;
	}

	public void execRpt(RowCache cache) {
		String lineSz = null;
		List<Cell> cellListN = null;		
		List<Cell> cellTotals = new ArrayList<Cell>();		
		if(heading1!=null) printHeadings();
		
		int numerator = ( lineLen - (columns * length));
		int denominator = (columns + 2);
		spaceLen = numerator / denominator;
		//System.out.println("spaceLen: "+spaceLen);
		spaces = SPACES.substring(0,spaceLen);
		 List<Row> rowList = cache.getList();
		 boolean firstTime = true;
		 for(Row rowN : rowList) {
			 if( (lineCtr % 10) == 0) {
				 printFootings(PAGE);
			 }
			 cellListN = rowN.getList();
			 if(firstTime==true) {
				 firstTime = false;
				 printColumnHeadings(cellListN);
			 }
			 lineSz = spaces;
			 for(Cell cellN : cellListN) {
				 if(prevBreak1 == null && controlBreak1 != null && controlBreak1.equals(cellN.getLabel())) {
					 prevBreak1 = cellN.getValue();
				 }
				 if(prevBreak2 == null && controlBreak2 != null && controlBreak2.equals(cellN.getLabel())) {
					 prevBreak2 = cellN.getValue();
				 }
				 if(prevBreak3 == null && controlBreak3 != null && controlBreak3.equals(cellN.getLabel())) {
					 prevBreak3 = cellN.getValue();
				 }
				 //System.out.println("prev: "+prevBreak1 + " " + prevBreak2 + " " + prevBreak3);
				 if(controlBreak1 != null &&
					controlBreak1.equals(cellN.getLabel()) &&
					prevBreak1 != null &&
					!prevBreak1.equals(cellN.getValue()) ) {
					 controlBreak= true;
					 prevBreak1 = cellN.getValue();
				 }
				 if(controlBreak2 != null &&
					controlBreak2.equals(cellN.getLabel()) &&
					prevBreak2 != null &&
					!prevBreak2.equals(cellN.getValue()) ) {
					 controlBreak = true;
					 prevBreak2 = cellN.getValue();
				 }
				 if(controlBreak3 != null &&
					controlBreak3.equals(cellN.getLabel()) &&
					prevBreak3 != null &&
					!prevBreak3.equals(cellN.getValue()) ) {
					 controlBreak = true;
					 prevBreak3 = cellN.getValue();
				 }
				 if( cellN.getDataType().equals("Integer")) {
					 cellTotals.add(cellN);
				 }
				 lineSz += cellN.getValue()+SPACES.substring(0,length-cellN.getValue().length()-1)+spaces;
			 }
			 if(controlBreak) {
				 printColumnTotals(cellListN);
			 	controlBreak = false;
			 }	
			 System.out.println(lineSz);
			 for(Cell cell: cellTotals){
				 addToTotal(cell);
			 }
			 cellTotals.removeAll(cellTotals);
			 lineCtr++;
			 lineSz = null;

		 }
		 printColumnTotals(cellListN);
		 printFinalTotals(cellListN);
	
	}

	public void addToTotal(Cell cell) {
		
		if( totalsCache.get(cell.getLabel()) == null) {
			totalsCache.put(cell.getLabel(),Integer.valueOf(cell.getValue() ));
		} else {
			int total = totalsCache.get(cell.getLabel());
			total+= Integer.valueOf( cell.getValue());
			totalsCache.put(cell.getLabel(),total);
			
		}
	}
	public void printColumnHeadings(List<Cell> cellListN) {
		
		if(columnHeading!=null) {
			System.out.println(columnHeading);
			lineCtr++;
			return;
		}
		if(cellListN!=null) {
			//System.out.print(spaces);
			columnHeading = spaces;
			for(Cell cellN : cellListN) {
				columnHeading = columnHeading + cellN.getLabel()+SPACES.substring(0,length-cellN.getLabel().length()-1)+spaces;
				//System.out.print(cellN.getLabel()+SPACES.substring(0,length-cellN.getLabel().length()-1)+spaces);
			}
			System.out.println(columnHeading);
			lineCtr++;
		}
		
		return;
	}
	public void printHeadings() {

		pageCtr++;
		int headSpace = 0;
		if(heading1!=null) {
			headSpace = (lineLen - heading1.length()) / 2;
			System.out.println( SPACES.substring(0,headSpace) + heading1);
			 lineCtr++;
		}
		if(heading2!=null) {
			headSpace = (lineLen - heading2.length()) / 2;
			System.out.println( SPACES.substring(0,headSpace) + heading2);
			 lineCtr++;
		}
		if(heading3!=null) {
			headSpace = (lineLen - heading3.length()) / 2;
			System.out.println( SPACES.substring(0,headSpace) + heading3);
			 lineCtr++;
		}
		printColumnHeadings(null);

	}
	
	public void printFootings(String cmd) {
		if(cmd=="subtot" && footing2!=null) { //subtotals
			System.out.println(footing2);
			lineCtr++;
		}
		if(cmd=="page" && footing1!=null) {
			System.out.println("Page "+pageCtr);
			//lineCtr++;
			lineCtr = 0;
			printHeadings();
		}
		if(cmd=="final" && footing3!=null) {
			//printFinalTotals();
			System.out.println(footing3);
			lineCtr++;
			System.out.println("Page "+pageCtr);
			lineCtr++;
		}
	}
	
	public void printColumnTotals(List<Cell> cellListN) {

		if(footing2 != null && cellListN!=null) {
			System.out.println("Subtotal");
			footing2 = spaces;
			for(Cell cellN : cellListN) {
				if(totalsCache.get(cellN.getLabel())==null) {
					footing2 = footing2 + SPACES.substring(0,length) + spaces;
				} else {
					int total = totalsCache.get(cellN.getLabel());
					String totalString = String.format("%,d", total);
					footing2 = footing2 + totalString + spaces;
					if(grandTotals.get(cellN.getLabel())==null) {
						grandTotals.put(cellN.getLabel(), total);
						totalsCache.put(cellN.getLabel(), 0);
					} else {
						int grand = grandTotals.get(cellN.getLabel());
						grandTotals.put(cellN.getLabel(), (grand + total) );
						totalsCache.put(cellN.getLabel(), 0);
					}
				//System.out.print(cellN.getLabel()+SPACES.substring(0,length-cellN.getLabel().length()-1)+spaces);
				}
			}	
			System.out.println("*"+footing2);
			lineCtr++;
		}
		lineCtr++;
		return;
	}

	public void printFinalTotals(List<Cell> cellListN) {

		if(footing3 != null && cellListN!=null) {
			System.out.println("GRAND TOTAL");
			footing3 = spaces;
			for(Cell cellN : cellListN) {
				if(totalsCache.get(cellN.getLabel())==null) {
					footing3 = footing3 + SPACES.substring(0,length) + spaces;
				} else {
					int total = grandTotals.get(cellN.getLabel());
//					grandTotals.put(cellN.getLabel(), total);
//					totalsCache.put(cellN.getLabel(), 0);
					String totalString = String.format("%,d", total);
					footing3 = footing3 + totalString + spaces;
				//System.out.print(cellN.getLabel()+SPACES.substring(0,length-cellN.getLabel().length()-1)+spaces);
				}
			}	
			System.out.println("*"+footing3);
			lineCtr++;
		}
		lineCtr++;
		return;
	}


}