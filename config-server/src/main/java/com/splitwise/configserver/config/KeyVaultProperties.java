package com.splitwise.configserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class KeyVaultProperties {

	@Value("${azure.keyvault.vaultUrl}")
	private String vaultUrl;
	@Value("${azure.keyvault.tenantId}")
	private String tenantId;
	@Value("${azure.keyvault.clientId}")
	private String clientId;
	@Value("${azure.keyvault.clientSecret}")
	private String clientSecret;
	// Standard constructors, getters and setters
}
