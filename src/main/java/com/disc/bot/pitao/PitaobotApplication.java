package com.disc.bot.pitao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;

import com.disc.bot.pitao.bot.PitaoBot;

@SpringBootConfiguration
public class PitaobotApplication {
	
	public static void main(String[] args){
		SpringApplication.run(PitaoBot.class, args);
	}

}