package com.bureauzero.core.controller;

import com.bureauzero.core.entity.CitizenApplication;
import com.bureauzero.core.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MissionControlController {

    private final ApplicationRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String PYTHON_AI_URL = "http://127.0.0.1:8001";

    @PostMapping("/ingest-document")
    public ResponseEntity<?> ingestCitizenDocument(@RequestParam("file") MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity response = restTemplate.postForEntity(
                PYTHON_AI_URL + "/ai/extract-profile", requestEntity, Map.class);
        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping("/dispatch-agent")
    public ResponseEntity<?> dispatchBrowserAgent(@RequestBody Map<String, Object> payload) {
        ResponseEntity response = restTemplate.postForEntity(
                PYTHON_AI_URL + "/ai/execute-workflow", payload, Map.class);
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/registry-ledger")
    public List<CitizenApplication> getLiveRegistryLedger() {
        return repository.findAll();
    }
}
