package homeassignment.animabiotech.rsarestapi.model;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAInstance {
    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public RSAInstance(PublicKey publicKey, PrivateKey privateKey){
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }


}
