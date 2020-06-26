package com.disc.bot.pitao.bot;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

import org.springframework.stereotype.Component;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@Component
public class PitaoBot {
	
	private static String SECRET_TOKEN = "NzI1MTc0MDcwNDU0NTE3ODAx.XvLGUQ.M1AQGmkSErFvYzBMTuHIzqAjlTo";
    
    public PitaoBot() {
    	this.init();
    }
    
    public void init() {
    	
	    try {
	    	JDA jda = new JDABuilder(SECRET_TOKEN).addEventListeners(new MyBotListener()).build();
	    } catch (LoginException e) {
	        e.printStackTrace();
	    
	    }
    }
}