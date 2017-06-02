package co.siten.myvtg.configuration;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * 
 * @author thomc
 *
 */ 
public class SessionListener implements HttpSessionListener {
	
 
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(30000);
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
    }
   
}