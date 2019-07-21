package com.alexislavie.coding.assignment.becare.service;

import com.alexislavie.coding.assignment.becare.controller.PipelineController;
import com.alexislavie.coding.assignment.becare.dto.PipelineStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PipelineService {

    private final PipelineController pipelineController;

    public void sendPipelineStatus(PipelineStatusDto pipelineStatusDto) {
        this.pipelineController.sendPipelineStatus(pipelineStatusDto);
    }
}
