package com.i3v.server_api.repository;

import com.i3v.server_api.entity.Client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByName(String name);
}
