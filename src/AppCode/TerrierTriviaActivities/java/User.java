public class User {

    String UserId;
    String UserName;
    int UserScore;

public User(){

}

    public User(String userId, String userName, int userScore) {
        this.UserId = userId;
        this.UserName = userName;
        this.UserScore = userScore;
    }

    public String getUserId() {
        return UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public int getUserScore() {
        return UserScore;
    }
}

