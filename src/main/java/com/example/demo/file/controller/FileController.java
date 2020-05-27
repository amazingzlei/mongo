package com.example.demo.file.controller;

import com.example.demo.file.service.FileService;
import com.example.demo.file.utils.CompressUtil;
import com.example.demo.file.utils.ZipCompress;
import com.example.demo.file.utils.ZipUtils;
import com.example.demo.file.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipOutputStream;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 打包压缩下载文件,不能级联子文件夹
     */
    @RequestMapping(value = "/downLoadZipFile")
    public void downLoadZipFile(HttpServletResponse response) throws IOException {
        String zipName = "myfile.zip";
        List<FileVo> fileList = fileService.getFileList();//查询数据库中记录
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=" + zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            for (Iterator<FileVo> it = fileList.iterator(); it.hasNext(); ) {
                FileVo file = it.next();
                ZipUtils.doCompress(file.getFilePath() + file.getFileName(), out);
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    /**
     * 文件zip下载，包括子文件夹，但是是下载到本地
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/downLoadZipFile2")
    public void downLoadZipFile2(HttpServletResponse response) throws IOException {
        String targetFolderPath = "E:\\books\\笔记\\";
        String newZipFilePath = "E:\\my.zip";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=" + "my.zip");

        CompressUtil.compress(targetFolderPath , newZipFilePath);
        response.flushBuffer();
    }

    /**
     * 文件夹多层下载(浏览器)
     * @param servletResponse
     * @throws Exception
     */
    @RequestMapping("/downLoadZipFile3")
    public void downloadZip(HttpServletResponse servletResponse) throws Exception{
        // 打包文件夹路径
        String baseTempPath = "E:\\books\\笔记\\";

        String zipFileName = "申请表" +".zip";
        // 压缩
        ZipCompress compress = new ZipCompress(zipFileName, baseTempPath);
        compress.zip();
        // 获取压缩文件流
        InputStream zipStream = new FileInputStream(new File(zipFileName));
        // 下载文件名中文处理
        String downZipFileName = URLEncoder.encode(zipFileName.substring(zipFileName.lastIndexOf("/") + 1), "UTF-8");
        // 下载到浏览器
        servletResponse.setHeader("Content-disposition", "attachment; filename*=UTF-8''" + downZipFileName);
        BufferedOutputStream bufferedOs = new BufferedOutputStream(servletResponse.getOutputStream());
        byte[] buffer = new byte[10240];
        int bytesRead = 0;
        while ((bytesRead = zipStream.read(buffer)) != -1) {
            bufferedOs.write(buffer, 0, bytesRead);
        }
        bufferedOs.flush();
        bufferedOs.close();
        zipStream.close();

//        FileUtils.deleteDirectory(new File(baseTempPath));
        new File(zipFileName).delete();
    }
}
