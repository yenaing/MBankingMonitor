package ua.kiev.sinenko.mbankingmonitor.model.repo;

import ua.kiev.sinenko.mbankingmonitor.model.entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.List;

/**
 * Created by a.sinenko on 09.03.2016.
 */

@Repository
public interface UserInfoRepository extends JpaRepository<Userinfo, BigDecimal> {

    @Query(value = "SELECT t FROM Userinfo t where t.name = :id")
    List<Userinfo> findUserById(@Param("id") BigDecimal id);

    @Query(value = "SELECT t FROM Userinfo t where t.name = :name")
    List<Userinfo> findUserByName(@Param("name") String name);

    @Query(value = "SELECT t FROM Userinfo t where t.name = :name AND t.pass = :pass")
    List<Userinfo> login(@Param("name") String name, @Param("pass") String pass);

}
