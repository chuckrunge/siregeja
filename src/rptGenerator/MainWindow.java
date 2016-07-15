package rptGenerator;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sidacoja.utils.Cell;
import com.sidacoja.utils.Row;
import com.sidacoja.utils.RowCache;
import com.sidacoja.utils.Sidacoja;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class MainWindow implements ActionListener {

	private JFrame frmSiregeja;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField txtTrue;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField txtTrue_1;
	
	JButton btnGo = new JButton("GO");			
	JButton btnCancel = new JButton("Cancel");
	JButton btnLoad = new JButton("Load");
	JComboBox<String> comboBox = new JComboBox<String>();
	JComboBox<String> comboBox_1 = new JComboBox<String>();
	JComboBox<String> comboBox_2 = new JComboBox<String>();
	Common common = new Common();
	RowCache sCache = new RowCache();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmSiregeja.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSiregeja = new JFrame();
		frmSiregeja.setTitle("Siregeja");
		frmSiregeja.setBounds(100, 100, 499, 339);
		frmSiregeja.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSiregeja.getContentPane().setLayout(null);
		
		JLabel lblSiregajaA = new JLabel("Siregaja - A Simple Report Generator");
		lblSiregajaA.setBounds(10, 11, 251, 14);
		frmSiregeja.getContentPane().add(lblSiregajaA);
		
		JLabel lblInput = new JLabel("Input");
		lblInput.setBounds(10, 64, 46, 14);
		frmSiregeja.getContentPane().add(lblInput);
		
		JLabel lblHeadings = new JLabel("Headings");
		lblHeadings.setBounds(10, 89, 83, 14);
		frmSiregeja.getContentPane().add(lblHeadings);
		
		JLabel lblControlBreaks = new JLabel("Control Breaks");
		lblControlBreaks.setBounds(10, 143, 101, 14);
		frmSiregeja.getContentPane().add(lblControlBreaks);
		
		JLabel lblFootings = new JLabel("Footings");
		lblFootings.setBounds(10, 189, 83, 14);
		frmSiregeja.getContentPane().add(lblFootings);
		
		textField = new JTextField();
		textField.setBounds(65, 61, 294, 20);
		frmSiregeja.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText("C:\\Users\\Chuck\\Documents\\workspace-D08052005\\SQLReport\\resources\\spreadsheet.xls");
		
		//JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 163, 101, 20);
		comboBox.addItem(" ");
		frmSiregeja.getContentPane().add(comboBox);
		
		//JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(128, 163, 111, 20);
		comboBox_1.addItem(" ");
		frmSiregeja.getContentPane().add(comboBox_1);
		
		//JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(249, 163, 110, 20);
		comboBox_2.addItem(" ");
		frmSiregeja.getContentPane().add(comboBox_2);
		
		JLabel lblNewLabel = new JLabel("Nbr Cols");
		lblNewLabel.setBounds(10, 36, 59, 14);
		frmSiregeja.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(65, 33, 46, 20);
		frmSiregeja.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText("6");
		
		JLabel lblNewLabel_1 = new JLabel("Col Width");
		lblNewLabel_1.setBounds(128, 36, 61, 14);
		frmSiregeja.getContentPane().add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(198, 33, 46, 20);
		frmSiregeja.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText("20");
		
		JLabel lblNewLabel_2 = new JLabel("Line Length");
		lblNewLabel_2.setBounds(254, 36, 67, 14);
		frmSiregeja.getContentPane().add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(331, 33, 46, 20);
		frmSiregeja.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText("132");
		
		textField_4 = new JTextField();
		textField_4.setBounds(10, 114, 101, 20);
		frmSiregeja.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText("===Heading1===");
		
		textField_5 = new JTextField();
		textField_5.setBounds(10, 214, 101, 20);
		frmSiregeja.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		textField_5.setText("True1");
		
		txtTrue = new JTextField();
		txtTrue.setText("True2");
		txtTrue.setBounds(128, 214, 111, 20);
		frmSiregeja.getContentPane().add(txtTrue);
		txtTrue.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(128, 114, 111, 20);
		textField_7.setText("==Heading2==");
		frmSiregeja.getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(249, 114, 110, 20);
		textField_8.setText("=Heading3=");
		frmSiregeja.getContentPane().add(textField_8);
		
		txtTrue_1 = new JTextField();
		txtTrue_1.setText("True3");
		txtTrue_1.setColumns(10);
		txtTrue_1.setBounds(249, 214, 110, 20);
		frmSiregeja.getContentPane().add(txtTrue_1);
		
		//JButton btnGo = new JButton("GO");
		btnGo.addActionListener(this);
		btnGo.setBounds(384, 162, 89, 23);
		frmSiregeja.getContentPane().add(btnGo);
		
		//JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(384, 213, 89, 23);
		frmSiregeja.getContentPane().add(btnCancel);
		
		//JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(this);
		btnLoad.setBounds(384, 60, 89, 23);
		frmSiregeja.getContentPane().add(btnLoad);
	}
	   
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getSource());
		
		if(e.getSource()==btnCancel) {
            System.exit(0);		}
		if(e.getSource()==btnGo) {
			//showMessageDialog(null, "Go!");
			//console("cache at go: " +sCache.countSelected());
			if(sCache.countSelected()>0){
				report(sCache);
			} else {
				sCache = sidacoja(textField.getText());
				console("cache count: " +sCache.countSelected()+"\n");
				loadDropDowns(sCache);
				//console("after load: " +sCache.countSelected());
				report(sCache);				
			}
		}
		if(e.getSource()==btnLoad) {
			//showMessageDialog(null, "Load!");
			sCache = sidacoja(textField.getText());
			console("cache count: " +sCache.countSelected()+"\n");
			loadDropDowns(sCache);
			//console("after load: " +sCache.countSelected());
		}
		/*     JOptionPane.showMessageDialog(frame,
        "Problem writing to backup directory: '" + backupDir + "'.",
        "Backup problem",
        JOptionPane.INFORMATION_MESSAGE);
    */

	}
	
	public void report(RowCache cache) {
		//console("report: "+cache.countSelected());
		Generator generator = new Generator(
				Integer.valueOf(textField_1.getText() ), 
				Integer.valueOf(textField_2.getText() ), 
				Integer.valueOf(textField_3.getText() ) );
		
		if(!common.isNullOrEmpty(textField_4.getText()))
			generator.setHeading1(textField_4.getText());
		if(!common.isNullOrEmpty(textField_7.getText()))
			generator.setHeading2(textField_7.getText());
		if(!common.isNullOrEmpty(textField_8.getText()))
			generator.setHeading3(textField_8.getText());
		
		//console("control: "+(String) comboBox.getSelectedItem() );
		if(!common.isNullOrEmpty((String) comboBox.getSelectedItem())) 	
			generator.setControlBreak1((String) comboBox.getSelectedItem());
		if(!common.isNullOrEmpty((String) comboBox_1.getSelectedItem())) 	
			generator.setControlBreak2((String) comboBox_1.getSelectedItem());
		if(!common.isNullOrEmpty((String) comboBox_2.getSelectedItem())) 	
			generator.setControlBreak3((String) comboBox_2.getSelectedItem());
		
		//console("footing: "+textField_5.getText());
		if(!common.isNullOrEmpty(textField_5.getText()))
			generator.setFooting1(textField_5.getText());
		if(!common.isNullOrEmpty(txtTrue.getText()))
			generator.setFooting2(txtTrue.getText());
		if(!common.isNullOrEmpty(txtTrue_1.getText()))
			generator.setFooting3(txtTrue_1.getText());
		
		generator.execRpt(cache);

	}
	public RowCache sidacoja(String sz) {
		//final String DB_URL = "jdbc:hsqldb:hsql://localhost:9001/mdb";
		//console(sz);
		String[] pieces = sz.split(".");
		//for(String s : pieces) {
			//console("piece: "+s);
		//}
		Sidacoja sdcj = new Sidacoja();

		sdcj.input(sz);
		sdcj.inputType(sz.split("[.]")[1]);
		//sdcj.setTable("Employees");

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
		return cache;  //copyCache(cache);
	}

	public static rptGenerator.RowCacheX copyCache(RowCache cache) {
		rptGenerator.RowCacheX genCache = new rptGenerator.RowCacheX();
		List<Row> rowList = cache.getList();
		List<rptGenerator.RowX> rptRowList = new ArrayList<rptGenerator.RowX>();
		for (Row row : rowList) {
			rptGenerator.RowX rptRow = new rptGenerator.RowX();
			List<Cell> cellList = row.getList();
			List<rptGenerator.CellX> rptCellList = new ArrayList<rptGenerator.CellX>();
			for (Cell cell : cellList) {
				rptGenerator.CellX rptCell = new rptGenerator.CellX();
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
	
	public void loadDropDowns(RowCache cache){
		List<Row> rows = cache.getList();
		for(Row row : rows) {
			List<Cell> cells = row.getList();
			for(Cell cell: cells) {
				comboBox.addItem(cell.getLabel());
				comboBox_1.addItem(cell.getLabel());
				comboBox_2.addItem(cell.getLabel());
			}
		}
	};

	public static void console(String sz) {
		System.out.print(sz);
	}
}
