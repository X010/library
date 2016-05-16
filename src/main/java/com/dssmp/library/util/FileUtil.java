package com.dssmp.library.util;

import com.google.common.base.Preconditions;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class FileUtil {

    private final static String TARGET_DIR = "photo";


    /**
     * 获取新的文件名
     *
     * @param fileName
     * @return
     */
    public static String getNewFileName(String fileName) {
        Preconditions.checkNotNull(fileName);
        String extFile = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        return String.valueOf(System.currentTimeMillis()) + extFile;
    }

    /**
     * 获取扩展名
     *
     * @param fileName
     * @return
     */
    public static String getExtFileName(String fileName) {
        Preconditions.checkNotNull(fileName);
        String extFile = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        return extFile;
    }

    /**
     * 保存图片
     *
     * @param file
     * @return
     */
    public static String saveFileUtil(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        String oldName = file.getOriginalFilename();
        String newFileName = getNewFileName(oldName);
        String extFileName = getExtFileName(oldName);
        File Dir = new File(TARGET_DIR);
        if (!Dir.exists()) {
            Dir.mkdirs();
        }

        try {
            File photoFile = new File(TARGET_DIR, newFileName);
            file.transferTo(photoFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newFileName;
    }
}
