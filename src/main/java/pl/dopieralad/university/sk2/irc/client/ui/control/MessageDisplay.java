package pl.dopieralad.university.sk2.irc.client.ui.control;

import java.time.format.DateTimeFormatter;
import javax.annotation.PostConstruct;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dopieralad.university.sk2.irc.client.broadcast.Broadcaster;
import pl.dopieralad.university.sk2.irc.client.provider.MessageProvider;
import pl.dopieralad.university.sk2.irc.message.Message;

@SpringComponent
@UIScope
public class MessageDisplay extends ListBox<Message> implements HasSize {

    private final MessageProvider messageProvider;
    private final Broadcaster<Message> messageBroadcaster;
    private Registration registration;

    @Autowired
    public MessageDisplay(MessageProvider messageProvider, Broadcaster<Message> messageBroadcaster) {
        this.messageProvider = messageProvider;
        this.messageBroadcaster = messageBroadcaster;
    }

    @PostConstruct
    public void setup() {
        setReadOnly(true);
        setSizeFull();
        setRenderer(new ComponentRenderer<>(renderingFunction()));
        setDataProvider(messageProvider);
    }

    private SerializableFunction<Message, Component> renderingFunction() {
        var timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return message -> {
            var timeString = timeFormatter.format(message.getTime());
            var time = new Text(timeString);

            var author = new Label(message.getAuthor());
            author.getStyle().set("font-weight", "bold");

            var content = new Label(message.getContent());
            content.setSizeFull();

            var horizontalLayout = new HorizontalLayout(time, author, content);
            horizontalLayout.setSizeFull();

            return horizontalLayout;
        };
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        var ui = attachEvent.getUI();
        registration = messageBroadcaster.register(message -> ui.access(messageProvider::refreshAll));
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        registration.remove();
        registration = null;
    }
}
