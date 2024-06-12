package com.server2.demo.tag.repository;

import com.server2.demo.member.domain.Member;
import com.server2.demo.tag.domain.MemberTagLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberTagLogRepository  extends JpaRepository<MemberTagLog, Long> {

    List<MemberTagLog> findByMember(Member member);
}
