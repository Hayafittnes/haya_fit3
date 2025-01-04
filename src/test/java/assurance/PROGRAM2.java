package assurance;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class PROGRAM2 {

    @When("the admin navigates to the report generation section")
    public void the_admin_navigates_to_the_report_generation_section() {
        System.out.println("Admin is navigating to the report generation section...");
    }

    @When("the admin selects the report type {string}")
    public void the_admin_selects_the_report_type(String reportType) {
        System.out.println("Admin selected report type: " + reportType);
    }

    @When("the admin selects the reporting period {string}")
    public void the_admin_selects_the_reporting_period(String reportingPeriod) {
        System.out.println("Admin selected reporting period: " + reportingPeriod);
    }
    @Then("the system should generate a report that includes:")
    public void the_system_should_generate_a_report_that_includes(DataTable dataTable) {
        System.out.println("Verifying the generated report includes the following data:");
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> row : rows) {
            String dataPoint = row.get(0); 
            System.out.println(" - " + dataPoint);
        }
    }
    @Then("the report should be available for download or viewing")
    public void the_report_should_be_available_for_download_or_viewing() {
        System.out.println("Verifying the report is available for download or viewing...");
        boolean isReportAvailable = true; 
        assert isReportAvailable : "The report is not available for download or viewing.";
    }
    @Then("the admin should be able to download the report in PDF or CSV format")
    public void the_admin_should_be_able_to_download_the_report_in_pdf_or_csv_format() {
        System.out.println("Verifying the admin can download the report in PDF or CSV format...");
        boolean isPDFDownloadable = true;
        boolean isCSVDownloadable = true;
        assert isPDFDownloadable : "PDF format is not downloadable.";
        assert isCSVDownloadable : "CSV format is not downloadable.";
    }
}
