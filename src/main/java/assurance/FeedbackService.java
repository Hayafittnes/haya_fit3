package assurance;

import java.util.HashMap;
import java.util.Map;

public class FeedbackService {
    private Map<String, Feedback> feedbackMap = new HashMap<>();

    public void addFeedback(Feedback feedback) {
        feedbackMap.put(feedback.getUserName(), feedback);
    }

    public Feedback getFeedbackByUser(String userName) {
        return feedbackMap.get(userName);
    }

    public void resolveFeedback(Feedback feedback) {
        feedback.setStatus("Resolved");
        feedback.setRejectionReason(null);
    }

    public void rejectFeedback(Feedback feedback, String rejectionReason) {
        if (feedback == null) {
            throw new IllegalArgumentException("Feedback cannot be null");
        } 
        feedback.setStatus("Rejected");
        feedback.setRejectionReason(rejectionReason);
    }

    public boolean notifyUser(String userName, String status, String rejectionReason) {
        return feedbackMap.containsKey(userName);
    }
}
