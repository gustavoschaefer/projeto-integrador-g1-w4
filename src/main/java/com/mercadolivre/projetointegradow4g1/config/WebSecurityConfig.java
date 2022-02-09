package com.mercadolivre.projetointegradow4g1.config;


import com.mercadolivre.projetointegradow4g1.repositories.UserRepository;
import com.mercadolivre.projetointegradow4g1.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    //autenticacao
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    //autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2/**").permitAll()
                .antMatchers( HttpMethod.POST,"/auth").permitAll()
                .antMatchers("/**").hasAnyAuthority("ADMIN")
                
                .antMatchers(HttpMethod.POST, "/registroestoque/salvar").hasAnyAuthority("REPRESENTANTE")
                .antMatchers(HttpMethod.POST, "/destino/salvar").hasAnyAuthority("REPRESENTANTE")
                .antMatchers(HttpMethod.GET, "/destino/**").hasAnyAuthority("REPRESENTANTE")
                .antMatchers(HttpMethod.PUT, "/registroestoque/**").hasAnyAuthority("REPRESENTANTE")
                .antMatchers(HttpMethod.GET, "/registroestoque/**").hasAnyAuthority("REPRESENTANTE")
                
                .antMatchers(HttpMethod.GET, "/quantidade/**").hasAnyAuthority("REPRESENTANTE")
                
                .antMatchers(HttpMethod.GET, "setores/buscalote/**").hasAnyAuthority("REPRESENTANTE")
                
                .antMatchers(HttpMethod.GET, "/produto/obtem**").hasAnyAuthority("CUSTOMER")
                
                .antMatchers(HttpMethod.POST, "/carrinho/salvar").hasAnyAuthority("CUSTOMER")
                .antMatchers(HttpMethod.PUT, "/carrinho/**").hasAnyAuthority("CUSTOMER")
                .antMatchers(HttpMethod.GET, "/carrinho**").hasAnyAuthority("CUSTOMER")
                
                .antMatchers(HttpMethod.GET, "/desconto/*").hasAnyAuthority("SELLER")
                .antMatchers(HttpMethod.POST, "/desconto/*").hasAnyAuthority("SELLER")
                
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, repository), UsernamePasswordAuthenticationFilter.class);

                http.headers().frameOptions().disable();
    }

    //autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(autenticacaoService).passwordEncoder(encoder);
    }


}
