package com.tutorialsninja.qa.Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class courseunitdummy {


    public static void main(String[] args) throws Exception{

 

        WebDriver driver;
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://csuat.sdt.manchester.ac.uk/psp/csuat/?cmd=login&languageCd=ENG&");
        driver.findElement(By.xpath("/html/body/div/form/div/div[1]/div[3]/input")).sendKeys("11063096");
        driver.findElement(By.xpath("/html/body/div/form/div/div[1]/div[5]/input")).sendKeys("UAT123");
        driver.findElement(By.xpath("/html/body/div/form/div/div[1]/div[8]/input")).click();
        //Alert confirmationAlert = driver.switchTo().alert();
        //System.out.println(confirmationAlert.getText());
        Thread.sleep(3000);
        // Click on Manage classes
       // driver.findElement(By.id("win0groupletPTNUI_LAND_REC_GROUPLET$2")).click();
        driver.findElement(By.xpath("//div[@id='win0groupletPTNUI_LAND_REC_GROUPLET$2']")).click();
        Thread.sleep(3000);
        //click on first mandatory course
        driver.findElement(By.xpath("//a[@name='CY2_MEP_WRK_DESCR_DRILL$2']")).click();
        Thread.sleep(2000);
        //click on return button
        driver.findElement(By.id("CY2_MEP_WRK_SSR_PB_CONTINUE")).click();
        
        //click on View my classes option
        
        

    }

}