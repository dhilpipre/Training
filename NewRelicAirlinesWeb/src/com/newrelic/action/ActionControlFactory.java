package com.newrelic.action;


public class ActionControlFactory {

	private java.util.Hashtable<String,ActionControl> actionControls = new java.util.Hashtable<String, ActionControl>();
	
	public ActionControl getAction(String classname, ClassLoader loader) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		ActionControl action = actionControls.get(classname);
		if(action == null) {
			Class<?> cl = loader.loadClass(classname);
			action = (ActionControl) cl.newInstance();
			actionControls.put(classname, action);
		}
		
		return action;
	}
}
