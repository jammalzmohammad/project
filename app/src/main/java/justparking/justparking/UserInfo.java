package justparking.justparking;

/**
 * Created by Mohammad Aljammal on 3/25/2018.
 */

public class UserInfo {
    private String name;
    private String email;
    public static UserInfo userInfo=new UserInfo();

    public UserInfo() {
    }

    public UserInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
