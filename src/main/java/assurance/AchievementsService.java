package assurance;

import java.util.ArrayList;
import java.util.List;

public class AchievementsService {
    public List<String[]> getAllBadges(UserAchiv user) {
        List<String[]> badges = new ArrayList<>();
        for (String program : user.getCompletedPrograms()) {
            String badgeTitle = getBadgeTitle(program);
            String badgeDescription = getBadgeDescription(program); 
            badges.add(new String[]{badgeTitle, badgeDescription});
        }
        return badges;
    }
    public String getBadgeTitle(String program) {
        if (program.equals("Beginner - Yoga for Beginners")) {
            return "Yoga Beginner".trim();
        }
        if (program.equals("Beginner - Cardio Basics")) {
            return "Cardio Starter".trim();
        }
        return "No Badge Earned".trim();
    }
    public String getBadgeDescription(String program) {
        if (program.equals("Beginner - Yoga for Beginners")) {
            return "Awarded for completing the Beginner - Yoga for Beginners program.".trim();
        }
        if (program.equals("Beginner - Cardio Basics")) {
            return "Awarded for completing the Beginner - Cardio Basics program.".trim();
        }
        return "No description available.".trim();
    }
    public String getNoBadgeMessage(UserAchiv user) {
        if (user.getCompletedPrograms().isEmpty()) {
            return "No achievements available yet. Complete a program to earn a badge.";
        }
        return "You have earned badges!"; 
    }
    public void viewAchievementsSummary(UserAchiv user) {
        int badgeCount = user.getCompletedPrograms().size();
        System.out.println("User has " + badgeCount + " badges.");
    }
    public String getAchievementsSummary(UserAchiv user) {
        int badgeCount = user.getCompletedPrograms().size(); 
        return "You have earned " + badgeCount + " badges for completing fitness programs."; 
    }

    public void viewAchievements(UserAchiv user) {
        System.out.println("Viewing achievements for user: " + user.getName());
        for (String program : user.getCompletedPrograms()) {
            String badgeTitle = getBadgeTitle(program); 
            String badgeDescription = getBadgeDescription(program); 
            System.out.println("Badge: " + badgeTitle + " - " + badgeDescription);
        }
    }
    public void viewMultipleAchievements(UserAchiv user) {
        System.out.println("User " + user.getName() + " has completed the following programs and earned badges:");
        for (String program : user.getCompletedPrograms()) {
            String badgeTitle = getBadgeTitle(program); 
            String badgeDescription = getBadgeDescription(program); 
            System.out.println("- " + badgeTitle + ": " + badgeDescription);
        }
    }
}
