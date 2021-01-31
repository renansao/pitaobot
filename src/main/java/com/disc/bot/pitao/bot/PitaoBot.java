package com.disc.bot.pitao.bot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class PitaoBot {
	
	private static String SECRET_TOKEN = System.getenv("SECRET_TOKEN");
    
    public PitaoBot() {
    	this.init();
    }
    
    public void init() {
    	
	    try {
	    	 JDA jda = JDABuilder.createDefault(SECRET_TOKEN).build();
	    	 jda.addEventListener(new MyBotListener());
	    	
	    } catch (LoginException e) {
	        e.printStackTrace();
	    
	    }
    }
}