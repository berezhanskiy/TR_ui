package help;


import org.apache.poi.ss.usermodel.Sheet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.pagefactory.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExelDataConfig {
	
	private String exPath =	"D:\\work\\eclipse\\eclipse-workspace(EE)\\TRlocators.xlsx";
	private static XSSFWorkbook wb;
	private static XSSFSheet sheet;

	//constructor for create exel data object
	public ExelDataConfig(String exelPath) throws IOException {
		File file = new File(exelPath);
		FileInputStream fileInput = null;
		try{
			fileInput = new FileInputStream(file);
			}
		catch(FileNotFoundException e){
			e.printStackTrace();
			}
		wb = new XSSFWorkbook(fileInput);
		sheet = wb.getSheetAt(0);
	}
	
	// method for print all data into console from exel file
	public void printExel(){
		FormulaEvaluator forlulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
			for(Row row : sheet){
				for(Cell cell : row){
					switch(forlulaEvaluator.evaluateInCell(cell).getCellType())
					{
					//if cell is a numeric format
					case Cell.CELL_TYPE_NUMERIC:
					{
						System.out.println(cell.getNumericCellValue()+"\t\t");
						break;
					}
					case Cell.CELL_TYPE_STRING:
					{
						System.out.println(cell.getStringCellValue()+"\t\t");
						break;
					}
				}
			}
			System.out.println();
		}
	}

	//method for read and save data from sheet
	public static List<String> getMainPagePreviewPathes()throws FileNotFoundException, IOException{
		Sheet sheet1 = wb.getSheetAt(2);
		List <String> results = new ArrayList<String>();
			for(int i = 0; i <= sheet1.getLastRowNum(); i++){
				results.add(sheet1.getRow(i).getCell(1).getStringCellValue());
			}
		return results;
	}

	//method for get specific data value from data_box	
	public static List<String> getData(int sheetNumber, List <String> results)throws FileNotFoundException, IOException{
		Sheet sheet1 = wb.getSheetAt(sheetNumber);
		results = new ArrayList<String>();
		for(int i = 0; i <= sheet1.getLastRowNum(); i++){
			results.add(sheet1.getRow(i).getCell(1).getStringCellValue());
		}
		return results;
	}
	
	//method for extract one value from sheet, witch located on row*cell position
	public static String getOneValue(int sheetNumber, int row, int cell){
		Sheet sheet1 = wb.getSheetAt(sheetNumber);
		String result = sheet1.getRow(row).getCell(cell).getStringCellValue();
		return result;
	}

}
