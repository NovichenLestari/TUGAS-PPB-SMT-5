package id.nesd.ovqat.tool;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    public void setTitleAppBar(AppCompatActivity activity, String title) {
        Objects.requireNonNull(activity.getSupportActionBar()).setTitle(title);
    }

    public boolean isValidMail(String email) {
        Matcher matcher = Pattern
                .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                        Pattern.CASE_INSENSITIVE).matcher(email);
        return matcher.find();
    }
}
