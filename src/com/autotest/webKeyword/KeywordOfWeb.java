package com.autotest.webKeyword;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.autotest.autocommon.AutoLogger;
import com.autotest.driverself.GoogleDriver;
import com.autotest.driverself.FFDriver;
import com.autotest.driverself.IEDriver;
import com.google.common.io.Files;



public class KeywordOfWeb
{
	//成员变量 webdriver,所有的方法都操作同一个driver
	public WebDriver driver;
	
	public EdgeDriver edgeDriver;
	
	/**
	 * 
	 * @param : browserType 浏览器的类型 chrome firefox IE Edge
	 * 
	 */
	
	
	public void openBrowser( String browserType )
	{
		try 
		{
			AutoLogger.log.info( "开始启动webdriver打开浏览器" );
			
			switch ( browserType )
			{
			case "chrome":
				
				GoogleDriver gg = new GoogleDriver( "driver/chromedriver.exe" );
				
				driver = gg.getdriver();
				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				break;
				
			case "firefox": 
				
				FFDriver ff = new FFDriver( "D:\\Program Files (x86)\\Firefox\\firefox.exe", "driver/geckodriver.exe");
				
				driver = ff.getdriver();
				
				driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
				
				break;
				
			case "IE":
				
				IEDriver ie = new IEDriver("driver/IEDriverServer.exe");
				
				driver = ie.getdriver();
				
				driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
				
				break;
				
			case "Edge":
				
				edgeDriver = new EdgeDriver();
				
				edgeDriver.manage().timeouts().implicitlyWait( 10,TimeUnit.SECONDS );
				
				break;
				
			default:
				
				GoogleDriver ggdefault = new GoogleDriver("webDrivers/chromedriver.exe");
				
				driver = ggdefault.getdriver();
				
			}
		} catch ( Exception e ) {
			
			e.printStackTrace();
			
			AutoLogger.log.error( "创建浏览器失败" );
		}
	}

	
	/**
	 *  打开某个网页
	 */
	public void visitWeb( String Url )
	{
		try {
			
			driver.get(Url);
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
			
		}
	}
	
	
	/**
	 * 向某个元素中输入对应的内容
	 * 
	 * @param name  输入框元素的name属性表达式
	 * @param content  输入的元素内容 
	 * 
	 */
	
	public void inputByName( String name, String content ) 
	{
		try {
			
			driver.findElement( By.name(name) ).sendKeys( content );
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
			
			System.out.println( "name 属性为 " + name + " 的元素定位不到");
			
		}
	}
	
	
	/**
	 * 通过xpath定位元素，并输入内容
	 * 
	 * @param xpath
	 * @param content
	 * 
	 */
	
	public void input( String xpath, String content ) 
	{
		try {
			
			driver.findElement(By.xpath( xpath )).sendKeys( content );
			
		} catch ( Exception e){
			
			AutoLogger.log.error(e,e.fillInStackTrace());
			
			saveScrShot("input方法");
			
			AutoLogger.log.error( xpath + " 的元素定位不到" );
			
		}
	}


	/**
	 * 通过switchcase 同时输入定位方法，和定位表达式完成定位并输入内容。
	 * 
	 * @param method
	 * @param locator
	 * @param content
	 * 
	 */

	public void inputBy( String method, String locator, String content ) 
	{
		switch ( method ) {
		
		case "id":
			
			driver.findElement(By.id( locator )).sendKeys( content );
			
			break;
		
		case "xpath":
			
			driver.findElement(By.xpath(locator)).sendKeys( content );
			
			break;
		
		}
	}

	
	/**
	 * 触发某个元素提交form表单的submit方法
	 * 
	 * @param name 元素的name属性
	 */

	public void submitByName( String name )
	{
		try {
			
			driver.findElement(By.name(name)).submit();
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
			
		}
	}

	
	/**
	 * 点击某个id属性的元素
	 * 
	 * @param id 元素的id属性值
	 */
	
	public void clickById( String id )
	{
		try {
			
			driver.findElement(By.id(id)).click();
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
			
		}
	}

	
	/**
	 * xpath方法定位点击。
	 */

	public void click( String xpath ) 
	{
		try {
			
			driver.findElement(By.xpath(xpath)).click();
			
		} catch ( Exception e ) {
			
			saveScrShot("click方法");
			
			AutoLogger.log.error(xpath + " 的元素定位不到");
			
			AutoLogger.log.error(e, e.fillInStackTrace());
			
		}
	}

	
	/**
	 * 
	 * @return 返回当前网页的标题
	 */

	public String getWebTitle() 
	{
		try {
			
			String title = driver.getTitle();
			
			return title;
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
			
			return "获取 title 失败";
		}
	}
	
	
	/**
	 * 关闭浏览器
	 */
	
	public void closeBrowser() 
	{
		try {
			
			driver.quit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}

	
	/**
	 * 通过左上角的坐标点和窗口大小，设置浏览器的位置和大小。
	 */
	
	public void setWindowSize() 
	{
		try {
			
			Point p = new Point(330, 0);
			
			Dimension d = new Dimension(1500, 1200);
			
			driver.manage().window().setPosition(p);
			
			driver.manage().window().setSize(d);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	
	/**
	 * 显式等待，语法规则使用expectedCondition作为自定义等待条件时，需要实现一个apply的方法，其返回值和Expectedcondition<类型>的<类型>是一致的。
	 * 
	 */
	
	public void explicitlyWait(String xpath) 
	{
		// 设置一个最长的等待时间
		
		try {
			
			WebDriverWait waitOb = new WebDriverWait(driver, 10);
			
			// expectedCondition的泛型V和public返回的类型，是统一的
			waitOb.until(new ExpectedCondition<WebElement>() 
			{
				// 固定方法名 apply,形参Webdriver d的传递的实参就是waitOb对象中的driver参数
				public WebElement apply(WebDriver d) 
				{
					// 返回一个布尔类型的返回值
					return d.findElement(By.xpath(xpath));
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 显式等待使用预定义的方法来等待某个元素的出现(能够被定位到了)
	 * 
	 * @param xpath xpath的定位表达式
	 */
	
	public void explicitlyWaitConditions(String xpath) 
	{
		try {
			
			WebDriverWait waitOb = new WebDriverWait(driver, 10);
			
			waitOb.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	
	/**
	 * 强制等待方法
	 * 
	 * @param seconds 输入参数为字符串类型的秒数。
	 */
	
	public void halt( String seconds )
	{
		int time = 1000;
		
		try {
			time = Integer.parseInt(seconds) * 1000;
			
			Thread.sleep(time);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	
	/**
	 * 断言页面是否包含某个内容
	 * 
	 * @return
	 */
	
	public boolean assertPageContains(String expectedContent) 
	{
		try {
			
			if (driver.getPageSource().contains(expectedContent)) {
				
				System.out.println("测试通过");
				
				return true;
				
			} else {
				
				System.out.println("测试失败，页面不包含" + expectedContent);
				
				return false;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;
		}

	}

	
	/**
	 * 
	 * @param xpath           xpath表达式
	 * @param attribute       属性名
	 * @param expectedContent 预期的属性值
	 * @return
	 */
	
	public boolean assertElementAttrEquals(String xpath, String attribute, String expectedContent) 
	{
		try {
			
			if (driver.findElement(By.xpath(xpath)).getAttribute(attribute).equals(expectedContent)) {
				
				System.out.println("测试通过");
				
				return true;
				
			} else {
				
				System.out.println("测试失败," + xpath + "元素不包含" + "属性值为" + expectedContent + "的" + attribute + "属性");
				
				return false;
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}

	
	/**
	 * 使用findelements方法，获取一组符合条件的元素，并且遍历输出其内容 参考这个方法，当需要在网页中批量操作符合某个条件的一组元素时，进行操作。
	 * 
	 * @param xpath
	 */
	
	public void multiElementLocate(String xpath) 
	{
		List<WebElement> list = driver.findElements(By.xpath(xpath));
		
		for (WebElement e : list) 
		{
			
			System.out.println(e.getText());
			
		}
	}

	
	// 封装一个登录的关键字。
	
	public void ShopLogin() 
	{
		visitWeb("http://www.testingedu.com.cn:8000/");
		
		click("//a[text()='登录']");
		
		input("//input[@id='username']", "13800138006");
		
		input("//input[@id='password']", "123456");
		
		input("//input[@id='verify_code']", "1");
		
		click("//a[@name='sbtbutton']");
	}

	
	/**
	 * 实现鼠标悬停到某个元素
	 */
	
	public void hover(String xpath) 
	{
		// 使用selenium的actions类，编辑动作流程，实现更多的操作方式
		try {
			
			Actions act = new Actions(driver);
			
			act.moveToElement(driver.findElement(By.xpath(xpath))).build().perform();
			
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
	}

	
	/**
	 * 基于页面标题切换窗口
	 * 
	 * @param targetTitle 目标窗口的标题
	 */
	
	public void switchWindowByTitle(String targetTitle) 
	{
		String targetHandle = "";
		
		Set<String> windowHandles = driver.getWindowHandles();
		
		System.out.println(windowHandles);
		
		// 通过切换到不同句柄的窗口，并且获取页面标题，来判断是否是想要的句柄
		for (String handle : windowHandles) 
		{
			// 切换到不同句柄所代表的窗口获取页面的标题
			if (driver.switchTo().window(handle).getTitle().equals(targetTitle)) 
			{
				// 说明找到了想要的句柄
				targetHandle = handle;
				
				// 跳出循环
				break;
			}
		}
		// 切换到目标窗口
		driver.switchTo().window(targetHandle);
	}

	
	// 关闭旧窗口，切换到新窗口操作
	
	public void closeOldWin() 
	{
		// 因为set是无序的，所以先把set中的每个句柄取出来放到list当中去，然后再通过list中的下标来进行操作。
		List<String> handlelist = new ArrayList<String>();
		
		// 返回一个句柄集合
		Set<String> handles = driver.getWindowHandles();
		
		// 循环获取集合里面的句柄，保存到List数组handles里面
		Iterator<String> it = handles.iterator();
		
		while (it.hasNext()) 
		{
			
			handlelist.add(it.next().toString());
			
		}
		
		// 关闭正在操作的窗口
		driver.close();
		
		// 切换到新窗口
		try {
			
			driver.switchTo().window(handlelist.get(1));
			
		} catch (Exception e) {
		}
	}

	
	// 关闭新窗口
	
	public void closeNewWin() 
	{
		List<String> handles = new ArrayList<String>();
		
		Set<String> s = driver.getWindowHandles();
		
		// 循环获取集合里面的句柄，保存到List数组handles里面
		for (Iterator<String> it = s.iterator(); it.hasNext();) 
		{
			
			handles.add(it.next().toString());
			
		}
		
		try {
			// 先切换到新的窗口，关闭掉
			driver.switchTo().window(handles.get(1));
			
			// 关闭切换之后的新窗口
			driver.close();
			
			// 切回旧窗口
			driver.switchTo().window(handles.get(0));
			
		} catch (Exception e) {
		}
	}

	
	/**
	 * 基于iframe的name或id属性切换
	 * 
	 * @param nameOrId
	 */
	
	public void switchIframeByName(String nameOrId) 
	{
		try {
			
			driver.switchTo().frame(nameOrId);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			System.out.println("切换Iframe失败");
		}
	}

	
	/**
	 * 基于元素定位Iframe
	 * 
	 * @param xpath
	 */
	
	public void switchIframeByELe(String xpath) 
	{
		// 基于元素定位
		try {
			
			driver.switchTo().frame(driver.findElement(By.xpath(xpath)));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	
	/**
	 * 切换到父级iframe
	 */
	
	public void switchOutIframe() 
	{
		try {
			
			driver.switchTo().parentFrame();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	
	/**
	 * 直接切换到html主页面
	 */
	
	public void switchToRoot() 
	{
		try {
			
			driver.switchTo().defaultContent();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 运行js脚本
	 */
	
	
	public void runJs(String jsScript) 
	{
		try {
			
			JavascriptExecutor jsExe = (JavascriptExecutor) driver;
			
			jsExe.executeScript(jsScript);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	
	public void getJs(String jsScript)
	{
		try {
			
			JavascriptExecutor jsExe = (JavascriptExecutor) driver;
			
			String result=jsExe.executeScript("return "+jsScript).toString();
			
			AutoLogger.log.info("js执行结果是"+result);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	public void runJsWithParam(String jsScript,String xpath) 
	{
		try {
			
			JavascriptExecutor jsExe=(JavascriptExecutor) driver;
			
			jsExe.executeScript(jsScript, driver.findElement(By.xpath(xpath)));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 竖直滚动浏览器
	 */
	
	public void scrollStraight(String yOffset) 
	{
		try {
			
			JavascriptExecutor jsExe = (JavascriptExecutor) driver;
			
			jsExe.executeScript("window.scrollTo(0," + yOffset + ")");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	
	/**
	 * 通过select的option元素的value值进行选择
	 * @param xpath
	 * @param value
	 */
	
	public void selectByValue(String xpath,String value) 
	{
		try {
			
			Select sel=new Select(driver.findElement(By.xpath(xpath)));
			
			sel.selectByValue(value);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	
	/**
	 * 通过option选项的文本内容，进行select的选择
	 */
	
	public void selectByText(String xpath,String text) 
	{
		try {
			
			Select sel=new Select(driver.findElement(By.xpath(xpath)));
			
			sel.selectByVisibleText(text);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 通过input[type='file']的元素完成文件上传，直接sendkeys即可
	 * @param xpath
	 * @param filepath
	 */
	
	public void uploadFile(String xpath,String filepath) 
	{
		try {
			
			driver.findElement(By.xpath(xpath)).sendKeys(filepath);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 报错时的截图实现，保存的截图文件名格式为方法+当前时间.png
	 * @param method 报错的方法名
	 */

	public void saveScrShot(String method)
	{

		//获取当前时间
		Date date = new Date(0);
		
		//将时间转换成指定格式
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd+HH-mm-ss" );
		
		//将当前时间转换为上一步指定格式的字符串
		String createdate = sdf.format( date );
		
		//拼接文件名，格式为：工作目录路径+方法名+执行时间.png
		String scrName = "log/ScrShot/" + method + createdate + ".png";
		
		//以当前文件名创建一个空的png文件
		File scrShot = new File( scrName );
		
		//将截图文件保存到内存中的临时文件
		File tmp = (( TakesScreenshot ) driver).getScreenshotAs( OutputType.FILE);
		
		//将内存中保存的截图复制到创建出来的png文件中
		try {
			
			Files.copy( tmp, scrShot );
			
			AutoLogger.log.info("错误截图保存在:" + scrName );
			
		} catch ( IOException e ) {
			
			e.printStackTrace();
			
			AutoLogger.log.error("截图失败");
			
		}
		
	}
	
}
