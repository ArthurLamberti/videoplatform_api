package com.arthurlamberti.videoplataform.domain.exception;

import com.arthurlamberti.videoplataform.domain.validation.Error;
import com.arthurlamberti.videoplataform.domain.validation.handler.Notification;

import java.util.List;

public class NotificationException extends DomainException {
    public NotificationException(String message, final Notification notification) {
        super(message, notification.getErrors());
    }
}
