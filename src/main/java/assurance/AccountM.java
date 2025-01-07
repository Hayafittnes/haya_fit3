package assurance;

import org.mockito.Mockito;

public class AccountM {

    private AccountM() {}
    private static AccountM instance;

    public static AccountM getInstance() {
        if (instance == null) {
            instance = new AccountM();
        }
        return instance;
    }

    public static clients createMockClient(int id, String name, int age, String fitnessGoals, String dietaryPreferences) {
        clients mockClient = Mockito.mock(clients.class);
        Mockito.when(mockClient.getId()).thenReturn(id);
        Mockito.when(mockClient.getName()).thenReturn(name);
        Mockito.when(mockClient.getAge()).thenReturn(age);
        Mockito.when(mockClient.getFitnessGoals()).thenReturn(fitnessGoals);
        Mockito.when(mockClient.getDietaryPreferences()).thenReturn(dietaryPreferences);
        return mockClient;
    }
}
