package com.alexislavie.coding.assignment.becare.event;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString(callSuper = true)
@SuppressWarnings({
        "serial",
        "squid:S1948"
})
abstract class GenericApplicationEvent<T> extends ApplicationEvent {

    private T value;

    GenericApplicationEvent(Object source, T value) {
        super(source);

        this.value = value;
    }
}
