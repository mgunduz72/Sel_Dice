package dice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Homework {
	
	/*HOMEWORK
	 * 1)Create arraylist of keywords.
add 20 different keyworks
list.add("java");

pass each item to search box and print accordingly.
modify your arraylist 

java-1234

2) Store all keywords into a text file 
read the text file and  repeat above steps.

store keyword and results count into an arraylist.
----

after closing browser.
print contents of arraylist that was updated each time 
we looped.

commit > push > share your github link

	 * 
	 */
	public static void main(String[] args) {
		
     // We can print time, when test is started		
		System.out.println("TEst started - " + LocalDateTime.now());
		
		  WebDriverManager.chromedriver().setup();   	  
		 //creating driver object
	    	   WebDriver driver = new ChromeDriver();
	    	   //maximizing window
	    	   driver.manage().window().maximize();
	      
	      //creating array list to hold keywords
		
		
		List<String> jobList = new ArrayList<>();
		
		String line; 
		try(
		FileReader fr = new FileReader("keywords.txt");
		BufferedReader bf = new BufferedReader(fr);
				){
			while((line = bf.readLine() ) != null) {
				jobList.add(line);
			}
			
		}catch (IOException e) {
			System.out.println("IO");
		}
		
		// now we can loop through each keyword and search one by one
	   try {
		loop:
		for (int i = 0; i < jobList.size(); i++) {
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		    driver.get("http://www.dice.com");
		    
		    // assign arraylist items to a variable
		    String keywords = jobList.get(i);
		    //send variable keyword to field keyword
		    
		   
		    driver.findElement(By.id("search-field-keyword")).sendKeys(keywords);
		    //send location
		    driver.findElement(By.id("search-field-location")).clear();
		    driver.findElement(By.id("search-field-location")).sendKeys("Dallas, TX");
		    //Click  find job buttons
		    driver.findElement(By.id("findTechJobs")).click();
		    
		    // assigning String value of Count to a variable
		    String count = null;
		    if(driver.getTitle().equalsIgnoreCase("Jobs not found | Dice.com")) {
		    	System.out.println("for keywords - " +keywords + "0 jobs found");
		    	driver.navigate().back();
		    	continue loop;
		    }else { 
		    	count = driver.findElement(By.id("posiCountId")).getText();
		    }
		    System.out.println("For keyword :" + keywords + " found : " + count + " positions");
		}
		
		//print time when test ended
        System.out.println("Test complited - " + LocalDateTime.now());		
		
	   
      } catch (NoSuchElementException e) {
      	e.printStackTrace() ;

    }
     
		
}
}

		
	
	
