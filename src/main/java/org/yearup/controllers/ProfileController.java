package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("profile")
@CrossOrigin
public class ProfileController {
    private ProfileDao profileDao;
    private UserDao userDao;

    public ProfileController(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @GetMapping()
    @PreAuthorize("permitAll()")
    public Profile getById(Principal principal) {
        User user = userDao.getByUserName(principal.getName());
        System.out.println(user);

        try {
            var profile = profileDao.getByUserId(user.getId());

            if (profile == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            return profile;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops...our bas.");
        }

    }

    @PutMapping()
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void update(@RequestBody Profile profile, Principal principal) {
        User user = userDao.getByUserName(principal.getName());

        var profile2 = profileDao.getByUserId(user.getId());

        Profile existingProfile = profileDao.getByUserId(user.getId());
        if (profile2 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        profileDao.update(existingProfile.getUserId(), profile);
    }
}




