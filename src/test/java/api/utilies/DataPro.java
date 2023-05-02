package api.utilies;

import java.io.IOException;
import org.testng.annotations.*;

public class DataPro {

	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {
		String path = System.getProperty("user.dir") + "//testdata//exceldata.xlsx";
		System.out.println(path);
		ExcelUtilies xl = new ExcelUtilies(path);

		int rownum = xl.getRowCount("Sheet1");
		System.out.println(rownum);
		int colcount = xl.getcellCount("Sheet1", 1);
		System.out.println(colcount);
		String apidata[][] = new String[rownum][colcount];

		for (int i=1; i<= rownum; i++) 
		
		{
			//System.out.println(i);
			for (int j = 0;j<colcount;j++)
				
			{
				//System.out.println(j);
				apidata[i-1][j]=xl.getCellData("Sheet1", i, j);
				
			}
		}
		return apidata;
	}
	
	
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

