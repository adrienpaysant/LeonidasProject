package com.leonidas4G.config;


import com.leonidas4G.service.PacketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@RequiredArgsConstructor
public class DestroyAction {
    private final PacketService packetService;

    @PreDestroy
    public void destroy() {
        packetService.deleteAll();
    }
}