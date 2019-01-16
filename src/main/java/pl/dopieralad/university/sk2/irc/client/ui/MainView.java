package pl.dopieralad.university.sk2.irc.client.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.dopieralad.university.sk2.irc.client.ui.control.MessageDisplay;
import pl.dopieralad.university.sk2.irc.client.ui.control.MessageSender;

@Route
@PageTitle("IRC")
public class MainView extends VerticalLayout {

    public MainView(MessageDisplay messageDisplay, MessageSender messageSender) {
        add(messageDisplay, messageSender);
    }
}
