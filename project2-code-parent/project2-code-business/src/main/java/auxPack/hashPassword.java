package auxPack;

import org.apache.log4j.Logger;


import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;
        import java.util.Base64;



//Usada para criar o hash para proteger as passwords usadas no programa
public class hashPassword {

    static Logger logger = Logger.getLogger(hashPassword.class);


    public String getPasswordHash(String password) {

        String passwordSalt = password;
        String passwordHash = "";

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-512");//Create MessageDigest object for SHA-512

            digest.update(passwordSalt.getBytes());

            passwordHash = Base64.getEncoder().encodeToString(digest.digest());

        } catch (NoSuchAlgorithmException e) {

            logger.fatal("hashPass"+e);

        }

        return passwordHash;
    }
}

