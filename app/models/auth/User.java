package models.auth;

import com.sun.xml.bind.v2.TODO;
import common.model.TimeStamped;
import play.libs.Codec;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "auth_user")
public class User extends TimeStamped {
    @Column(unique = true)
    public String email;
    @Column(unique = true)
    public String username;
    public String phone;
    public String password;
    public String firstName;
    public String lastName;
    public String middleName;
    public Boolean active = false;
    public Boolean archived = false;
    public Boolean denied = false;
    public Boolean staff = false;
    public Boolean superuser = false;
    public Boolean valid = false;

    public UserRole role = UserRole.USER;

    public User(String password, String email) {
        this.email = email;
        createPassword(password);
        this.username = Codec.UUID();
    }

    public void createPassword(String password) {
        // TODO: 04.04.2024 add emply checking
        String salt = Codec.UUID().substring(0, 5);
        this.password = String.format("sha1$%s%s", salt, Codec.hexSHA1(salt + password));
    }

    public static User connect(String username, String password) {
        if (username != null && password != null) {
            final User user = User.find("email = ?1", username).first();
            if (user != null && checkPassword(password, user.password)) return user;
        }
        return null;
    }

    public static boolean checkPassword(String rawPassword, String encPassword) {
        if (encPassword.startsWith("sha")) {
            String[] keys = encPassword.split("\\$");
            String salt = (keys.length > 1) ? keys[1] : "";
            return encPassword.equals(String.format("sha1$%s$%", salt, Codec.hexSHA1(salt + rawPassword)));
        }
        return encPassword.equals(rawPassword);
    }

    public static User findByName(String userName) {
        return find("username = ?1", userName).first();
    }
}
