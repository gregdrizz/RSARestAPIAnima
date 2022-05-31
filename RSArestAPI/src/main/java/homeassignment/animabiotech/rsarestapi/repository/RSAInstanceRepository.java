package homeassignment.animabiotech.rsarestapi.repository;

import homeassignment.animabiotech.rsarestapi.model.RSAInstance;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class RSAInstanceRepository {
    private final HashMap<String, RSAInstance> rsaInMemoryDB = new HashMap<>();

    public String add(RSAInstance rsaInstance) {
        String id = UUID.randomUUID().toString();
        rsaInMemoryDB.put(id, rsaInstance);
        return id;
    }

    /*
    list all existing key id's
     */
    public Set<String> findAll() {
        return rsaInMemoryDB.keySet();
    }

    public void delete(String id) throws IllegalArgumentException {
        if (!rsaInMemoryDB.containsKey(id)) {
            throw new IllegalArgumentException("Cannot delete id " + id + " does not exist");
        }
        rsaInMemoryDB.remove(id);
    }

    public PrivateKey getPrivateKeyById(String id) throws IllegalArgumentException {
        final RSAInstance rsaInstance = rsaInMemoryDB.get(id);
        if (rsaInstance == null) {
            throw new IllegalArgumentException("Cannot sign id " + id + " - doesn't exist");
        }
        return rsaInstance.getPrivateKey();
    }

    public PublicKey getPublicKeyById(String id) {
        final RSAInstance rsaInstance = rsaInMemoryDB.get(id);
        if (rsaInstance == null) {
            throw new IllegalArgumentException("Cannot verify id " + id + " - doesn't exist");
        }
        return rsaInstance.getPublicKey();
    }

}
