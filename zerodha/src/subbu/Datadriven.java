package subbu;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Datadriven {
	
	public ArrayList<String> getData() throws IOException {
		ArrayList<String> a=new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream("./data/Futures1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		int sheets = workbook.getNumberOfSheets(); //Gets the number of sheets in a workbook
		for(int i=0; i<sheets; i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase("futures")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row firstrow = rows.next();
				Iterator<Cell> ce= firstrow.cellIterator();
				int k=0;
				int column=0;
				while(ce.hasNext()){
					Cell value = ce.next();
					if(value.getStringCellValue().equalsIgnoreCase("Futures Symbol"))
					{
						column=k;
					}
					k++;
				}
				while(rows.hasNext()) {
					Row r=rows.next();
					//if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename)) {
						//Iterator<Cell> cv = r.cellIterator();
						//while(cv.hasNext()) {
						//	Cell c = cv.next();
						//System.out.println(r.getCell(column).getCellTypeEnum());
							//if(r.getCell(column).getCellTypeEnum()==CellType.STRING)
							//{
								a.add(r.getCell(column).getStringCellValue());
							//}
							//else
							//{
							//	a.add(NumberToTextConverter.toText(r.getCell(column).getNumericCellValue()));
							//}
						//}
					//}
				}
			}	
		}
		//workbook.close();
		//fis.close();
		return a;
	}
}
