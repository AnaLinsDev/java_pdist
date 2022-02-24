package br.com.ifpb.gpsback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifpb.gpsback.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
