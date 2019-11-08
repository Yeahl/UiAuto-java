package com.autotest.webKeyword;

public class TestingShopLogin
{
	public static void main (String[] args)
	{
		KeywordOfWeb kw = new KeywordOfWeb();
		
		kw.openBrowser("chrome");
		
		kw.visitWeb("http://www.testingedu.com.cn:8000/");
		
		
	}
}
