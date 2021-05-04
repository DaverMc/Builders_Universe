package de.buun.haven.lang;

import de.buun.haven.lang.json.MessageBuilder;
import de.buun.haven.lang.json.MessageEvent;
import de.buun.haven.lang.json.MessageType;
import de.buun.haven.util.Registration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageRegistration implements Registration<LanguageFile> {

    private final Map<String, LanguageFile> languageFiles;

    public MessageRegistration(){
        this.languageFiles = new HashMap<>();
    }

    public void send(String language, String path, Player player, Object...values){
        MessageBuilder builder = new MessageBuilder(MessageType.SYSTEM_MESSAGE);
        String message = languageFiles.get(language).processMessage(path, values);
        builder.append(message);
        builder.send(player);
    }

    public void send(String language, String path, Player player, List<MessageEvent> events, Object...values){
        String message = languageFiles.get(language).processMessage(path, values);
        String[] parts = message.split("<event: ");
        processEvents(events, parts).send(player);
    }

    private MessageBuilder processEvents(List<MessageEvent> events, String[] parts){
        MessageBuilder builder = new MessageBuilder(MessageType.SYSTEM_MESSAGE);

        int rounds = Math.min(events.size(), parts.length);

        for (int i = 0; i < rounds; i++){
            processEvent(builder, events.get(i), parts[i]);
        }

        return builder;
    }

    private void processEvent(MessageBuilder builder, MessageEvent event, String part){
        if(!(part.contains("<text: ") && part.contains("<value: "))){
            builder.append(part);
            return;
        }
        String[] split = part.split("> <");
        String text = split[0].replace("<text: ", "");
        String rawValue = split[1].replace("value: ", "");
        String[] valueText = rawValue.split(">>");
        event.setText(text);
        event.setValue(new String[]{valueText[0]});
        builder.append(event);
        if(valueText.length > 1){
            builder.append(valueText[1]);
        }
    }

    @Override
    public void register(LanguageFile value) {
        languageFiles.put(value.getLanguage(), value);
    }

    @Override
    public void unregister(LanguageFile value) {
        languageFiles.remove(value.getLanguage());
    }
}
