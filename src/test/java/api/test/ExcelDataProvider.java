package api.test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {
	private static final String EXCEL_FILE_PATH = "C:\\Users\\desktop\\eclipse\\RestAssured\\testdata\\data.xlsx";
	private static final String SHEET_NAME = "Sheet1";

	@DataProvider(name = "excelData")
	public Iterator<Object[]> readExcelData() throws IOException {
		FileInputStream fileInputStream = new FileInputStream(EXCEL_FILE_PATH);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet(SHEET_NAME);
		List<Object[]> data = new ArrayList<Object[]>();

		for (Row row : sheet) {
			List<Object> rowData = new ArrayList<Object>();
			for (Cell cell : row) {
				switch (cell.getCellType()) {
				case STRING:
					rowData.add(cell.getStringCellValue());
					break;
				case NUMERIC:
					rowData.add(cell.getNumericCellValue());
					break;
				case BOOLEAN:
					rowData.add(cell.getBooleanCellValue());
					break;
				case _NONE:
					rowData.add(cell.getBooleanCellValue());
					break;
				default:
					rowData.add(null);
					break;
				}
			}
			data.add(rowData.toArray());
		}

		workbook.close();
		fileInputStream.close();

		return data.iterator();
	}

					 
				}
				
				
			