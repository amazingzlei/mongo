package com.example.demo.file.utils;
import lombok.extern.log4j.Log4j2;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 * 文件压缩工具类
 */
@Log4j2
public class ZipCompress {

    private String zipFileName; // 目的地Zip文件
    private String sourceFileName; // 源文件（带压缩的文件或文件夹）

    public ZipCompress(String zipFileName, String sourceFileName) {
        this.zipFileName = zipFileName;
        this.sourceFileName = sourceFileName;
    }


    public void zip() throws Exception {
        //File zipFile = new File(zipFileName);
        log.info("开始压缩中...");
        long begin = System.currentTimeMillis();
        //创建zip输出流
        File zipFile = new File(zipFileName);
        if (!zipFile.exists()) {
            zipFile.createNewFile();
        }
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        //创建缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(out);
        File sourceFile = new File(sourceFileName);
        //调用函数
        compress(out, bos, sourceFile, sourceFile.getName());
        bos.close();
        out.close();
        long currentTimeMillis = System.currentTimeMillis();
        log.info(zipFileName + "压缩完成(100%)....." + (currentTimeMillis - begin) + "ms");
    }

    public void zipNoDirectory() throws Exception {
        //File zipFile = new File(zipFileName);
        log.info("开始压缩中...");
        long begin = System.currentTimeMillis();
        //创建zip输出流
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        //创建缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(out);
        File sourceFile = new File(sourceFileName);
        if (sourceFile.isDirectory()) {
            File[] flist = sourceFile.listFiles();
            for (int i = 0; i < flist.length; i++) {
                compress(out, bos, flist[i], flist[i].getName());
            }
        }
        //调用函数
//        compress(out,bos,sourceFile,sourceFile.getName());
        bos.close();
        out.close();
        long currentTimeMillis = System.currentTimeMillis();
        log.info(zipFileName + "压缩完成(100%)....." + (currentTimeMillis - begin) + "ms");
    }

    public void compress(ZipOutputStream out, BufferedOutputStream bos, File sourceFile, String base) throws Exception {
        //如果路径为目录（文件夹）
        if (sourceFile.isDirectory()) {
            //取出文件夹中的文件（或子文件夹）
            File[] flist = sourceFile.listFiles();
            if (flist.length == 0) {//如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点
                //System.out.println("空:******"+base+"/");
                out.putNextEntry(new ZipEntry(base + File.separator));
            } else {//如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
                for (int i = 0; i < flist.length; i++) {
                    compress(out, bos, flist[i], base + "/" + flist[i].getName());
                }
            }
        } else {//如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
            out.putNextEntry(new ZipEntry(base));
            FileInputStream fos = new FileInputStream(sourceFile);
            BufferedInputStream bis = new BufferedInputStream(fos);
            int tag;
            // System.out.println(base);
            //将源文件写入到zip文件中
            while ((tag = bis.read()) != -1) {
                bos.write(tag);
            }
            bos.flush();
            bis.close();
            fos.close();
        }
    }
}