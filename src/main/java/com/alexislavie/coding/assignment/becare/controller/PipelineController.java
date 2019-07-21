package com.alexislavie.coding.assignment.becare.controller;

import com.alexislavie.coding.assignment.becare.configuration.application.ApplicationConfiguration;
import com.alexislavie.coding.assignment.becare.dto.PipelineStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class PipelineController {

    private final ApplicationConfiguration applicationConfiguration;

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void sendPipelineStatus(PipelineStatusDto pipelineStatus) {
        this.simpMessagingTemplate.convertAndSend(
                applicationConfiguration.getApi().getWebSocketTopicDestinationPrefix() + "/pipeline",
                pipelineStatus
        );
    }
}
