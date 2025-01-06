package assurance;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
public class AccountM {
	  // Helper method to create a mock user
	public static user createMockUser(int id, String name, int age, String fitnessGoals, String dietaryPreferences) {
            user mockUser = Mockito.mock(user.class);
            Mockito.when(mockUser.getId()).thenReturn(id);
            Mockito.when(mockUser.getName()).thenReturn(name);
            Mockito.when(mockUser.getAge()).thenReturn(age);
            Mockito.when(mockUser.getFitnessGoals()).thenReturn(fitnessGoals);
            Mockito.when(mockUser.getDietaryPreferences()).thenReturn(dietaryPreferences);
            return mockUser;
}
}
