package com.sigma.demo.FrameWork.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.sigma.demo.Constants.DataConstants;
import com.sigma.demo.FrameWork.Helpers.PropertyValue;


public class ReadXLFile {

	static Logger log = Logger.getLogger(ReadXLFile.class);
	
	
	/**
	 * @param args
	 * @return 
	 */
	public ArrayList<PropertyValue> readPropertiesFromXL(String fileName, int sheetNo, int rowNo)
	{
		try {
			FileInputStream input = new FileInputStream(fileName);
			POIFSFileSystem myData = new POIFSFileSystem(input);
			HSSFWorkbook workBook = new HSSFWorkbook(myData);
			
			
				HSSFSheet sheet = workBook.getSheetAt(sheetNo);
				System.out.println("Sheet No : "+sheetNo);
				HSSFRow rowProperty;
				HSSFRow rowValue;
			//	Iterator<?> iteratorRow = sheet.rowIterator();
				ArrayList<PropertyValue> propertyKeyPair = new ArrayList<PropertyValue>();
				
                if (((rowProperty = sheet.getRow(1)) != null) && ((rowValue = sheet.getRow(rowNo)) != null))
                {
                	Iterator iteratorProperty = rowProperty.cellIterator();
                	Iterator iteratorValue = rowValue.cellIterator();
                	rowProperty.getLastCellNum();
                
                 while(iteratorProperty.hasNext())
                 {
                	 PropertyValue propertyPair = new PropertyValue();
                	 String propertyName = ((HSSFCell) iteratorProperty.next()).getStringCellValue();
                	 propertyPair.setPropertyName(propertyName);
                	 if(iteratorValue.hasNext())
                	 {   
                		 String propertyValue = getCellIndexValue((HSSFCell) iteratorValue.next());
                		// String propertyValue = ((HSSFCell) iteratorValue.next()).getStringCellValue();
                		 propertyPair.setPropertyValue(propertyValue);
                	 }
                	 propertyKeyPair.add(propertyPair);
                }
                }
             input.close();
             return propertyKeyPair;
	    }
		catch(FileNotFoundException fe)
		{
			log.error(fe.getMessage());
		}
		catch(IOException io)
		{
			log.error(io.getMessage());
		}
		return null;
	}
	
	public HashMap<String, Integer> createHashMap(String fileName,int sheetNo)
	{
		try {
			FileInputStream input = new FileInputStream(fileName);
			POIFSFileSystem myData = new POIFSFileSystem(input);
			HSSFWorkbook workBook = new HSSFWorkbook(myData);
			
			    HashMap<String,Integer> mapTestRow = new HashMap<String, Integer>();
				HSSFSheet sheet = workBook.getSheetAt(sheetNo);
				HSSFRow propertyRowValue;
				HSSFRow rowProperty = sheet.getRow(1);
				Iterator rowIterator = rowProperty.cellIterator();
				String testID;
				if(rowProperty != null)
				{
					int ColumnNo = 0;
			      for(;rowIterator.hasNext(); ColumnNo++)
			      {
			    	  HSSFCell cell = (HSSFCell)rowIterator.next();
			    	  if(cell.getStringCellValue().trim().equalsIgnoreCase(DataConstants.TestDataID))
			    	  break;
			      }
				 for(int rowCount = 2; (propertyRowValue = sheet.getRow(rowCount)) != null;rowCount ++)
				 {
					 
					 if ((testID = getCellIndexValue(propertyRowValue.getCell((short)ColumnNo))) == null) {
							break;
				 }
			      mapTestRow.put(testID.trim(), rowCount);
				}
		}
				return mapTestRow;
		}
		catch(FileNotFoundException fEx)
		{
        log.error("File doesnot exists");			
		}
		catch(IOException ioEx)
		{
			log.error("Error reading input file");
		}
		return null;
		
	}
	private static String getCellIndexValue(HSSFCell cell)
	{
		String cellValue = null;
		if(cell == null)
		{
			return null;
		}
		try {
		switch(cell.getCellType())
		{
		case HSSFCell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			cellValue = java.lang.Double.toString(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			cellValue = java.lang.Boolean.toString(cell.getBooleanCellValue());
		case HSSFCell.CELL_TYPE_FORMULA:
			cellValue = cell.getCellFormula();
		}
		}
		catch(NullPointerException nEx)
		{
			log.error("Value gets nullified");
		}
		return cellValue;
	}
	public String readPropertyValues(ArrayList<PropertyValue> propertyList, String propName)
	{
		PropertyValue propTempName;
		for(int i =0; i < propertyList.size(); i++)
		{
			propTempName = propertyList.get(i);
			if(propTempName.getPropertyName().equalsIgnoreCase(propName))
			{
				return propTempName.getPropertyValue();
		}
	}
		return null;
		}

	public String getTestSuiteDetails(String fileName, int sheetNumb) {
		try {
			FileInputStream input = new FileInputStream(fileName);
			POIFSFileSystem myData = new POIFSFileSystem(input);
			HSSFWorkbook workBook = new HSSFWorkbook(myData);
			String testSuiteName = null;
			
			HSSFSheet sheet = workBook.getSheetAt(sheetNumb);
			HSSFRow rowProperty;
			if((rowProperty = sheet.getRow(DataConstants.TESTSUITEROWNUMBER)) != null)
			{
				HSSFCell cell = rowProperty.getCell((short)DataConstants.TESTSUITECELLNO);
				if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
				{
					testSuiteName = cell.getStringCellValue();
				}
			}
		return testSuiteName;
	}
		catch(IOException ex)
		{
		log.error(ex.getMessage());	
		return null;
		}
}
}