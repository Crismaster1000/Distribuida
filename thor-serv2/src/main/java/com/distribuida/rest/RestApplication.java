package com.distribuida.rest;

import java.net.InetAddress;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import javax.ws.rs.core.Application;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryForever;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;
import org.eclipse.microprofile.config.inject.ConfigProperty;

//import com.bettercloud.vault.Vault;
//import com.bettercloud.vault.VaultConfig;
//import com.bettercloud.vault.VaultException;


@ApplicationPath("/")
@ApplicationScoped
public class RestApplication extends Application {
	
	@Inject
	@ConfigProperty(name = "thorntail.http.port", defaultValue = "8080")
	private Integer port;

	@Inject
	@ConfigProperty(name = "thorntail.port.offset")
	private Integer offset;

	public void init(@Observes @Initialized(ApplicationScoped.class) Object event) throws Exception {

//		String vaulttoken = System.getenv("VAULT_TOKEN");
//		String vaulthost = System.getenv("VAULT_ADDR");
//		System.out.format("Using Vault Host %s\n", vaulthost);
//		System.out.format("With Vault Token %s\n", vaulttoken);
//
//		final VaultConfig config = new VaultConfig().build();
//		final Vault vault = new Vault(config);
//
//		try {
//			final String value = vault.logical().read("secret/customer").getData().get("pruebaVault");
//			System.out.format("pruebaVault key in secret/customer is " + value + "\n");
//			System.setProperty("thorntail.http.port",value);
//
//		} catch (VaultException e) {
//			System.out.println("Exception thrown: " + e);
//		}

		System.out.println("Init");
		InetAddress direccion = InetAddress.getLocalHost();
		String nombreDelHost = direccion.getHostName();// nombre host
		String IP_local = direccion.getHostAddress();// ip como String
		System.out.println("La IP de la maquina local es : " + IP_local);
		System.out.println("El nombre del host local es : " + nombreDelHost);

		////// zuukeeper
		CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("localhost:2181", new RetryForever(5));

		curatorFramework.start();

		ServiceInstance<Object> serviceInstance = ServiceInstance.builder()
				.id("OrdersID:" + (port + offset))
				.name("Orders")
				.port(port + offset)
				.address(IP_local)
				.uriSpec(new UriSpec("{scheme}://{address}:{port}"))
				.build();

		ServiceDiscoveryBuilder.builder(Object.class)
			.basePath("services")
			.client(curatorFramework)
			.thisInstance(serviceInstance)
			.build()
			.start();

	}

	public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object event) {
		System.out.println("Destroy");

	}

}
