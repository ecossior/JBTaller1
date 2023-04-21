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
public class ProductoActualizarTest {
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
	 Flujo Básico: Se llena todos los campos del  Producto (producto, Categoria y Precio) y   se   lleva   la 
	 actualización de una nueva categoría de manera exitosa
	 */
	@Order(1)
	@Test
	void actualizarProductoExitosoTest() throws InterruptedException {
		
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
		String productoABuscar = "COCA QUINA";
		WebElement filtrarTxt = driver.findElement(By.id("txtFiltro"));
		filtrarTxt.sendKeys(productoABuscar);
		
		Thread.sleep(2000);
		WebElement filtrarBtn = driver.findElement(By.name("btnFiltrar"));
		filtrarBtn.click();
		
		String locator = String.format("//td[contains(.,'%s')]", productoABuscar);		
		WebElement cell =  driver.findElement(By.xpath(locator));
		cell.click();
		
		WebElement actualizarBtn = driver.findElement(By.id("btnActualizar"));
		actualizarBtn.click();
				
		Thread.sleep(2000);
		String actualizarproducto = "COCO";
		String actualizarCategoria = "GALLETAS";
		String actualizarPrecio = "7";
		
		WebElement nombreTxt = driver.findElement(By.id("txtNombre"));
		nombreTxt.clear();
		nombreTxt.sendKeys(actualizarproducto);
		
		WebElement webElement = driver.findElement(By.id("cboCategoria"));		
		Select categoriaCbo = new Select(webElement);
		categoriaCbo.selectByVisibleText(actualizarCategoria);
				
		WebElement precioTxt = driver.findElement(By.id("txtPrecio"));
		precioTxt.clear();
		precioTxt.sendKeys(actualizarPrecio);
				
		WebElement guardarBtn = driver.findElement(By.id("btnGuardar"));
		guardarBtn.click();
		
		Thread.sleep(2000);
		WebElement infoMessage = driver.findElement(By.className("ui-messages-info-summary"));
		String actualMessage = infoMessage.getText();
		String expecMessage =	"Se actualizó de manera correcta el Producto";
		Assertions.assertEquals(actualMessage, expecMessage);

	}
	
	/*	
	 Flujo Alternativo: 
	 Se  deja  el  campo  nombre  vacío  y  se  muestra  mensaje  de advertencia.
	 * */
	@Order(2)
	@Test
	void actualizarNomreConVacioTest() throws InterruptedException {		

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
		String productoABuscar = "MANI";
		WebElement filtrarTxt = driver.findElement(By.id("txtFiltro"));
		filtrarTxt.sendKeys(productoABuscar);
		
		Thread.sleep(2000);
		WebElement filtrarBtn = driver.findElement(By.name("btnFiltrar"));
		filtrarBtn.click();
		
		String locator = String.format("//td[contains(.,'%s')]", productoABuscar);		
		WebElement cell =  driver.findElement(By.xpath(locator));
		cell.click();
		
		WebElement actualizarBtn = driver.findElement(By.id("btnActualizar"));
		actualizarBtn.click();
				
		Thread.sleep(2000);
		String actualizarProducto = "";
		String actualizarCategoria = "GASEOSAS";
		String actualizarPrecio = "20";
		
		WebElement nombreTxt = driver.findElement(By.id("txtNombre"));
		nombreTxt.clear();
		nombreTxt.sendKeys(actualizarProducto);
		
		Thread.sleep(2000);
		WebElement webElement = driver.findElement(By.id("cboCategoria"));		
		Select categoriaCbo = new Select(webElement);
		categoriaCbo.selectByVisibleText(actualizarCategoria);
		
		Thread.sleep(2000);
		WebElement precioTxt = driver.findElement(By.id("txtPrecio"));
		precioTxt.clear();
		precioTxt.sendKeys(actualizarPrecio);
		
		Thread.sleep(2000);
		WebElement guardarBtn = driver.findElement(By.id("btnGuardar"));
		guardarBtn.click();
		
		Thread.sleep(2000);
		WebElement infoMessage = driver.findElement(By.className("ui-messages-error-summary"));
		String actualMessage = infoMessage.getText();
		String expecMessage =	"Nombre: Validation Error: Value is required.";
		Assertions.assertEquals(actualMessage, expecMessage);

	}
	
	/*	
	 Flujo Alternativo:
	 No  se  selecciona  ningún  elemento  para  editar  de  la  grilla  de búsqueda
	 * */
	
	@Order(3)
	@Test
	void actualizarProductoVacioTest() throws InterruptedException {		

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
				
		WebElement actualizarBtn = driver.findElement(By.id("btnActualizar"));
		actualizarBtn.click();
		
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
