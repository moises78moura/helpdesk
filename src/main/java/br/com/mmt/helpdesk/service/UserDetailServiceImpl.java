package br.com.mmt.helpdesk.service;

import br.com.mmt.helpdesk.domain.Pessoa;
import br.com.mmt.helpdesk.domain.repository.PessoaRepository;
import br.com.mmt.helpdesk.security.UserSpringSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final PessoaRepository repository;

    public UserDetailServiceImpl(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Pessoa> usuario = repository.findByEmail(email);
        if(usuario.isPresent()){
            return new UserSpringSecurity(usuario.get().getId(),
                    usuario.get().getEmail(),
                    usuario.get().getSenha(),
                    usuario.get().getPerfis());
        }
        throw new UsernameNotFoundException(email);
    }
}
