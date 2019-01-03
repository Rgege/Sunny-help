package org.blue.helper.StringHelper.utils;

import org.blue.helper.StringHelper.common.exception.HelperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileUtils {

    private static final Logger LOGGER=LoggerFactory.getLogger(FileUtils.class);
    /**
     * 使目录/文件夹存在  若已经目录/文件夹已经存在直接返回  若不存在新建
     *
     * @param drctPath
     * @return
     */
    public static boolean makPathExist(String drctPath) {
        if (drctPath == null || drctPath == "") {
            return false;
        }
        File file = new File(drctPath);
        if (file.isDirectory()) {
            return true;
        } else {
            file.mkdir();
        }
        return false;
    }

    public static boolean isFolder(String fileName) {
        File file = new File(fileName);
        return file.isDirectory();
    }

    public static String getSuffix(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return suffix;
    }

    private static List<String> getAllFilePaths(File filePath, List<String> filePaths) {

        if (filePath.exists()) {
            File[] files = filePath.listFiles();
            if (files == null) {
                return filePaths;
            }
            for (File f : files) {
                if (f.isDirectory()) {
                    filePaths.add(f.getPath());
                    getAllFilePaths(f, filePaths);
                } else {
                    filePaths.add(f.getPath());
                }
            }
        }
        return filePaths;
    }

    public static void copyAll(Map<String,String> map) {
        if(map ==null) return;
        for (Map.Entry<String,String> entry:map.entrySet()) {
            try {
                copy(entry.getKey(), entry.getValue());
            }catch (HelperException e){
                LOGGER.error("复制文件："+entry.getKey()+" 出错",e);
                continue;
            }
        }
    }


    public static void copy(String sourcePath, String targetDir) {
        File sourceFile = new File(sourcePath);
        if (!sourceFile.exists()) {
            throw new HelperException("Source file can not be null");
        }
        File targetFile = new File(targetDir);
        try {

            if (targetFile.exists()) {
                throw new HelperException("The file already exists in the destination folder");
            } else {
                LOGGER.debug("");
                targetFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new HelperException(e);
        }
        BufferedReader br = null;
        BufferedWriter bw = null;
        StringBuilder sb=new StringBuilder();

        try {
            br = new BufferedReader(new FileReader(sourceFile));
            bw = new BufferedWriter(new FileWriter(targetFile));

            String line=null;
            while ((line = br.readLine()) != null){
                sb.append(line).append("\n");
            }
            bw.write(sb.toString());
            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new HelperException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new HelperException(e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public static String getProjectFilePath(){
        ClassLoader classLoader=FileUtils.class.getClassLoader();
        URL projectUrl=classLoader.getResource("");
        String path=projectUrl.getPath();
        String projectPath=path.substring(1,(path.indexOf("target/classes/")-1)).replaceAll("/","\\\\");
        return projectPath;
    }
    public static void main(String[] args) {
//        copy("C:\\Users\\User\\Desktop\\c\\a\\BillRecordDayMapper.xml",
//                "C:\\Users\\User\\Desktop\\c\\b");
    }
}
