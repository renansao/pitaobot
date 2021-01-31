package com.disc.bot.pitao.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.disc.bot.pitao.domain.PugDomain;
import com.disc.bot.pitao.utils.MessageWriter;
import com.disc.bot.pitao.utils.PugUtils;
import com.disc.bot.pitao.utils.Utils;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Service
@Configurable
public class PugService{
	
	private PugDomain pugCached = new PugDomain();
	
	private static PugService instancia;
	
	private PugService () {
	}
	
	public static synchronized PugService getInstance() {
		if(instancia == null) {
			instancia = new PugService();
		}
		return instancia;
	}
	
	public void startPug(MessageReceivedEvent event) {
		
		if (this.pugCached.isStarted() == true) {
			event.getChannel().sendMessage("A PUG is already running").queue();
			return;
		}
		
		List<Member> membersInVoiceChannel = new ArrayList<Member>();
		membersInVoiceChannel = event.getMember().getVoiceState().getChannel().getMembers();
		
		//if (membersInVoiceChannel.size() != 10) {
		//	event.getChannel().sendMessage("Number of players is invalid.").queue();
		//	return;
		//}
		
		PugUtils pugUtils = new PugUtils();
		
		List<Member> captainsInPug = new ArrayList<Member>();
		
		for (Member member : membersInVoiceChannel) {
			for (String possibleCaptain : pugUtils.getCaptainsId()) {
				if(member.getId().equalsIgnoreCase(possibleCaptain)) {
					captainsInPug.add(member);
				}
			}
		}
		
		if (captainsInPug.size() < 2) {
			event.getChannel().sendMessage("Not enough captains.").queue();
		}

		Collections.shuffle(captainsInPug);
		
		//Prepare list of available picks
		List<Member> availablePicks = new ArrayList<Member>();
		for (Member member : membersInVoiceChannel) {
			if ((!member.equals(captainsInPug.get(0)) && ((!member.equals(captainsInPug.get(1)))))) {
				availablePicks.add(member);
			}
		}
		
		System.out.println("captains in pug: " + captainsInPug);
		System.out.println("members in channel: " + membersInVoiceChannel);
		
		PugDomain pugDomain = new PugDomain();
		pugDomain.setCaptain1(captainsInPug.get(0));
		pugDomain.setCaptain2(captainsInPug.get(1));
		pugDomain.getTeam1().add(captainsInPug.get(0));
		pugDomain.getTeam2().add(captainsInPug.get(1));
		pugDomain.setMaps(pugUtils.getMapPool());
		pugDomain.setPlayers(availablePicks);
		pugDomain.setStarted(true);
		
		//Prepare PickOrder
		//PICK PLAYERS
		HashMap<Integer, Member> pickOrder = new HashMap<Integer, Member>();
		pickOrder.put(1, pugDomain.getCaptain1());
		pickOrder.put(2, pugDomain.getCaptain2());
		pickOrder.put(3, pugDomain.getCaptain2());
		pickOrder.put(4, pugDomain.getCaptain1());
		pickOrder.put(5, pugDomain.getCaptain1());
		pickOrder.put(6, pugDomain.getCaptain2());
		pickOrder.put(7, pugDomain.getCaptain2());
		pickOrder.put(8, pugDomain.getCaptain1());
		//MAP VETO
		pickOrder.put(9, pugDomain.getCaptain1());
		pickOrder.put(10, pugDomain.getCaptain2());
		pickOrder.put(11, pugDomain.getCaptain1());
		pickOrder.put(12, pugDomain.getCaptain2());
		pickOrder.put(13, pugDomain.getCaptain1());
		pickOrder.put(14, pugDomain.getCaptain2());
		//
		
		pugDomain.setPickOrder(pickOrder);
		
		this.pugCached = pugDomain;
		
		event.getChannel().sendMessage("Captain 1 : " + pugDomain.getCaptain1().getUser().getAsMention()).queue();
		event.getChannel().sendMessage("Captain 2 : " + pugDomain.getCaptain2().getUser().getAsMention()).queue();
		
		MessageWriter msgWritter = new MessageWriter();
		Utils utils = new Utils();
		
		event.getChannel().sendMessage(msgWritter.writeTeamEmbed(utils.returnPlayersInTeam(pugCached.getTeam1()), "Team 1").build()).queue();
		event.getChannel().sendMessage(msgWritter.writeTeamEmbed(utils.returnPlayersInTeam(pugCached.getTeam2()), "Team 2").build()).queue();
		event.getChannel().sendMessage(msgWritter.writePlayersLeft(utils.returnMembersUsername(pugCached.getPlayers())).build()).queue();
		
		String userPick = "";
		userPick += this.pugCached.getPickOrder().get(this.pugCached.getCurrentPick()).getAsMention();
		userPick += " it's your pick!";
		event.getChannel().sendMessage(userPick).queue();
		
		event.getChannel().sendMessage("Use '.pug pick {player} to pick a player.").queue();
	}
	
	public void pickPlayer (MessageReceivedEvent event) {
		
		if (this.pugCached.isStarted() == false) {
			event.getChannel().sendMessage("There is no PUG running.").queue();
			return;
		}
		
		if (event.getMember() != pugCached.getPickOrder().get(pugCached.getCurrentPick())) {
			event.getChannel().sendMessage("You are not the captain").queue();
			return;
		}
		
		if (pugCached.getCurrentPick() > 8) {
			event.getChannel().sendMessage("The player pick phase has ended.").queue();
			event.getChannel().sendMessage("Use '.pug ban {map}' to ban a map.").queue();
			return;
		}
		
		System.out.println(event.getMessage().getContentRaw().substring(10));
		
		for (Member member : pugCached.getPlayers()) {
			if (event.getMessage().getContentRaw().substring(10).equalsIgnoreCase(member.getUser().getName())) {
				
				String msg = "";
				msg += event.getMember().getUser().getName();
				msg += " picked ";
				msg += member.getUser().getName();
				msg += ".";
				
				MessageWriter msgWritter = new MessageWriter();
				Utils utils = new Utils();
				
				event.getChannel().sendMessage(msgWritter.writeEmbeded(msg).build()).queue();
				
				//event.getChannel().sendMessage(msg).queue();
				
				if (event.getMember().equals(pugCached.getCaptain1())) {
					pugCached.getTeam1().add(member);
				}else {
					pugCached.getTeam2().add(member);
				}
				
				event.getChannel().sendMessage(msgWritter.writeTeamEmbed(utils.returnPlayersInTeam(pugCached.getTeam1()), "Team 1").build()).queue();
				event.getChannel().sendMessage(msgWritter.writeTeamEmbed(utils.returnPlayersInTeam(pugCached.getTeam2()), "Team 2").build()).queue();
				this.pugCached.getPlayers().remove(member);
				
				//event.getChannel().sendMessage("Players Left to be picked").queue();
				//event.getChannel().sendMessage(utils.returnMembersUsername(pugCached.getPlayers())).queue();
				
				event.getChannel().sendMessage(msgWritter.writePlayersLeft(utils.returnMembersUsername(pugCached.getPlayers())).build()).queue();
				
				//this.pugCached.setCurrentPick(pugCached.getCurrentPick() + 1);
				this.pugCached.setCurrentPick(9);
				
				String userPick = "";
				userPick += this.pugCached.getPickOrder().get(this.pugCached.getCurrentPick()).getAsMention();
				userPick += " it's your pick!";
				event.getChannel().sendMessage(userPick).queue();
				
				if (this.pugCached.getCurrentPick() == 9) {
					event.getChannel().sendMessage("Entering ban phase.").queue();
					event.getChannel().sendMessage("Use '.pug ban {map}' to ban a map.").queue();
					
					event.getChannel().sendMessage(msgWritter.writeTeamEmbed(utils.returnMapsInList(this.pugCached.getMaps()), "Maps left to be banned").build()).queue();
					
					String userBan = "";
					userBan += this.pugCached.getPickOrder().get(this.pugCached.getCurrentPick()).getAsMention();
					userBan += " it's your ban!";
					event.getChannel().sendMessage(userBan).queue();
				}
				
				return;
			}
		}
		event.getChannel().sendMessage("Player not valid.").queue();
		return;
	}
	
	public void banMap (MessageReceivedEvent event) {
		
		if (this.pugCached.isStarted() == false) {
			event.getChannel().sendMessage("There is no PUG running.").queue();
			return;
		}
		
		if (event.getMember() != pugCached.getPickOrder().get(pugCached.getCurrentPick())) {
			event.getChannel().sendMessage("You are not the captain").queue();
			return;
		}
		
		if (pugCached.getCurrentPick() < 9) {
			event.getChannel().sendMessage("The pug is in pick phase.").queue();
			event.getChannel().sendMessage("Use '.pug pick {player}' to pick a player.").queue();
			return;
		}
		
		for (String map : this.pugCached.getMaps()) {
			if (map.equalsIgnoreCase(event.getMessage().getContentRaw().substring(9))) {
				
				String msg = "";
				msg += event.getMember().getUser().getName();
				msg += " banned ";
				msg += map;
				msg += ".";
				
				MessageWriter msgWritter = new MessageWriter();
				Utils utils = new Utils();
				
				event.getChannel().sendMessage(msgWritter.writeEmbeded(msg).build()).queue();
				this.pugCached.getMaps().remove(map);
				
				
				
				if (this.pugCached.getCurrentPick() != 14) {
					event.getChannel().sendMessage(msgWritter.writeTeamEmbed(utils.returnMapsInList(this.pugCached.getMaps()), "Maps left to be banned").build()).queue();
					
					String userBan = "";
					userBan += this.pugCached.getPickOrder().get(this.pugCached.getCurrentPick() + 1).getAsMention();
					userBan += " it's your ban!";
					event.getChannel().sendMessage(userBan).queue();
				}
				
				this.pugCached.setCurrentPick(pugCached.getCurrentPick() + 1);
				
				
				if (this.pugCached.getCurrentPick() == 15) {
					event.getChannel().sendMessage("The PUG is about to begin.").queue();
					event.getChannel().sendMessage("Teams are:").queue();
					event.getChannel().sendMessage(msgWritter.writeTeamEmbed(utils.returnPlayersInTeam(pugCached.getTeam1()), "Team 1").build()).queue();
					event.getChannel().sendMessage(msgWritter.writeTeamEmbed(utils.returnPlayersInTeam(pugCached.getTeam2()), "Team 2").build()).queue();
					
					//event.getChannel().sendMessage("The PUG is going to be played at:").queue();
					
					PugUtils pugUtils = new PugUtils();
					event.getChannel().sendMessage(msgWritter.writeMap(pugUtils.getMapUrl().get(this.pugCached.getMaps().get(0)), this.pugCached.getMaps().get(0)).build()).queue();;
					
					event.getChannel().sendMessage("Connect to server: connect 131.196.199.238:27025; password mix159").queue();
				}
				return;
			}
		}
		event.getChannel().sendMessage("Map not valid.").queue();
		return;
		
	}
}
