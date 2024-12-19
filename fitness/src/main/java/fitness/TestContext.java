package fitness;

public class TestContext {
    private static user user;

    public static user getUser() {
        return user;
    }

    public static void setUser(user user) {
        TestContext.user = user;
    }
    //this for message in enrolltest and reviow 
    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}