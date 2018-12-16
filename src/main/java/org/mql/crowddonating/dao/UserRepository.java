package org.mql.crowddonating.dao;

import org.mql.crowddonating.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}