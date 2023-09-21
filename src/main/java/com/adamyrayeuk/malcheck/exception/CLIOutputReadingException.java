package com.adamyrayeuk.malcheck.exception;

import java.io.IOException;

public class CLIOutputReadingException extends IOException {
    public CLIOutputReadingException(String errorMessage) {
        super("CLI output reading failed, " + errorMessage);
    }
}
