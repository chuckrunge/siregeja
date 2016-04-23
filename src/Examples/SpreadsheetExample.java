package Examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import rptGenerator.Generator;
import rptGenerator.RowCache;

	public class SpreadsheetExample {
// implements SourceData {

		public RowCache processInput(String input) {
			
			console(input);
			int i=0, j=0, k=0, m=0;
			boolean firstRow = true;
		    RowCache cache = new RowCache();
			
			try {
			     
			    FileInputStream file = new FileInputStream(new File(input));
			     
			    HSSFWorkbook workbook = new HSSFWorkbook(file); //create workbook instance
			    HSSFSheet sheet = workbook.getSheetAt(0); //select first worksheet

		    	List<rptGenerator.Row> rows = new ArrayList<rptGenerator.Row>();
		    	List<rptGenerator.Cell> cells = new ArrayList<rptGenerator.Cell>();
				List<String> labels= new ArrayList<String>();
	            rptGenerator.Row sRow = new rptGenerator.Row();
				rptGenerator.Cell sCell = new rptGenerator.Cell();

			    Iterator<Row> rowIterator = sheet.iterator(); //Iterate through rows
			    while(rowIterator.hasNext()) {
			    	
			        Row row = rowIterator.next();
			        cells = new ArrayList<rptGenerator.Cell>();
			        if(k % 1000 == 0) {
			        	console("row: "+k); //" cell "+i
			        }
			        k++;
			        i=0;m=0;
			        //Iterator<Cell> cellIterator = row.cellIterator(); //iterate through columns 
			        for(int cn=0; cn<row.getLastCellNum(); cn++) {
		            //while(cellIterator.hasNext()) {
			             
			            Cell cell  = row.getCell(cn, Row.CREATE_NULL_AS_BLANK); //cellIterator.next();
			            if(firstRow == false) {
			            	sCell.setNumber(i);
			            	sCell.setLabel(labels.get(i));
			            	i = i + 1;
			            }
			            //console("index: "+cell.getColumnIndex()+" prev: "+m);
			            if( !(cell.getColumnIndex() == 1 + m)) {
			            	if(cell.getColumnIndex()>0) {console("wacky cell : "+cell.getColumnIndex());}
			            }
			            m = cell.getColumnIndex();
			            switch(cell.getCellType()) {
			                case Cell.CELL_TYPE_BOOLEAN:
			                    sCell.setValue(Boolean.toString(cell.getBooleanCellValue()));
			                    sCell.setDataType("Boolean");
			                    break;
			                case Cell.CELL_TYPE_NUMERIC:
			                    double dv = cell.getNumericCellValue();
			                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
			                        Date date = HSSFDateUtil.getJavaDate(dv);
			                        String dateFmt = cell.getCellStyle().getDataFormatString();
			                        
			                        /* strValue = new SimpleDateFormat(dateFmt).format(date); - won't work as 
			                        Java fmt differs from Excel fmt. If Excel date format is mm/dd/yyyy, Java 
			                        will always be 00 for date since "m" is minutes of the hour.*/
			                        String strValue = new CellDateFormatter(dateFmt).format(date); 
			                        
				                    sCell.setValue(strValue);
			                    	sCell.setDataType("Date");
			                    } else {
				                    sCell.setValue(Double.toString(cell.getNumericCellValue()));
			                    	sCell.setDataType("Double");
			                    }
			                    break;
			                case Cell.CELL_TYPE_STRING:
			                    if(firstRow) {
			                    	labels.add(cell.getStringCellValue());
			                    } else {
			                    	sCell.setValue(cell.getStringCellValue());
			                    	sCell.setDataType("String");
			                    }
			                    break;
			                case Cell.CELL_TYPE_BLANK:
	                    		sCell.setValue(cell.getStringCellValue());
	                    		sCell.setDataType("String");		                	
			                	break;
			            } //end switch
			            if(firstRow == false) {
			            	cells.add(sCell);
			            	sCell = new rptGenerator.Cell();
			            }
			        } //end cell loop
		        	i=0;
		            if(firstRow == false) {
		            	sRow = new rptGenerator.Row();
		            	sRow.setNumber(j);
		            	sRow.setList(cells);
		            	rows.add(sRow);
		            	cells = new ArrayList<rptGenerator.Cell>();
		            	j = j + 1;
		            }
		            else {firstRow = false;}
		            
			    } //end row loop
			    
			    workbook.close();
			    file.close();
			    cache.setList(rows);
			    
			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
			return cache;
			
		}
		
		public void displayCells(List<rptGenerator.Cell> cells) {
			for(rptGenerator.Cell cell:cells) {
				console(cell.toString());
			}
		}
		
	    public void console(String sz) {
	    	System.out.println(sz);
	    }
	    
		public static void main(String[] args) {
			SpreadsheetExample xSheet = new SpreadsheetExample(); 
	    	RowCache cache = xSheet.processInput(".\\resources\\spreadsheet.XLS");
	    	List<rptGenerator.Row> rows = cache.getList();
	    	/*for(rptGenerator.Row row: rows) {
	    		List<rptGenerator.Cell> cells = row.getList();
	    		for(rptGenerator.Cell cell: cells) {
	    			System.out.println(cell.getLabel()+": "+cell.getDataType());
	    		}
	    	}
	    	//if(true) return;
		    */
			try {

				Generator generator = new Generator(6, 18,132); // nbr cols, data
																	// length,
																	// linelength
				generator.setHeading1("Spreadsheet Example");
				Calendar cal = Calendar.getInstance();
				String dfString = DateFormat.getDateInstance().format(cal.getTime());
				generator.setHeading2(dfString);
				generator.setHeading3(" ");
				generator.setControlBreak1("CURRENCY1");
				generator.setControlBreak2(null);
				generator.setControlBreak3(null);
				generator.setFooting1("*");
				generator.setFooting2("**");
				generator.setFooting3("End of Report");
				generator.execRpt(cache);

			} catch (Exception rex) {
				System.out.println(rex.getMessage());
				rex.printStackTrace();
			}
	    }

	} //end class
