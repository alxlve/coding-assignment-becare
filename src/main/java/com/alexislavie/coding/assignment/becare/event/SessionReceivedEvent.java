package com.alexislavie.coding.assignment.becare.event;

import com.alexislavie.coding.assignment.becare.entity.Session;
import lombok.ToString;

@ToString(callSuper = true)
@SuppressWarnings("serial")
public class SessionReceivedEvent extends GenericApplicationEvent<Session> {

    public SessionReceivedEvent(Object source, Session session) {
        super(source, session);
    }
}
