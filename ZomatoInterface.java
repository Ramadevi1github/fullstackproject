package com.project.fullstack1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.fullstack1.blueprint.Zomato;



public interface ZomatoInterface extends JpaRepository<Zomato, Integer> {

}
