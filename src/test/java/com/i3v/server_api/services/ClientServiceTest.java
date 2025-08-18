package com.i3v.server_api.services;

import com.i3v.server_api.dto.ClientDTO;
import com.i3v.server_api.entity.Client;
import com.i3v.server_api.exception.ResourceNotFoundException;
import com.i3v.server_api.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchClients() {
        Client client1 = new Client();
        client1.setId(1L);
        client1.setName("Alice");
        Client client2 = new Client();
        client2.setId(2L);
        client2.setName("Bob");
        Mockito.when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));
        List<ClientDTO> result = clientService.searchClients();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Bob", result.get(1).getName());
    }

    @Test
    void testSearchClientByNameFound() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Alice");
        Mockito.when(clientRepository.findByName("Alice")).thenReturn(Optional.of(client));
        ClientDTO dto = clientService.searchClientByName("Alice");
        assertNotNull(dto);
        assertEquals("Alice", dto.getName());
    }

    @Test
    void testSearchClientByNameNotFound() {
        Mockito.when(clientRepository.findByName("Unknown")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> clientService.searchClientByName("Unknown"));
    }
}
