package com.adamyrayeuk.malcheck.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adamyrayeuk.malcheck.service.MalcheckService;
import com.adamyrayeuk.malcheck.util.FileTypeChecker;
import com.google.gson.Gson;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Malware Checker", description = "Malware checker using Yara application (find out about Yara through https://github.com/VirusTotal/yara)")
@RestController
@RequestMapping("v1/api/")
public class MalcheckController {
    
    private static final Gson gson = new Gson();

    @Autowired
    MalcheckService malcheckService;

    @Operation(summary = "Pattern matching using set of rules obtained from https://github.com/Yara-Rules/rules")
    @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) })
    @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) })
    @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    @PostMapping(path = "/malware-checking", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> malwareChecking(@RequestParam MultipartFile file) {
        Map<String, String> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("message", "Please select a file to upload");
            return new ResponseEntity<>(gson.toJson(response), HttpStatus.BAD_REQUEST);
        }

        if (FileTypeChecker.isCompressedFile(file)) {
            response.put("message", "Please do not upload compressed file");
            return new ResponseEntity<>(gson.toJson(response), HttpStatus.BAD_REQUEST);
        }

        try {
            response = malcheckService.malwareCheckWithDefinedRules(file);
            return new ResponseEntity<>(gson.toJson(response), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
