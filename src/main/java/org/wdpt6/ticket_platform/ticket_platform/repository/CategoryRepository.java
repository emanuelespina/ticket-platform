package org.wdpt6.ticket_platform.ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wdpt6.ticket_platform.ticket_platform.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
