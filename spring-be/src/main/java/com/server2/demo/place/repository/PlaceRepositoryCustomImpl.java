package com.server2.demo.place.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server2.demo.member.domain.Member;
import com.server2.demo.place.QPlace;
import com.server2.demo.tag.domain.QPlaceTagLog;
import com.server2.demo.tag.domain.QTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlaceRepositoryCustomImpl implements PlaceRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    QPlaceTagLog placeTagLog = QPlaceTagLog.placeTagLog;
    QTag tag = QTag.tag;
    QPlace place = QPlace.place;

    @Override
    public List<Tuple> findExplorePlaceWithTags(Member member, List<Long> tagIds) {
        // 서브쿼리를 사용하여 특정 tagIds에 해당하는 tagScore의 합을 계산
        NumberExpression<Integer> totalTagScore = new CaseBuilder()
                .when(tag.id.in(tagIds))
                .then(placeTagLog.tagScore)
                .otherwise(0)
                .sum();

        return jpaQueryFactory
                .select(
                        place,
                        totalTagScore.as("totalTagScore") // 조건부 tagScore의 합계
                )
                .from(place)
                .join(placeTagLog).on(placeTagLog.place.eq(place))
                .join(placeTagLog.tag, tag)
                .groupBy(place.id)
                .fetch();

    }


    //    @Override
//    public List<Tuple> findExplorePlaceWithTags(Member member, List<Long> tagIds) {
//        // 서브쿼리를 사용하여 특정 tagIds에 해당하는 tagScore의 합을 계산
//        NumberExpression<Integer> totalTagScore = new CaseBuilder()
//                .when(tag.id.in(tagIds))
//                .then(placeTagLog.tagScore)
//                .otherwise(0)
//                .sum();
//
//        return jpaQueryFactory
//                .select(
//                        place,
//                        totalTagScore.as("totalTagScore") // 조건부 tagScore의 합계
//                )
//                .from(place)
//                .join(placeTagLog).on(placeTagLog.place.eq(place))
//                .join(placeTagLog.tag, tag)
//                .leftJoin(placeRegister).on(placeRegister.place.eq(place).and(placeRegister.member.eq(member))) // 여행지 등록 정보를 확인하기 위한 조인
//                .groupBy(place.id)
//                .fetch();
//    }
}
