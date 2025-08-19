package com.acsabo.server_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDTO {
	private Long id;
	private String name;
	private String email;
}
