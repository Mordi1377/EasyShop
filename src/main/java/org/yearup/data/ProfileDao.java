package org.yearup.data;


import org.yearup.models.Profile;

import javax.validation.Valid;
import java.util.Optional;

public interface ProfileDao
{
    Profile create(Profile profile);

    Profile getByUserId(int id);

    // Update an existing profile
    void update(int userId, Profile profile);


}
