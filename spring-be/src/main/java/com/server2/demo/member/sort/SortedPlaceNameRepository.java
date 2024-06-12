package com.server2.demo.member.sort;

import com.server2.demo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SortedPlaceNameRepository extends JpaRepository<SortedPlaceName, Long>, SortedPlaceNameRepositoryCustom {

    @Modifying
    @Query("DELETE FROM SortedPlaceName s WHERE s.member = :member")
    void deleteByMember(@Param("member") Member member);
}
