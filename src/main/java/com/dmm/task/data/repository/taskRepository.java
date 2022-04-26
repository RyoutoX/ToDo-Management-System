//リポジトリクラス　JPAを使ってDBアクセスするためのインタフェース
package com.dmm.task.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmm.task.data.entity.task;

public interface taskRepository extends JpaRepository<task, Integer> {

}

