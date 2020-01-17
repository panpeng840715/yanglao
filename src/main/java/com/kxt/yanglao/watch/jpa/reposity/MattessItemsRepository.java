package com.kxt.yanglao.watch.jpa.reposity;

import com.kxt.yanglao.watch.jpa.entity.Mattess;
import com.kxt.yanglao.watch.jpa.entity.MattessItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MattessItemsRepository extends JpaRepository<MattessItems,Integer> {
}
