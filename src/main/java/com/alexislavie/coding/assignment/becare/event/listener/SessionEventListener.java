package com.alexislavie.coding.assignment.becare.event.listener;


import com.alexislavie.coding.assignment.becare.dto.PipelineStatusDto;
import com.alexislavie.coding.assignment.becare.entity.Session;
import com.alexislavie.coding.assignment.becare.event.SessionEnteredStage1Event;
import com.alexislavie.coding.assignment.becare.event.SessionEnteredStage2Event;
import com.alexislavie.coding.assignment.becare.event.SessionProcessedEvent;
import com.alexislavie.coding.assignment.becare.event.SessionReceivedEvent;
import com.alexislavie.coding.assignment.becare.helper.DateHelper;
import com.alexislavie.coding.assignment.becare.helper.MathHelper;
import com.alexislavie.coding.assignment.becare.service.PipelineService;
import com.alexislavie.coding.assignment.becare.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class SessionEventListener {

    private static final Logger LOGGER = LogManager.getLogger();

    private final DateHelper dateHelper;

    private final MathHelper mathHelper;

    private final PipelineService pipelineService;

    private final SessionService sessionService;

    @Async
    @EventListener
    public void onSessionReceivedEvent(SessionReceivedEvent event) {
        Session session = event.getValue();

        pipelineService.sendPipelineStatus(
                PipelineStatusDto.builder()
                        .timestamp(
                                dateHelper.toZonedDateTime(
                                        session
                                                .getAudit()
                                                .getUpdatedOn(),
                                        ZoneId.systemDefault()
                                )
                        )
                        .message("Received session ID " + session.getId()
                                + " with data " + session.getMeasures())
                        .build()
        );

        try {
            TimeUnit.MILLISECONDS.sleep(mathHelper.randomValue(1000, 2000));
        } catch (InterruptedException e) {
            LOGGER.error(e);

            Thread.currentThread().interrupt();
        }

        sessionService.enterStage1(session);
    }

    @Async
    @EventListener
    public void onSessionEnteredStage1Event(SessionEnteredStage1Event event) {
        Session session = event.getValue();

        pipelineService.sendPipelineStatus(
                PipelineStatusDto.builder()
                        .timestamp(
                                dateHelper.toZonedDateTime(
                                        session
                                                .getAudit()
                                                .getUpdatedOn(),
                                        ZoneId.systemDefault()
                                )
                        )
                        .message("Session ID " + session.getId()
                                + " Entered stage STAGE_1")
                        .build()
        );

        try {
            TimeUnit.MILLISECONDS.sleep(mathHelper.randomValue(1000, 2000));
        } catch (InterruptedException e) {
            LOGGER.error(e);

            Thread.currentThread().interrupt();
        }

        sessionService.enterStage2(session);
    }

    @Async
    @EventListener
    public void onSessionEnteredStage2Event(SessionEnteredStage2Event event) {
        Session session = event.getValue();

        pipelineService.sendPipelineStatus(
                PipelineStatusDto.builder()
                        .timestamp(
                                dateHelper.toZonedDateTime(
                                        session
                                                .getAudit()
                                                .getUpdatedOn(),
                                        ZoneId.systemDefault()
                                )
                        )
                        .message("Session ID " + session.getId()
                                + " Entered stage STAGE_2")
                        .build()
        );

        try {
            TimeUnit.MILLISECONDS.sleep(mathHelper.randomValue(1000, 2000));
        } catch (InterruptedException e) {
            LOGGER.error(e);

            Thread.currentThread().interrupt();
        }

        sessionService.process(session);
    }

    @Async
    @EventListener
    public void onSessionProcessedEvent(SessionProcessedEvent event) {
        Session session = event.getValue();

        pipelineService.sendPipelineStatus(
                PipelineStatusDto.builder()
                        .timestamp(
                                dateHelper.toZonedDateTime(
                                        session
                                                .getAudit()
                                                .getUpdatedOn(),
                                        ZoneId.systemDefault()
                                )
                        )
                        .message("Session ID " + session.getId()
                                + " is processed.")
                        .build()
        );
    }
}
