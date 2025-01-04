package assurance;
import org.junit.Test;
import static org.junit.Assert.*;

public class Subscriptiontest {
    private final SUBSCRIPTION1 subscription = new SUBSCRIPTION1();
    @Test
    public void testUpdateSubscriptionPlan() {
        subscription.the_admin_navigates_to_the_subscription_management_section();
        subscription.the_admin_views_the_subscription_details_for_the_user("mohammed khatatbeh");
        subscription.the_admin_sees_the_current_subscription_plan("Basic");
        subscription.the_admin_decides_to_change_the_subscription_plan_for_the_user_to("Premium");
        subscription.the_system_should_update_the_user_s_subscription_plan_to("Premium");
        subscription.the_system_should_notify_the_user_about_the_subscription_plan_change_via_in_app_notification("mohammed khatatbeh");
        subscription.the_system_should_reflect_the_new_subscription_plan_in_the_user_s_profile();
        subscription.the_admin_should_be_able_to_see_a_confirmation_of_the_subscription_update();
    }
    @Test
    public void testAddNewUserWithSubscription() {
        subscription.the_admin_navigates_to_the_user_registration_section();
        subscription.the_admin_adds_a_new_user_with_email("shahed thaher", "shahed@example.com");
        subscription.the_admin_assigns_the_subscription_plan_to_the_new_user("Premium");
        subscription.the_system_should_create_the_new_user_account_with_the_subscription_plan("Premium");
        subscription.the_system_should_notify_the_new_user_about_the_subscription_plan_via_in_app_notification("shahed thaher");
    }

    @Test
    public void testDeactivateSubscription() {
        subscription.the_admin_navigates_to_the_subscription_management_section();
        subscription.the_admin_views_the_subscription_details_for_the_user("Jane ayman");
        subscription.the_admin_decides_to_deactivate_the_subscription_for_the_user();
        subscription.the_system_should_deactivate_the_user_s_subscription();
        subscription.the_system_should_notify_the_user_about_the_subscription_deactivation_via_in_app_notification("Jane ayman");
        subscription.the_system_should_reflect_the_deactivated_subscription_status_in_the_user_s_profile();
    }
}
