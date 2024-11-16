import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends Parameters {

	@BeforeTest
	public void MySetUp() {

		MySetupToEnterWebsite();
	}

	@Test(priority = 1, enabled = true)
	public void CheckTheEnglishLanguageIsDefault() throws IOException, InterruptedException {

		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		Assert.assertEquals(ActualLanguage, ExpecttedLanguage);

		ScreenShoot();

	}

	@Test(priority = 2, enabled = true)
	public void CheckTheDefaultCurrensyIsSAR() throws IOException, InterruptedException {

		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();

		Assert.assertEquals(ActualCurrency, ExpecttedCurrensy);
		ScreenShoot();

	}

	@Test(priority = 3, enabled = true)

	public void CheckPhoneNambur() {

		String ActualNamber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();

		Assert.assertEquals(ActualNamber, ExpecttedNambur);

	}

	@Test(priority = 4, enabled = true)

	public void CheckQitafLogo() {

		boolean ActualQitafLogo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG"))
				.isDisplayed();

		Assert.assertEquals(ActualQitafLogo, ExpecttedQitafLogo);

	}

	@Test(priority = 5, enabled = true)
	public void CheckHotelTabIsNotSelect() {

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		String ActualValue = HotelTab.getAttribute("aria-selected");

		Assert.assertEquals(ActualValue, ExpictedHotelTabIsNotSelect);

	}

	@Test(priority = 6, enabled = true)
	public void CheckDepatureDate() {

		String ActualDepature = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();

		Assert.assertEquals(ActualDepature, ExpictedDepature);

	}

	@Test(priority = 7, enabled = true)
	public void CheckReturnDate() {

		String ActualReturnDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();

		Assert.assertEquals(ActualReturnDate, ExpictedReturnDate);

	}

	@Test(priority = 8, enabled = true)
	public void RandomlyChangeLanguage() throws InterruptedException {

		RandomlyCheckTheWebsite();

		WebElement HotelSearchBar = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

		CheckTheWebSiteToEnterCityName(HotelSearchBar);
		EnterNumberOfVisetorAndSearchResult();

	}

	@Test(priority = 9, enabled = true)

	public void CheckThatThePageIsFullyLoaded() {
		WebElement SearchResult = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));

		boolean ActualResult = SearchResult.getText().contains("found") || SearchResult.getText().contains("مكان");

		Assert.assertEquals(ActualResult, ExpectedThePageIsFullyLoaded);
	}

	@Test(priority = 10, enabled = true)
	public void CheckTheSortOption() throws InterruptedException {

		Thread.sleep(45000);

		WebElement CheckTheSortOption = driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
		CheckTheSortOption.click();

		Thread.sleep(15000);

		SortOptionChecker();

	}

}
