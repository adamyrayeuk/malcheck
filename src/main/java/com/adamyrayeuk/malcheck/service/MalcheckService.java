package com.adamyrayeuk.malcheck.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.adamyrayeuk.malcheck.exception.CLIOutputReadingException;
import com.adamyrayeuk.malcheck.exception.UploadFailedException;
import com.adamyrayeuk.malcheck.util.Randomizer;

@Service
public class MalcheckService {
    
    @Value("${uploaded-file-dir}")
    private String uploadDir;

    public Map<String, String> malwareCheckWithDefinedRules(MultipartFile file) throws UploadFailedException, CLIOutputReadingException {
        Map<String, String> response = new HashMap<>();

        String fileName;
        File destinationFile;

        try {
            // Randomize the file name to prevent uncessary risk
            fileName = Randomizer.generateRandomString(8) + "_" + file.getOriginalFilename();

            // Make sure upload directory exist, if not create it
            Path uploadPath = Paths.get(uploadDir);
            if (!uploadPath.toFile().exists()) {
                uploadPath.toFile().mkdirs();
            }

            // Save the file to the specified directory
            destinationFile = new File(uploadDir + File.separator + fileName);
            file.transferTo(destinationFile);
        } catch (IOException e) {
            throw new UploadFailedException(e.getMessage());
        }

        ProcessBuilder processBuilder = new ProcessBuilder(
            "cmd.exe", 
            "/c", 
            "yara -w .\\src\\main\\resources\\rules\\index.yar " + uploadDir + "\\" + fileName 
        );

        try {
            Process p = processBuilder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String output = r.readLine();
            String message = output == null ? "File is safe" : output.split(" ")[0].replace("_", " ") + " detected in the file";
            response.put("message", message);
        } catch (IOException e) {
            throw new CLIOutputReadingException(e.getMessage());
        } finally {
            if (destinationFile.exists()) {
                destinationFile.delete();
            }
        }

        return response;
    }
}
