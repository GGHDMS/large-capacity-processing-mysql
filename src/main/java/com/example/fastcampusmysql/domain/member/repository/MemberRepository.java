package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    final static private String TABLE = "Member";

    public Optional<Member> findById(Long id) {
        /*
        select *
        from Member
        where id = :id
         */

        String sql = String.format("SELECT * FROM %s WHERE id = :id", TABLE);
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);

        RowMapper<Member> rowMapper = (ResultSet resultSet, int rowNum) -> Member
                .builder()
                .id(resultSet.getLong("id"))
                .email(resultSet.getString("email"))
                .nickname(resultSet.getString("nickname"))
                .birthday(resultSet.getObject("birthday", LocalDate.class))
                .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
                .build();

        Member member = namedParameterJdbcTemplate.queryForObject(sql, param, rowMapper);
        return Optional.ofNullable(member);
    }


    public Member save(Member member) {
        /*
        member id를 보고 갱신 또는 삽입을 정함
        반환값은 id를 담아서 반환한다.
         */
        if (member.getId() == null) {
            return insert(member);
        }

        return update(member);
    }


    public Member insert(Member member) {
        //TODO: 이코드가 작동을 안한다.. 이유를 찾아보자
/*
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("Member")
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        Long id = jdbcInsert.executeAndReturnKey(params).longValue();

        return Member.builder()
                .id(id)
                .nickname(member.getNickname())
                .email(member.getEmail())
                .birthday(member.getBirthday())
                .build();
*/

        String sql = "INSERT INTO Member (nickname, email, birthday, createdAt) " +
                "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql, member.getNickname(), member.getEmail(), member.getBirthday(), member.getCreatedAt());

        // 데이터베이스가 생성한 ID를 얻기 위해 마지막으로 삽입된 ID를 가져올 수 있습니다.
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return Member.builder()
                .id(id)
                .nickname(member.getNickname())
                .email(member.getEmail())
                .birthday(member.getBirthday())
                .createdAt(member.getCreatedAt())
                .build();
    }

    private Member update(Member member) {
        //TODO: implemented
        return member;
    }

}
