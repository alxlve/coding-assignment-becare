package com.alexislavie.coding.assignment.becare.service;

import com.alexislavie.coding.assignment.becare.entity.Session;
import com.alexislavie.coding.assignment.becare.entity.SessionState;
import com.alexislavie.coding.assignment.becare.event.publisher.SessionEventPublisher;
import com.alexislavie.coding.assignment.becare.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class SessionService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final SessionEventPublisher sessionEventPublisher;

    private final SessionRepository sessionRepository;

    public Session receive(final Session session) {
        session.setSessionState(SessionState.RECEIVED);
        Session savedSession = sessionRepository.save(session);

        sessionEventPublisher.publishSessionReceivedEvent(savedSession);

        return savedSession;
    }

    public void enterStage1(final Session session) {
        session.setSessionState(SessionState.STAGE_1);

        sessionEventPublisher.publishSessionEnteredStage1Event(
                sessionRepository.save(session)
        );
    }

    public void enterStage2(final Session session) {
        session.setSessionState(SessionState.STAGE_2);

        sessionEventPublisher.publishSessionEnteredStage2Event(
                sessionRepository.save(session)
        );
    }

    public void process(final Session session) {
        session.setSessionState(SessionState.PROCESSED);

        sessionEventPublisher.publishSessionProcessedEvent(
                sessionRepository.save(session)
        );
    }
}
