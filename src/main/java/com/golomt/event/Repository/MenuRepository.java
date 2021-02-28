package com.golomt.event.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.golomt.event.Model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{

}
