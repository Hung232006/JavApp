package com.javapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javapp.dao.MemberRepository;
import com.javapp.model.Member;

@Controller
public class HomeController {

    private final MemberRepository memberRepository;

    // Constructor injection (BẮT BUỘC)
    public HomeController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("member", new Member());
        return "index";
    }

    @GetMapping("/auth/listDV")
    public String listDV(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        return "listDV";
    }
}
