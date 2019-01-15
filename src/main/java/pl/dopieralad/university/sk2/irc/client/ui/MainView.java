package pl.dopieralad.university.sk2.irc.client.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dopieralad.university.sk2.irc.client.ui.control.MessageSender;

@SpringComponent
@Route
public class MainView extends VerticalLayout {

    @Autowired
    public MainView(MessageSender messageSender) {
        add(messageSender);
    }

}
