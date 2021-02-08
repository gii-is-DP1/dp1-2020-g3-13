package org.springframework.samples.petclinic.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/resources/**", "/webjars/**", "/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/", "/oups").permitAll()
				.antMatchers("/lugaresRealizacion/**").hasAnyAuthority("admin")
				.antMatchers("/admins/**").hasAnyAuthority("admin")
				.antMatchers("/peticion/new").anonymous()
				.antMatchers("/peticion/**").hasAnyAuthority("admin")
				.antMatchers("/usuarios/myprofile/**").hasAnyAuthority("cliente", "organizacion")
				.antMatchers("/alquilerEspacio/**").hasAnyAuthority("organizacion")
				.antMatchers("/usuarios/**").hasAnyAuthority("admin")
				.antMatchers("/clientes/new").anonymous()
				.antMatchers("/consultas/{id_evento}/nuevo").hasAnyAuthority("cliente")
				.antMatchers("/clientes/**").hasAnyAuthority("admin")
				.antMatchers("/consultas/organizacion/misConsultas").hasAnyAuthority("organizacion")
				.antMatchers("/consultas/organizacion/misConsultas/*").hasAnyAuthority("organizacion")
				.antMatchers("/consultas/cliente/misConsultas/*").hasAnyAuthority("cliente")
				.antMatchers("/consultas/cliente/misConsultas").hasAnyAuthority("cliente")
				.antMatchers("/organizaciones/**").hasAnyAuthority("admin")
				.antMatchers("/eventos/nuevo/**").hasAnyAuthority("organizacion","admin")
				.antMatchers("/eventos/{evento_id}/actividades/{actividad_id}/**").hasAnyAuthority("organizacion", "admin")
				.antMatchers("/eventos/{evento_id}/sponsors/**").hasAnyAuthority("organizacion")
				.antMatchers("/eventos/{eventoId}/{tipoEntradasId}/entrada").hasAnyAuthority("cliente","admin")
				.antMatchers("/eventos/{eventoId}/anadirEventosFavoritos").hasAnyAuthority("cliente")
				.antMatchers("/eventos/{eventoId}/hacerPublico").hasAnyAuthority("organizacion")
				.antMatchers("/eventos/{eventoId}/delete").hasAnyAuthority("organizacion","admin")
				.antMatchers("/eventos/{eventoId}/tipoEntradas/nuevo").hasAnyAuthority("organizacion","admin")
				// .antMatchers("/eventos").hasAnyAuthority("cliente","admin", "organizacion")
				.antMatchers("/eventos/{evento_id}").hasAnyAuthority("cliente", "organizacion", "admin")
				.antMatchers("/eventos").permitAll()
				.antMatchers("/tipoentradas/**").permitAll()
				.antMatchers("/carrito/organizacion/**").hasAnyAuthority("organizacion")	
				.antMatchers("/carrito/org/finalizarCompra").hasAnyAuthority("organizacion")
				.antMatchers("/carrito/finalizarCompra").hasAnyAuthority("cliente")
				.antMatchers("/carrito/cliente/**").hasAnyAuthority("cliente")
				.antMatchers("/actividades/**").hasAnyAuthority("organizacion")
				.antMatchers("/facturas/**").hasAnyAuthority("organizacion","cliente")
				.anyRequest().denyAll().and().formLogin()
				/* .loginPage("/login") */
				.failureUrl("/login-error").and().logout().logoutSuccessUrl("/");
		// Configuración para que funcione la consola de administración
		// de la BD H2 (deshabilitar las cabeceras de protección contra
		// ataques de tipo csrf y habilitar los framesets si su contenido
		// se sirve desde esta misma página.
		http.csrf().ignoringAntMatchers("/h2-console/**");
		http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"select nombre_usuario,password,enabled " + "from usuarios " + "where nombre_usuario = ?")
				.authoritiesByUsernameQuery("select usuario, autoridad " + "from autoridades " + "where usuario = ?")
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();
		return encoder;
	}

}
