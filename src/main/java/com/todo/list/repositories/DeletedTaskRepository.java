package com.todo.list.repositories;

import com.todo.list.models.DeletedTaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeletedTaskRepository extends JpaRepository<DeletedTaskModel, Long> {
    Optional<DeletedTaskModel> findByDeletedId(Long deletedId);
    // repository to store DeletedTaskModel data
}
