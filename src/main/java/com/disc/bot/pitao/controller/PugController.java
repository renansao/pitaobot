package com.disc.bot.pitao.controller;

import org.springframework.stereotype.Component;


import com.disc.bot.pitao.service.PugService;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


@Component
public class PugController {
	
	private PugService pugService;
	
	public PugController () {
		pugService = PugService.getInstance();
	}
	
	public void redirectEvent(MessageReceivedEvent event) {
		
		if (event.getMessage().getContentRaw().startsWith(".pug start")) {
			pugService.startPug(event);
		}
		
		if (event.getMessage().getContentRaw().startsWith(".pug pick"))	{
			pugService.pickPlayer(event);
		}
		
		if (event.getMessage().getContentRaw().startsWith(".pug ban")) {
			pugService.banMap(event);
		}
		
	}
	
}
