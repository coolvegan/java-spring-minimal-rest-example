package com.kittel.todoapp.Repository;

import com.kittel.todoapp.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query(value = "SELECT t FROM Todo t where t.finished = :status")
    List<Todo> findByStatus(@Param("status") boolean status);
}
