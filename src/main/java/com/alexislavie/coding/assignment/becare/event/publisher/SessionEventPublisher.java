package com.alexislavie.coding.assignment.becare.event.publisher;

import com.alexislavie.coding.assignment.becare.entity.Session;
import com.alexislavie.coding.assignment.becare.event.SessionEnteredStage1Event;
import com.alexislavie.coding.assignment.becare.event.SessionEnteredStage2Event;
import com.alexislavie.coding.assignment.becare.event.SessionProcessedEvent;
import com.alexislavie.coding.assignment.becare.event.SessionReceivedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class SessionEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishSessionReceivedEvent(final Session session) {
        SessionReceivedEvent sessionReceivedEvent = new SessionReceivedEvent(this, session);

        applicationEventPublisher.publishEvent(sessionReceivedEvent);
    }

    public void publishSessionEnteredStage1Event(final Session session) {
        SessionEnteredStage1Event sessionEnteredStage1Event = new SessionEnteredStage1Event(this, session);

        applicationEventPublisher.publishEvent(sessionEnteredStage1Event);
    }

    public void publishSessionEnteredStage2Event(final Session session) {
        SessionEnteredStage2Event sessionStage2Event = new SessionEnteredStage2Event(this, session);

        applicationEventPublisher.publishEvent(sessionStage2Event);
    }

    public void publishSessionProcessedEvent(final Session session) {
        SessionProcessedEvent sessionProcessedEvent = new SessionProcessedEvent(this, session);

        applicationEventPublisher.publishEvent(sessionProcessedEvent);
    }
}
