package com.autotest.driverself;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * 
 * @author Administrator
 * @FFDriver:火狐浏览器驱动类
 * 
 */

public class FFDriver
{
	public WebDriver driver = null;
	
	public FFDriver( String propath, String driverpath )
	{
		//设置 Firefox 驱动的路径
		System.setProperty( "webdriver.gecko.driver", driverpath );
		
		//设置 Firefox 的安装目录，如果不需要设置，那么参数给一个空字符串
		if ( propath != null && propath.length() > 0 )
		{
			System.setProperty( "webdriver.firefox.bin", propath );
			
		}
		
		//加载火狐浏览器的用户文件
		FirefoxOptions firefoxOptions = new FirefoxOptions()
		{
			FirefoxProfile profile = new FirefoxProfile();
		};
		
		//创建一个 Firefox 的浏览器实例，赋值给成员变量
		try {
			
			driver = new FirefoxDriver( firefoxOptions );
			
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
