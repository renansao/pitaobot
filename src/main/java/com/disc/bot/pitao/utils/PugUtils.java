package com.disc.bot.pitao.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PugUtils {
	
	private List<String> captainsId = new ArrayList<String>();
	
	private List<String> mapPool = new ArrayList<String>();
	
	private HashMap<String, String> mapUrl = new HashMap<String, String>();
	
	public PugUtils() {
		
		captainsId.add("252052954583007234");//pitao
		captainsId.add("206081354364878850");//ologo
		//captainsId.add("205831433237823488");//nariga
		captainsId.add("250997976204312577");//MNG
		//captainsId.add("305441068751781898");//keochi
		
		mapPool.add("de_mirage");
		mapPool.add("de_nuke");
		mapPool.add("de_train");
		mapPool.add("de_inferno");
		mapPool.add("de_vertigo");
		mapPool.add("de_dust2");
		mapPool.add("de_overpass");
		
		mapUrl.put("de_mirage", "https://liquipedia.net/commons/images/f/f3/Csgo_mirage.jpg");
		mapUrl.put("de_nuke", "https://liquipedia.net/commons/images/5/5e/Nuke_csgo.jpg");
		mapUrl.put("de_train", "https://liquipedia.net/commons/images/5/56/Train_csgo.jpg");
		mapUrl.put("de_inferno", "https://liquipedia.net/commons/images/2/2b/De_new_inferno.jpg");
		mapUrl.put("de_vertigo", "https://liquipedia.net/commons/images/5/59/Csgo_de_vertigo_new.jpg");
		mapUrl.put("de_dust2", "https://liquipedia.net/commons/images/1/12/Csgo_dust2.0.jpg");
		mapUrl.put("de_overpass", "https://liquipedia.net/commons/images/0/0f/Csgo_overpass.jpg");
		
	}

	public List<String> getCaptainsId() {
		return captainsId;
	}

	public void setCaptainsId(List<String> captainsId) {
		this.captainsId = captainsId;
	}

	public List<String> getMapPool() {
		return mapPool;
	}

	public void setMapPool(List<String> mapPool) {
		this.mapPool = mapPool;
	}

	public HashMap<String, String> getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(HashMap<String, String> mapUrl) {
		this.mapUrl = mapUrl;
	}
	
}
