package eu.frezilla.sandbox.password.format;

public class PasswordFormatController {
    
    private static class Holder {
        private static final PasswordFormatController instance = new PasswordFormatController();
    }
    
    private final String regExp;
    
    private PasswordFormatController() {
        regExp = 
                "^"                         // début de la chaine de caractères
                + "(?=.*[0-9])"             // un chiffre doit apparaitre au moins une fois
                + "(?=.*[a-z])"             // une lettre minuscule doit apparaitre au moins une fois
                + "(?=.*[A-Z])"             // une lettre majuscule doit apparaitre au moins une fois
                + "(?=.*[@#$%^&+=])"        // un caractère spécial doit apparaitre au moins une fois
                + "(?=\\S+$)"               // aucun blanc, espace n'est autorisé dans la chaine de caractères
                + ".{8,}"                   // longueur minimale de 8 caractères
                + "$";                      // fin de la chaine de caractères
        
    }
    
    public boolean checkFormat(String password) {
        if (password == null) return false;
        return password.matches(regExp);
    }
    
    public static PasswordFormatController getInstance() {
        return Holder.instance;
    }
    
}
