package com.basicsourcecode.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class ProdProfileServiceImpl implements ProfilesService {

    @Override
    public String getProfileName() {
        return "prod profile";
    }
}
