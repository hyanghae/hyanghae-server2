package com.server2.demo.member.sort;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@RequiredArgsConstructor
public class SortedPlaceNameRepositoryCustomImpl implements SortedPlaceNameRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    public void saveAllSortedPlace(List<SortedPlaceName> placeNames) {
        jdbcTemplate.batchUpdate("insert into " +
                        "sorted_place_name " +
                        "(place_name, member_id, tag_score, image_score, sum) " +
                        "values (?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        SortedPlaceName placeName = placeNames.get(i);
                        ps.setString(1, placeName.getPlaceName());
                        ps.setLong(2, placeName.getMember().getMemberId());

                        // 태그 스코어가 null이면 null을 설정하고, 그렇지 않으면 값 설정
                        if (placeName.getTagScore() != null) {
                            ps.setDouble(3, placeName.getTagScore());
                        } else {
                            ps.setNull(3, Types.DOUBLE);
                        }

                        // 이미지 스코어가 null이면 null을 설정하고, 그렇지 않으면 값 설정
                        if (placeName.getImageScore() != null) {
                            ps.setDouble(4, placeName.getImageScore());
                        } else {
                            ps.setNull(4, Types.DOUBLE);
                        }

                        // 합계가 null이면 null을 설정하고, 그렇지 않으면 값 설정
                        if (placeName.getSum() != null) {
                            ps.setDouble(5, placeName.getSum());
                        } else {
                            ps.setNull(5, Types.DOUBLE);
                        }
                    }


                    @Override
                    public int getBatchSize() {
                        return placeNames.size();
                    }
                });
    }
}
