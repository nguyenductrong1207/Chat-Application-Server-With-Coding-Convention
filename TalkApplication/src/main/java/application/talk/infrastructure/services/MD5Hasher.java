package application.talk.infrastructure.services;

import application.talk.usecases.adapters.Hasher;

public class MD5Hasher implements Hasher {
    public String hash(String text) {
        return text;
    }
}
