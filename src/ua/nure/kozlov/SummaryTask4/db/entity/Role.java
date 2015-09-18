package ua.nure.kozlov.SummaryTask4.db.entity;

/**
 * User role type.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public enum Role {

	ADMIN, DOCTOR, NURSE;
	
	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
	

}
