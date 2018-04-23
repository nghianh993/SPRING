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

import vn.fis.cms.domain.Permission;
import vn.fis.cms.services.IPermissionService;

public class CustomerSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Map<String, List<ConfigAttribute>> resourceMap = null;
    private PathMatcher pathMatcher = new AntPathMatcher();
    
    private IPermissionService permissionService;
    
    public CustomerSecurityMetadataSource(IPermissionService permissionService) {
        super();
        this.permissionService = permissionService;
        resourceMap = loadResourceMatchAuthority();
    }
    
    private Map<String, List<ConfigAttribute>> loadResourceMatchAuthority() {
        List<Permission> lstAction = permissionService.GetAllPermission();
        Map<String, List<ConfigAttribute>> map = new HashMap<>();
        if (lstAction != null && !lstAction.isEmpty()) { 
            for (Permission action : lstAction) {
                List<ConfigAttribute> list = new ArrayList<>();
                ConfigAttribute config = new SecurityConfig(action.getCode());
                list.add(config);
                map.put(action.getLink(), list);
            }
            
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