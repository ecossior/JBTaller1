package com.jbgroup.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import io.github.bonigarcia.wdm.WebDriverManager;

@TestMethodOrder(OrderAnnotation.class)
public class ProductoListarTest {
WebDriver driver;
	
	@BeforeAll
	static void setupDriverMangement() {
		WebDriverManager.chromedriver().setup();
		
	}
	@BeforeEach
	void instanciarDriver() {
		driver = new ChromeDriver();
		
	}
	
	/*
	 Flujo Básico: Se llena el campo filtro y devuelve toda la información de las categorías que cumplan con el filtro	 
	 * 
	 * */
	@Order(1)
	@Test
	void filtrarProductoExitosoTest() throws InterruptedException {
		
		driver.get("http://localhost:8080/VisorWeb/index.xhtml");
		
		WebElement userTxt = driver.findElement(By.id("txtUsuario"));
		userTxt.clear();		
		userTxt.sendKeys("admin");
		
		WebElement pwdTxt = driver.findElement(By.id("txtClave"));
		pwdTxt.clear();
		pwdTxt.sendKeys("clave");
		
		WebElement sesionBtn = driver.findElement(By.name("btnIniciarSesion"));
		sesionBtn.click();
		
		Thread.sleep(2000);
		String expecTitle ="Bienvenido(a) al Sistema Visor de Almacén";
		WebElement actualTitle = driver.findElement(By.xpath("//div[@class='title-main']"));		
		Assertions.assertEquals(expecTitle, actualTitle.getText());
		
		Thread.sleep(2000);
		WebElement menu = driver.findElement(By.xpath("//div[@class='menu']/div"));
		menu.click();
		
		Thread.sleep(2000);
		WebElement menuModAlmacen = driver.findElement(By.xpath("//span[contains(.,'Mod. de Almacén')]"));
		menuModAlmacen.click();
		
		Thread.sleep(2000);
		WebElement menuCategoria = driver.findElement(By.xpath("//a[.='Mnt. de Productos']"));
		menuCategoria.click();
				
		Thread.sleep(2000);
		String productoABuscar = "SUBLIME";
		WebElement filtrarTxt = driver.findElement(By.id("txtFiltro"));
		filtrarTxt.sendKeys(productoABuscar);
		
		Thread.sleep(2000);
		WebElement filtrarBtn = driver.findElement(By.name("btnFiltrar"));
		filtrarBtn.click();
		
		Thread.sleep(2000);
		String locator = String.format("//td[contains(.,'%s')]", productoABuscar);		
		WebElement cell =  driver.findElement(By.xpath(locator));
		Boolean displayed = cell.isDisplayed();
		String actual = cell.getText();
		String expec =	"SUBLIME";
		Assertions.assertTrue(displayed);
		Assertions.assertEquals(actual, expec);
	}
	
	
	
	/*
	 Flujo Alternativo: Se llena el campo filtro y no encuentra ninguna categoría que coincida con el filtro.
	 * 
	 * */
	@Order(2)
	@Test
	void filtrarProductoQueNoExisteTest() throws InterruptedException {
		
		driver.get("http://localhost:8080/VisorWeb/index.xhtml");
		
		WebElement userTxt = driver.findElement(By.id("txtUsuario"));
		userTxt.clear();		
		userTxt.sendKeys("admin");
		
		WebElement pwdTxt = driver.findElement(By.id("txtClave"));
		pwdTxt.clear();
		pwdTxt.sendKeys("clave");
		
		WebElement sesionBtn = driver.findElement(By.name("btnIniciarSesion"));
		sesionBtn.click();
		
		Thread.sleep(2000);
		String expecTitle ="Bienvenido(a) al Sistema Visor de Almacén";
		WebElement actualTitle = driver.findElement(By.xpath("//div[@class='title-main']"));		
		Assertions.assertEquals(expecTitle, actualTitle.getText());
		
		Thread.sleep(2000);
		WebElement menu = driver.findElement(By.xpath("//div[@class='menu']/div"));
		menu.click();
		
		Thread.sleep(2000);
		WebElement menuModAlmacen = driver.findElement(By.xpath("//span[contains(.,'Mod. de Almacén')]"));
		menuModAlmacen.click();
		
		Thread.sleep(2000);
		WebElement menuCategoria = driver.findElement(By.xpath("//a[.='Mnt. de Productos']"));
		menuCategoria.click();
				
		Thread.sleep(2000);
		String productoABuscar = "POLLITO";
		WebElement filtrarTxt = driver.findElement(By.id("txtFiltro"));
		filtrarTxt.sendKeys(productoABuscar);
		
		Thread.sleep(2000);
		WebElement filtrarBtn = driver.findElement(By.name("btnFiltrar"));
		filtrarBtn.click();
				
		Thread.sleep(2000);
		WebElement cell =  driver.findElement(By.xpath("//td[contains(.,'No existe registros para la consulta')]"));
		Boolean displayed = cell.isDisplayed();
		Assertions.assertTrue(displayed);
	}
	
	
	@AfterEach
	void cerrarDriver(){
		driver.quit();
	}

}
