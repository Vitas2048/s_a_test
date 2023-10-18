package ru.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.app.model.Result;

import java.util.Optional;

public interface ResultRepository extends JpaRepository< Result, Integer> {

    public Optional<Result> findTopByOrderByIdDesc();
}
