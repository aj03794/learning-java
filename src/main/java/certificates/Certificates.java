package certificates;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;

public class Certificates {
    public static void main(String[] args) {
        loadKeyStore();
    }

    private static void loadKeyStore() {

        KeyStore keystore = null;

        String relativeCacertsPath = "/lib/security/cacerts".replace("/", File.separator);
        System.out.println(relativeCacertsPath);
        System.out.println("-----------");
        String filename = System.getProperty("java.home") + relativeCacertsPath;

        try {
            FileInputStream is = new FileInputStream(filename);

            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            String password = "changeit";
            keystore.load(is, password.toCharArray());
            keystore.aliases().asIterator().forEachRemaining(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
