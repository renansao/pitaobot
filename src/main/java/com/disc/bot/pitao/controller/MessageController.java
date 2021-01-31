package com.disc.bot.pitao.controller;

import org.springframework.stereotype.Service;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Service
public class MessageController {
	
	private PugController pugController;
	
	public MessageController() {
		pugController = new PugController();
	}

	public void redirectMessage(MessageReceivedEvent event) {
		
		if (event.getMessage().getContentRaw().startsWith(".pug")) {
			pugController.redirectEvent(event);
		}
	}

}