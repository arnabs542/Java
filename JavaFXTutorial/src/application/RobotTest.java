package application;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;


public class RobotTest {
	
	public static void main(String[]args) throws IOException, URISyntaxException, AWTException, InterruptedException {
		Desktop desk = Desktop.getDesktop();
		desk.browse(new URI("https://www.google.com"));
		
		String line;
		String pidInfo;
		do {
			Thread.sleep(4000);
			pidInfo = "";
			
			Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\"+"tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			while ((line = input.readLine()) != null) {
				pidInfo += line;
			}
			input.close();
		} while (!(pidInfo.contains("chrome.exe") || (pidInfo.contains("firefox.exe"))));
		
		Thread.sleep(1000);
		String str = "your mom gay";
		Robot robot = new Robot();
		for (char c: str.toCharArray() ) {
			Thread.sleep(100);
			robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
			robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		System.exit(0);
	}
	
}
