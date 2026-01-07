package com.javapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javapp.dao.MemberRepository;
import com.javapp.model.Member;

@Controller
public class HomeController {

    private final MemberRepository memberRepository;

    // Constructor injection
    public HomeController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Trang index
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("member", new Member());
        return "index";
    }

    // Trang danh sách đoàn viên
    @GetMapping("/auth/listDV")
    public String listDV(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members != null ? members : new ArrayList<>());
        return "listDV";
    }

    // Lấy chi tiết member cho modal
@GetMapping("/home/member/{id}")
public String getMemberPage(@PathVariable String id, Model model) {
        Member member = memberRepository.findById(id).orElse(null);

        if (member == null) {
            model.addAttribute("message", "Đoàn viên không tồn tại!");
            return "fragments/memberNotFound"; // fragment hiển thị lỗi
        }

        model.addAttribute("member", member);
        return "fragments/infor"; // fragment HTML load vào modal
    }
}
