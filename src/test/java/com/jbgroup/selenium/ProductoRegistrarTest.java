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
import org.openqa.selenium.support.ui.Select;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;


import io.github.bonigarcia.wdm.WebDriverManager;

@TestMethodOrder(OrderAnnotation.class)
public class ProductoRegistrarTest {
	
	WebDriver driver;
	
	@BeforeAll
	static void setupDriverMangement() {
		WebDriverManager.chromedriver().setup();
		
	}
	
	@BeforeEach
	void instanciarDriver() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	/*
	 *	Flujo Básico: Se llena todos los campos de Producto y se lleva el registro de una nuevo Producto
	 *	de manera exitosaoFlujo
	 */
	@Order(1)
	@Test
	void registrarProductoExitosoTest() throws InterruptedException {
		
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
				
		WebElement nuevoBtn = driver.findElement(By.id("btnNuevo"));
		nuevoBtn.click();
				
		Thread.sleep(2000);
		String nuevoproducto = "COCA QUINA";
		String nuevoCategoria = "FANTA";
		String nuevoPrecio = "20";
		
		WebElement nombreTxt = driver.findElement(By.id("txtNombre"));
		nombreTxt.clear();
		nombreTxt.sendKeys(nuevoproducto);
		
		WebElement webElement = driver.findElement(By.id("cboCategoria"));		
		Select categoriaCbo = new Select(webElement);
		categoriaCbo.selectByVisibleText(nuevoCategoria);
				
		WebElement precioTxt = driver.findElement(By.id("txtPrecio"));
		precioTxt.clear();
		precioTxt.sendKeys(nuevoPrecio);
		
		Thread.sleep(2000);
		WebElement guardarBtn = driver.findElement(By.id("btnGuardar"));
		guardarBtn.click();
		
		Thread.sleep(2000);
		WebElement infoMessage = driver.findElement(By.className("ui-messages-info-summary"));
		String actualMessage = infoMessage.getText();
		String expecMessage =	"Se guardó de manera correcta el Producto";
		Assertions.assertEquals(actualMessage, expecMessage);

	}
		
	
	/* 
	 * Alternativo: Se  deje  el  campo  nombre  vacío  y  se  muestra  mensaje  de advertencia.
	 */
	@Order(2)
	@Test
	void registrarProductoConDatosVaciosTest() throws InterruptedException {
		
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
		WebElement nuevoBtn = driver.findElement(By.id("btnNuevo"));
		nuevoBtn.click();
		
		Thread.sleep(2000);
		WebElement nombreTxt = driver.findElement(By.id("txtNombre"));
		nombreTxt.clear();
		nombreTxt.sendKeys("");
		
		Thread.sleep(2000);
		WebElement webElement = driver.findElement(By.id("cboCategoria"));		
		Select categoriaCbo = new Select(webElement);
		categoriaCbo.selectByVisibleText("FANTA");
				
		Thread.sleep(2000);
		WebElement precioTxt = driver.findElement(By.id("txtPrecio"));
		precioTxt.clear();
		precioTxt.sendKeys("10");
		
		Thread.sleep(2000);
		WebElement guardarBtn = driver.findElement(By.id("btnGuardar"));
		guardarBtn.click();
		
		Thread.sleep(2000);
		WebElement errorMessage = driver.findElement(By.className("ui-messages-error-summary"));
		String actualMessage = errorMessage.getText();
		String expecMessage =	"Nombre: Validation Error: Value is required.";
		Assertions.assertEquals(actualMessage, expecMessage);
	}	
	
	@AfterEach
	void cerrarDriver(){
		driver.quit();
	}
}
