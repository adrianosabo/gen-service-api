package com.i3v.server_api.controller;

import com.i3v.server_api.dto.ClientDTO;
import com.i3v.server_api.services.ClientService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/clients/search")
    public List<ClientDTO> searchClients() {
        return clientService.searchClients();
    }
}
