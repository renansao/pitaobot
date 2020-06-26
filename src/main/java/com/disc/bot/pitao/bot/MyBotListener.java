package com.disc.bot.pitao.bot;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.disc.bot.pitao.controller.MessageController;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Component
@Configurable
public class MyBotListener extends ListenerAdapter{
	
	MessageController messageController;
	
	public MyBotListener() {
	}
	
	private static final String PREFIX = ".";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
    	
        if (event.getAuthor().isBot()) return;
        
        MessageReceivedEvent evento = event;
        
        Message message = event.getMessage();
        String content = message.getContentRaw();
        
        if (content.substring(0).startsWith(PREFIX)) {
        	
        	System.out.println("prefixado");
        	
        	System.out.println(event.getAuthor().toString());
        	
        	if (messageController == null) {
        		messageController = new MessageController();
        	}
        	
        	//MessageController messageController = new MessageController();
        	messageController.redirectMessage(evento);
        	
            //MessageChannel channel = event.getChannel();
            //channel.sendMessage("Pong!").queue();
        }
    }
}
