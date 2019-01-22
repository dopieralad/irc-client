package pl.dopieralad.university.sk2.irc.client.ui.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.dopieralad.university.sk2.irc.client.ui.control.ChannelSelect;
import pl.dopieralad.university.sk2.irc.client.ui.control.MessageDisplay;
import pl.dopieralad.university.sk2.irc.client.ui.control.MessageSender;

@Route
@Push
@PageTitle("IRC")
public class MainView extends VerticalLayout {

    public MainView(ChannelSelect channelSelect, MessageDisplay messageDisplay, MessageSender messageSender) {
        add(channelSelect, messageDisplay, messageSender);
    }
}
