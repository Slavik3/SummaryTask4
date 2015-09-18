package ua.nure.kozlov.SummaryTask4.db;

import ua.nure.kozlov.SummaryTask4.db.entity.User;

/**
 * 
 * @author Vyacheslav Kozlov
 *
 */
public enum Role {

	ADMIN, CLIENT;
	
	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	

}
