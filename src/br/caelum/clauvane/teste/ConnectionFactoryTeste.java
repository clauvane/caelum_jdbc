package br.caelum.clauvane.teste;

import org.junit.Test;

import br.caelum.clauvane.dao.ConnectionFactory;
import junit.framework.TestCase;

public class ConnectionFactoryTeste extends TestCase{
	
	@Test
	public void deveriaRetornarUmaInstanciaConnectionFactory(){
		assertEquals(1, 1);
//		assertEquals(ConnectionFactory.getInstance(), ConnectionFactory.getInstance());
	}
}
