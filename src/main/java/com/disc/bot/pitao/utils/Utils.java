package com.disc.bot.pitao.utils;

import java.util.List;

import net.dv8tion.jda.api.entities.Member;

public class Utils {
	
	public String returnMembersUsername (List<Member> lista) {
		
		if (lista.size() == 0) {
			return "There are no players left";
		}
		
		String msg = "";
		
		for (Member member : lista) {
			msg += "" + member.getUser().getName() + "\n";
		}
		
		return msg;
	}
	
	public String returnPlayersInTeam (List<Member> lista) {
		
		String msg = "";
		
		for (Member member : lista) {
			msg += "" + member.getUser().getName() + "\n";
		}
		
		return msg;
	}
	
	public String returnPlayersLeftToBePicked (List<Member> lista) {
		
		String msg = "";
		
		for (Member member : lista) {
			msg += "" + member.getUser().getName() + "\n";
		}
		
		return msg;
	}
	
	public String returnMapsInList (List<String> lista) {
		
		if (lista.size() == 0) {
			return "There are maps left.";
		}
		
		String msg = "";
		
		for (String map : lista) {
			msg += "" + map + "\n";
		}
		
		return msg;
	}
}
