import java.io.File;
import java.io.IOException;
import java.sql.Driver;
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

public class Parameters {

	WebDriver driver = new ChromeDriver();

	String MyWebSiteUrl = "https://www.almosafer.com/en";
	Random rand = new Random();
	String ExpecttedLanguage = "en";
	String ExpecttedCurrensy = "SAR";
	String ExpecttedNambur = "+966554400000";
	boolean ExpecttedQitafLogo = true;
	String ExpictedHotelTabIsNotSelect = "false";
	boolean ExpectedThePageIsFullyLoaded = true;
	int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();

	String ExpictedDepature = String.format("%02d", tomorrow);
	int DayAfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();
	String ExpictedReturnDate = String.format("%02d", DayAfterTomorrow);

	String[] EnglishCitiesNames = { "jeddah", "riyadh", "dubai" };
	String[] ArabicCitiesNames = { "دبي", "جدة" };

	int randomArabicCity = rand.nextInt(ArabicCitiesNames.length);
	int randomEnglishCity = rand.nextInt(EnglishCitiesNames.length);

	String[] myWebsites = { "https://www.almosafer.com/ar", "https://www.almosafer.com/en" };

	int randomIndex = rand.nextInt(myWebsites.length);

	public void MySetupToEnterWebsite() {

		driver.manage().window().maximize();
		driver.get(MyWebSiteUrl);

		WebElement ButtunForTheCurrency = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));

		ButtunForTheCurrency.click();

	}

	public void ScreenShoot() throws IOException, InterruptedException {
		Thread.sleep(2000);
		Date MyDate = new Date();
		String FileName = MyDate.toString().replace(":", "-");
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./ScreenShot/" + FileName + ".png");
		FileUtils.copyFile(srcFile, destFile);

	}

	public void CheckTheWebSiteToEnterCityName(WebElement HotelSearchBar) throws InterruptedException {

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
	}

	public void EnterNumberOfVisetorAndSearchResult() throws InterruptedException {
		WebElement CitiesList = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		WebElement SelectNamberOfVistor = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		CitiesList.findElements(By.tagName("li")).get(1).click();

		Select select = new Select(SelectNamberOfVistor);
		int RandomOfVistor = rand.nextInt(2);

		select.selectByIndex(RandomOfVistor);

		WebElement SearchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchButton.click();

		Thread.sleep(45000);
	}

	public void RandomlyCheckTheWebsite() {
		driver.get(myWebsites[randomIndex]);

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		HotelTab.click();
	}

	public void SortOptionChecker() {

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
