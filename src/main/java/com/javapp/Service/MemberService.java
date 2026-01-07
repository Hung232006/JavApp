package com.javapp.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javapp.dao.MemberRepository;
import com.javapp.model.Member;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Lấy tất cả member
    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    // Lưu hoặc cập nhật member
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    // Xóa member theo ID
    public void delete(String memberID) {
        if (memberID == null || memberID.isEmpty()) {
            throw new IllegalArgumentException("memberID cannot be null or empty");
        }
        memberRepository.deleteById(memberID);
    }

    // Lấy member theo ID, nếu không tồn tại sẽ ném exception
    public Member getById(String memberID) {
        if (memberID == null || memberID.isEmpty()) {
            throw new IllegalArgumentException("memberID cannot be null or empty");
        }
        return memberRepository.findById(memberID)
                .orElseThrow(() -> new RuntimeException("Member with ID " + memberID + " not found"));
    }

public Member findById(String id) {
    return getById(id);  // getById đã có logic lấy member từ DB
}

}
