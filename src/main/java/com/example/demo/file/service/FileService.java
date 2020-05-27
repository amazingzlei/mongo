package com.example.demo.file.service;

import com.example.demo.file.vo.FileVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    public List<FileVo> getFileList(){
        List<FileVo> fileVos = new ArrayList<>();
        FileVo fileVo = new FileVo();
        fileVo.setFileId(1);
        fileVo.setFileName("activiti工作流.docx");
        fileVo.setFilePath("E:\\books\\笔记\\");
        fileVos.add(fileVo);

        FileVo fileVo2 = new FileVo();
        fileVo2.setFileId(2);
        fileVo2.setFileName("oracle.pptx");
        fileVo2.setFilePath("E:\\books\\笔记\\");
        fileVos.add(fileVo2);

        FileVo fileVo3 = new FileVo();
        fileVo3.setFileId(3);
        fileVo3.setFileName("redis.docx");
        fileVo3.setFilePath("E:\\books\\笔记\\");
        fileVos.add(fileVo3);

        return fileVos;
    }
}
