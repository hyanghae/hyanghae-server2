package com.server2.demo.uploadImage;



import com.server2.demo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UploadImageRepository extends JpaRepository<UploadImage, Long> {

    List<UploadImage> findByMemberOrderByCreatedTimeDesc(Member member);

    @Modifying
    @Query("DELETE FROM UploadImage u WHERE u.member = :member")
    void deleteByMember(@Param("member")Member member); //멤버가 등록한 모든 이미지 삭제

    Optional<UploadImage> findByMemberAndIsSetting(Member member, boolean isSetting);


}
