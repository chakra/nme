package com.nme.userservice.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Roland Krüger
 */
@Component
@Profile("DEV")
public class DevelopmentProfileConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    private final static Logger LOG = LoggerFactory.getLogger(DevelopmentProfileConfiguration.class);

    //public final static Authority adminAuthority, userAuthority, developerAuthority;
    //public final static Nmeuser alice, bob, charly, user;

    static {
        LOG.info("Creating test data: authorities 'admin', 'user', 'developer'");

        /*adminAuthority = new Authority("admin", "The admin role");
        userAuthority = new Authority("user", "The user role");
        developerAuthority = new Authority("developer", "The developer role");


        LOG.info("Creating test data: users 'alice', 'bob', 'charly'");
        alice = new Nmeuser("alice");
        alice.setUnencryptedPassword("alice");
        alice.createRegistrationConfirmationToken();
        alice.setEmail("alice@example.com");
        alice.addAuthority(adminAuthority);

        bob = new Nmeuser("bob");
        bob.setUnencryptedPassword("bob");
        bob.addAuthority(developerAuthority);
        bob.addAuthority(userAuthority);

        charly = new Nmeuser("charly");
        charly.setLastLogin(LocalDateTime.now());
        charly.setUnencryptedPassword("charly");

        user = new Nmeuser("user");
        user.setLastLogin(LocalDateTime.now());
        user.setUnencryptedPassword("password");
        user.addAuthority(userAuthority);*/
    }

   /* @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;
*/
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        saveAuthorities();
        saveUsers();
    }

    private void saveAuthorities() {
        //authorityRepository.save(Arrays.asList(adminAuthority, userAuthority, developerAuthority));
    }

    private void saveUsers() {
        //final List<Nmeuser> users = Arrays.asList(alice, bob, charly);
        //userRepository.save(users);
        //LOG.info("Added users {}", users);
    }
}
