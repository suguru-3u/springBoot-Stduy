package com.example.springBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springBoot.entity.TaskEntity;


@Repository
public interface TaskReoisitory extends JpaRepository<TaskEntity, Integer>{
	
//	public List<TaskEntity> findByUserIdAndFinish_flg(int userId,boolean finishFlg);
	
	public TaskEntity findById(int taskId);
	
}