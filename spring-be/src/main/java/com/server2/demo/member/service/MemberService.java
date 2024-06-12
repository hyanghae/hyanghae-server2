package com.server2.demo.member.service;

import com.server2.demo.exception.BaseException;
import com.server2.demo.exception.BaseResponseCode;
import com.server2.demo.member.domain.Member;
import com.server2.demo.member.repository.MemberRepository;
import com.server2.demo.tag.domain.MemberTagLog;
import com.server2.demo.tag.domain.Tag;
import com.server2.demo.tag.repository.MemberTagLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberTagLogRepository memberTagLogRepository;

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NO_ID_EXCEPTION));
    }

    public List<Tag> getRegisteredTag(Member member) { //N+1 문제 대비 -> 페치 조인 적용해보기
        List<MemberTagLog> memberTagLogs = memberTagLogRepository.findByMember(member);
        return memberTagLogs.stream().map(MemberTagLog::getTag).toList();
    }

    public void save(Member member) {
        memberRepository.save(member);
    }
}
