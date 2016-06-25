package common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelKit
{
    public static void write(String filename, List<String> headers, List<Map<String, String>> datas)
    {
        //创建工作薄
        Workbook workbook = new HSSFWorkbook();

        //创建工作区
        Sheet sheet = workbook.createSheet();

        //创建第一行
        Row row;
        Cell cell;

        row = sheet.createRow(0);
        for (int ci = 0; ci < headers.size(); ci++)
        {
            cell = row.createCell(ci);
            cell.setCellValue(headers.get(ci));
        }

        Map<String, String> data;
        for (int ri = 0; ri < datas.size(); ri++)
        {
            row = sheet.createRow(ri + 1);
            data = datas.get(ri);
            for (int ci = 0; ci < headers.size(); ci++)
            {
                cell = row.createCell(ci);
                cell.setCellValue(data.get(headers.get(ci)));
            }
        }

        File file = new File(filename);
        try
        {
            FileOutputStream stream = FileUtils.openOutputStream(file);
            workbook.write(stream);
            stream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
