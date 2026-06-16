package com.example.hkt1.controller;

import com.example.hkt1.model.Watch;
import com.example.hkt1.service.WatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class WatchController {

    private final WatchService watchService;

    @GetMapping
    public ResponseEntity<?> getAllWatches() {
        log.info("GET /api/v1/watches called");
        return ResponseEntity.ok(watchService.getAllWatches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Watch> getWatchById(@PathVariable Long id) {
        log.info("GET /api/v1/watches/{} called", id);
        return ResponseEntity.ok(watchService.getWatchById(id));
    }

    @PostMapping
    public ResponseEntity<Watch> addWatch(@Valid @RequestBody Watch watch) {
        log.info("POST /api/v1/watches called");
        Watch createdWatch = watchService.addWatch(watch);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWatch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Watch> updateWatch(
            @PathVariable Long id,
            @Valid @RequestBody Watch watch
    ) {
        log.info("PUT /api/v1/watchs/{} called", id);
        Watch updatedWatch = watchService.updateWatch(id, watch);
        return ResponseEntity.ok(updatedWatch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWatch(@PathVariable Long id) {
        log.info("DELETE /api/v1/watches/{} called", id);
        watchService.deleteWatch(id);
        return ResponseEntity.noContent().build();
    }

}
