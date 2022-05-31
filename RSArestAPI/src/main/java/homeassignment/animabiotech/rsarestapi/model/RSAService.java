package homeassignment.animabiotech.rsarestapi.model;

import homeassignment.animabiotech.rsarestapi.repository.RSAInstanceRepository;

import java.security.*;
import java.util.Base64;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RSAService {

    private static final String RSA = "RSA";
    private static final String ALGORITHM = "SHA256withRSA";
    private final RSAInstanceRepository rsaInstanceRepository;

    public RSAService(RSAInstanceRepository rsaInstanceRepository) {
        this.rsaInstanceRepository = rsaInstanceRepository;
    }

    public Set<String> findAll() {
        return rsaInstanceRepository.findAll();
    }

    public void delete(String id) throws IllegalArgumentException {
        rsaInstanceRepository.delete(id);
    }

    public String create() throws NoSuchAlgorithmException {
        KeyPair newKeyPair = this.generateKeyPair();
        RSAInstance newInstance = new RSAInstance(newKeyPair.getPublic(), newKeyPair.getPrivate());
        return rsaInstanceRepository.add(newInstance);
    }

    private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA);
        generator.initialize(2048, new SecureRandom());
        return generator.generateKeyPair();
    }

    public String sign(String plainText, String id) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        PrivateKey privateKey = rsaInstanceRepository.getPrivateKeyById(id);
        Signature privateSignature = Signature.getInstance(ALGORITHM);
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes(UTF_8));

        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }

    public boolean verify(String plainText, String signature, String id) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        PublicKey publicKey = rsaInstanceRepository.getPublicKeyById(id);
        Signature publicSignature = Signature.getInstance(ALGORITHM);
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes(UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);
    }

}
