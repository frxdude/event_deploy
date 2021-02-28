package com.golomt.event.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.golomt.event.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
