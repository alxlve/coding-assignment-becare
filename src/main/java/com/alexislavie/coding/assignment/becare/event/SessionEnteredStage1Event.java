package com.alexislavie.coding.assignment.becare.event;

import com.alexislavie.coding.assignment.becare.entity.Session;
import lombok.ToString;

@ToString(callSuper = true)
@SuppressWarnings("serial")
public class SessionEnteredStage1Event extends GenericApplicationEvent<Session> {

    public SessionEnteredStage1Event(Object source, Session session) {
        super(source, session);
    }
}
