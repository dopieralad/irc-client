package pl.dopieralad.university.sk2.irc.client.ui.control;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyDownEvent;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dopieralad.university.sk2.irc.channel.Channel;
import pl.dopieralad.university.sk2.irc.channel.ChannelRepository;
import pl.dopieralad.university.sk2.irc.client.broadcast.Broadcaster;

@SpringComponent
@UIScope
public class ChannelSelect extends ComboBox<Channel> implements KeyNotifier {

    private final ChannelRepository channelRepository;
    private final Broadcaster<Channel> channelBroadcaster;
    private Registration registration;

    @Autowired
    public ChannelSelect(ChannelRepository channelRepository, Broadcaster<Channel> channelBroadcaster) {
        this.channelRepository = channelRepository;
        this.channelBroadcaster = channelBroadcaster;
    }

    @PostConstruct
    public void setup() {
        setPlaceholder("Channel");
        setItems(channelRepository.getChannels());
        setItemLabelGenerator(Channel::getName);
        setAllowCustomValue(true);
        addKeyDownListener(Key.ENTER, submitHandler());
    }

    private ComponentEventListener<KeyDownEvent> submitHandler() {
        return event -> {
            var channelName = getFilterString();
            var channel = new Channel(channelName);
            channelRepository.addChannel(channel);
            setItems(channelRepository.getChannels());
            setValue(channel);
        };
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        var ui = attachEvent.getUI();
        registration = channelBroadcaster.register(message -> ui.access(() -> {
            var channel = getValue();
            setItems(channelRepository.getChannels());
            setValue(channel);
        }));
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        registration.remove();
        registration = null;
    }
}
