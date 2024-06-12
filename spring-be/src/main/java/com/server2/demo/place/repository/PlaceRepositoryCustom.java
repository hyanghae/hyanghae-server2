package com.server2.demo.place.repository;

import com.querydsl.core.Tuple;
import com.server2.demo.member.domain.Member;

import java.util.List;

public interface PlaceRepositoryCustom {

    List<Tuple> findExplorePlaceWithTags(Member member, List<Long> tagIds);
}
