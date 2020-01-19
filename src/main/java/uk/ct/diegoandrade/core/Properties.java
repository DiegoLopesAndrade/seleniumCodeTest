package uk.ct.diegoandrade.core;

public class Properties {
	
	public static boolean FECHAR_BROWSER = false;
	
	public static Browsers browser = Browsers.CHROME;
	
	public static String NAME_ACCOUNT_CHANGED = "Acccount Changed " + System.nanoTime();  
	
	public enum Browsers {
		CHROME,
		FIREFOX
	}

}
