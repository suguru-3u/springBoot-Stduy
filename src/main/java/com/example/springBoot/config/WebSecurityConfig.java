package com.example.springBoot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.springBoot.dom.JpaUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
    JpaUserDetailsServiceImpl userDetailsService;
	
	
	//パスワードの暗号化
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/image/**",
                "/css/**",
                "/js/**"
                );
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/", "/home","/singup").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login") // 認証処理を起動させるパス
		    .failureUrl("/login") // ログイン処理失敗時の遷移先
		    .successForwardUrl("/user/home") // 認証成功時の遷移先
            .failureUrl("/login?error")
		    .usernameParameter("email")
		    .passwordParameter("password") // ユーザ名(今回はメールアドレスだけど)とパラメータ
			.permitAll()
			.and()
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout**")) // ログアウト処理を起動させるパス
		    .logoutSuccessUrl("/"); // ログアウト完了時のパス
	}

	  @Autowired
	    public void configure(AuthenticationManagerBuilder auth) throws Exception{
	    //UserDetailsServiceを設定してDaoAuthenticationProviderを有効化する
	     auth.userDetailsService(userDetailsService).
	     //上記作成のエンコードを設定しハッシュ化する
	     passwordEncoder(passwordEncoder());
	    }
	
}