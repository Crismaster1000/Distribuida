package com.programacion.servicios;

import javax.enterprise.context.ApplicationScoped;

// Componente de ambito de aplicacion que implementa una interfaz

@ApplicationScoped
public class ServicioOperacionesImpl implements ServicioOperaciones {

	@Override
	public int sumar(int x, int y) {

		return x + y;

	}

}
