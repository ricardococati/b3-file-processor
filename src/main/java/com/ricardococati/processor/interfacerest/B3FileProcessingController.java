package com.ricardococati.processor.interfacerest;

import com.ricardococati.processor.application.usecase.ProcessB3FileUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/b3")
public class B3FileProcessingController {

    private final ProcessB3FileUseCase processB3FileUseCase;

    public B3FileProcessingController(ProcessB3FileUseCase processB3FileUseCase) {
        this.processB3FileUseCase = processB3FileUseCase;
    }

    @GetMapping("/processar-arquivo")
    public ResponseEntity<String> processarArquivo(@RequestParam String filePath) {
        try {
            processB3FileUseCase.execute(filePath);
            return ResponseEntity.ok("Job de processamento iniciado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao iniciar o job: " + e.getMessage());
        }
    }
}