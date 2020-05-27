package com.example.demo.file.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileVo implements Serializable {
    private static final long serialVersionUID = -5452801884470115159L;

    private Integer fileId;//主键

    private String filePath;//文件保存路径

    private String fileName;//文件保存名称
}
