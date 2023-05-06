package Practice.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

	public class Excelreaderpractice {  

	    private static final String FILE_NAME = "C:\\Users\\desktop\\eclipse\\RestAssured\\excel\\data.xlsx";
	    private static final String SHEET_NAME = "Sheet1";

	    @DataProvider(name = "excelData")
	    public static Object[][] readExcelData() throws IOException {
	        List<Object[]> data = new ArrayList<>();
	        File file = new File(FILE_NAME);
	        FileInputStream inputStream = new FileInputStream(file);
	        Workbook workbook = WorkbookFactory.create(inputStream);
	        Sheet sheet = workbook.getSheet(SHEET_NAME);
	        int rowCount = sheet.getLastRowNum();
	        for (int i = 1; i <= rowCount; i++) { // start from second row
	            Row row = sheet.getRow(i);
	            Object[] rowValues = new Object[row.getLastCellNum()];
	            for (int j = 0; j < row.getLastCellNum(); j++) {
	                Cell cell = row.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
	                System.out.println("cell============"+cell);
	                
	                if (cell != null) {
	                    rowValues[j] = getCellValue(cell);
	                } else {
	                    rowValues[j] = null;
	                }
	            }
	            data.add(rowValues);
	        }
	        return data.toArray(new Object[0][0]);
	    }

	    private static Object getCellValue(Cell cell) {
	        CellType cellType = cell.getCellType();
	        switch (cellType) {
	            case BOOLEAN:
	                return cell.getBooleanCellValue();
	            case NUMERIC:
	                return cell.getNumericCellValue();
	            case STRING:
	                return cell.getStringCellValue();
	            default:
	                return null;
	        }
	    }
	

	@Test(dataProvider = "excelData", priority = 1)
	public void myTest(String Method, String URL, String payload, double Expectedstatuscode)

	{

		System.out.println("Excuted===========");

	}

}

/*
 * @DataProvider(name = "excelData") public Object[][] dataProviderMethod()
 * throws IOException { // Load the Excel file File file = new
 * File("C:\\Users\\desktop\\eclipse\\RestAssured\\excel\\data.xlsx");
 * XSSFWorkbook workbook = null; try { workbook = new XSSFWorkbook(file); }
 * catch (InvalidFormatException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
 * block e.printStackTrace(); }
 * 
 * // Get the sheet by name Sheet sheet = workbook.getSheet("Sheet1");
 * 
 * // Count the number of rows in the sheet int rowCount = sheet.getLastRowNum()
 * - sheet.getFirstRowNum() + 1;
 * 
 * // Create a 2D array to hold the data Object[][] data = new
 * Object[rowCount][1];
 * 
 * // Iterate over the rows for (int i = 0; i < rowCount; i++) { Row row =
 * sheet.getRow(i); System.out.println("row==============="+row); // Read the
 * second column and add it to the data array Cell cell = row.getCell(1);
 * System.out.println("cell============"+cell); String value =
 * cell.getStringCellValue(); data[i][0] = value; }
 * 
 * // Close the workbook workbook.close();
 * 
 * // Return the data array return data; }
 * 
 * 
 * @Test(dataProvider = "excelData",priority=2) public void myTest(String
 * Method, String URL, String payload,double Expectedstatuscode)
 * 
 * {
 * 
 * System.out.println("Excuted===========");
 * 
 * }
 * 
 * 
 * 
 * 
 * 
 * }
 */

/*
 * @Test(dataProvider = "data-provider") public void testMethod(String data) {
 * // Do something with the data System.out.println("Data: " + data); }
 * 
 */
