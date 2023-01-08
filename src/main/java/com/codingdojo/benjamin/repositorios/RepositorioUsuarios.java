package com.codingdojo.benjamin.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.benjamin.modelos.User;

@Repository
public interface RepositorioUsuarios extends CrudRepository<User, Long> {

	User findByEmail(String email);
}
