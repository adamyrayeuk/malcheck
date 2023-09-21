package com.adamyrayeuk.malcheck.exception;

import java.io.IOException;

public class UploadFailedException extends IOException {
    public UploadFailedException(String errorMessage) {
        super("File transfer to destionation failed, " + errorMessage);
    }
}
