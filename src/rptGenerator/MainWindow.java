package rptGenerator;

import java.awt.EventQueue;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sidacoja.utils.Cell;
import com.sidacoja.utils.Row;
import com.sidacoja.utils.RowCache;
import com.sidacoja.utils.Sidacoja;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;

public class MainWindow implements ActionListener {

	private JFrame frmSiregeja;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtImportantData;
	private JTextField txtTrue_2;
	private JTextField txtTrue;
	private JTextField txtDistrict;
	private JTextField txtJuly;
	private JTextField txtTrue_1;
	
	JButton btnGo = new JButton("GO");			
	JButton btnCancel = new JButton("Cancel");
	JButton btnSelectFile = new JButton("Select File");
	JButton btnLoad = new JButton("Load Columns");
	JComboBox<String> comboBox = new JComboBox<String>();
	JComboBox<String> comboBox_1 = new JComboBox<String>();
	JComboBox<String> comboBox_2 = new JComboBox<String>();
	Common common = new Common();
	RowCache sCache = new RowCache();
	private JTextField textField_4;
	
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
		
		JLabel lblSiregajaA = new JLabel("Siregeja - A Simple Report Generator");
		lblSiregajaA.setBounds(10, 11, 251, 14);
		frmSiregeja.getContentPane().add(lblSiregajaA);
		
		JLabel lblInput = new JLabel("Input");
		lblInput.setBounds(10, 64, 46, 14);
		frmSiregeja.getContentPane().add(lblInput);
		
		JLabel lblHeadings = new JLabel("Headings");
		lblHeadings.setBounds(10, 123, 83, 14);
		frmSiregeja.getContentPane().add(lblHeadings);
		
		JLabel lblControlBreaks = new JLabel("Control Breaks");
		lblControlBreaks.setBounds(10, 179, 101, 14);
		frmSiregeja.getContentPane().add(lblControlBreaks);
		
		JLabel lblFootings = new JLabel("Footings");
		lblFootings.setBounds(10, 242, 83, 14);
		frmSiregeja.getContentPane().add(lblFootings);
		
		textField = new JTextField();
		textField.setBounds(65, 61, 294, 20);
		frmSiregeja.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText("C:\\Users\\Chuck\\Documents\\workspace-D08052005\\SQLReport\\resources\\spreadsheet.xls");
		
		//JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 204, 101, 20);
		comboBox.addItem(" ");
		frmSiregeja.getContentPane().add(comboBox);
		
		//JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(128, 204, 111, 20);
		comboBox_1.addItem(" ");
		frmSiregeja.getContentPane().add(comboBox_1);
		
		//JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(249, 204, 110, 20);
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
		
		txtImportantData = new JTextField();
		txtImportantData.setBounds(10, 148, 101, 20);
		frmSiregeja.getContentPane().add(txtImportantData);
		txtImportantData.setColumns(10);
		txtImportantData.setText("Important Data");
		
		txtTrue_2 = new JTextField();
		txtTrue_2.setBounds(10, 267, 101, 20);
		frmSiregeja.getContentPane().add(txtTrue_2);
		txtTrue_2.setColumns(10);
		txtTrue_2.setText("True");
		
		txtTrue = new JTextField();
		txtTrue.setText("True");
		txtTrue.setBounds(128, 267, 111, 20);
		frmSiregeja.getContentPane().add(txtTrue);
		txtTrue.setColumns(10);
		
		txtDistrict = new JTextField();
		txtDistrict.setColumns(10);
		txtDistrict.setBounds(128, 148, 111, 20);
		txtDistrict.setText("District 9");
		frmSiregeja.getContentPane().add(txtDistrict);
		
		txtJuly = new JTextField();
		txtJuly.setColumns(10);
		txtJuly.setBounds(249, 148, 110, 20);
		txtJuly.setText("July 4, 1776");
		frmSiregeja.getContentPane().add(txtJuly);
		
		txtTrue_1 = new JTextField();
		txtTrue_1.setText("True");
		txtTrue_1.setColumns(10);
		txtTrue_1.setBounds(249, 267, 110, 20);
		frmSiregeja.getContentPane().add(txtTrue_1);
		
		//JButton btnGo = new JButton("GO");
		btnGo.addActionListener(this);
		btnGo.setBounds(384, 203, 89, 23);
		frmSiregeja.getContentPane().add(btnGo);
		
		//JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(384, 266, 89, 23);
		frmSiregeja.getContentPane().add(btnCancel);
		
		//JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(this);
		btnLoad.setBounds(356, 89, 117, 23);
		frmSiregeja.getContentPane().add(btnLoad);
		
		//JButton btnSelectFile = new JButton("Select File");
		btnSelectFile.setBounds(369, 60, 104, 23);
		btnSelectFile.addActionListener(this);
		frmSiregeja.getContentPane().add(btnSelectFile);
		
		JLabel lblTableName = new JLabel("Table Name");
		lblTableName.setBounds(10, 89, 77, 23);
		frmSiregeja.getContentPane().add(lblTableName);
		
		textField_4 = new JTextField();
		textField_4.setBounds(97, 92, 86, 20);
		frmSiregeja.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lbljdbcOnly = new JLabel("(JDBC only)");
		lbljdbcOnly.setBounds(193, 92, 68, 20);
		frmSiregeja.getContentPane().add(lbljdbcOnly);
	}
	   
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getSource());
		
		if(e.getSource()==btnCancel) {
            System.exit(0);		}
		if (e.getSource() == btnSelectFile) {
            JFileChooser openFile = new JFileChooser(textField.getText());
            openFile.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter1 = new FileNameExtensionFilter("spreadsheets (xls)", "xls");
            openFile.addChoosableFileFilter(filter1);
            FileNameExtensionFilter filter2 = new FileNameExtensionFilter("comma-delimited files (csv)", "csv");
            openFile.addChoosableFileFilter(filter2);
            FileNameExtensionFilter filter3 = new FileNameExtensionFilter("xml documents (xml)", "xml");
            openFile.addChoosableFileFilter(filter3);
            FileNameExtensionFilter filter4 = new FileNameExtensionFilter("json documents (json)", "json");
            openFile.addChoosableFileFilter(filter4);
            openFile.showOpenDialog(frmSiregeja);
            File inFile = openFile.getSelectedFile();
            if(inFile!=null) {
            	//System.out.println(inFile.getAbsolutePath());
            	textField.setText(inFile.getAbsolutePath());
            	String type = textField.getText().substring(textField.getText().length()-4, textField.getText().length());
            	int typeLen = 0;
            	if(".".equals(type.substring(0, 1)))
            		typeLen = 3;
            	else
            		typeLen = 4;
            	comboBox.setSelectedItem(textField.getText().substring(textField.getText().length()-typeLen, textField.getText().length()).toUpperCase());
            }
		} 

		if(e.getSource()==btnGo) {
			boolean valid = validateInput();
			if(valid && sCache.countSelected()>0){
				report(sCache);
			} else {
				if(valid) {
					sCache = sidacoja(textField.getText());
					console("cache count: " +sCache.countSelected()+"\n");
					loadDropDowns(sCache);
					report(sCache);
				}
			}
		}
		if(e.getSource()==btnLoad) {
			if( common.isNullOrEmpty(textField.getText())) {
				showMessageDialog(frmSiregeja, "Input is required!  Please enter input.","Input Error",JOptionPane.ERROR_MESSAGE);
			} else {	
				sCache = sidacoja(textField.getText());
				console("cache count: " +sCache.countSelected()+"\n");
				loadDropDowns(sCache);
			}
		}
	}
	
	public void report(RowCache cache) {
		
		Generator generator = null;
		//console("report: "+cache.countSelected());
		if( isInteger(textField_1.getText()) &&
			isInteger(textField_2.getText()) &&
			isInteger(textField_3.getText()) ) {
			generator = new Generator(
				Integer.valueOf(textField_1.getText() ), 
				Integer.valueOf(textField_2.getText() ), 
				Integer.valueOf(textField_3.getText() ) );
		}
		if(!common.isNullOrEmpty(txtImportantData.getText()))
			generator.setHeading1(txtImportantData.getText());
		if(!common.isNullOrEmpty(txtDistrict.getText()))
			generator.setHeading2(txtDistrict.getText());
		if(!common.isNullOrEmpty(txtJuly.getText()))
			generator.setHeading3(txtJuly.getText());
		
		//console("control: "+(String) comboBox.getSelectedItem() );
		if(!common.isNullOrEmpty((String) comboBox.getSelectedItem())) 	
			generator.setControlBreak1((String) comboBox.getSelectedItem());
		if(!common.isNullOrEmpty((String) comboBox_1.getSelectedItem())) 	
			generator.setControlBreak2((String) comboBox_1.getSelectedItem());
		if(!common.isNullOrEmpty((String) comboBox_2.getSelectedItem())) 	
			generator.setControlBreak3((String) comboBox_2.getSelectedItem());
		
		//console("footing: "+textField_5.getText());
		if(!common.isNullOrEmpty(txtTrue_2.getText()))
			generator.setFooting1(txtTrue_2.getText());
		if(!common.isNullOrEmpty(txtTrue.getText()))
			generator.setFooting2(txtTrue.getText());
		if(!common.isNullOrEmpty(txtTrue_1.getText()))
			generator.setFooting3(txtTrue_1.getText());
		
		generator.execRpt(cache);

	}
	public RowCache sidacoja(String sz) {
		//final String DB_URL = "jdbc:hsqldb:hsql://localhost:9001/mdb";
		
		Sidacoja sdcj = new Sidacoja();
		sdcj.input(sz);
		if(!common.isNullOrEmpty(textField_4.getText())) {
			sdcj.setTable(textField_4.getText());
			sdcj.inputType("jdbc");
		} else {
			sdcj.inputType(sz.split("[.]")[1]);
		}

		sdcj.setCacheOnly(true);
		sdcj.output("N/A");
		sdcj.outputType("jdbc");

		RowCache cache = new RowCache();
		try {
			cache = sdcj.fire();
		} catch (Exception e) {
			console(e.getMessage());
			e.printStackTrace();
		}
		return cache;
	}
	
	public void loadDropDowns(RowCache cache){
		List<Row> rows = cache.getList();
		//for(Row row : rows) {
			Row row = rows.get(0);
			List<Cell> cells = row.getList();
			for(Cell cell: cells) {
				comboBox.addItem(cell.getLabel());
				comboBox_1.addItem(cell.getLabel());
				comboBox_2.addItem(cell.getLabel());
			}
		//}
	};

	public boolean isInteger( String input ) {
	   try {
	      Integer.parseInt( input );
	      return true;
	   } catch( Exception e) {
	      return false;
	   }
	}
	
	public boolean validateInput() {
		boolean valid = true;
		if( !isInteger(textField_1.getText()) ||
			!isInteger(textField_2.getText()) ||
			!isInteger(textField_3.getText()) ) {
			showMessageDialog(frmSiregeja, "Number of columns, column width, and line width are required.","Input Error",JOptionPane.ERROR_MESSAGE);
			valid = false;
		}
		int i = Integer.parseInt(textField_1.getText());
		int j = Integer.parseInt(textField_2.getText());
		int k = Integer.parseInt(textField_3.getText());
		if( !(i*j < k) ) {
			showMessageDialog(frmSiregeja, "Number of columns time column width must be less than line width.","Input Error",JOptionPane.ERROR_MESSAGE);			
			valid = false;
		}
		if( common.isNullOrEmpty(textField.getText())) {
			showMessageDialog(frmSiregeja, "Input is required!  Please enter input.","Input Error",JOptionPane.ERROR_MESSAGE);
			valid = false;
		}
		return valid;
	}
	public static void console(String sz) {
		System.out.print(sz);
	}
}
