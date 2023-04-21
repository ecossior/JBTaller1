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
public class ProductoEliminarTest {
	
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
	 Flujo Básico: Se  selecciona  un  elemento  de  la  grilla  de  búsqueda  y  se elimina correctamente. 
	 */
	@Order(1)
	@Test
	void eliminarProductoExitosoTest() throws InterruptedException {
		
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
		String productoABuscar = "COCO";
		WebElement filtrarTxt = driver.findElement(By.id("txtFiltro"));
		filtrarTxt.sendKeys(productoABuscar);
		
		Thread.sleep(2000);
		WebElement filtrarBtn = driver.findElement(By.name("btnFiltrar"));
		filtrarBtn.click();
		
		Thread.sleep(2000);
		String locator = String.format("//td[contains(.,'%s')]", productoABuscar);		
		WebElement cell =  driver.findElement(By.xpath(locator));
		cell.click();
		
		Thread.sleep(2000);
		WebElement actualizarBtn = driver.findElement(By.id("btnEliminar"));
		actualizarBtn.click();
				
		Thread.sleep(2000);		
		WebElement siBtn = driver.findElement(By.id("btnSi"));
		siBtn.click();
		
		Thread.sleep(2000);
		WebElement infoMessage = driver.findElement(By.className("ui-messages-info-summary"));
		String actualMessage = infoMessage.getText();
		String expecMessage =	"Se eliminó de manera correcta el Producto";
		Assertions.assertEquals(actualMessage, expecMessage);
	}

	 /*
 	  Alternativo: No se selecciona ningún elemento para eliminar de la grilla de búsqueda y se intenta eliminar.
	 */
	@Order(2)
	@Test
	void eliminarSinSeleccionarProductoTest() throws InterruptedException {
		
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
				
		WebElement actualizarBtn = driver.findElement(By.id("btnEliminar"));
		actualizarBtn.click();
				
		Thread.sleep(2000);		
		WebElement siBtn = driver.findElement(By.id("btnSi"));
		siBtn.click();
		
		Thread.sleep(2000);
		WebElement infoMessage = driver.findElement(By.className("ui-messages-warn-summary"));
		String actualMessage = infoMessage.getText();
		String expecMessage =	"No ha seleccionado un Producto";
		Assertions.assertEquals(actualMessage, expecMessage);
	}
	
	@AfterEach
	void cerrarDriver(){
		driver.quit();
	}

}
