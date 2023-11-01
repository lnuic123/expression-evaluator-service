package com.exercise.expressionevaluator.dao.repository;

import com.exercise.expressionevaluator.dao.model.Expression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpressionRepository extends JpaRepository<Expression, Long> {

    @Query("SELECT e FROM Expression e WHERE e.uuid = ?1")
    Expression findExpressionByUuid(String uuid);

    @Query("SELECT e FROM Expression e WHERE e.name = ?1")
    Expression findExpressionByName(String name);
}
