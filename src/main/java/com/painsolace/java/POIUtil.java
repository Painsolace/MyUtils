package com.painsolace.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * POI操作工具类
 * 
 * @author painsolace
 */
public class POIUtil {
    private POIUtil() {
    }

    /**
     * 文件名分隔符
     */
    private static String SEPARATOR = File.separator;

    /**
     * 以给定的文件路径创建一个新的Excel文件
     * 
     * @param filepath
     *            相对于工程的文件路径
     * @return
     * @throws IOException
     */
    public static File createExcelFile(String excelTemplatePath, String filepath)
                                                                                 throws IOException {

        // 获取到文件的父目录
        String path = POIUtil.getFilePath(filepath);

        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        // 复制模板
        FileOutputStream fos = null;
        InputStream fis = null;
        byte[] bytes = new byte[1024];
        int len = 0;
        try {
            fos = new FileOutputStream(file);
            fis = POIUtil.class.getResourceAsStream(excelTemplatePath);
            while ((len = fis.read(bytes)) > 0) {
                fos.write(bytes, 0, len);
            }
        } finally {
            if (fos != null) {
                fos.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return file;
    }

    /**
     * 上下文路径
     */
    public static String getFilePath(String filepath) {
        if (filepath.startsWith(SEPARATOR)) {
            throw new IllegalArgumentException("【" + filepath + "】不是一个有效的路径");
        }
        return POIUtil.class.getResource("/").getPath() + filepath;
    }

    /**
     * 设置第一行标题
     * 
     * @param attendance
     * @param sheet
     */
    public static void setTitle(String title, HSSFWorkbook work, int i) {
        work.getSheetAt(i).getRow(0).getCell(0)
            .setCellValue(new HSSFRichTextString(String.valueOf(title)));
    }

    /**
     * 获取单元格样式
     * 
     * @param sheet
     * @return
     */
    public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook, Map<String, Object> param) {
        HSSFCellStyle style = workbook.createCellStyle();

        // 字体
        HSSFFont font = workbook.createFont();
        if (param.get("fontName") != null) {
            font.setFontName((String) param.get("fontName"));
        }
        if (param.get("fontHeight") != null)
            font.setFontHeight((Short) param.get("fontHeight"));
        style.setFont(font);

        // 设置边框
        if (param.get("borderLeft") != null)
            style.setBorderLeft((Short) param.get("borderLeft"));
        if (param.get("borderRight") != null)
            style.setBorderRight((Short) param.get("borderRight"));
        if (param.get("borderTop") != null)
            style.setBorderTop((Short) param.get("borderTop"));
        if (param.get("borderBottom") != null)
            style.setBorderBottom((Short) param.get("borderBottom"));

        // 设置居中
        if (param.get("alignment") != null)
            style.setAlignment((Short) param.get("alignment"));
        if (param.get("verticalAlignment") != null)
            style.setVerticalAlignment((Short) param.get("verticalAlignment"));

        return style;
    }

    /**
     * 计算某列，从哪行到哪行连续数据的和
     * */
    public static int calcCount(int col, int sRow, int eRow, HSSFSheet sheet) {
        int sum = 0;
        for (int i = sRow; i <= eRow; i++) {
            if (sheet.getRow(i) != null && sheet.getRow(i).getCell(col) != null) {
                int j = (int) sheet.getRow(i).getCell(col).getNumericCellValue();

                sum += j;
            }
        }
        return sum;
    }

    /**
     * 设置单元格样式
     * 
     * @param cell
     * @param wb
     * @param style
     */
    public static void setCellStyle(HSSFCell cell, HSSFWorkbook wb, Map<String, Object> style) {
        cell.setCellStyle(getCellStyle(wb, style));
    }
}
