package com.basicsourcecode.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class DevProfileServiceImpl implements ProfilesService {
    @Override
    public String getProfileName() {
        return "dev profile";
    }
}
