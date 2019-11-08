package com.autotest.driverself;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * 
 * @author 
 * @IEDriver：ie浏览器驱动类
 *
 */

public class IEDriver
{
	
	public WebDriver driver;
	
	// webdriver 连接启动浏览器时，启动服务
	public InternetExplorerDriverService service = null;
	
	public IEDriver( String driverpath )
	{
		//设置 ie 浏览器的路径
		System.setProperty( "webdriver.ie.driver", driverpath );
		
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		
		//设置忽略安全校验
		ieCapabilities.setCapability( InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true );
		
		//创建一个 iedriver 的服务，用于连接 ie
		try {
			//使用指定的IEdriver 文件以及任意空闲端口完成服务的启动
			service = new InternetExplorerDriverService.Builder().usingDriverExecutable( new File( driverpath )).usingAnyFreePort().build();
			
			service.start();
			
		} catch ( IOException e ) {
			
			e.printStackTrace();
			
			System.out.println( "log--error：service启动错误！" );
		}
		
		try {
			//创建一个IE的浏览器实例
			this.driver = new RemoteWebDriver( service.getUrl(),ieCapabilities );
			
			//让浏览器访问空白页面
			driver.get( "about:blank" );
			
		} catch ( Exception e ) {
			
			e.printStackTrace();
			
			System.out.println( "log--error：创建driver失败！！" );
		}
	
	}
	
	//将构造函数中实例化的成员变量 driver对象通过该方法返回
	public WebDriver getdriver() 
	{
		return this.driver;
	}
	
	//由手动启动了driverserver服务，因此关闭时除了调用quit，也一样将service服务关闭
	public void closeIE() {
	
		driver.quit();
		
		service.stop();
	}
}
