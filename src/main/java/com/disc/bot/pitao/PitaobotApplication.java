package com.disc.bot.pitao;

import javax.security.auth.login.LoginException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.disc.bot.pitao.bot.MyBotListener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@SpringBootApplication
@Configuration
@ComponentScan
public class PitaobotApplication {
	
	public static void main(String[] args) throws LoginException {
		SpringApplication.run(PitaobotApplication.class, args);
	}

}