import static org.testng.Assert.assertEquals;

import java.sql.Driver;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class MyTestCases {

	WebDriver driver = new ChromeDriver();

	String MyWebSiteUrl = "https://www.almosafer.com/en";
	Random rand = new Random();

	@BeforeTest
	public void MySetUp() {

		driver.manage().window().maximize();
		driver.get(MyWebSiteUrl);

		WebElement ButtunForTheCurrency = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));

		ButtunForTheCurrency.click();

	}

	@Test(priority = 1, enabled = true)
	public void CheckTheEnglishLanguageIsDefault() {

		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		String ExpecttedLanguage = "en";

		Assert.assertEquals(ActualLanguage, ExpecttedLanguage);
	}

	@Test(priority = 2, enabled = true)
	public void CheckTheDefaultCurrensyIsSAR() {

		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();

		String ExpecttedCurrensy = "SAR";

		Assert.assertEquals(ActualCurrency, ExpecttedCurrensy);

	}

	@Test(priority = 3, enabled = true)

	public void CheckPhoneNambur() {

		String ActualNamber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		String ExpecttedNambur = "+966554400000";
		Assert.assertEquals(ActualNamber, ExpecttedNambur);

	}

	@Test(priority = 4, enabled = true)

	public void CheckQitafLogo() {

		boolean ActualQitafLogo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG"))
				.isDisplayed();
		boolean ExpecttedQitafLogo = true;

		Assert.assertEquals(ActualQitafLogo, ExpecttedQitafLogo);

	}

	@Test(priority = 5, enabled = true)
	public void CheckHotelTabIsNotSelect() {

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		String ActualValue = HotelTab.getAttribute("aria-selected");
		String ExpictedValue = "false";

		Assert.assertEquals(ActualValue, ExpictedValue);

	}

	@Test(priority = 6, enabled = true)
	public void CheckDepatureDate() {
		int Today = LocalDate.now().getDayOfMonth();
		int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();
		int Dayaftertomorrow = LocalDate.now().plusDays(2).getDayOfMonth();

		String ActualDepature = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
		String ExpictedDepature = Integer.toString(tomorrow);

		Assert.assertEquals(ActualDepature, ExpictedDepature);

	}

	@Test(priority = 7, enabled = true)
	public void CheckReturnDate() {

		int DayAfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();

		String ActualReturnDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
		String ExpictedReturnDate = Integer.toString(DayAfterTomorrow);

		Assert.assertEquals(ActualReturnDate, ExpictedReturnDate);

	}

	@Test(priority = 8, enabled = true)
	public void RandomlyChangeLanguage() throws InterruptedException {

		String[] EnglishCitiesNames = { "jeddah", "riyadh", "dubai" };
		String[] ArabicCitiesNames = { "دبي", "جدة" };

		int randomArabicCity = rand.nextInt(ArabicCitiesNames.length);
		int randomEnglishCity = rand.nextInt(EnglishCitiesNames.length);

		String[] myWebsites = { "https://www.almosafer.com/ar", "https://www.almosafer.com/en" };

		int randomIndex = rand.nextInt(myWebsites.length);

		driver.get(myWebsites[randomIndex]);

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		HotelTab.click();

		WebElement HotelSearchBar = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

		if (driver.getCurrentUrl().equals("https://www.almosafer.com/ar")) {
			String ActualLaguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "ar";

			Assert.assertEquals(ActualLaguage, ExpectedLanguage);
			HotelSearchBar.sendKeys(ArabicCitiesNames[randomArabicCity]);
		} else {
			String ActualLaguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "en";

			Assert.assertEquals(ActualLaguage, ExpectedLanguage);
			HotelSearchBar.sendKeys(EnglishCitiesNames[randomEnglishCity]);

		}
		Thread.sleep(2000);
		WebElement CitiesList = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		WebElement SelectNamberOfVistor = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		CitiesList.findElements(By.tagName("li")).get(1).click();

		Select select = new Select(SelectNamberOfVistor);
		int RandomOfVistor = rand.nextInt(2);

		select.selectByIndex(RandomOfVistor);

		WebElement SearchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchButton.click();

		Thread.sleep(60000);

	}

	@Test(priority = 9, enabled = true)

	public void CheckThatThePageIsFullyLoaded() {
		WebElement SearchResult = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));

		boolean ActualResult = SearchResult.getText().contains("found") || SearchResult.getText().contains("مكان");

		boolean ExpectedResult = true;

		Assert.assertEquals(ActualResult, ExpectedResult);
	}

	@Test(priority = 10, enabled = true)
	public void CheckTheSortOption() throws InterruptedException {

		
		Thread.sleep(130000);

		WebElement CheckTheSortOption = driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
		CheckTheSortOption.click();

		Thread.sleep(15000);

		WebElement Container = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[5]/div/div[2]"));

		if (driver.getCurrentUrl().contains("en")) {
			List<WebElement> PriceList = Container.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muiltr-18vmb2l"));
			int LowestPrice = Integer.parseInt(PriceList.get(0).getText().replace("SAR ", ""));

			int HighestPrice = Integer.parseInt(PriceList.get(PriceList.size() - 1).getText().replace("SAR ", ""));

			boolean ActualPrice = LowestPrice < HighestPrice;
			boolean ExpectedPrice = true;

			Assert.assertEquals(ActualPrice, ExpectedPrice);
		} else {
			List<WebElement> PriceList = Container.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muirtl-1l5b3qq"));
			int lowestPrice = Integer.parseInt(PriceList.get(0).getText().replace("ر.س. ", ""));
			System.out.println();
			int HighestPrice = Integer.parseInt(PriceList.get(PriceList.size() - 1).getText().replace("ر.س. ", ""));
			

			boolean ActualValue = lowestPrice < HighestPrice;
			boolean ExpectedValue = true;
			Assert.assertEquals(ActualValue, ExpectedValue);
		}
	}

}
