package com.kxt.yanglao.watch.jpa.reposity;
import com.kxt.yanglao.watch.jpa.entity.Medical;
import com.kxt.yanglao.watch.jpa.entity.Tonometer;
import com.kxt.yanglao.watch.jpa.entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MedicalRepository extends JpaRepository<Medical,Integer>{
}
