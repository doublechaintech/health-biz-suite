package com.doublechaintech.health;

public class Action {
	public static final String CHANGE_REQUEST_TYPE="changerequesttype";
	public static final String CUSTOM_ACTION="custom";
	
	public Action asCustomGroup() {
		this.setActionGroup(CUSTOM_ACTION);
		return this;
	}
	public Action asChangeRequestGroup() {
		this.setActionGroup(CHANGE_REQUEST_TYPE);
		return this;
	}
	
	public String[] specialActionTypes() {
		return new String[] {Action.CUSTOM_ACTION,Action.CHANGE_REQUEST_TYPE};
	}

	protected String actionName;
	protected String actionPath;
	protected String managerBeanName;
	protected String actionKey;
	protected String localeKey;
	protected String actionGroup;
	protected String actionLevel;
	protected String actionIcon;
	protected String actionId;
	
	public String getActionIcon() {
		return actionIcon;
	}

	public void setActionIcon(String actionIcon) {
		this.actionIcon = actionIcon;
	}
	
	
	public String getActionId() {
		if(actionId==null) {
			actionId = java.util.UUID.randomUUID().toString();
		}
		return actionId;
		
	}

	public String getActionGroup() {
		return actionGroup;
	}

	public void setActionGroup(String actionGroup) {
		this.actionGroup = actionGroup;
	}

	public String getActionLevel() {
		return actionLevel;
	}

	public void setActionLevel(String actionLevel) {
		this.actionLevel = actionLevel;
	}

	public String getLocaleKey() {
		return localeKey;
	}

	public void setLocaleKey(String localeKey) {
		this.localeKey = localeKey;
	}

	public String getActionKey() {
		return actionKey;
	}

	public void setActionKey(String actionKey) {
		this.actionKey = actionKey;
	}

	public String getActionPath() {
		return actionPath;
	}

	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}

	public String getManagerBeanName() {
		return managerBeanName;
	}

	public void setManagerBeanName(String managerBeanName) {
		this.managerBeanName = managerBeanName;
	}
	
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Action withActionName(String name){
		this.setActionName(name);
		return this;
		
	}
	public Action withManagerBeanName(String managerBeanName){
		this.setManagerBeanName(managerBeanName);
		return this;
		
	}
	public Action withActionPath(String actionPath){
		this.setActionPath(actionPath);
		return this;
	}
	public Action withActionKey(String actionKey){
		this.setActionKey(actionKey);
		return this;
	}
	public Action withLocaleKey(String localeKey){
		this.setLocaleKey(localeKey);
		return this;
	}
	public Action withActionGroup(String actionGroup){
		this.setActionGroup(actionGroup);
		return this;
	}
	public Action withActionLevel(String actionLevel){
		this.setActionLevel(actionLevel);
		return this;
	}
	
}



