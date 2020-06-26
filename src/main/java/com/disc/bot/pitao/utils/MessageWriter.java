package com.disc.bot.pitao.utils;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;

public class MessageWriter {
	
	public static final String ICON_URL = "https://a-static.mlcdn.com.br/1500x1500/pitao-para-madeira-bicromatizado-16x40mm-com-4-pecas-metropac/ceccasaeconstrucao/8f710ea380110916aecb0b3dfa8c32bc/0489d580b123b25e56f1dd4683172892.jpg";
	public static final String GOOGLE_URL = "https://www.google.com/";
	
	public EmbedBuilder writeEmbeded (String msg) {
		
		EmbedBuilder embeded = new EmbedBuilder().setAuthor("Pitao Bot").setDescription(msg);
		
		return embeded;
	}
	
	public EmbedBuilder writeTeamEmbed (String msg, String teamName) {
		
		EmbedBuilder embeded = new EmbedBuilder();
		
		if (teamName.equalsIgnoreCase("Team 1")) {
			embeded = new EmbedBuilder()
					.setAuthor("Pitao Bot", GOOGLE_URL, ICON_URL)
					.setTitle(teamName)
					.setDescription(msg)
					.setColor(Color.CYAN);
		}else {
			embeded = new EmbedBuilder()
					.setAuthor("Pitao Bot", GOOGLE_URL, ICON_URL)
					.setTitle(teamName)
					.setDescription(msg)
					.setColor(Color.RED);
		}
		
		return embeded;
	}
	
	public EmbedBuilder writePlayersLeft (String msg) {
		
		EmbedBuilder embeded = new EmbedBuilder()
				.setAuthor("Pitao Bot", GOOGLE_URL, ICON_URL)
				.setTitle("Players Left to be picked")
				.setDescription(msg)
				.setColor(Color.GREEN);
		
		return embeded;
	}
	
	public EmbedBuilder writeMap (String url, String map) {
		
		EmbedBuilder embeded = new EmbedBuilder()
				.setAuthor("Pitao Bot", GOOGLE_URL, ICON_URL)
				.setTitle("Map")
				.setDescription(map)
				.setImage(url)
				.setColor(Color.MAGENTA);
		
		return embeded;
	}
	
}