package com.javapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javapp.dao.MemberRepository;
import com.javapp.model.Member;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/list")
    public String listMembers(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        return "listDV";
    }

    @PostMapping("/save")
    public String saveMember(
            @ModelAttribute Member member,
            RedirectAttributes redirectAttributes
    ) {
        memberRepository.save(member);
        redirectAttributes.addFlashAttribute("success", "Lưu thành công!");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(
            @PathVariable("id") String memberID,
            RedirectAttributes redirectAttributes
    ) {
        memberRepository.deleteById(memberID);
        redirectAttributes.addFlashAttribute(
                "success", "Xóa thành công đoàn viên: " + memberID);
        return "redirect:/member/list";
    }

    @GetMapping("/edit/{id}")
    public String editMember(@PathVariable("id") String memberID, Model model) {
        Member member = memberRepository.findById(memberID).orElse(null);
        if (member == null) {
            return "redirect:/member/list";
        }
        model.addAttribute("member", member);
        return "index";
    }
}
