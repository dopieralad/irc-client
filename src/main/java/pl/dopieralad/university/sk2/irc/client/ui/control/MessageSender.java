package pl.dopieralad.university.sk2.irc.client.ui.control;

import java.io.IOException;
import javax.annotation.PostConstruct;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dopieralad.university.sk2.irc.server.ServerAdapter;

@SpringComponent
@UIScope
public class MessageSender extends HorizontalLayout {

    private final transient Logger logger;
    private final transient ServerAdapter serverAdapter;

    @Autowired
    public MessageSender(Logger logger, ServerAdapter serverAdapter) {
        this.logger = logger;
        this.serverAdapter = serverAdapter;
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void setup() {
        var messageField = new TextField();
        messageField.setSizeFull();
        messageField.setPlaceholder("Message");

        var sendButton = new Button("Send");

        var sumbitHandler = createHandleSubmit(messageField);
        messageField.addKeyDownListener(Key.ENTER, sumbitHandler);
        sendButton.addClickListener(sumbitHandler);

        setSizeFull();
        add(messageField, sendButton);
    }

    private ComponentEventListener createHandleSubmit(TextField textField) {
        return event -> {
            try {
                var message = textField.getValue();
                serverAdapter.send(message);
                textField.setValue("");
            } catch (IOException e) {
                logger.error("An error has occurred during message submit!", e);
                Notification.show("An error has occurred during message submit! Please retry.");
            }
            textField.focus();
        };
    }
}
