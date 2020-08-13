package com.distribuida;

import io.helidon.microprofile.server.Server;

public class ServerMain {
	// ESTA CLASE SIRVE COMO MAIN EN CASO DE NO QUERER EJECUTAR EL MAIN DE HELIDON
	public static void main(String args[]) {
		Server server = Server.create()
				.start();
		System.out.printf(" Servidor disponible en http://127.0.0.1:%d", server.port() );
	}

}
