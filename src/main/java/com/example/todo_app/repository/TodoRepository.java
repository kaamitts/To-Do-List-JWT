package com.example.todo_app.repository;

import com.example.todo_app.enumiration.Status;
import com.example.todo_app.model.Todo;
import com.example.todo_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "select t from Todo t where t.user = :user")
    List<Todo> findByUser(@Param(value = "user") User user);

    @Query(value = "select t from Todo t where t.id= :id and t.user = :user")
    Optional<Todo> findByIdAndUser(@Param(value = "id") long id, @Param(value = "user") User user);

    @Query("select t from Todo t where t.user = :user and t.status = :status")
    List<Todo> findByUserAndStatus(@Param("user") User user, @Param("status") Status status);
}
