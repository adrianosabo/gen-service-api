
package com.acsabo.server_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import com.acsabo.server_api.dto.ClientDTO;
import com.acsabo.server_api.exception.ResourceNotFoundException;
import com.acsabo.server_api.services.ClientService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;


    @GetMapping("/search")
    @Operation(summary = "Busca todos os clientes", description = "Retorna uma lista de todos os clientes cadastrados.")
    public ResponseEntity<List<ClientDTO>> searchClients() {
        List<ClientDTO> clients = clientService.searchClients();
        return ResponseEntity.ok(clients);
    }


    @GetMapping("/by-name")
    @Operation(summary = "Busca cliente pelo nome", description = "Retorna um cliente específico pelo nome.")
    public ResponseEntity<ClientDTO> searchClientByName(
            @Parameter(description = "Nome do cliente a ser buscado") @RequestParam String name) {
        ClientDTO client = clientService.searchClientByName(name);
        if (client == null) {
            throw new ResourceNotFoundException("Client not found with name: " + name);
        }
        return ResponseEntity.ok(client);
    }
    
}
