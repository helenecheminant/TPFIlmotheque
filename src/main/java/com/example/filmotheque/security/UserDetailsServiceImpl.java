package com.example.filmotheque.security;


import com.example.filmotheque.dal.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.filmotheque.bo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component("userDetailsService")
// on fait notre bean qui était avant dans le WebSecurityConfig
public class UserDetailsServiceImpl implements UserDetailsService {

    //====== la méthode qui utilise les user en database===============
    private UserDAO userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserFromDB(username);
        System.out.println("depuis la DAO USER---------->");
        System.out.println(user);

        if (user == null) {
            throw new UsernameNotFoundException("ce pseudo n'existe pas : " + username);
        }
       return new MembreUserDetail(user);
    }

};



    // ================ GERER LES USERS EN DUR ===================================
 /*   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("coucou depuis le UserDetail methode loadUserByUserName");
        UserDetails userBob = null;
        if (username.equals("bob")) {
            userBob = createBob();
        } else {
            throw new UsernameNotFoundException(username);
        }
        System.out.println("coucou");
        return userBob;
    }


    //on créé un nouveau User avec les permissions dedans
    private UserDetails createBob() {
        UserDetails bob = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> permissions = new ArrayList<>();
                permissions.add(() -> "ROLE_USER");
                return permissions;
            }
            @Override
            public String getPassword() {
                return "azerty";
            }
            @Override
            public String getUsername() {
                return "bob";
            }
            @Override
            public boolean isAccountNonExpired() {
                return true;
            }
            @Override
            public boolean isAccountNonLocked() {
                return true;
            }
            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }
            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        return bob;
    }
*/



