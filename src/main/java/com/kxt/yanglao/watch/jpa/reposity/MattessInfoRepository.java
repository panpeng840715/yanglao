package com.kxt.yanglao.watch.jpa.reposity;

import com.kxt.yanglao.watch.jpa.entity.MattessInfo;
import com.kxt.yanglao.watch.jpa.entity.MattessItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MattessInfoRepository extends JpaRepository<MattessInfo,Integer> {
}
