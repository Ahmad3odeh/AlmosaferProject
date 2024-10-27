import static org.testng.Assert.assertEquals;

import java.sql.Driver;
import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class MyTestCases {

	WebDriver driver = new ChromeDriver();

	String MyWebSiteUrl = "https://www.almosafer.com/en";

	@BeforeTest
	public void MySetUp() {

		driver.manage().window().maximize();
		driver.get(MyWebSiteUrl);

		WebElement ButtunForTheCurrency = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));

		ButtunForTheCurrency.click();

	}

	@Test(priority = 1)
	public void CheckTheEnglishLanguageIsDefault() {

		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		String ExpecttedLanguage = "en";

		Assert.assertEquals(ActualLanguage, ExpecttedLanguage);
	}

	@Test(priority = 2)
	public void CheckTheDefaultCurrensyIsSAR() {

		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();

		String ExpecttedCurrensy = "SAR";

		Assert.assertEquals(ActualCurrency, ExpecttedCurrensy);

	}

	@Test(priority = 3)

	public void CheckPhoneNambur() {

		String ActualNamber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		String ExpecttedNambur = "+966554400000";
		Assert.assertEquals(ActualNamber, ExpecttedNambur);

	}

	@Test(priority = 4)

	public void CheckQitafLogo() {

		boolean ActualQitafLogo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG"))
				.isDisplayed();
		boolean ExpecttedQitafLogo = true;

		Assert.assertEquals(ActualQitafLogo, ExpecttedQitafLogo);

	}

	@Test(priority = 5)
	public void CheckHotelTabIsNotSelect() {

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		String ActualValue = HotelTab.getAttribute("aria-selected");
		String ExpictedValue = "false";

		Assert.assertEquals(ActualValue, ExpictedValue);

	}

	@Test(priority = 6)
	public void CheckDepatureDate() {
		int Today=LocalDate.now().getDayOfMonth();
		int tomoro =LocalDate.now().plusDays(1).getDayOfMonth();
		
		driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']")).getTagName();
		
		
		
		
		
		
		

	}
}
