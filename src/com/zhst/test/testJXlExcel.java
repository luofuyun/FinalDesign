package com.zhst.test;


import java.io.File;  


import java.io.IOException;  
import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException;  
public class testJXlExcel {
  
	public static void main(String[] args) throws Exception {  
	       String strPath="D:\\计算机三班.xls";   
	       int rowNum = 0;    
	       String cellValue = "";   
	       File file=new File(strPath);  
	       Workbook wb = null;    
	    
	        //构造Workbook（工作薄）对象    
	          
	        wb = Workbook.getWorkbook(file);     
	        if(wb!=null)  
	        {  
	             Sheet[] sheet = wb.getSheets();     
	              
	             if(sheet!=null){      
	            //对每个工作表进行循环      
	                    for(int i=0;i<sheet.length;i++)   
	                    {   
	                      
	                    //得到当前工作表的行数     
	                    rowNum = sheet[i].getRows();   
	                                    
	                    for(int j=0;j<rowNum;j++){   
	                                     Cell[] cells = sheet[i].getRow(j);   
	                      
	                    if(cells != null && cells.length>0){   
	                        //获取单元格  
	                 for(int k=0;k<cells.length;k++){   
	                         
	                  cellValue = cells[k].getContents();   
	                       System.out.println(cellValue);  
	                      //单元格的值  
	                      }   
	                 }   
	                }   
	           }   
	          }  
	        }  
	          
	    }  
	       
	}  

