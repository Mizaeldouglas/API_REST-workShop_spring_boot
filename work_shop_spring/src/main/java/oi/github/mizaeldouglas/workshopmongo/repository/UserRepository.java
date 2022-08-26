package oi.github.mizaeldouglas.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import oi.github.mizaeldouglas.workshopmongo.domain.User;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

}