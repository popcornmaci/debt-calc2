package hu.popcornmaci.service.api;

public interface RegService {
	public void register(String fullName,String username, String passw) throws RegException;
	
}
