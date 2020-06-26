package com.disc.bot.pitao.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("PugStatus")
public class PugStatus {
	
	private String pugId;
	
	private String serverId;
	
	private boolean isActive;

	public String getPugId() {
		return pugId;
	}

	public void setPugId(String pugId) {
		this.pugId = pugId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}