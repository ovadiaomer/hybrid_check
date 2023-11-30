import com.chrome.handler.ChromedriverHandler
import io.github.bonigarcia.wdm.WebDriverManager
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobilePlatform
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.MalformedURLException
import java.net.URL

object AppTest {
//    fun setupChromeDriver(): String {
//        WebDriverManager.chromedriver().browserVersion("118").setup()
//        return WebDriverManager.chromedriver().downloadedDriverPath
//    }

    @kotlin.Throws(MalformedURLException::class)
    @kotlin.jvm.JvmStatic
    fun main(args: Array<String>) {
        val capabilities = DesiredCapabilities()
        val projectPath = System.getProperty("user.dir")
        val relativePath = "/src/main/resources/chromedriver"
        capabilities.setCapability("automationName", "UiAutomator2")
        capabilities.setCapability("deviceName", "R5CTC0V040V")
        capabilities.setCapability("udid", "R5CTC0V040V")
        capabilities.setCapability("newCommandTimeout", 60 * 60)
        capabilities.setCapability("noReset", true)
        capabilities.setCapability("fullReset", false)
        capabilities.setCapability("platformName", MobilePlatform.ANDROID)
        capabilities.setCapability("automationName", "UiAutomator2")
        capabilities.setCapability("uiautomator2ServerInstallTimeout", 60000)
        capabilities.setCapability("uiautomator2ServerLaunchTimeout", 60000)
        capabilities.setCapability("autoGrantPermissions", true)
        capabilities.setCapability("setWebContentsDebuggingEnabled", true)
        capabilities.setCapability("chromedriverExecutable", projectPath + relativePath)
        capabilities.setCapability("adbExecTimeout", 120000)
        capabilities.setCapability("autoWebview", true)
        capabilities.setCapability("chromedriverAutoDownload", false)
        capabilities.setCapability("appPackage", "com.metropcs.metrozone")
        capabilities.setCapability(
            "appActivity",
            "com.metropcs.metrozone/com.ironsource.aura.news.presentation.splash_activity.SplashActivity"
        )
//
//        val serverAddress = URL("http://127.0.0.1:4723/")
//        val driver = AndroidDriver(serverAddress, capabilities) //worked

        //---------------------

//        val capabilities = DesiredCapabilities()
//        val projectPath = System.getProperty("user.dir")
//        val relativePath = "/src/main/resources/chromedriver"
//        capabilities.setCapability("automationName", "UiAutomator2")
//        capabilities.setCapability("deviceName", "R5CTC0V040V")
//        capabilities.setCapability("udid", "R5CTC0V040V")
//        capabilities.setCapability("newCommandTimeout", 60 * 60)
//        capabilities.setCapability("noReset", true)
//        capabilities.setCapability("fullReset", false)
//        capabilities.setCapability("platformName", MobilePlatform.ANDROID)
//        capabilities.setCapability("uiautomator2ServerInstallTimeout", 60000)
//        capabilities.setCapability("uiautomator2ServerLaunchTimeout", 60000)
//        capabilities.setCapability("autoGrantPermissions", true)
//        capabilities.setCapability("setWebContentsDebuggingEnabled", true)
//        capabilities.setCapability("adbExecTimeout", 120000)
//        capabilities.setCapability("autoWebview", true)
//        capabilities.setCapability("chromedriverExecutable", projectPath + relativePath)
//
//        capabilities.setCapability("chromedriverAutoDownload", false)
//        capabilities.setCapability("appPackage", "com.metropcs.metrozone")
//        capabilities.setCapability(
//            "appActivity",
//            "com.ironsource.aura.news.presentation.splash_activity.SplashActivity"
//        )

        val serverAddress = URL("http://127.0.0.1:4723/")
        val driver = AndroidDriver(serverAddress, capabilities)


        val contextHandles1 = driver.contextHandles
        val webContext1 = driver.context("WEBVIEW_com.metropcs.metrozone")

        val windowHandles = driver.windowHandles.toList()
        var matchingHandle: String? = null

        // Iterate in reverse order
        for (handle in windowHandles.asReversed()) {
            driver.switchTo().window(handle)
            // Check if the title matches "Metro Portal"
            if (driver.title.equals("Metro Portal")) {
                matchingHandle = handle // Found the matching handle, no need to continue
                break
            }
        }
        // After finding the matching handle, switch to that window
        if (matchingHandle != null) {
            driver.switchTo().window(matchingHandle)
            val headerShadow = driver.findElements(By.cssSelector("div[class^='_ArticleImageHeaderShadow']"))
            val articleBody = driver.findElements(By.cssSelector("div[class^='_articleBody']"))
        } else {
            println("No window with the title 'Metro Portal' was found.")
        }



    }
}
