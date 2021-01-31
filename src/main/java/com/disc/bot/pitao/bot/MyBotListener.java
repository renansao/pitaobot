package com.disc.bot.pitao.bot;

import java.util.Random;

import com.disc.bot.pitao.DAOImpl.CauleDaoImpl;
import com.disc.bot.pitao.controller.MessageController;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MyBotListener extends ListenerAdapter{
	
	MessageController messageController;
	CauleDaoImpl cauleDAOImpl;
	Random random;
	
	public MyBotListener() {
		messageController = new MessageController();
		cauleDAOImpl = new CauleDaoImpl();
		random = new Random();
	}
	
	private static final String PREFIX = ".";
	
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
    	
        if (event.getAuthor().isBot()) return;
        
        Message message = event.getMessage();
        String content = message.getContentRaw();
        
        if (content.substring(0).startsWith(PREFIX)) {
        	
        	messageController.redirectMessage(event);
        }
    }
}
