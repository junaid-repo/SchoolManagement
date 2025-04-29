package com.splitwise.configserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;


@Configuration
public class AzureKVConfig {
	
	@Autowired
	private KeyVaultProperties keyVaultProperties;

	@Bean
	public KeyVaultSecret getKeyValultSecret() {
		String secretName = "db-url";
		String fetchedSecret = null;
		KeyVaultSecret secret=null;

		try {
			// Create a SecretClient instance to interact with Azure Key Vault
			SecretClient secretClient = new SecretClientBuilder().vaultUrl(keyVaultProperties.getVaultUrl())
					.credential(new ClientSecretCredentialBuilder().tenantId(keyVaultProperties.getTenantId())
							.clientId(keyVaultProperties.getClientId())
							.clientSecret(keyVaultProperties.getClientSecret()).build())
					.buildClient();

			// Retrieve the Base64-encoded secret from Azure Key Vault
			 secret = secretClient.getSecret(secretName);
			fetchedSecret = "Fetched Secret Values is " + secret.getValue();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return secret;
	}
	
}
