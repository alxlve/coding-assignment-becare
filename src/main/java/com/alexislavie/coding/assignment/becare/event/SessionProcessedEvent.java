package com.alexislavie.coding.assignment.becare.event;

import com.alexislavie.coding.assignment.becare.entity.Session;
import lombok.ToString;

@ToString(callSuper = true)
@SuppressWarnings("serial")
public class SessionProcessedEvent extends GenericApplicationEvent<Session> {

    public SessionProcessedEvent(Object source, Session session) {
        super(source, session);
    }
}
