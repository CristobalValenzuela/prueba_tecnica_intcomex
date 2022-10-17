package com.incomex.api.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration("tokenProperties")
@ConfigurationProperties(prefix = "key.security.token")
@Data
public class Configurations implements Serializable{

	private static final long serialVersionUID = -8973497443711364401L;
	private String secretKey;
	private int expiration;
}
