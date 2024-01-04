package bbl.agent.banking.security;

import bbl.agent.banking.entities.Role;
import bbl.agent.banking.entities.Users;
import bbl.agent.banking.repositories.RoleRepository;
import bbl.agent.banking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          Users user = userRepository.findByUsername(username)
                 .orElseThrow(() ->
                         new UsernameNotFoundException("User not found with username or email: "+ username));


        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roleList = roleRepository.findByUserId(String.valueOf(user.getId()));
        if(!roleList.isEmpty()){
            for(Role role :roleList){
                authorities.add(new SimpleGrantedAuthority(role.getUserRoleName()));
            }
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }*/

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users userEntity = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: "+ username));

        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roleList = roleRepository.findByUserId(String.valueOf(userEntity.getId()));
        if(!roleList.isEmpty()){
            for(Role role :roleList){
                authorities.add(new SimpleGrantedAuthority(role.getUserRoleName()));
            }
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity.getUsername(),
                userEntity.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
        customUserDetails.setUserId(userEntity.getId());
        customUserDetails.setNameEn(userEntity.getNameEn());
        return customUserDetails;

    }
}
