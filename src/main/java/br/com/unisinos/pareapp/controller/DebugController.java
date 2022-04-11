package br.com.unisinos.pareapp.controller;

import br.com.unisinos.pareapp.model.dto.MemoryStatsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {
    @GetMapping("memory-status")
    public MemoryStatsDto getMemoryStatistics() {
        MemoryStatsDto stats = new MemoryStatsDto();
        stats.setHeapSize(Runtime.getRuntime().totalMemory());
        stats.setHeapMaxSize(Runtime.getRuntime().maxMemory());
        stats.setHeapFreeSize(Runtime.getRuntime().freeMemory());
        return stats;
    }
}
