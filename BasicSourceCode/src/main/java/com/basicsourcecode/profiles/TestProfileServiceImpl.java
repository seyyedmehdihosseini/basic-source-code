package com.basicsourcecode.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class TestProfileServiceImpl implements ProfilesService {
    @Override
    public String getProfileName() {
        return "test profile";
    }
}
