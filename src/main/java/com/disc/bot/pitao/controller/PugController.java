package com.disc.bot.pitao.controller;

import org.springframework.stereotype.Component;


import com.disc.bot.pitao.service.PugService;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


@Component
public class PugController {
	
	private PugService pugService;
	
	public void redirectEvent(MessageReceivedEvent event) {
		
		if (event.getMessage().getContentRaw().startsWith(".pug start")) {

			if (pugService == null) {
				pugService = PugService.getInstance();
			}
			
			pugService.startPug(event);
		}
		
		if (event.getMessage().getContentRaw().startsWith(".pug pick"))	{
			
			if (pugService == null) {
				pugService = PugService.getInstance();
			}
			
			pugService.pickPlayer(event);
		}
		
		if (event.getMessage().getContentRaw().startsWith(".pug ban")) {
			
			if (pugService == null) {
				pugService = PugService.getInstance();
			}
			
			pugService.banMap(event);
		}

		
	}
	
	
}
