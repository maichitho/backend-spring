package com.mulodo.fiveneed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mulodo.fiveneed.entity.TblQuestion;

public interface QuestionRepository extends JpaRepository<TblQuestion, Long> {


}
