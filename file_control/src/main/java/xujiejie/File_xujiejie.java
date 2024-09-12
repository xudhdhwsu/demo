package xujiejie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class File_xujiejie {

    public static StringBuilder content = new StringBuilder();
    private BufferedReader bufferedReader;
    private String parentFileString;
    private String FileName;


    public File_xujiejie(String FilePath) throws IOException {
        Read_Txt_File(FilePath);
    }

    @SuppressWarnings("resource")
    private void Read_Txt_File(String FilePath) throws IOException {
        File file = new File(FilePath);
        parentFileString = file.getParent();
        FileName = file.getName().substring(0, file.getName().lastIndexOf("."));
        FileOutputStream fileOutputStream = new FileOutputStream(parentFileString + "/" + FileName + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Data");
        int rowNum = 0;
        String line;
        bufferedReader = new BufferedReader(new FileReader(file));
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line).append("\n");
            XSSFRow row = sheet.createRow(rowNum++);
            String[] values = line.split("\t"); // 假设数据是用制表符分隔的
            for (int i = 0; i < values.length; i++) {
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(values[i]);
            }
        }
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
    }

}
