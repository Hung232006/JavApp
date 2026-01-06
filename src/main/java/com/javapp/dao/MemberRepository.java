package com.javapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javapp.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    // Các phương thức tùy chỉnh nếu cần
}
