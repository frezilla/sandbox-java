package eu.frezilla.sandbox.password.hash;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Objects;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordFactory {
    
    private static class Holder {
        private static PasswordFactory instance;
        
        static {
            try {
                instance = new PasswordFactory();
            } catch (NoSuchAlgorithmException e) {
                System.err.println(e);
            }
        }
    }
    
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ITERATIONCOUNT = 65536;
    private static final int KEYLENGTH = 128;
    private static final int SALT_LENGTH = 16;
    
    private final SecretKeyFactory secretKeyFactory;
    private final SecureRandom secureRandom;
    
    private PasswordFactory() throws NoSuchAlgorithmException {
        secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        secureRandom = new SecureRandom();
    }
    
    public boolean checkPassword(String password, EncodedPassword passwordResult) throws InvalidKeySpecException {
        char[] passwordArray = Objects.requireNonNull(password).toCharArray();
        byte[] salt = Objects.requireNonNull(passwordResult).getSalt();
        
        KeySpec spec = new PBEKeySpec(passwordArray, salt, ITERATIONCOUNT, KEYLENGTH);
        byte[] computedHash = secretKeyFactory.generateSecret(spec).getEncoded();
        
        return Arrays.equals(
                computedHash, 
                passwordResult.getHash()
        );
    }
    
    public static PasswordFactory getInstance() {
        return Holder.instance;
    }
    
    public EncodedPassword encodeNewPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);
        
        KeySpec spec = new PBEKeySpec(Objects.requireNonNull(password).toCharArray(), salt, ITERATIONCOUNT, KEYLENGTH);
        byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();
        
        return new EncodedPassword(hash, salt);
    }
}
