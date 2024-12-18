package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;

import javax.validation.Valid;

@RestController
@RequestMapping("/profiles")
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
    public Profile getProfile(@PathVariable int id) {
        return profileDao.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));
    }

    @PutMapping("/{id}")
    public Profile updateProfile(@PathVariable int id, @Valid @RequestBody Profile profile) {
        profile.setUserId(id); // Ensure the ID is consistent
        return profileDao.update(profile);
    }

    @PostMapping
    public Profile createProfile(@Valid @RequestBody Profile profile) {
        return profileDao.create(profile);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable int id) {
        profileDao.delete(id);
    }
}
