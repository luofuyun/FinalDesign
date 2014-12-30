package com.zhst.Util;
import org.apache.commons.lang.StringUtils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class JxlExcelUtil {

	public static int getRightRows(Sheet sheet) {
		int rsCols = sheet.getColumns(); //列数
		int rsRows = sheet.getRows(); //行数
		int nullCellNum;
		int afterRows = rsRows;
		for (int i = 1; i < rsRows; i++) { //统计行中为空的单元格数
		   nullCellNum = 0;
		    for (int j = 0; j < rsCols; j++) {
		        String val = sheet.getCell(j, i).getContents();
		        val = StringUtils.trimToEmpty(val);
		        if (StringUtils.isBlank(val))
		           nullCellNum++;
		    }
		    if (nullCellNum >= rsCols) { //如果nullCellNum大于或等于总的列数
		     afterRows--;          //行数减一
		   }
		}
		return afterRows;
		}
}
