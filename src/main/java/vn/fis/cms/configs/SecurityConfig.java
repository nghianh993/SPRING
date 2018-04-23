package vn.fis.cms.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import vn.fis.cms.services.IPermissionService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private IPermissionService permissionService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/account/login").anonymous()
		.antMatchers("/static/**").permitAll()
		.antMatchers("/notfound").anonymous()
		.antMatchers("/admin/**").authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                FilterInvocationSecurityMetadataSource newSource = new CustomerSecurityMetadataSource(permissionService);
                AccessDecisionManager accessDecisionManager = new CustomerAccessDecisionManager();
                fsi.setSecurityMetadataSource(newSource);
                fsi.setAccessDecisionManager(accessDecisionManager);
                return fsi;
            }
        })
		.antMatchers("/api/**").authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                FilterInvocationSecurityMetadataSource newSource = new CustomerSecurityMetadataSource(permissionService);
                AccessDecisionManager accessDecisionManager = new CustomerAccessDecisionManager();
                fsi.setSecurityMetadataSource(newSource);
                fsi.setAccessDecisionManager(accessDecisionManager);
                return fsi;
            }
        })
		.anyRequest().permitAll()
		.and().formLogin().loginPage("/account/login")
		.defaultSuccessUrl("/admin/home").usernameParameter("email").passwordParameter("password")
		.failureUrl("/account/login?error").and().logout().logoutSuccessUrl("/account/login?logout")
		.and().exceptionHandling().accessDeniedPage("/notfound")
		.and().csrf().ignoringAntMatchers("/api/**");
	}

	/*@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}*/
	
	@Bean
	public Md5PasswordEncoder passwordEncoder() throws Exception {
		return new Md5PasswordEncoder();
	}
}