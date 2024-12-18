package org.yearup.data;


import org.yearup.models.Profile;

import javax.validation.Valid;
import java.util.Optional;

public interface ProfileDao
{
    Profile create(Profile profile);

    Optional<Profile> getById(int userId);

    // Update an existing profile
    Profile update(@Valid Profile profile);

    // Delete a profile by user ID
    void delete(int userId);

}
