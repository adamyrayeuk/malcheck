package com.adamyrayeuk.malcheck.util;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class FileTypeChecker {
    private static final ArrayList<String> COMPRESSED_TYPE = new ArrayList<>(Arrays.asList(
        ".zip",
        ".rar",
        ".tar",
        ".gz",
        ".7z"
    ));

    private FileTypeChecker() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isCompressedFile(MultipartFile file) {
        String fileName = file.getOriginalFilename().toLowerCase();

        for (String type : COMPRESSED_TYPE) {
            if (fileName.endsWith(type)) {
                return true;
            }
        }

        return false;
    }
}