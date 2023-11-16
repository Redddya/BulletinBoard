package org.finalproject.service.security;

import org.finalproject.domain.Author;
import org.finalproject.security.AuthorDetails;
import org.finalproject.service.impl.AuthorServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorDetailsServiceImpl implements UserDetailsService {
    private final AuthorServiceImpl authorService;

    public AuthorDetailsServiceImpl(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author author = authorService.findByEmail(username);
        return new AuthorDetails(author);
    }
}
