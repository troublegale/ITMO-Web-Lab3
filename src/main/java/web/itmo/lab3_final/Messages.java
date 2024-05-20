package web.itmo.lab3_final;

import java.util.ResourceBundle;

public class Messages {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("localization");

    public static String getEclipseEstablishError() {
        return bundle.getString("eclipseEstablishError");
    }

    public static String getWrongArgsAdd() {
        return bundle.getString("wrongArgsAdd");
    }
}
