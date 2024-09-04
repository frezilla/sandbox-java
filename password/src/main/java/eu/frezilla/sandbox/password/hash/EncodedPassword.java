package eu.frezilla.sandbox.password.hash;

import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

public class EncodedPassword {
    
    private final byte[] hash;
    private final byte[] salt;

    public EncodedPassword(byte[] hash, byte[] salt) {
        this.hash = Arrays.copyOf(Objects.requireNonNull(hash), hash.length);
        this.salt = Arrays.copyOf(Objects.requireNonNull(salt), salt.length);
    }

    public EncodedPassword(String hash, String salt) {
        this.hash = Base64.getDecoder().decode(Objects.requireNonNull(hash));
        this.salt = Base64.getDecoder().decode(Objects.requireNonNull(salt));
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof EncodedPassword)) return false;
        EncodedPassword encodedPassword = (EncodedPassword) o;
        if (this == encodedPassword) return true;
        return Arrays.equals(hash, encodedPassword.getHash()) && Arrays.equals(salt, encodedPassword.getSalt());
    }
    
    public byte[] getHash() {
        return Arrays.copyOf(hash, hash.length);
    }
    
    public byte[] getSalt() {
        return Arrays.copyOf(salt, salt.length);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(hash, salt);
    }
    
    public String hashToString() {
        return Base64.getEncoder().encodeToString(hash);
    }    
    
    public String saltToString() {
        return Base64.getEncoder().encodeToString(salt);
    }
    
    @Override
    public String toString() {
        return "hash: " + hashToString() + " / salt: " + saltToString();
    }
    
}
