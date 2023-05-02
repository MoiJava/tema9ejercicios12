package com.ciudades;

import java.util.List;
import java.util.Scanner;

import com.ciudades.dao.CiudadDAO;
import com.ciudades.model.Ciudad;

public class App {
	
	static void mostrarMenu() {
		System.out.println("MENÚ");
		System.out.println("1. Insertar ciudad");
		System.out.println("2. Eliminar ciudad");
		System.out.println("3. Actualizar nombre de ciudad");
		System.out.println("4. Actualizar habitantes de ciudad");
		System.out.println("5. Listar ciudades");
		System.out.println("6. Mostrar ciudad");
		System.out.println("7. Listar ciudades con más de 1 millón de habitantes");
		System.out.println("8. Listar ciudades con menos de x habitantes");
		System.out.println("9. Mostrar habitantes dado el nombre");
		System.out.println("0. Salir");
		System.out.print("Seleccione opción: ");
	}

	public static void main(String[] args) {
		
		int opcion;
		int idc;
		String nomc;
		long habsc;
		Scanner s=new Scanner(System.in);
		Ciudad c=null;
		CiudadDAO cd=new CiudadDAO();
		List<Ciudad> ciudades=null;
		
		do {
			mostrarMenu();
			opcion=s.nextInt();
			switch (opcion) {
			case 1: 
				System.out.print("Introduzca nombre: ");
				nomc=s.next();
				System.out.print("Introduzca nº de habitantes: ");
				habsc=s.nextInt();
				c=new Ciudad(nomc,habsc);
				cd.insertCiudad(c);
				break;
			case 2: 
				System.out.print("Introduzca id de ciudad: ");
				idc=s.nextInt();
				cd.deleteciudad(idc);
				break;
			case 3: 
				System.out.print("Introduzca id de ciudad: ");
				idc=s.nextInt();
				System.out.print("Introduzca nuevo nombre: ");
				nomc=s.next();
				c=cd.selectCiudadById(idc);
				c.setNom(nomc);
				cd.updateCiudad(c);
				break;
			case 4: 
				System.out.print("Introduzca id de ciudad: ");
				idc=s.nextInt();
				System.out.print("Introduzca nuevo nº de habitantes: ");
				habsc=s.nextInt();
				c=cd.selectCiudadById(idc);
				c.setHabs(habsc);
				cd.updateCiudad(c);
				break;
			case 5: 
				ciudades=cd.selectAllCiudades();
				System.out.println("Ciudades:");
				ciudades.forEach(ciu->System.out.println(ciu.getId()+" "+ciu.getNom()+" "+ciu.getHabs()));
				break;
			case 6: 
				System.out.print("Introduzca id de ciudad: ");
				idc=s.nextInt();
				c=cd.selectCiudadById(idc);
				if (c!=null) {
					System.out.println(c.getNom()+" "+c.getHabs());
				} else {
					System.out.println("Ciudad no encontrada");
				}
				break;	
			case 7:
				ciudades=cd.selectCiudadesMillon();
				System.out.println("Ciudades:");
				if (ciudades.size()!=0) {
					ciudades.forEach(ciu->System.out.println(ciu.getId()+" "+ciu.getNom()+" "+ciu.getHabs()));
				} else {
					System.out.println("No se ha encontrado ninguna ciudad con esos parámetros");
				}
				break;
			case 8:
				System.out.print("Introduzca nº de habitantes: ");
				habsc=s.nextInt();
				ciudades=cd.selectCiudadesMenos(habsc);
				System.out.println("Ciudades:");
				if (ciudades.size()!=0) {
					ciudades.forEach(ciu->System.out.println(ciu.getId()+" "+ciu.getNom()+" "+ciu.getHabs()));
				} else {
					System.out.println("No se ha encontrado ninguna ciudad con esos parámetros");
				}
				break;
			case 9:
				System.out.print("Introduzca nombre: ");
				nomc=s.next();
				c=cd.selectCiudadByName(nomc);
				if (c!=null) {
					System.out.println(c.getHabs());
				} else {
					System.out.println("No se ha encontrado ninguna ciudad con ese nombre");
				}
				break;
			default: System.out.println("Fin");
			}
		} while (opcion!=0);
	}
}
