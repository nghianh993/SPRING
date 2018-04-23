package vn.fis.cms.configs;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class CustomerAccessDecisionManager implements AccessDecisionManager {
	
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		if (configAttributes == null) {
            return;
        }
		
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();    
		
		while(iterator.hasNext()) {    
            ConfigAttribute configAttribute = iterator.next();
            String functionId = configAttribute.getAttribute();
            for(GrantedAuthority ga : authentication.getAuthorities()) {    
                if(functionId.equals(ga.getAuthority())) {    
                    return;
                }
            }
        }
		
        throw new AccessDeniedException("Cannot Access!");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
	
}