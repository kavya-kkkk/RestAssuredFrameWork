package api.utilies;

import java.io.IOException;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataPro {

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() throws IOException {
        // Load the Excel file
        File file = new File("data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        // Get the sheet by name
        Sheet sheet = workbook.getSheet("Sheet1");

        // Count the number of rows in the sheet
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;

        // Create a 2D array to hold the data
        Object[][] data = new Object[rowCount][1];

        // Iterate over the rows
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);

            // Read the second column and add it to the data array
            Cell cell = row.getCell(1);
            String value = cell.getStringCellValue();
            data[i][0] = value;
        }

        // Close the workbook
        workbook.close();

        // Return the data array
        return data;
    }

   /*@Test(dataProvider = "data-provider")
    public void testMethod(String data) {
        // Do something with the data
        System.out.println("Data: " + data);
    }
}*/

	
	
	@DataProvider (name="UserNames") public String[] getUserNames() throws IOException {
		String path=System.getProperty("user.dir")+"//Userdata.xlsx";
	ExcelUtilies xl=new ExcelUtilies(path);
		int rownum=xl.getRowCount ("Sheet1");
		String apidata[]=new String[rownum];
		for(int i=1;i<=rownum; i++) {
		apidata[i-1]= xl.getCellData("Sheet1", i, 1);
		}
		
		return apidata;
		}
		}

