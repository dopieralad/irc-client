package pl.dopieralad.university.sk2.irc.client.ui.control;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyDownEvent;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dopieralad.university.sk2.irc.channel.Channel;
import pl.dopieralad.university.sk2.irc.channel.ChannelRepository;

@SpringComponent
@UIScope
public class ChannelSelect extends ComboBox<Channel> implements KeyNotifier {

    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelSelect(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
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
}
