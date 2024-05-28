package eu.frezilla.sandbox.password.hash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HashPasswordTest {
    
    @DisplayName("Test de \"hashage\" des mots de passe")
    @Test
    public void test() throws InvalidKeySpecException, NoSuchAlgorithmException {
        PasswordFactory instance = PasswordFactory.getInstance();
        
        String password = "password";
        
        EncodedPassword hash1 = instance.encodeNewPassword(password);
        EncodedPassword hash2 = instance.encodeNewPassword(password);
        Assertions.assertFalse(Objects.equals(hash1, hash2), "Les \"hashages\" d'un mot de passe identique doivent être différents");

        Assertions.assertTrue(instance.checkPassword(password, hash1), "Le mot de passe n'a pas pu être vérifié");
        Assertions.assertTrue(instance.checkPassword(password, hash2), "Le mot de passe n'a pas pu être vérifié");
    }
    
}
