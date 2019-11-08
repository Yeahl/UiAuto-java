package com.autotest.driverself;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * 
 * @author Administrator
 * @GoogleDriver:谷歌浏览器驱动类
 */

public class GoogleDriver
{
	private WebDriver driver = null;
	
	public GoogleDriver( String driverpath )
	{
		//设置 Chrome 的路径
		System.setProperty( "webdriver.chrome.driver", driverpath );
		
		// Chrome 浏览器参数对象
		ChromeOptions option = new ChromeOptions();
		
		//去除 Chrome 浏览器上的被自动化软件操作警告
		option.addArguments( "disable-infobars" );
		
		//加载 Chrome 用户文件
//		option.addArguments( "--user-data-dir=C:\\Users\\\\roy08\\\\AppData\\\\Local\\\\Google\\\\Chrome\\\\User Data" );
		
		//最大化浏览器窗口
		option.addArguments( "--start-maximized" );
		 
		try {  //创建一个 Chrome 的浏览器实例
			
			this.driver = new ChromeDriver( option );
			
			//让浏览器访问空白页
			driver.get( "about:blank" );
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
			
			System.out.println( "log--error：创建driver失败！！" );
		}
	}
	
	public WebDriver getdriver() 
	{
		return this.driver;
	}
	
}
