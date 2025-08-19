package com.acsabo.server_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acsabo.server_api.dto.ClientDTO;
import com.acsabo.server_api.exception.ResourceNotFoundException;
import com.acsabo.server_api.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;

	public List<ClientDTO> searchClients() {
        
        List<ClientDTO> result = null;

        try {
            result = clientRepository.findAll().stream()
				.map(client -> new ClientDTO(client.getId(), client.getName(), null))
				.collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error while searching clients: {}", e.getMessage());
        }

		return result;
	}

    public ClientDTO searchClientByName(String name) {
        return clientRepository.findByName(name)
            .map(client -> new ClientDTO(client.getId(), client.getName(), null))
            .orElseThrow(() -> new ResourceNotFoundException("Client not found with name: " + name));
    }
}
