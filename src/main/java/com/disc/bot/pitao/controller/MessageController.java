package com.disc.bot.pitao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Service
public class MessageController {
	
	private PugController pugController;

	public void redirectMessage(MessageReceivedEvent event) {
		
		if (event.getMessage().getContentRaw().startsWith(".pug")) {
			
			if(pugController == null) {
				pugController = new PugController();
			}
			
			pugController.redirectEvent(event);
		}
	}

}