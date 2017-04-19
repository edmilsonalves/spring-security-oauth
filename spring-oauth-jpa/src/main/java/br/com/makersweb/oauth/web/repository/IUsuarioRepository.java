/**
 * 
 */
package br.com.makersweb.oauth.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.makersweb.oauth.web.entity.Usuario;

/**
 *
 * @author anderson.aristides
 *
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByEmail(String email);

}
