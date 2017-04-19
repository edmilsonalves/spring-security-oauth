/**
 * 
 */
package br.com.makersweb.oauth.web.config;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import br.com.makersweb.oauth.web.entity.Usuario;
import br.com.makersweb.oauth.web.exception.UserNotActivatedException;
import br.com.makersweb.oauth.web.repository.IUsuarioRepository;

/**
 *
 * @author anderson.aristides
 *
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("Authenticating {}", username);
		
		Usuario user = usuarioRepository.findByEmail(username);
		
		if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("Usuário " + username + " Não foi encontrado no banco de dados.");
        } else if (!user.getEnabled()) {
            throw new UserNotActivatedException("Usuário " + username + " Não está ativo.");
        }
		
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		user.getPerfil().getRegraAcesso().forEach(r -> {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(r.getNome());
			grantedAuthorities.add(grantedAuthority);
		});
		
		return new User(user.getEmail(), user.getAutenticacao().getPassword(), grantedAuthorities);
	}

}
