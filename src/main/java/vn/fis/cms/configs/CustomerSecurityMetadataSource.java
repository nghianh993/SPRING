package vn.fis.cms.configs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.security.access.SecurityConfig;

import vn.fis.cms.domain.Action;
import vn.fis.cms.domain.Permission;
import vn.fis.cms.services.IActionService;

public class CustomerSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Map<String, List<ConfigAttribute>> resourceMap = null;
    private PathMatcher pathMatcher = new AntPathMatcher();
    
    private IActionService actionService;
    
    public CustomerSecurityMetadataSource(IActionService actionService) {
        super();
        this.actionService = actionService;
        resourceMap = loadResourceMatchAuthority();
    }
    
    private Map<String, List<ConfigAttribute>> loadResourceMatchAuthority() {
        List<Action> urlRoles = actionService.GetListAction();
        Map<String, List<ConfigAttribute>> map = new HashMap<>();
        if (urlRoles != null && !urlRoles.isEmpty()) {
            for (Action urlRole : urlRoles) {
                List<Permission> lstpermission = new ArrayList<Permission>(urlRole.getPermissions());
                if (lstpermission != null && lstpermission.size() > 0) {
                    List<ConfigAttribute> list = new ArrayList<>();
                    ConfigAttribute config;
                    for (Permission permission : lstpermission) {
                        config = new SecurityConfig(permission.getCode());
                        list.add(config);
                    }
                    map.put(urlRole.getLink(), list);
                }
            }
        } else {
        }
        return map;
    }
    
	public List<ConfigAttribute> getAttributes(Object object) {
		
		String url = ((FilterInvocation) object).getRequestUrl();
        if (resourceMap == null) {
            resourceMap = loadResourceMatchAuthority();
        }

        for (Map.Entry<String, List<ConfigAttribute>> resURL : resourceMap.entrySet()) {
            if (pathMatcher.match(resURL.getKey(), url)) {
            	System.out.println("URL :" + "'" + url + "'");
            	System.out.println("Danh sach permission URL: " + resURL.getValue());
                return resURL.getValue();                
            }
        }
        return resourceMap.get(url);
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
}